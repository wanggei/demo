package org.webjars.demo.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("defaultWebSecurityManager") DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean shiro=new ShiroFilterFactoryBean();
        shiro.setSecurityManager(defaultWebSecurityManager);
        Map<String,String> map=new LinkedHashMap<>();
        map.put("/user/*","authc");
        map.put("/admin/*","authc");
        shiro.setLoginUrl("/login.html");
//        shiro.set
        shiro.setFilterChainDefinitionMap(map);
        return shiro;
    }

    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager defaultWebSecurityManager=new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(userRealm);
        return defaultWebSecurityManager;
    }

    @Bean
    public UserRealm userRealm(){

        return new UserRealm();
    }
    @Bean
    public ShiroDialect shiroDialect(){

        return new ShiroDialect();
    }
}

