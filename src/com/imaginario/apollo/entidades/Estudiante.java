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
public class Estudiante extends Entidad {
    
    private String nombre;
    private String carne;
    private String correo;
    private ArrayList<Integer> notas;
    private ArrayList<Integer> asistencias;
    private int instanciaCurso;

    public Estudiante(int _id, String _nombre, String _carne, String _correo, int _instanciaCurso){
        setId(_id);
        setNombre(_nombre);
        setCarne(_carne);
        setCorreo(_correo);
        setNotas(new ArrayList<Integer>());
        setAsistencias(new ArrayList<Integer>());
        setInstanciaCurso(_instanciaCurso);
        setColumnas(new String[]{"id","nombre","carne","correo","instanciacurso"});
    }

    public Estudiante(){
        setColumnas(new String[]{"id","nombre","carne","correo","instanciacurso"});
    }

    @Override
    public String toString() {
        return "Estudiante{" +
                "id=" + getId() +
                ", nombre='" + nombre + '\'' +
                ", carne='" + carne + '\'' +
                ", correo='" + correo + '\'' +
                '}';
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCarne() {
        return carne;
    }

    public void setCarne(String carne) {
        this.carne = carne;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public ArrayList<Integer> getNotas() {
        return notas;
    }

    public void setNotas(ArrayList<Integer> notas) {
        this.notas = notas;
    }

    public ArrayList<Integer> getAsistencias() {
        return asistencias;
    }

    public void setAsistencias(ArrayList<Integer> asistencias) {
        this.asistencias = asistencias;
    }

    public int getInstanciaCurso() {
        return instanciaCurso;
    }

    public void setInstanciaCurso(int instanciaCurso) {
        this.instanciaCurso = instanciaCurso;
    }
    
}
