import { post } from '@/utils/request'

export function fetchList() {
  return post('/exam/api/sys/role/list', {})
}

