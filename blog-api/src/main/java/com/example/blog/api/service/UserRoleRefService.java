package com.example.blog.api.service;

import com.example.blog.api.common.base.BaseService;
import com.example.blog.api.entity.UserRoleRef;


public interface UserRoleRefService extends BaseService<UserRoleRef, Long> {

    /**
     * 根据用户Id删除
     *
     * @param userId 用户Id
     */
    void deleteByUserId(Long userId);


}
