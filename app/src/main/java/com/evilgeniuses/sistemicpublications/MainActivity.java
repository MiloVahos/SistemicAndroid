package com.evilgeniuses.sistemicpublications;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    //Declaración de Componentes del Layout
    private Toolbar                 MainToolbar;
    private FloatingActionButton    FABAdd;

    private FirebaseAuth    mAuth;
    private FirebaseUser    User;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth           =   FirebaseAuth.getInstance();

        MainToolbar     =   findViewById(R.id.MainToolbar);
        MainToolbar.setTitle("");
        FABAdd          =   findViewById(R.id.FABAdd);

        //MANEJO DE LA TOOLBAR
        setSupportActionBar(MainToolbar);//Se cambia el Action bar por nuestra toolbar
        MainToolbar.setTitle(R.string.MainToolbarName);
        MainToolbar.setTitleTextColor(getResources().getColor(R.color.white));


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

}
