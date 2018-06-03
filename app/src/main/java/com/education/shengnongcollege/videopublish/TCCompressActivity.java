package com.education.shengnongcollege.videopublish;//package com.education.shengnongcollege.videopublish;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.support.v4.app.FragmentActivity;
//import android.text.TextUtils;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.LinearLayout;
//import android.widget.RadioButton;
//import android.widget.RadioGroup;
//import android.widget.Toast;
//
//import com.tencent.liteav.basic.log.TXCLog;
//import com.tencent.liteav.demo.R;
//import com.tencent.liteav.demo.common.utils.TCConstants;
//import com.tencent.liteav.demo.common.widget.VideoWorkProgressFragment;
//import com.tencent.liteav.demo.shortvideo.editor.utils.TCEditerUtil;
//import com.tencent.ugc.TXVideoEditConstants;
//import com.tencent.ugc.TXVideoEditer;
//import com.tencent.ugc.TXVideoInfoReader;
//
///**
// * Created by vinsonswang on 2018/3/27.
// */
//
//public class TCCompressActivity extends FragmentActivity {
//    private final String TAG = "TCCompressActivity";
//    private TXVideoEditer mTXVideoEditer;
//    private LinearLayout llBack;
//    private RadioGroup rgVideoResolution;
//    private RadioButton rbVideoNotCompress, rbVideoResolution360p, rbVideoResolution480p, rbVideoResolution540p, rbVideoResolution720p;
//    private EditText mEtCompressBitrate;
//    private Button mBtnStartCompress;
//    private int mVideoResolution;
//    private String mInputPath, mOutputPath;
//    private TXVideoEditer.TXVideoGenerateListener mTXVideoGenerateListener;
//    private VideoWorkProgressFragment mWorkLoadingProgress; // 生成视频的等待框
//    private TXVideoEditConstants.TXVideoInfo mTXVideoInfo;
//    private int mBiteRate; // 码率
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_compress);
//
//        initView();
//
//        initData();
//    }
//
//    private void initData() {
//        mTXVideoEditer = new TXVideoEditer(getApplicationContext());
//        mVideoResolution = -1;
//        mInputPath = getIntent().getStringExtra(TCConstants.VIDEO_EDITER_PATH);
//        mOutputPath = TCEditerUtil.generateVideoPath();
//        mTXVideoEditer.setVideoPath(mInputPath);
//
//        mTXVideoInfo = TXVideoInfoReader.getInstance().getVideoFileInfo(mInputPath);
//
//        initListener();
//    }
//
//    private void initListener() {
//        mTXVideoGenerateListener = new TXVideoEditer.TXVideoGenerateListener() {
//            @Override
//            public void onGenerateProgress(final float progress) {
//                TXCLog.i(TAG, "onGenerateProgress = " + progress);
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        mWorkLoadingProgress.setProgress((int) (progress * 100));
//                    }
//                });
//            }
//
//            @Override
//            public void onGenerateComplete(final TXVideoEditConstants.TXGenerateResult result) {
//                TXCLog.i(TAG, "onGenerateComplete result retCode = " + result.retCode);
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        if (mWorkLoadingProgress != null && mWorkLoadingProgress.isAdded()) {
//                            mWorkLoadingProgress.dismiss();
//                        }
//                        if (result.retCode == TXVideoEditConstants.GENERATE_RESULT_OK) {
//                            // 生成成功
//                            startPublishActivity(mOutputPath);
//                        } else {
//                            Toast.makeText(TCCompressActivity.this, result.descMsg, Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });
//            }
//        };
//        mTXVideoEditer.setVideoGenerateListener(mTXVideoGenerateListener);
//    }
//
//    private void initView() {
//        rgVideoResolution = (RadioGroup) findViewById(R.id.rg_video_resolution);
//        rbVideoNotCompress = (RadioButton) findViewById(R.id.rb_video_compress_none);
//        rbVideoResolution360p = (RadioButton) findViewById(R.id.rb_video_compress_resolution_360p);
//        rbVideoResolution480p = (RadioButton) findViewById(R.id.rb_video_compress_resolution_480p);
//        rbVideoResolution540p = (RadioButton) findViewById(R.id.rb_video_compress_resolution_540p);
//        rbVideoResolution720p = (RadioButton) findViewById(R.id.rb_video_compress_resolution_720p);
//
//        mEtCompressBitrate = (EditText) findViewById(R.id.et_compress_bitrate);
//
//        rgVideoResolution.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                if(checkedId == rbVideoNotCompress.getId()){
//                    mVideoResolution = -1;
//                    mEtCompressBitrate.setText("");
//                }else if(checkedId == rbVideoResolution360p.getId()){
//                    mVideoResolution = TXVideoEditConstants.VIDEO_COMPRESSED_360P;
//                    mEtCompressBitrate.setText("2400");
//                }else if(checkedId == rbVideoResolution480p.getId()){
//                    mVideoResolution = TXVideoEditConstants.VIDEO_COMPRESSED_480P;
//                    mEtCompressBitrate.setText("2400");
//                }else if(checkedId == rbVideoResolution540p.getId()){
//                    mVideoResolution = TXVideoEditConstants.VIDEO_COMPRESSED_540P;
//                    mEtCompressBitrate.setText("6500");
//                }else if(checkedId == rbVideoResolution720p.getId()){
//                    mVideoResolution = TXVideoEditConstants.VIDEO_COMPRESSED_720P;
//                    mEtCompressBitrate.setText("9600");
//                }
//            }
//        });
//
//        mBtnStartCompress = (Button) findViewById(R.id.btn_compress_ok);
//        mBtnStartCompress.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startCompressVideo();
//            }
//        });
//
//        llBack = (LinearLayout) findViewById(R.id.back_ll);
//        llBack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
//    }
//
//    private void startCompressVideo() {
//        if(mVideoResolution == -1){
//            startPublishActivity(mInputPath);
//        }else{
//            if (mWorkLoadingProgress == null) {
//                initWorkLoadingProgress();
//            }
//            mWorkLoadingProgress.setProgress(0);
//            mWorkLoadingProgress.setCancelable(false);
//            mWorkLoadingProgress.show(getSupportFragmentManager(), "progress_dialog");
//
//            String inputBitrateStr = mEtCompressBitrate.getText().toString();
//            if(!TextUtils.isEmpty(inputBitrateStr)){
//                mBiteRate = Integer.parseInt(inputBitrateStr);
//            }else{
//                mBiteRate = 0;
//            }
//            mTXVideoEditer.setVideoBitrate(mBiteRate);
//            mTXVideoEditer.setCutFromTime(0, mTXVideoInfo.duration);
//            mTXVideoEditer.generateVideo(mVideoResolution, mOutputPath);
//        }
//    }
//
//    private void initWorkLoadingProgress() {
//        if (mWorkLoadingProgress == null) {
//            mWorkLoadingProgress = new VideoWorkProgressFragment();
//            mWorkLoadingProgress.setOnClickStopListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (mTXVideoEditer != null) {
//                        mTXVideoEditer.cancel();
//                        mWorkLoadingProgress.dismiss();
//                        mWorkLoadingProgress.setProgress(0);
//                    }
//                }
//            });
//        }
//        mWorkLoadingProgress.setProgress(0);
//    }
//
//    private void startPublishActivity(String videoPath){
//        Intent intent = new Intent(TCCompressActivity.this, TCVideoPublishActivity.class);
//        intent.putExtra(TCConstants.VIDEO_EDITER_PATH, videoPath);
//        startActivity(intent);
//    }
//
//}