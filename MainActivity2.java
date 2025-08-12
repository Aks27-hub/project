package com.example.project1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

public class MainActivity2 extends AppCompatActivity {

    Menu menu;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        menu = findViewById(R.id.nav_menu);

        toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//                this, drawerLayout, toolbar,
//                R.string.navigation_drawer_open,
//                R.string.navigation_drawer_close);

//        drawerLayout.addDrawerListener(toggle);
//        toggle.syncState();

        // Handle item clicks
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                if (id == R.id.bingo) {
                    Intent intent = new Intent(MainActivity2.this, MainActivity3.class);
                    startActivity(intent);
                } else if (id == R.id.music) {
                    Intent intent = new Intent(MainActivity2.this, MainActivity4.class);
                    startActivity(intent);
                } else if (id == R.id.color_number) {
                    Intent intent = new Intent(MainActivity2.this, MainActivity5.class);
                    startActivity(intent);
                } else if (id == R.id.scramble) {
                    Intent intent = new Intent(MainActivity2.this, MainActivity6.class);
                    startActivity(intent);
                } else if (id == R.id.peace) {
                    Intent intent = new Intent(MainActivity2.this, MainActivity7.class);
                    startActivity(intent);
                }

                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });

    }


//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        if (item.getItemId() == R.id.bingo) {
//            Intent intent1 = new Intent(MainActivity2.this, MainActivity3.class);
//            startActivity(intent1);
//            return true;
//        }
//
//        else if (item.getItemId() == R.id.music) {
//            Intent intent2 = new Intent(MainActivity2.this, MainActivity4.class);
//            startActivity(intent2);
//            return true;
//        }
//
//        else if (item.getItemId() == R.id.color_number) {
//            Intent intent3 = new Intent(MainActivity2.this, MainActivity5.class);
//            startActivity(intent3);
//            return true;
//        }
//
//        else {
//            return super.onOptionsItemSelected(item);
//        }
//    }
}