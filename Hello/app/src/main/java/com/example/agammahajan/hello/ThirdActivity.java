package com.example.agammahajan.hello;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class ThirdActivity extends AppCompatActivity {


    private ImageView downloadedImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        byte[] byteArray =  getIntent().getByteArrayExtra("Im");
        System.out.println(byteArray.length);
        Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        downloadedImg = (ImageView) findViewById(R.id.imageView);
        downloadedImg.setImageBitmap(bmp);
    }



}
