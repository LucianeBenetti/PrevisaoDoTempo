package com.example.previsaodotempo.control;

import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.previsaodotempo.R;
import com.example.previsaodotempo.model.Previsao;
import com.example.previsaodotempo.model.PrevisaoDTO;
import com.example.previsaodotempo.model.Resultado;
import com.example.previsaodotempo.model.ResultadoDTO;
import com.example.previsaodotempo.model.Tempo;
import com.example.previsaodotempo.model.TempoDTO;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class MainControl {

    private Activity activity;
    private TextView tvData;
    private TextView tvCidade;
    private TextView tvUmidade;
    private TextView tvMinimo;
    private TextView tvMaximo;
    private ListView lvPrevisoes;
    private List<PrevisaoDTO> listPrevisoes;
    private ArrayAdapter<Previsao> adapterPrevisao;

    public MainControl(Activity activity) {
        this.activity = activity;
        initComponents();
    }

    private void initComponents() {
        tvData = activity.findViewById(R.id.tvData);
        tvCidade = activity.findViewById(R.id.tvCidade);
        tvUmidade = activity.findViewById(R.id.tvUmidade);
        tvMinimo = activity.findViewById(R.id.tvMinimo);
        tvMaximo = activity.findViewById(R.id.tvMaximo);
        lvPrevisoes = activity.findViewById(R.id.lvPrevisoes);
        pesquisarPorResultado();
    }

    private void pesquisarPorResultado(){
        AsyncHttpClient client = new AsyncHttpClient();
       // String URL = ;
        client.get("https://api.hgbrasil.com/weather?woeid=455861", new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                Toast.makeText(activity, "Iniciou", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRetry(int retryNO) {
                super.onRetry(retryNO);
                Toast.makeText(activity, "Tentativa" + retryNO, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                //retorno em string do viacep em Json
                String resultadoJason = new String(bytes);
                //conversao da string json para objeto
                Gson gson = new Gson();
                TempoDTO tDTO = gson.fromJson(resultadoJason, TempoDTO.class);
                Tempo t = tDTO.getTempo();
                carregarForm(t);

//
//                JsonElement root = new JsonParser().parse(resultadoJason);
//                JsonElement forecasts = root.getAsJsonObject().get("results");
//                String forecast = forecasts.getAsJsonObject().get("forecast").toString();
//
//                ResultadoDTO rDTO = gson.fromJson(root.getAsJsonObject().get("results").toString(), ResultadoDTO.class);
//                Resultado resultado = rDTO.getResultado();
//                PrevisaoDTO[] pDTO = gson.fromJson(forecast, PrevisaoDTO[].class);

//                //Transforma a resposta em uma lista de objetos Categoria
//                Type previsoesListType = new TypeToken<ArrayList<PrevisaoDTO>>(){}.getType();
//                List<PrevisaoDTO> listPrevisoes = gson.fromJson(resultadoJason, previsoesListType);
//
//                adapterPrevisao.addAll(listPrevisoes); //Adicionando dentro de algum Adapter
//               PrevisaoDTO dto = gson.fromJson(forecast, PrevisaoDTO.class);
               //Previsao previsao = dto.getPrevisao();

                configListView(t);
               // adapterPrevisao.addAll(Arrays.asList(pDTO));
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                Toast.makeText(activity, "Falhou", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void configListView(Tempo t) {

        adapterPrevisao = new ArrayAdapter<>(activity, android.R.layout.simple_list_item_1, t.getResultado().getPrevisao());
        lvPrevisoes.setAdapter(adapterPrevisao);
        Toast.makeText(activity, "Carregando", Toast.LENGTH_SHORT).show();
        cliqueCurto();
    }

    private void cliqueCurto() {
        lvPrevisoes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Previsao p = adapterPrevisao.getItem(i);
                carregaListViewPrevisão(p);
            }
        });
    }

    private void carregaListViewPrevisão(Previsao p) {

        tvData.setText("Data: " + p.getData());
        tvMinimo.setText(String.valueOf(p.getTemperaturaMinima()));
        tvMaximo.setText(String.valueOf(p.getTemperaturaMaxima()));
    }

    private void limparDados() {
    }

    private void carregarForm(Tempo t) {


        tvCidade.setText("Cidade: " + t.getResultado().getCidade());
        tvData.setText("Data: " +  t.getResultado().getData());
        tvUmidade.setText(t.getResultado().getUmidade() + "%");
        tvMaximo.setText("-/-");
        tvMinimo.setText("-/-");
    }
}
