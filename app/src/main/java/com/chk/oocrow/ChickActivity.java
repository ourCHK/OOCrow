package com.chk.oocrow;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SeekBar;

import com.chk.oocrow.View.ChickView;
import com.chk.oocrow.View.PowerView;

public class ChickActivity extends AppCompatActivity {

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

    void init() {
        viewInit();
        dataInit();
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

    void dataInit() {

    }

}
