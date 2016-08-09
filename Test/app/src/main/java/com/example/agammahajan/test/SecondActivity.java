package com.example.agammahajan.test;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class SecondActivity extends AppCompatActivity {

    private ImageView downloadedImg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        byte[] byteArray =  getIntent().getByteArrayExtra("Im");
        System.out.println(byteArray.length);
        Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        System.out.println("Hello");
        downloadedImg = (ImageView) findViewById(R.id.imageView);
        downloadedImg.setImageBitmap(bmp);

    }
}
