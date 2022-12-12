<template>
  <div class="content">
    <el-upload
      v-model="fileUrl"
      :action="server"
      :accept="accept"
      :before-remove="beforeRemove"
      :on-remove="handleRemove"
      :on-success="handleSuccess"
      :on-exceed="handleExceed"

      :drag="listType!=='picture'"
      :limit="limit"
      :headers="header"
      :file-list="fileList"
      :list-type="listType"
    >

      <el-button v-if="listType==='picture'" size="small" type="primary">点击上传</el-button>

      <i v-if="listType!=='picture'" class="el-icon-upload" />
      <div v-if="listType!=='picture'" class="el-upload__text">
        将文件拖到此处，或
        <em>点击上传</em>
      </div>
      <div v-if="tips" slot="tip" class="el-upload__tip">{{ tips }}</div>
    </el-upload>

  </div>

</template>

<script>

import { getToken } from '@/utils/auth'

export default {
  name: 'FileUploadLocal',
  props: {
    value: String,
    accept: String,
    tips: String,
    listType: String,
    limit: {
      type: Number,
      default: 1
    }
  },
  data() {
    return {
      server: `${process.env.VUE_APP_BASE_API}/common/api/file/upload`,
      fileList: [],
      fileUrl: '',
      header: {}
    }
  },

  watch: {
    // 检测查询变化
    value: {
      handler() {
        this.fillValue()
      }
    }
  },

  created() {
    this.fillValue()
    this.header = { token: getToken() }
  },

  methods: {

    fillValue() {
      this.fileList = []
      this.fileUrl = this.value
      if (this.fileUrl) {
        this.fileList = [{ name: this.fileUrl, url: this.fileUrl }]
      }
    },

    // 文件超出个数限制时的钩子
    handleExceed() {
      this.$message.warning(`每次只能上传 ${this.limit} 个文件`)
    },
    // 删除文件之前的钩子
    beforeRemove() {
      return this.$confirm(`确定移除文件吗？`)
    },

    // 文件列表移除文件时的钩子
    handleRemove() {
      this.$emit('input', '')
      this.fileList = []
    },

    // 文件上传成功时的钩子
    handleSuccess(response) {
      if (response.code === 1) {
        this.$message({
          type: 'error',
          message: response.msg
        })

        this.fileList = []
        return
      }
      this.$emit('input', response.data.url)
    }

  }
}
</script>
