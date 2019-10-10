package com.example.previsaodotempo.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.previsaodotempo.R;
import com.example.previsaodotempo.control.MainControl;

public class MainActivity extends AppCompatActivity {
    private MainControl control;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        control = new MainControl(this);
    }

}
