package com.gualberto.vive100;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AyudanteBaseDeDatos extends SQLiteOpenHelper {

    private static final String NOMBRE_BASE_DATOS = "vivedb", NOMBRE_TABLA_VENTAS = "ventas", NOMBRE_TABLA_PRECIOS = "precios";
    private static final int VERSION_BASE_DATOS = 1;

    public AyudanteBaseDeDatos(Context context) {
        super(context, NOMBRE_BASE_DATOS, null, VERSION_BASE_DATOS);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(String.format("CREATE TABLE IF NOT EXISTS %s(id integer primary key autoincrement, nombre string, cantvp int, cantvg int, cantsav int,cantzen int, cantsp int, cantdvp int, cantdvg int, cantdsav int, cantdzen int, cantdsp int, canthielo int, dialoco int)", NOMBRE_TABLA_VENTAS));
        db.execSQL(String.format("CREATE TABLE IF NOT EXISTS %s(idprecio integer , preciopq int, preciogr int, preciosav int, preciozen int, preciosp int, costopq int,costogr int, costosav int, costozen int, costosp int, preciohielo int)", NOMBRE_TABLA_PRECIOS));

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
