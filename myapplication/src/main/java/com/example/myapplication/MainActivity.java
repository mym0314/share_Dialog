package com.example.myapplication;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Window;

public class MainActivity extends Activity {
    private Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        handler.postDelayed(runnable, 2000);

    }
    private Runnable  runnable  = new Runnable() {
        @Override
        public void run() {
            Intent intent = new Intent(MainActivity.this, CenterActivity.class);
            startActivity(intent);
            finish();
        }
    };


}
