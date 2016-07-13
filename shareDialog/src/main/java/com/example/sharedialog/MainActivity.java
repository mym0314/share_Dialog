package com.example.sharedialog;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener {
    private Button share;
    private ShareView  shareView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        setContentView(R.layout.activity_main);
        share = (Button) findViewById(R.id.btn_share);
        shareView = new ShareView(MainActivity.this);
        shareView.setActivity(this);
        share.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        shareView.show();
    }
}
