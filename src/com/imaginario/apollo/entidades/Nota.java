/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imaginario.apollo.entidades;

import java.util.Hashtable;

/**
 *
 * @author Ismael
 */
public class Nota extends Entidad {
    
    private byte valor;
    private int asignacion;
    private int estudiante;

    public Nota(int _id, byte _valor, int _asignacion, int _estudiante){
        setId(_id);
        setValor(_valor);
        setAsignacion(_asignacion);
        setEstudiante(_estudiante);
        setColumnas(new String[]{"id","valor","asignacion","estudiante"});
    }

    public Nota(){
        setColumnas(new String[]{"id","valor","asignacion","estudiante"});
    }
    
    public Nota(Hashtable table){
        Nota entidad = new Nota();
        entidad.setId((Integer)table.get(getColumnas()[0]));
        entidad.setValor((Byte)table.get(getColumnas()[1]));
        entidad.setAsignacion((Integer)table.get(getColumnas()[2]));
        entidad.setEstudiante((Integer)table.get(getColumnas()[3]));
    }

    public void setValores(){
        Object[] vs = new Object[]{
            getId(),getValor(),getAsignacion(),getEstudiante()
        };
        setValores(vs);
    }
    
    @Override
    public Hashtable toHashtable(){
        setValores();
        return super.toHashtable();
    }

    @Override
    public String toString() {
        return "Nota{" +
                "id=" + getId() +
                ", valor=" + valor +
                '}';
    }

    public byte getValor() {
        return valor;
    }

    public void setValor(byte valor) {
        this.valor = valor;
    }

    public int getAsignacion() {
        return asignacion;
    }

    public void setAsignacion(int asignacion) {
        this.asignacion = asignacion;
    }

    public int getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(int estudiante) {
        this.estudiante = estudiante;
    }
    
}
