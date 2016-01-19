package com.example.brayanasdrubal.appalice;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
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
        EditText campo= (EditText) findViewById(R.id.editText5);
        String num= campo.getText().toString();
        //Abrir la actividad
        SharedPreferences prefs = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=prefs.edit();
        editor.putString("num",num);
       // editor.clear(); //Limpia todas las preferencias
        //editor.remove("nombre");
        editor.commit();
        Intent i = new Intent(info_cuidador.this, MainActivity.class);
        startActivity(i);
        finish();

    }
}
