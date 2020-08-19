package com.example.blog.api.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.example.blog.api.common.base.BaseEntity;
import lombok.Data;

/**
 * <pre>
 *     文章分类
 * </pre>
 *
 * @author : author
 */
@Data
@TableName("category")
public class Category extends BaseEntity {

    /**
     * 分类名称
     */
    private String cateName;

    /**
     * 分类排序号
     */
    private Integer cateSort;

    /**
     * 分类描述
     */
    private String cateDesc;
}
