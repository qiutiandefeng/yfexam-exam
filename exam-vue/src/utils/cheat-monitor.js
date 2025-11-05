// 防作弊监控模块
// 实时采集用户行为数据并上传到服务器

const CheatMonitor = {
  // 配置参数
  config: {
    sampleRate: 50, // 采样率（ms/次）
    uploadInterval: 3000, // 上传间隔（ms）
    cacheLimit: 10 * 1024 * 1024, // 本地缓存上限（10MB）
    apiUrl: '/exam/api/cheat/monitor/upload-behavior' // 上传API地址
  },
  
  // 状态
  state: {
    isMonitoring: false,
    userId: '',
    examId: '',
    paperId: '',
    behaviorData: [],
    lastUploadTime: 0,
    cache: [],
    timer: null
  },
  
  // 初始化监控
  init(userId, examId, paperId, config = {}) {
    this.state.userId = userId;
    this.state.examId = examId;
    this.state.paperId = paperId;
    
    // 合并配置
    Object.assign(this.config, config);
    
    // 开始监控
    this.startMonitoring();
  },
  
  // 开始监控
  startMonitoring() {
    if (this.state.isMonitoring) return;
    
    this.state.isMonitoring = true;
    
    // 采集鼠标轨迹
    this.startMouseTracking();
    
    // 采集键盘输入
    this.startKeyboardTracking();
    
    // 采集窗口状态
    this.startWindowTracking();
    
    // 定时上传数据
    this.startUploadTimer();
    
    console.log('防作弊监控已启动');
  },
  
  // 停止监控
  stopMonitoring() {
    if (!this.state.isMonitoring) return;
    
    this.state.isMonitoring = false;
    
    // 清除定时器
    if (this.state.timer) {
      clearInterval(this.state.timer);
      this.state.timer = null;
    }
    
    // 移除事件监听
    this.removeEventListeners();
    
    // 上传剩余数据
    this.uploadData();
    
    console.log('防作弊监控已停止');
  },
  
  // 采集鼠标轨迹
  startMouseTracking() {
    let lastMousePos = null;
    let lastTime = null;
    
    const handleMouseMove = (e) => {
      const now = Date.now();
      const pos = { x: e.clientX, y: e.clientY };
      
      if (lastMousePos && lastTime) {
        const deltaX = pos.x - lastMousePos.x;
        const deltaY = pos.y - lastMousePos.y;
        const deltaTime = now - lastTime;
        const speed = Math.sqrt(deltaX * deltaX + deltaY * deltaY) / deltaTime;
        
        this.addBehaviorData(1, {
          x: pos.x,
          y: pos.y,
          deltaX: deltaX,
          deltaY: deltaY,
          speed: speed,
          timestamp: now
        });
      }
      
      lastMousePos = pos;
      lastTime = now;
    };
    
    document.addEventListener('mousemove', handleMouseMove);
    this.state.mouseMoveHandler = handleMouseMove;
  },
  
  // 采集键盘输入
  startKeyboardTracking() {
    let lastKeyTime = null;
    
    const handleKeyDown = (e) => {
      const now = Date.now();
      
      if (lastKeyTime) {
        const interval = now - lastKeyTime;
        
        this.addBehaviorData(2, {
          key: e.key,
          keyCode: e.keyCode,
          interval: interval,
          timestamp: now
        });
      }
      
      lastKeyTime = now;
    };
    
    document.addEventListener('keydown', handleKeyDown);
    this.state.keyDownHandler = handleKeyDown;
  },
  
  // 采集窗口状态
  startWindowTracking() {
    let isFocused = document.hasFocus();
    let lastBlurTime = null;
    let blurCount = 0;
    
    const handleFocus = () => {
      const now = Date.now();
      
      if (!isFocused) {
        const blurDuration = now - lastBlurTime;
        blurCount++;
        
        this.addBehaviorData(3, {
          event: 'focus',
          blurDuration: blurDuration,
          blurCount: blurCount,
          timestamp: now
        });
        
        isFocused = true;
      }
    };
    
    const handleBlur = () => {
      const now = Date.now();
      
      if (isFocused) {
        lastBlurTime = now;
        
        this.addBehaviorData(3, {
          event: 'blur',
          blurCount: blurCount,
          timestamp: now
        });
        
        isFocused = false;
      }
    };
    
    window.addEventListener('focus', handleFocus);
    window.addEventListener('blur', handleBlur);
    
    this.state.focusHandler = handleFocus;
    this.state.blurHandler = handleBlur;
  },
  
  // 添加行为数据
  addBehaviorData(type, content) {
    if (!this.state.isMonitoring) return;
    
    const dataItem = {
      type: type,
      content: JSON.stringify(content),
      timestamp: Date.now()
    };
    
    this.state.behaviorData.push(dataItem);
    
    // 检查数据量，超过一定数量立即上传
    if (this.state.behaviorData.length > 100) {
      this.uploadData();
    }
  },
  
  // 定时上传数据
  startUploadTimer() {
    this.state.timer = setInterval(() => {
      this.uploadData();
    }, this.config.uploadInterval);
  },
  
  // 上传数据
  uploadData() {
    if (!this.state.behaviorData.length) return;
    
    const dataToUpload = [...this.state.behaviorData];
    this.state.behaviorData = [];
    
    const params = {
      userId: this.state.userId,
      examId: this.state.examId,
      paperId: this.state.paperId,
      dataList: dataToUpload
    };
    
    // 使用系统的request工具上传数据
    import('@/utils/request').then(({ post }) => {
      post(this.config.apiUrl, params)
        .then(response => {
          if (response.code === 0) {
            console.log('行为数据上传成功');
            // 处理服务器返回的风险评分
            this.handleRiskScore(response.data);
          } else {
            console.error('行为数据上传失败:', response.msg);
            // 保存到本地缓存
            this.saveToCache(dataToUpload);
          }
        })
        .catch(error => {
          console.error('行为数据上传失败:', error);
          // 保存到本地缓存
          this.saveToCache(dataToUpload);
        });
    });
  },
  
  // 保存到本地缓存
  saveToCache(data) {
    // 简单的本地缓存实现，使用localStorage
    try {
      const cache = JSON.parse(localStorage.getItem('cheatMonitorCache') || '[]');
      cache.push(...data);
      
      // 检查缓存大小
      const cacheStr = JSON.stringify(cache);
      if (cacheStr.length > this.config.cacheLimit) {
        // 超过上限，删除最早的数据
        while (cacheStr.length > this.config.cacheLimit && cache.length > 0) {
          cache.shift();
        }
      }
      
      localStorage.setItem('cheatMonitorCache', JSON.stringify(cache));
    } catch (error) {
      console.error('保存缓存失败:', error);
    }
  },
  
  // 从缓存中恢复数据
  restoreFromCache() {
    try {
      const cache = JSON.parse(localStorage.getItem('cheatMonitorCache') || '[]');
      if (cache.length) {
        this.state.behaviorData.push(...cache);
        localStorage.removeItem('cheatMonitorCache');
        console.log('已恢复缓存的行为数据:', cache.length);
      }
    } catch (error) {
      console.error('恢复缓存失败:', error);
    }
  },
  
  // 处理风险评分
  handleRiskScore(riskScoreData) {
    const { riskScore, riskLevel, riskDesc, needAdjustPaper } = riskScoreData;
    
    console.log('风险评分:', riskScore, '风险等级:', riskLevel, '描述:', riskDesc);
    
    // 根据风险等级显示不同的提示
    if (riskLevel === 1) {
      // 可疑
      this.showWarning('系统检测到您的行为异常，请规范考试行为');
    } else if (riskLevel === 2) {
      // 高风险
      this.showWarning('系统检测到您有作弊嫌疑，将密切监控您的行为');
    }
    
    // 如果需要调整试卷，触发相应逻辑
    if (needAdjustPaper) {
      this.handlePaperAdjustment();
    }
  },
  
  // 显示警告
  showWarning(message) {
    // 使用系统的消息提示组件
    if (typeof this.$message !== 'undefined') {
      this.$message.warning(message);
    } else {
      alert(message);
    }
  },
  
  // 处理试卷调整
  handlePaperAdjustment() {
    // TODO: 实现试卷动态调整逻辑
    console.log('需要调整试卷');
  },
  
  // 移除事件监听
  removeEventListeners() {
    if (this.state.mouseMoveHandler) {
      document.removeEventListener('mousemove', this.state.mouseMoveHandler);
      this.state.mouseMoveHandler = null;
    }
    
    if (this.state.keyDownHandler) {
      document.removeEventListener('keydown', this.state.keyDownHandler);
      this.state.keyDownHandler = null;
    }
    
    if (this.state.focusHandler) {
      window.removeEventListener('focus', this.state.focusHandler);
      this.state.focusHandler = null;
    }
    
    if (this.state.blurHandler) {
      window.removeEventListener('blur', this.state.blurHandler);
      this.state.blurHandler = null;
    }
  }
};

export default CheatMonitor;