import { post } from '@/utils/request'

/**
 * 题库详情
 * @param data
 */
export function fetchDetail(id) {
  return post('/exam/api/exam/exam/detail', { id: id })
}

/**
 * 保存题库
 * @param data
 */
export function saveData(data) {
  return post('/exam/api/exam/exam/save', data)
}

/**
 * 题库详情
 * @param data
 */
export function fetchList() {
  return post('/exam/api/exam/exam/paging', { current: 1, size: 100 })
}
