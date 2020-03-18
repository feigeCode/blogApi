package com.feige;


import com.feige.commen.utils.redis.RedisCache;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class FeigeApplicationTests {

    @Autowired
    RedisCache redisCache;
    
    @Test
    public void contextLoads() {
        Object feige = redisCache.getCacheObject("feige");
        System.out.println(feige);
    }

}
