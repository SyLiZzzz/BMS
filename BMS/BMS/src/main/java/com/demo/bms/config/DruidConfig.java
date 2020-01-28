package com.demo.bms.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
//配置Druid数据连接池
@Configuration
public class DruidConfig {
    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource druid() {
        return  new DruidDataSource();
    }

    //配置Druid监控
    //1、配置一个管理后台的Servlet
    @Bean
    public ServletRegistrationBean servletRegistrationBean() {
        ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        Map<String,String> initParm  =new HashMap<>();
        initParm.put("loginUsername","admin");
        initParm.put("loginPassword","111111");
        initParm.put("allow","");//不写则默认允许所有
        bean.setInitParameters(initParm);
        return bean;
    }

    //2、配置一个监控filter
    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean<WebStatFilter> bean = new FilterRegistrationBean();
        bean.setFilter(new WebStatFilter());
        Map<String,String> initParm  =new HashMap<>();
        initParm.put("exclusions","*.js,*.css,/druid/*");
        bean.setInitParameters(initParm);
        bean.setUrlPatterns(Arrays.asList("/*"));
        return bean;
    }
}
