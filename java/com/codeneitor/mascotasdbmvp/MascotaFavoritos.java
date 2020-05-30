package com.codeneitor.mascotasdbmvp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.codeneitor.mascotasdbmvp.adapter.FavoritosAdapter;
import com.codeneitor.mascotasdbmvp.model.Mascota;

import java.util.ArrayList;

public class MascotaFavoritos extends AppCompatActivity {

    ArrayList<Mascota> mascotas ;
    private RecyclerView listaMascotas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favoritos_mascota);
        //Agregamos nuestro ToolBar
        Toolbar toolbar = (Toolbar) findViewById(R.id.miActionBar);
        setSupportActionBar(toolbar);
        //Agregamos soporte al botón de navegación hacia atrás
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //Agregamos la huella en nuestro Toolbar
        getSupportActionBar().setIcon(R.drawable.huella_perro);
        // Remove default title text
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        listaMascotas = (RecyclerView) findViewById(R.id.rvMascotas);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        listaMascotas.setLayoutManager(llm);

        iniciarListaMascotas();
        iniciarAdaptador();

    }

    public FavoritosAdapter adaptador;
    public void iniciarAdaptador(){
        adaptador = new FavoritosAdapter(mascotas, this);
        //MascotaAdaptador adaptador = new MascotaAdaptador(mascotas);
        listaMascotas.setAdapter(adaptador);
    }

    public void iniciarListaMascotas(){
        mascotas = new ArrayList<>();
        mascotas.add(new Mascota(R.drawable.garfield,"Huella",200));
        mascotas.add(new Mascota(R.drawable.gyo,"Hueso",145));
        mascotas.add(new Mascota(R.drawable.gyo2,"huella",124));
        mascotas.add(new Mascota(R.drawable.oddie,"Favoritos",298));
        mascotas.add(new Mascota(R.drawable.gato,"Huella",234));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_favoritos, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.mContacto:
                Intent contact = new Intent(MascotaFavoritos.this, ContactoActivity.class);
                startActivity(contact);
                break;

            case R.id.mAbout:
                Intent about = new Intent(MascotaFavoritos.this, AboutActivity.class);
                startActivity(about);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
