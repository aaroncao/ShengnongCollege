package com.education.shengnongcollege.videopublish.server;

/**
 * Created by vinsonswang on 2018/3/26.
 */

public interface PublishSigListener {
    void onSuccess(String signatureStr);

    void onFail(int errCode);
}
