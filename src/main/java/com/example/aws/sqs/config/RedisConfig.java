package com.example.aws.sqs.config;

import com.example.aws.sqs.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.data.redis.JedisClientConfigurationBuilderCustomizer;
import org.springframework.cache.CacheManager;
import org.springframework.cloud.aws.cache.config.annotation.CacheClusterConfig;
import org.springframework.cloud.aws.cache.config.annotation.EnableElastiCache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
@EnableElastiCache({@CacheClusterConfig(name="first-redis-cache-for-ec2")})
public class RedisConfig {

  @Value("${spring.redis.host}")
  private String host;

  @Value("${spring.redis.port}")
  private Integer port;

  @Bean
  public JedisConnectionFactory jedisConnectionFactory(){
    RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(host,port);
    return new JedisConnectionFactory(redisStandaloneConfiguration);
  }

  @Bean(value = "redisTemplate")
  public RedisTemplate<String, User> redisTemplate(JedisConnectionFactory jedisConnectionFactory){
    RedisTemplate<String,User> redisTemplate = new RedisTemplate<>();
    redisTemplate.setConnectionFactory(jedisConnectionFactory);
    return redisTemplate;
  }

  @Bean(name = "cacheManager")
  public CacheManager cacheManager(JedisConnectionFactory jedisConnectionFactory) {
    return RedisCacheManager.builder(jedisConnectionFactory)
        .cacheDefaults(RedisCacheConfiguration.defaultCacheConfig())
        .build();
  }
}