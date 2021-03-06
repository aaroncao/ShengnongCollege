package com.education.shengnongcollege.api;

/**
 * Created by wuweixiang on 18/6/1.
 */

public class UserApiPath {
    //用户注册
    public static final String REGISTER_PATH = "api/user/register";
    //用户登录
    public static final String LOGIN_PATH = "/api/user/login";
    //获取获取云通信票据（签名）
    public static final String GET_USER_SIGN_PATH = "api/common/getusersig";
    //发送短信验证码
    public static final String SEND_SMS_VERIFY_CODE_PATH = "api/user/sendsmsverificationcode";
    public static final String FEEDBACK_PATH = "api/user/feedback";
}
