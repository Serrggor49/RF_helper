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

    private static final String INPUT_ERROR = "Убедитесь в правильности заполнения полей";
    private static final String FREQ_DEFAULT = "2400";
    private static final String DISTANCE_DEFAULT = "1000";
    private static final String KEY_FREQ = "KEY_FREQ";
    private static final String KEY_DISTANCE = "KEY_DISTANCE";
    private static final String APP_PREFERENCES = "SignalLossDistance";
    private static final String BAR_TITLE = "<font color='#f4fcf2'>Затухание сигнала</font>";
    private static final int DESCRIPTION = R.string.signal_loss_distance; // текст с описанием

    private EditText freqEditText;  // поле ввода частоты сигнала
    private EditText distanceEditText;  // поле ввода частоты сигнала
    private TextView textViewResult; // результат расчета
    private SharedPreferences mSettings;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signal_loss_distance);
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
        freqEditText.setText(mSettings.getString(KEY_FREQ, FREQ_DEFAULT));
        distanceEditText.setText( mSettings.getString(KEY_DISTANCE, DISTANCE_DEFAULT));
    }

    void init() {
        ImageView imageView = findViewById(R.id.header_id);
        imageView.setBackgroundResource(R.drawable.loss_signal);

        TextView description = findViewById(R.id.description_id); // добавили описание что такое радиогоризонт
        description.setText(DESCRIPTION);

        freqEditText = findViewById(R.id.freq_id);  // частота
        distanceEditText = findViewById(R.id.distance_id); // расстояние
        textViewResult = findViewById(R.id.loss_id); // результат

        Button mButtonCalc = findViewById(R.id.button_calc_id);
        mButtonCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculate();
            }
        });

        setGrayColorForResult(freqEditText);
        setGrayColorForResult(distanceEditText);

    }

    public void calculate() {

        try {
            double freq = Double.parseDouble(freqEditText.getText().toString());
            double distance = Double.parseDouble(distanceEditText.getText().toString());
            Double result = (32.4 + 20 * Math.log10(freq) + 20 * Math.log10(distance));
            textViewResult.setText("Затухание дБ: " + (String.format("%.1f", result)) + " дБ.");

            textViewResult.setTextColor(getResources().getColor(R.color.Black));
        } catch (NumberFormatException e) {
            Toast.makeText(this, INPUT_ERROR, Toast.LENGTH_LONG).show();
        }

    }

    @Override
    protected void onPause() {
        super.onPause();

        SharedPreferences.Editor editor = mSettings.edit();

        editor.putString(KEY_FREQ, freqEditText.getText().toString());
        editor.putString(KEY_DISTANCE, distanceEditText.getText().toString());
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
                textViewResult.setTextColor(getResources().getColor(R.color.gray_light));
            }
        });
    }

}
