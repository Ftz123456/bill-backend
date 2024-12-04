package com.zsc.edu.bill.modules.system.utils;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.Getter;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.MessageFormat;

/**
 * @author ftz
 * 创建时间:2024/4/5 17:24
 * 描述: TODO
 */
@Component
public class sendMail {
    @jakarta.annotation.Resource
    JavaMailSenderImpl mailSender;
    @Getter
    private  int randomCode;

    @Value("${spring.mail.username}")
    private String username;

    /**
     * 读取邮件模板
     * 替换模板中的信息
     *
     * @param title 内容
     * @return
     */
    public String buildContent(String title) {
        //加载邮件html模板
        Resource resource = new ClassPathResource("mailTemplate/mailtemplate.ftl");
        InputStream inputStream = null;
        BufferedReader fileReader = null;
        StringBuffer buffer = new StringBuffer();
        String line = "";
        try {
            inputStream = resource.getInputStream();
            fileReader = new BufferedReader(new InputStreamReader(inputStream));
            while ((line = fileReader.readLine()) != null) {
                buffer.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        //替换html模板中的参数
        return MessageFormat.format(buffer.toString(), title);
    }
    public void sendEmailMessage(String email) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
             randomCode = (int) ((Math.random() * 9 + 1) * 100000);
            // RandomStringUtils
            //邮箱发送内容组成
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setSubject("主题");

            helper.setText(buildContent(randomCode + ""), true);
            helper.setTo(email);
            helper.setFrom("发件人名字" + '<' + username + '>');
            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}
