/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imaginario.apollo.entidades;

import java.util.Hashtable;
import java.util.Vector;

/**
 *
 * @author Ismael
 */
public class Horario extends Entidad {
    
    private String dia;
    private byte entradaHora;
    private byte entradaMinuto;
    private byte salidaHora;
    private byte salidaMinuto;
    private int instanciaCurso;

    public Horario(int _id, String _dia, byte _entradaHora, byte _entradaMinuto, byte _salidaHora, byte _salidaMinuto, int _instanciaCurso){
        setId(_id);
        setDia(_dia);
        setEntradaHora(_entradaHora);
        setEntradaMinuto(_entradaMinuto);
        setSalidaHora(_salidaHora);
        setSalidaMinuto(_salidaMinuto);
        setInstanciaCurso(_instanciaCurso);
        setColumnas(new String[]{"id","dia","entradahora","entradaminuto","salidahora","salidaminuto","instanciacurso"});
    }

    public Horario(){
        setColumnas(new String[]{"id","dia","entradahora","entradaminuto","salidahora","salidaminuto","instanciacurso"});
    }
    
    public Horario(Hashtable table){
        Horario entidad = new Horario();
        entidad.setId((Integer)table.get(getColumnas()[0]));
        entidad.setDia((String)table.get(getColumnas()[1]));
        entidad.setEntradaHora((Byte)table.get(getColumnas()[2]));
        entidad.setEntradaMinuto((Byte)table.get(getColumnas()[3]));
        entidad.setSalidaHora((Byte)table.get(getColumnas()[4]));
        entidad.setSalidaMinuto((Byte)table.get(getColumnas()[5]));
        entidad.setInstanciaCurso((Integer)table.get(getColumnas()[6]));
    }

    public void setValores(){
        Object[] vs = new Object[]{
            getId(),getDia(),getEntradaHora(),getEntradaMinuto(),getSalidaHora(),getSalidaMinuto(),getInstanciaCurso()
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
        return "Horario{" +
                "id=" + getId() +
                ", dia='" + dia + '\'' +
                ", entradaHora=" + entradaHora +
                ", entradaMinuto=" + entradaMinuto +
                ", salidaHora=" + salidaHora +
                ", salidaMinuto=" + salidaMinuto +
                '}';
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public byte getEntradaHora() {
        return entradaHora;
    }

    public void setEntradaHora(byte entradaHora) {
        this.entradaHora = entradaHora;
    }

    public byte getEntradaMinuto() {
        return entradaMinuto;
    }

    public void setEntradaMinuto(byte entradaMinuto) {
        this.entradaMinuto = entradaMinuto;
    }

    public byte getSalidaHora() {
        return salidaHora;
    }

    public void setSalidaHora(byte salidaHora) {
        this.salidaHora = salidaHora;
    }

    public byte getSalidaMinuto() {
        return salidaMinuto;
    }

    public void setSalidaMinuto(byte salidaMinuto) {
        this.salidaMinuto = salidaMinuto;
    }

    public int getInstanciaCurso() {
        return instanciaCurso;
    }

    public void setInstanciaCurso(int instanciaCurso) {
        this.instanciaCurso = instanciaCurso;
    }
    
}
