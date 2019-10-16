package com.example.previsaodotempo.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class ResultadoDTO {

    private Integer temp;
    private String city;
    private String date;
    private List<PrevisaoDTO> forecast;
    private Integer humidity;

    public ResultadoDTO() {
    }

    public Integer getHumidity() {
        return humidity;
    }

    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }

    public Integer getTemp() {
        return temp;
    }

    public void setTemp(Integer temp) {
        this.temp = temp;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ResultadoDTO(Integer temp, String city, String date, List<PrevisaoDTO> forecast, Integer humidity) {
        this.temp = temp;
        this.city = city;
        this.date = date;
        this.forecast = forecast;
        this.humidity = humidity;
    }

    public List<PrevisaoDTO> getForecast() {
        return forecast;
    }

    public void setForecast(List<PrevisaoDTO> forecast) {
        this.forecast = forecast;
    }

    public Resultado getResultado(){
        Resultado r = new Resultado();

      //  Date data = converteData(date);
        r.setData(date);
        r.setCidade(city);
        r.setTemperatura(temp);
        r.setUmidade(humidity);
        r.setPrevisao(new ArrayList<Previsao>());
        for(PrevisaoDTO pDTO : forecast){
            r.getPrevisao().add(pDTO.getPrevisao());
        }

        return r;
    }

    public static java.sql.Date converteData(String data){
        try {
            SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
            java.util.Date dataUtil = formatador.parse(data);
            return new java.sql.Date(dataUtil.getTime());
        } catch (ParseException ex) {
            System.out.println("Erro ao converter data");
            return null;
        }

    }


}
