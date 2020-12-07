package com49.example49.rfhelper;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com49.example49.rfhelper.R;

public class FresnelZone extends AppCompatActivity {

    final String INPUT_ERROR = "Убедитесь в правильности заполнения полей";
    private int descriptions_fresnel = R.string.descriptions_fresnel; // текст с описанием
    private EditText mFreqEditText;  // поле ввода частоты в МГц
    private EditText mDistanceEditText; // поле ввода расстояния в метрах
    private TextView mTextView_100; // результат 100% зоны Френеля
    private TextView mTextView_80; // результат 80% зоны Френеля
    private TextView mTextView_60; // результат 60% зоны Френеля

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fresnel_zone);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#f4fcf2'>Зона Френеля</font>"));
        init();

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


    void init() {
        TextView description = findViewById(R.id.description_id);
        description.setText(descriptions_fresnel);

        ImageView imageView = findViewById(R.id.header_id);
        imageView.setBackgroundResource(R.drawable.fresnel_animate);

        AnimationDrawable mAnimationDrawable = (AnimationDrawable) imageView.getBackground();
        mAnimationDrawable.start();

        Button buttonCalc = findViewById(R.id.button_calc_id);
        buttonCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fieldsCorrect();
            }
        });
    }

    public void calculate() {

        double distance = Double.parseDouble(mDistanceEditText.getText().toString());
        double freq = Double.parseDouble(mFreqEditText.getText().toString());

        Double zoneDouble_100 = (17.3) * Math.sqrt((distance / (4 * freq)));

        mTextView_100.setText("100% - " + String.format("%.1f", zoneDouble_100));
        mTextView_80.setText("80% - " + (String.format("%.1f", zoneDouble_100 * 0.8)));
        mTextView_60.setText("60% - " + (String.format("%.1f", zoneDouble_100 * 0.6)));
    }

    public void fieldsCorrect() {  // проверка правильности заполнения полей

        mFreqEditText = findViewById(R.id.freq_text_edit_id);  // поле ввода частоты в МГц
        mDistanceEditText = findViewById(R.id.distance_text_edit_id); // поле ввода расстояния в метрах
        mTextView_100 = findViewById(R.id.radius_100_id); // результат 100% зоны Френеля
        mTextView_80 = findViewById(R.id.radius_80_id); // результат 80% зоны Френеля
        mTextView_60 = findViewById(R.id.radius_60_id); // результат 60% зоны Френеля


        try {
            //double distance = Double.parseDouble(distanceEditText.getText().toString());
            //double freq = Double.parseDouble(freqEditText.getText().toString());
            calculate();

        } catch (Exception e1) {
            e1.printStackTrace();
            Toast.makeText(this, INPUT_ERROR, Toast.LENGTH_LONG).show();

        }


    }


}
