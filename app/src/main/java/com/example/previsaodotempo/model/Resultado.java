package com.example.previsaodotempo.model;

import java.util.Date;
import java.util.List;

public class Resultado {

    private Integer temperatura;
    private String cidade;
    private Date data;
    private Integer Umidade;
    private Previsao[] previsao;

    public Resultado() {
    }

    public Resultado(Integer temperatura, String cidade, Date data, Integer umidade, Previsao[] previsao) {
        this.temperatura = temperatura;
        this.cidade = cidade;
        this.data = data;
        Umidade = umidade;
        this.previsao = previsao;
    }

    public Integer getUmidade() {
        return Umidade;
    }

    public void setUmidade(Integer umidade) {
        Umidade = umidade;
    }

    public Integer getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(Integer temperatura) {
        this.temperatura = temperatura;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Previsao[] getPrevisao() {
        return previsao;
    }

    public void setPrevisao(Previsao[] previsao) {
        this.previsao = previsao;
    }
}
