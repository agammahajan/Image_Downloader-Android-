package com.example.agammahajan.test;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    EditText mEdit;
    private String downloadUrl;
    private ResponseReceiver receiver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                mEdit   = (EditText)findViewById(R.id.editText);
                downloadUrl=mEdit.getText().toString();
                Intent serviceIntent = new Intent(getApplicationContext(),BroadcastService.class);
                serviceIntent.putExtra("key", downloadUrl);
                serviceIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                startService(serviceIntent);
                //new ImageDownloader().execute(downloadUrl);
            }
        });


                IntentFilter filter = new IntentFilter(ResponseReceiver.ACTION_RESP);
                filter.addCategory(Intent.CATEGORY_DEFAULT);
                receiver = new ResponseReceiver();
                registerReceiver(receiver, filter);
    }


    public class ResponseReceiver extends BroadcastReceiver {
        public static final String ACTION_RESP =
                "Hello";

        @Override
        public void onReceive(Context context, Intent intent) {
            byte[] byteArray = intent.getByteArrayExtra("Image");
            System.out.println(byteArray.length);
            Intent send = new Intent(MainActivity.this,SecondActivity.class);
            send.putExtra("Im", byteArray);
            System.out.println("Catched");
            startActivity(send);

        }
    }
}
