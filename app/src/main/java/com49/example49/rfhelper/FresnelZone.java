package com49.example49.rfhelper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class FresnelZone extends AppCompatActivity {

    private static final String INPUT_ERROR = "Убедитесь в правильности заполнения полей";
    private static final String BAR_TITLE = "Зона Френеля";
    private static final String FREQ_DEFAULT = "900";
    private static final String DISTANCE_DEFAULT = "2000";
    private static final String KEY_LAST_FREQ = "KEY_LAST_FREQ";
    private static final String KEY_LAST_DISTANCE = "KEY_LAST_DISTANCE";
    private static final String APP_PREFERENCES = "FresnelZone";
    private static final int HEADER = R.drawable.fresnel_animate;
    private static final int DESCRIPTION = R.string.descriptions_fresnel;

    private EditText mFreqEditText;
    private EditText mDistanceEditText;
    private TextView mTextView100;
    private TextView mTextView80;
    private TextView mTextView60;
    private SharedPreferences mSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fresnel_zone);
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
        editor.putString(KEY_LAST_FREQ, mFreqEditText.getText().toString());
        editor.putString(KEY_LAST_DISTANCE, mDistanceEditText.getText().toString());
        editor.apply();
    }

    private void init() {
        TextView description = findViewById(R.id.description_id);
        ImageView imageHeader = findViewById(R.id.header_id);
        Button buttonCalc = findViewById(R.id.button_calc_id);
        mFreqEditText = findViewById(R.id.freq_text_edit_id);
        mDistanceEditText = findViewById(R.id.distance_text_edit_id);
        mTextView100 = findViewById(R.id.radius_100_id);
        mTextView80 = findViewById(R.id.radius_80_id);
        mTextView60 = findViewById(R.id.radius_60_id);
        imageHeader.setBackgroundResource(HEADER);
        description.setText(DESCRIPTION);

        AnimationDrawable mAnimationDrawable = (AnimationDrawable) imageHeader.getBackground();
        mAnimationDrawable.start();

        buttonCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculate();
            }
        });

        setGrayColorForResult(mFreqEditText);
        setGrayColorForResult(mDistanceEditText);
    }

    private void getLastValues() {
        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        mFreqEditText.setText(mSettings.getString(KEY_LAST_FREQ, FREQ_DEFAULT));
        mDistanceEditText.setText(mSettings.getString(KEY_LAST_DISTANCE, DISTANCE_DEFAULT));
    }

    private void calculate() {
        try {
            double distance = Double.parseDouble(mDistanceEditText.getText().toString());
            double freq = Double.parseDouble(mFreqEditText.getText().toString());
            double zoneDouble100 = (17.3) * Math.sqrt((distance / (4 * freq)));

            mTextView100.setText("100% - " + String.format("%.1f", zoneDouble100));
            mTextView80.setText("80% - " + (String.format("%.1f", zoneDouble100 * 0.8)));
            mTextView60.setText("60% - " + (String.format("%.1f", zoneDouble100 * 0.6)));

            mTextView100.setTextColor(getColor(R.color.Black));
            mTextView80.setTextColor(getColor(R.color.Black));
            mTextView60.setTextColor(getColor(R.color.Black));

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
                mTextView100.setTextColor(getColor(R.color.gray_light));
                mTextView80.setTextColor(getColor(R.color.gray_light));
                mTextView60.setTextColor(getColor(R.color.gray_light));
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }






}
