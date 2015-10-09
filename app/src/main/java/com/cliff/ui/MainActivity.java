package com.cliff.ui;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.widget.DrawerLayout;

import com.cliff.ui.arclayout.ArcLayoutFragment;
import com.cliff.ui.cirprobtn.CPBFragment;
import com.cliff.ui.dialog.DateDialogFragment;
import com.cliff.ui.dialog.DialogFragment;
import com.cliff.ui.dialog.EffectsFragment;
import com.cliff.ui.loading.LoadingFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.cliff.ui.R.layout.activity_main);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(com.cliff.ui.R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                com.cliff.ui.R.id.navigation_drawer,
                (DrawerLayout) findViewById(com.cliff.ui.R.id.drawer_layout));
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();

        switch (position) {
            case 0:
                fragmentManager.beginTransaction().replace(R.id.container, ArcLayoutFragment.newInstance(position + 1)).commit();
                break;
            case 1:
                fragmentManager.beginTransaction().replace(R.id.container, DialogFragment.newInstance(position + 1)).commit();
                break;
            case 2:
            case 3:
                fragmentManager.beginTransaction().replace(R.id.container, MainFragment.newInstance(position + 1)).commit();
                break;
            case 4:
                fragmentManager.beginTransaction().replace(R.id.container, DateDialogFragment.newInstance(position + 1)).commit();
                break;
            case 5:
                fragmentManager.beginTransaction().replace(R.id.container, EffectsFragment.newInstance(position + 1)).commit();
                break;
            case 28:
                fragmentManager.beginTransaction().replace(R.id.container, LoadingFragment.newInstance(position + 1)).commit();
                break;
            case 29:
                fragmentManager.beginTransaction().replace(R.id.container, CPBFragment.newInstance(position + 1)).commit();
        }
    }

    public void onSectionAttached(int number) {
        mTitle = getResources().getStringArray(com.cliff.ui.R.array.UiList)[number - 1];
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(com.cliff.ui.R.menu.menu_main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == com.cliff.ui.R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

//    /**
//     * A placeholder fragment containing a simple view.
//     */
//    public static class PlaceholderFragment extends Fragment implements AdapterView.OnItemClickListener{
//        /**
//         * The fragment argument representing the section number for this
//         * fragment.
//         */
//        private static final String ARG_SECTION_NUMBER = "section_number";
//
//        /**
//         * Returns a new instance of this fragment for the given section
//         * number.
//         */
//        public static PlaceholderFragment newInstance(int sectionNumber) {
//            PlaceholderFragment fragment = new PlaceholderFragment();
//            Bundle args = new Bundle();
//            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
//            fragment.setArguments(args);
//            return fragment;
//        }
//
//        public PlaceholderFragment() {
//        }
//
//        @Override
//        public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                                 Bundle savedInstanceState) {
//            View rootView = inflater.inflate(com.cliff.ui.R.layout.fragment_main, container, false);
//            ListView listView = (ListView)rootView.findViewById(R.id.list);
//            listView.setOnItemClickListener(this);
//
//            ArrayAdapter<String> demoAdapter = new ArrayAdapter<String>(getActivity(),
//                    android.R.layout.simple_list_item_1);
//
//            for (Demo demo : Demo.values()) {
//                demoAdapter.add(getString(demo.titleResId));
//            }
//
//            listView.setAdapter(demoAdapter);
//            return rootView;
//        }
//
//        @Override
//        public void onAttach(Activity activity) {
//            super.onAttach(activity);
//            ((MainActivity) activity).onSectionAttached(
//                    getArguments().getInt(ARG_SECTION_NUMBER));
//        }
//
//        @Override
//        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//            Demo demo = Demo.values()[position];
//            demo.startActivity(getActivity());
//        }
//    }

}
