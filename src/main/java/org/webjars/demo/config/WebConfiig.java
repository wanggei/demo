package org.webjars.demo.config;

import org.springframework.boot.autoconfigure.web.embedded.EmbeddedWebServerFactoryCustomizerAutoConfiguration;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.WebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.webjars.demo.servlet.servlet;

import javax.servlet.ServletRegistration;

@Configuration
public class WebConfiig implements WebMvcConfigurer{
    //注册三大组件
    @Bean
    public ServletRegistrationBean servletRegistration(){

        ServletRegistrationBean registrationBean =  new ServletRegistrationBean(new servlet(),"/login");

        return registrationBean ;
    }
//    @Bean
//    public WebMvcConfigurer webMvcConfigurer(){
//        WebMvcConfigurer WebMvcConfigurer=new WebMvcConfigurer() {
//            @Override
//            public void addViewControllers(ViewControllerRegistry registry) {
//                  registry.addViewController("/").setViewName("qihangke");
//                registry.addViewController("/index.html").setViewName("qihangke");
//            }
//            @Override
//            public void addInterceptors(InterceptorRegistry registry) {
//                registry.addInterceptor(new LoginHandlerIntero()).addPathPatterns("/**").excludePathPatterns("/","/index.html");
//            }
//        };
//        return WebMvcConfigurer;
//    }
    @Bean
    public WebServerFactoryCustomizer<ConfigurableWebServerFactory> WebServerFactoryCustomizer(){

        return new WebServerFactoryCustomizer<ConfigurableWebServerFactory>() {
            @Override
            public void customize(ConfigurableWebServerFactory factory) {
                factory.setPort(8080);
            }
        };
    }
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new LoginHandlerIntero()).addPathPatterns("/**").excludePathPatterns("/","/index.html","/login.html","/webjars/bootstrap/**","" +
//                "/webjars/jquery/**","/css/**","/images/**","/js/**","/error/**");
//    }

    @Bean
    public LocaleResolver localeResolver(){

        return new LocalConfig();
    }


}
