package com.cliff.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cliff.ui.bezier.BezierView;
import com.cliff.ui.crossview.CrossView;

/**
 * Created by geeboo on 2015/9/7.
 */
public class MainFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    public static MainFragment newInstance(int sectionNumber) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public MainFragment() {
    }


    private CrossView mCrossView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        BezierView mBezierView = (BezierView) rootView.findViewById(R.id.bezier);
        mBezierView.setText("14");          //设置未读信息数

        mCrossView = (CrossView) rootView.findViewById(R.id.sample_cross_view);
        mCrossView.setOnClickListener(mCrossViewClickListener);
        mCrossView.setColor(getResources().getColor(R.color.cross_view_stroke_color));

        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));
    }
    private final View.OnClickListener mCrossViewClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mCrossView.toggle();
        }
    };
}
