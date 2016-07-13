package com.example.bean;

import java.io.Serializable;

/**
 * Created by mym_0314 on 2016/5/24.
 */
public class ContentBean implements Serializable {

    private String content;
    private int type;

    public ContentBean(String content, int type) {
        this.content = content;
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
