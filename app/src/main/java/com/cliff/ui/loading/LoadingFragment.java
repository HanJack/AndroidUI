package com.cliff.ui.loading;

import android.app.Activity;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.os.Handler;

import com.cliff.ui.MainActivity;
import com.cliff.ui.R;
import com.hsj.libloading.AnimationUtils;
import com.hsj.libloading.LeafLoadingView;

import java.util.Random;

/**
 * Created by geeboo on 2015/9/7.
 * 自定义对话框组件demo
 */
public class LoadingFragment extends Fragment implements View.OnClickListener,SeekBar.OnSeekBarChangeListener {

    private static final String ARG_SECTION_NUMBER = "section_number";

    public static LoadingFragment newInstance(int sectionNumber) {
        LoadingFragment fragment = new LoadingFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public LoadingFragment() {
    }

    Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case REFRESH_PROGRESS:
                    if (mProgress < 40) {
                        mProgress += 1;
                        // 随机800ms以内刷新一次
                        mHandler.sendEmptyMessageDelayed(REFRESH_PROGRESS,
                                new Random().nextInt(800));
                        mLeafLoadingView.setProgress(mProgress);
                    } else {
                        mProgress += 1;
                        // 随机1200ms以内刷新一次
                        mHandler.sendEmptyMessageDelayed(REFRESH_PROGRESS,
                                new Random().nextInt(1200));
                        mLeafLoadingView.setProgress(mProgress);

                    }
                    break;

                default:
                    break;
            }
        };
    };

    private static final int REFRESH_PROGRESS = 0x10;
    private LeafLoadingView mLeafLoadingView;
    private SeekBar mAmpireSeekBar;
    private SeekBar mDistanceSeekBar;
    private TextView mMplitudeText;
    private TextView mDisparityText;
    private View mFanView;
    private Button mClearButton;
    private int mProgress = 0;

    private TextView mProgressText;
    private View mAddProgress;
    private SeekBar mFloatTimeSeekBar;

    private SeekBar mRotateTimeSeekBar;
    private TextView mFloatTimeText;
    private TextView mRotateTimeText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_loading, container, false);
        mFanView = rootView.findViewById(R.id.fan_pic);
        RotateAnimation rotateAnimation = AnimationUtils.initRotateAnimation(false, 1500, true,
                Animation.INFINITE);
        mFanView.startAnimation(rotateAnimation);
        mClearButton = (Button) rootView.findViewById(R.id.clear_progress);
        mClearButton.setOnClickListener(this);

        mLeafLoadingView = (LeafLoadingView) rootView.findViewById(R.id.leaf_loading);
        mMplitudeText = (TextView) rootView.findViewById(R.id.text_ampair);
        mMplitudeText.setText(getString(R.string.current_mplitude,
                mLeafLoadingView.getMiddleAmplitude()));

        mDisparityText = (TextView) rootView.findViewById(R.id.text_disparity);
        mDisparityText.setText(getString(R.string.current_Disparity,
                mLeafLoadingView.getMplitudeDisparity()));

        mAmpireSeekBar = (SeekBar) rootView.findViewById(R.id.seekBar_ampair);
        mAmpireSeekBar.setOnSeekBarChangeListener(this);
        mAmpireSeekBar.setProgress(mLeafLoadingView.getMiddleAmplitude());
        mAmpireSeekBar.setMax(50);

        mDistanceSeekBar = (SeekBar) rootView.findViewById(R.id.seekBar_distance);
        mDistanceSeekBar.setOnSeekBarChangeListener(this);
        mDistanceSeekBar.setProgress(mLeafLoadingView.getMplitudeDisparity());
        mDistanceSeekBar.setMax(20);

        mAddProgress = rootView.findViewById(R.id.add_progress);
        mAddProgress.setOnClickListener(this);
        mProgressText = (TextView) rootView.findViewById(R.id.text_progress);

        mFloatTimeText = (TextView) rootView.findViewById(R.id.text_float_time);
        mFloatTimeSeekBar = (SeekBar) rootView.findViewById(R.id.seekBar_float_time);
        mFloatTimeSeekBar.setOnSeekBarChangeListener(this);
        mFloatTimeSeekBar.setMax(5000);
        mFloatTimeSeekBar.setProgress((int) mLeafLoadingView.getLeafFloatTime());
        mFloatTimeText.setText(getResources().getString(R.string.current_float_time,
                mLeafLoadingView.getLeafFloatTime()));

        mRotateTimeText = (TextView) rootView.findViewById(R.id.text_rotate_time);
        mRotateTimeSeekBar = (SeekBar) rootView.findViewById(R.id.seekBar_rotate_time);
        mRotateTimeSeekBar.setOnSeekBarChangeListener(this);
        mRotateTimeSeekBar.setMax(5000);
        mRotateTimeSeekBar.setProgress((int) mLeafLoadingView.getLeafRotateTime());
        mRotateTimeText.setText(getResources().getString(R.string.current_float_time,
                mLeafLoadingView.getLeafRotateTime()));
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (seekBar == mAmpireSeekBar) {
            mLeafLoadingView.setMiddleAmplitude(progress);
            mMplitudeText.setText(getString(R.string.current_mplitude,
                    progress));
        } else if (seekBar == mDistanceSeekBar) {
            mLeafLoadingView.setMplitudeDisparity(progress);
            mDisparityText.setText(getString(R.string.current_Disparity,
                    progress));
        } else if (seekBar == mFloatTimeSeekBar) {
            mLeafLoadingView.setLeafFloatTime(progress);
            mFloatTimeText.setText(getResources().getString(R.string.current_float_time,
                    progress));
        }
        else if (seekBar == mRotateTimeSeekBar) {
            mLeafLoadingView.setLeafRotateTime(progress);
            mRotateTimeText.setText(getResources().getString(R.string.current_rotate_time,
                    progress));
        }

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onClick(View v) {
        if (v == mClearButton) {
            mLeafLoadingView.setProgress(0);
            mHandler.removeCallbacksAndMessages(null);
            mProgress = 0;
        } else if (v == mAddProgress) {
            mProgress++;
            mLeafLoadingView.setProgress(mProgress);
            mProgressText.setText(String.valueOf(mProgress));
        }
    }
}
