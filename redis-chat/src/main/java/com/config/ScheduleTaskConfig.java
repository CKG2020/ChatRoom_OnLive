package com.config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

public class ScheduleTaskConfig {
    private static  final Logger log=LoggerFactory.getLogger(ScheduleTaskConfig.class);
    private  static  final Long MINUTE_30=1000*60*30L;


    private StringRedisTemplate redisTemplate;

}
