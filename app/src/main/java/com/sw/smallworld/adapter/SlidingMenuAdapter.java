package com.sw.smallworld.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sw.smallworld.R;
import com.sw.smallworld.model.ItemSlideMenu;

import java.util.List;

/**
 * Created by wdw88_000 on 7/27/2016.
 */
public class SlidingMenuAdapter extends BaseAdapter {
    private Context context;
    private List<ItemSlideMenu> listItem;

    public SlidingMenuAdapter(Context context, List<ItemSlideMenu> listItem){
        this.context = context;
        this.listItem = listItem;
    }

    @Override
    public int getCount() {
        return listItem.size();
    }

    @Override
    public Object getItem(int i) {
        return listItem.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View v = View.inflate(context, R.layout.item_sliding_menu,null);
        ImageView img = (ImageView) v.findViewById(R.id.item_img);
        TextView tv = (TextView) v.findViewById(R.id.item_title);

        ItemSlideMenu  item = listItem.get(i);
        img.setImageResource(item.getImgId());
        tv.setText(item.getTitle());

        return v;
    }
}
