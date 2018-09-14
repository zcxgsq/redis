package com.zcx;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import java.net.UnknownHostException;
/**
 * @author zcx
 * @Title 程序入口
 * @date 2018年09月13日 11:31
 * 首先这是一个Spring Boot应用程序的入口，或者叫做主程序。
 * 其中使用了一个注解@SpringBootApplication来标注他是一个Spring Boot应用。
 * main方法使他成为一个主程序，将在应用被启动时首先被执行。
 * 注解@RestController同时标注这个程序还是一个控制器，如果浏览器访问应用的/hello。
 * 他将调用home方法，并输出字符串hello。
 *
 **/
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    @Bean
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory)
            throws UnknownHostException {
        RedisTemplate<Object, Object> template = new RedisTemplate<Object, Object>();
        template.setConnectionFactory(redisConnectionFactory);
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        template.setValueSerializer(jackson2JsonRedisSerializer);//1 设置值（value）的序列化采用Jackson2JsonRedisSerializer。
        template.setKeySerializer(new StringRedisSerializer());//2 设置键（key）的序列化采用StringRedisSerializer。
        template.afterPropertiesSet();
        return template;
    }
}
