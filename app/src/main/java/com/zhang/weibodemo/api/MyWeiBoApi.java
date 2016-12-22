package com.zhang.weibodemo.api;


import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;

import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.net.RequestListener;
import com.sina.weibo.sdk.net.WeiboParameters;
import com.sina.weibo.sdk.openapi.AbsOpenAPI;
import com.zhang.weibodemo.constants.MyConstants;
import com.zhang.weibodemo.constants.URLs;

/**
 * Created by Mr.Z on 2016/10/28 0028.
 */

public class MyWeiBoApi extends AbsOpenAPI {

    private Handler mainLooperHandler = new Handler(Looper.getMainLooper());

    /**
     * 构造函数，使用各个 API 接口提供的服务前必须先获取 Token。
     *
     * @param context
     * @param appKey
     * @param accessToken
     */
    public MyWeiBoApi(Context context, String appKey, Oauth2AccessToken accessToken) {
        super(context, appKey, accessToken);
    }

    public void requestInMainLooper(String url, WeiboParameters params, String httpMethod, final RequestListener listener) {
        requestAsync(url, params, httpMethod, new RequestListener() {
            @Override
            public void onComplete(final String s) {
                mainLooperHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        listener.onComplete(s);
                    }
                });
            }

            @Override
            public void onWeiboException(final WeiboException e) {
                mainLooperHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        listener.onWeiboException(e);
                    }
                });
            }
        });
    }

    @Override
    protected void requestAsync(String url, WeiboParameters params, String httpMethod, RequestListener listener) {
        super.requestAsync(url, params, httpMethod, listener);
    }

    /**
     * 获取用户信息(uid和screen_name二选一)
     *
     * @param uid         根据用户ID获取用户信息
     * @param screen_name 需要查询的用户昵称。
     * @param listener
     */
    public void usersShow(String uid, String screen_name, RequestListener listener) {
        WeiboParameters params = new WeiboParameters(MyConstants.APP_KEY);
        if (!TextUtils.isEmpty(uid)) {
            params.add("uid", uid);
        } else if (!TextUtils.isEmpty(screen_name)) {
            params.add("screen_name", screen_name);
        }
        requestInMainLooper(URLs.usersShow, params, HTTPMETHOD_GET, listener);
    }

    /**
     * 获取某个用户最新发表的微博列表(uid和screen_name二选一)
     *
     * @param uid         需要查询的用户ID。
     * @param screen_name 需要查询的用户昵称。
     * @param page        返回结果的页码。(单页返回的记录条数，默认为20。)
     * @param listener
     */
    public void statusesUser_timeline(long uid, String screen_name, long page, RequestListener listener) {
        WeiboParameters params = new WeiboParameters(MyConstants.APP_KEY);
        if (uid > 0) {
            params.add("uid", uid);
        } else if (!TextUtils.isEmpty(screen_name)) {
            params.add("screen_name", screen_name);
        }
        params.add("page", page);
        requestInMainLooper(URLs.statusesUser_timeline, params, HTTPMETHOD_GET, listener);
    }

    /**
     * 获取当前登录用户及其所关注用户的最新微博
     *
     * @param page     返回结果的页码。(单页返回的记录条数，默认为20。)
     * @param listener
     */
    public void statusesHome_timeline(long page, RequestListener listener) {
        WeiboParameters parameters = new WeiboParameters(MyConstants.APP_KEY);
        parameters.add("page", page);
        requestInMainLooper(URLs.statusesHome_timeline, parameters, HTTPMETHOD_GET, listener);
    }

    /**
     * 根据微博ID返回某条微博的评论列表
     *
     * @param id       需要查询的微博ID。
     * @param page     返回结果的页码。(单页返回的记录条数，默认为50。)
     * @param listener
     */
    public void commentsShow(long id, long page, RequestListener listener) {
        WeiboParameters params = new WeiboParameters(MyConstants.APP_KEY);
        params.add("id", id);
        params.add("page", page);
        requestInMainLooper(URLs.commentsShow, params, HTTPMETHOD_GET, listener);
    }

    /**
     * 对一条微博进行评论
     *
     * @param id       需要评论的微博ID。
     * @param comment  评论内容
     * @param listener
     */
    public void commentsCreate(long id, String comment, RequestListener listener) {
        WeiboParameters params = new WeiboParameters(MyConstants.APP_KEY);
        params.add("id", id);
        params.add("comment", comment);
        requestInMainLooper(URLs.commentsCreate, params, HTTPMETHOD_POST, listener);
    }

    /**
     * 发布或转发一条微博
     *
     * @param status           要发布的微博文本内容。
     * @param imgFilePath      要上传的图片文件路径(为空则代表发布无图微博)。
     * @param retweetedStatusId 要转发的微博ID(<=0时为原创微博)。
     * @param listener
     */
    public void statusesSend(String status, String imgFilePath, long retweetedStatusId, RequestListener listener) {
        String url;

        WeiboParameters params = new WeiboParameters(MyConstants.APP_KEY);
        params.add("status", status);
        if (retweetedStatusId > 0) {
            url = URLs.statusesRepost;
            params.add("id", retweetedStatusId);
        } else if (!TextUtils.isEmpty(imgFilePath)) {
            params.add("pic", imgFilePath);
            url = URLs.statusesUpload;
        } else {
            url = URLs.statusesUpdate;
        }
        requestInMainLooper(url, params, HTTPMETHOD_POST, listener);
    }
}
