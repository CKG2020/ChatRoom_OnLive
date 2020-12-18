package com.easyarch.config;


import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * 配置数据源，指定mapper包的扫描路径和SessionFactory对象
 */
@Configuration
@MapperScan(value = "com.example.javaanli.demo.dao1.mapper",sqlSessionFactoryRef = "sqlSessionFactory")
public class MyDataSource {

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;

    @Bean
    public DataSource dataSource() {
    	DruidDataSource datasource = new DruidDataSource();
        datasource.setUrl(url);
        datasource.setUsername(username);
        datasource.setPassword(password);
        return datasource;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory( ) throws Exception {
    	// 设置mapper的xml文件路径
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource());
//        Resource[] resources = new PathMatchingResourcePatternResolver()
//                .getResources("classpath:mapper/*.xml");
//        factoryBean.setMapperLocations(resources);
        // 设置mybatis-config.xml的路径
//        Resource config = new PathMatchingResourcePatternResolver()
//                .getResource("classpath:mybatis-config.xml");
//        factoryBean.setConfigLocation(config);
        return factoryBean.getObject();
    }

    @Bean(name = "dataServiceSqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate() throws Exception {
    	// 使用上面配置的Factory
        SqlSessionTemplate template = new SqlSessionTemplate(sqlSessionFactory());
        return template;
    }

}
