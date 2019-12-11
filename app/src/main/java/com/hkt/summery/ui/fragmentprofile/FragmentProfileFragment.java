package com.hkt.summery.ui.fragmentprofile;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hkt.summery.Fragment_post;
import com.hkt.summery.Fragment_save;
import com.hkt.summery.R;
import com.hkt.summery.ViewPaperAdapterForProfile;


public class FragmentProfileFragment extends Fragment {

    private FragmentProfileViewModel mViewModel;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPaperAdapterForProfile adapter;

    public static FragmentProfileFragment newInstance() {
        return new FragmentProfileFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        tabLayout = (TabLayout) view.findViewById(R.id.tabLayout);
        viewPager = (ViewPager) view.findViewById(R.id.viewpaper);
        adapter = new ViewPaperAdapterForProfile(getActivity().getSupportFragmentManager());

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.post);
        tabLayout.getTabAt(1).setIcon(R.drawable.saved);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(FragmentProfileViewModel.class);
        // TODO: Use the ViewModel
    }

}
