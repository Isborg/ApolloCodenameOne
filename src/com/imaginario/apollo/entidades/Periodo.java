/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imaginario.apollo.entidades;

import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

/**
 *
 * @author Ismael
 */
public class Periodo extends Entidad {
    
    private short anio;
    private String tipo;
    private byte numero;
    private String institucion;
    private Vector<Integer> cursos;
    private int profesor;

    public Periodo(int _id, short _anio, String _tipo, byte _numero, String _institucion, int _profesor){
        setId(_id);
        setAnio(_anio);
        setTipo(_tipo);
        setNumero(_numero);
        setInstitucion(_institucion);
        setCursos(new Vector<Integer>());
        setProfesor(_profesor);
        setColumnas(new String[]{"id","anio","tipo","numero","institucion","cursos","profesor"});
    }

    public Periodo(){
        setColumnas(new String[]{"id","anio","tipo","numero","institucion","cursos","profesor"});
    }
    
    public Periodo(Hashtable table){
        Periodo entidad = new Periodo();
        entidad.setId((Integer)table.get(getColumnas()[0]));
        entidad.setAnio((Short)table.get(getColumnas()[1]));
        entidad.setTipo((String)table.get(getColumnas()[2]));
        entidad.setNumero((Byte)table.get(getColumnas()[3]));
        entidad.setInstitucion((String)table.get(getColumnas()[4]));
        entidad.setCursos((Vector<Integer>)table.get(getColumnas()[5]));
        entidad.setProfesor((Integer)table.get(getColumnas()[6]));
    }

    public void setValores(){
        Object[] vs = new Object[]{
            getId(),getAnio(),getTipo(),getNumero(),getInstitucion(),getCursos(),getProfesor()
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
        return "Periodo{" +
                "id=" + getId() +
                ", anio=" + anio +
                ", tipo='" + tipo + '\'' +
                ", numero=" + numero +
                ", institucion='" + institucion + '\'' +
                '}';
    }

    public short getAnio() {
        return anio;
    }

    public void setAnio(short anio) {
        this.anio = anio;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public byte getNumero() {
        return numero;
    }

    public void setNumero(byte numero) {
        this.numero = numero;
    }

    public String getInstitucion() {
        return institucion;
    }

    public void setInstitucion(String institucion) {
        this.institucion = institucion;
    }

    public Vector<Integer> getCursos() {
        return cursos;
    }

    public void setCursos(Vector<Integer> cursos) {
        this.cursos = cursos;
    }

    public int getProfesor() {
        return profesor;
    }

    public void setProfesor(int profesor) {
        this.profesor = profesor;
    }
    
}
