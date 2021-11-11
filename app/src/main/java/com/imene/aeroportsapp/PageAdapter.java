package com.imene.aeroportsapp;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.imene.aeroportsapp.models.metar.Datum;

import java.util.List;

public class PageAdapter extends FragmentPagerAdapter {

    // 1 - Array of colors that will be passed to PageFragment
    private int[] colors;
    List<Datum> liste;

    // 2 - Default Constructor
    public PageAdapter(FragmentManager mgr, int[] colors,List<Datum> liste ) {
        super(mgr);
        this.colors = colors;
        this.liste = liste;

    }

    @Override
    public int getCount() {
        return(liste.size()); // 3 - Number of page to show
    }

    @Override
    public Fragment getItem(int position) {
        // 4 - Page to return
        return(PageFragment.newInstance(position, this.colors[position]));
    }
}

