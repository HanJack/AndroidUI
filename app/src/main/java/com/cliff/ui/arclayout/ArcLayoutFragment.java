package com.cliff.ui.arclayout;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.cliff.ui.MainActivity;
import com.cliff.ui.R;

/**
 * Created by geeboo on 2015/9/7.
 */
public class ArcLayoutFragment extends Fragment implements AdapterView.OnItemClickListener {

    private static final String ARG_SECTION_NUMBER = "section_number";

    public static ArcLayoutFragment newInstance(int sectionNumber) {
        ArcLayoutFragment fragment = new ArcLayoutFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public ArcLayoutFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_arclayout, container, false);
        ListView listView = (ListView)rootView.findViewById(R.id.list);
        listView.setOnItemClickListener(this);

        ArrayAdapter<String> demoAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1);

        for (Demo demo : Demo.values()) {
            demoAdapter.add(getString(demo.titleResId));
        }

        listView.setAdapter(demoAdapter);
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Demo demo = Demo.values()[position];
        demo.startActivity(getActivity());
    }
}
