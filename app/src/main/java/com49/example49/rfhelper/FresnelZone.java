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

public class FresnelZone extends AppCompatActivity {

    final String INPUT_ERROR = "Убедитесь в правильности заполнения полей";
    private int mDescriptionsFresnel = R.string.descriptions_fresnel;
    private EditText mFreqEditText;
    private EditText mDistanceEditText;
    private TextView mTextView100;
    private TextView mTextView80;
    private TextView mTextView60;
    private TextView mDescription;
    private ImageView mImageHeader;
    private AnimationDrawable mAnimationDrawable;
    private Button mButtonCalc;


    private String FREQ_DEFAULT = "900";
    private String DISTANCE_DEFAULT = "2000";
    private String mKeylastFreq = "mKeylastFreq";
    private String mKeylastDistance = "mKeylastDistance";
    private SharedPreferences mSettings;
    final String APP_PREFERENCES = "FresnelZone";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fresnel_zone);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#f4fcf2'>Зона Френеля</font>"));
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

        mFreqEditText.setText(mSettings.getString(mKeylastFreq, FREQ_DEFAULT));
        mDistanceEditText.setText(mSettings.getString(mKeylastDistance, DISTANCE_DEFAULT));
    }

    void init() {
        mFreqEditText = findViewById(R.id.freq_text_edit_id);
        mDistanceEditText = findViewById(R.id.distance_text_edit_id);
        mTextView100 = findViewById(R.id.radius_100_id);
        mTextView80 = findViewById(R.id.radius_80_id);
        mTextView60 = findViewById(R.id.radius_60_id);

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

        setGrayColor(mFreqEditText);
        setGrayColor(mDistanceEditText);
    }

    public void calculate() {
        try {
            double distance = Double.parseDouble(mDistanceEditText.getText().toString());
            double freq = Double.parseDouble(mFreqEditText.getText().toString());
            double zoneDouble_100 = (17.3) * Math.sqrt((distance / (4 * freq)));

            mTextView100.setText("100% - " + String.format("%.1f", zoneDouble_100));
            mTextView80.setText("80% - " + (String.format("%.1f", zoneDouble_100 * 0.8)));
            mTextView60.setText("60% - " + (String.format("%.1f", zoneDouble_100 * 0.6)));

            mTextView100.setTextColor(getResources().getColor(R.color.Black));
            mTextView80.setTextColor(getResources().getColor(R.color.Black));
            mTextView60.setTextColor(getResources().getColor(R.color.Black));

        } catch (NumberFormatException e) {
            Toast.makeText(this, INPUT_ERROR, Toast.LENGTH_LONG).show();
        }

    }


    @Override
    protected void onPause() {
        super.onPause();

        SharedPreferences.Editor editor = mSettings.edit();
        editor.putString(mKeylastFreq, mFreqEditText.getText().toString());
        editor.putString(mKeylastDistance, mDistanceEditText.getText().toString());
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
                mTextView100.setTextColor(getResources().getColor(R.color.gray_light));
                mTextView80.setTextColor(getResources().getColor(R.color.gray_light));
                mTextView60.setTextColor(getResources().getColor(R.color.gray_light));
            }
        });
    }


}
