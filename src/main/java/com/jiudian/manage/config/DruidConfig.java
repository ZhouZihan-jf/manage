package com.jiudian.manage.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DruidConfig {

    //创建连接池时有空指针是配置的问题
    @ConfigurationProperties(prefix = "spring.datasource.druid")
    @Bean
    public DataSource druidDataSource(){
        return new DruidDataSource();
    }

    @Bean
    //后台监控
    public ServletRegistrationBean statViewServlet(){
        //获取的druid后台
        ServletRegistrationBean<StatViewServlet> bean = new ServletRegistrationBean<>(new StatViewServlet(),"/druid/*");

        HashMap<String,String> initParameters = new HashMap<>();
        //后台登录
        initParameters.put("loginUsername","root");//key是不可变的就是loginusername和loginpassword
        initParameters.put("loginPassword","991231");

        //允许谁可以访问
        initParameters.put("allow","");//允许key就是allow
        //禁止谁访问
        initParameters.put("tanggou","192.168.11.123");//key:value就是名字与对应ip对应


        bean.setInitParameters(initParameters);//设置初始化参数
        return bean;
    }

    //过滤器
    public FilterRegistrationBean webStatFilter(){
        FilterRegistrationBean bean = new FilterRegistrationBean();

        bean.setFilter(new WebStatFilter());

        //可以过滤哪些请求呢
        Map<String,String> initParameters = new HashMap<>();

        //这些信息不进行统计
        initParameters.put("exclusions","*.js,*.css,/druid/*");


        bean.setInitParameters(initParameters);

        return bean;
    }

}
