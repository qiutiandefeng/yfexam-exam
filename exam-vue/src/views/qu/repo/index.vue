<template>

  <data-table
    ref="pagingTable"
    :options="options"
    :list-query="listQuery"
  >
    <template #filter-content>

      <el-input v-model="listQuery.params.title" placeholder="搜索题库名称" style="width: 200px;" class="filter-item" />

    </template>

    <template #data-columns>

      <el-table-column
        label="题库ID"
        prop="id"
        align="center"
      />

      <el-table-column
        label="题库名称"
      >

        <template slot-scope="data">

          <router-link :to="{ name: 'UpdateRepo', params:{id: data.row.id}}">
            {{ data.row.title }}
          </router-link>

        </template>

      </el-table-column>

      <el-table-column
        label="单选题数量"
        prop="radioCount"
        align="center"
      />

      <el-table-column
        label="多选题数量"
        prop="multiCount"
        align="center"
      />

      <el-table-column
        label="判断题数量"
        prop="judgeCount"
        align="center"
      />

      <el-table-column
        label="创建时间"
        align="center"
        prop="createTime"
      />

    </template>

  </data-table>

</template>

<script>
import DataTable from '@/components/DataTable'

export default {
  name: 'QuList',
  components: { DataTable },
  data() {
    return {

      listQuery: {
        current: 1,
        size: 10,
        params: {
          title: ''
        }
      },

      options: {

        // 可批量操作
        multi: true,

        // 批量操作列表
        multiActions: [
          {
            value: 'delete',
            label: '删除'
          }
        ],
        // 列表请求URL
        listUrl: '/exam/api/repo/paging',
        // 删除请求URL
        deleteUrl: '/exam/api/repo/delete',
        // 启用禁用
        stateUrl: '/qu/repo/state',
        // 添加数据路由
        addRoute: 'AddRepo'
      }
    }
  },
  methods: {

  }
}
</script>
