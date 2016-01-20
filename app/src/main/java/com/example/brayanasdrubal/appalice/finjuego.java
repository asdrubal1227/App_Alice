package com.example.brayanasdrubal.appalice;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Window;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class finjuego extends AppCompatActivity {
    private static final long SPLASH_DELAY = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_finjuego);
        TextView punt=(TextView) findViewById(R.id.punf);
        if (Evaluate.n==1){
            Evaluate.n=2;
            startActivity(new Intent(finjuego.this, Memoria.class));
            finish();
        }else if (Evaluate.n==2){

            SharedPreferences prefs = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
            float promedio= prefs.getFloat("promedio",0);
            Evaluate.promedio= (float) (((Logica.punt*(0.5)+Memoria.puntaje)/2+promedio)/2);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putFloat("promedio", Evaluate.promedio);
            editor.commit();
            Logica.ju=5;
            Logica.punt=0;
            Memoria.puntaje=0;
            Memoria.fallas=0;
            startActivity(new Intent(finjuego.this, Nivel.class));
            overridePendingTransition(R.anim.zoom_in, R.anim.zoom_out);
            finish();

        }else if (Memoria.puntaje!=0){
            punt.setText("Puntaje: " + (Memoria.puntaje * 10 - Memoria.fallas * 5));

        }else
        punt.setText("Puntaje: " + Logica.punt);
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Logica.ju=5;
                Logica.punt=0;
                Memoria.puntaje=0;
                Memoria.fallas=0;
                overridePendingTransition(R.anim.zoom_in, R.anim.zoom_out);
                finish();

            }
        };

        Timer timer = new Timer();
        timer.schedule(task, SPLASH_DELAY);
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if ((keyCode == KeyEvent.KEYCODE_BACK))
        {
            Logica.ju=5;
            Logica.punt=0;
            Memoria.puntaje=0;
            Memoria.fallas=0;
            Evaluate.n=0;
            finish();

            return true;

        }

        return super.onKeyDown(keyCode, event);
    }
}
