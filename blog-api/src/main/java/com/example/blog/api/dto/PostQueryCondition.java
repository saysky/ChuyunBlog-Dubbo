package com.example.blog.api.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author author
 * @date 2020/3/12 4:53 下午
 */
@Data
public class PostQueryCondition implements Serializable {

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 标签ID
     */
    private Long tagId;

    /**
     * 分类ID
     */
    private Long cateId;

    /**
     * 关键字
     */
    private String keywords;

}
