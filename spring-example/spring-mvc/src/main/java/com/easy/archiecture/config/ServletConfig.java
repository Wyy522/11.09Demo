package com.easy.archiecture.config;

import com.easy.archiecture.interceptors.AuthInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.List;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.easy.archiecture")
@EnableAspectJAutoProxy(proxyTargetClass = true) //使用CGlib的方式来实现动态代理
public class ServletConfig implements WebMvcConfigurer {
    //处理页面路径
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.viewResolver(resourceViewResolver());
    }

    @Bean
    public InternalResourceViewResolver resourceViewResolver() {
        InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
        //请求页面文件的前缀地址
        internalResourceViewResolver.setPrefix("/page/");
        //请求页面文件的后缀
        internalResourceViewResolver.setSuffix(".html");
        return internalResourceViewResolver;
    }

    //开启spring DispatcherServlet 的支持
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }


    //让spring 知道当前项目中的除了页面之外的静态资源都放在哪个目录下。
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/js/").addResourceLocations("/js/**");
//        registry.addResourceHandler("/css/").addResourceLocations("/css/**");
        registry.addResourceHandler("/static/*")
                .addResourceLocations("classpath:/static/");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LocaleChangeInterceptor())
                .excludePathPatterns("classpath:/static/css/fonts/*.ttf")
                .excludePathPatterns("classpath:/static/css/fonts/*.woff");
        registry.addInterceptor(getAuthInterceptor()).addPathPatterns("/**");
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new MappingJackson2HttpMessageConverter());
//        converters.add(new MappingJackson2XmlHttpMessageConverter());
    }


    @Bean
    public HandlerInterceptor getAuthInterceptor() {
        return new AuthInterceptor();
    }
}
