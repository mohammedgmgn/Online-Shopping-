package com.mahmoud.mohammed.onlineshopping.ui.categories;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.mahmoud.mohammed.onlineshopping.R;
import com.mahmoud.mohammed.onlineshopping.ui.categories.tabs.elctoronics.ElectoronicsFragment;
import com.mahmoud.mohammed.onlineshopping.ui.categories.tabs.lifestyle.LifeStyleFragment;
import com.mahmoud.mohammed.onlineshopping.ui.categories.tabs.moreproducts.MoreFragment;

public class CategoriesFragment extends Fragment {
    View root;
    Fragment fragment = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_categories, container, false);
        initiatUI();
        return root;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public CategoriesFragment() {
    }

    private void initiatUI() {
        AHBottomNavigation bottomNavigation = root.findViewById(R.id.top_tabs);
// Create items
        AHBottomNavigationItem item1 = new AHBottomNavigationItem("Elctoronics",R.drawable.common_full_open_on_phone);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem("LifeStyle", R.drawable.common_full_open_on_phone);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem("More", R.drawable.common_full_open_on_phone);
// Add items
        bottomNavigation.addItem(item1);
        bottomNavigation.addItem(item2);
        bottomNavigation.addItem(item3);
     //   bottomNavigation.setBackgroundColor(Color.parseColor("#6699ff"));
        //set default fragment
        fragment = new ElectoronicsFragment();
        addFraagment();
        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                // Do something cool here...
                switch (position) {
                    case 0:
                        //addTrackFraagment();
                        fragment = new ElectoronicsFragment();
                        break;
                    case 1:
                        fragment = new LifeStyleFragment();
                        break;
                    case 2:
                        fragment = new MoreFragment();
                        break;

                }

                if (fragment != null) {
                    addFraagment();
                }

                return true;
            }
        });


    }

    private void addFraagment() {
        FragmentTransaction fragmentTransaction =
                getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container_content, fragment);
        fragmentTransaction.commit();

    }


}
