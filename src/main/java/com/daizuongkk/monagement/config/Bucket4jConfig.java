package com.daizuongkk.monagement.config;

import java.time.Duration;
import java.util.Objects;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

import io.github.bucket4j.distributed.ExpirationAfterWriteStrategy;
import io.github.bucket4j.distributed.proxy.ProxyManager;
import io.github.bucket4j.redis.lettuce.Bucket4jLettuce;
import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.codec.ByteArrayCodec;
import io.lettuce.core.codec.RedisCodec;

@Configuration
public class Bucket4jConfig {

  @Bean
  public ProxyManager<byte[]> redisProxyManager(RedisConnectionFactory connectionFactory) {
    LettuceConnectionFactory lettuceFactory = (LettuceConnectionFactory) connectionFactory;
    RedisClient redisClient = Objects.requireNonNull(
        (RedisClient) lettuceFactory.getNativeClient(), "Redis client must not be null");

    StatefulRedisConnection<byte[], byte[]> connection = redisClient
        .connect(RedisCodec.of(new ByteArrayCodec(), new ByteArrayCodec()));

    return Bucket4jLettuce.casBasedBuilder(connection)
        .expirationAfterWrite(ExpirationAfterWriteStrategy.fixedTimeToLive(Duration.ofMinutes(15)))
        .build();
  }
}
