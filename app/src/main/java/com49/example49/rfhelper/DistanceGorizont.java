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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class DistanceGorizont extends AppCompatActivity {

    private static final String INPUT_ERROR = "Убедитесь в правильности заполнения полей";
    private static final String HEIGHT_FIRST_ANT_DEFAULT = "20";
    private static final String HEIGHT_SECOND_ANT_DEFAULT = "5";
    private static final String KEY_HEIGHT_FIRST_ANT = "KEY_HEIGHT_FIRST_ANT";
    private static final String KEY_HEIGHT_SECOND_ANT = "KEY_HEIGHT_SECOND_ANT";
    private static final String APP_PREFERENCES = "DistanceGorizont";
    private static final String BAR_TITLE = "Дальность видимости";
    private static final int HEADER = R.drawable.header_distance_horizont;
    private static final int DESCRIPTION = R.string.distance_horizont_description; // текст с описанием

    private EditText mFirstAntHeight;  // поле ввода высоты первой антенны
    private EditText mSecondAntHeight;  // поле ввода высоты второй антенны
    private TextView mTextViewResult; // результат расчета
    private TextView mTextViewResultRefraction; // результат расчета с учетом рефракции
    private SharedPreferences mSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_distance_gorizont);
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

        editor.putString(KEY_HEIGHT_FIRST_ANT, mFirstAntHeight.getText().toString());
        editor.putString(KEY_HEIGHT_SECOND_ANT, mSecondAntHeight.getText().toString());
        editor.apply();
    }

    private void init() {
        TextView description = findViewById(R.id.description_id); // добавили описание что такое радиогоризонт
        Button buttonCalc = findViewById(R.id.button_calc_id);
        ImageView imageHeader = findViewById(R.id.header_id);

        mFirstAntHeight = findViewById(R.id.first_ant_height);  // высота первой антенны
        mSecondAntHeight = findViewById(R.id.second_ant_height); // высота второй антенны
        mTextViewResult = findViewById(R.id.distance_id); // результат
        mTextViewResultRefraction = findViewById(R.id.distance_refraction_id); // результат
        description.setText(DESCRIPTION);

        imageHeader.setBackgroundResource(HEADER);
        description.setText(DESCRIPTION);

        buttonCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculate();
            }
        });

        setGrayColorForResult(mFirstAntHeight);
        setGrayColorForResult(mSecondAntHeight);
    }

    private void getLastValues() {
        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        mFirstAntHeight.setText(mSettings.getString(KEY_HEIGHT_FIRST_ANT, HEIGHT_FIRST_ANT_DEFAULT));
        mSecondAntHeight.setText(mSettings.getString(KEY_HEIGHT_SECOND_ANT, HEIGHT_SECOND_ANT_DEFAULT));
    }

    private void calculate() {

        try {
            double heightFirstAnt = Double.parseDouble(mFirstAntHeight.getText().toString());
            double heightSecondtAnt = Double.parseDouble(mSecondAntHeight.getText().toString());
            double result = (3.57 * (Math.sqrt(heightFirstAnt) + Math.sqrt(heightSecondtAnt)));

            mTextViewResult.setText("Дальность видимости: " + (String.format("%.1f", result)) + " км.");
            mTextViewResultRefraction.setText("С учетом рефракции: " + (String.format("%.1f", result * 1.06)) + " км.");

            mTextViewResult.setTextColor(getColor(R.color.Black));
            mTextViewResultRefraction.setTextColor(getColor(R.color.Black));
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
                mTextViewResult.setTextColor(getColor(R.color.gray_light));
                mTextViewResultRefraction.setTextColor(getColor(R.color.gray_light));
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
