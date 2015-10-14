package com.cliff.ui.coverflow;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.cliff.ui.MainActivity;

/**
 * Created by geeboo on 2015/9/7.
 * 自定义对话框组件demo
 */
public class CFFragment extends ListFragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    public static CFFragment newInstance(int sectionNumber) {
        CFFragment fragment = new CFFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public CFFragment() {
    }

    private final Class[] exampleActivities = new Class[]{SimpleExample.class, ViewGroupExample.class,
            ViewGroupReflectionExample.class, XmlInflateExample.class};


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String[] items = new String[]{"SimpleExample", "ViewGroupExample",
                "ViewGroupReflectionExample", "XmlInflateExample"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, items);
        setListAdapter(adapter);

    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Intent i = new Intent(getContext(), exampleActivities[position]);
        startActivity(i);

    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));
    }
}
