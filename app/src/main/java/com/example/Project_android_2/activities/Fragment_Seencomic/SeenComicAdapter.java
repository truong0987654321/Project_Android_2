package com.example.Project_android_2.activities.Fragment_Seencomic;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;


public class SeenComicAdapter extends FragmentStatePagerAdapter {

    public SeenComicAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new ListChapterFragment();
            case 1:
                return new IntroFragment();
            default:
                return new ListChapterFragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "D.S Chương";
            case 1:
                return "Giới thiệu";
            default:
                return "D.S Chương";
        }
    }
}
