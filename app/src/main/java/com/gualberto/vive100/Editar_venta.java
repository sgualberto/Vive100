package com.gualberto.vive100;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.gualberto.vive100.Controller.VentasController;
import com.gualberto.vive100.Modelo.Venta;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;

public class Editar_venta extends AppCompatActivity {
    private EditText txtVenta;
    private EditText cantVP;
    private EditText cantVG;
    private EditText cantSav;
    private EditText cantZen;
    private EditText cantSpa;
    private EditText cantDVP;
    private EditText cantDVG;
    private EditText cantDSav;
    private EditText cantDZen;
    private EditText cantDSpa;
    private EditText cantHielo;

    private TextView cantTVP;
    private TextView cantTVG;
    private TextView cantTSav;
    private TextView cantTZen;
    private TextView cantTSpa;
    private TextView totalHielo;

    private TextView totalVP;
    private TextView totalVG;
    private TextView totalSav;
    private TextView totalZen;
    private TextView totalSpa;

    int vP = 0;
    int devVP = 0;
    int totVP = 0;

    int vG = 0;
    int devVG = 0;
    int totVG = 0;

    int sav = 0;
    int devSav = 0;
    int totSav = 0;

    int zen = 0;
    int devZen = 0;
    int totZen = 0;

    int spa = 0;
    int devSpa = 0;
    int totSpa = 0;

    int hielo = 0;
    int dialoco = 0;

    boolean cambio = false;
    int primerinicio = -10;

    private Venta venta;
    private VentasController ventasController;

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_venta);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        Objects.requireNonNull(getSupportActionBar()).setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        setTitle(R.string.editarVenta);

        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            finish();
            return;
        }
        ventasController = new VentasController(Editar_venta.this);

        long idVenta = extras.getLong("idVenta");
        String nombreVenta = extras.getString("nombreVenta");
        int cantvp = extras.getInt("cantvp");
        int cantvg = extras.getInt("cantvg");
        int cantsav = extras.getInt("cantsav");
        int cantzen = extras.getInt("cantzen");
        int cantsp = extras.getInt("cantsp");
        int cantdvp = extras.getInt("cantdvp");
        int cantdvg = extras.getInt("cantdvg");
        int cantdsav = extras.getInt("cantdsav");
        int cantdzen = extras.getInt("cantdzen");
        int cantdsp = extras.getInt("cantdsp");
        int canthielo = extras.getInt("canthielo");
        dialoco = extras.getInt("dialoco");
        venta = new Venta(nombreVenta, cantvp, cantvg, cantsav, cantzen, cantsp, cantdvp, cantdvg, cantdsav, cantdzen, cantdsp, canthielo, dialoco, idVenta);

        cantVP = findViewById(R.id.cantVP);
        cantVG = findViewById(R.id.cantVG);
        cantSav = findViewById(R.id.cantSaviloe);
        cantZen = findViewById(R.id.cantZen);
        cantSpa = findViewById(R.id.cantSpartan);
        cantHielo = findViewById(R.id.cantHielo);

        cantDVP = findViewById(R.id.cantVPD);
        cantDVG = findViewById(R.id.cantVGD);
        cantDSav = findViewById(R.id.cantSaviloeD);
        cantDZen = findViewById(R.id.cantZenD);
        cantDSpa = findViewById(R.id.cantSpartanD);
        txtVenta = findViewById(R.id.txtVenta);

        cantTVP = findViewById(R.id.cantTVP);
        cantTVG = findViewById(R.id.cantTVG);
        cantTSav = findViewById(R.id.cantTSav);
        cantTZen = findViewById(R.id.cantTZen);
        cantTSpa = findViewById(R.id.cantTSpartan);

        totalVP = findViewById(R.id.totalVP);
        totalVG = findViewById(R.id.totalVG);
        totalSav = findViewById(R.id.totalSav);
        totalZen = findViewById(R.id.totalZen);
        totalSpa = findViewById(R.id.totalSpartan);
        totalHielo = findViewById(R.id.totalHielo);

        Button btnGuardar = findViewById(R.id.btnGuardar);

        cantDVP.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                vpChanged();
            }

        });

        cantDVG.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                vgChanged();
            }
        });

        cantDSav.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                savChanged();
            }
        });

        cantDZen.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                zenChanged();
            }
        });

        cantDSpa.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                spaChanged();
            }
        });

        cantVP.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                vpChanged();
            }
        });
        cantVG.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                vgChanged();
            }
        });

        cantSav.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                savChanged();
            }
        });

        cantZen.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                zenChanged();
            }
        });

        cantSpa.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                spaChanged();
            }
        });
        cantHielo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                hieloChanged();
            }
        });

        cantVP.setText(String.valueOf(venta.getCantvp()));
        cantVG.setText(String.valueOf(venta.getCantvg()));
        cantSav.setText(String.valueOf(venta.getCantsav()));
        cantZen.setText(String.valueOf(venta.getCantzen()));
        cantSpa.setText(String.valueOf(venta.getCantsp()));

        cantDVP.setText(String.valueOf(venta.getCantdvp()));
        cantDVG.setText(String.valueOf(venta.getCantdvg()));
        cantDSav.setText(String.valueOf(venta.getCantdsav()));
        cantDZen.setText(String.valueOf(venta.getCantdzen()));
        cantDSpa.setText(String.valueOf(venta.getCantdsp()));
        cantHielo.setText(String.valueOf(venta.getCanthielo()));

        txtVenta.setText(venta.getNombre());

        btnGuardar.setOnClickListener(v -> guardar());

    }

    public void guardar() {
        txtVenta.setError(null);
        cantVP.setError(null);
        cantVG.setError(null);
        cantSav.setError(null);
        cantZen.setError(null);
        cantSpa.setError(null);

        cantDVP.setError(null);
        cantDSav.setError(null);
        cantDZen.setError(null);
        cantDVG.setError(null);
        cantDSpa.setError(null);

        String nuevoNombre = txtVenta.getText().toString();
        String posibleNuevaCantVP = cantVP.getText().toString();
        String posibleNuevaCantVG = cantVG.getText().toString();
        String posibleNuevaCantSav = cantSav.getText().toString();
        String posibleNuevaCantZen = cantZen.getText().toString();
        String posibleNuevaCantSpa = cantSpa.getText().toString();

        String posibleNuevaCantdVP = cantDVP.getText().toString();
        String posibleNuevaCantdVG = cantDVG.getText().toString();
        String posibleNuevaCantdSav = cantDSav.getText().toString();
        String posibleNuevaCantdZen = cantDZen.getText().toString();
        String posibleNuevaCantdSpa = cantDSpa.getText().toString();
        String posibleNuevaCantHielo = cantHielo.getText().toString();


        if (nuevoNombre.isEmpty()) {
            txtVenta.setError(getString(R.string.txtErrorNombreVenta));
            txtVenta.requestFocus();
            return;
        }
        if (posibleNuevaCantVP.isEmpty()) {
            cantVP.setError(getString(R.string.error_cant_peq));
            cantVP.requestFocus();
            return;
        }
        if (posibleNuevaCantVG.isEmpty()) {
            cantVG.setError(getString(R.string.error_cant_grande));
            cantVG.requestFocus();
            return;
        }
        if (posibleNuevaCantSav.isEmpty()) {
            cantSav.setError(getString(R.string.error_cant_sav));
            cantSav.requestFocus();
            return;
        }
        if (posibleNuevaCantZen.isEmpty()) {
            cantZen.setError(getString(R.string.error_cant_zen));
            cantZen.requestFocus();
            return;
        }

        if (posibleNuevaCantSpa.isEmpty()) {
            cantSpa.setError(getString(R.string.error_cant_spa));
            cantZen.requestFocus();
            return;
        }

        if (posibleNuevaCantdVP.isEmpty()) {
            cantDVP.setError(getString(R.string.error_cant_dev_peq));
            cantDVP.requestFocus();
            return;
        }
        if (posibleNuevaCantdVG.isEmpty()) {
            cantDVG.setError(getString(R.string.error_cant_dev_grande));
            cantDVG.requestFocus();
            return;
        }
        if (posibleNuevaCantdSav.isEmpty()) {
            cantDSav.setError(getString(R.string.error_cant_dev_sav));
            cantDSav.requestFocus();
            return;
        }
        if (posibleNuevaCantdZen.isEmpty()) {
            cantDZen.setError(getString(R.string.error_cant_dev_zen));
            cantDZen.requestFocus();
        }
        if (posibleNuevaCantdSpa.isEmpty()) {
            cantDSpa.setError(getString(R.string.error_cant_dev_spartan));
            cantDSpa.requestFocus();
        }

        int nuevaCantVP = Integer.parseInt(posibleNuevaCantVP);
        int nuevaCantVG = Integer.parseInt(posibleNuevaCantVG);
        int nuevaCantSav = Integer.parseInt(posibleNuevaCantSav);
        int nuevaCantZen = Integer.parseInt(posibleNuevaCantZen);
        int nuevaCantSpa = Integer.parseInt(posibleNuevaCantSpa);

        int nuevaCantDVP = Integer.parseInt(posibleNuevaCantdVP);
        int nuevaCantDVG = Integer.parseInt(posibleNuevaCantdVG);
        int nuevaCantDSav = Integer.parseInt(posibleNuevaCantdSav);
        int nuevaCantDZen = Integer.parseInt(posibleNuevaCantdZen);
        int nuevaCantDSpa = Integer.parseInt(posibleNuevaCantdSpa);
        int nuevaCantHielo = Integer.parseInt(posibleNuevaCantHielo);


        Venta ventaConNuevosCambios = new Venta(nuevoNombre, nuevaCantVP, nuevaCantVG, nuevaCantSav, nuevaCantZen, nuevaCantSpa, nuevaCantDVP, nuevaCantDVG, nuevaCantDSav, nuevaCantDZen, nuevaCantDSpa, nuevaCantHielo, venta.getDialoco(), venta.getId());
        int filasModificadas = ventasController.guardarCambios(ventaConNuevosCambios);
        if (filasModificadas != 1) {
            mostrarToast(getString(R.string.error_guardando));
        } else {
            mostrarToast(getString(R.string.camb_guar));
        }
    }

    private void hieloChanged() {

        primerinicio = primerinicio + 1;
        if (primerinicio > 1) {
            cambio = true;
        }
        try {
            String hieloText = cantHielo.getText().toString();
            hielo = Integer.parseInt(hieloText);

            NumberFormat nftotal = NumberFormat.getCurrencyInstance(Locale.getDefault());
            String txtHielo = nftotal.format((long) hielo * ViveMain.precioHielo);

            totalHielo.setText(txtHielo);
        } catch (Exception ignored) {

        }
    }

    private void spaChanged() {
        primerinicio = primerinicio + 1;
        if (primerinicio > 1) {
            cambio = true;
        }
        try {
            String spaText = cantSpa.getText().toString();
            spa = Integer.parseInt(spaText);

            String spaDText = cantDSpa.getText().toString();
            devSpa = Integer.parseInt(spaDText);

            totSpa = spa - devSpa;
            cantTSpa.setText(String.valueOf(totSpa));

            NumberFormat nftotal = NumberFormat.getCurrencyInstance(Locale.getDefault());
            String txtSpa = nftotal.format((long) totSpa * ViveMain.precioSPA);

            totalSpa.setText(txtSpa);
        } catch (Exception ignored) {
        }
    }

    private void zenChanged() {
        primerinicio = primerinicio + 1;
        if (primerinicio > 1) {
            cambio = true;
        }
        try {
            String zenText = cantZen.getText().toString();
            zen = Integer.parseInt(zenText);

            String zenDText = cantDZen.getText().toString();
            devZen = Integer.parseInt(zenDText);

            totZen = zen - devZen;
            cantTZen.setText(String.valueOf(totZen));

            NumberFormat nftotal = NumberFormat.getCurrencyInstance(Locale.getDefault());
            String txtZen = nftotal.format((long) totZen * ViveMain.precioZEN);

            totalZen.setText(txtZen);
        } catch (Exception ignored) {
        }
    }

    private void savChanged() {
        primerinicio = primerinicio + 1;
        if (primerinicio > 1) {
            cambio = true;
        }
        try {
            String savText = cantSav.getText().toString();
            sav = Integer.parseInt(savText);

            String savDText = cantDSav.getText().toString();
            devSav = Integer.parseInt(savDText);

            totSav = sav - devSav;
            cantTSav.setText(String.valueOf(totSav));

            NumberFormat nftotal = NumberFormat.getCurrencyInstance(Locale.getDefault());
            String txtSav = nftotal.format((long) totSav * ViveMain.precioSAV);

            totalSav.setText(txtSav);
        } catch (Exception ignored) {
        }
    }

    private void vgChanged() {
        primerinicio = primerinicio + 1;
        if (primerinicio > 1) {
            cambio = true;
        }
        try {
            String vgText = cantVG.getText().toString();
            vG = Integer.parseInt(vgText);

            String vgdText = cantDVG.getText().toString();
            devVG = Integer.parseInt(vgdText);

            totVG = vG - devVG;
            cantTVG.setText(String.valueOf(totVG));

            NumberFormat nftotal = NumberFormat.getCurrencyInstance(Locale.getDefault());
            String txtVG = nftotal.format((long) totVG * ViveMain.precioGR);

            totalVG.setText(txtVG);
        } catch (Exception ignored) {
        }
    }

    public void vpChanged() {
        primerinicio = primerinicio + 1;
        if (primerinicio > 1) {
            cambio = true;
        }
        try {

            String vpText = cantVP.getText().toString();
            vP = Integer.parseInt(vpText);

            String vpdText = cantDVP.getText().toString();
            devVP = Integer.parseInt(vpdText);

            totVP = vP - devVP;
            cantTVP.setText(String.valueOf(totVP));

            NumberFormat nftotal = NumberFormat.getCurrencyInstance(Locale.getDefault());
            String txtVP = nftotal.format((long) totVP * ViveMain.precioVP);

            totalVP.setText(txtVP);
        } catch (Exception ignored) {
        }
    }

    public void BtnCalcular(View view) {
        guardar();
        Intent intent = new Intent(this, Totales.class);
        intent.putExtra("datovp", totVP);
        intent.putExtra("datovg", totVG);
        intent.putExtra("datosav", totSav);
        intent.putExtra("datozen", totZen);
        intent.putExtra("datosp", totSpa);
        intent.putExtra("datohielo", hielo);
        intent.putExtra("datodialoco", dialoco);

        intent.putExtra("preciopq", ViveMain.precioVP);
        intent.putExtra("preciogr", ViveMain.precioGR);
        intent.putExtra("preciosav", ViveMain.precioSAV);
        intent.putExtra("preciozen", ViveMain.precioZEN);
        intent.putExtra("preciosp", ViveMain.precioSPA);
        intent.putExtra("preciohielo", ViveMain.precioHielo);

        intent.putExtra("costopq", ViveMain.costoVP);
        intent.putExtra("costogr", ViveMain.costoGR);
        intent.putExtra("costosav", ViveMain.costoSAV);
        intent.putExtra("costozen", ViveMain.costoZEN);
        intent.putExtra("costosp", ViveMain.costoSPA);

        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        if (cambio) {
            mostrarToast(getString(R.string.txtGuardando));
            guardar();
        }
        finish();
    }

    public void mostrarToast(String mens) {
        Toast toast = new Toast(Editar_venta.this);
        View toast_layout = getLayoutInflater().inflate(R.layout.custom_toast, findViewById(R.id.custom_layout));
        toast.setView(toast_layout);

        TextView textView = toast_layout.findViewById(R.id.textMsg);
        textView.setText(mens);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.show();
    }

}