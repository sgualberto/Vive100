package com.gualberto.vive100.Modelo;

public class Venta {
    private final String nombre;
    private final int cantvp;
    private final int cantvg;
    private final int cantsav;
    private final int cantzen;
    private final int cantsp;
    private final int cantdvp;
    private final int cantdvg;
    private final int cantdsav;
    private final int cantdzen;
    private final int cantdsp;
    private final int canthielo;
    private final int dialoco;
    private long id;

    public Venta(String nombre, int cantvp, int cantvg, int cantsav, int cantzen, int cantsp, int cantdvp, int cantdvg, int cantdsav, int cantdzen, int cantdsp, int canthielo, int dialoco) {
        this.nombre = nombre;
        this.cantvp = cantvp;
        this.cantvg = cantvg;
        this.cantsav = cantsav;
        this.cantzen = cantzen;
        this.cantsp = cantsp;
        this.cantdvp = cantdvp;
        this.cantdvg = cantdvg;
        this.cantdsav = cantdsav;
        this.cantdzen = cantdzen;
        this.cantdsp = cantdsp;
        this.canthielo = canthielo;
        this.dialoco = dialoco;
    }

    public Venta(String nombre, int cantvp, int cantvg, int cantsav, int cantzen, int cantsp, int cantdvp, int cantdvg, int cantdsav, int cantdzen, int cantdsp, int canthielo, int dialoco, long id) {
        this.nombre = nombre;
        this.cantvp = cantvp;
        this.cantvg = cantvg;
        this.cantsav = cantsav;
        this.cantzen = cantzen;
        this.cantsp = cantsp;
        this.cantdvp = cantdvp;
        this.cantdvg = cantdvg;
        this.cantdsav = cantdsav;
        this.cantdzen = cantdzen;
        this.cantdsp = cantdsp;
        this.canthielo = canthielo;
        this.dialoco = dialoco;
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCantvp() {
        return cantvp;
    }

    public int getCantvg() {
        return cantvg;
    }

    public int getCantsav() {
        return cantsav;
    }

    public int getCantzen() {
        return cantzen;
    }

    public int getCantsp() {
        return cantsp;
    }

    public int getCantdvp() {
        return cantdvp;
    }

    public int getCantdvg() {
        return cantdvg;
    }

    public int getCantdsav() {
        return cantdsav;
    }

    public int getCantdzen() {
        return cantdzen;
    }

    public int getCantdsp() {
        return cantdsp;
    }

    public int getCanthielo() {
        return canthielo;
    }

    public int getDialoco() {
        return dialoco;
    }

    public long getId() {
        return id;
    }
}
