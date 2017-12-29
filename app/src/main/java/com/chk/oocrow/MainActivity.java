package com.chk.oocrow;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Toast;

import com.chk.oocrow.View.MyPowerView;

public class MainActivity extends AppCompatActivity{

    final int UP = 1;
    final int DOWN = 2;

    SeekBar seekBar;
    MyPowerView myPowerView;
    ImageView imageView;
    RelativeLayout relativeLayout;
    MediaPlayer mediaPlayer;

    int progressSelect;

    Thread downThread;
    boolean isStopThread;
    Handler handler;

    int whichAni;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    void init() {
        viewInit();
        dataInit();
    }

    void viewInit() {
        seekBar = findViewById(R.id.seekBar);
        myPowerView = findViewById(R.id.powerView);
        relativeLayout = findViewById(R.id.relativeLayout);
        imageView = findViewById(R.id.imageView);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "you click this", Toast.LENGTH_SHORT).show();
                if (whichAni == 0) {
                    ObjectAnimator
                            .ofFloat(imageView, "rotationX", 0.0F, 360.0F)//
                            .setDuration(1000)
                            .start();
                    whichAni = 1;
                } else if (whichAni == 1){
                    ObjectAnimator
                            .ofFloat(imageView, "rotationY", 0.0F, 360.0F)//
                            .setDuration(1000)
                            .start();
                    whichAni = 2;
                } else {
                    ObjectAnimator anim1 = ObjectAnimator
                            .ofFloat(imageView,"rotationX", 0.0F, 360.0F);
                    ObjectAnimator anim2 = ObjectAnimator
                            .ofFloat(imageView,"rotationY",0.0F, 360.0F);
                    AnimatorSet aniSet = new AnimatorSet();
                    aniSet.setDuration(1000);
                    aniSet.playTogether(anim1,anim2);
                    aniSet.start();
                    whichAni = 0;
                }
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//                ObjectAnimator.ofFloat(imageView,"scaleX",1+progress/25f).start();
//                ObjectAnimator.ofFloat(imageView,"scaleY",1+progress/25f).start();
                progressSelect = progress;
                imageView.setScaleX(1+progress/25f);
                imageView.setScaleY(1+progress/25f);
                myPowerView.setCurrentHeight(progress);
                Log.i("MainActivity",progress+"");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                isStopThread = true;
                mediaPlayer.start();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.stop();
                mediaPlayer.release();
                mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.crow);
                startDownThread();
            }
        });

//
//        up.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                up.performClick();
//                seekBar.incrementProgressBy(progress);
//            }
//        });
//
//        up.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                v.performClick();
//                seekBar.incrementProgressBy(progress);
//                Log.i("mainActivity","press");
//                return true;
//            }
//        });
//
//
//
//
//        down.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                seekBar.incrementProgressBy(-progress);
//            }
//        });
    }

    @SuppressLint("HandlerLeak")
    void dataInit() {

        progressSelect = 5;
        mediaPlayer = MediaPlayer.create(this,R.raw.crow);

        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                switch(msg.what) {
                    case UP:
                        seekBar.incrementProgressBy(1);
                        break;
                    case DOWN:
                        seekBar.incrementProgressBy(-1);
                        break;
                        default:
                            break;
                }
            }
        };
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mediaPlayer.stop();
        mediaPlayer.release();
    }

    void startDownThread() {
        isStopThread = false;
        downThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while(seekBar.getProgress() != 0 && !isStopThread) {
                    handler.sendEmptyMessage(DOWN);
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        downThread.start();
    }

}
