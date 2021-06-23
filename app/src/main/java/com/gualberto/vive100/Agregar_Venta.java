package com.gualberto.vive100;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.gualberto.vive100.Controller.VentasController;
import com.gualberto.vive100.Modelo.Venta;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Objects;

public class Agregar_Venta extends AppCompatActivity {
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

    final Calendar c = Calendar.getInstance();

    private VentasController ventasController;

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar__venta);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        Objects.requireNonNull(getSupportActionBar()).setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        setTitle(R.string.txtTitle);
        txtVenta = findViewById(R.id.edVenta);
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

        dialoco = c.get(Calendar.DAY_OF_WEEK);

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

        ventasController = new VentasController(Agregar_Venta.this);
    }

    private void hieloChanged() {
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


    public void getFecha(View view) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, (view1, year, monthOfYear, dayOfMonth) -> {
            c.set(Calendar.YEAR, year);
            c.set(Calendar.MONTH, monthOfYear);
            c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            String date = DateFormat.getDateInstance(DateFormat.MEDIUM).format(c.getTime());
            txtVenta.setText(date);

        }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();
    }

    public void btnCalcular(View view) {
        guardar(view);
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
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_personalizado, null);
        builder.setView(view);

        final AlertDialog dialog = builder.create();
        dialog.show();

        TextView txt = view.findViewById(R.id.text_dialog);
        txt.setText(R.string.conf_guardar);
        Button btnAceptar = view.findViewById(R.id.btnAceptar);
        btnAceptar.setText(R.string.btnYes);
        Button btnCancelar = view.findViewById(R.id.btnCancel);
        btnCancelar.setText(R.string.negativo);

        btnAceptar.setOnClickListener(v -> finish());
        btnCancelar.setOnClickListener(v -> dialog.cancel());
    }

    public void mostrarToast(String mens) {
        Toast toast = new Toast(Agregar_Venta.this);
        View toast_layout = getLayoutInflater().inflate(R.layout.custom_toast, findViewById(R.id.custom_layout));
        toast.setView(toast_layout);

        TextView textView = toast_layout.findViewById(R.id.textMsg);
        textView.setText(mens);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.show();
    }

    public void guardar(View view) {
        txtVenta.setError(null);
        cantVP.setError(null);
        cantVG.setError(null);
        cantSav.setError(null);
        cantZen.setError(null);
        cantSpa.setError(null);
        cantDVP.setError(null);
        cantDVG.setError(null);
        cantDSav.setError(null);
        cantDZen.setError(null);
        cantDSpa.setError(null);

        String nombreVenta = txtVenta.getText().toString();
        String cantVPComoCadena = cantVP.getText().toString();
        String cantVGComoCadena = cantVG.getText().toString();
        String cantSavComoCadena = cantSav.getText().toString();
        String cantZenComoCadena = cantZen.getText().toString();
        String cantSpaComoCadena = cantSpa.getText().toString();
        String cantDVPComoCadena = cantDVP.getText().toString();
        String cantDVGComoCadena = cantDVG.getText().toString();
        String cantDSavComoCadena = cantDSav.getText().toString();
        String cantDZenComoCadena = cantDZen.getText().toString();
        String cantDSpaComoCadena = cantDSpa.getText().toString();
        String cantHieloComoCadena = cantHielo.getText().toString();

        if ("".equals(nombreVenta)) {
            txtVenta.setError(getString(R.string.txtErrorNombreVenta));
            mostrarToast(getString(R.string.txtErrorNombreVenta));
            txtVenta.requestFocus();
            return;
        }
        if ("".equals(cantVPComoCadena)) {
            cantVP.setError(getString(R.string.error_cant_peq));
            cantVP.requestFocus();
            return;
        }

        if ("".equals(cantVGComoCadena)) {
            cantVG.setError(getString(R.string.error_cant_grande));
            cantVG.requestFocus();
            return;
        }

        if ("".equals(cantSavComoCadena)) {
            cantSav.setError(getString(R.string.error_cant_sav));
            cantSav.requestFocus();
            return;
        }

        if ("".equals(cantZenComoCadena)) {
            cantZen.setError(getString(R.string.error_cant_zen));
            cantZen.requestFocus();
            return;
        }

        if ("".equals(cantSpaComoCadena)) {
            cantSpa.setError(getString(R.string.error_cant_spa));
            cantSpa.requestFocus();
            return;
        }

        if ("".equals(cantDVPComoCadena)) {
            cantDVP.setError(getString(R.string.error_cant_dev_peq));
            cantDVP.requestFocus();
            return;
        }

        if ("".equals(cantDVGComoCadena)) {
            cantDVG.setError(getString(R.string.error_cant_dev_grande));
            cantDVG.requestFocus();
            return;
        }

        if ("".equals(cantDSavComoCadena)) {
            cantDSav.setError(getString(R.string.error_cant_dev_sav));
            cantDSav.requestFocus();
            return;
        }

        if ("".equals(cantDZenComoCadena)) {
            cantDZen.setError(getString(R.string.error_cant_dev_zen));
            cantDZen.requestFocus();
            return;
        }

        if ("".equals(cantDSpaComoCadena)) {
            cantDSpa.setError(getString(R.string.error_cant_dev_spartan));
            cantDSpa.requestFocus();
            return;
        }

        if ("".equals(cantHieloComoCadena)) {
            cantHielo.setText("0");
        }

        int cantvp;
        cantvp = Integer.parseInt(cantVP.getText().toString());

        int cantvg;
        cantvg = Integer.parseInt(cantVG.getText().toString());

        int cantsav;
        cantsav = Integer.parseInt(cantSav.getText().toString());

        int cantzen;
        cantzen = Integer.parseInt(cantZen.getText().toString());

        int cantsp;
        cantsp = Integer.parseInt(cantSpa.getText().toString());

        int cantdvp;
        cantdvp = Integer.parseInt(cantDVP.getText().toString());

        int cantdvg;
        cantdvg = Integer.parseInt(cantDVG.getText().toString());

        int cantdsav;
        cantdsav = Integer.parseInt(cantDSav.getText().toString());

        int cantdzen;
        cantdzen = Integer.parseInt(cantDZen.getText().toString());

        int cantdsp;
        cantdsp = Integer.parseInt(cantDSpa.getText().toString());

        int canthielo = Integer.parseInt(cantHielo.getText().toString());

        Venta nuevaVenta = new Venta(nombreVenta, cantvp, cantvg, cantsav, cantzen, cantsp, cantdvp, cantdvg, cantdsav, cantdzen, cantdsp, canthielo, dialoco);
        long id = ventasController.nuevaVenta(nuevaVenta);
        if (id == -1) {
            mostrarToast(getString(R.string.error_guardando));
        } else {
            mostrarToast(getString(R.string.venta_guardada));
            finish();
        }
    }

}