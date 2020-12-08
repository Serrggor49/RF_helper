package com49.example49.rfhelper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

public class SwrToPower extends AppCompatActivity {

    private TextView description;
    private int descriptionsSignalLossDistance = R.string.swr_to_dbm; // текст с описанием


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swr_to_power);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);  // включает отображение стрелочки назад в тулбаре
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#f4fcf2'>КСВ->дБ</font>"));

        description = findViewById(R.id.description_id); // добавили описание что такое радиогоризонт
        description.setText(descriptionsSignalLossDistance);
    }

    @Override public boolean onSupportNavigateUp() {
        onBackPressed(); return true;
    }

}