package com.jessmelo.cep.app.config;

import com.jessmelo.cep.app.data.repository.ViaCepAPI;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@ConfigurationProperties("app")
public class AppConfig {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public ViaCepAPI viaCepServer(RestTemplate restTemplate) {
        return new ViaCepAPI(restTemplate);
    }
}
