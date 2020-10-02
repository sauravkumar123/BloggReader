package com.example.tech;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Main2Activity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    androidx.appcompat.widget.Toolbar toolbar;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;
    RecyclerView recyclerView;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        getData();
        setuptoolbar();

    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setuptoolbar()
    {
        drawerLayout=(DrawerLayout)findViewById(R.id.drawer);
        toolbar= (androidx.appcompat.widget.Toolbar) findViewById(R.id.toolbar);
        navigationView=(NavigationView)findViewById(R.id.navigationar);
        recyclerView=(RecyclerView) findViewById(R.id.recycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        setSupportActionBar(toolbar);
        actionBarDrawerToggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.app_name,R.string.app_name);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId())
                {
                    case R.id.home:
                        Toast.makeText(Main2Activity.this,"Home",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.Site:
                        Toast.makeText(Main2Activity.this,"Site",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.serach:
                        Toast.makeText(Main2Activity.this,"Search",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.setting:
                        Toast.makeText(Main2Activity.this,"Settings",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.About:
                        Toast.makeText(Main2Activity.this,"About",Toast.LENGTH_SHORT).show();
                        break;
                }

                return false;
            }
        });


    }
    private  void getData()
    {
        Call<PostList> postList=BloggerAPi.getservice().getPostList();
        postList.enqueue(new Callback<PostList>() {
            @Override
            public void onResponse(Call<PostList> call, Response<PostList> response) {
                PostList list=response.body();
                recyclerView.setAdapter(new PostAdapter(Main2Activity.this,list.getItems()));
                Toast.makeText(Main2Activity.this, "WELCOME", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<PostList> call, Throwable t) {
                Toast.makeText(Main2Activity.this, "Error Occured", Toast.LENGTH_SHORT).show();

            }
        });
    }

}
