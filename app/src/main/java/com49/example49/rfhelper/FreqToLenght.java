package com49.example49.rfhelper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class FreqToLenght extends AppCompatActivity {

    private static final String INPUT_ERROR = "Укажите частоту";
    private static final String FREQ_DEFAULT = "30";
    private static final String KEY_FREQ = "mKeyFreq";
    private static final String APP_PREFERENCES = "FreqToLenght";
    private static final int DESCRIPTION = R.string.freq_to_lenght; // текст с описанием
    private static final String BAR_TITLE = "<font color='#f4fcf2'>Расчет длины волны</font>";



    private EditText mFreqEditText;
    private TextView mLenghtWave;
    private TextView mLenghtWave2;
    private TextView mLenghtWave4;
    private TextView mLenghtWave8;
    private SharedPreferences mSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_freq_to_lenght);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);  // включает отображение стрелочки назад в тулбаре
        getSupportActionBar().setTitle(Html.fromHtml(BAR_TITLE));
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
        mFreqEditText.setText(mSettings.getString(KEY_FREQ, FREQ_DEFAULT));
    }

    void init() {
        TextView description = findViewById(R.id.description_id); // описание
        description.setText(DESCRIPTION);

        mFreqEditText = findViewById(R.id.freq_edit_text_id);
        mLenghtWave = findViewById(R.id.lenght_wave_id);
        mLenghtWave2 = findViewById(R.id.lenght_wave_id_2);
        mLenghtWave4 = findViewById(R.id.lenght_wave_id_4);
        mLenghtWave8 = findViewById(R.id.lenght_wave_id_8);

        Button buttonCalc = findViewById(R.id.button_calc_id);
        buttonCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculate();
            }
        });

        setGrayColorForResult(mFreqEditText);

    }

    public void calculate() {

        try {
            double freq = Double.parseDouble(mFreqEditText.getText().toString());
            double lamda = 299792458 / (freq * 1000000);

            mLenghtWave.setTextColor(getResources().getColor(R.color.Black));
            mLenghtWave2.setTextColor(getResources().getColor(R.color.Black));
            mLenghtWave4.setTextColor(getResources().getColor(R.color.Black));
            mLenghtWave8.setTextColor(getResources().getColor(R.color.Black));

            if (lamda >= 1) {
                mLenghtWave.setText("λ:     " + String.format("%.2f", lamda) + " м.");
                mLenghtWave2.setText("λ/2:  " + String.format("%.2f", (lamda / 2)) + " м.");
                mLenghtWave4.setText("λ/4:  " + String.format("%.2f", (lamda / 4)) + " м.");
                mLenghtWave8.setText("λ/8:  " + String.format("%.2f", (lamda / 8)) + " м.");
            }
            else if (lamda>0.01 ) {
                mLenghtWave.setText("λ:     " + String.format("%.3f", lamda * 100) + " cм.");
                mLenghtWave2.setText("λ/2:  " + String.format("%.3f", (lamda * 100 / 2)) + " cм.");
                mLenghtWave4.setText("λ/4:  " + String.format("%.3f", (lamda * 100 / 4)) + " cм.");
                mLenghtWave8.setText("λ/8:  " + String.format("%.3f", (lamda * 100 / 8)) + " cм.");
            }

            else {
                mLenghtWave.setText("λ:     " + String.format("%.3f", lamda * 1000) + " мм.");
                mLenghtWave2.setText("λ/2:  " + String.format("%.3f", (lamda * 1000 / 2)) + " мм.");
                mLenghtWave4.setText("λ/4:  " + String.format("%.3f", (lamda * 1000 / 4)) + " мм.");
                mLenghtWave8.setText("λ/8:  " + String.format("%.3f", (lamda * 1000 / 8)) + " мм.");
            }


        } catch (NumberFormatException e) {
            Toast.makeText(this, INPUT_ERROR, Toast.LENGTH_LONG).show();

        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor editor = mSettings.edit();
        editor.putString(KEY_FREQ, mFreqEditText.getText().toString());
        editor.apply();
    }


    /*
     * в случае внесения изменений в EditText
     * меняем цвет вычислений на светло серый, чтобы
     * визуально обозначить их неактуальность. После выполнения
     * метода calculate, значения снова становятся актуальными.
     */
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
                mLenghtWave.setTextColor(getResources().getColor(R.color.gray_light));
                mLenghtWave2.setTextColor(getResources().getColor(R.color.gray_light));
                mLenghtWave4.setTextColor(getResources().getColor(R.color.gray_light));
                mLenghtWave8.setTextColor(getResources().getColor(R.color.gray_light));
            }
        });
    }

}
