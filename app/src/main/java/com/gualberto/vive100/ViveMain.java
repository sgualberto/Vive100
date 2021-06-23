package com.gualberto.vive100;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionMode;
import androidx.cardview.widget.CardView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.gualberto.vive100.Adapters.AdapterListVenta;
import com.gualberto.vive100.Controller.VentasController;
import com.gualberto.vive100.Modelo.Venta;

import java.util.ArrayList;
import java.util.List;

public class ViveMain extends AppCompatActivity {
    public static int precioVP = 0;
    public static int precioGR = 0;
    public static int precioSAV = 0;
    public static int precioZEN = 0;
    public static int precioSPA = 0;
    public static int precioHielo = 0;

    public static int costoVP = 0;
    public static int costoGR = 0;
    public static int costoSAV = 0;
    public static int costoZEN = 0;
    public static int costoSPA = 0;

    private AdapterListVenta adaptadorVentas;
    private ActionModeCallback actionModeCallback;
    private ActionMode actionMode;
    private List<Venta> items = new ArrayList<>();
    private VentasController ventasController;

    CoordinatorLayout lyt_parent;
    BottomAppBar bottomAppBar;
    Context context;
    private String position = "rigth";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_vive);

        ventasController = new VentasController(ViveMain.this);
        items = new ArrayList<>();

        initComponent();
        getPrecios();
    }

    @SuppressLint("NotifyDataSetChanged")
    private void addItems() {
        if (adaptadorVentas == null) return;
        items = ventasController.obtenerVenta();
        adaptadorVentas.setListaDeVentas(items);
        adaptadorVentas.notifyDataSetChanged();
    }

    @SuppressLint("NonConstantResourceId")
    private void initComponent() {
        FloatingActionButton fab_Load = findViewById(R.id.fab_add);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Vive 100% - por Gualberto");
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setIcon(R.mipmap.ic_launcher);
        }
        context = ViveMain.this;

        lyt_parent = findViewById(R.id.lyt_parent);
        bottomAppBar = findViewById(R.id.bottomAppBar);

        bottomAppBar.replaceMenu(R.menu.menu_setting);
        bottomAppBar.setHideOnScroll(true);

        if (getScreenWidthDp() >= 1200) {
            final GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
            recyclerView.setLayoutManager(gridLayoutManager);
        } else if (getScreenWidthDp() >= 800) {
            final GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
            recyclerView.setLayoutManager(gridLayoutManager);
        } else {
            final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(linearLayoutManager);
        }

        cargarPreferencias();

        bottomAppBar.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()) {
                case R.id.action_settings:
                    Intent precio = new Intent(ViveMain.this, Precios.class);
                    startActivity(precio);
                    break;
                case R.id.action_custom:
                   // mostrarToast("E");
                    CardView cardView = findViewById(R.id.cardView);
                    cardView.setCardBackgroundColor(getResources().getColor(R.color.primaryLightColor));
                    break;
                case R.id.action_position:
                    if (position.equals("center")) {
                        bottomAppBar.setFabAlignmentMode(BottomAppBar.FAB_ALIGNMENT_MODE_END);
                        position = "rigth";
                    } else {
                        bottomAppBar.setFabAlignmentMode(BottomAppBar.FAB_ALIGNMENT_MODE_CENTER);
                        position = "center";
                    }
                    guardarPreferencias();
                    break;
            }
            return false;
        });

        adaptadorVentas = new AdapterListVenta(items);

        recyclerView.setAdapter(adaptadorVentas);
        adaptadorVentas.setOnClickListener(new AdapterListVenta.OnClickListener() {
            @Override
            public void onItemClick(View view, Venta obj, int pos) {
                if (adaptadorVentas.getSelectedItemCount() > 0) {
                    enableActionMode(pos);
                } else {
                    Venta ventaSeleccionada = adaptadorVentas.getItem(pos);
                    Intent intent = new Intent(ViveMain.this, Editar_venta.class);
                    intent.putExtra("idVenta", ventaSeleccionada.getId());
                    intent.putExtra("nombreVenta", ventaSeleccionada.getNombre());
                    intent.putExtra("cantvp", ventaSeleccionada.getCantvp());
                    intent.putExtra("cantvg", ventaSeleccionada.getCantvg());
                    intent.putExtra("cantsav", ventaSeleccionada.getCantsav());
                    intent.putExtra("cantzen", ventaSeleccionada.getCantzen());
                    intent.putExtra("cantsp", ventaSeleccionada.getCantsp());
                    intent.putExtra("cantdvp", ventaSeleccionada.getCantdvp());
                    intent.putExtra("cantdvg", ventaSeleccionada.getCantdvg());
                    intent.putExtra("cantdsav", ventaSeleccionada.getCantdsav());
                    intent.putExtra("cantdzen", ventaSeleccionada.getCantdzen());
                    intent.putExtra("cantdsp", ventaSeleccionada.getCantdsp());
                    intent.putExtra("canthielo", ventaSeleccionada.getCanthielo());
                    intent.putExtra("dialoco", ventaSeleccionada.getDialoco());
                    startActivity(intent);
                }
            }

            @Override
            public void onItemLongClick(View view, Venta obj, int pos) {
                enableActionMode(pos);
            }
        });

        fab_Load.setOnClickListener(v -> {
            Intent intent = new Intent(ViveMain.this, Agregar_Venta.class);
            startActivity(intent);

        });

        fab_Load.setOnLongClickListener(v -> {

            AlertDialog.Builder builder = new AlertDialog.Builder(ViveMain.this);
            LayoutInflater inflater = getLayoutInflater();
            View view = inflater.inflate(R.layout.dialog_personalizado, null);
            builder.setView(view);

            final AlertDialog dialog = builder.create();
            dialog.show();

            TextView txt = view.findViewById(R.id.text_dialog);
            txt.setText("Realizado por Gualberto Sarmiento, \n\nRevise mi página para actualizaciones");
            Button btnAceptar = view.findViewById(R.id.btnAceptar);
            btnAceptar.setText(R.string.site);
            Button btnCancelar = view.findViewById(R.id.btnCancel);
            btnCancelar.setText(R.string.fabCerrar);

            btnAceptar.setOnClickListener(v1 -> {
                Intent intentNavegador = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.sites.google.com/view/vive100gualberto"));
                startActivity(intentNavegador);
            });
            btnCancelar.setOnClickListener(v12 -> dialog.dismiss());

            return false;
        });

        addItems();
        actionModeCallback = new ActionModeCallback();

    }

    public void getPrecios() {
        try {
            AyudanteBaseDeDatos admin = new AyudanteBaseDeDatos(this);
            SQLiteDatabase BaseDeDatos = admin.getReadableDatabase();
            int idprecio = 0;
            Cursor precio = BaseDeDatos.rawQuery("select preciopq, preciogr, preciosav, preciozen, preciosp, costopq, costogr, costosav, costozen, costosp, preciohielo from precios where idprecio =" + idprecio, null);

            if (precio.moveToFirst()) {
                precioVP = precio.getInt(0);
                precioGR = precio.getInt(1);
                precioSAV = precio.getInt(2);
                precioZEN = precio.getInt(3);
                precioSPA = precio.getInt(4);
                costoVP = precio.getInt(5);
                costoGR = precio.getInt(6);
                costoSAV = precio.getInt(7);
                costoZEN = precio.getInt(8);
                costoSPA = precio.getInt(9);
                precioHielo = precio.getInt(10);
                BaseDeDatos.close();
                precio.close();

            } else {
                mostrarToast(getString(R.string.btnErroDb));
                Intent precios = new Intent(this, Precios.class);
                startActivity(precios);
            }
        } catch (Exception e) {
            mostrarToast(getString(R.string.btnErrorMsj));
        }
    }

    private void enableActionMode(int position) {
        if (actionMode == null) {
            actionMode = startSupportActionMode(actionModeCallback);
        }
        toggleSelection(position);
    }

    private void toggleSelection(int position) {
        adaptadorVentas.toggleSelection(position);
        int count = adaptadorVentas.getSelectedItemCount();

        if (count == 0) {
            actionMode.finish();
        } else {
            actionMode.setTitle(String.valueOf(count));
            actionMode.invalidate();
        }
    }

    private class ActionModeCallback implements ActionMode.Callback {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            Tools.setSystemBarColor(ViveMain.this, R.color.primaryColor);
            mode.getMenuInflater().inflate(R.menu.menu_delete, menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            int id = item.getItemId();

            if (id == R.id.action_delete) {

                String mensaje = getString(R.string.recycMsj);

                AlertDialog.Builder builder = new AlertDialog.Builder(ViveMain.this);
                LayoutInflater inflater = getLayoutInflater();
                View view = inflater.inflate(R.layout.dialog_personalizado, null);
                builder.setView(view);

                final AlertDialog dialog = builder.create();
                dialog.show();

                TextView txt = view.findViewById(R.id.text_dialog);
                txt.setText(mensaje);

                Button btnAceptar = view.findViewById(R.id.btnAceptar);
                btnAceptar.setText(R.string.fabBtnEliminar);
                Button btnCancelar = view.findViewById(R.id.btnCancel);
                btnCancelar.setText(R.string.fabBtnCancelar);

                btnAceptar.setOnClickListener(v -> {
                    deleteInboxes();
                    mode.finish();
                    dialog.dismiss();
                });
                btnCancelar.setOnClickListener(v -> dialog.cancel());
                return true;
            }

            return false;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            adaptadorVentas.clearSelections();
            actionMode = null;
            Tools.setSystemBarColor(ViveMain.this, R.color.primaryColor);
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private void deleteInboxes() {
        List<Integer> selectedItemPositions = adaptadorVentas.getSelectedItems();
        for (int i = selectedItemPositions.size() - 1; i >= 0; i--) {
            int a = selectedItemPositions.get(i);
            final Venta ventaParaEliminar = items.get(a);
            ventasController.eliminarVenta(ventaParaEliminar);
            adaptadorVentas.removeData(selectedItemPositions.get(i));
        }
        mostrarToast(getString(R.string.fabConfirmarEliminar));
        adaptadorVentas.notifyDataSetChanged();
    }

    protected void onResume() {
        super.onResume();
        addItems();
    }

    public void mostrarToast(String mens) {
        Toast toast = new Toast(ViveMain.this);
        View toast_layout = getLayoutInflater().inflate(R.layout.custom_toast, findViewById(R.id.custom_layout));
        toast.setView(toast_layout);
        TextView textView = toast_layout.findViewById(R.id.textMsg);
        textView.setText(mens);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.show();
    }

    public void guardarPreferencias() {
        SharedPreferences prefs = getSharedPreferences("preferenciasMiApp", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.clear();
        editor.putString("position", position);
        editor.apply();
    }

    public void cargarPreferencias() {
        SharedPreferences prefs = getSharedPreferences("preferenciasMiApp", Context.MODE_PRIVATE);
        this.position = prefs.getString("position", position);

        if (position.equals("center")) {
            bottomAppBar.setFabAlignmentMode(BottomAppBar.FAB_ALIGNMENT_MODE_CENTER);
        } else {
            bottomAppBar.setFabAlignmentMode(BottomAppBar.FAB_ALIGNMENT_MODE_END);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private int getScreenWidthDp() {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        return (int) (displayMetrics.widthPixels / displayMetrics.density);
    }
}