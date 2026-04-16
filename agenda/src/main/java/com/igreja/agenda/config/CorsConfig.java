package com.igreja.agenda.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.*;

@Configuration
public class CorsConfig {
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration Config = new CorsConfiguration();
        Config.addAllowedOrigin("*");
        Config.addAllowedHeader("*");
        Config.addAllowedMethod("*");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", Config);
        return source;
    }
}
