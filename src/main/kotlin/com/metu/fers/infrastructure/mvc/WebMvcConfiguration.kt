package com.metu.fers.infrastructure.mvc

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebMvcConfiguration : WebMvcConfigurer {
    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
            .allowedHeaders("Access-Control-Allow-Origin")
            .allowedOriginPatterns("*")
            .allowCredentials(true)
            .allowedMethods("GET", "POST", "DELETE", "PUT", "OPTIONS", "PATCH")
            .maxAge(3600)
    }
}
