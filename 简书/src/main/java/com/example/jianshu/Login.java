package com.example.jianshu;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by mym_0314 on 2016/3/21.
 */
public class Login extends BaseActivity implements TextWatcher, View.OnClickListener, View.OnFocusChangeListener {

    private EditText edt_account;
    private EditText edt_pwd;
    private ImageView cancel_account;
    private ImageView cancel_pwd;
    private String account;
    private String pwd;

    private String bef_account;
    private String bef_pwd;
    private Button submit;
    private TextView close;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
        initListener();
    }

    private void initListener() {
        edt_account.addTextChangedListener(this);
        edt_pwd.addTextChangedListener(this);
        edt_account.setOnFocusChangeListener(this);
        edt_pwd.setOnFocusChangeListener(this);

        cancel_account.setOnClickListener(this);
        cancel_pwd.setOnClickListener(this);
        close.setOnClickListener(this);
        submit.setOnClickListener(this);

    }

    private void initData() {

    }

    private void initView() {

        setContentView(R.layout.main);
        edt_account = (EditText) findViewById(R.id.edt_account);
        edt_pwd = (EditText) findViewById(R.id.edt_pwd);
        cancel_account = (ImageView) findViewById(R.id.cancel_account);
        cancel_pwd = (ImageView) findViewById(R.id.cancel_pwd);
        submit = (Button) findViewById(R.id.btn_submit);
        close = (TextView) findViewById(R.id.close);

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        if (edt_account.hasFocus()) {
            bef_account = s.toString();
        }
        if (edt_pwd.hasFocus()) {
            bef_pwd = s.toString();
        }
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        getEditText();
        if (account.length() > 0 && edt_account.hasFocus()) {
            cancel_account.setVisibility(View.VISIBLE);
            if (account.length() > 11) {
                edt_account.setText(bef_account);
                edt_account.setSelection(bef_account.length() - 1);
            }
        } else {
            cancel_account.setVisibility(View.GONE);
        }
        if (pwd.length() > 0 && edt_pwd.hasFocus()) {
            cancel_pwd.setVisibility(View.VISIBLE);
            if (account.length() > 0)
                submit.setEnabled(true);
            else
                submit.setEnabled(false);
            if (pwd.length() > 11) {
                edt_pwd.setText(bef_pwd);
                edt_pwd.setSelection(bef_pwd.length() - 1);
            }
        } else {
            cancel_pwd.setVisibility(View.GONE);
        }


    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(Login.this, ShowActivity.class);
        switch (v.getId()) {
            case R.id.btn_submit:
                //TODO
                // startActivity(intent);
                finish();
                break;
            case R.id.cancel_account:
                edt_account.setText("");
                break;
            case R.id.cancel_pwd:
                edt_pwd.setText("");
                break;
            case R.id.close:
//                startActivity(intent);
                finish();
                break;
        }
    }

    private void getEditText() {
        account = edt_account.getText().toString();
        pwd = edt_pwd.getText().toString();
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        getEditText();
        switch (v.getId()) {
            case R.id.edt_account:
                if (hasFocus && account.length() > 0)
                    cancel_account.setVisibility(View.VISIBLE);
                else
                    cancel_account.setVisibility(View.GONE);
                break;
            case R.id.edt_pwd:
                if (hasFocus && pwd.length() > 0)
                    cancel_pwd.setVisibility(View.VISIBLE);
                else
                    cancel_pwd.setVisibility(View.GONE);
                break;
        }
    }
}
