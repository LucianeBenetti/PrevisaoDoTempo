package com.example.previsaodotempo.control;

import android.app.Activity;
import android.view.View;
import android.widget.Adapter;
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
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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
    private ArrayAdapter<PrevisaoDTO> adapterPrevisao;
    private PrevisaoDTO previsao;


    public MainControl(Activity activity) {
        this.activity = activity;
        previsao = new PrevisaoDTO();
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


    public void pesquisarPorResultado(){
        AsyncHttpClient client = new AsyncHttpClient();
        client.get("https://api.hgbrasil.com/weather/", new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                Toast.makeText(activity, "Iniciou", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                //retorno em string do viacep em Json

                String resultadoJason = new String(bytes);
                //conversao da string json para objeto
                Gson gson = new Gson();
                //conversao direta
                JsonElement root = new JsonParser().parse(resultadoJason);
                JsonElement forecasts = root.getAsJsonObject().get("results");
                String forecast = forecasts.getAsJsonObject().get("forecast").toString();

                ResultadoDTO rDTO = gson.fromJson(root.getAsJsonObject().get("results").toString(), ResultadoDTO.class);
                Resultado resultado = rDTO.getResultado();
                PrevisaoDTO[] pDTO = gson.fromJson(forecast, PrevisaoDTO[].class);

               //PrevisaoDTO dto = gson.fromJson(forecast, PrevisaoDTO.class);
               //Previsao previsao = dto.getPrevisao();
                carregarForm(resultado);
                configListView();
                adapterPrevisao.addAll(Arrays.asList(pDTO));

            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                limparDados();
            }
        });
    }


    public void configListView() {

        listPrevisoes = new ArrayList<>();
        adapterPrevisao = new ArrayAdapter<>(activity, android.R.layout.simple_list_item_1, listPrevisoes);
        lvPrevisoes.setAdapter(adapterPrevisao);
        Toast.makeText(activity, "Carregando", Toast.LENGTH_SHORT).show();
        cliqueCurto();
    }


    private void cliqueCurto() {
        lvPrevisoes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                PrevisaoDTO p = adapterPrevisao.getItem(i);

                carregaListViewPrevisão(p);
            }
        });
    }

    private void carregaListViewPrevisão(PrevisaoDTO p) {
   //     SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    //    tvData.setText("Data: " + sdf.format(p.getDate()));
        tvData.setText("Data: " + p.getDate());
        tvMinimo.setText(String.valueOf(p.getMin()));
        tvMaximo.setText(String.valueOf(p.getMax()));
    }

    private void limparDados() {
    }

    private void carregarForm(Resultado r) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        tvCidade.setText("Cidade: " + r.getCidade());
        tvData.setText("Data: " +  sdf.format(r.getData()));
        tvUmidade.setText(r.getUmidade() + "%");
        tvMaximo.setText("-/-");
        tvMinimo.setText("-/-");
    }

}
