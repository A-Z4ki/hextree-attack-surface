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
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    private Map<Integer, Supplier<Fragment>> fragmentMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        // Initialize the fragment map
        fragmentMap = new HashMap<>();
        fragmentMap.put(R.id.nav_home, MainFragment::new);
        fragmentMap.put(R.id.nav_flag1, Flag1Fragment::new);
        fragmentMap.put(R.id.nav_flag2, Flag2Fragment::new);
        fragmentMap.put(R.id.nav_flag3, Flag3Fragment::new);
        fragmentMap.put(R.id.nav_flag4, Flag4Fragment::new);
        fragmentMap.put(R.id.nav_flag5, Flag5Fragment::new);
        fragmentMap.put(R.id.nav_flag6, Flag6Fragment::new);
        fragmentMap.put(R.id.nav_flag7, Flag7Fragment::new);
        fragmentMap.put(R.id.nav_flag8, Flag8Fragment::new);
        fragmentMap.put(R.id.nav_flag9, Flag9Fragment::new);
        fragmentMap.put(R.id.nav_flag13, Flag13Fragment::new);
        fragmentMap.put(R.id.nav_flag15, Flag15Fragment::new);
        fragmentMap.put(R.id.nav_flag16, Flag16Fragment::new);
        fragmentMap.put(R.id.nav_flag17, Flag17Fragment::new);
        fragmentMap.put(R.id.nav_flag18, Flag18Fragment::new);
        fragmentMap.put(R.id.nav_flag19, Flag19Fragment::new);
        fragmentMap.put(R.id.nav_flag20, Flag20Fragment::new);
        fragmentMap.put(R.id.nav_flag21, Flag21Fragment::new);
        fragmentMap.put(R.id.nav_flag22, Flag22Fragment::new);
        fragmentMap.put(R.id.nav_flag24, Flag24Fragment::new);
        fragmentMap.put(R.id.nav_flag25, Flag25Fragment::new);
        fragmentMap.put(R.id.nav_flag26, Flag26Fragment::new);
        fragmentMap.put(R.id.nav_flag27, Flag27Fragment::new);
        fragmentMap.put(R.id.nav_flag28, Flag28Fragment::new);
        fragmentMap.put(R.id.nav_flag29, Flag29Fragment::new);
        fragmentMap.put(R.id.nav_flag30, Flag30Fragment::new);
        fragmentMap.put(R.id.nav_flag31, Flag31Fragment::new);
        fragmentMap.put(R.id.nav_flag32, Flag32Fragment::new);
        fragmentMap.put(R.id.nav_flag33_1, Flag33_1Fragment::new);
        fragmentMap.put(R.id.nav_flag33_2, Flag33_2Fragment::new);
        fragmentMap.put(R.id.nav_flag34, Flag34Fragment::new);
        fragmentMap.put(R.id.nav_flag35, Flag35Fragment::new);
        fragmentMap.put(R.id.nav_flag36, Flag36Fragment::new);
        fragmentMap.put(R.id.nav_flag37, Flag37Fragment::new);

        // Show MainFragment by default
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new MainFragment())
                .commit();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.nav_flag10) {
            startActivity(new android.content.Intent(this, Flag10Activity.class));
            drawer.closeDrawer(GravityCompat.START);
            return true;
        }
        if (item.getItemId() == R.id.nav_flag11) {
            startActivity(new android.content.Intent(this, Flag11Activity.class));
            drawer.closeDrawer(GravityCompat.START);
            return true;
        }
        if (item.getItemId() == R.id.nav_flag12) {
            startActivity(new android.content.Intent(this, Flag12Activity.class));
            drawer.closeDrawer(GravityCompat.START);
            return true;
        }
        if (item.getItemId() == R.id.nav_flag14) {
            startActivity(new android.content.Intent(this, Flag14Activity.class));
            drawer.closeDrawer(GravityCompat.START);
            return true;
        }
        if (item.getItemId() == R.id.nav_flag23) {
            startActivity(new android.content.Intent(this, Flag23Activity.class));
            drawer.closeDrawer(GravityCompat.START);
            return true;
        }
        Supplier<Fragment> fragmentSupplier = fragmentMap.get(item.getItemId());
        if (fragmentSupplier != null) {
            Fragment fragment = fragmentSupplier.get();
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