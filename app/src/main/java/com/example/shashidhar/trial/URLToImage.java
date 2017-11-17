package com.example.shashidhar.trial;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;

/**
 * Created by tmp-sda-1124 on 11/8/17.
 */

public class URLToImage extends AsyncTask<String, Void, Bitmap> {
    ImageView bmImage;
    String pathToFile;
    public URLToImage(ImageView bmImage) {
        this.bmImage = bmImage;
    }

    protected Bitmap doInBackground(String... urls) {
        pathToFile = urls[0];
        Bitmap bitmap = null;
        try {
            InputStream in = new java.net.URL(pathToFile).openStream();
            bitmap = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        return bitmap;
    }
    protected void onPostExecute(Bitmap result) {
        bmImage.setImageBitmap(result);
    }

}
