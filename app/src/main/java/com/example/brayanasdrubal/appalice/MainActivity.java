package com.example.brayanasdrubal.appalice;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        refeshPrefs();
    }


   /* public void onClick (View v){
        Toast.makeText(MainActivity.this, "Cuidador", Toast.LENGTH_SHORT).show();
        //Abrir la actividad
        savePrefs(v);
        Intent i = new Intent(MainActivity.this, info_cuidador.class);
        startActivity(i);
        finish();

    }*/

   /* public void savePrefs(View view){
        EditText campo= (EditText) findViewById(R.id.editText);
        String campoStr= campo.getText().toString();
        cuenta.editor.putString("nombre", campoStr);
        cuenta.editor.clear(); //Limpia todas las preferencias
        //editor.remove("nombre");
        cuenta.editor.commit();
        refeshPrefs();
    }*/

    public void refeshPrefs(){


        SharedPreferences prefs= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        TextView info= (TextView) findViewById(R.id.textView);
        String dato= prefs.getString("nombre","Hola");
        info.setText("Hola "+dato);

    }
}
