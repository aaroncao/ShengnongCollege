package com.education.shengnongcollege.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.education.shengnongcollege.BaseTopActivity;
import com.education.shengnongcollege.MainActivity;
import com.education.shengnongcollege.R;
import com.education.shengnongcollege.api.LiveBroadcastApiManager;
import com.education.shengnongcollege.model.GetVideoListRespData;
import com.education.shengnongcollege.model.ListRespObj;
import com.education.shengnongcollege.network.listener.GWResponseListener;
import com.education.shengnongcollege.network.model.ListResponseResult;
import com.education.shengnongcollege.play.LivePlayerActivity;
import com.education.shengnongcollege.play.VodListPlayerActivity;
import com.education.shengnongcollege.push.LivePublisherActivity;
import com.education.shengnongcollege.utils.BaseUtil;
import com.tencent.rtmp.TXLiveBase;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wuweixiang on 18/6/3.
 */

public class TestActivity extends BaseTopActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        String sdkver = TXLiveBase.getSDKVersionStr();
        Log.d("liteavsdk", "liteav sdk version is : " + sdkver);

        findViewById(R.id.zhibo_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TestActivity.this, LivePlayerActivity.class));
            }
        });

        findViewById(R.id.dianbo_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TestActivity.this, VodListPlayerActivity.class));
            }
        });

        findViewById(R.id.lubo_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TestActivity.this, LivePublisherActivity.class));
            }
        });

        findViewById(R.id.get_video_list_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText et = findViewById(R.id.category_id_et);
                String id = et.getText().toString();
                LiveBroadcastApiManager.getVideoList(new GWResponseListener() {
                    @Override
                    public void successResult(Serializable result, String path, int requestCode, int resultCode) {
                        ListResponseResult<GetVideoListRespData, ListRespObj> responseResult = (ListResponseResult<GetVideoListRespData, ListRespObj>) result;
                        List<GetVideoListRespData> data = responseResult.getData();
                        if (data != null && data.size() > 1)
                            LiveBroadcastApiManager.getVideoDetail(new GWResponseListener() {
                                @Override
                                public void successResult(Serializable result, String path, int requestCode, int resultCode) {
                                    Toast.makeText(getApplicationContext(), "视频详情获取成功", Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void errorResult(Serializable result, String path, int requestCode, int resultCode) {
                                    Toast.makeText(getApplicationContext(), "视频详情获取失败", Toast.LENGTH_SHORT).show();
                                }
                            }, "1");
                        Toast.makeText(getApplicationContext(), "视频列表获取成功", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void errorResult(Serializable result, String path, int requestCode, int resultCode) {
                        Toast.makeText(getApplicationContext(), "视频列表获取失败", Toast.LENGTH_SHORT).show();
                    }
                }, id, null, 0, 10);
            }
        });

        findViewById(R.id.hello_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "点击hello", Toast.LENGTH_SHORT).show();

                LiveBroadcastApiManager.getVideoList(new GWResponseListener() {
                    @Override
                    public void successResult(Serializable result, String path, int requestCode, int resultCode) {
                        ListResponseResult<GetVideoListRespData, ListRespObj> responseResult = (ListResponseResult<GetVideoListRespData, ListRespObj>) result;
                        List<GetVideoListRespData> data = responseResult.getData();
                        if (data != null && data.size() > 1)
                            LiveBroadcastApiManager.getVideoDetail(new GWResponseListener() {
                                @Override
                                public void successResult(Serializable result, String path, int requestCode, int resultCode) {
                                    Toast.makeText(getApplicationContext(), "视频详情获取成功", Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void errorResult(Serializable result, String path, int requestCode, int resultCode) {
                                    Toast.makeText(getApplicationContext(), "视频详情获取失败", Toast.LENGTH_SHORT).show();
                                }
                            }, "1");
                        Toast.makeText(getApplicationContext(), "视频列表获取成功", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void errorResult(Serializable result, String path, int requestCode, int resultCode) {
                        Toast.makeText(getApplicationContext(), "视频列表获取失败", Toast.LENGTH_SHORT).show();
                    }
                }, null, null, 0, 10);

//                LiveBroadcastApiManager.getCategoryList(new GWResponseListener() {
//                    @Override
//                    public void successResult(Serializable result, String path, int requestCode, int resultCode) {
//                        ListResponseResult<GetCategoryListRespData, ListRespObj> responseResult = (ListResponseResult<GetCategoryListRespData, ListRespObj>) result;
//                        List<GetCategoryListRespData> data = responseResult.getData();
//                        GetCategoryListRespObj obj = responseResult.getObj();
//                        Toast.makeText(getApplicationContext(), "分类列表获取成功", Toast.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    public void errorResult(Serializable result, String path, int requestCode, int resultCode) {
//                        Toast.makeText(getApplicationContext(), "分类列表获取失败", Toast.LENGTH_SHORT).show();
//                    }
//                }, 1, 10);

//                UserApiManager.getUserSign(new GWResponseListener() {
//                    @Override
//                    public void successResult(Serializable result, String path, int requestCode, int resultCode) {
//                        ResponseResult<String, RespObjBase> responseResult = (ResponseResult<String, RespObjBase>) result;
//                        String userSign = responseResult.getData();
//                        Toast.makeText(getApplicationContext(), "云通信票据获取成功=" + userSign, Toast.LENGTH_SHORT).show();
//                        UserApiManager.registerUser(new GWResponseListener() {
//                            @Override
//                            public void successResult(Serializable result, String path, int requestCode, int resultCode) {
//
//                            }
//
//                            @Override
//                            public void errorResult(Serializable result, String path, int requestCode, int resultCode) {
//
//                            }
//                        }, "13606645039", "13606645039", "780377", "wu19840710", userSign);
//                    }
//
//                    @Override
//                    public void errorResult(Serializable result, String path, int requestCode, int resultCode) {
//                        Toast.makeText(getApplicationContext(), "云通信票据获取失败", Toast.LENGTH_SHORT).show();
//                    }
//                }, "13606645039");

//                UserApiManager.sendSmsVerfyCode(new GWResponseListener() {
//                    @Override
//                    public void successResult(Serializable result, String path, int requestCode, int resultCode) {
//                        ResponseResult<String, RespObjBase> responseResult = (ResponseResult<String, RespObjBase>) result;
//                        Toast.makeText(getApplicationContext(), "发送验证码成功=" + responseResult.getData(), Toast.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    public void errorResult(Serializable result, String path, int requestCode, int resultCode) {
//                        Toast.makeText(getApplicationContext(), "发送验证码失败", Toast.LENGTH_SHORT).show();
//                    }
//                }, "13606645039");

//                LiveBroadcastApiManager.getPushFlowPlayUrl(new GWResponseListener() {
//                    @Override
//                    public void successResult(Serializable result, String path, int requestCode, int resultCode) {
//
//                    }
//
//                    @Override
//                    public void errorResult(Serializable result, String path, int requestCode, int resultCode) {
//
//                    }
//                }, BaseUtil.UserId, "", "");


            }
        });
    }
}