package dev.mehdizebhi.springbootdataredisdemo.config;

import com.redis.om.spring.annotations.EnableRedisDocumentRepositories;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRedisDocumentRepositories(basePackages = "dev.mehdizebhi.springbootdataredisdemo.*")
public class RedisConfig { }
