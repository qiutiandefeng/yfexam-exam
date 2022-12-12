<template>
  <div>

    <div class="title-box">
      <div>用户注册</div>
    </div>

    <el-form ref="postForm" :model="postForm" :rules="loginRules">

      <el-form-item prop="userName">
        <el-input
          v-model="postForm.userName"
          style="width: 100%"
          placeholder="用户名"
          prefix-icon="el-icon-mobile"
        />
      </el-form-item>

      <el-form-item prop="realName">
        <el-input
          v-model="postForm.realName"
          style="width: 100%"
          placeholder="姓名"
          prefix-icon="el-icon-user"
        />
      </el-form-item>

      <el-form-item prop="password">
        <el-input
          v-model="postForm.password"
          show-password
          style="width: 100%"
          placeholder="密码"
          type="password"
          prefix-icon="el-icon-lock"
        />
      </el-form-item>

      <el-form-item>
        <el-button :loading="loading" type="primary" style="width: 100%" @click.native.prevent="handleReg">注册</el-button>
      </el-form-item>

    </el-form>

    <div style="display: flex; align-items: center; justify-content: flex-end">
      <el-link type="primary" href="/#/login">已有账号</el-link>
    </div>

  </div>

</template>

<script>
import { mapGetters } from 'vuex'

export default {
  data() {
    return {
      postForm: {
        mobile: '',
        password: ''
      },
      loginRules: {
        password: [{ required: true, trigger: 'blur', message: '登录密码不能为空！' }],
        userName: [{ required: true, trigger: 'blur', message: '用户名不能为空！' }],
        realName: [{ required: true, trigger: 'blur', message: '姓名不能为空！' }],
        captchaValue: [{ required: true, trigger: 'blur', message: '验证码不能为空' }]
      },
      loading: false
    }
  },
  computed: {
    ...mapGetters([
      'siteData'
    ])
  },

  methods: {

    handleReg() {
      this.$refs.postForm.validate(valid => {
        if (valid) {
          this.loading = true
          this.$store.dispatch('user/reg', this.postForm)
            .then(() => {
              this.$router.push({ path: this.redirect || '/admin/dashboard' })
              this.loading = false
            })
            .catch(() => {
              this.loading = false
            })
        }
      })
    }

  }
}
</script>

