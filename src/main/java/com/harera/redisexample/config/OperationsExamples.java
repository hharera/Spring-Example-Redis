package com.harera.redisexample.config;


import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
public class OperationsExamples {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Scheduled(fixedRate = 1000)
    public void operationsForValues() {
        ValueOperations<String, Object> stringObjectValueOperations = redisTemplate.opsForValue();

        stringObjectValueOperations.set("key", "value");
        stringObjectValueOperations.get("key");

        stringObjectValueOperations.set("key", "value", 1000);

        stringObjectValueOperations.setIfAbsent("key", "value");
        stringObjectValueOperations.setIfAbsent("key", "value", Duration.ofMillis(1000));
        
        stringObjectValueOperations.multiSet(null);
        stringObjectValueOperations.multiGet(null);

        stringObjectValueOperations.increment("key");
        stringObjectValueOperations.increment("key", 100);

        stringObjectValueOperations.decrement("key");
        stringObjectValueOperations.decrement("key", 100);

        stringObjectValueOperations.append("key", "value");
        stringObjectValueOperations.get("key", 0, 100);
    }
}
