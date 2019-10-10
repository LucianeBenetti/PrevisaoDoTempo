package com.example.previsaodotempo.control;

import android.app.Activity;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.previsaodotempo.R;
import com.example.previsaodotempo.model.ConsultaCidade;
import com.example.previsaodotempo.model.Previsao;
import com.example.previsaodotempo.model.PrevisaoDTO;
import com.example.previsaodotempo.model.Resultado;
import com.example.previsaodotempo.model.ResultadoDTO;
import com.google.gson.Gson;
import com.google.gson.internal.bind.ReflectiveTypeAdapterFactory;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.util.ArrayList;
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
    private List<Previsao> listPrevisoes;
    private ArrayAdapter<Previsao> adapterPrevisao;
    private Previsao previsao;
    private Resultado resultado;

    public MainControl(Activity activity) {
        this.activity = activity;
        previsao = new Previsao();
        resultado = new Resultado();
        initComponents();
        pesquisarPorResultado();
    }

    private void initComponents() {
        tvData = activity.findViewById(R.id.tvData);
        tvCidade = activity.findViewById(R.id.tvCidade);
        tvUmidade = activity.findViewById(R.id.tvUmidade);
        tvMinimo = activity.findViewById(R.id.tvMinimo);
        tvMaximo = activity.findViewById(R.id.tvMaximo);
        lvPrevisoes = activity.findViewById(R.id.lvPrevisoes);

        configListView();

    }

    private void configListView() {

        listPrevisoes = new ArrayList<>();
        listPrevisoes.add(new Previsao());
        adapterPrevisao = new ArrayAdapter<>(activity, android.R.layout.simple_list_item_1, listPrevisoes);
        lvPrevisoes.setAdapter(adapterPrevisao);
    }


    private void pesquisarPorResultado(){
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
                ResultadoDTO eDTO = gson.fromJson(resultadoJason, ResultadoDTO.class);
                Resultado resultado = eDTO.getResultado();
                carregarForm(resultado);

            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                limparDados();
            }
        });
    }


    private void limparDados() {
    }

    private void carregarForm(Resultado r) {

        tvCidade.setText(r.getCidade());
        tvData.setText(r.getData().getDate());
        tvUmidade.setText(r.getUmidade());
    }

}
