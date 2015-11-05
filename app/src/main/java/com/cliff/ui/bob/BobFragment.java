package com.cliff.ui.bob;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.cliff.libbob.ExpandableItem;
import com.cliff.libbob.ExpandableSelector;
import com.cliff.libbob.ExpandableSelectorListener;
import com.cliff.libbob.OnExpandableItemClickListener;
import com.cliff.ui.MainActivity;
import com.cliff.ui.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by geeboo on 2015/9/7.
 */
public class BobFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    public static BobFragment newInstance(int sectionNumber) {
        BobFragment fragment = new BobFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public BobFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_bob, container, false);

        initializeColorsExpandableSelector(rootView);
        initializeSizesExpandableSelector(rootView);
        initializeIconsExpandableSelector(rootView);
        initializeCloseAllButton(rootView);
        return rootView;
    }

    private View colorsHeaderButton;
    private ExpandableSelector colorsExpandableSelector;
    private ExpandableSelector sizesExpandableSelector;
    private ExpandableSelector iconsExpandableSelector;


    //初始化自定义样式按钮
    private void initializeColorsExpandableSelector(View rootView) {
        colorsExpandableSelector = (ExpandableSelector) rootView.findViewById(R.id.es_colors);
        List<ExpandableItem> expandableItems = new ArrayList<ExpandableItem>();
        expandableItems.add(new ExpandableItem(R.drawable.item_brown));
        expandableItems.add(new ExpandableItem(R.drawable.item_green));
        expandableItems.add(new ExpandableItem(R.drawable.item_orange));
        expandableItems.add(new ExpandableItem(R.drawable.item_pink));
        colorsExpandableSelector.showExpandableItems(expandableItems);
        colorsHeaderButton = rootView.findViewById(R.id.bt_colors);
        colorsHeaderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                colorsHeaderButton.setVisibility(View.INVISIBLE);
                colorsExpandableSelector.expand();
            }
        });
        colorsExpandableSelector.setOnExpandableItemClickListener(new OnExpandableItemClickListener() {
            @Override
            public void onExpandableItemClickListener(int index, View view) {
                switch (index) {
                    case 0:
                        colorsHeaderButton.setBackgroundResource(R.drawable.item_brown);
                        break;
                    case 1:
                        colorsHeaderButton.setBackgroundResource(R.drawable.item_green);
                        break;
                    case 2:
                        colorsHeaderButton.setBackgroundResource(R.drawable.item_orange);
                        break;
                    default:
                        colorsHeaderButton.setBackgroundResource(R.drawable.item_pink);
                }
                colorsExpandableSelector.collapse();
            }
        });
    }

    //文字按钮
    private void initializeSizesExpandableSelector(View rootView) {
        sizesExpandableSelector = (ExpandableSelector) rootView.findViewById(R.id.es_sizes);
        List<ExpandableItem> expandableItems = new ArrayList<ExpandableItem>();
        expandableItems.add(new ExpandableItem("XL"));
        expandableItems.add(new ExpandableItem("L"));
        expandableItems.add(new ExpandableItem("M"));
        expandableItems.add(new ExpandableItem("S"));
        sizesExpandableSelector.showExpandableItems(expandableItems);
        sizesExpandableSelector.setOnExpandableItemClickListener(new OnExpandableItemClickListener() {
            @Override
            public void onExpandableItemClickListener(int index, View view) {
                switch (index) {
                    case 1:
                        ExpandableItem firstItem = sizesExpandableSelector.getExpandableItem(1);
                        swipeFirstItem(1, firstItem);
                        break;
                    case 2:
                        ExpandableItem secondItem = sizesExpandableSelector.getExpandableItem(2);
                        swipeFirstItem(2, secondItem);
                        break;
                    case 3:
                        ExpandableItem fourthItem = sizesExpandableSelector.getExpandableItem(3);
                        swipeFirstItem(3, fourthItem);
                        break;
                    default:
                }
                sizesExpandableSelector.collapse();
            }

            private void swipeFirstItem(int position, ExpandableItem clickedItem) {
                ExpandableItem firstItem = sizesExpandableSelector.getExpandableItem(0);
                sizesExpandableSelector.updateExpandableItem(0, clickedItem);
                sizesExpandableSelector.updateExpandableItem(position, firstItem);
            }
        });
    }

    //初始化图片按钮
    private void initializeIconsExpandableSelector(View rootView) {
        iconsExpandableSelector = (ExpandableSelector) rootView.findViewById(R.id.es_icons);
        List<ExpandableItem> expandableItems = new ArrayList<ExpandableItem>();
        ExpandableItem item = new ExpandableItem();
        item.setResourceId(R.drawable.ic_keyboard_arrow_up_black);
        expandableItems.add(item);
        item = new ExpandableItem();
        item.setResourceId(R.drawable.ic_gamepad_black);
        expandableItems.add(item);
        item = new ExpandableItem();
        item.setResourceId(R.drawable.ic_device_hub_black);
        expandableItems.add(item);
        iconsExpandableSelector.showExpandableItems(expandableItems);
        iconsExpandableSelector.setOnExpandableItemClickListener(new OnExpandableItemClickListener() {
            @Override
            public void onExpandableItemClickListener(int index, View view) {
                if (index == 0 && iconsExpandableSelector.isExpanded()) {
                    iconsExpandableSelector.collapse();
                    updateIconsFirstButtonResource(R.drawable.ic_keyboard_arrow_up_black);
                }
                switch (index) {
                    case 1:
                        showToast("Gamepad icon button clicked.");
                        break;
                    case 2:
                        showToast("Hub icon button clicked.");
                        break;
                    default:
                }
            }
        });
        iconsExpandableSelector.setExpandableSelectorListener(new ExpandableSelectorListener() {
            @Override
            public void onCollapse() {

            }

            @Override
            public void onExpand() {
                updateIconsFirstButtonResource(R.drawable.ic_keyboard_arrow_down_black);
            }

            @Override
            public void onCollapsed() {

            }

            @Override
            public void onExpanded() {

            }
        });
    }

    private void initializeCloseAllButton(View rootView) {
        final View closeButton = rootView.findViewById(R.id.bt_close);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                colorsExpandableSelector.collapse();
                sizesExpandableSelector.collapse();
                iconsExpandableSelector.collapse();
                updateIconsFirstButtonResource(R.drawable.ic_keyboard_arrow_up_black);
            }
        });
        colorsExpandableSelector.setExpandableSelectorListener(new ExpandableSelectorListener() {
            @Override
            public void onCollapse() {

            }

            @Override
            public void onExpand() {

            }

            @Override
            public void onCollapsed() {
                colorsHeaderButton.setVisibility(View.VISIBLE);
            }

            @Override
            public void onExpanded() {

            }
        });
    }

    private void updateIconsFirstButtonResource(int resourceId) {
        ExpandableItem arrowUpExpandableItem = new ExpandableItem();
        arrowUpExpandableItem.setResourceId(resourceId);
        iconsExpandableSelector.updateExpandableItem(0, arrowUpExpandableItem);
    }

    private void showToast(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));
    }
}
