package com.example.brayanasdrubal.appalice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

public class info_cuidador extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_info_cuidador);
    }
    public void onClick (View v){
        Toast.makeText(info_cuidador.this, "Menú", Toast.LENGTH_SHORT).show();
        //Abrir la actividad
        Intent i = new Intent(info_cuidador.this, MainActivity.class);
        startActivity(i);
        finish();

    }
}
