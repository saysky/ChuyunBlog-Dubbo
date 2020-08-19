package com.example.blog.api.dto;

import com.example.blog.api.vo.SearchVo;
import lombok.Data;

import java.io.Serializable;

/**
 * 查询封装类
 *
 * @author author
 */
@Data
public class QueryCondition<T> implements Serializable {

    /**
     * 根据字段筛选
     */
    private T data;

    /**
     * 一般筛选
     */
    private SearchVo searchVo;


    public QueryCondition() {
    }

    public QueryCondition(T data) {
        this.data = data;
    }

    public QueryCondition(T data, SearchVo searchVo) {
        this.data = data;
        this.searchVo = searchVo;
    }
}
