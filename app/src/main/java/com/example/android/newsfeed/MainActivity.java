package com.example.android.newsfeed;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import androidx.core.view.GravityCompat;
import androidx.viewpager.widget.ViewPager;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.android.newsfeed.adapter.CategoryFragmentPagerAdapter;
import com.example.android.newsfeed.utils.Constants;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Log.i("DRAWER LAYOUT", "set drawer layout");
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        Log.i("VIEW PAGER", "get view pager view");
        viewPager = findViewById(R.id.viewpager);
        Log.i("TAB LAYOUT", "set tab layout");
        TabLayout tabLayout = findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        Log.i("NAVIGATION VIEW", "get navigation view");
        NavigationView navigationView = findViewById(R.id.nav_view);
        assert navigationView != null;
        navigationView.setNavigationItemSelectedListener(this);
        Log.i("NAVIGATION VIEW", "set default fragment");
        onNavigationItemSelected(navigationView.getMenu().getItem(0).setChecked(true));
        Log.i("PAGER ADAPTER", "set pager adapter");
        CategoryFragmentPagerAdapter pagerAdapter =
                new CategoryFragmentPagerAdapter(this, getSupportFragmentManager());
        Log.i("PAGER ADAPTER", "set pager adapter onto the view pager");
        viewPager.setAdapter(pagerAdapter);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Log.i("FRAGMENTS", "switch between fragments on item click in nav drawer");
        int id = item.getItemId();
        if (id == R.id.nav_home) {
            viewPager.setCurrentItem(Constants.HOME);
        } else if (id == R.id.nav_world) {
            viewPager.setCurrentItem(Constants.WORLD);
        } else if (id == R.id.nav_science) {
            viewPager.setCurrentItem(Constants.SCIENCE);
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.i("OPTIONS MENU", "initialize options menu with settings");
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.i("OPTIONS MENU", "an item in the options menu is selected!");
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent settingsIntent = new Intent(this, SettingsActivity.class);
            startActivity(settingsIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
