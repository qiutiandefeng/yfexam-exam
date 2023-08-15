<template>

  <div>

    <data-table
      ref="pagingTable"
      :options="options"
      :list-query="listQuery"
      @multi-actions="handleMultiAction"
    >
      <template #data-columns>

        <el-table-column
          label="角色ID"
          prop="id"
        />

        <el-table-column
          label="角色名称"
          prop="roleName"
        />

      </template>

    </data-table>
  </div>

</template>

<script>
import DataTable from '@/components/DataTable'

export default {
  name: 'SysRoleList',
  components: { DataTable },
  filters: {

    // 订单状态
    userState(value) {
      const map = {
        '0': '正常',
        '1': '禁用'
      }
      return map[value]
    }
  },
  data() {
    return {

      listQuery: {
        current: 1,
        size: 10,
        params: {
        }
      },

      options: {
        // 列表请求URL
        listUrl: '/exam/api/sys/role/paging',
        // 启用禁用
        stateUrl: '/sys/user/state'
      }
    }
  },

  methods: {

    // 批量操作监听
    handleMultiAction(obj) {
      if (obj.opt === 'cancel') {
        this.handleCancelOrder(obj.ids)
      }
    }
  }
}
</script>
