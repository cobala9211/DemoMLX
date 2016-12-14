package com.example.dnp.demoxml;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.dnp.demoxml.dom.DomActivity;
import com.example.dnp.demoxml.jsoup.JSoupActivity;
import com.example.dnp.demoxml.retrofit.RetrofitActivity;
import com.example.dnp.demoxml.sax.SaxActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnDom = (Button) findViewById(R.id.btnDom);
        btnDom.setOnClickListener(this);
        Button btnSax = (Button) findViewById(R.id.btnSax);
        btnSax.setOnClickListener(this);
        Button btnJSoup = (Button) findViewById(R.id.btnJSoup);
        btnJSoup.setOnClickListener(this);
        Button btnRetrofit = (Button) findViewById(R.id.btnRetrofit);
        btnRetrofit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnDom:
                initActivity(DomActivity.class);
                break;
            case R.id.btnSax:
                initActivity(SaxActivity.class);
                break;
            case R.id.btnJSoup:
                initActivity(JSoupActivity.class);
                break;
            case R.id.btnRetrofit:
                initActivity(RetrofitActivity.class);
                break;
            default:
                break;
        }
    }

    private void initActivity(Class className) {
        Intent intent = new Intent(MainActivity.this, className);
        this.startActivity(intent);
    }
}
