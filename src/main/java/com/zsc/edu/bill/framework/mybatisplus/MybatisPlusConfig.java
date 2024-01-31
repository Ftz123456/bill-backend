package com.zsc.edu.bill.framework.mybatisplus;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Yao
 */
@MapperScan(basePackages = "com.zsc.edu.bill.modules.*.repo")
@Configuration
public class MybatisPlusConfig {

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
//        // 添加数据权限插件
//       MyDataPermissionInterceptor dataPermissionInterceptor = new MyDataPermissionInterceptor();
//        // 添加自定义的数据权限处理器
//        dataPermissionInterceptor.setDataPermissionHandler(new MyDataPermissionHandler());
//        interceptor.addInnerInterceptor(dataPermissionInterceptor);


        return interceptor;
    }

}
