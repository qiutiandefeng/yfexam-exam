<template>

  <el-select
    v-model="currentValue"
    :multiple="multi"
    :remote-method="fetchData"
    filterable
    remote
    clearable
    placeholder="选择或搜索考试"
    class="filter-item"
    @change="handlerChange"
  >
    <el-option
      v-for="item in dataList"
      :key="item.id"
      :label="item.title"
      :value="item.id"
    />
  </el-select>

</template>

<script>

import { fetchList } from '@/api/exam/exam'

export default {
  name: 'ExamSelect',
  props: {
    multi: Boolean,
    value: Array,
    default: String
  },
  data() {
    return {
      // 下拉选项值
      dataList: [],
      currentValue: []
    }
  },

  watch: {
    // 检测查询变化
    value: {
      handler() {
        this.currentValue = this.value
      }
    }
  },
  created() {
    this.currentValue = this.value
    this.fetchData()
  },
  methods: {

    fetchData() {
      fetchList().then(response => {
        this.dataList = response.data.records
      })
    },
    handlerChange(e) {
      console.log(e)

      this.$emit('change', e)
      this.$emit('input', e)
    }
  }
}
</script>
