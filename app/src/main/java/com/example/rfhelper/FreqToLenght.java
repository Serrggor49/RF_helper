package com.example.rfhelper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class FreqToLenght extends AppCompatActivity {

    int descriptions_fresnel = R.string.freq_to_lenght; // текст с описанием

    EditText freqEditText;  // поле ввода частоты;
    TextView lenghtWave; // результат вычислений
    TextView lenghtWave_2; // результат вычислений
    TextView lenghtWave_4; // результат вычислений
    TextView lenghtWave_8; // результат вычислений

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_freq_to_lenght);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);  // включает отображение стрелочки назад в тулбаре
        //getSupportActionBar().setHomeAsUpIndicator(R.mipmap.back_orig);  // добавляем картинку клавише назад в тулбаре
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#f4fcf2'>Расчет длины волны</font>"));

        TextView description = findViewById(R.id.description_id); // описание
        description.setText(descriptions_fresnel);

    }

    @Override public boolean onSupportNavigateUp() { onBackPressed(); return true; } // обработка назад в toolbar


    public void refresh(){

        Double freq = Double.parseDouble(freqEditText.getText().toString());
        Double result = 299792458/(freq*1000000); // получаем результат в метрах


        lenghtWave.setText("λ - " + String.format("%.3f", 299792458/(freq*1000000)) + " м");
        lenghtWave_2.setText("λ/2 - " + String.format("%.3f", 299792458/(freq*1000000*2)) + " м");
        lenghtWave_4.setText("λ/4 - " + String.format("%.3f", 299792458/(freq*1000000*4)) + " м");
        lenghtWave_8.setText("λ/8 - " + String.format("%.3f", 299792458/(freq*1000000*8)) + " м");

    }



    public void fieldsCorrect(View view){  // проверка правильности заполнения полей

        freqEditText = findViewById(R.id.freq_edit_text_id);
        lenghtWave = findViewById(R.id.lenght_wave_id);
        lenghtWave_2 = findViewById(R.id.lenght_wave_id_2);
        lenghtWave_4 = findViewById(R.id.lenght_wave_id_4);
        lenghtWave_8 = findViewById(R.id.lenght_wave_id_8);


        try
        {
            refresh();

        } catch (Exception e1) {
            e1.printStackTrace();
            Toast.makeText(this, "Убедитесь в правильности заполнения полей", Toast.LENGTH_LONG).show();

        }




    }



}
