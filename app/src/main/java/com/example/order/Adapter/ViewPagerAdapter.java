package com.example.order.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.order.Fragment.MenuFragment;
import com.example.order.Fragment.OrderFragment;
import com.example.order.Fragment.ReportFragment;
import com.example.order.Fragment.StaffFragment;
import com.example.order.Fragment.TableFragment;


public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return new OrderFragment();
            case 1:
                return new TableFragment();
            case 2:
                return new MenuFragment();
            case 3:
                return new ReportFragment();
            case 4:
                return new StaffFragment();
            default:
                return new OrderFragment();
        }
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position) {
            case 0:
                title = "Orders";
                break;
            case 1:
                title = "Table";
                break;
            case 2:
                title = "Menu";
                break;
            case 3:
                title = "Report";
                break;
            case 4:
                title="Staff";
        }
        return title;
    }
}
