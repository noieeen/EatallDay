package com.example.eatall;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.emergencyphone.R;

import java.io.IOException;
import java.io.InputStream;

public class ShowActivity extends AppCompatActivity {
    private TextView title_s;
    private TextView cal_s;
    private ImageView image_s;
    private TextView time_s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String cal = intent.getStringExtra("cal");
        String time = intent.getStringExtra("time");
        String image = intent.getStringExtra("image");

        image_s = findViewById(R.id.pic_show);
        title_s = findViewById(R.id.food_show);
        cal_s = findViewById(R.id.cal_show);
        time_s = findViewById(R.id.time_show);

        title_s.setText(title);  //todo โชว์ หัวเรื่อง
        cal_s.setText(cal); // todo โชว์ detail
        time_s.setText(time);

        AssetManager am = getAssets();
        try {
            InputStream is = am.open(image);//todo เปิดไฟล์ใน asserts มาอ่าน
            Drawable drawable = Drawable.createFromStream(is," ");
            image_s.setImageDrawable(drawable); // todo โชว์ รูป
        } catch (IOException e) {
            e.printStackTrace();
        }

        Button cacel = findViewById(R.id.Cacel_button);
        cacel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

}
