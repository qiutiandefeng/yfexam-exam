<template>
  <div class="app-container">

    <el-card style="margin-top: 20px">

      <div class="qu-content">

        <p>【{{ quData.quType | quTypeFilter }}】{{ quData.content }}</p>
        <p v-if="quData.image!=null && quData.image!=''">
          <el-image :src="quData.image" style="max-width:100%;" />
        </p>

        <div v-if="quData.quType === 1 || quData.quType===3 ">
          <el-radio-group v-model="answerValues[0]" readonly>
            <el-radio v-for="an in quData.answerList" :key="an.id" :label="an.id" readonly>
              {{ an.abc }}.{{ an.content }}
              <div v-if="an.image!=null && an.image!=''" style="clear: both">
                <el-image :src="an.image" style="max-width:100%;" />
              </div>
            </el-radio>
          </el-radio-group>
        </div>

        <!-- 多选题 -->
        <div v-if="quData.quType === 2">
          <el-checkbox-group v-model="answerValues" readonly>
            <el-checkbox v-for="an in quData.answerList" :key="an.id" :label="an.id">
              {{ an.abc }}.{{ an.content }}
              <div v-if="an.image!=null && an.image!=''" style="clear: both">
                <el-image :src="an.image" style="max-width:100%;" />
              </div>
            </el-checkbox>
          </el-checkbox-group>
        </div>

        <div v-if="analysisShow" style="margin-top: 20px; color: #1890ff; font-weight: bold">
          正确答案：{{ rightTags.join(' ') }}
        </div>

      </div>

    </el-card>

    <el-card v-if="analysisShow" class="qu-analysis" style="margin-top: 20px">
      整题解析：
      <p>{{ quData.analysis }}</p>
      <p v-if="!quData.analysis">暂无解析内容！</p>
    </el-card>

    <el-card v-if="analysisShow" class="qu-analysis" style="margin-top: 20px;">
      选项解析：
      <div v-for="an in quData.answerList" v-if="an.analysis" class="qu-analysis-line">
        <p style="color: #555;">{{ an.content }}：</p>
        <p style="color: #1890ff;">{{ an.analysis }}</p>
      </div>
      <p v-if="analysisCount === 0">暂无选项解析</p>

    </el-card>

    <div style="padding-top: 30px">
      <el-button type="primary" @click="handNext">继续下一题</el-button>
      <el-button type="info" @click="onCancel">返回</el-button>
    </div>

  </div>
</template>

<script>
import { fetchDetail } from '@/api/qu/qu'
import { nextQu } from '@/api/user/book'

export default {
  name: 'BookTrain',
  data() {
    return {

      examId: '',
      quId: '',
      tags: ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N'],
      analysisShow: false,
      quData: {

      },
      answerValues: [],
      rightValues: [],
      rightTags: []

    }
  },
  created() {
    this.examId = this.$route.params.examId

    this.fetchNextQu()
  },
  methods: {

    // 清理值
    clearValues() {
      this.answerValues = []
      this.rightValues = []
      this.analysisShow = false
      this.rightTags = []
    },

    // 查找试卷详情
    fetchQuDetail(id) {
      // 当前赋值
      this.quId = id
      this.clearValues()

      fetchDetail(id).then(response => {
        // 题目信息
        this.quData = response.data

        // 保存正确答案
        this.quData.answerList.forEach((an, index) => {
          an.abc = this.tags[index]

          // 用户选定的
          if (an.isRight) {
            this.rightValues.push(an.id)
            this.rightTags.push(an.abc)
          }
        })
      })
    },

    fetchNextQu() {
      // 查找下一个
      nextQu(this.examId, this.quId).then(response => {
        this.fetchQuDetail(response.data.id)
      })
    },
    onCancel() {
      // this.$router.push({ name: 'ListTran' })
      this.$router.push({ name: 'BookList' })
    },

    handNext() {
      // 直接显示下一个
      if (this.analysisShow) {
        // 正确显示下一个
        this.fetchNextQu()
      } else {
        // 直接判断正确性
        if (this.rightValues.join(',') === this.answerValues.join(',')) {
          this.$message({
            message: '回答正确，你好棒哦！',
            type: 'success'
          })

          // 正确显示下一个
          this.fetchNextQu()
        } else {
          // 错误显示解析
          this.analysisShow = true

          this.$message({
            message: '很遗憾，做错了呢，请参考答案解析！',
            type: 'error'
          })
        }
      }
    }

  }
}
</script>

<style scoped>

  .qu-content div{
    line-height: 30px;
  }

  .qu-analysis p{
    color: #555; font-size: 14px
  }
  .qu-analysis-line{
    margin-top: 20px; border-bottom: #eee 1px solid
  }

  .el-checkbox-group label,.el-radio-group label{
    width: 100%;
  }

</style>

