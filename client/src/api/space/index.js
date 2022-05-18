import request from '@/utils/request'

export function showAll() {
  return request({
    url: '/api/wiki/show/spaces/all',
    method: 'get'
  })
}

export function page(query) {
  return request({
    url: '/api/space/page',
    method: 'get',
    params: query
  })
}

export function get(id) {
  return request({
    url: '/api/space/get/' + id,
    method: 'get'
  })
}

export function showPage(query, userId) {
  return request({
    url: '/api/space/page/' + userId,
    method: 'get',
    params: query
  })
}

export function addOrUpdate(params) {
  return request({
    url: '/api/space/addOrUpdate',
    method: 'post',
    data: params
  })
}

export function deletedById(id) {
  return request({
    url: '/api/space/deleted/' + id,
    method: 'delete'
  })
}
