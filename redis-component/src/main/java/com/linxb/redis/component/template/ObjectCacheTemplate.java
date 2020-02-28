package com.linxb.redis.component.template;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class ObjectCacheTemplate {

    @Autowired
    private RedisTemplate redisTemplate;


}
