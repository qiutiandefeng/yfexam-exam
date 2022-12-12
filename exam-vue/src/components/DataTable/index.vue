<template>
  <div class="app-container">

    <div class="filter-container">

      <slot name="filter-content" />

      <el-row>
        <el-col>
          <el-button v-if="options.addRoute" type="primary" icon="el-icon-plus" @click="handleAdd">添加</el-button>
        </el-col>
      </el-row>

    </div>

    <div v-show="multiShow && options.multiActions" class="filter-container">

      <el-select v-model="multiNow" :placeholder="selectedLabel" class="filter-item" style="width: 130px" @change="handleOption">
        <el-option
          v-for="item in options.multiActions"
          :key="item.value"
          :label="item.label"
          :value="item.value"
        />
      </el-select>

    </div>

    <el-table
      v-loading="listLoading"
      :data="dataList.records"
      :header-cell-style="{'background':'#f2f3f4', 'color':'#555', 'font-weight':'bold', 'line-height':'32px'}"
      border
      fit
      highlight-current-row
      @selection-change="handleSelection"
    >

      <el-table-column
        v-if="options.multi"
        align="center"
        type="selection"
        width="55"
      />

      <slot name="data-columns" />

    </el-table>

    <pagination v-show="dataList.total>0" :total="dataList.total" :page.sync="listQuery.current" :limit.sync="listQuery.size" @pagination="getList" />
  </div>
</template>

<script>
import { fetchList, deleteData, changeState } from '@/api/common'
import Pagination from '@/components/Pagination'

export default {
  name: 'PagingTable',
  components: { Pagination },
  // 组件入参
  props: {
    options: {
      type: Object,
      default: () => {
        return {
          // 批量操作
          multiActions: [],
          // 列表请求URL
          listUrl: '/exam/api',
          // 删除请求URL
          deleteUrl: '',
          // 启用禁用
          stateUrl: '',
          // 可批量操作
          multi: false
        }
      }
    },

    // 列表查询参数
    listQuery: {
      type: Object,
      default: () => {
        return {
          current: 1,
          size: 10,
          params: {},
          t: 0
        }
      }
    }
  },
  data() {
    return {
      // 接口数据返回
      dataList: {
        total: 0
      },
      // 数据加载标识
      listLoading: true,
      // 选定和批量操作
      selectedIds: [],
      selectedObjs: [],
      // 显示已中多少项
      selectedLabel: '',
      // 显示批量操作
      multiShow: false,
      // 批量操作的标识
      multiNow: ''
    }
  },
  watch: {

    // 检测查询变化
    listQuery: {
      handler() {
        this.getList()
      },
      deep: true
    }
  },
  created() {
    this.getList()
  },
  methods: {

    /**
     * 添加数据跳转
     */
    handleAdd() {
      if (this.options.addRoute) {
        this.$router.push({ name: this.options.addRoute, params: {}})
        return
      }
      console.log('未设置添加数据跳转路由！')
    },

    /**
     * 查询数据列表
     */
    getList() {
      this.listLoading = true
      this.listQuery.t = new Date().getTime()
      fetchList(this.options.listUrl, this.listQuery).then(response => {
        this.dataList = response.data
        this.listLoading = false
      })
    },

    /**
     * 搜索
     */
    handleFilter() {
      // 重新搜索
      this.getList()
    },

    /**
     * 批量操作回调
     */
    handleOption(v) {
      this.multiNow = ''

      // 内部消化的操作
      if (v === 'delete') {
        this.handleDelete()
        return
      }

      if (v === 'enable') {
        this.handleState(0)
        return
      }

      if (v === 'disable') {
        this.handleState(1)
        return
      }

      // 向外回调的操作
      this.$emit('multi-actions', { opt: v, ids: this.selectedIds })
    },

    /**
     * 修改状态，启用禁用
     */
    handleState(state) {
      // 修改状态
      changeState(this.options.stateUrl, this.selectedIds, state).then(response => {
        if (response.code === 0) {
          this.$message({
            type: 'success',
            message: '状态修改成功!'
          })

          // 重新搜索
          this.getList()
        }
      })
    },

    /**
     * 删除数据
     */
    handleDelete() {
      if (this.selectedIds.length === 0) {
        this.$message({
          message: '请至少选择一条数据！',
          type: 'warning'
        })
        return
      }

      // 删除
      this.$confirm('确实要删除吗?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        deleteData(this.options.deleteUrl, this.selectedIds).then(() => {
          this.$message({
            type: 'success',
            message: '删除成功!'
          })
          this.getList()
        })
      })
    },

    /**
     * 列表多选操作
     * @param val
     */
    handleSelection(val) {
      const ids = []
      val.forEach(row => {
        ids.push(row.id)
      })

      this.selectedObjs = val
      this.selectedIds = ids
      this.multiShow = ids.length > 0
      this.selectedLabel = '已选' + ids.length + '项'

      this.$emit('select-changed', { ids: this.selectedIds, objs: this.selectedObjs })
    }

  }
}
</script>

<style>

  .filter-container .filter-item{
    margin-left: 5px;
  }

  .filter-container .filter-item:first-child{
    margin-left: 0px;
  }
</style>
