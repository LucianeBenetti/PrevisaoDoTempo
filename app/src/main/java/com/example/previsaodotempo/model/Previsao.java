package com.example.previsaodotempo.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Previsao {
    private String data;
    private Integer temperaturaMaxima;
    private Integer temperaturaMinima;
    private String descricao;
    private String condicao;

    public Previsao() {
    }

    public Previsao(String data, Integer temperaturaMaxima, Integer temperaturaMinima, String descricao, String condicao) {
        this.data = data;
        this.temperaturaMaxima = temperaturaMaxima;
        this.temperaturaMinima = temperaturaMinima;
        this.descricao = descricao;
        this.condicao = condicao;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Integer getTemperaturaMaxima() {
        return temperaturaMaxima;
    }

    public void setTemperaturaMaxima(Integer temperaturaMaxima) {
        this.temperaturaMaxima = temperaturaMaxima;
    }

    public Integer getTemperaturaMinima() {
        return temperaturaMinima;
    }

    public void setTemperaturaMinima(Integer temperaturaMinima) {
        this.temperaturaMinima = temperaturaMinima;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCondicao() {
        return condicao;
    }

    public void setCondicao(String condicao) {
        this.condicao = condicao;
    }

    @Override
    public String toString() {

        return data;
    }
}
