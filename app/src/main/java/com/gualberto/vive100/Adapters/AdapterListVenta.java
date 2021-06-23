package com.gualberto.vive100.Adapters;

import android.annotation.SuppressLint;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gualberto.vive100.Modelo.Venta;
import com.gualberto.vive100.R;
import com.gualberto.vive100.ViveMain;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AdapterListVenta extends RecyclerView.Adapter<AdapterListVenta.ViewHolder> {
    private List<Venta> items;
    private OnClickListener onClickListener = null;
    private final SparseBooleanArray selected_items;
    private int current_selected_idx = -1;

    public AdapterListVenta(List<Venta> items) {
        this.items = items;
        selected_items = new SparseBooleanArray();
    }

    public void setListaDeVentas(List<Venta> items) {
        this.items = items;
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_venta, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Venta venta = items.get(position);

        String nombreVenta = venta.getNombre();
        int vpv = (venta.getCantvp() - venta.getCantdvp()) * ViveMain.precioVP;
        int vgv = (venta.getCantvg() - venta.getCantdvg()) * ViveMain.precioGR;
        int savv = (venta.getCantsav() - venta.getCantdsav()) * ViveMain.precioSAV;
        int zenv = (venta.getCantzen() - venta.getCantdzen()) * ViveMain.precioZEN;
        int spav = (venta.getCantsp() - venta.getCantdsp()) * ViveMain.precioSPA;

        int vpd = (venta.getCantvp() - venta.getCantdvp()) * ViveMain.costoVP;
        int vgd = (venta.getCantvg() - venta.getCantdvg()) * ViveMain.costoGR;
        int savd = (venta.getCantsav() - venta.getCantdsav()) * ViveMain.costoSAV;
        int zend = (venta.getCantzen() - venta.getCantdzen()) * ViveMain.costoZEN;
        int spad = (venta.getCantsp() - venta.getCantdsp()) * ViveMain.costoSPA;

        int hielo = (venta.getCanthielo()) * ViveMain.precioHielo;

        int dialoco = venta.getDialoco();
        int pagodialoco = 0;
        if (dialoco == 1) {
            pagodialoco = 5000;
        }

        int total = vpv + vgv + savv + zenv + spav;
        int entregar = vpd + vgd + savd + zend + spad;
        int ganancia = total - entregar - hielo + pagodialoco;
        NumberFormat nftotal = NumberFormat.getCurrencyInstance(Locale.getDefault());
        String toStrV = nftotal.format(total);
        String toStrD = nftotal.format(ganancia);

        String cadenaVenta = "Venta del día: ";
        String txtVentDia = cadenaVenta + nombreVenta;

        String cadenaDescrip = "Total vendido: ";
        String txtDescrip = cadenaDescrip + toStrV;
        String cadenaGanancia = "Ganancia: " + toStrD;

        holder.nombre.setText(txtVentDia);
        holder.venta.setText(txtDescrip);
        holder.ganancia.setText(cadenaGanancia);

        holder.lyt_parent.setActivated(selected_items.get(position, false));

        holder.lyt_parent.setOnClickListener(v -> {
            if (onClickListener == null) return;
            onClickListener.onItemClick(v, venta, position);
        });

        holder.lyt_parent.setOnLongClickListener(v -> {
            if (onClickListener == null) return false;
            onClickListener.onItemLongClick(v, venta, position);
            return true;
        });

        toggleCheckedIcon(holder, position);

    }

    private void toggleCheckedIcon(ViewHolder holder, int position) {
        if (selected_items.get(position, false)) {
            holder.lyt_checked.setVisibility(View.VISIBLE);
        } else {
            holder.lyt_checked.setVisibility(View.GONE);
        }
        if (current_selected_idx == position) resetCurrentIndex();
    }

    public void toggleSelection(int pos) {
        current_selected_idx = pos;
        if (selected_items.get(pos, false)) {
            selected_items.delete(pos);
        } else {
            selected_items.put(pos, true);
        }
        notifyItemChanged(pos);
    }

    @SuppressLint("NotifyDataSetChanged")
    public void clearSelections() {
        selected_items.clear();
        notifyDataSetChanged();
    }

    public int getSelectedItemCount() {
        return selected_items.size();
    }

    public List<Integer> getSelectedItems() {
        List<Integer> items = new ArrayList<>(selected_items.size());
        for (int i = 0; i < selected_items.size(); i++) {
            items.add(selected_items.keyAt(i));
        }
        return items;
    }

    public void removeData(int position) {
        items.remove(position);
        resetCurrentIndex();
    }

    private void resetCurrentIndex() {
        current_selected_idx = -1;
    }

    public Venta getItem(int position) {
        return items.get(position);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nombre, venta, ganancia;
        public ImageView image;
        public RelativeLayout lyt_checked;
        public View lyt_parent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.nombre);
            venta = itemView.findViewById(R.id.venta);
            ganancia = itemView.findViewById(R.id.ganancia);
            image = itemView.findViewById(R.id.image);
            lyt_checked = itemView.findViewById(R.id.lyt_checked);
            lyt_parent = itemView.findViewById(R.id.lyt_parent1);
        }
    }

    public interface OnClickListener {
        void onItemClick(View view, Venta obj, int pos);

        void onItemLongClick(View view, Venta obj, int pos);
    }
}
