package com.example.rfhelper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class DecibelToWatt extends AppCompatActivity {

    EditText powerDecibell;  // поле ввода мощности в dBm
    TextView powerWatt; // результат вычислений


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decibel_to_watt);
    }




    public void refresh(){

        Double dBm = Double.parseDouble(powerDecibell.getText().toString());

        Double zoneDouble_100 =  (Math.pow(10, (dBm/10))); // мощность в мВт

        if (zoneDouble_100>=1000) {  // если мощность свыше 1000 мВт, то отображаем ее в Вт
            zoneDouble_100 = zoneDouble_100/1000;
            powerWatt.setText("Мощность " + String.format("%.1f", zoneDouble_100) + " Вт");
        }

        else  {
            powerWatt.setText("Мощность " + String.format("%.0f", zoneDouble_100) + " мВт");
        }



    }



    public void fieldsCorrect(View view){  // проверка правильности заполнения полей

        powerDecibell = findViewById(R.id.power_decibell_id);
        powerWatt = findViewById(R.id.power_watt_id);


        try
        {
            //double distance = Double.parseDouble(distanceEditText.getText().toString());
            //double freq = Double.parseDouble(freqEditText.getText().toString());
            refresh();

        } catch (Exception e1) {
            e1.printStackTrace();
            Toast.makeText(this, "Убедитесь в правильности заполнения полей", Toast.LENGTH_LONG).show();

        }




    }



}
