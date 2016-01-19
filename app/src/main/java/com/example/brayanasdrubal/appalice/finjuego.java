package com.example.brayanasdrubal.appalice;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class finjuego extends AppCompatActivity {
    private static final long SPLASH_DELAY = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finjuego);
        TextView punt=(TextView) findViewById(R.id.punf);
        if (Memoria.puntaje!=0){
            punt.setText("Puntaje: " + (Memoria.puntaje*10-Memoria.fallas*5));

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
}
