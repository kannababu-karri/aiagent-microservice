package com.restful.aiagent.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {
    @Bean
    public RestTemplate restTemplate() {
    	SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
    	factory.setConnectTimeout(60_000); // 60 sec
	    factory.setReadTimeout(600_000);   // 10 min
	    return new RestTemplate(factory);
        //return new RestTemplate();
    }
}