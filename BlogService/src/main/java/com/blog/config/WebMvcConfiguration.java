package com.blog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.*;

import java.util.List;


@Configuration
@EnableWebMvc
public class WebMvcConfiguration implements WebMvcConfigurer {

    //拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MvcMultipleInterceptor()).addPathPatterns("/static/**/packed**.txt");
    }
    //页面跳转
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/", "/static/main/index.html");
    }
    //静态资源
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("file:D:\\haijunzhang\\MyBlog\\BlogUI\\");
    }
    //默认静态资源处理器
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
    }
    //视图解析器
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
    }
    //跨域
    @Override
    public void addCorsMappings(CorsRegistry registry) {
    }
    //信息转换器
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
    }
    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
/*
        /* 是否通过请求Url的扩展名来决定media type * /
        configurer.favorPathExtension(true)
                /* 不检查Accept请求头 * /
                .ignoreAcceptHeader(true)
                .parameterName("mediaType")
                /* 设置默认的media yype * /
                .defaultContentType(MediaType.TEXT_HTML)
                /* 请求以.html结尾的会被当成MediaType.TEXT_HTML* /
                .mediaType("html", MediaType.TEXT_HTML)
                /* 请求以.json结尾的会被当成MediaType.APPLICATION_JSON* /
                .mediaType("json", MediaType.APPLICATION_JSON);
*/
    }
}