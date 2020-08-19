package com.example.blog.web.controller.home;

import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.blog.api.dto.QueryCondition;
import com.example.blog.api.enums.PostTypeEnum;
import com.example.blog.web.controller.common.BaseController;
import com.example.blog.api.dto.PostQueryCondition;
import com.example.blog.api.entity.Category;
import com.example.blog.api.entity.Post;
import com.example.blog.api.service.CategoryService;
import com.example.blog.api.service.PostService;
import com.example.blog.api.service.TagService;
import com.example.blog.api.util.PageUtil;
import org.apache.commons.lang3.StringUtils;

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
public class FrontCategoryController extends BaseController {


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
            interfaceName = "com.example.blog.api.service.CategoryService",
            check = false,
            timeout = 3000,
            retries = 0
    )
    private CategoryService categoryService;


    @Reference(version = "1.0.0",
            application = "${dubbo.application.id}",
            interfaceName = "com.example.blog.api.service.TagService",
            check = false,
            timeout = 3000,
            retries = 0
    )
    private TagService tagService;
    /**
     * 分类列表
     *
     * @param model
     * @return
     */
    @GetMapping("/category")
    public String category(@RequestParam(value = "keywords", required = false) String keywords,
                           Model model) {
        Category category = new Category();
        category.setCateName(keywords);
        List<Category> categories = categoryService.findAll(new QueryCondition<>(category));
        model.addAttribute("categories", categories);
        return "home/category";
    }


    /**
     * 分类对应的帖子列表
     *
     * @param model
     * @return
     */
    @GetMapping("/category/{id}")
    public String index(@PathVariable("id") Long cateId,
                        @RequestParam(value = "page", defaultValue = "1") Integer pageNumber,
                        @RequestParam(value = "size", defaultValue = "10") Integer pageSize,
                        @RequestParam(value = "sort", defaultValue = "createTime") String sort,
                        @RequestParam(value = "order", defaultValue = "desc") String order,
                        Model model) {

        Category category = categoryService.get(cateId);
        if(category == null) {
            return renderNotFound();
        }

        Page page = PageUtil.initMpPage(pageNumber, pageSize, sort, order);
        PostQueryCondition condition = new PostQueryCondition();
        condition.setCateId(cateId);
        Page<Post> postPage = postService.findPostByCondition(condition, page);
        model.addAttribute("posts", postPage);
        model.addAttribute("category", category);
        return "home/category_post";
    }


}
