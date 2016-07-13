package com.example.sharedialog;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WeiboAuthListener;
import com.sina.weibo.sdk.auth.sso.SsoHandler;
import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.net.RequestListener;
import com.sina.weibo.sdk.openapi.legacy.StatusesAPI;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import share.AccessTokenKeeper;
import share.Constance;

/**
 * Created by mym_0314 on 2016/4/27.
 */
public class ShareView extends ProgressDialog implements View.OnClickListener {


    public ShareView(Context context) {
        super(context);
        //设置在外点击关闭对话框
        setCanceledOnTouchOutside(true);
    }

    private Button cancel;
    private ImageView mWeiXin, mWeiBo, mFriends;
    private IWXAPI iWX;


    private AuthInfo mAuthInfo;
    private SsoHandler mSsoHandler;
    private Oauth2AccessToken mAccessToken;
    private StatusesAPI mStatusesAPI;

    private Activity act;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initClick();
        initWeiXin();
        initWeiBo();
    }

    public void setActivity(Activity act) {
        this.act = act;
    }

    private void initWeiXin() {
        iWX = WXAPIFactory.createWXAPI(getContext(), Constance.WX_KEY);
        iWX.registerApp(Constance.WX_KEY);
    }

    private void initWeiBo() {
        mAuthInfo = new AuthInfo(getContext(), Constance.WB_APPKEY, Constance.WB_REDIRECT_URL, Constance.SCOPE);
        mSsoHandler = new SsoHandler(act, mAuthInfo);
        mAccessToken = AccessTokenKeeper.readAccessToken(getContext());
    }

    private void initClick() {
        cancel.setOnClickListener(this);
        mWeiXin.setOnClickListener(this);
        mWeiBo.setOnClickListener(this);
        mFriends.setOnClickListener(this);
    }

    private void initView() {
        setContentView(R.layout.share_view);
        cancel = (Button) findViewById(R.id.share_cancel);
        mWeiXin = (ImageView) findViewById(R.id.share_weixin);
        mWeiBo = (ImageView) findViewById(R.id.share_weibo);
        mFriends = (ImageView) findViewById(R.id.share_friends);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.share_cancel:
                dismiss();
                break;
            case R.id.share_weixin:

                break;
            case R.id.share_weibo:
                dismiss();
                if (mAccessToken.isSessionValid()) {
                    mStatusesAPI = new StatusesAPI(getContext(), Constance.WB_APPKEY, mAccessToken);
                    mStatusesAPI.update("我的测试", null, null,request );
                    return;
                }
                mSsoHandler.authorize(new WeiboListener());
                break;
            case R.id.share_friends:
                break;
        }
    }
    private class WeiboListener implements WeiboAuthListener {

        @Override
        public void onComplete(Bundle bundle) {
            mAccessToken = Oauth2AccessToken.parseAccessToken(bundle);
            if(mAccessToken.isSessionValid()){
                mStatusesAPI = new StatusesAPI(getContext(),Constance.WB_APPKEY,mAccessToken);
                mStatusesAPI.update("授权测试",null,null,request);
            }
        }

        @Override
        public void onWeiboException(WeiboException e) {

        }

        @Override
        public void onCancel() {

        }
    }
    private RequestListener request = new RequestListener() {
        @Override
        public void onComplete(String s) {
            if (s.startsWith("{\"created_at\""))
                Toast.makeText(getContext(), "发送成功", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onWeiboException(WeiboException e) {
        }
    };
}
