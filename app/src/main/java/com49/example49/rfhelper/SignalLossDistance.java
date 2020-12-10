package com49.example49.rfhelper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
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

public class SignalLossDistance extends AppCompatActivity {

    private String INPUT_ERROR = "Убедитесь в правильности заполнения полей";
    private int descriptionsSignalLossDistance = R.string.signal_loss_distance; // текст с описанием
    private EditText freqEditText;  // поле ввода частоты сигнала
    private EditText distanceEditText;  // поле ввода частоты сигнала
    private TextView textView_result; // результат расчета
    private TextView description;
    private Button mButtonCalc;

    private String FREQ_DEFAULT = "2400";
    private String DISTANCE_DEFAULT = "1000";
    private String mKeyFreq = "mKeyFreq";
    private String mKeyDistance = "mKeyDistance";
    private SharedPreferences mSettings;
    final String APP_PREFERENCES = "SignalLossDistance";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signal_loss_distance);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);  // включает отображение стрелочки назад в тулбаре
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#f4fcf2'>Затухание сигнала</font>"));
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
        freqEditText.setText(mSettings.getString(mKeyFreq, FREQ_DEFAULT));
        distanceEditText.setText( mSettings.getString(mKeyDistance, DISTANCE_DEFAULT));
    }

    void init() {

        freqEditText = findViewById(R.id.freq_id);  // частота
        distanceEditText = findViewById(R.id.distance_id); // расстояние
        textView_result = findViewById(R.id.loss_id); // результат

        ImageView imageView = findViewById(R.id.header_id);
        imageView.setBackgroundResource(R.drawable.loss_signal);

//        AnimationDrawable mAnimationDrawable = (AnimationDrawable) imageView.getBackground();
//        mAnimationDrawable.start();

        description = findViewById(R.id.description_id); // добавили описание что такое радиогоризонт
        description.setText(descriptionsSignalLossDistance);

        mButtonCalc = findViewById(R.id.button_calc_id);
        mButtonCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculate();
            }
        });

        setGrayColor(freqEditText);
        setGrayColor(distanceEditText);

    }

    public void calculate() {

        try {
            double freq = Double.parseDouble(freqEditText.getText().toString());
            double distance = Double.parseDouble(distanceEditText.getText().toString());
            Double result = (32.4 + 20 * Math.log10(freq) + 20 * Math.log10(distance));
            textView_result.setText("Затухание дБ: " + (String.format("%.1f", result)) + " дБ.");

            freqEditText.setTextColor(getResources().getColor(R.color.Black));
            distanceEditText.setTextColor(getResources().getColor(R.color.Black));
        } catch (NumberFormatException e) {
            Toast.makeText(this, INPUT_ERROR, Toast.LENGTH_LONG).show();
        }

    }

    @Override
    protected void onPause() {
        super.onPause();

        SharedPreferences.Editor editor = mSettings.edit();

        editor.putString(mKeyFreq, freqEditText.getText().toString());
        editor.putString(mKeyDistance, distanceEditText.getText().toString());
        editor.apply();
    }


    /**
     * в случае внесения изменений в переданном EditText
     * меняем цвет полученных значений на светло серый, чтобы
     * визуально обозначить их неактуальность. После выполнения
     * метода calculate, значения снова становятся актуальными.
     */
    private void setGrayColor(final EditText editText) {

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                freqEditText.setTextColor(getResources().getColor(R.color.gray_light));
                distanceEditText.setTextColor(getResources().getColor(R.color.gray_light));
            }
        });
    }

}
