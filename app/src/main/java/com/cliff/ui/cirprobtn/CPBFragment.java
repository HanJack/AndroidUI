package com.cliff.ui.cirprobtn;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.cliff.ui.MainActivity;
import com.cliff.ui.arclayout.Demo;

/**
 * Created by geeboo on 2015/9/7.
 */
public class CPBFragment extends ListFragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    public static CPBFragment newInstance(int sectionNumber) {
        CPBFragment fragment = new CPBFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public CPBFragment() {
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String[] items = new String[]{"IndeterminateProgressSample", "IntegerProgressSample",
                "StateChangeSample", "ProgressPaddingSample", "CustomSelectorSample"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, items);
        setListAdapter(adapter);

    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        switch (position) {
            case 0:
                Sample1Activity.startThisActivity(getActivity());
                break;
            case 1:
                Sample2Activity.startThisActivity(getActivity());
                break;
            case 2:
                Sample3Activity.startThisActivity(getActivity());
                break;
            case 3:
                Sample4Activity.startThisActivity(getActivity());
                break;
            case 4:
                Sample5Activity.startThisActivity(getActivity());
                break;
        }

    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));
    }
}
