import { post } from '@/utils/request'

/**
 * 题库详情
 * @param data
 */
export function fetchDetail(data) {
  return post('/exam/api/repo/detail', data)
}

/**
 * 保存题库
 * @param data
 */
export function saveData(data) {
  return post('/exam/api/repo/save', data)
}

/**
 * 保存题库
 * @param data
 */
export function fetchPaging(data) {
  return post('/exam/api/repo/paging', data)
}

/**
 * 题库批量操作
 * @param data
 */
export function batchAction(data) {
  return post('/exam/api/repo/batch-action', data)
}
