package com.example.rfhelper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

public class MainActivity extends AppCompatActivity {

    int WIDTH_DISPLAY; // ширина дисплея
    int BUTON_HEIGHT; // высота кнопки для квадратного изображения. Определяется шириной дисплея
    int HEIGHT_COEFFICIENT = 3; // данный коэффициент показывает, какую часть от ширины экрана будет занимать кнопка (например, одна треть)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);  // включает отображение стрелочки назад в тулбаре
        //getSupportActionBar().setHomeAsUpIndicator(R.mipmap.back_orig);  // добавляем картинку клавише назад в тулбаре
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#18450e'>Другое</font>"));


        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        WIDTH_DISPLAY = size.x;
        BUTON_HEIGHT = WIDTH_DISPLAY / HEIGHT_COEFFICIENT;


        buttons_generate(R.drawable.fresnel, "Расчет зоны Френеля", openFresnel);
        buttons_generate(R.drawable.radiovidimost, "Дальность радиогоризонта", openDistanceHorizont);
        buttons_generate(R.drawable.signal_zatuhanie, "Затухание сигнала в пространстве", openSignalLossDistance);
        buttons_generate(R.drawable.antenna, "КСВ -> дБ", openSwrToPower);
        buttons_generate(R.drawable.watts, "дБмВт -> Вт", openDecibelToWatt);
        buttons_generate(R.drawable.battery, "Таблица частот", openDecibelToWatt);
        buttons_generate(R.drawable.waves, "Длина волны по частоте", method_vita);

    }


    View.OnClickListener openFresnel = new View.OnClickListener() {  // калькулятор Френеля
        @Override
        public void onClick(View v) {

            startActivity(new Intent(MainActivity.this, FresnelZone.class));


        }
    };


    View.OnClickListener openDistanceHorizont = new View.OnClickListener() {  // обработка кнопки "Литература"
        @Override
        public void onClick(View v) {
            startActivity(new Intent(MainActivity.this, DistanceGorizont.class));
        }
    };


    View.OnClickListener openSignalLossDistance = new View.OnClickListener() {  // обработка кнопки "Литература"
        @Override
        public void onClick(View v) {

            startActivity(new Intent(MainActivity.this, SignalLossDistance.class));

        }
    };


    View.OnClickListener openSwrToPower = new View.OnClickListener() {  // обработка кнопки "Литература"
        @Override
        public void onClick(View v) {

            startActivity(new Intent(MainActivity.this, SwrToPower.class));

        }
    };

    // https://tilda.cc/page/?pageid=12216270&previewmode=yes


    View.OnClickListener openDecibelToWatt = new View.OnClickListener() {  // обработка кнопки "Литература"
        @Override
        public void onClick(View v) {
            startActivity(new Intent(MainActivity.this, DecibelToWatt.class));
        }
    };


    View.OnClickListener method_ethic = new View.OnClickListener() {  // обработка кнопки "Литература"
        @Override
        public void onClick(View v) {
            //       startActivity(new Intent(Other_menu_new.this, Ethic_firm.class));

        }
    };


    View.OnClickListener method_vita = new View.OnClickListener() {  // обработка кнопки "Литература"
        @Override
        public void onClick(View v) {
//            URL_obshee.open_in_apk = true;
//            URL_obshee.URL = ("https://justveg.ru/vita");
//            URL_obshee.activity_name = "Vita_news";
//            URL_obshee.setTitle("<font color='#18450e'>Новости ВИТА</font>");
//            startActivity(new Intent(Other_menu_new.this, URL_obshee.class));
        }
    };


    public void buttons_generate(int image, String name_button, View.OnClickListener method) {

        int LINE_PADDING = 20; // отступы от кнопки до разделительной линии (сверху и знизу)
        int TEXT_ZAGOLOVOK_SIZE = 20;  // размер текста заголовков
        int PADDING_TEXT_LEFT = 10; // отступ текста слева
        int PADDING_TEXT_RIGHT = 0; // отступ текста справа
        int PADDING_TEXT_TOP = 5; // отступ между заголовком и описательным текстом
        int PADDING_TEXT_BOTTOM = 10; // отступ между заголовком и описательным текстом
        float RADIUS = 0.2f; // радиус скругления углов кнопок

        LinearLayout layout = findViewById(R.id.lin_o);

        LinearLayout layoutButton = new LinearLayout(this); // создали LinearLayout в который будем помещать картинку и тексты
        LinearLayout.LayoutParams params_layoutButton = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, BUTON_HEIGHT); // максимальная ширина кнопки и ее высота которую мы выбираем сами
        layoutButton.setLayoutParams(params_layoutButton);
        //layoutButton.setBackgroundColor(getColor(R.color.text_color_primary_3));  // определили цвет для кнопки
        layoutButton.setBackground(getDrawable(R.drawable.ripple));  // определили цвет для кнопки

        ImageView imageView = new ImageView(this);  // создали ImageView в который помещаем картинку для кнопки
        LinearLayout.LayoutParams params_image = new LinearLayout.LayoutParams(WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        imageView.setLayoutParams(params_image);
        Drawable d = getDrawable(image);
        //d = new Rounded_corners().setEffect(d, RADIUS, layoutButton.getSolidColor()); // необходимо, чтобы места скругления имели тот же цвет, что и фон
        d = new Rounded_corners().setEffect(d, RADIUS, getColor(R.color.text_color_primary_3)); // необходимо, чтобы места скругления имели тот же цвет, что и фон
        imageView.setImageDrawable(d);
        imageView.setAdjustViewBounds(true);

        TextView textView_zagolovok = new TextView(this);
        textView_zagolovok.setPadding(PADDING_TEXT_LEFT, PADDING_TEXT_TOP, PADDING_TEXT_RIGHT, PADDING_TEXT_BOTTOM);
        textView_zagolovok.setText(name_button);
        textView_zagolovok.setTypeface(Typeface.DEFAULT_BOLD);
        textView_zagolovok.setTextColor(getColor(R.color.Black));
        textView_zagolovok.setTextSize(TEXT_ZAGOLOVOK_SIZE);
        textView_zagolovok.setGravity(Gravity.CENTER_VERTICAL);
        textView_zagolovok.setPadding(25, 0, 0, 0);

        LinearLayout.LayoutParams params_for_text1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, MATCH_PARENT);
        textView_zagolovok.setLayoutParams(params_for_text1);

        layoutButton.addView(imageView);
        layoutButton.addView(textView_zagolovok);
        layoutButton.setOnClickListener(method);

        View line = new View(this);
        LinearLayout.LayoutParams params_for_line = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 3);
        params_for_line.setMargins(0, LINE_PADDING, 0, LINE_PADDING);
        line.setLayoutParams(params_for_line);
        line.setBackgroundColor(Color.parseColor("#B3B3B3"));

        layout.addView(layoutButton);
        layout.addView(line);

    }
}
