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
public class InstanciaCurso extends Entidad {
    
    private Date fechaInicio;
    private byte cantidadSemanas;
    private Vector<Integer> estudiantes;
    private Vector<Integer> horarios;
    private Vector<Integer> recordatorios;
    private int curso;

    public InstanciaCurso(int _id, Date _fechaInicio, byte _cantidadSemanas, int _curso){
        setId(_id);
        setFechaInicio(_fechaInicio);
        setCantidadSemanas(_cantidadSemanas);
        setEstudiantes(new Vector<Integer>());
        setHorarios(new Vector<Integer>());
        setRecordatorios(new Vector<Integer>());
        setCurso(_curso);
        setColumnas(new String[]{"id","fechainicio","cantidadsemanas","estudiantes","horarios","recordatorios","curso"});
    }

    public InstanciaCurso(){
        setColumnas(new String[]{"id","fechainicio","cantidadsemanas","estudiantes","horarios","recordatorios","curso"});
    }
    
    public InstanciaCurso(Hashtable table){
        InstanciaCurso entidad = new InstanciaCurso();
        entidad.setId((Integer)table.get(getColumnas()[0]));
        entidad.setFechaInicio((Date)table.get(getColumnas()[1]));
        entidad.setCantidadSemanas((Byte)table.get(getColumnas()[2]));
        entidad.setEstudiantes((Vector<Integer>)table.get(getColumnas()[3]));
        entidad.setHorarios((Vector<Integer>)table.get(getColumnas()[4]));
        entidad.setRecordatorios((Vector<Integer>)table.get(getColumnas()[5]));
        entidad.setCurso((Integer)table.get(getColumnas()[6]));
    }

    public void setValores(){
        Object[] vs = new Object[]{
            getId(),getFechaInicio(),getCantidadSemanas(),getEstudiantes(),getHorarios(),getRecordatorios(),getCurso()
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
        return "InstanciaCurso{" +
                "id=" + getId() +
                ", fechaInicio=" + fechaInicio +
                ", cantidadSemanas=" + cantidadSemanas +
                '}';
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public byte getCantidadSemanas() {
        return cantidadSemanas;
    }

    public void setCantidadSemanas(byte cantidadSemanas) {
        this.cantidadSemanas = cantidadSemanas;
    }

    public Vector<Integer> getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(Vector<Integer> estudiantes) {
        this.estudiantes = estudiantes;
    }

    public Vector<Integer> getHorarios() {
        return horarios;
    }

    public void setHorarios(Vector<Integer> horarios) {
        this.horarios = horarios;
    }

    public Vector<Integer> getRecordatorios() {
        return recordatorios;
    }

    public void setRecordatorios(Vector<Integer> recordatorios) {
        this.recordatorios = recordatorios;
    }

    public int getCurso() {
        return curso;
    }

    public void setCurso(int curso) {
        this.curso = curso;
    }
    
}
