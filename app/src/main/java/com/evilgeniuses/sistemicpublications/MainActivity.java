package com.evilgeniuses.sistemicpublications;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
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
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //Declaración de Componentes del Layout
    private Toolbar                 MainToolbar;
    private ViewPager               MainViewPager;
    private TabLayout               MainTabLayout;
    private FloatingActionButton    FABAdd;

    private FirebaseAuth    mAuth;
    private FirebaseUser    User;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth           =   FirebaseAuth.getInstance();

        MainViewPager  =   findViewById(R.id.MainViewPager);
        MainTabLayout  =   findViewById(R.id.MainTabLayout);
        MainToolbar     =   findViewById(R.id.MainToolbar);
        MainToolbar.setTitle("");
        FABAdd          =   findViewById(R.id.FABAdd);

        //MANEJO DE LA TOOLBAR
        setSupportActionBar(MainToolbar);//Se cambia el Action bar por nuestra toolbar
        MainToolbar.setTitle(R.string.MainToolbarName);
        MainToolbar.setTitleTextColor(getResources().getColor(R.color.white));
        //MANEJO DEL VIEWPAGER Y LOS TABS
        setupViewPager(MainViewPager);//Se configura el ViewPager
        MainTabLayout.setupWithViewPager(MainViewPager);


        FABAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent  intent  =   new Intent(MainActivity.this,AdministratorActivity.class);
                startActivity(intent);
            }
        });

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
                Intent intent   =   new Intent(MainActivity.this, IniciarSesionActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        User = mAuth.getCurrentUser();
        if (User !=  null){
            FABAdd.setVisibility(View.VISIBLE);
        }else{
            FABAdd.setVisibility(View.GONE);
        }
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new BooksFragment(), "BOOK");
        adapter.addFrag(new ChaptersFragment(), "CHAPTER");
        adapter.addFrag(new JournalsFragment(), "JOURNAL");
        adapter.addFrag(new ConferencesFragment(), "CONFERENCE");
        adapter.addFrag(new SoftwareFragment(), "SOFTWARE");
        adapter.addFrag(new PrototypesFragment(), "PROTOTYPE");
        adapter.addFrag(new ThesisFragment(), "THESIS");
        adapter.addFrag(new AuthorFragment(), "AUTHOR");
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
