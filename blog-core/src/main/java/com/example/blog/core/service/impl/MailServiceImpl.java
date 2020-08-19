package com.example.blog.core.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.example.blog.api.util.SensUtils;
import com.example.blog.api.service.MailService;
import io.github.biezhi.ome.OhMyEmail;
import org.springframework.beans.factory.annotation.Value;

import javax.mail.MessagingException;

/**
 * <pre>
 *     邮件发送业务逻辑实现类
 * </pre>
 *
 * @author : author
 * @date : 2018/1/23
 */
@Service(
        version = "1.0.0",
        application = "${dubbo.application.id}",
        protocol = "${dubbo.protocol.id}",
        registry = "${dubbo.registry.id}"
)
public class MailServiceImpl implements MailService {

    @Value("${mail.smtp.host}")
    private String host;

    @Value("${mail.smtp.username}")
    private String username;

    @Value("${mail.smtp.password}")
    private String password;

    @Value("${mail.from.name}")
    private String fromName;

    /**
     * 发送邮件
     *
     * @param to      to 接收者
     * @param title   subject 标题
     * @param content content 内容
     */
    @Override
    public void sendMail(String to, String title, String content) throws MessagingException {
        //配置邮件服务器
        SensUtils.configMail(host, username, password);
        OhMyEmail.subject(title)
                .from(fromName)
                .to(to)
                .text(content)
                .send();
    }
}
