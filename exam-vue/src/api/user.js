import { post } from '@/utils/request'

export function login(data) {
  return post('/exam/api/sys/user/login', data)
}

export function getInfo(token) {
  return post('/exam/api/sys/user/info?token=' + token)
}

export function logout() {
  return post('/exam/api/sys/user/logout', {})
}

export function reg(data) {
  return post('/exam/api/sys/user/reg', data)
}
