package com.jessmelo.cep.app;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jessmelo.cep.app.data.CepRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class CepRequestApplicationTests {
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void testPostCepRequest() throws Exception {
		CepRequest request = new CepRequest("03308010");
		String requestBody = objectMapper.writeValueAsString(request);

		mockMvc.perform(MockMvcRequestBuilders.post("/v1/consulta-endereco")
						.contentType(MediaType.APPLICATION_JSON)
						.content(requestBody))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers
						.jsonPath("$.cep")
						.value("03308-010"));
	}

	@Test
	void testInvalidPostCepRequest() throws Exception {
		CepRequest request = new CepRequest("1489083");
		String requestBody = objectMapper.writeValueAsString(request);

		mockMvc.perform(MockMvcRequestBuilders.post("/v1/consulta-endereco")
						.contentType(MediaType.APPLICATION_JSON)
						.content(requestBody))
				.andExpect(status().isNotFound())
				.andExpect(MockMvcResultMatchers
						.jsonPath("$.erro")
						.value("Não foi possível encontrar o CEP."));
	}
}
