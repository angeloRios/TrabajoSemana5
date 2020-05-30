package com.codeneitor.mascotasdbmvp.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.codeneitor.mascotasdbmvp.MascotaDetalle;
import com.codeneitor.mascotasdbmvp.R;
import com.codeneitor.mascotasdbmvp.model.Mascota;

import java.util.ArrayList;

/**
 * Created by b41n on 7/01/19.
 */

public class PerfilAdapter extends RecyclerView.Adapter<PerfilAdapter.PerfilViewHolder>{
    ArrayList<Mascota> mascotas;
    Activity activity;

    public PerfilAdapter(ArrayList<Mascota> mascotas, Activity activity){
        this.mascotas = mascotas;
        this.activity = activity;
    }

    @Override
    public PerfilViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.cardview_grid_mascota, parent, false);
        return new PerfilViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PerfilViewHolder perfilViewHolder, int position) {
        final Mascota mascota = mascotas.get(position);
        perfilViewHolder.imvMascota.setImageResource(mascota.getFoto());
        //perfilViewHolder.tvMascotaNombre.setText(mascota.getNombre());
        perfilViewHolder.tvMascotaLikes.setText(Integer.toString(mascota.getLikes()));

        perfilViewHolder.imvMascota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, MascotaDetalle.class);

                intent.putExtra(activity.getResources().getString(R.string.pfoto), mascota.getFoto());
                intent.putExtra(activity.getResources().getString(R.string.pnombre), mascota.getNombre());
                intent.putExtra(activity.getResources().getString(R.string.plikes), mascota.getLikes());

                Log.i("FOTO",":"+mascota.getFoto());
                Log.i("NOMBRE",":"+mascota.getNombre());
                Log.i("LIKES",":"+mascota.getLikes());
                activity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mascotas.size();
    }

    public static class PerfilViewHolder extends RecyclerView.ViewHolder{

        private ImageView imvMascota;
        //private TextView tvMascotaNombre;
        private TextView tvMascotaLikes;

        public PerfilViewHolder(View itemView) {
            super(itemView);
            imvMascota = (ImageView) itemView.findViewById(R.id.imvMascota);
            //tvMascotaNombre = (TextView) itemView.findViewById(R.id.tvMascotaNombre);
            tvMascotaLikes =(TextView) itemView.findViewById(R.id.tvMascotaLikes);
        }
    }
}
