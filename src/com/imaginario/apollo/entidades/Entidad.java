/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imaginario.apollo.entidades;

import com.codename1.io.Storage;
import java.util.Hashtable;

/**
 *
 * @author Ismael
 */
public class Entidad {

    private int id;
    private String[] columnas;
    private Object[] valores;
    private String nombreDeposito;

    public Entidad() {
    }

    public Hashtable toHashtable() {
        Hashtable table = new Hashtable();
        for (int i = 0; i < getColumnas().length; i++) {
            table.put(getColumnas()[i], getValores()[i]);
        }
        return table;
    }

    public void guardarEnStorage() {
        Hashtable entidades = (Hashtable) Storage.getInstance().readObject(getNombreDeposito());
        int id;
        if (getId() == -1) {
            if (entidades.isEmpty()) {
                id = 0;
            } else {
                id = (Integer) entidades.keySet().toArray()[0] + 1;
            }
        } else {
            id = getId();
        }
        setId(id);
        entidades.put(getId(), toHashtable());
        Storage.getInstance().writeObject(getNombreDeposito(), entidades);
    }

    public String getNombreDeposito() {
        return nombreDeposito;
    }

    public void setNombreDeposito(String nombreDeposito) {
        this.nombreDeposito = nombreDeposito;
    }

    public Object[] getValores() {
        return valores;
    }

    public void setValores(Object[] valores) {
        this.valores = valores;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String[] getColumnas() {
        return columnas;
    }

    public void setColumnas(String[] columnas) {
        this.columnas = columnas;
    }
}
