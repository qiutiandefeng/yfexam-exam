import { post } from '@/utils/request'

/**
 * 题库详情
 * @param data
 */
export function nextQu(examId, quId) {
  return post('/exam/api/user/wrong-book/next', { examId: examId, quId: quId })
}

