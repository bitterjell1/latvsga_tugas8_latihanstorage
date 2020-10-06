package com.fitriasari.latvsga_tugas8_latihanstorage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button btnInternalstorage,btnexstorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInternalstorage = findViewById(R.id.btnInternalstorage);
        btnexstorage = findViewById(R.id.btnexstorage);

        btnInternalstorage.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,InternalActivity.class);
                startActivity(intent);
            }
        });
        btnexstorage.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,EksternalActivity.class);
                startActivity(intent);
            }
        });
    }
}