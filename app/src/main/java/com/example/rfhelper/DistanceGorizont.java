package com.example.rfhelper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class DistanceGorizont extends AppCompatActivity {

    int descriptionsDistanceGorizont = R.string.distance_horizont_description; // текст с описанием
    EditText firstAntHeight;  // поле ввода высоты первой антенны
    EditText secondAntHeight;  // поле ввода высоты второй антенны
    TextView textView_result; // результат расчета
    TextView textView_result_refraction; // результат расчета с учетом рефракции
    TextView description;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_distance_gorizont);

        description = findViewById(R.id.description_id); // добавили описание что такое радиогоризонт
        description.setText(descriptionsDistanceGorizont);

    }




    public void refresh(){

        Double heightFirstAnt = Double.parseDouble(firstAntHeight.getText().toString());
        Double heightSecondtAnt = Double.parseDouble(secondAntHeight.getText().toString());

        Double result = (   3.57*(Math.sqrt(heightFirstAnt) + Math.sqrt(heightSecondtAnt)) );

        textView_result.setText( "Дальность радиогоризонта составляет - " + (String.format("%.1f", result)) + " км.");
        textView_result_refraction.setText( "С учетом рефракции - " + (String.format("%.1f", result*1.06)) + " км.");

    }




    public void fieldsCorrect(View view){  // проверка правильности заполнения полей


        firstAntHeight = findViewById(R.id.first_ant_height);  // высота первой антенны
        secondAntHeight = findViewById(R.id.second_ant_height); // высота второй антенны
        textView_result = findViewById(R.id.distance_id); // результат
        textView_result_refraction = findViewById(R.id.distance_refraction_id); // результат


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
