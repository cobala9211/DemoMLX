package com.example.dnp.demoxml.jsoup;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.dnp.demoxml.R;

/**
 * Created by DNP on 12/14/2016.
 */

public class JSoupActivity extends AppCompatActivity {
    private static final String URL = "http://600tuvungtoeic.com/";
    private static final String TAG = "JSoupActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jsoup);
        new XmlHandlerJSoup(this).execute(URL);
        Log.d(TAG, "onCreate: ");
    }
}
