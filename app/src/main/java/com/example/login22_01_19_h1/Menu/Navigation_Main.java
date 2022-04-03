package com.example.login22_01_19_h1.Menu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.login22_01_19_h1.LoginSingup.Login_MySql;
import com.example.login22_01_19_h1.R;
import com.example.login22_01_19_h1.SharedPrefManger;
import com.google.android.material.navigation.NavigationView;


public class Navigation_Main extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_main);

        if(!SharedPrefManger.getInstance(this).isLoggedIn()){
            finish();
            startActivity(new Intent(this , Login_MySql.class));

        }

        NavigationView navigationView2 = findViewById(R.id.nav_view);
        View headerView = navigationView2.getHeaderView(0);
        TextView txUserName = (TextView) headerView.findViewById(R.id.UserName);
        TextView txUserEmail = (TextView) headerView.findViewById(R.id.UserEmail);
        TextView txUserId = (TextView) headerView.findViewById(R.id.UserId);
        txUserName.setText(SharedPrefManger.getInstance(this).getUserName() + "");
        txUserEmail.setText(SharedPrefManger.getInstance(this).getUserEmail() + "");
        txUserId.setText(SharedPrefManger.getInstance(this).getUserId() + "");
        Log.println(Log.ASSERT, "NAV", SharedPrefManger.getInstance(this).getUserId() + ":");




        Toolbar toolbar1 = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar1);

//
//        TextView txUserName = (TextView) findViewById(R.id.UserName);
//        txUserName.setText(SharedPrefManger.getInstance(this).getUserName().toString());
//
//        TextView txUserEmai = (TextView) findViewById(R.id.UserEmail);
//        txUserEmai.setText(SharedPrefManger.getInstance(this).getUserEmail().toString());

//        Log.println(Log.ASSERT, "NAV",SharedPrefManger.getInstance(this).getUserEmail().toString());

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar1,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new HomeFragment()).commit();
            navigationView.setCheckedItem(R.id.home);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_person:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ProfileFragment()).commit();
                break;
            case R.id.nav_Reservation:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new BookingFragment()).commit();
                break;
            case R.id.nav_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new HomeFragment()).commit();
                break;
            case R.id.nav_Histroy:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new HistoryFragment()).commit();

                break;
            case R.id.nav_Centers:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new CentersFragment()).commit();

                break;
            case R.id.Logout:
//                Toast.makeText(this, "Share", Toast.LENGTH_SHORT).show();
                SharedPrefManger.getInstance(this).Logout();
                Intent intent1 = new Intent(this, Login_MySql.class);
                startActivity(intent1);

                break;

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



    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

}