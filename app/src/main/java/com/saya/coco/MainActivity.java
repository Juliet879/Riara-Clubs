package com.saya.coco;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.animation.ArgbEvaluator;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;
import com.saya.coco.events.EventsFragment;
import com.saya.coco.menu.AboutUs;
import com.saya.coco.menu.NewPassword;
import com.saya.coco.menu.Notifications;
import com.saya.coco.menu.Profile;
import com.saya.coco.menu.VerifyEmail;
import com.saya.coco.menu.VerifyPhone;
import com.tbuonomo.viewpagerdotsindicator.SpringDotsIndicator;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    //Hooks
    ChipNavigationBar chipNavBar;

    ViewPager vp;
    Adapter a;
    List<Model> models;
    Integer[] colors = null;
    ArgbEvaluator argbEvaluator = new ArgbEvaluator();

    NavigationView navigationView;

    FloatingActionButton fab1, fab2, fab3;

    DrawerLayout dl;

    Button btn;

    SpringDotsIndicator springDotsIndicator;

    public int position0;
    public int position1;
    public int position2;
    public int position3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Bottom navigation bar with home, events, and payments
        chipNavBar = findViewById(R.id.nav_bar);

        //Default item
        chipNavBar.setItemSelected(R.id.home, true);


        //Replace Home fragment with Main Activity
        getSupportFragmentManager().beginTransaction().replace(R.id.frag_container, new HomeFragment()).commit();


        bottomMenu();

        //sideMenu();

        models = new ArrayList<>();
        models.add(new Model(R.drawable.floral, R.drawable.beyoulogo, "BE YOU!", "Be who you are truly meant to be", "Info", "Join"));
        models.add(new Model(R.drawable.bg_music, R.drawable.musiclogo, "MUSIC", "Don't enjoy it alone", "Info", "Join"));
        models.add(new Model(R.drawable.images, R.drawable.redcrosslogo, "RED CROSS", "Save a life", "Info", "Join"));
        models.add(new Model(R.drawable.pexels, R.drawable.beyoulogo, "RUBIIC", "Where innovation meets business", "Info", "Join"));


        a = new Adapter(models, this);

        vp = findViewById(R.id.viewPager);
        vp.setAdapter(a);
        vp.setPadding(25, 150, 25, 0);

        Intent i = getIntent();
        if (vp.getCurrentItem() == 0) {
            position0 = i.getIntExtra("vp_position", 0);
        }
        else if (vp.getCurrentItem() == 1) {
            position1 = i.getIntExtra("vp_position", 1);
        }
        else if (vp.getCurrentItem() == 2) {
            position2 = i.getIntExtra("vp_position", 2);
        }
        else if (vp.getCurrentItem() == 3) {
            position3 = i.getIntExtra("vp_position", 3);
        }


        //Used for setting different background color for the pages in viewpager.
        //The same color is used for background color for all four pages
        colors = new Integer[] {
                ContextCompat.getColor(this, R.color.color1),
                ContextCompat.getColor(this, R.color.color1),
                ContextCompat.getColor(this, R.color.color1),
                ContextCompat.getColor(this, R.color.color1),
        };


        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position < (a.getCount() - 1) && position < (colors.length - 1)) {
                    vp.setBackgroundColor(

                            (Integer) argbEvaluator.evaluate(
                                    positionOffset,
                                    colors[position],
                                    colors[position + 1]));
                } else {
                    vp.setBackgroundColor(colors[colors.length - 1]);

                }

            }

            @Override
            public void onPageSelected(int position) {


            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        //Floating action buttons
        fab1 = findViewById(R.id.fabFacebook);
        fab2 = findViewById(R.id.fabTwitter);
        fab3 = findViewById(R.id.fabIG);

        //Line 257. Open url on click of respective floating action buttons
        fab1.setOnClickListener(this);
        fab2.setOnClickListener(this);
        fab3.setOnClickListener(this);


        dl = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.nav_view);
        btn = findViewById(R.id.menu_icon);

        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(item -> {
            //Do something on click of each item
            sideMenu(item);
            return true;
        });


        //Open navigation drawer on click of menu icon
        btn.setOnClickListener(view -> dl.openDrawer(GravityCompat.START));


        springDotsIndicator = findViewById(R.id.dots_indicator);
        springDotsIndicator.setViewPager(vp);

        //Change color of musiclogo
        Drawable bg = ContextCompat.getDrawable(this, R.drawable.musiclogo);
        DrawableCompat.setTint(Objects.requireNonNull(bg), ContextCompat.getColor(this, R.color.musicLogo_tint));

        //Change color of redcrosslogo
        Drawable bg1 = ContextCompat.getDrawable(this, R.drawable.redcrosslogo);
        DrawableCompat.setTint(Objects.requireNonNull(bg1), ContextCompat.getColor(this, R.color.redCrossLogo_tint));

    }

    //Navigation drawer menu items on click
    private void sideMenu(MenuItem item) {
            if (item.getItemId() == R.id.profile) {
                Intent i = new Intent(MainActivity.this, Profile.class);
                startActivity(i);
            }

            else if (item.getItemId() == R.id.updatePass) {
                Intent i2 = new Intent(MainActivity.this, NewPassword.class);
                startActivity(i2);
            }

            else if (item.getItemId() == R.id.info) {
                Intent i3 = new Intent(MainActivity.this, AboutUs.class);
                startActivity(i3);
            }

            else if (item.getItemId() == R.id.notifications) {
                Intent intent = new Intent(MainActivity.this, Notifications.class);
                startActivity(intent);
            }

            else if (item.getItemId() == R.id.verifyPhone) {
                Intent i5 = new Intent(MainActivity.this, VerifyPhone.class);
                startActivity(i5);
            }


            else if (item.getItemId() == R.id.logOut) {
                FirebaseAuth.getInstance().signOut();
                Intent i7 = new Intent(MainActivity.this, LogIn.class);
                startActivity(i7);
            }

    }

    private void bottomMenu() {
        chipNavBar.setOnItemSelectedListener(i -> {
            Fragment fragment = null;

            if (i == R.id.home) {
                fragment = new HomeFragment();
            }

            else if (i == R.id.events) {
                fragment = new EventsFragment();
            }

            else if (i == R.id.payments) {
                fragment = new PaymentsFragment();
            }

            /*switch (i) {
                case R.id.home:
                    fragment = new HomeFragment();
                    break;

                case R.id.events:
                    fragment = new EventsFragment();
                    break;

                case R.id.payments:
                    fragment = new PaymentsFragment();
                    break;

            }*/

            assert fragment != null;
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frag_container, fragment)
                    .commit();

        });
    }

    //Set status bar color to of Events Fragment
    public void updateStatusBarColorEvents(String color) {
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.parseColor(color));
    }

    //Set status bar color to of Home Fragment
    public void updateStatusBarColorHome(String color) {
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.parseColor(color));
    }

    //Set status bar color to of Payments Fragment
    public void updateStatusBarColorPayments(String color) {
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.parseColor(color));
    }


    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.fabFacebook) {
            String url1 = "https://www.facebook.com/";
            Intent i1;
            i1 = new Intent(Intent.ACTION_VIEW);
            i1.setData(Uri.parse(url1));
            startActivity(i1);
        }

        else if (view.getId() == R.id.fabTwitter) {
            String url2 = "https://www.twitter.com/";
            Intent i2;
            i2 = new Intent(Intent.ACTION_VIEW);
            i2.setData(Uri.parse(url2));
            startActivity(i2);
        }

        else if (view.getId() == R.id.fabIG) {
            String url3 = "https://www.instagram.com/";
            Intent i3;
            i3 = new Intent(Intent.ACTION_VIEW);
            i3.setData(Uri.parse(url3));
            startActivity(i3);
        }

        //Alternative switch case for above code
        /*switch (view.getId()) {
            case R.id.fabFacebook:
                String url1 = "https://www.facebook.com/";
                Intent i1;
                i1 = new Intent(Intent.ACTION_VIEW);
                i1.setData(Uri.parse(url1));
                startActivity(i1);
                break;

            case R.id.fabTwitter:
                String url2 = "https://www.twitter.com/";
                Intent i2;
                i2 = new Intent(Intent.ACTION_VIEW);
                i2.setData(Uri.parse(url2));
                startActivity(i2);
                break;

            case R.id.fabIG:
                String url3 = "https://www.instagram.com/";
                Intent i3;
                i3 = new Intent(Intent.ACTION_VIEW);
                i3.setData(Uri.parse(url3));
                startActivity(i3);
                break;
        }*/
    }
}