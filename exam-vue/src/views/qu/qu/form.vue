<template>
  <div class="app-container">

    <el-form ref="postForm" :model="postForm" :rules="rules" label-position="left" label-width="150px">

      <el-card>

        <el-form-item label="题目类型 " prop="quType">

          <el-select v-model="postForm.quType" :disabled="quTypeDisabled" class="filter-item" @change="handleTypeChange">
            <el-option
              v-for="item in quTypes"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>

        </el-form-item>

        <el-form-item label="难度等级 " prop="level">

          <el-select v-model="postForm.level" class="filter-item">
            <el-option
              v-for="item in levels"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>

        </el-form-item>

        <el-form-item label="归属题库" prop="repoIds">

          <repo-select v-model="postForm.repoIds" :multi="true" />

        </el-form-item>

        <el-form-item label="题目内容" prop="content">
          <el-input v-model="postForm.content" type="textarea" />
        </el-form-item>

        <el-form-item label="试题图片">
          <file-upload v-model="postForm.image" accept=".jpg,.jepg,.png" />
        </el-form-item>

        <el-form-item label="整题解析" prop="oriPrice">
          <el-input v-model="postForm.analysis" :precision="1" :max="999999" type="textarea" />
        </el-form-item>

      </el-card>

      <div v-if="postForm.quType!==4" class="filter-container" style="margin-top: 25px">

        <el-button class="filter-item" type="primary" icon="el-icon-plus" size="small" plain @click="handleAdd">
          添加
        </el-button>

        <el-table
          :data="postForm.answerList"
          :border="true"
          style="width: 100%;"
        >
          <el-table-column
            label="是否答案"
            width="120"
            align="center"
          >
            <template v-slot="scope">

              <el-checkbox v-model="scope.row.isRight">答案</el-checkbox>

            </template>

          </el-table-column>

          <el-table-column
            v-if="itemImage"
            label="选项图片"
            width="120px"
            align="center"
          >
            <template v-slot="scope">

              <file-upload
                v-model="scope.row.image"
                accept=".jpg,.jepg,.png"
              />

            </template>
          </el-table-column>

          <el-table-column
            label="答案内容"
          >
            <template v-slot="scope">
              <el-input v-model="scope.row.content" type="textarea" />
            </template>
          </el-table-column>

          <el-table-column
            label="答案解析"
          >
            <template v-slot="scope">
              <el-input v-model="scope.row.analysis" type="textarea" />
            </template>
          </el-table-column>

          <el-table-column
            label="操作"
            align="center"
            width="100px"
          >
            <template v-slot="scope">
              <el-button type="danger" icon="el-icon-delete" circle @click="removeItem(scope.$index)" />
            </template>
          </el-table-column>

        </el-table>

      </div>

      <div style="margin-top: 20px">
        <el-button type="primary" @click="submitForm">保存</el-button>
        <el-button type="info" @click="onCancel">返回</el-button>
      </div>

    </el-form>

  </div>
</template>

<script>
import { fetchDetail, saveData } from '@/api/qu/qu'
import RepoSelect from '@/components/RepoSelect'
import FileUpload from '@/components/FileUpload'

export default {
  name: 'QuDetail',
  components: { FileUpload, RepoSelect },
  data() {
    return {

      quTypeDisabled: false,
      itemImage: true,

      levels: [
        { value: 1, label: '普通' },
        { value: 2, label: '较难' }
      ],

      quTypes: [{
        value: 1,
        label: '单选题'
      }, {
        value: 2,
        label: '多选题'
      },
      {
        value: 3,
        label: '判断题'
      }
      ],

      postForm: {
        repoIds: [],
        tagList: [],
        answerList: []
      },
      rules: {
        content: [
          { required: true, message: '题目内容不能为空！' }
        ],

        quType: [
          { required: true, message: '题目类型不能为空！' }
        ],

        level: [
          { required: true, message: '必须选择难度等级！' }
        ],

        repoIds: [
          { required: true, message: '至少要选择一个题库！' }
        ]
      }
    }
  },
  created() {
    const id = this.$route.params.id
    if (typeof id !== 'undefined') {
      this.quTypeDisabled = true
      this.fetchData(id)
    }
  },
  methods: {

    handleTypeChange(v) {
      this.postForm.answerList = []
      if (v === 3) {
        this.postForm.answerList.push({ isRight: true, content: '正确', analysis: '' })
        this.postForm.answerList.push({ isRight: false, content: '错误', analysis: '' })
      }

      if (v === 1 || v === 2) {
        this.postForm.answerList.push({ isRight: false, content: '', analysis: '' })
        this.postForm.answerList.push({ isRight: false, content: '', analysis: '' })
        this.postForm.answerList.push({ isRight: false, content: '', analysis: '' })
        this.postForm.answerList.push({ isRight: false, content: '', analysis: '' })
      }
    },

    // 添加子项
    handleAdd() {
      this.postForm.answerList.push({ isRight: false, content: '', analysis: '' })
    },

    removeItem(index) {
      this.postForm.answerList.splice(index, 1)
    },

    fetchData(id) {
      fetchDetail(id).then(response => {
        this.postForm = response.data
      })
    },
    submitForm() {
      console.log(JSON.stringify(this.postForm))

      let rightCount = 0

      this.postForm.answerList.forEach(function(item) {
        if (item.isRight) {
          rightCount += 1
        }
      })

      if (this.postForm.quType === 1) {
        if (rightCount !== 1) {
          this.$message({
            message: '单选题答案只能有一个',
            type: 'warning'
          })

          return
        }
      }

      if (this.postForm.quType === 2) {
        if (rightCount < 2) {
          this.$message({
            message: '多选题至少要有两个正确答案！',
            type: 'warning'
          })

          return
        }
      }

      if (this.postForm.quType === 3) {
        if (rightCount !== 1) {
          this.$message({
            message: '判断题只能有一个正确项！',
            type: 'warning'
          })

          return
        }
      }

      this.$refs.postForm.validate((valid) => {
        if (!valid) {
          return
        }

        saveData(this.postForm).then(response => {
          this.postForm = response.data
          this.$notify({
            title: '成功',
            message: '试题保存成功！',
            type: 'success',
            duration: 2000
          })

          this.$router.push({ name: 'ListQu' })
        })
      })
    },
    onCancel() {
      this.$router.push({ name: 'ListQu' })
    }

  }
}
</script>

<style scoped>

</style>

