import { post } from '@/utils/request'

/**
 * 创建试卷
 * @param data
 */
export function createPaper(data) {
  return post('/exam/api/paper/paper/create-paper', data)
}

/**
 * 试卷详情
 * @param data
 */
export function paperDetail(data) {
  return post('/exam/api/paper/paper/paper-detail', data)
}

/**
 * 题目详情
 * @param data
 */
export function quDetail(data) {
  return post('/exam/api/paper/paper/qu-detail', data)
}

/**
 * 填充答案
 * @param data
 */
export function fillAnswer(data) {
  return post('/exam/api/paper/paper/fill-answer', data)
}

/**
 * 交卷
 * @param data
 */
export function handExam(data) {
  return post('/exam/api/paper/paper/hand-exam', data)
}

/**
 * 试卷详情
 * @param data
 */
export function paperResult(data) {
  return post('/exam/api/paper/paper/paper-result', data)
}

/**
 * 错题训练
 * @param data
 */
export function training(data) {
  return post('/exam/api/paper/paper/training', data)
}


/**
 * 检查是否有进行中的考试
 * @returns {*}
 */
export function checkProcess() {
  return post('/exam/api/paper/paper/check-process', {})
}
