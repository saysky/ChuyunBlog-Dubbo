package com.example.blog.web.controller.admin;

import com.alibaba.dubbo.config.annotation.Reference;
import com.example.blog.web.controller.common.BaseController;
import com.example.blog.api.dto.JsonResult;
import com.example.blog.api.entity.Permission;
import com.example.blog.api.entity.User;
import com.example.blog.api.service.PermissionService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * <pre>
 *     后台首页控制器
 * </pre>
 *
 */
@Slf4j
@Controller
@RequestMapping(value = "/admin")
public class AdminController extends BaseController {

    @Reference(version = "1.0.0",
            application = "${dubbo.application.id}",
            interfaceName = "com.example.blog.api.service.PermissionService",
            check = false,
            timeout = 3000,
            retries = 0
    )
    private PermissionService permissionService;

    /**
     * 请求后台页面
     *
     * @param model model
     * @return 模板路径admin/admin_index
     */
    @GetMapping
    public String index(Model model) {

        return "admin/admin_index";
    }


    /**
     * 获得当前用户的菜单
     *
     * @return
     */
    @GetMapping(value = "/currentMenus")
    @ResponseBody
    public JsonResult getMenu() {
        Long userId = getLoginUserId();
        List<Permission> permissions = permissionService.findPermissionTreeByUserIdAndResourceType(userId, "menu");
        return JsonResult.success("", permissions);
    }

    /**
     * 获得当前登录用户
     */
    @GetMapping(value = "/currentUser")
    @ResponseBody
    public JsonResult currentUser() {
        User user = getLoginUser();
        if (user != null) {
            return JsonResult.success("", user);
        }
        return JsonResult.error("用户未登录");
    }


}
