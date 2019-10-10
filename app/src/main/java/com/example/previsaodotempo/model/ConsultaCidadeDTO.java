package com.example.previsaodotempo.model;

import android.content.Context;

import java.util.List;

public class ConsultaCidadeDTO {

    private String by;
    private boolean valid_key;
    private List<Resultado> results;

    public ConsultaCidadeDTO() {
    }

    public ConsultaCidadeDTO(String by, boolean valid_key, List<Resultado> results) {
        this.by = by;
        this.valid_key = valid_key;
        this.results = results;
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    public boolean isValid_key() {
        return valid_key;
    }

    public void setValid_key(boolean valid_key) {
        this.valid_key = valid_key;
    }

    public List<Resultado> getResults() {
        return results;
    }

    public void setResults(List<Resultado> results) {
        this.results = results;
    }

    public ConsultaCidade getConsultaCidade(){
        ConsultaCidade cd = new ConsultaCidade();
        cd.setCodigoCidade(by);
        cd.setCodigoValido(valid_key);
        cd.setResultados(results);
        return cd;
    }
}
