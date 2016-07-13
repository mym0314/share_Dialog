package com.example.testdemo;

/**
 * Created by mym_0314 on 2016/5/18.
 * "_id": "1",
 * "imgpath": "top1.jpg",
 * "goodsId": null,
 * "UserId": null,
 * "NewsId": null,
 * "advertId": "1",
 * "shopId": null
 */
public class Bean {
    private String _id;
    private String imgpath;
    private String advertId;

    public Bean(String _id, String imgpath, String advertId) {
        this._id = _id;
        this.imgpath = imgpath;
        this.advertId = advertId;
    }

    @Override
    public String toString() {
        return "Bean{" +
                "_id='" + _id + '\'' +
                ", imgpath='" + imgpath + '\'' +
                ", advertId='" + advertId + '\'' +
                '}';
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getImgpath() {
        return imgpath;
    }

    public void setImgpath(String imgpath) {
        this.imgpath = imgpath;
    }

    public String getAdvertId() {
        return advertId;
    }

    public void setAdvertId(String advertId) {
        this.advertId = advertId;
    }
}
