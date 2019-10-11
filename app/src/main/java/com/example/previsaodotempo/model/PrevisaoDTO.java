package com.example.previsaodotempo.model;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class PrevisaoDTO {

    private String date;
    private Integer max;
    private Integer min;
    private String description;
    private String condition;

    public PrevisaoDTO() {
    }

    public PrevisaoDTO(String date, Integer max, Integer min, String description, String condition) {
        this.date = date;
        this.max = max;
        this.min = min;
        this.description = description;
        this.condition = condition;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getMax() {
        return max;
    }

    public void setMax(Integer max) {
        this.max = max;
    }

    public Integer getMin() {
        return min;
    }

    public void setMin(Integer min) {
        this.min = min;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public Previsao getPrevisao(){
        SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");

        Date data = null;
        try {
            data = formatador.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Previsao p = new Previsao();
        p.setData(data);
        p.setTemperaturaMaxima(max);
        p.setTemperaturaMinima(min);
        p.setDescricao(description);
        p.setCondicao(condition);
        return p;
    }

    @Override
    public String toString() {
       // SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
       // return sdf.format(date);
        return date;
    }
}

