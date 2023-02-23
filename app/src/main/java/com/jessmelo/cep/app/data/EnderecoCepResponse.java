package com.jessmelo.cep.app.data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public
class EnderecoCepResponse {
    @Id
    private String cep;
    private String rua;
    private String complemento;
    private String cidade;
    private String estado;
    private Double frete;
    public EnderecoCepResponse(
            String cep, String rua, String complemento, String cidade, String estado, Double frete
    ) {
        this.cep = cep;
        this.rua = rua;
        this.complemento = complemento;
        this.cidade = cidade;
        this.estado = estado;
        setFrete(estado);
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Double getFrete() {
        return frete;
    }

    public void setFrete(String estado) {
        if (estado.equals("SP") || estado.equals("MG") || estado.equals("RJ") || estado.equals("ES")) {
            this.frete = 7.85;
        } else if (estado.equals("GO") || estado.equals("DF") || estado.equals("MT") || estado.equals("MS")) {
            this.frete = 12.50;
        } else if (estado.equals("PE") || estado.equals("PB") || estado.equals("BA") || estado.equals("PI")
        || estado.equals("MA") || estado.equals("CE") || estado.equals("AL") || estado.equals("SE") ||
                estado.equals("RN")){
            this.frete = 15.98;
        } else if (estado.equals("PR") || estado.equals("RS") || estado.equals("SC")) {
            this.frete = 17.30;
        }else if (estado.equals("AC") || estado.equals("AP") || estado.equals("PA") || estado.equals("RO")
                || estado.equals("AM") || estado.equals("RR") || estado.equals("TO")) {
            this.frete = 20.83;
        } else {
            this.frete = 0.0;
        }
    }
}
