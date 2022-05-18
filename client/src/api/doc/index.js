import request from '@/utils/request'

export function docPrivilege(query) {
  return request({
    url: '/api/doc/docPrivilege',
    method: 'get',
    params: query
  })
}

export function page(query) {
  return request({
    url: '/api/doc/page',
    method: 'get',
    params: query
  })
}

export function showPage(query) {
  return request({
    url: '/api/wiki/show/docs/page/' + query.pageNum + '/' + query.pageSize,
    method: 'get',
    params: query
  })
}

export function get(id) {
  return request({
    url: '/api/doc/get/' + id,
    method: 'get'
  })
}

export function dashboardPage(query, userId) {
  return request({
    url: '/api/doc/dashboard/' + userId,
    method: 'get',
    params: query
  })
}
export function showDocPage(query, userId) {
  return request({
    url: '/api/doc/page/' + userId,
    method: 'get',
    params: query
  })
}

export function addOrUpdate(doc) {
  return request({
    url: '/api/doc/addOrUpdate',
    method: 'post',
    data: doc
  })
}

export function deletedById(id) {
  return request({
    url: '/api/doc/deleted/' + id,
    method: 'delete'
  })
}
