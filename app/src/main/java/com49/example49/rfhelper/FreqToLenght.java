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

    private String INPUT_ERROR = "Укажите частоту";
    private int mDescriptions_fresnel = R.string.freq_to_lenght;
    private EditText mFreqEditText;
    private TextView mLenghtWave;
    private TextView mLenghtWave_2;
    private TextView mLenghtWave_4;
    private TextView mLenghtWave_8;
    private Button mButtonCalc;

    private String FREQ_DEFAULT = "30";
    private String mKeyFreq = "mKeyFreq";
    private SharedPreferences mSettings;
    final String APP_PREFERENCES = "FreqToLenght";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_freq_to_lenght);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);  // включает отображение стрелочки назад в тулбаре
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#f4fcf2'>Расчет длины волны</font>"));
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
        mFreqEditText.setText(mSettings.getString(mKeyFreq, FREQ_DEFAULT));
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

        setGrayColorForResult(mFreqEditText);

    }

    public void calculate() {

        try {

            double freq = Double.parseDouble(mFreqEditText.getText().toString());
            double lamda = 299792458 / (freq * 1000000);


            mLenghtWave.setTextColor(getResources().getColor(R.color.Black));
            mLenghtWave_2.setTextColor(getResources().getColor(R.color.Black));
            mLenghtWave_4.setTextColor(getResources().getColor(R.color.Black));
            mLenghtWave_8.setTextColor(getResources().getColor(R.color.Black));

            if (lamda >= 1) {
                mLenghtWave.setText("λ:     " + String.format("%.2f", lamda) + " м.");
                mLenghtWave_2.setText("λ/2:  " + String.format("%.2f", (lamda / 2)) + " м.");
                mLenghtWave_4.setText("λ/4:  " + String.format("%.2f", (lamda / 4)) + " м.");
                mLenghtWave_8.setText("λ/8:  " + String.format("%.2f", (lamda / 8)) + " м.");
            }
            else if (lamda>0.01 ) {
                mLenghtWave.setText("λ:     " + String.format("%.3f", lamda * 100) + " cм.");
                mLenghtWave_2.setText("λ/2:  " + String.format("%.3f", (lamda * 100 / 2)) + " cм.");
                mLenghtWave_4.setText("λ/4:  " + String.format("%.3f", (lamda * 100 / 4)) + " cм.");
                mLenghtWave_8.setText("λ/8:  " + String.format("%.3f", (lamda * 100 / 8)) + " cм.");
            }

            else {
                mLenghtWave.setText("λ:     " + String.format("%.3f", lamda * 1000) + " мм.");
                mLenghtWave_2.setText("λ/2:  " + String.format("%.3f", (lamda * 1000 / 2)) + " мм.");
                mLenghtWave_4.setText("λ/4:  " + String.format("%.3f", (lamda * 1000 / 4)) + " мм.");
                mLenghtWave_8.setText("λ/8:  " + String.format("%.3f", (lamda * 1000 / 8)) + " мм.");
            }


        } catch (NumberFormatException e) {
            Toast.makeText(this, INPUT_ERROR, Toast.LENGTH_LONG).show();

        }
    }



    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor editor = mSettings.edit();
        editor.putString(mKeyFreq, mFreqEditText.getText().toString());
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
                mLenghtWave_2.setTextColor(getResources().getColor(R.color.gray_light));
                mLenghtWave_4.setTextColor(getResources().getColor(R.color.gray_light));
                mLenghtWave_8.setTextColor(getResources().getColor(R.color.gray_light));
            }
        });
    }

}
