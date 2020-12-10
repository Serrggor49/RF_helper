package com49.example49.rfhelper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class DecibelToWatt extends AppCompatActivity {

    private String INPUT_ERROR = "Укажите мощность сигнала в dBm";
    private EditText mPowerDecibellEdit;  // поле ввода мощности в dBm
    private TextView mPowerWatt; // результат вычислений
    private int mDescriptionsFresnel = R.string.decibell_to_watt; // текст с описанием
    private TextView mDescription;
    private Button mButtonCalc;

    private String POWER_DBM_DEFAULT = "30";
    private String mKeyPower = "mKeyPower";
    private SharedPreferences mSettings;
    final String APP_PREFERENCES = "DecibelToWatt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decibel_to_watt);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);  // включает отображение стрелочки назад в тулбаре
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#f4fcf2'>дБмВт->Вт</font>"));
        init();
        getLastValues();
        calculate();

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    void getLastValues() {
        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        mPowerDecibellEdit.setText(mSettings.getString(mKeyPower, POWER_DBM_DEFAULT));
    }

    void init() {
        mDescription = findViewById(R.id.description_id); // описание
        mDescription.setText(mDescriptionsFresnel);

        mPowerDecibellEdit = findViewById(R.id.power_decibell_id);
        mPowerWatt = findViewById(R.id.power_watt_id);

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
            double powerDbm = Double.parseDouble(mPowerDecibellEdit.getText().toString());
            double powerWt = (Math.pow(10, (powerDbm / 10))); // мощность в мВт

            if (powerWt >= 1000) {  // если мощность свыше 1000 мВт, то отображаем ее в Вт
                powerWt = powerWt / 1000;
                mPowerWatt.setText("Мощность: " + String.format("%.1f", powerWt) + " Вт");
            } else {
                mPowerWatt.setText("Мощность: " + String.format("%.0f", powerWt) + " мВт");
            }

        } catch (NumberFormatException e) {
            Toast.makeText(this, INPUT_ERROR, Toast.LENGTH_LONG).show();
        }

    }


    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor editor = mSettings.edit();
        editor.putString(mKeyPower, mPowerDecibellEdit.getText().toString());
        editor.apply();
    }



}
