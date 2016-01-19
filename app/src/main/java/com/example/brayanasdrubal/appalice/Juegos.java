package com.example.brayanasdrubal.appalice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;

public class Juegos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_juegos);
    }

    public void sudoku (View view){
        startActivity(new Intent(Juegos.this, sudoku.class));
    }
    public void logica (View view){
        startActivity(new Intent(Juegos.this,Logica.class));
    }
    public void memo (View view){
        startActivity(new Intent(Juegos.this,Memoria.class));
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if ((keyCode == KeyEvent.KEYCODE_BACK))
        {
            MainActivity.regreso=1;
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
