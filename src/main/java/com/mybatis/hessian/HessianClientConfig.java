package com.mybatis.hessian;

import com.mybatis.api.APIUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.caucho.HessianProxyFactoryBean;

/**
 * Created by yunkai on 2017/9/19.
 */
@Configuration
public class HessianClientConfig {

    @Bean
    public HessianProxyFactoryBean clientConfig(){
        HessianProxyFactoryBean proxy = new HessianProxyFactoryBean();
        proxy.setServiceUrl("http://127.0.0.1:8911/apiUserServiceExporter.hessian");
        proxy.setServiceInterface(APIUserService.class);
        return proxy;
    }

}
