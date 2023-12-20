package br.edu.ifpb.pweb2.sisyphus.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import br.edu.ifpb.pweb2.sisyphus.interceptor.AuthInterceptor;

public class InterceptorConfiguration implements WebMvcConfigurer{
    @Autowired
    AuthInterceptor authInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry
                .addInterceptor(authInterceptor)
                .addPathPatterns("/**", "/administrador/**", "/aluno/**", "/professor/**", "/coordenador/**")
                .excludePathPatterns("/auth/**","/css/**","/images/**","/js/**");
    }
}
