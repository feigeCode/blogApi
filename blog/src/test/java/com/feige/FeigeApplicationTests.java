package com.feige;


import com.alibaba.druid.pool.DruidDataSource;
import com.feige.common.utils.redis.RedisCache;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;


@SpringBootTest
public class FeigeApplicationTests {

    @Autowired
    RedisCache redisCache;

    @Autowired
    DataSource dataSource;
    
    @Test
    public void contextLoads() {
        //Object feige = redisCache.getCacheObject("feige");
        //System.out.println(feige);
        DruidDataSource druidDataSource = (DruidDataSource) dataSource;
        System.out.println(druidDataSource.getActiveCount());
    }

}
