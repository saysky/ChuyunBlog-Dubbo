package com.example.blog.service.service.impl;

import cn.hutool.core.util.StrUtil;
import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.blog.api.entity.BlackWord;
import com.example.blog.service.mapper.BlackWordMapper;
import com.example.blog.api.service.BlackWordService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author author
 * @date 2020/4/4 10:47 上午
 */
@Service(
        version = "1.0.0",
        application = "${dubbo.application.id}",
        protocol = "${dubbo.protocol.id}",
        registry = "${dubbo.registry.id}"
)
public class BlackWordServiceImpl implements BlackWordService {

    @Autowired
    private BlackWordMapper blackWordMapper;

    @Override
    public BaseMapper<BlackWord> getRepository() {
        return blackWordMapper;
    }

    @Override
    public QueryWrapper<BlackWord> getQueryWrapper(BlackWord blackWord) {
        //对指定字段查询
        QueryWrapper<BlackWord> queryWrapper = new QueryWrapper<>();
        if (blackWord != null) {
            if (StrUtil.isNotBlank(blackWord.getContent())) {
                queryWrapper.eq("content", blackWord.getContent());
            }
        }
        return queryWrapper;
    }
}
