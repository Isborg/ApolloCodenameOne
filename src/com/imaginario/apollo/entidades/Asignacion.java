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
        setNombreDeposito("depositoAsignaciones");
        setColumnas(new String[]{"id","nombre","descripcion","porcentaje","fechaentrega","notas","curso"});
    }

    public Asignacion(){
        setNombreDeposito("depositoAsignaciones");
        setColumnas(new String[]{"id","nombre","descripcion","porcentaje","fechaentrega","notas","curso"});
    }
    
    public Asignacion(Hashtable table){
        setNombreDeposito("depositoAsignaciones");
        setColumnas(new String[]{"id","nombre","descripcion","porcentaje","fechaentrega","notas","curso"});
        setId((Integer)table.get(getColumnas()[0]));
        setNombre((String)table.get(getColumnas()[1]));
        setDescripcion((String)table.get(getColumnas()[2]));
        setPorcentaje((Byte)table.get(getColumnas()[3]));
        setFechaEntrega((Date)table.get(getColumnas()[4]));
        setNotas((Vector<Integer>)table.get(getColumnas()[5]));
        setCurso((Integer)table.get(getColumnas()[6]));
    }

    public void setValores(){
        Object[] vs = new Object[]{
            getId(),getNombre(),getDescripcion(),getPorcentaje(),getFechaEntrega(),getNotas(),getCurso()
        };
        if(getNotas() == null){
            vs[5] = new Vector<Integer>();
        }
        setValores(vs);
    }
    
    @Override
    public void guardarEnStorage(){
        super.guardarEnStorage();
        Curso entidad = Deposito.getCursoById(getCurso());
        if(!entidad.getAsignaciones().contains(getId())){
            entidad.getAsignaciones().add(getId());
        }
        entidad.guardarEnStorage();
        
        // CREAR NOTAS DE LA ASIGNACION PARA CADA ESTUDIANTE
        ArrayList<Estudiante> estudiantes = new ArrayList<Estudiante>();
        ArrayList<InstanciaCurso> instancias = Deposito.getInstanciasByCurso(entidad.getId());
        for(InstanciaCurso instancia : instancias){
            estudiantes.addAll(Deposito.getEstudiantesByInstancia(instancia.getId()));
        }
        for(Estudiante estudiante : estudiantes){
            Nota nuevaNota = new Nota(-1, (byte)0, getId(), estudiante.getId());
            nuevaNota.guardarEnStorage();
        }
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
