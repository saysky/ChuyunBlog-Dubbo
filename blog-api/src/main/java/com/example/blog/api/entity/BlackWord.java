package com.example.blog.api.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.example.blog.api.common.base.BaseEntity;
import lombok.Data;

/**
 * 黑名单词语
 * @author author
 * @date 2020/4/4 10:02 上午
 */
@Data
@TableName("black_word")
public class BlackWord extends BaseEntity {

    /**
     * 内容
     */
    private String content;
}
