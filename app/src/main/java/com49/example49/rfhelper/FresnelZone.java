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

public class FresnelZone extends AppCompatActivity {

    final String INPUT_ERROR = "Убедитесь в правильности заполнения полей";
    private int mDescriptionsFresnel = R.string.descriptions_fresnel; // текст с описанием
    private EditText mFreqEditText;  // поле ввода частоты в МГц
    private EditText mDistanceEditText; // поле ввода расстояния в метрах
    private TextView mTextView100; // результат 100% зоны Френеля
    private TextView mTextView80; // результат 80% зоны Френеля
    private TextView mTextView60; // результат 60% зоны Френеля
    private TextView mDescription;
    private ImageView mImageHeader;
    private AnimationDrawable mAnimationDrawable;
    private Button mButtonCalc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fresnel_zone);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#f4fcf2'>Зона Френеля</font>"));
        init();
        calculate();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


    void init() {
        mFreqEditText = findViewById(R.id.freq_text_edit_id);  // поле ввода частоты в МГц
        mDistanceEditText = findViewById(R.id.distance_text_edit_id); // поле ввода расстояния в метрах
        mTextView100 = findViewById(R.id.radius_100_id); // результат 100% зоны Френеля
        mTextView80 = findViewById(R.id.radius_80_id); // результат 80% зоны Френеля
        mTextView60 = findViewById(R.id.radius_60_id); // результат 60% зоны Френеля

        mDescription = findViewById(R.id.description_id);
        mDescription.setText(mDescriptionsFresnel);

        mImageHeader = findViewById(R.id.header_id);
        mImageHeader.setBackgroundResource(R.drawable.fresnel_animate);

        mAnimationDrawable = (AnimationDrawable) mImageHeader.getBackground();
        mAnimationDrawable.start();

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
            double distance = Double.parseDouble(mDistanceEditText.getText().toString());
            double freq = Double.parseDouble(mFreqEditText.getText().toString());
            double zoneDouble_100 = (17.3) * Math.sqrt((distance / (4 * freq)));

            mTextView100.setText("100% - " + String.format("%.1f", zoneDouble_100));
            mTextView80.setText("80% - " + (String.format("%.1f", zoneDouble_100 * 0.8)));
            mTextView60.setText("60% - " + (String.format("%.1f", zoneDouble_100 * 0.6)));

        } catch (NumberFormatException e) {
            Toast.makeText(this, INPUT_ERROR, Toast.LENGTH_LONG).show();
        }

    }

}
