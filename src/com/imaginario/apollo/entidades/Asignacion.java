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
public class Asignacion extends Entidad {
    
    private String nombre;
    private String descripcion;
    private byte porcentaje;
    private Date fechaEntrega;
    private Vector<Integer> notas;
    private int curso;

    public Asignacion(int _id, String _nombre, String _descripcion, byte _porcentaje, Date _fechaEntrega, int _curso){
        setId(_id);
        setNombre(_nombre);
        setDescripcion(_descripcion);
        setPorcentaje(_porcentaje);
        setFechaEntrega(_fechaEntrega);
        setNotas(new Vector<Integer>());
        setCurso(_curso);
        setColumnas(new String[]{"id","nombre","descripcion","porcentaje","fechaentrega","notas","curso"});
    }

    public Asignacion(){
        setColumnas(new String[]{"id","nombre","descripcion","porcentaje","fechaentrega","notas","curso"});
    }
    
    public Asignacion(Hashtable table){
        Asignacion entidad = new Asignacion();
        entidad.setId((Integer)table.get(getColumnas()[0]));
        entidad.setNombre((String)table.get(getColumnas()[1]));
        entidad.setDescripcion((String)table.get(getColumnas()[2]));
        entidad.setPorcentaje((Byte)table.get(getColumnas()[3]));
        entidad.setFechaEntrega((Date)table.get(getColumnas()[4]));
        entidad.setNotas((Vector<Integer>)table.get(getColumnas()[5]));
        entidad.setCurso((Integer)table.get(getColumnas()[6]));
    }

    public void setValores(){
        Object[] vs = new Object[]{
            getId(),getNombre(),getDescripcion(),getPorcentaje(),getFechaEntrega(),getNotas(),getCurso()
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

    public Vector<Integer> getNotas() {
        return notas;
    }

    public void setNotas(Vector<Integer> notas) {
        this.notas = notas;
    }

    public int getCurso() {
        return curso;
    }

    public void setCurso(int curso) {
        this.curso = curso;
    }
    
}
