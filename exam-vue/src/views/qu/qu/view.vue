<template>
  <div class="app-container">

    <el-card style="margin-top: 20px">

      <div class="qu-content">

        <p>【{{ quData.quType===1?'单选题':'多选题' }}】{{ quData.content }}</p>
        <p v-if="quData.image!=null && quData.image!=''">
          <el-image :src="quData.image" style="max-width:80%;" />
        </p>
        <div v-if="quData.quType === 1">
          <el-radio-group v-model="radioValues" readonly>
            <el-radio v-for="an in quData.answerList" :label="an.id" readonly>{{ an.content }}</el-radio>
          </el-radio-group>
        </div>

        <!-- 多选题 -->
        <div v-if="quData.quType === 2">
          <el-checkbox-group v-model="multiValues" readonly>
            <el-checkbox v-for="an in quData.answerList" :label="an.id">{{ an.content }}</el-checkbox>
          </el-checkbox-group>
        </div>

      </div>

    </el-card>

    <el-card class="qu-analysis" style="margin-top: 20px">
      整题解析：
      <p>{{ quData.analysis }}</p>
      <p v-if="!quData.analysis">暂无解析内容！</p>
    </el-card>

    <el-card class="qu-analysis" style="margin-top: 20px; margin-bottom: 30px">
      选项解析：
      <div v-for="an in quData.answerList" v-if="an.analysis" class="qu-analysis-line">
        <p style="color: #555;">{{ an.content }}：</p>
        <p style="color: #1890ff;">{{ an.analysis }}</p>
      </div>
      <p v-if="analysisCount === 0">暂无选项解析</p>

    </el-card>

    <el-button type="info" @click="onCancel">返回</el-button>

  </div>
</template>

<script>
import { fetchDetail } from '@/api/qu/qu'

export default {
  name: 'QuView',
  data() {
    return {

      quData: {

      },

      radioValues: '',
      multiValues: [],
      analysisCount: 0

    }
  },
  created() {
    const id = this.$route.params.id
    if (typeof id !== 'undefined') {
      this.fetchData(id)
    }
  },
  methods: {

    fetchData(id) {
      fetchDetail(id).then(response => {
        this.quData = response.data

        this.quData.answerList.forEach((an) => {
          // 解析数量
          if (an.analysis) {
            this.analysisCount += 1
          }

          // 用户选定的
          if (an.isRight) {
            if (this.quData.quType === 1) {
              this.radioValues = an.id
            } else {
              this.multiValues.push(an.id)
            }
          }
        })
      })
    },
    onCancel() {
      this.$router.push({ name: 'ListTran' })
    }

  }
}
</script>

<style scoped>

  .qu-content{
    padding-bottom: 10px;
  }

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

