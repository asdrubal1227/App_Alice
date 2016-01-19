package com.example.brayanasdrubal.appalice;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

public class Verifica extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_verifica);
    }
}
