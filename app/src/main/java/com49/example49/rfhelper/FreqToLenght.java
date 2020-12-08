package com49.example49.rfhelper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class FreqToLenght extends AppCompatActivity {

    private String INPUT_ERROR = "Укажите частоту";
    private int mDescriptions_fresnel = R.string.freq_to_lenght;
    private EditText mFreqEditText;
    private TextView mLenghtWave;
    private TextView mLenghtWave_2;
    private TextView mLenghtWave_4;
    private TextView mLenghtWave_8;
    private Button mButtonCalc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_freq_to_lenght);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);  // включает отображение стрелочки назад в тулбаре
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#f4fcf2'>Расчет длины волны</font>"));
        init();
        calculate();

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


    void init() {
        TextView description = findViewById(R.id.description_id); // описание
        description.setText(mDescriptions_fresnel);

        mFreqEditText = findViewById(R.id.freq_edit_text_id);
        mLenghtWave = findViewById(R.id.lenght_wave_id);
        mLenghtWave_2 = findViewById(R.id.lenght_wave_id_2);
        mLenghtWave_4 = findViewById(R.id.lenght_wave_id_4);
        mLenghtWave_8 = findViewById(R.id.lenght_wave_id_8);

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
            Double freq = Double.parseDouble(mFreqEditText.getText().toString());

            mLenghtWave.setText("λ:     " + String.format("%.3f", 299792458 / (freq * 1000000)) + " м.");
            mLenghtWave_2.setText("λ/2:  " + String.format("%.3f", 299792458 / (freq * 1000000 * 2)) + " м.");
            mLenghtWave_4.setText("λ/4:  " + String.format("%.3f", 299792458 / (freq * 1000000 * 4)) + " м.");
            mLenghtWave_8.setText("λ/8:  " + String.format("%.3f", 299792458 / (freq * 1000000 * 8)) + " м.");
        } catch (NumberFormatException e) {
            Toast.makeText(this, INPUT_ERROR, Toast.LENGTH_LONG).show();

        }
    }


}
