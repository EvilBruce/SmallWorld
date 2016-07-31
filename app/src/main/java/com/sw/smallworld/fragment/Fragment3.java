package com.sw.smallworld.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sw.smallworld.R;
import com.sw.smallworld.ReadWSJ;

public class Fragment3 extends Fragment {

    final public static String address = "http://www.wsj.com/xml/rss/3_7085.xml";
    RecyclerView recyclerView;
    public Fragment3() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment3,container,false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerview);
        ReadWSJ readWSJ= new ReadWSJ(this.getContext(),recyclerView);
        readWSJ.address = address;
        readWSJ.execute();
        return rootView;
    }


}