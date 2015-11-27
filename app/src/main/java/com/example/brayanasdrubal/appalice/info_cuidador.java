package com.example.brayanasdrubal.appalice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

public class info_cuidador extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_info_cuidador);
    }
}
