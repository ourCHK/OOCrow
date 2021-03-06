package com.chk.oocrow;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.SeekBar;

import com.chk.oocrow.View.ChickView;
import com.chk.oocrow.View.PowerView;

public class ChickActivity extends AppCompatActivity {

    private AudioManager mAudioManager;
    private float defaultVolume;
    private Handler mHandler;

    private Utils mUtils;

    private ChickView mMyChickView;
    private PowerView mPowerView;
    private SeekBar mSeekBar;
    private int mPower;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chick);
        init();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_VOLUME_UP:
                defaultVolume = mUtils.getVolume();
                break;
            case KeyEvent.KEYCODE_VOLUME_DOWN:
                defaultVolume = mUtils.getVolume();
                Log.d("asd", "defaultVolume: " + String.valueOf(defaultVolume));
                break;
        }
        return super.onKeyDown(keyCode, event);
    }

    void init() {
        dataInit();
        viewInit();
    }

    void viewInit() {
        mMyChickView = findViewById(R.id.myChickView);
        mPowerView = findViewById(R.id.powerView);
        mPowerView.setAlpha(0.2f);
        mSeekBar = findViewById(R.id.seekBar);
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                mPower = progress;
                mMyChickView.setPower(progress);
                mPowerView.setPower(progress);
                mUtils.setVolume(progress);
                mUtils.playMusic(progress);
                if (progress == 0)
                    mPowerView.setAlpha(0.1f);
                else
                    mPowerView.setAlpha(1f);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                mPowerView.setAlpha(1);
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if (mPower == 0)
                    mPowerView.setAlpha(0.1f);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUtils.stopMusic();
    }

    @SuppressLint("HandlerLeak")
    void dataInit() {
        mAudioManager = (AudioManager) this.getSystemService(Context.AUDIO_SERVICE);
        mUtils = new Utils(this, mAudioManager, mHandler);
        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 0) {
                    defaultVolume = mUtils.getVolume();
                }
            }
        };
        mHandler.sendEmptyMessageDelayed(0, 0);
    }

}
