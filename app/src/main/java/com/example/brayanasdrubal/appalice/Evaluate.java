package com.example.brayanasdrubal.appalice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;

public class Evaluate extends AppCompatActivity {
public  static int n=0;
    public  static double  promedio=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_evaluate);
    }

    public void test(View v){
        n=1;
        startActivity(new Intent(Evaluate.this, Logica.class));
    }
    public void estado(View v){

        startActivity(new Intent(Evaluate.this,Nivel.class));
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
