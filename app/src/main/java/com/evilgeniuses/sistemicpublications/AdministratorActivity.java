package com.evilgeniuses.sistemicpublications;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class AdministratorActivity extends AppCompatActivity {

    private Toolbar AdminToolbar;
    private ViewPager AdminViewPager;
    private TabLayout AdminTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrator);

        AdminViewPager  =   findViewById(R.id.AdminViewPager);
        AdminTabLayout  =   findViewById(R.id.AdminTabLayout);
        AdminToolbar    =   findViewById(R.id.AdminToolbar);
        AdminToolbar.setTitle("");

        //MANEJO DE LA TOOLBAR
        setSupportActionBar(AdminToolbar);//Se cambia el Action bar por nuestra toolbar
        AdminToolbar.setTitle(R.string.AdminToolbarName);
        AdminToolbar.setTitleTextColor(getResources().getColor(R.color.white));
        //MANEJO DEL VIEWPAGER Y LOS TABS
        setupViewPager(AdminViewPager);//Se configura el ViewPager
        AdminTabLayout.setupWithViewPager(AdminViewPager);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.Login:
                Intent intent   =   new Intent(AdministratorActivity.this, IniciarSesionActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new AddAuthorFragment(), "AUTHOR");
        adapter.addFrag(new AddBookFragment(), "BOOK");
        adapter.addFrag(new AddChapterFragment(), "CHAPTER");
        adapter.addFrag(new AddJournalFragment(), "JOURNAL");
        adapter.addFrag(new AddConferenceFragment(), "CONFERENCE");
        adapter.addFrag(new AddSoftwareFragment(), "SOFTWARE");
        adapter.addFrag(new AddPrototypeFragment(), "PROTOTYPE");
        adapter.addFrag(new AddThesisFragment(), "THESIS");
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(7);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }


}
