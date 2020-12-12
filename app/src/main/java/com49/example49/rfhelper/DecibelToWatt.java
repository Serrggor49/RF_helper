package com49.example49.rfhelper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class DecibelToWatt extends AppCompatActivity {

    private static final int DESCRIPTION = R.string.decibell_to_watt; // текст с описанием
    private static final String INPUT_ERROR = "Укажите мощность сигнала в dBm";
    private static final String BAR_TITLE = "дБмВт->Вт";
    private static final String POWER_DBM_DEFAULT = "30";
    private static final String KEY_POWER = "KEY_POWER";
    private static final String APP_PREFERENCES = "DecibelToWatt";

    private EditText mPowerDecibellEdit;  // поле ввода мощности в dBm
    private TextView mPowerWatt; // результат вычислений
    private SharedPreferences mSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decibel_to_watt);
        init();
        getLastValues();
        calculate();

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(BAR_TITLE);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor editor = mSettings.edit();
        editor.putString(KEY_POWER, mPowerDecibellEdit.getText().toString());
        editor.apply();
    }

    private void init() {
        TextView description = findViewById(R.id.description_id); // описание
        Button buttonCalc = findViewById(R.id.button_calc_id);
        mPowerDecibellEdit = findViewById(R.id.power_decibell_id);
        mPowerWatt = findViewById(R.id.power_watt_id);
        description.setText(DESCRIPTION);

        buttonCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculate();
            }
        });

        setGrayColorForResult(mPowerDecibellEdit);
    }

    private void getLastValues() {
        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        mPowerDecibellEdit.setText(mSettings.getString(KEY_POWER, POWER_DBM_DEFAULT));
    }

    private void calculate() {

        try {
            double powerDbm = Double.parseDouble(mPowerDecibellEdit.getText().toString());
            double powerWt = (Math.pow(10, (powerDbm / 10))); // мощность в мВт
            mPowerWatt.setTextColor(getColor(R.color.Black));

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

    private void setGrayColorForResult(final EditText editText) {

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                mPowerWatt.setTextColor(getColor(R.color.gray_light));
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
