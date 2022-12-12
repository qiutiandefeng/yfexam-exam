import { post } from '@/utils/request'

export function updateData(data) {
  return post('/exam/api/sys/user/update', data)
}

export function saveData(data) {
  return post('/exam/api/sys/user/save', data)
}

export function userReg(data) {
  return post('/exam/api/sys/user/reg', data)
}
