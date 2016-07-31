package com.sw.smallworld;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


import com.sw.smallworld.adapter.SlidingMenuAdapter;
import com.sw.smallworld.fragment.Fragment1;
import com.sw.smallworld.fragment.Fragment2;
import com.sw.smallworld.fragment.Fragment3;
import com.sw.smallworld.model.ItemSlideMenu;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<ItemSlideMenu> listSliding;
    private SlidingMenuAdapter adapter;
    private ListView listViewSliding;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;

    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewSliding = (ListView) findViewById(R.id.lv_sliding_menu);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        listSliding = new ArrayList<>();
        listSliding.add(new ItemSlideMenu(R.drawable.bbc_news, "BBC"));
        listSliding.add(new ItemSlideMenu(R.drawable.nyt_logo, "NYTimes"));
        listSliding.add(new ItemSlideMenu(R.drawable.wsj_sm_logo, "The Wall Street Journal"));
        listSliding.add(new ItemSlideMenu(R.drawable.bbc_news, "TW"));
        listSliding.add(new ItemSlideMenu(R.drawable.bbc_news, "SC"));
        listSliding.add(new ItemSlideMenu(R.drawable.bbc_news, "SC"));
        adapter = new SlidingMenuAdapter(this, listSliding);
        listViewSliding.setAdapter(adapter);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle(listSliding.get(0).getTitle());
        listViewSliding.setItemChecked(0,true);
        drawerLayout.closeDrawer(listViewSliding);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        listViewSliding.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                setTitle(listSliding.get(i).getTitle());
                listViewSliding.setItemChecked(i,true);
                replaceFragment(i);
                drawerLayout.closeDrawer(listViewSliding);
            }
        });
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.drawer_opened,R.string.drawer_closed){


            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                invalidateOptionsMenu();
            }
        };
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return  super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInsceState){
        super.onPostCreate(savedInsceState);
        actionBarDrawerToggle.syncState();
    }
    private void replaceFragment(int pos){

        Fragment fragment = null;
        switch(pos){
            case 0:
                fragment = new Fragment1();
                break;
            case 1:
                fragment = new Fragment2();
                break;
            case 2:
                fragment = new Fragment3();
                break;
            default:
                fragment = new Fragment1();
                break;
        }
        if(null!=fragment){
            FragmentManager fragmentManger =  getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManger.beginTransaction();
            transaction.replace(R.id.main_content,fragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }
}
