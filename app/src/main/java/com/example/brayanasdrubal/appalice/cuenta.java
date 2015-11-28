package com.example.brayanasdrubal.appalice;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

public class cuenta extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_cuenta);
        //refeshPrefs();

    }


    public void onClick (View v){
        Toast.makeText(cuenta.this, "Cuidador", Toast.LENGTH_SHORT).show();
        //Abrir la actividad
        savePrefs(v);
        Intent i = new Intent(cuenta.this, info_cuidador.class);
        startActivity(i);
        finish();

    }

    public void savePrefs(View view){
        SharedPreferences prefs= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor=prefs.edit();
        EditText campo= (EditText) findViewById(R.id.editText);
        String campoStr= campo.getText().toString();
        editor.putString("nombre",campoStr);
        editor.clear(); //Limpia todas las preferencias
        //editor.remove("nombre");
        editor.commit();
    }

    public void clearPrefs(View view){
       // refeshPrefs();
    }

    /*public void refeshPrefs(){
        TextView info= (TextView) findViewById(R.id.tNombre);
        String dato= prefs.getString("nombre","NA");
        info.setText(dato);

    }*/
}
