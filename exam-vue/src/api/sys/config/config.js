import { post } from '@/utils/request'

// 获得用户协议详情，固定ID为0
export function fetchDetail() {
  return post('/exam/api/sys/config/detail', { id: '1' })
}

export function saveData(data) {
  return post('/exam/api/sys/config/save', data)
}
