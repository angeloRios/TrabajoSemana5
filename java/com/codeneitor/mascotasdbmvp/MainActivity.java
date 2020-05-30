package com.codeneitor.mascotasdbmvp;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.codeneitor.mascotasdbmvp.adapter.PageAdapter;
import com.codeneitor.mascotasdbmvp.view.fragment.PerfilFragment;
import com.codeneitor.mascotasdbmvp.view.fragment.RecyclerViewFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {



    private static final String KEY_EXTRA_NAME = "name";
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Agregamos nuestro ToolBar
        toolbar = (Toolbar) findViewById(R.id.miActionBar);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        Log.e("MainActivity", "onCreate");


        if(toolbar!= null){
            setSupportActionBar(toolbar);
            // Remove default title text
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        setUpViewPager();

    }

    private ArrayList<Fragment> agregarFragments(){
        Log.e("MainActivity", "agregarFragments");
        ArrayList<Fragment> fragments = new ArrayList<>();

        fragments.add(new RecyclerViewFragment());
        fragments.add(new PerfilFragment());

        return fragments;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_opciones, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.mFavoritos:
                Intent favs = new Intent(MainActivity.this, MascotaFavoritos.class);
                startActivity(favs);
                break;
            case R.id.mContacto:
                Intent contact = new Intent(MainActivity.this, ContactoActivity.class);
                startActivity(contact);
                break;

            case R.id.mAbout:
                Intent about = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(about);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setUpViewPager(){
        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(), agregarFragments()));
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.huella_perro);
        tabLayout.getTabAt(1).setIcon(R.drawable.bone_yellow);
    }

}
