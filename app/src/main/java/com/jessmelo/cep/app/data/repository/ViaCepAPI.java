package com.jessmelo.cep.app.data.repository;

import com.jessmelo.cep.app.data.EnderecoCepResponse;
import com.jessmelo.cep.app.data.repository.model.CepResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

public class ViaCepAPI {
    private final RestTemplate restTemplate;

    public ViaCepAPI(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public CepResponse getCepData(String cep) {
        String apiUrl = "https://viacep.com.br/ws/" + cep + "/json/";
        try {
            ResponseEntity<CepResponse> responseEntity =
                    restTemplate.getForEntity(apiUrl, CepResponse.class);
            return responseEntity.getBody();
        } catch (HttpClientErrorException e) {
            return new CepResponse(true);
        }
    }
}
