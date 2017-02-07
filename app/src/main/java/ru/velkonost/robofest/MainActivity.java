package ru.velkonost.robofest;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import adapters.MainTabsFragmentAdapter;

import static managers.Initializations.changeActivityCompat;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Button btnDay1;
    private Button btnDay2;

    private ImageView imageMap, imageGraphic, imageDay;
    private int day;

    private LinearLayout dayImage;

    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Главная");
        setSupportActionBar(toolbar);

//        btnDay1 = (Button) findViewById(R.id.buttonDay1);
//        btnDay2 = (Button) findViewById(R.id.buttonDay2);
//
//        dayImage = (LinearLayout) findViewById(R.id.dayImage);
//
//        imageMap = (ImageView) findViewById(R.id.imageMap);
//        imageGraphic = (ImageView) findViewById(R.id.imageGraphic);
//        imageDay = (ImageView) findViewById(R.id.imageDay);

//        Display display = getWindowManager().getDefaultDisplay();
//        Point sizeP = new Point();
//        display.getSize(sizeP);
//        int width = sizeP.x;
//        int height = sizeP.y;

//        Glide.with(MainActivity.this)
//                .load("http://www.robofestomsk.ru/images/robofestomsk_sheme.jpg")
//                .placeholder(R.mipmap.ic_launcher)
//                .override(width, 200)
//                .into(imageMap);


        initTabs();

        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        final NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public void openMap (View view) {
        Intent intent = new Intent(MainActivity.this, FullScreenPhotoActivity.class);
        intent.putExtra("Photo", 1);
        MainActivity.this.startActivity(intent);
    }

    private void initTabs() {
        viewPager = (ViewPager) findViewById(R.id.viewPagerMain);

        MainTabsFragmentAdapter adapter
                = new MainTabsFragmentAdapter(this, getSupportFragmentManager());

        viewPager.setAdapter(adapter);


        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);

        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);

    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        Intent nextIntent = null;

        int id = item.getItemId();

        if (id == R.id.registration) {

            nextIntent =
                    new Intent("ru.velkonost.Browser");
            nextIntent.setData(Uri.parse(
                    "https://docs.google.com/forms/d/e/1FAIpQLSfg7od0RMlO5CCML1MZB2dxVnS-3KG8rqTGZ2hitnVY2tdpxg/formResponse"
            ));

        } else if (id == R.id.competition) {
            nextIntent = new Intent(MainActivity.this, CompetitionActivity.class);
        } else if (id == R.id.about) {
            nextIntent = new Intent(MainActivity.this, AboutActivity.class);
        }

        final Intent finalNextIntent = nextIntent;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                /**
                 * Обновляет страницу.
                 * {@link Initializations#changeActivityCompat(Activity, Intent)}
                 * */
                changeActivityCompat(MainActivity.this, finalNextIntent);
            }
        }, 350);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
