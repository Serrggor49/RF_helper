package com49.example49.rfhelper;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com49.example49.rfhelper.R;

public class FresnelZone extends AppCompatActivity {


    int descriptions_fresnel = R.string.descriptions_fresnel; // текст с описанием

    EditText freqEditText;  // поле ввода частоты в МГц
    EditText distanceEditText; // поле ввода расстояния в метрах
    TextView textView_100; // результат 100% зоны Френеля
    TextView textView_80; // результат 80% зоны Френеля
    TextView textView_60; // результат 60% зоны Френеля

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fresnel_zone);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);  // включает отображение стрелочки назад в тулбаре
        //getSupportActionBar().setHomeAsUpIndicator(R.mipmap.back_orig);  // добавляем картинку клавише назад в тулбаре
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#f4fcf2'>Зона Френеля</font>"));

        TextView description = findViewById(R.id.description_id); // описание зоны Френеля
        description.setText(descriptions_fresnel);


        ImageView imageView = (ImageView) findViewById(R.id.header_id);
        imageView.setBackgroundResource(R.drawable.fresnel_animate);
        AnimationDrawable mAnimationDrawable = (AnimationDrawable) imageView.getBackground();
        mAnimationDrawable.start();

    }

    @Override public boolean onSupportNavigateUp() { onBackPressed(); return true; } // обработка назад в toolbar


    public void refresh(){

        Double distance = Double.parseDouble(distanceEditText.getText().toString());
        Double freq = Double.parseDouble(freqEditText.getText().toString());

        Double zoneDouble_100 = (17.3)*Math.sqrt( (distance/ (4*freq))  );

        textView_100.setText( "100% - " + String.format("%.1f", zoneDouble_100));
        textView_80.setText( "80% - " + (String.format("%.1f", zoneDouble_100*0.8)));
        textView_60.setText( "60% - " + (String.format("%.1f", zoneDouble_100*0.6)));

    }



    public void fieldsCorrect(View view){  // проверка правильности заполнения полей


        freqEditText = findViewById(R.id.freq_text_edit_id);  // поле ввода частоты в МГц
        distanceEditText = findViewById(R.id.distance_text_edit_id); // поле ввода расстояния в метрах
        textView_100 = findViewById(R.id.radius_100_id); // результат 100% зоны Френеля
        textView_80 = findViewById(R.id.radius_80_id); // результат 80% зоны Френеля
        textView_60 = findViewById(R.id.radius_60_id); // результат 60% зоны Френеля


        try
        {
            //double distance = Double.parseDouble(distanceEditText.getText().toString());
            //double freq = Double.parseDouble(freqEditText.getText().toString());
            refresh();

        } catch (Exception e1) {
            e1.printStackTrace();
            Toast.makeText(this, "Убедитесь в правильности заполнения полей", Toast.LENGTH_LONG).show();

        }




    }


}
