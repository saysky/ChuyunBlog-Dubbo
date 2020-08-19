package com.example.blog.api.service;


import com.example.blog.api.common.base.BaseService;
import com.example.blog.api.entity.Comment;

import java.util.List;

/**
 * <pre>
 *     回复业务逻辑接口
 * </pre>
 *
 * @author : author
 * @date : 2018/1/22
 */
public interface CommentService extends BaseService<Comment, Long> {

    /**
     * 根据用户Id删除回复
     *
     * @param userId 用户Id
     */
    Integer deleteByUserId(Long userId);

    /**
     * 根据回复接受人Id删除回复
     *
     * @param acceptId 用户Id
     */
    Integer deleteByAcceptUserId(Long acceptId);

    /**
     * 根据文章ID获得评论列表
     * @param postId
     * @return
     */
    List<Comment> findByPostId(Long postId);
}
