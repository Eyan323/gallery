package kr.co.wikibook.gallery.common.config;

import kr.co.wikibook.gallery.common.interceptor.ApiInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {

    private final ApiInterceptor apiInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) { 
        registry.addInterceptor(apiInterceptor)
                .addPathPatterns("/v1/api/**") // 인터셉터를 적용할 URL 경로
                .excludePathPatterns("/v1/api/account/**", "/v1/api/items/**"); // 엔터셉터를 적용하지 않을 URL 경로
    }
}