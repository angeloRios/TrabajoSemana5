package com.codeneitor.mascotasdbmvp.db;

/**
 * Created by b41n on 9/01/19.
 */

public final class ConstDB {

    public static final String DATABASE_NAME = "mascotas";
    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_PETS           = "mascota";
    public static final String TABLE_PETS_ID        = "id";
    public static final String TABLE_PETS_NOMBRE    = "nombre";
    public static final String TABLE_PETS_FOTO      = "foto";

    public static final String TABLE_LIKES_PET              = "mascota_likes";
    public static final String TABLE_LIKES_PET_ID           = "id";
    public static final String TABLE_LIKES_PET_ID_PET       = "id_mascota";
    public static final String TABLE_LIKES_PET_NUMERO_LIKES = "numero_likes";
}
