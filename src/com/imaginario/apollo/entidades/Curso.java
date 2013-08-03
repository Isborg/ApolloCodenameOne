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
public class Curso extends Entidad {
    
    private String nombre;
    private String descripcion;
    private ArrayList<Integer> asignaciones;
    private ArrayList<Integer> materias;
    private ArrayList<Integer> instancias;
    private int periodo;

    public Curso(int _id, String _nombre, String _descrpcion, int _periodo){
        setId(_id);
        setNombre(_nombre);
        setDescripcion(_descrpcion);
        setAsignaciones(new ArrayList<Integer>());
        setMaterias(new ArrayList<Integer>());
        setInstancias(new ArrayList<Integer>());
        setPeriodo(_periodo);
        setColumnas(new String[]{"id","nombre","descripcion","periodo"});
    }

    public Curso(){
        setColumnas(new String[]{"id","nombre","descripcion","periodo"});
    }

    @Override
    public String toString() {
        return "Curso{" +
                "id=" + getId() +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public ArrayList<Integer> getAsignaciones() {
        return asignaciones;
    }

    public void setAsignaciones(ArrayList<Integer> asignaciones) {
        this.asignaciones = asignaciones;
    }

    public ArrayList<Integer> getMaterias() {
        return materias;
    }

    public void setMaterias(ArrayList<Integer> materias) {
        this.materias = materias;
    }

    public ArrayList<Integer> getInstancias() {
        return instancias;
    }

    public void setInstancias(ArrayList<Integer> instancias) {
        this.instancias = instancias;
    }

    public int getPeriodo() {
        return periodo;
    }

    public void setPeriodo(int periodo) {
        this.periodo = periodo;
    }
    
}
