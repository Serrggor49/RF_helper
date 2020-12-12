package com49.example49.rfhelper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class SwrToPower extends AppCompatActivity {

    private static final int DESCRIPTION = R.string.swr_to_dbm; // текст с описанием
    private static final String BAR_TITLE = "КСВ->дБ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swr_to_power);

        TextView description = findViewById(R.id.description_id); // добавили описание что такое радиогоризонт
        description.setText(DESCRIPTION);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(BAR_TITLE);
        }
    }

    @Override public boolean onSupportNavigateUp() {
        onBackPressed(); return true;
    }

}