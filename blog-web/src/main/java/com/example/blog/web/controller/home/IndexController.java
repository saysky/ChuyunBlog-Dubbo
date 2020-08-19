package com.example.blog.web.controller.home;

import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.blog.web.controller.common.BaseController;
import com.example.blog.api.dto.PostQueryCondition;
import com.example.blog.api.entity.*;
import com.example.blog.api.service.*;
import com.example.blog.api.util.PageUtil;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author author
 * @date 2020/3/9 11:00 上午
 */

@Controller
public class IndexController extends BaseController {

    @Reference(version = "1.0.0",
            application = "${dubbo.application.id}",
            interfaceName = "com.example.blog.api.service.PostService",
            check = false,
            timeout = 3000,
            retries = 0
    )
    private PostService postService;

    @GetMapping("/")
    public String index(@RequestParam(value = "page", defaultValue = "1") Integer pageNumber,
                        @RequestParam(value = "size", defaultValue = "10") Integer pageSize,
                        @RequestParam(value = "sort", defaultValue = "createTime") String sort,
                        @RequestParam(value = "order", defaultValue = "desc") String order,
                        @RequestParam(value = "keywords", required = false) String keywords,
                        Model model) {

        Page page = PageUtil.initMpPage(pageNumber, pageSize, sort, order);
        PostQueryCondition condition = new PostQueryCondition();
        condition.setKeywords(keywords);
        Page<Post> postPage = postService.findPostByCondition(condition, page);
        model.addAttribute("posts", postPage);
        return "home/index";
    }


}
