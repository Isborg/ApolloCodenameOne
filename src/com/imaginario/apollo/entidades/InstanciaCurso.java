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
public class InstanciaCurso extends Entidad {
    
    private Date fechaInicio;
    private byte cantidadSemanas;
    private ArrayList<Integer> estudiantes;
    private ArrayList<Integer> horarios;
    private ArrayList<Integer> recordatorios;
    private int curso;

    public InstanciaCurso(int _id, Date _fechaInicio, byte _cantidadSemanas, int _curso){
        setId(_id);
        setFechaInicio(_fechaInicio);
        setCantidadSemanas(_cantidadSemanas);
        setEstudiantes(new ArrayList<Integer>());
        setHorarios(new ArrayList<Integer>());
        setRecordatorios(new ArrayList<Integer>());
        setCurso(_curso);
        setColumnas(new String[]{"id","fechainicio","cantidadsemanas","curso"});
    }

    public InstanciaCurso(){
        setColumnas(new String[]{"id","fechainicio","cantidadsemanas","curso"});
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

    public ArrayList<Integer> getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(ArrayList<Integer> estudiantes) {
        this.estudiantes = estudiantes;
    }

    public ArrayList<Integer> getHorarios() {
        return horarios;
    }

    public void setHorarios(ArrayList<Integer> horarios) {
        this.horarios = horarios;
    }

    public ArrayList<Integer> getRecordatorios() {
        return recordatorios;
    }

    public void setRecordatorios(ArrayList<Integer> recordatorios) {
        this.recordatorios = recordatorios;
    }

    public int getCurso() {
        return curso;
    }

    public void setCurso(int curso) {
        this.curso = curso;
    }
    
}
