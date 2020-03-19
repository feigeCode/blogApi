package com.feige.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DruidConfig {


    /**
     * 将自定义的Druid数据源添加到容器中，不再让springboot自动创建
     * 绑定全局配置文件中的druid数据源属性到com.alibaba.druid.pool.DruidDatasource
     * ConfigurationProperties(prefix = "spring.datasource")的作用是将
     * 将全局配置文件中前缀为spring.datasource的属性注入到
     * com.alibaba.druid.pool.DruidDatasource的同名参数中
     * @return
     */
    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource druidDataSource(){
        return new DruidDataSource();
    }

    /**
     * 配置监控管理后台
     * @return
     */
    @Bean
    public ServletRegistrationBean registrationBean(){
        ServletRegistrationBean<StatViewServlet> bean = new ServletRegistrationBean<>(new StatViewServlet(),"/druid/*");
        Map<String, String> map = new HashMap<>();
        map.put("loginUsername","admin");
        map.put("loginPassword","123456");
        //为空所有都可以访问
        map.put("allow","");
        bean.setInitParameters(map);
        return bean;

    }
}
