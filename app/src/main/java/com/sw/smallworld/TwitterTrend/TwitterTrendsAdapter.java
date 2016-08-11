package com.sw.smallworld.TwitterTrend;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.sw.smallworld.R;

/**
 * Created by wdw88_000 on 8/10/2016.
 */
public class TwitterTrendsAdapter extends ArrayAdapter<String> {
    private final Activity context;
    private final String[] hashtag;
    private final int[] volumn;

    public TwitterTrendsAdapter(Activity context, String[] hashtag, int[] volumn) {
        super(context, R.layout.activity_trends, hashtag);
        // TODO Auto-generated constructor stub

        this.context = context;
        this.hashtag = hashtag;
        this.volumn = volumn;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.activity_trends, null);

        TextView txtID = (TextView) rowView.findViewById(R.id.trendId);
        TextView txtHashtag = (TextView) rowView.findViewById(R.id.hashTag);
        TextView txtVolumn = (TextView) rowView.findViewById(R.id.trendsVolumn);


        txtID.setText(position + 1 + "");
        txtHashtag.setText(hashtag[position]);
        //Log.e("xxxxxxxxx------>",hashtag[position]);
        txtVolumn.setText(Integer.toString(volumn[position]));
        return rowView;

    }
}

