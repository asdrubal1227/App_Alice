package com.example.brayanasdrubal.appalice;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class Nivel extends AppCompatActivity {
    private static final long SPLASH_DELAY = 7000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_nivel);

        TextView avance=(TextView) findViewById(R.id.avan);SharedPreferences prefs = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
        float promedio= prefs.getFloat("promedio",0);
        avance.setText(Double.toString(promedio));
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Logica.ju=5;
                Logica.punt=0;
                Memoria.puntaje=0;
                Memoria.fallas=0;
                Evaluate.n=0;
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
