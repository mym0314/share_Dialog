package com.example.myapplication;

import com.example.base.BaseActivity;
import com.example.bean.User;
import com.example.db.DBUtils;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest extends BaseActivity{
    @Test
    public void addition_isCorrect() throws Exception {
       User user = new User();
        user.setName("张三");
        user.setAge(12);
        user.setDate(new Date());
        user.setSex("男");
        DBUtils dbUtils = new DBUtils(this);
        dbUtils.saveData("user",user);

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }
}