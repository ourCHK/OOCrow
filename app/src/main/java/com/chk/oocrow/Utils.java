package com.chk.oocrow;

import android.content.Context;
import android.database.ContentObserver;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.util.Log;

/**
 * Created by ljc on 17-12-27.
 */

public class Utils {
    private AudioManager mAudioManager;
    private MediaPlayer mMediaPlayer;
    private float mSpeed;
    private Context mContext;
    private SettingsContentObserver mSettingsContentObserver;
    private Handler mHandler;

    public Utils(Context context, AudioManager audioManager, Handler handler) {
        mContext = context;
        mHandler = handler;
        mAudioManager = audioManager;
        mMediaPlayer = MediaPlayer.create(context, R.raw.crownew);
        mMediaPlayer.setLooping(true);
        registerVolumeChangeReceiver(mContext, mHandler);
    }

    public int getVolume() {
        int mVolume = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        return mVolume;
    }

    public void setVolume(int progress) {
        float ratio = Math.round(progress / 10) / 10f;
        mMediaPlayer.setVolume(ratio, ratio);
    }

    public void playMusic(int progress) {
        if (progress > 60) {
            mSpeed = (float) progress / 60f;
        } else {
            mSpeed = 1f;
        }
        mMediaPlayer.setPlaybackParams(mMediaPlayer.getPlaybackParams().setSpeed(mSpeed));
        mMediaPlayer.start();
    }


    public void stopMusic() {
        if (mMediaPlayer.isPlaying()){
            mMediaPlayer.stop();
            mMediaPlayer.release();
        }
        unregisterVolumeChangeReceiver(mContext);
    }

    private void registerVolumeChangeReceiver(Context context, Handler handler) {
        mSettingsContentObserver = new SettingsContentObserver(handler);
        context.getContentResolver().registerContentObserver(android.provider.Settings.System.CONTENT_URI, true, mSettingsContentObserver);
    }

    private void unregisterVolumeChangeReceiver(Context context) {
        context.getContentResolver().unregisterContentObserver(mSettingsContentObserver);
    }


    class SettingsContentObserver extends ContentObserver {
        private Handler mHandler;

        public SettingsContentObserver(Handler handler) {
            super(handler);
            mHandler = handler;
        }

        @Override
        public void onChange(boolean selfChange) {
            super.onChange(selfChange);
            mHandler.sendEmptyMessageDelayed(0, 0);
        }
    }
}
