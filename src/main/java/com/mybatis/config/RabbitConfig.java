package com.mybatis.config;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * rabbit config
 *
 * Created by yunkai on 2018/1/24.
 */
@Configuration
public class RabbitConfig {

    @Autowired
    private Environment env;

    @Bean
    public ConnectionFactory connectionFactory(){

        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setAddresses(env.getProperty("spring.rabbitmq.host"));
        connectionFactory.setPort(Integer.parseInt(env.getProperty("spring.rabbitmq.port")));
        connectionFactory.setUsername(env.getProperty("spring.rabbitmq.username"));
        connectionFactory.setPassword(env.getProperty("spring.rabbitmq.password"));
        connectionFactory.setVirtualHost(env.getProperty("spring.rabbitmq.virtual-host"));
        /** 如果要进行消息回调，则这里必须要设置为true */
        //connectionFactory.setPublisherConfirms(true);
        return connectionFactory;
    }
}
