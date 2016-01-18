package com.example.brayanasdrubal.appalice;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class Logica extends AppCompatActivity {

    private MalibuCountDownTimer countDownTimer;
    private boolean timerHasStarted=false;
    private final long startTime = 5000;
    private final long interval = 1000;
    int num1, num2, signo, result,v;
    String valor;
    private static final long SPLASH_DELAY = 2000;
    public static int ju=5,punt=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logica);
        countDownTimer = new MalibuCountDownTimer(startTime, interval);
        if (ju==0){
            countDownTimer.cancel();
            startActivity(new Intent(Logica.this, finjuego.class));
            overridePendingTransition(R.anim.zoom_in, R.anim.zoom_out);
            finish();
        }else{
            fin();
        }
        TextView v = (TextView) findViewById(R.id.vida);
        v.setText("Vidas: "+Integer.toString(ju));
        TextView p = (TextView) findViewById(R.id.punt);
        p.setText(Integer.toString(punt));
        TextView gp = (TextView) findViewById(R.id.result);
        gp.setVisibility(View.INVISIBLE);
        num1 = (int) (Math.random() * 50 + 1);
        num2 = (int) (Math.random() * 50 + 1);
        signo = (int) (Math.random() * 3 + 1);
        TextView num11 = (TextView) findViewById(R.id.primernum);
        TextView num22 = (TextView) findViewById(R.id.segundonum);
        TextView sign = (TextView) findViewById(R.id.signo);
        num11.setText(Integer.toString(num1));
        num22.setText(Integer.toString(num2));
        if (signo == 1) {
            sign.setText("+");
            result = num1 + num2;
        } else if (signo == 2) {
            sign.setText("-");
            result = num1 - num2;
        } else if (signo == 3) {
            num1 = (int) (Math.random() * 10 + 1);
            num2 = (int) (Math.random() * 10 + 1);
            num11.setText(Integer.toString(num1));
            num22.setText(Integer.toString(num2));
            sign.setText("*");
            result = num1 * num2;
        }
    }

    public void verifica(View v) {
        EditText res = (EditText) findViewById(R.id.valor);
        valor = res.getText().toString();
        if (valor.equals(Integer.toString(result))) {
            punt=punt+10;
            TextView gp = (TextView) findViewById(R.id.result);
            gp.setText("¡CORRECTO!");
            gp.setVisibility(View.VISIBLE);
        } else {
            punt=punt-5;
            TextView gp = (TextView) findViewById(R.id.result);
            gp.setText("INCORRECTO");
            gp.setVisibility(View.VISIBLE);
        }
        fin();
        TextView p = (TextView) findViewById(R.id.punt);
        p.setText(Integer.toString(punt));
        refrescar();
    }

    public void fin(){

        if (!timerHasStarted)
        {
            countDownTimer.start();
            timerHasStarted = true;
        }
        else
        {

            countDownTimer.cancel();
            timerHasStarted = false;
        }
    }
    // CountDownTimer class
    public class MalibuCountDownTimer extends CountDownTimer {

        public MalibuCountDownTimer(long startTime, long interval)
        {
            super(startTime, interval);
        }

        @Override
        public void onFinish()
        {
            TextView tim = (TextView) findViewById(R.id.time);
            tim.setText("Listo!" );
            fin();
            ju=ju-1;
            TextView v = (TextView) findViewById(R.id.vida);
            v.setText("Vidas: " + Integer.toString(ju));
            refrescar();
        }

        @Override
        public void onTick(long millisUntilFinished)
        {
            TextView tim = (TextView) findViewById(R.id.time);
            tim.setText("" + millisUntilFinished / 1000);
        }
    }

    public void refrescar(){

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                startActivity(new Intent(Logica.this, Logica.class));
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
            countDownTimer.cancel();
            Logica.ju=5;
            Logica.punt=0;
            startActivity(new Intent(Logica.this, Juegos.class));
            finish();

            return true;

        }

        return super.onKeyDown(keyCode, event);
    }
}


