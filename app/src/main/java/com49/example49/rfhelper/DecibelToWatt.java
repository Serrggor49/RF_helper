package com49.example49.rfhelper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com49.example49.rfhelper.R;

public class DecibelToWatt extends AppCompatActivity {

    EditText powerDecibell;  // поле ввода мощности в dBm
    TextView powerWatt; // результат вычислений
    int descriptions_fresnel = R.string.decibell_to_watt; // текст с описанием


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decibel_to_watt);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);  // включает отображение стрелочки назад в тулбаре
        //getSupportActionBar().setHomeAsUpIndicator(R.mipmap.back_orig);  // добавляем картинку клавише назад в тулбаре
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#f4fcf2'>дБмВт->Вт</font>"));

        TextView description = findViewById(R.id.description_id); // описание
        description.setText(descriptions_fresnel);
    }


    @Override public boolean onSupportNavigateUp() { onBackPressed(); return true; } // обработка назад в toolbar


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
