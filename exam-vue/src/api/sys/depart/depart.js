import { post } from '@/utils/request'

export function pagingTree(data) {
  return post('/exam/api/sys/depart/paging', data)
}

export function fetchTree(data) {
  return post('/exam/api/sys/depart/tree', data)
}

export function fetchDetail(id) {
  const data = { id: id }
  return post('/exam/api/sys/depart/detail', data)
}

export function deleteData(ids) {
  const data = { ids: ids }
  return post('/exam/api/sys/depart/delete', data)
}

export function saveData(data) {
  return post('/exam/api/sys/depart/save', data)
}

export function sortData(id, sort) {
  const data = { id: id, sort: sort }
  return post('/exam/api/sys/depart/sort', data)
}
