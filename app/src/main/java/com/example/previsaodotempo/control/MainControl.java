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
import com.example.previsaodotempo.model.ConsultaCidade;
import com.example.previsaodotempo.model.ConsultaCidadeDTO;
import com.example.previsaodotempo.model.Previsao;
import com.example.previsaodotempo.model.PrevisaoDTO;
import com.example.previsaodotempo.model.Resultado;
import com.example.previsaodotempo.model.ResultadoDTO;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.internal.bind.ReflectiveTypeAdapterFactory;
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
    private List<Previsao> listPrevisoes;
    private ArrayAdapter<Previsao> adapterPrevisao;
    private Previsao previsao;


    public MainControl(Activity activity) {
        this.activity = activity;
        previsao = new Previsao();
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
                ResultadoDTO rDTO = gson.fromJson(root.getAsJsonObject().get("results").toString(), ResultadoDTO.class);
                Resultado resultado = rDTO.getResultado();

                PrevisaoDTO pDTO = gson.fromJson(root.getAsJsonObject().get("results").toString(), PrevisaoDTO.class);
                Previsao previsao = pDTO.getPrevisao();
//                adapterPrevisao.add(previsao);
                carregarForm(resultado);
                configListView(previsao);

            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                limparDados();
            }
        });
    }


    public void configListView(Previsao p) {

        listPrevisoes = new ArrayList<>();
        listPrevisoes.add(p);
        adapterPrevisao = new ArrayAdapter<>(activity, android.R.layout.simple_list_item_1, listPrevisoes);
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
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        tvData.setText("Data: " + sdf.format(p.getData()));

        tvMinimo.setText(String.valueOf(p.getTemperaturaMinima()));
        tvMaximo.setText(String.valueOf(p.getTemperaturaMaxima()));
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
