package com.example.blog.core.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.example.blog.api.entity.RolePermissionRef;
import com.example.blog.core.mapper.RolePermissionRefMapper;
import com.example.blog.api.service.RolePermissionRefService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service(
        version = "1.0.0",
        application = "${dubbo.application.id}",
        protocol = "${dubbo.protocol.id}",
        registry = "${dubbo.registry.id}"
)
public class RolePermissionRefServiceImpl implements RolePermissionRefService {

    @Autowired
    private RolePermissionRefMapper rolePermissionRefMapper;

    @Override
    public void deleteRefByRoleId(Long roleId) {
        rolePermissionRefMapper.deleteByRoleId(roleId);
    }

    @Override
    public void saveByRolePermissionRef(RolePermissionRef rolePermissionRef) {
        rolePermissionRefMapper.insert(rolePermissionRef);
    }

    @Override
    public void batchSaveByRolePermissionRef(List<RolePermissionRef> rolePermissionRefs) {
        rolePermissionRefMapper.batchInsert(rolePermissionRefs);
    }
}