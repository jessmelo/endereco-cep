package com.jessmelo.cep.app.data;

public class CepRequest {
    String cep;
    CepRequest() {}

    public CepRequest(String cep) {
        this.cep = cep;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }
}
