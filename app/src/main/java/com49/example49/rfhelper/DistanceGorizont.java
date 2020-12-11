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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class DistanceGorizont extends AppCompatActivity {

    private static final String INPUT_ERROR = "Убедитесь в правильности заполнения полей";
    private static final int DESCRIPTION = R.string.distance_horizont_description; // текст с описанием
    private static final String HEIGHT_FIRST_ANT_DEFAULT = "20";
    private static final String HEIGHT_SECOND_ANT_DEFAULT = "5";
    private static final String KEY_HEIGHT_FIRST_ANT = "KEY_HEIGHT_FIRST_ANT";
    private static final String KEY_HEIGHT_SECOND_ANT = "KEY_HEIGHT_SECOND_ANT";
    private static final String APP_PREFERENCES = "DistanceGorizont";
    private static final int HEADER = R.drawable.header_distance_horizont;


    private EditText mFirstAntHeight;  // поле ввода высоты первой антенны
    private EditText mSecondAntHeight;  // поле ввода высоты второй антенны
    private TextView mTextView_result; // результат расчета
    private TextView mTextViewResultRefraction; // результат расчета с учетом рефракции
    private SharedPreferences mSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_distance_gorizont);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#f4fcf2'>Дальность видимости</font>"));
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
        mFirstAntHeight.setText(mSettings.getString(KEY_HEIGHT_FIRST_ANT, HEIGHT_FIRST_ANT_DEFAULT));
        mSecondAntHeight.setText(mSettings.getString(KEY_HEIGHT_SECOND_ANT, HEIGHT_SECOND_ANT_DEFAULT));
    }

    void init() {
        TextView mDescription = findViewById(R.id.description_id); // добавили описание что такое радиогоризонт
        Button mButtonCalc = findViewById(R.id.button_calc_id);
        ImageView mImageHeader = findViewById(R.id.header_id);

        mFirstAntHeight = findViewById(R.id.first_ant_height);  // высота первой антенны
        mSecondAntHeight = findViewById(R.id.second_ant_height); // высота второй антенны
        mTextView_result = findViewById(R.id.distance_id); // результат
        mTextViewResultRefraction = findViewById(R.id.distance_refraction_id); // результат
        mDescription.setText(DESCRIPTION);

        mImageHeader.setBackgroundResource(HEADER);
        mDescription.setText(DESCRIPTION);


        mButtonCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculate();
            }
        });

        setGrayColorForResult(mFirstAntHeight);
        setGrayColorForResult(mSecondAntHeight);
    }


    public void calculate() {

        try {
            double heightFirstAnt = Double.parseDouble(mFirstAntHeight.getText().toString());
            double heightSecondtAnt = Double.parseDouble(mSecondAntHeight.getText().toString());
            double result = (3.57 * (Math.sqrt(heightFirstAnt) + Math.sqrt(heightSecondtAnt)));

            mTextView_result.setText("Дальность видимости: " + (String.format("%.1f", result)) + " км.");
            mTextViewResultRefraction.setText("С учетом рефракции: " + (String.format("%.1f", result * 1.06)) + " км.");

            mTextView_result.setTextColor(getResources().getColor(R.color.Black));
            mTextViewResultRefraction.setTextColor(getResources().getColor(R.color.Black));
        } catch (NumberFormatException e) {
            Toast.makeText(this, INPUT_ERROR, Toast.LENGTH_LONG).show();
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


    /*
     * в случае внесения изменений в EditText
     * меняем цвет вычислений на светло серый, чтобы
     * визуально обозначить их неактуальность. После выполнения
     * метода calculate, значения снова становятся актуальными.
     */
    private void setGrayColorForResult (final EditText editText) {

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                mTextView_result.setTextColor(getResources().getColor(R.color.gray_light));
                mTextViewResultRefraction.setTextColor(getResources().getColor(R.color.gray_light));
            }
        });
    }

}
