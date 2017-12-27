package com.chk.oocrow;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;

/**
 * Created by ljc on 17-12-27.
 */

public class Utils {
    private AudioManager mAudioManager;
    private MediaPlayer mMediaPlayer;
    private float mSpeed;

    public Utils(Context context) {
        mAudioManager = (AudioManager)context.getSystemService(Context.AUDIO_SERVICE);
        mMediaPlayer = MediaPlayer.create(context, R.raw.crownew);
        mMediaPlayer.start();
    }

    public int getVolume(){
        int mVolume = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        return mVolume;
    }

    public void setVolume(int volume){
        mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, volume, 0);
    }

    public void playMusic(float speed){
        mSpeed = speed;
        mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mMediaPlayer.setPlaybackParams(mMediaPlayer.getPlaybackParams().setSpeed(mSpeed));
                mMediaPlayer.start();
            }
        });
        mMediaPlayer.setPlaybackParams(mMediaPlayer.getPlaybackParams().setSpeed(mSpeed));
    }

    public void stopMusic(){
        mMediaPlayer.stop();
        mMediaPlayer.release();
    }

}
