# Endereço CEP
 Aplicação REST para pesquisa de endereços e valor de frete pelo CEP.

## Construído com:
  * Java 11
  * Spring Boot
  * ViaCep API
  * Testes unitários JUnit5

## Endpoint POST /v1/consulta-endereco
Recebe CEP no request body:
```json
{
 "cep": "0404053"
 }
 ```
 
 Retorna JSON com endereço (se o CEP existir):
 ```json
 {
 "cep": "01001-000",
 "rua": "Praça da Sé",
 "complemento": "lado ímpar",
 "bairro": "Sé",
 "cidade": "São Paulo",
 "estado": "SP",
 "frete": 7.85
}
```
Retorna código 404 e mensagem de erro se não houver:

 ```json
 {
 "erro": "Não foi possível encontrar o CEP."
}
```
