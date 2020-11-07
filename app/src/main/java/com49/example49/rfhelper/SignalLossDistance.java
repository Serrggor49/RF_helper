package com49.example49.rfhelper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com49.example49.rfhelper.R;

public class SignalLossDistance extends AppCompatActivity {

    int descriptionsSignalLossDistance = R.string.signal_loss_distance; // текст с описанием
    EditText freqEditText;  // поле ввода частоты сигнала
    EditText distanceEditText;  // поле ввода частоты сигнала
    TextView textView_result; // результат расчета
    TextView description;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signal_loss_distance);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);  // включает отображение стрелочки назад в тулбаре
        //getSupportActionBar().setHomeAsUpIndicator(R.mipmap.back_orig);  // добавляем картинку клавише назад в тулбаре
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#f4fcf2'>Затухание сигнала</font>"));

        description = findViewById(R.id.description_id); // добавили описание что такое радиогоризонт
        description.setText(descriptionsSignalLossDistance);
    }

    @Override public boolean onSupportNavigateUp() { onBackPressed(); return true; } // обработка назад в toolbar


    public void refresh() {

        Double freq = Double.parseDouble(freqEditText.getText().toString());
        Double distance = Double.parseDouble(distanceEditText.getText().toString());

        Double result = (32.4 + 20 * Math.log10(freq) + 20 * Math.log10(distance));

        textView_result.setText("Затухание сигнала составляет - " + (String.format("%.1f", result)) + " дБ.");

    }


    public void fieldsCorrect(View view) {  // проверка правильности заполнения полей


        freqEditText = findViewById(R.id.freq_id);  // частота
        distanceEditText = findViewById(R.id.distance_id); // расстояние
        textView_result = findViewById(R.id.loss_id); // результат


        try {
            //double distance = Double.parseDouble(distanceEditText.getText().toString());
            //double freq = Double.parseDouble(freqEditText.getText().toString());
            refresh();

        } catch (Exception e1) {
            e1.printStackTrace();
            Toast.makeText(this, "Убедитесь в правильности заполнения полей", Toast.LENGTH_LONG).show();

        }

    }
}
