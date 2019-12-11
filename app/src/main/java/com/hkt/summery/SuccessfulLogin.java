package com.hkt.summery;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hkt.summery.ui.fragmentcategory.FragmentCategoryFragment;
import com.hkt.summery.ui.fragmentprofile.FragmentProfileFragment;
import com.luseen.spacenavigation.SpaceItem;
import com.luseen.spacenavigation.SpaceNavigationView;
import com.luseen.spacenavigation.SpaceOnClickListener;

public class SuccessfulLogin extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private TextView mTextMessage;
    SpaceNavigationView spaceNavigationView;
    final Fragment fragmentCategory = new FragmentCategoryFragment();
    final Fragment fragmentProfile = new FragmentProfileFragment();
    FragmentManager fm = null;
    Fragment active = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_successful_login);
        spaceNavigationView = (SpaceNavigationView) findViewById(R.id.spaceNavigation);
        spaceNavigationView.initWithSaveInstanceState(savedInstanceState);
        addCoponentForBottomNavigation();
        bottomClick();

        drawerLayout = (DrawerLayout) findViewById(R.id.drawable_layout);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        toggle.setDrawerIndicatorEnabled(true);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        fm = getSupportFragmentManager();
        spaceNavigationView.showIconOnly();
        fm.beginTransaction().add(R.id.fragment_container, fragmentProfile).hide(fragmentProfile).commit();
        fm.beginTransaction().add(R.id.fragment_container, fragmentCategory).commit();
        active = fragmentCategory;

        ActionBar bar = getSupportActionBar();
        if(bar!=null){
            TextView tv = new TextView(getApplicationContext());
            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                    ActionBar.LayoutParams.MATCH_PARENT, // Width of TextView
                    ActionBar.LayoutParams.WRAP_CONTENT); // Height of TextView
            tv.setLayoutParams(lp);
            tv.setText(bar.getTitle());
            tv.setTextColor(Color.WHITE);
            tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
            bar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
            bar.setCustomView(tv);
            bar.setDisplayHomeAsUpEnabled(true);
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return toggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

    public void bottomClick() {
        spaceNavigationView.setSpaceOnClickListener(new SpaceOnClickListener() {
            @Override
            public void onCentreButtonClick() {
                fm.beginTransaction().hide(active).show(fragmentProfile).commit();
                active = fragmentProfile;
            }

            @Override
            public void onItemClick(int itemIndex, String itemName) {
                switch (itemIndex) {
                    case 0:
                        fm.beginTransaction().hide(active).show(fragmentCategory).commit();
                        active = fragmentCategory;
                        break;

                    case 1:
                        break;

                }

            }

            @Override
            public void onItemReselected(int itemIndex, String itemName) {
                switch (itemIndex) {
                    case 0:
                        fm.beginTransaction().hide(active).show(fragmentCategory).commit();
                        active = fragmentCategory;
                        break;

                    case 1:
                        break;

                }
            }
        });
    }

    private void addCoponentForBottomNavigation() {
        spaceNavigationView.addSpaceItem(new SpaceItem("Category", R.drawable.menu));
        spaceNavigationView.addSpaceItem(new SpaceItem("Meals", R.drawable.knife_and_fork));
    }
}



