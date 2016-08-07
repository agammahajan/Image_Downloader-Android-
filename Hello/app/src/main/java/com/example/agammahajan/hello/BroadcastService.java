package com.example.agammahajan.hello;

import android.app.IntentService;
import android.content.Intent;

import android.graphics.Bitmap;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;

import java.net.HttpURLConnection;

import android.util.Log;
import android.graphics.BitmapFactory;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p/>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class BroadcastService extends IntentService {
    // TODO: Rename actions, choose action names that describe tasks that this


    public BroadcastService() {
        super("BroadcastService");
    }




    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            System.out.println("ServiceActivity");
            String downloadUrl = intent.getExtras().getString("key");

            Bitmap bmp=downloadBitmap(downloadUrl);

            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            try{
                bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
            }
            catch(NullPointerException e){
                System.out.println("NullPointerException");
            }

            byte[] byteArray = stream.toByteArray();
            System.out.println(byteArray.length);
            Intent broadcastIntent = new Intent();
            broadcastIntent.setAction(SecActivity.ResponseReceiver.ACTION_RESP);
            broadcastIntent.addCategory(Intent.CATEGORY_DEFAULT);
            broadcastIntent.putExtra("Image", byteArray);
            sendBroadcast(broadcastIntent);
            }
            else {
            System.out.println("Error");
        }


    }

    private Bitmap downloadBitmap(String url) {
        HttpURLConnection urlConnection = null;
        try {
            URL uri = new URL(url);
            urlConnection = (HttpURLConnection) uri.openConnection();

//                int statusCode = urlConnection.getResponseCode();
//                if (statusCode != HttpStatus.SC_OK) {
//                    return null;
//                }

            InputStream inputStream = urlConnection.getInputStream();
            if (inputStream != null) {

                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                return bitmap;
            }
        } catch (Exception e) {
            Log.d("URLCONNECTIONERROR", e.toString());
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            Log.w("ImageDownloader", "Error downloading image from " + url);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();

            }
        }
        return null;
    }

}
