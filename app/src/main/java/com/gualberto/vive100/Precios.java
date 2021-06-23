package com.gualberto.vive100;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class Precios extends AppCompatActivity {
    EditText precioPeq;
    EditText precioGr;
    EditText precioSav;
    EditText precioZen;
    EditText precioSpa;
    EditText precioHielo;

    EditText costoPeq;
    EditText costoGr;
    EditText costoSav;
    EditText costoZen;
    EditText costoSpa;

    Button btnCancelar;

    @SuppressLint("SourceLockedOrientationActivity")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_precios);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        Objects.requireNonNull(getSupportActionBar()).setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        setTitle(getString(R.string.setting));

        precioPeq = findViewById(R.id.precioPeq);
        precioGr = findViewById(R.id.precioGr);
        precioSav = findViewById(R.id.precioSav);
        precioZen = findViewById(R.id.precioZen);
        precioSpa = findViewById(R.id.precioSpartan);
        precioHielo = findViewById(R.id.precioHielo);

        costoPeq = findViewById(R.id.costoPeq);
        costoGr = findViewById(R.id.costoGr);
        costoSav = findViewById(R.id.costoSav);
        costoZen = findViewById(R.id.costoZen);
        costoSpa = findViewById(R.id.costoSpartan);

        btnCancelar = findViewById(R.id.btnAtras);
        mostrarPrecios();
    }

    public void mostrarPrecios() {
        int idprecio = 0;
        AyudanteBaseDeDatos admin = new AyudanteBaseDeDatos(this);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();
        Cursor prec = BaseDeDatos.rawQuery("select preciopq, preciogr, preciosav, preciozen, preciosp, costopq, costogr, costosav, costozen, costosp, preciohielo from precios where idprecio =" + idprecio, null);

        if (prec.moveToFirst()) {

            precioPeq.setText(prec.getString(0));
            precioGr.setText(prec.getString(1));
            precioSav.setText(prec.getString(2));
            precioZen.setText(prec.getString(3));
            precioSpa.setText(prec.getString(4));

            costoPeq.setText(prec.getString(5));
            costoGr.setText(prec.getString(6));
            costoSav.setText(prec.getString(7));
            costoZen.setText(prec.getString(8));
            costoSpa.setText(prec.getString(9));
            precioHielo.setText(prec.getString(10));

        } else {
            int precpq = 1500;
            String txtPrPq = String.valueOf(precpq);
            int precgr = 2000;
            String txtPrGr = String.valueOf(precgr);
            int precsav = 3000;
            String txtPrSv = String.valueOf(precsav);
            int preczen = 2000;
            String txtPrZn = String.valueOf(preczen);
            int precsp = 2000;
            String txtPrSp = String.valueOf(precsp);

            int costpq = 1250;
            String txtCosPq = String.valueOf(costpq);
            int costgr = 1650;
            String txtCosGr = String.valueOf(costgr);
            int costsav = 2400;
            String txtCosSv = String.valueOf(costsav);
            int costzen = 1650;
            String txtCosZn = String.valueOf(costzen);
            int costsp = 1650;
            String txtCosSp = String.valueOf(costsp);
            int prechielo = 300;
            String txtPrecHielo = String.valueOf(prechielo);

            precioPeq.setText(txtPrPq);
            precioGr.setText(txtPrGr);
            precioSav.setText(txtPrSv);
            precioZen.setText(txtPrZn);
            precioSpa.setText(txtPrSp);

            costoPeq.setText(txtCosPq);
            costoGr.setText(txtCosGr);
            costoSav.setText(txtCosSv);
            costoZen.setText(txtCosZn);
            costoSpa.setText(txtCosSp);
            precioHielo.setText(txtPrecHielo);

            ContentValues precios = new ContentValues();
            precios.put("idprecio", idprecio);
            precios.put("preciopq", precpq);
            precios.put("preciogr", precgr);
            precios.put("preciosav", precsav);
            precios.put("preciozen", preczen);
            precios.put("preciosp", precsp);

            precios.put("costopq", costpq);
            precios.put("costogr", costgr);
            precios.put("costosav", costsav);
            precios.put("costozen", costzen);
            precios.put("costosp", costsp);
            precios.put("preciohielo", prechielo);

            try {
                BaseDeDatos.insert("precios", null, precios);
                mostrar_mensaje("Clic en guardar.");

            } catch (Exception e) {
                mostrar_mensaje("No se pudo guardar");
            }
        }
        BaseDeDatos.close();
        prec.close();
    }

    public void btnGuardar(View view) {
        int idprecio = 0;
        AyudanteBaseDeDatos admin = new AyudanteBaseDeDatos(this);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        String precpq = precioPeq.getText().toString();
        String precgr = precioGr.getText().toString();
        String precsav = precioSav.getText().toString();
        String preczen = precioZen.getText().toString();
        String precsp = precioSpa.getText().toString();

        String costpq = costoPeq.getText().toString();
        String costgr = costoGr.getText().toString();
        String costsav = costoSav.getText().toString();
        String costzen = costoZen.getText().toString();
        String costsp = costoSpa.getText().toString();
        String prechielo = precioHielo.getText().toString();

        ContentValues precios = new ContentValues();

        precios.put("idprecio", idprecio);
        precios.put("preciopq", precpq);
        precios.put("preciogr", precgr);
        precios.put("preciosav", precsav);
        precios.put("preciozen", preczen);
        precios.put("preciosp", precsp);

        precios.put("costopq", costpq);
        precios.put("costogr", costgr);
        precios.put("costosav", costsav);
        precios.put("costozen", costzen);
        precios.put("costosp", costsp);
        precios.put("preciohielo", prechielo);

        int cantidad = BaseDeDatos.update("precios", precios, "idprecio=" + idprecio, null);

        if (cantidad == 1) {
            mostrarToast(getString(R.string.mensaje1));
        }

        btnCancelar.setText(R.string.atras);
        Intent i = getBaseContext().getPackageManager()
                .getLaunchIntentForPackage(getBaseContext().getPackageName());
        assert i != null;
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }

    public void mostrarToast(String mens) {
        Toast toast = new Toast(Precios.this);
        View toast_layout = getLayoutInflater().inflate(R.layout.custom_toast, findViewById(R.id.custom_layout));
        toast.setView(toast_layout);

        TextView textView = toast_layout.findViewById(R.id.textMsg);
        textView.setText(mens);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.show();
    }

    public void mostrar_mensaje(String mensaje) {
        AlertDialog.Builder mensaj = new AlertDialog.Builder(this);
        mensaj.setTitle(getString(R.string.btnSalirTit))
                .setMessage(mensaje).setPositiveButton(getString(R.string.txtAceptar), null);
        mensaj.create().show();
    }

    public void btnCancelar(View view) {
        Intent i = getBaseContext().getPackageManager()
                .getLaunchIntentForPackage(getBaseContext().getPackageName());
        assert i != null;
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }
}