<template>
  <el-form>
    <el-form-item label="用户名">
      <el-input v-model.trim="user.name" readonly />
    </el-form-item>
    <el-form-item label="密码">
      <el-input v-model.trim="user.password" type="password" placeholder="不修改请留空" />
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="submit">修改</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
import { updateData } from '@/api/sys/user/user'

export default {
  props: {
    user: {
      type: Object,
      default: () => {
        return {
          password: ''
        }
      }
    }
  },
  methods: {
    async logout() {
      await this.$store.dispatch('user/logout')
      this.$router.push(`/login?redirect=${this.$route.fullPath}`)
    },

    async submit() {
      updateData(this.user).then(() => {
        this.$notify({
          title: '成功',
          message: '用户资料保存成功！！',
          type: 'success',
          duration: 2000
        })

        this.logout()
      })
    }
  }
}
</script>
