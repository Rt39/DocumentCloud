import request from '@/utils/request'

export function userSpacePage(query, spaceId) {
  return request({
    url: '/api/uspace/page/' + spaceId,
    method: 'get',
    params: query
  })
}

export function userSpaceAll(spaceId) {
  return request({
    url: '/api/wiki/users/spaces/all/' + spaceId,
    method: 'get'
  })
}

export function spaceNonUserAll(spaceId) {
  return request({
    url: '/api/wiki/users/spaces/all/non/' + spaceId,
    method: 'get'
  })
}

export function addOrUpdate(params) {
  return request({
    url: '/api/uspace/addOrUpdate',
    method: 'post',
    data: params
  })
}

export function deletedById(id) {
  return request({
    url: '/api/uspace/deleted/' + id,
    method: 'delete'
  })
}
