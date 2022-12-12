import variables from '@/styles/element-variables.scss'
import defaultSettings from '@/settings'
import { fetchDetail } from '@/api/sys/config/config'

const { showSettings, tagsView, fixedHeader, sidebarLogo } = defaultSettings

const state = {
  theme: variables.theme,
  showSettings: showSettings,
  tagsView: tagsView,
  fixedHeader: fixedHeader,
  sidebarLogo: sidebarLogo,
  siteData: {}
}

const mutations = {
  CHANGE_SETTING: (state, { key, value }) => {
    if (state.hasOwnProperty(key)) {
      state[key] = value
    }
  },
  SET_SITE_DATA: (state, siteData) => {
    state.siteData = siteData
  }

}

const actions = {
  changeSetting({ commit }, data) {
    commit('CHANGE_SETTING', data)
  },
  // 获取网站配置信息
  getSite({ commit }) {
    return new Promise((resolve, reject) => {
      fetchDetail({}).then(response => {
        const { data } = response
        commit('SET_SITE_DATA', data)
        resolve(data)
      }).catch(error => {
        reject(error)
      })
    })
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}

