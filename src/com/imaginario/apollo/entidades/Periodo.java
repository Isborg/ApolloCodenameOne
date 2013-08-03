/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imaginario.apollo.entidades;

import java.util.ArrayList;

/**
 *
 * @author Ismael
 */
public class Periodo extends Entidad {
    
    private short anio;
    private String tipo;
    private byte numero;
    private String institucion;
    private ArrayList<Integer> cursos;
    private int profesor;

    public Periodo(int _id, short _anio, String _tipo, byte _numero, String _institucion, int _profesor){
        setId(_id);
        setAnio(_anio);
        setTipo(_tipo);
        setNumero(_numero);
        setInstitucion(_institucion);
        setCursos(new ArrayList<Integer>());
        setProfesor(_profesor);
        setColumnas(new String[]{"id","anio","tipo","numero","institucion","profesor"});
    }

    public Periodo(){
        setColumnas(new String[]{"id","anio","tipo","numero","institucion","profesor"});
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

    public ArrayList<Integer> getCursos() {
        return cursos;
    }

    public void setCursos(ArrayList<Integer> cursos) {
        this.cursos = cursos;
    }

    public int getProfesor() {
        return profesor;
    }

    public void setProfesor(int profesor) {
        this.profesor = profesor;
    }
    
}
