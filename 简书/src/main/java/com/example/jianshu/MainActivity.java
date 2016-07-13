package com.example.jianshu;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

public class MainActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent_login = new Intent(MainActivity.this, Login.class);
                Intent intent_show = new Intent(MainActivity.this, ShowActivity.class);
                Intent[] intent = new Intent[]{ intent_show,intent_login};
                startActivities(intent);
                finish();
            }
        }, 2000);
    }
}
