package com.sw.smallworld;


import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;


import com.sw.smallworld.fragment.Fragment1;
import com.sw.smallworld.fragment.Fragment2;
import com.sw.smallworld.fragment.Fragment3;
import com.sw.smallworld.fragment.Fragment4;
import com.sw.smallworld.fragment.Fragment5;
import com.sw.smallworld.fragment.Fragment6;

import com.sw.smallworld.fragment.Fragment7;
import com.sw.smallworld.fragment.Fragment8;
import com.sw.smallworld.fragment.Fragment9;
import com.sw.smallworld.model.ItemSlideMenu;

import java.util.List;


public class   MainActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;

    RecyclerView recyclerView;
    FragmentTransaction fragmentTransaction;
    NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        // listViewSliding = (ListView) findViewById(R.id.lv_sliding_menu);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        // listSliding = new ArrayList<>();

        // listSliding.add(new ItemSlideMenu(R.drawable.home, "HOME"));
        // listSliding.add(new ItemSlideMenu(R.drawable.bbc_news, "BBC"));
        // listSliding.add(new ItemSlideMenu(R.drawable.abccom_logo, "ABC"));
        // listSliding.add(new ItemSlideMenu(R.drawable.wsj_sm_logo, "The Wall Street Journal"));
        // listSliding.add(new ItemSlideMenu(R.drawable.bbc_news, "TW"));
        // listSliding.add(new ItemSlideMenu(R.drawable.bbc_news, "SC"));
        // listSliding.add(new ItemSlideMenu(R.drawable.bbc_news, "SC"));


        // adapter = new SlidingMenuAdapter(this, listSliding);
        //  listViewSliding.setAdapter(adapter);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // setTitle(listSliding.get(0).getTitle());
        // listViewSliding.setItemChecked(0,true);
        //drawerLayout.closeDrawer(listViewSliding);
        // recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        //   listViewSliding.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        //      @Override
        //       public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        //          setTitle(listSliding.get(i).getTitle());
        //          listViewSliding.setItemChecked(i,true);
        //          replaceFragment(i);
        //          drawerLayout.closeDrawer(listViewSliding);
        //     }
        // });
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.drawer_opened, R.string.drawer_closed) {


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
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        replaceFragment(0);
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        replaceFragment(0);
                        item.setChecked(true);
                        getSupportActionBar().setTitle("Home");
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.bbcworld:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.main_content,new Fragment1());
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle("BBC World");
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.bbcbusiness:
                        replaceFragment(4);
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        getSupportActionBar().setTitle("BBC Business");
                        break;
                    case R.id.bbcpolitics:
                        replaceFragment(7);
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        getSupportActionBar().setTitle("BBC Politics");
                        break;
                    case R.id.abcworld:
                        replaceFragment(2);
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        getSupportActionBar().setTitle("ABC World");
                        break;
                    case R.id.abcsport:
                        replaceFragment(5);
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        getSupportActionBar().setTitle("ABC Sports");
                        break;
                    case R.id.abcpolitics:
                        replaceFragment(8);
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        getSupportActionBar().setTitle("ABC Health");
                        break;
                    case R.id.wsjworld:
                        replaceFragment(3);
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        getSupportActionBar().setTitle("WSJ World");
                        break;
                    case R.id.wsjsport:
                        replaceFragment(6);
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        getSupportActionBar().setTitle("WSJ Business");
                        break;
                    case R.id.wsjpolitics:
                        replaceFragment(9);
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        getSupportActionBar().setTitle("WSJ Technology");
                        break;

                }
                return true;
            }
        });
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
                getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                break;

            case 1:
                fragment = new Fragment1();
                break;

            case 2:
                fragment = new Fragment2();
                break;

            case 3:
                fragment = new Fragment3();
                break;

            case 4:
                fragment = new Fragment4();
                break;

            case 5:
                fragment = new Fragment5();
                break;
            case 6:
                fragment = new Fragment6();
                break;
            case 7:
                fragment = new Fragment7();
                break;
            case 8:
                fragment = new Fragment8();
                break;
            case 9:
                fragment = new Fragment9();
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
