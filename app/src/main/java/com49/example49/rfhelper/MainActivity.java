package com49.example49.rfhelper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initButton();
    }

    private void initButton() {
        ImageButton buttonFresnelZone = findViewById(R.id.button_fresnel_id);
        buttonFresnelZone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, FresnelZone.class);
                startActivity(intent);
            }
        });

        ImageButton buttonDalnost = findViewById(R.id.button_dalnost_id);
        buttonDalnost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DistanceGorizont.class);
                startActivity(intent);
            }
        });

        ImageButton buttonZatuhanie = findViewById(R.id.button_zatuhanie_id);
        buttonZatuhanie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SignalLossDistance.class);
                startActivity(intent);
            }
        });

        ImageButton buttonSwrToDb = findViewById(R.id.button_ksv_to_db_id);
        buttonSwrToDb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SwrToPower.class);
                startActivity(intent);
            }
        });

        ImageButton buttonDbmToWt = findViewById(R.id.button_dbm_to_vt_id);
        buttonDbmToWt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DecibelToWatt.class);
                startActivity(intent);
            }
        });

        ImageButton buttonFreqToLength = findViewById(R.id.button_lenght_id);
        buttonFreqToLength.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, FreqToLenght.class);
                startActivity(intent);
            }
        });

    }


}
