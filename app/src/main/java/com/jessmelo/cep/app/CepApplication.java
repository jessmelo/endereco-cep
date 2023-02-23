package com.jessmelo.cep.app;

import com.jessmelo.cep.app.config.AppConfig;
import com.jessmelo.cep.app.data.CepRequest;
import com.jessmelo.cep.app.data.EnderecoCepResponse;
import com.jessmelo.cep.app.data.ErrorResponse;
import com.jessmelo.cep.app.data.repository.ViaCepAPI;
import com.jessmelo.cep.app.data.repository.model.CepResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@RestController
@EnableConfigurationProperties(AppConfig.class)
public class CepApplication {

	private final ViaCepAPI viaCepServer;

	@Autowired
	public CepApplication(ViaCepAPI viaCepServer) {
		this.viaCepServer = viaCepServer;
	}

	public static void main(String[] args) {
		SpringApplication.run(CepApplication.class, args);
	}

	@PostMapping("/v1/consulta-endereco")
	public ResponseEntity searchAddress(@RequestBody CepRequest cepRequest) {
		CepResponse apiResponse = viaCepServer.getCepData(cepRequest.getCep());

		if (apiResponse.getErro()) {
			ErrorResponse response = new ErrorResponse(
					"Não foi possível encontrar o CEP."
			);
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}

		EnderecoCepResponse response = new EnderecoCepResponse(
				apiResponse.getCep(),
				apiResponse.getLogradouro(),
				apiResponse.getComplemento(),
				apiResponse.getLocalidade(),
				apiResponse.getUf(),
				5.3);

		// Return the response as a JSON object
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
