package com49.example49.rfhelper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class DistanceGorizont extends AppCompatActivity {

    private String INPUT_ERROR = "Убедитесь в правильности заполнения полей";
    private int mDescriptionsDistanceGorizont = R.string.distance_horizont_description; // текст с описанием
    private EditText mFirstAntHeight;  // поле ввода высоты первой антенны
    private EditText mSecondAntHeight;  // поле ввода высоты второй антенны
    private TextView mTextView_result; // результат расчета
    private TextView mTextViewResultRefraction; // результат расчета с учетом рефракции
    private TextView mDescription;
    private Button mButtonCalc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_distance_gorizont);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#f4fcf2'>Дальность видимости</font>"));
        init();
        calculate();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    void init() {
        mFirstAntHeight = findViewById(R.id.first_ant_height);  // высота первой антенны
        mSecondAntHeight = findViewById(R.id.second_ant_height); // высота второй антенны
        mTextView_result = findViewById(R.id.distance_id); // результат
        mTextViewResultRefraction = findViewById(R.id.distance_refraction_id); // результат

        mDescription = findViewById(R.id.description_id); // добавили описание что такое радиогоризонт
        mDescription.setText(mDescriptionsDistanceGorizont);

        mButtonCalc = findViewById(R.id.button_calc_id);
        mButtonCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculate();
            }
        });
    }


    public void calculate() {

        try {
            double heightFirstAnt = Double.parseDouble(mFirstAntHeight.getText().toString());
            double heightSecondtAnt = Double.parseDouble(mSecondAntHeight.getText().toString());
            double result = (3.57 * (Math.sqrt(heightFirstAnt) + Math.sqrt(heightSecondtAnt)));

            mTextView_result.setText("Дальность видимости - " + (String.format("%.1f", result)) + " км.");
            mTextViewResultRefraction.setText("С учетом рефракции - " + (String.format("%.1f", result * 1.06)) + " км.");
        } catch (NumberFormatException e) {
            Toast.makeText(this, INPUT_ERROR, Toast.LENGTH_LONG).show();
        }
    }
}
