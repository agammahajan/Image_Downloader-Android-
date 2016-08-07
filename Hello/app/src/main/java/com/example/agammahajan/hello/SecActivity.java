package com.example.agammahajan.hello;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;


public class SecActivity extends AppCompatActivity {

    EditText mEdit;
    private String downloadUrl;
    private ImageView downloadedImg;
    private ResponseReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sec);
        Button but2 = (Button) findViewById(R.id.but2);
        //downloadedImg = (ImageView) findViewById(R.id.imageView);
        but2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                mEdit   = (EditText)findViewById(R.id.editText);
                downloadUrl=mEdit.getText().toString();
                Intent serviceIntent = new Intent(getApplicationContext(),BroadcastService.class);
                serviceIntent.putExtra("key", downloadUrl);
                serviceIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                System.out.println("SecondActivity");
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
            Intent send = new Intent(SecActivity.this,ThirdActivity.class);
            send.putExtra("Im", byteArray);
            System.out.println("Catched");
            startActivity(send);

        }
    }

//    private class ImageDownloader extends AsyncTask<String, Void, Bitmap>{
//
//        @Override
//        protected Bitmap doInBackground(String... param) {
//            return downloadBitmap(param[0]);
//        }
//
//        @Override
//        protected void onPostExecute(Bitmap result) {
//            Log.i("Async-Example", "onPostExecute Called");
//            downloadedImg.setImageBitmap(result);
//
//
//        }
//
//        private Bitmap downloadBitmap(String url) {
//            HttpURLConnection urlConnection = null;
//            try {
//                URL uri = new URL(url);
//                urlConnection = (HttpURLConnection) uri.openConnection();
//
////                int statusCode = urlConnection.getResponseCode();
////                if (statusCode != HttpStatus.SC_OK) {
////                    return null;
////                }
//
//                InputStream inputStream = urlConnection.getInputStream();
//                if (inputStream != null) {
//
//                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
//                    return bitmap;
//                }
//            } catch (Exception e) {
//                Log.d("URLCONNECTIONERROR", e.toString());
//                if (urlConnection != null) {
//                    urlConnection.disconnect();
//                }
//                Log.w("ImageDownloader", "Error downloading image from " + url);
//            } finally {
//                if (urlConnection != null) {
//                    urlConnection.disconnect();
//
//                }
//            }
//            return null;
//        }
//    }
}


