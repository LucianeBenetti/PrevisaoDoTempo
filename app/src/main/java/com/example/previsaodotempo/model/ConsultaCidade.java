package com.example.previsaodotempo.model;

import java.util.List;

public class ConsultaCidade {

    private String codigoCidade;
    private boolean codigoValido;
    private List<Resultado> resultados;

    public ConsultaCidade() {
    }

    public ConsultaCidade(String codigoCidade, boolean codigoValido, List<Resultado> resultados) {
        this.codigoCidade = codigoCidade;
        this.codigoValido = codigoValido;
        this.resultados = resultados;
    }

    public String getCodigoCidade() {
        return codigoCidade;
    }

    public void setCodigoCidade(String codigoCidade) {
        this.codigoCidade = codigoCidade;
    }

    public boolean isCodigoValido() {
        return codigoValido;
    }

    public void setCodigoValido(boolean codigoValido) {
        this.codigoValido = codigoValido;
    }

    public List<Resultado> getResultados() {
        return resultados;
    }

    public void setResultados(List<Resultado> resultados) {
        this.resultados = resultados;
    }
}
