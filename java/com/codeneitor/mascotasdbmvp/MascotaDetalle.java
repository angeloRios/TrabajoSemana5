package com.codeneitor.mascotasdbmvp;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MascotaDetalle extends AppCompatActivity {

    ImageView imvMascota;
    TextView tvMascotaNombre;
    TextView tvMascotaLikes;
    @Override
    public void onCreate(Bundle savedInstanceState){
        int fotoId = 0;
        String nombre = null;
        int likes =0;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalle_mascota);
        //Agregamos nuestro ToolBar
        Toolbar toolbar = (Toolbar) findViewById(R.id.miActionBar);
        setSupportActionBar(toolbar);
        // Eliminamos el título por defecto del Toolbar
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        Bundle parametros = getIntent().getExtras();
        fotoId = parametros.getInt(getResources().getString(R.string.pfoto));
        likes = parametros.getInt(getResources().getString(R.string.plikes));

        imvMascota = (ImageView) findViewById(R.id.imvMascota);
        tvMascotaNombre = (TextView) findViewById(R.id.tvMascotaNombre);
        tvMascotaLikes = (TextView) findViewById(R.id.tvMascotaLikes);

        //Verificamos si el nombre viene vacio
        // lo que podríamos hacer es traer como parámetro adicional la clase que ha iniciado esta
        // actividad para así volver a dicha actividad.
        // Esto en caso de disponer de diferentes pantallas con diferente interface y elementos.
        if(parametros.getString("nombre")!=null){
            nombre = parametros.getString(getResources().getString(R.string.pnombre));
            tvMascotaNombre.setText(nombre);
            Log.i("NOMBRE",""+nombre);
        }
        imvMascota.setImageResource(fotoId);
        Log.i("FOTO",""+fotoId);
        tvMascotaLikes.setText(""+likes);
        Log.i("LIKES",""+likes);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            Intent intent = new Intent(MascotaDetalle.this,MainActivity.class);
            startActivity(intent);
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_mascota, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.mDelMascota:
                try {
                    Toast.makeText(this, R.string.mDelete, Toast.LENGTH_LONG).show();
                } catch (Resources.NotFoundException e) {
                    Log.i("EROOR",getResources().getString(R.string.error011));
                }
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


}
