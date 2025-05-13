package com.appdev.intentattacks;

import android.os.Bundle;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        // Show MainFragment by default
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new MainFragment())
                .commit();
            // Optionally set the checked item if you add a Home entry
            // navigationView.setCheckedItem(R.id.nav_home);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        int id = item.getItemId();
        if (id == R.id.nav_flag1) {
            fragment = new Flag1Fragment();
        } else if (id == R.id.nav_flag2) {
            fragment = new Flag2Fragment();
        } else if (id == R.id.nav_flag3) {
            fragment = new Flag3Fragment();
        } else if (id == R.id.nav_flag4) {
            fragment = new Flag4Fragment();
        } else if (id == R.id.nav_flag5) {
            fragment = new Flag5Fragment();
        } else if (id == R.id.nav_flag6) {
            fragment = new Flag6Fragment();
        } else if (id == R.id.nav_flag7) {
            fragment = new Flag7Fragment();
        } else if (id == R.id.nav_home) {
            fragment = new MainFragment();
        }
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}