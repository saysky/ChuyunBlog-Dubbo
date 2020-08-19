package com.example.blog.core.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.blog.api.entity.UserRoleRef;
import com.example.blog.core.mapper.UserRoleRefMapper;
import com.example.blog.api.service.UserRoleRefService;
import org.springframework.beans.factory.annotation.Autowired;


@Service(
        version = "1.0.0",
        application = "${dubbo.application.id}",
        protocol = "${dubbo.protocol.id}",
        registry = "${dubbo.registry.id}"
)
public class UserRoleRefServiceImpl implements UserRoleRefService {

    @Autowired
    private UserRoleRefMapper roleRefMapper;


    @Override
    public void deleteByUserId(Long userId) {
        roleRefMapper.deleteByUserId(userId);
    }

    @Override
    public BaseMapper<UserRoleRef> getRepository() {
        return roleRefMapper;
    }

    @Override
    public QueryWrapper<UserRoleRef> getQueryWrapper(UserRoleRef userRoleRef) {
        //对指定字段查询
        QueryWrapper<UserRoleRef> queryWrapper = new QueryWrapper<>();
        if (userRoleRef != null) {
            if (userRoleRef.getUserId() != null) {
                queryWrapper.eq("user_id", userRoleRef.getUserId());
            }
            if (userRoleRef.getRoleId() != null) {
                queryWrapper.eq("role_id", userRoleRef.getRoleId());
            }
        }
        return queryWrapper;
    }

}
