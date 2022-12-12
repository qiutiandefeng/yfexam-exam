<template>
  <div class="app-container">
    <el-row :gutter="20">
      <el-col :span="16">
        <el-card class="box">
          <div slot="header" class="clearfix">
            <span>项目信息</span>
            <el-button style="float: right; padding: 3px 0; font-size: 12px" type="text">系统版本：{{ version }}</el-button>
          </div>

          <div style="line-height: 32px;">

            <div class="title" style="padding-top: 0px">系统介绍</div>
            云帆考试系统是一款基于SpringBoot+Vue开发的考试系统。包含在线考试、用户体系、错题训练、考试规则、智能算分等考试功能，流程通畅。

            <div class="title">技术栈</div>
            <div class="tags">
              <el-tag>SpringBoot</el-tag>
              <el-tag>Shiro</el-tag>
              <el-tag>MyBatis Plus</el-tag>
              <el-tag>Redis</el-tag>
              <el-tag>JWT</el-tag>
              <el-tag>Vue</el-tag>
              <el-tag>Vue2</el-tag>
              <el-tag>ElementUI</el-tag>
            </div>

            <div class="title">产品功能</div>
            <div>
              <div>系统完善：完善的前后端分离架构、规范的接口文档</div>
              <div>权限控制：基于Shiro和JWT开发的权限控制功能。</div>
              <div>基础功能：系统配置、用户管理、部门管理、角色管理等。</div>
              <div>题库管理：支持单选题、多选题、判断题、支持试题批量导入导出</div>
              <div>考试权限：支持指定考试范围：完全公开、指定某些部门人员考试</div>
              <div>在线考试：学员在线考试、查看分数、考试错题训练。</div>
            </div>

          </div>

        </el-card>
      </el-col>
      <el-col :span="8">

        <el-card class="box">
          <div slot="header" class="clearfix">
            <span>支持信息</span>
          </div>

          <div>

            <div class="title" style="padding-top: 0px">商业咨询</div>
            <div style="display: flex;">
              <div style="flex-grow: 1;">
                <div>微信：gyh_yinzi（微信号）或搜索手机18603038204</div>
                <div>手机：<a href="tel:18603038204">18603038204（郭女士）</a></div>
                <div>邮箱：<a href="mailto:835487894@qq.com">835487894@qq.com</a> </div>
              </div>
              <div style="width: 120px; display: flex; align-items: center; flex-direction: column; align-content: flex-end">
                <img style="width: 100px; height: 100px" src="@/assets/contact.png" >
                <div style="font-size: 12px; text-align: center; width: 100%">微信扫码加我好友</div>
              </div>

            </div>

            <div class="title">支持网站</div>
            <div>
              <div>企业官网：<a href="https://www.yfhl.net" target="_blank">https://www.yfhl.net</a></div>
              <div>在线试用：<a href="https://exam.yfhl.net" target="_blank">https://exam.yfhl.net</a></div>
            </div>

            <div class="title">在线试用</div>
            <div style="display: flex; align-items: center">
              <img src="@/assets/h5.png" style="width: 150px; height: 150px; border: #efefef 1px solid">
              <img src="@/assets/mp.jpg" style="width: 150px; height: 150px; border: #efefef 1px solid; margin-left: 20px">
            </div>

          </div>

        </el-card>

      </el-col>
    </el-row>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import config from '../../../package.json'

export default {
  name: 'Dashboard',
  data() {
    return {
      currentRole: 'adminDashboard',
      version: config.version
    }
  },
  computed: {
    ...mapGetters([
      'roles',
      'siteData'
    ])
  },
  created() {

  }
}
</script>

<style scoped>
.box{
  height: calc(100vh - 120px);
  font-size: 14px;
  line-height: 28px;
}
.title{
  font-size: 18px;
  font-weight: 700;
  padding-bottom: 10px;
  padding-top: 20px;
  margin-bottom: 10px;
  border-bottom: #eee 1px dotted;
}

.tags span{
  margin-right: 10px;
}

.box a{
  color: #20a0ff;
}

.box a:hover{
  color: #ff0000;
}
</style>
