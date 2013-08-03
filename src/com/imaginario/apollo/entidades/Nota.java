/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imaginario.apollo.entidades;

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
