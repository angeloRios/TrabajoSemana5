package com.codeneitor.mascotasdbmvp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.codeneitor.mascotasdbmvp.model.Mascota;

import java.util.ArrayList;

/**
 * Created by b41n on 9/01/19.
 */

public class BaseDatos extends SQLiteOpenHelper {

    private Context context;

    public BaseDatos(Context context) {
        super(context, ConstDB.DATABASE_NAME, null, ConstDB.DATABASE_VERSION);
        Log.i("DATABASE","Creación");
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryCrearTablaMascota = "CREATE TABLE " + ConstDB.TABLE_PETS + "(" +
                ConstDB.TABLE_PETS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ConstDB.TABLE_PETS_NOMBRE + " TEXT, " +
                ConstDB.TABLE_PETS_FOTO + " INTEGER" +
                ")";
        String queryCrearTablaLikesMascota = "CREATE TABLE " + ConstDB.TABLE_LIKES_PET + "(" +
                ConstDB.TABLE_LIKES_PET_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ConstDB.TABLE_LIKES_PET_ID_PET + " INTEGER, " +
                ConstDB.TABLE_LIKES_PET_NUMERO_LIKES + " INTEGER, " +
                "FOREIGN KEY (" + ConstDB.TABLE_LIKES_PET_ID_PET + ") " +
                "REFERENCES " + ConstDB.TABLE_PETS + "(" + ConstDB.TABLE_PETS_ID + ")" +
                ")";

        //Ejecutamos la consulta que crea la tabla Mascota
        db.execSQL(queryCrearTablaMascota);
        Log.i("DATABASE","Creación Tabla mascota");
        db.execSQL(queryCrearTablaLikesMascota);
        Log.i("DATABASE","Creación Tabla mascota_likes");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + ConstDB.TABLE_PETS);
        db.execSQL("DROP TABLE IF EXISTS " + ConstDB.TABLE_LIKES_PET);
        onCreate(db);
    }

    public ArrayList<Mascota> obtenerMascotasBD(){
        ArrayList<Mascota> mascotas = new ArrayList<>();

        String query = "SELECT * FROM " + ConstDB.TABLE_PETS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);




        while(cursor.moveToNext()){
            Mascota mascotaActual = new Mascota();
            mascotaActual.setId(cursor.getInt(0));
            mascotaActual.setNombre(cursor.getString(1));
            mascotaActual.setFoto(cursor.getInt(2));
            String queryLikes="SELECT COUNT("+ConstDB.TABLE_LIKES_PET_NUMERO_LIKES+") as likes "+
                    "FROM "+ConstDB.TABLE_LIKES_PET+
                    " WHERE "+ConstDB.TABLE_LIKES_PET_ID_PET+ "="+mascotaActual.getId();
            Cursor cursorLikes = db.rawQuery(queryLikes, null);
            if(cursorLikes.moveToNext()){
                mascotaActual.setLikes(cursorLikes.getInt(0));
            }else{
                mascotaActual.setLikes(0);
            }

            mascotas.add(mascotaActual);
            //mascotaActual.setLikes();
        }
        db.close();
        return mascotas;
    }

    public void insertarMascotaDB(ContentValues contentValues){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstDB.TABLE_PETS, null, contentValues);
        db.close();
    }

    public void insertarLikeMascotaDB(ContentValues contentValues){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstDB.TABLE_LIKES_PET, null, contentValues);
        db.close();
    }

    public int obtenerLikesMascotaDB(Mascota mascota){
        int likes = 0;
        String query ="SELECT COUNT("+ConstDB.TABLE_LIKES_PET_NUMERO_LIKES+")" +
                " FROM " + ConstDB.TABLE_LIKES_PET+
                " WHERE "+ConstDB.TABLE_LIKES_PET_ID_PET + "="+mascota.getId();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToNext()){
            likes = cursor.getInt(0);
        }
        db.close();
        return likes;
    }

}