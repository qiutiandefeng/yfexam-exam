import { post } from '@/utils/request'

/**
 * 开始训练了
 * @param mode
 * @param repoId
 * @param userId
 * @returns {*}
 */
export function startTrain(mode, repoId, userId, clear) {
  return post('/exam/api/user/repo/start', { mode: mode, repoId: repoId, userId: userId, clear: clear })
}

/**
 * 开始训练了
 * @param mode
 * @param repoId
 * @param userId
 * @returns {*}
 */
export function fillResult(id, answers, isRight) {
  return post('/exam/api/user/repo/fill', { id: id, answers: answers, isRight: isRight })
}

/**
 * 是否存在训练
 * @param mode
 * @param repoId
 * @param userId
 * @returns {*}
 */
export function hasTrain(mode, repoId, userId) {
  return post('/exam/api/user/repo/check', { mode: mode, repoId: repoId, userId: userId })
}

/**
 * 查找答题卡
 * @param mode
 * @param repoId
 * @param userId
 * @returns {*}
 */
export function listCard(mode, repoId, userId) {
  return post('/exam/api/user/repo/card', { mode: mode, repoId: repoId, userId: userId })
}

/**
 * 下一题或者下一题
 * @param mode
 * @param repoId
 * @param userId
 * @param sequence
 * @returns {*}
 */
export function nextQu(mode, repoId, userId, sequence) {
  return post('/exam/api/user/repo/next', { mode: mode, repoId: repoId, userId: userId, sequence: sequence })
}

