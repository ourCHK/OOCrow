package com.chk.oocrow;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.ContentObserver;
import android.media.AudioManager;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.SeekBar;

public class ChickActivity extends AppCompatActivity {

    MyChickView mMyChickView;
    SeekBar mSeekBar;
    Utils mUtils;

    private float defaultVolume;
    private AudioManager mAudioManager;
    private Handler mHandler;


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
        mSeekBar = findViewById(R.id.seekBar);
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mMyChickView.setPower(progress);
                mUtils.setVolume(progress);
                mUtils.playMusic(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mUtils.isPlaying()) {
            mUtils.stopMusic();
        }
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
