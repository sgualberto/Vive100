package com.gualberto.vive100;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.text.NumberFormat;
import java.util.Locale;

public class Totales extends AppCompatActivity {
    TextView totalVen;
    TextView totalEnt;
    TextView totalGan;
    TextView totalGan2;
    TextView txtLoco;
    TextView lblLoco;
    ImageView diaLoco;
    int pagodialoco = 0;

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_totales);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setTitle(getString(R.string.totales));

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        totalVen = findViewById(R.id.totalVen);
        totalEnt = findViewById(R.id.totalEnt);
        totalGan = findViewById(R.id.totalGan);
        totalGan2 = findViewById(R.id.totalGan2);
        txtLoco = findViewById(R.id.txtLoco);
        lblLoco = findViewById(R.id.lblLoco);
        diaLoco = findViewById(R.id.diaLoco);
        Button btnAtras = findViewById(R.id.btnAtras);

        Bundle datos = this.getIntent().getExtras();
        assert datos != null;
        int venP = datos.getInt("datovp");
        int venG = datos.getInt("datovg");
        int venSav = datos.getInt("datosav");
        int venZen = datos.getInt("datozen");
        int venSpa = datos.getInt("datosp");
        int hielo = datos.getInt("datohielo");

        int precPq = datos.getInt("preciopq");
        int precGR = datos.getInt("preciogr");
        int precSav = datos.getInt("preciosav");
        int precZen = datos.getInt("preciozen");
        int precSpa = datos.getInt("preciosp");
        int precHielo = datos.getInt("preciohielo");

        int costPq = datos.getInt("costopq");
        int costGr = datos.getInt("costogr");
        int costSav = datos.getInt("costosav");
        int costZen = datos.getInt("costozen");
        int costSpa = datos.getInt("costosp");
        int dialoco = datos.getInt("datodialoco");

        if (dialoco == 1) {
            diaLoco.setVisibility(View.VISIBLE);
            txtLoco.setVisibility(View.VISIBLE);
            lblLoco.setVisibility(View.VISIBLE);
            pagodialoco = 5000;
        }

        int totalVend = (venP * precPq) + (venG * precGR) + (venSav * precSav) + (venZen * precZen) + (venSpa * precSpa);
        int totalEntr = (venP * costPq) + (venG * costGr) + (venSav * costSav) + (venZen * costZen) + (venSpa * costSpa);
        int totHielo = hielo * precHielo;
        int totalGanc = (totalVend - totalEntr);
        int totalGDL = totalGanc + pagodialoco;
        int totalGanc2 = totalGanc + pagodialoco - totHielo;
        NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.getDefault());

        String resgan = nf.format(totalGanc);
        String resgan2 = nf.format(totalGanc2);
        String resvend = nf.format(totalVend);
        String resentr = nf.format(totalEntr);
        String resloco = nf.format(totalGDL);

        totalVen.setText(resvend);
        totalEnt.setText(resentr);
        totalGan.setText(resgan);
        totalGan2.setText(resgan2);
        txtLoco.setText(resloco);

        btnAtras.setOnClickListener(v -> onBackPressed());
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }
}