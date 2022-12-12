<template>
  <div class="app-container">

    <el-form ref="postForm" :model="postForm" :rules="rules" label-position="top" label-width="100%">

      <el-card>

        <el-form-item label="题库名称" prop="title">
          <el-input v-model="postForm.title" />
        </el-form-item>

        <el-form-item label="题库备注" prop="remark">
          <el-input v-model="postForm.remark" type="textarea" />
        </el-form-item>

      </el-card>

      <div style="margin-top: 20px">
        <el-button type="primary" @click="submitForm">保存</el-button>
        <el-button type="info" @click="onCancel">返回</el-button>
      </div>

    </el-form>

  </div>
</template>

<script>

import { fetchDetail, saveData } from '@/api/qu/repo'

export default {
  name: 'QuRepoDetail',
  data() {
    return {
      postForm: {

      },
      loading: false,
      rules: {

        title: [
          { required: true, message: '题库名称不能为空！' }
        ]
      }
    }
  },
  created() {
    const id = this.$route.params.id
    if (typeof id !== 'undefined') {
      this.fetchData(id)
    }
  },
  methods: {

    // 添加子项
    handleAdd() {
      this.postForm.answerList.push({ isRight: false, content: '', analysis: '' })
    },

    fetchData(id) {
      const params = { id: id }
      fetchDetail(params).then(response => {
        this.postForm = response.data
      })
    },
    submitForm() {
      console.log(JSON.stringify(this.postForm))

      this.$refs.postForm.validate((valid) => {
        if (!valid) {
          return
        }

        saveData(this.postForm).then(() => {
          this.$notify({
            title: '成功',
            message: '题库保存成功！',
            type: 'success',
            duration: 2000
          })

          this.$router.push({ name: 'ListRepo' })
        })
      })
    },

    onCancel() {
      this.$router.push({ name: 'ListRepo' })
    }

  }
}
</script>
