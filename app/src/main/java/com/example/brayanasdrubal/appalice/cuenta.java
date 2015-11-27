package com.example.brayanasdrubal.appalice;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

public class cuenta extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_cuenta);
    }


    public void onClick (View v){
        Toast.makeText(cuenta.this, "Cuidador", Toast.LENGTH_SHORT).show();
        //Abrir la actividad
        Intent i = new Intent(cuenta.this, info_cuidador.class);
        startActivity(i);
        finish();

    }
}
