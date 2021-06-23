package com.gualberto.vive100.Controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.gualberto.vive100.AyudanteBaseDeDatos;
import com.gualberto.vive100.Modelo.Venta;

import java.util.ArrayList;

public class VentasController {

    private final AyudanteBaseDeDatos ayudanteBaseDeDatos;
    private final String NOMBRE_TABLA = "ventas";

    public VentasController(Context contexto) {
        ayudanteBaseDeDatos = new AyudanteBaseDeDatos(contexto);
    }

    public ArrayList<Venta> obtenerVenta() {
        ArrayList<Venta> ventas = new ArrayList<>();
        SQLiteDatabase baseDeDatos = ayudanteBaseDeDatos.getReadableDatabase();
        String[] columnasAConsultar = {"nombre", "cantvp", "cantvg", "cantsav", "cantzen", "cantsp", "cantdvp", "cantdvg", "cantdsav", "cantdzen", "cantdsp", "canthielo", "dialoco", "id"};
        Cursor cursor = baseDeDatos.query(NOMBRE_TABLA, columnasAConsultar, null, null, null, null, null);
        if (cursor == null) {
            return ventas;
        }
        if (!cursor.moveToFirst()) return ventas;

        do {
            String nombreObtenidoBD = cursor.getString(0);
            int cantvpObtenidoBD = cursor.getInt(1);
            int cantvgObtenidoBD = cursor.getInt(2);
            int cantsavObtenidoBD = cursor.getInt(3);
            int cantzenObtenidoBD = cursor.getInt(4);
            int cantspObtenidoBD = cursor.getInt(5);
            int cantdvpObtenidoBD = cursor.getInt(6);
            int cantdvgObtenidoBD = cursor.getInt(7);
            int cantdsavObtenidoBD = cursor.getInt(8);
            int cantdzenObtenidoBD = cursor.getInt(9);
            int cantdspObtenidoBD = cursor.getInt(10);
            int canthieloObtenidoBD = cursor.getInt(11);
            int dialocoObtenidoBD = cursor.getInt(12);
            long idVenta = cursor.getLong(13);
            Venta ventaObtenidaBD = new Venta(nombreObtenidoBD, cantvpObtenidoBD, cantvgObtenidoBD, cantsavObtenidoBD, cantzenObtenidoBD, cantspObtenidoBD, cantdvpObtenidoBD, cantdvgObtenidoBD, cantdsavObtenidoBD, cantdzenObtenidoBD, cantdspObtenidoBD, canthieloObtenidoBD, dialocoObtenidoBD, idVenta);
            ventas.add(ventaObtenidaBD);
        } while (cursor.moveToNext());
        cursor.close();
        return ventas;
    }

    public long nuevaVenta(Venta venta) {
        SQLiteDatabase baseDeDatos = ayudanteBaseDeDatos.getWritableDatabase();
        ContentValues valoresParaInsertar = new ContentValues();
        valoresParaInsertar.put("nombre", venta.getNombre());
        valoresParaInsertar.put("cantvp", venta.getCantvp());
        valoresParaInsertar.put("cantvg", venta.getCantvg());
        valoresParaInsertar.put("cantsav", venta.getCantsav());
        valoresParaInsertar.put("cantzen", venta.getCantzen());
        valoresParaInsertar.put("cantsp", venta.getCantsp());
        valoresParaInsertar.put("cantdvp", venta.getCantdvp());
        valoresParaInsertar.put("cantdvg", venta.getCantdvg());
        valoresParaInsertar.put("cantdsav", venta.getCantdsav());
        valoresParaInsertar.put("cantdzen", venta.getCantdzen());
        valoresParaInsertar.put("cantdsp", venta.getCantdsp());
        valoresParaInsertar.put("canthielo", venta.getCanthielo());
        valoresParaInsertar.put("dialoco", venta.getDialoco());

        return baseDeDatos.insert(NOMBRE_TABLA, null, valoresParaInsertar);
    }

    public int guardarCambios(Venta ventaEditada) {
        SQLiteDatabase baseDeDatos = ayudanteBaseDeDatos.getWritableDatabase();
        ContentValues valoresParaActualizar = new ContentValues();
        valoresParaActualizar.put("nombre", ventaEditada.getNombre());
        valoresParaActualizar.put("cantvp", ventaEditada.getCantvp());
        valoresParaActualizar.put("cantvg", ventaEditada.getCantvg());
        valoresParaActualizar.put("cantsav", ventaEditada.getCantsav());
        valoresParaActualizar.put("cantzen", ventaEditada.getCantzen());
        valoresParaActualizar.put("cantsp", ventaEditada.getCantsp());
        valoresParaActualizar.put("cantdvp", ventaEditada.getCantdvp());
        valoresParaActualizar.put("cantdvg", ventaEditada.getCantdvg());
        valoresParaActualizar.put("cantdsav", ventaEditada.getCantdsav());
        valoresParaActualizar.put("cantdzen", ventaEditada.getCantdzen());
        valoresParaActualizar.put("cantdsp", ventaEditada.getCantdsp());
        valoresParaActualizar.put("canthielo", ventaEditada.getCanthielo());

        String campoParaActualizar = "id = ?";
        String[] argumentosParaActualizar = {String.valueOf(ventaEditada.getId())};
        return baseDeDatos.update(NOMBRE_TABLA, valoresParaActualizar, campoParaActualizar, argumentosParaActualizar);
    }

    public void eliminarVenta(Venta venta) {
        SQLiteDatabase baseDeDatos = ayudanteBaseDeDatos.getWritableDatabase();
        String[] argumentos = {String.valueOf(venta.getId())};
        baseDeDatos.delete(NOMBRE_TABLA, "id = ?", argumentos);
    }
}
