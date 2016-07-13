package com.example.volly;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.VolleyError;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initHttp();
    }

    private void initHttp() {
        VollyRequest.getRequest("", "Get", new VollyInterface(MainActivity.this) {
            @Override
            public void onMySuccess(String result) {
                Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onMyError(VolleyError volleyError) {
                Toast.makeText(MainActivity.this, volleyError.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
