/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imaginario.apollo.entidades;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Ismael
 */
public class Asignacion extends Entidad {
    
    private String nombre;
    private String descripcion;
    private byte porcentaje;
    private Date fechaEntrega;
    private ArrayList<Integer> notas;
    private int curso;

    public Asignacion(int _id, String _nombre, String _descripcion, byte _porcentaje, Date _fechaEntrega, int _curso){
        setId(_id);
        setNombre(_nombre);
        setDescripcion(_descripcion);
        setPorcentaje(_porcentaje);
        setFechaEntrega(_fechaEntrega);
        setNotas(new ArrayList<Integer>());
        setCurso(_curso);
        setColumnas(new String[]{"id","nombre","descripcion","porcentaje","fechaentrega","curso"});
    }

    public Asignacion(){
        setColumnas(new String[]{"id","nombre","descripcion","porcentaje","fechaentrega","curso"});
    }

    @Override
    public String toString() {
        return "Asignacion{" +
                "id=" + getId() +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", porcentaje=" + porcentaje +
                ", fechaEntrega=" + fechaEntrega +
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

    public byte getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(byte porcentaje) {
        this.porcentaje = porcentaje;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public ArrayList<Integer> getNotas() {
        return notas;
    }

    public void setNotas(ArrayList<Integer> notas) {
        this.notas = notas;
    }

    public int getCurso() {
        return curso;
    }

    public void setCurso(int curso) {
        this.curso = curso;
    }
    
}
