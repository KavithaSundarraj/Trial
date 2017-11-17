package com.example.shashidhar.trial;

import android.graphics.Bitmap;
import android.widget.ImageView;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by tmp-sda-1124 on 11/16/17.
 */
public class URLToImageTest {
    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test (expected = NullPointerException.class)
    public void doInBackground() throws Exception {
        String pathToFile=null;
        assertTrue("URL is empty",pathToFile.isEmpty());
    }

    @Test
    public void onPostExecute() throws Exception {

    }

}