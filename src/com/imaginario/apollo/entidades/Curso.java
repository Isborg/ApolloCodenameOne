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
public class Curso extends Entidad {
    
    private String nombre;
    private String descripcion;
    private Vector<Integer> asignaciones;
    private Vector<Integer> materias;
    private Vector<Integer> instancias;
    private int periodo;

    public Curso(int _id, String _nombre, String _descrpcion, int _periodo){
        setId(_id);
        setNombre(_nombre);
        setDescripcion(_descrpcion);
        setAsignaciones(new Vector<Integer>());
        setMaterias(new Vector<Integer>());
        setInstancias(new Vector<Integer>());
        setPeriodo(_periodo);
        setNombreDeposito("depositoCursos");
        setColumnas(new String[]{"id","nombre","descripcion","asignaciones","materias","instancias","periodo"});
    }

    public Curso(){
        setNombreDeposito("depositoCursos");
        setColumnas(new String[]{"id","nombre","descripcion","asignaciones","materias","instancias","periodo"});
    }
    
    public Curso(Hashtable table){
        setNombreDeposito("depositoCursos");
        setColumnas(new String[]{"id","nombre","descripcion","asignaciones","materias","instancias","periodo"});
        setId((Integer)table.get(getColumnas()[0]));
        setNombre((String)table.get(getColumnas()[1]));
        setDescripcion((String)table.get(getColumnas()[2]));
        setAsignaciones((Vector<Integer>)table.get(getColumnas()[3]));
        setMaterias((Vector<Integer>)table.get(getColumnas()[4]));
        setInstancias((Vector<Integer>)table.get(getColumnas()[5]));
        setPeriodo((Integer)table.get(getColumnas()[6]));
    }

    public void setValores(){
        Object[] vs = new Object[]{
            getId(),getNombre(),getDescripcion(),getAsignaciones(),getMaterias(),getInstancias(),getPeriodo()
        };
        setValores(vs);
    }
    
    @Override
    public void guardarEnStorage(){
        Periodo entidad = Deposito.getPeriodoById(getPeriodo());
        if(!entidad.getCursos().contains(getId())){
            entidad.getCursos().add(getId());
        }
        entidad.guardarEnStorage();
        super.guardarEnStorage();
    }
    
    @Override
    public Hashtable toHashtable(){
        setValores();
        return super.toHashtable();
    }

    @Override
    public String toString() {
        return getNombre();
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

    public Vector<Integer> getAsignaciones() {
        return asignaciones;
    }

    public void setAsignaciones(Vector<Integer> asignaciones) {
        this.asignaciones = asignaciones;
    }

    public Vector<Integer> getMaterias() {
        return materias;
    }

    public void setMaterias(Vector<Integer> materias) {
        this.materias = materias;
    }

    public Vector<Integer> getInstancias() {
        return instancias;
    }

    public void setInstancias(Vector<Integer> instancias) {
        this.instancias = instancias;
    }

    public int getPeriodo() {
        return periodo;
    }

    public void setPeriodo(int periodo) {
        this.periodo = periodo;
    }
    
}
