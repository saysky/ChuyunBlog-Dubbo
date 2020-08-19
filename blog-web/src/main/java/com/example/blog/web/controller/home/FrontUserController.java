package com.example.blog.web.controller.home;

import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.blog.web.controller.common.BaseController;
import com.example.blog.api.dto.PostQueryCondition;
import com.example.blog.api.entity.Post;
import com.example.blog.api.entity.User;
import com.example.blog.api.service.*;
import com.example.blog.api.util.PageUtil;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author author
 * @date 2020/3/11 4:59 下午
 */
@Controller
public class FrontUserController extends BaseController {

    @Reference(version = "1.0.0",
            application = "${dubbo.application.id}",
            interfaceName = "com.example.blog.api.service.CategoryService",
            check = false,
            timeout = 3000,
            retries = 0
    )
    private CategoryService categoryService;

    @Reference(version = "1.0.0",
            application = "${dubbo.application.id}",
            interfaceName = "com.example.blog.api.service.PostService",
            check = false,
            timeout = 3000,
            retries = 0
    )
    private PostService postService;

    @Reference(version = "1.0.0",
            application = "${dubbo.application.id}",
            interfaceName = "com.example.blog.api.service.UserService",
            check = false,
            timeout = 3000,
            retries = 0
    )
    private UserService userService;

    @Reference(version = "1.0.0",
            application = "${dubbo.application.id}",
            interfaceName = "com.example.blog.api.service.CommentService",
            check = false,
            timeout = 3000,
            retries = 0
    )
    private CommentService commentService;

    @Reference(version = "1.0.0",
            application = "${dubbo.application.id}",
            interfaceName = "com.example.blog.api.service.TagService",
            check = false,
            timeout = 3000,
            retries = 0
    )
    private TagService tagService;

    /**
     * 用户列表
     *
     * @param model
     * @return
     */
    @GetMapping("/user")
    public String hotUser(Model model) {
        List<User> users = userService.getHotUsers(100);
        model.addAttribute("users", users);
        return "home/user";
    }


    /**
     * 用户帖子列表
     *
     * @param model
     * @return
     */
    @GetMapping("/user/{id}")
    public String index(@PathVariable("id") Long userId,
                        @RequestParam(value = "page", defaultValue = "1") Integer pageNumber,
                        @RequestParam(value = "size", defaultValue = "10") Integer pageSize,
                        @RequestParam(value = "sort", defaultValue = "createTime") String sort,
                        @RequestParam(value = "order", defaultValue = "desc") String order,
                        Model model) {

        User user = userService.get(userId);
        if(user == null) {
            return renderNotFound();
        }

        Page page = PageUtil.initMpPage(pageNumber, pageSize, sort, order);
        PostQueryCondition condition = new PostQueryCondition();
        condition.setUserId(userId);
        Page<Post> postPage = postService.findPostByCondition(condition, page);
        model.addAttribute("posts", postPage);
        model.addAttribute("user", user);
        return "home/user_post";
    }


}
