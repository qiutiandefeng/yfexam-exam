import { post } from '@/utils/request'

export function fetchList(url, query) {
  return post(url, query)
}

export function fetchDetail(url, id) {
  return post(url, { 'id': id })
}

export function saveData(url, data) {
  return post(url, data)
}

export function deleteData(url, ids) {
  return post(url, { 'ids': ids })
}

export function changeState(url, ids, state) {
  return post(url, { 'ids': ids, 'state': state })
}
