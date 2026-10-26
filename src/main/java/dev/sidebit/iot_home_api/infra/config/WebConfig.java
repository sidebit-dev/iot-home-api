package dev.sidebit.iot_home_api.infra.config;

import dev.sidebit.iot_home_api.infra.security.AuthInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private AuthInterceptor authInterceptor;

    @Override
    public void addCorsMappings(CorsRegistry registry) {

        // /* /tickets  ,   /**   /tickets/2
        registry.addMapping("/**")
      //          .allowedOrigins("http://localhost:4200", "http://localhost:3000") // Para estudo vai ser somente um *
                .allowedOrigins("*")
                .allowedMethods("*") // ("POST", "GET", DELE")
                .allowedHeaders("*");  // AUTHORIZATION, CONTENT-TYPE
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
       registry.addInterceptor(authInterceptor)
               .addPathPatterns("/**")
               .excludePathPatterns(
                       "/auth/**",
                       "/h2-console/**",
                       "/error",
                       "/ewent/**"
               );
    }
}
