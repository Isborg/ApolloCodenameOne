/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imaginario.apollo.entidades;

import com.imaginario.apollo.utilidades.Deposito;
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
    private int periodo;

    public InstanciaCurso(int _id, Date _fechaInicio, byte _cantidadSemanas, int _curso, int _periodo){
        setId(_id);
        setFechaInicio(_fechaInicio);
        setCantidadSemanas(_cantidadSemanas);
        setEstudiantes(new Vector<Integer>());
        setHorarios(new Vector<Integer>());
        setRecordatorios(new Vector<Integer>());
        setCurso(_curso);
        setPeriodo(_periodo);
        setNombreDeposito("depositoInstancias");
        setColumnas(new String[]{"id","fechainicio","cantidadsemanas","estudiantes","horarios","recordatorios","curso","periodo"});
    }

    public InstanciaCurso(){
        setNombreDeposito("depositoInstancias");
        setColumnas(new String[]{"id","fechainicio","cantidadsemanas","estudiantes","horarios","recordatorios","curso","periodo"});
    }
    
    public InstanciaCurso(Hashtable table){
        setNombreDeposito("depositoInstancias");
        setColumnas(new String[]{"id","fechainicio","cantidadsemanas","estudiantes","horarios","recordatorios","curso","periodo"});
        setId((Integer)table.get(getColumnas()[0]));
        setFechaInicio((Date)table.get(getColumnas()[1]));
        setCantidadSemanas((Byte)table.get(getColumnas()[2]));
        setEstudiantes((Vector<Integer>)table.get(getColumnas()[3]));
        setHorarios((Vector<Integer>)table.get(getColumnas()[4]));
        setRecordatorios((Vector<Integer>)table.get(getColumnas()[5]));
        setCurso((Integer)table.get(getColumnas()[6]));
        setPeriodo((Integer)table.get(getColumnas()[7]));
    }

    public void setValores(){
        Object[] vs = new Object[]{
            getId(),getFechaInicio(),getCantidadSemanas(),getEstudiantes(),getHorarios(),getRecordatorios(),getCurso(),getPeriodo()
        };
        if(getEstudiantes() == null){
            vs[3] = new Vector<Integer>();
        }
        if(getHorarios() == null){
            vs[4] = new Vector<Integer>();
        }
        if(getRecordatorios() == null){
            vs[5] = new Vector<Integer>();
        }
        setValores(vs);
    }
    
    @Override
    public void guardarEnStorage(){
        super.guardarEnStorage();
        Curso entidad1 = Deposito.getCursoById(getCurso());
        if(!entidad1.getInstancias().contains(getId())){
            entidad1.getInstancias().add(getId());
        }
        entidad1.guardarEnStorage();
        Periodo entidad2 = Deposito.getPeriodoById(getPeriodo());
        if(!entidad2.getInstancias().contains(getId())){
            entidad2.getInstancias().add(getId());
        }
        entidad2.guardarEnStorage();
    }
    
    @Override
    public Hashtable toHashtable(){
        setValores();
        return super.toHashtable();
    }

    @Override
    public String toString() {
        return Deposito.getCursoById(getCurso()).getNombre();
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

    public int getPeriodo() {
        return periodo;
    }

    public void setPeriodo(int periodo) {
        this.periodo = periodo;
    }
    
}
