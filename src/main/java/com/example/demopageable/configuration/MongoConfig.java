package com.example.demopageable.configuration;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@Configuration
public class MongoConfig {

    @Bean
    public TaskScheduler mongoReconnectTaskScheduler() {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setThreadNamePrefix("mongo.reconnect.scheduler-");
        scheduler.setPoolSize(1);
        scheduler.afterPropertiesSet();
        scheduler.getScheduledThreadPoolExecutor().setMaximumPoolSize(2);
        return scheduler;
    }

    @Bean
    public MongoClientOptions mongoOptions(@Value("${application.mongo.timeoutMilliseconds}") int mongoTimeoutMilliseconds) {
        return MongoClientOptions.builder().serverSelectionTimeout(mongoTimeoutMilliseconds).build();
    }

    @Bean
    public MongoTemplate mongoTemplate(@Value("${spring.data.mongodb.database}") String database, MongoClient mongoClient) {
        return new MongoTemplate(mongoClient, database);
    }
}
