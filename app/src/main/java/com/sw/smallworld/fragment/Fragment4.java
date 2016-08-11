package com.sw.smallworld.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sw.smallworld.R;
import com.sw.smallworld.ReadCnn;
import com.sw.smallworld.ReadRss;


public class Fragment4 extends  Fragment {
    RecyclerView recyclerView;
    final static public String address="http://feeds.bbci.co.uk/news/world/rss.xml";

    public Fragment4() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment3,container,false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerview);
        ReadRss readRss = new ReadRss(this.getContext(),recyclerView);
        readRss.address = address;
        readRss.execute();
        return rootView;
    }

}
