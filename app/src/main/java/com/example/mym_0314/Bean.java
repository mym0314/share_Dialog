package com.example.mym_0314;

/**
 * Created by mym_0314 on 2016/3/24.
 */
public class Bean {

    private String title;
    private String content;

    private String time;

    private String hot;

    public Bean(String title, String content, String time, String hot) {
        this.title = title;
        this.content = content;
        this.time = time;
        this.hot = hot;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getHot() {
        return hot;
    }

    public void setHot(String hot) {
        this.hot = hot;
    }


}
