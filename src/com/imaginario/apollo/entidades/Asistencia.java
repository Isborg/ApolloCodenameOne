/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imaginario.apollo.entidades;

import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

/**
 *
 * @author Ismael
 */
public class Asistencia extends Entidad {
    
    private Date fecha;
    private String estado;
    private int estudiante;

    public Asistencia(int _id, Date _fecha, String _estado, int _estudiante){
        setId(_id);
        setFecha(_fecha);
        setEstado(_estado);
        setEstudiante(_estudiante);
        setColumnas(new String[]{"id","fecha","estado","estudiante"});
    }

    public Asistencia(){
        setColumnas(new String[]{"id","fecha","estado","estudiante"});
    }
    
    public Asistencia(Hashtable table){
        Asistencia entidad = new Asistencia();
        entidad.setId((Integer)table.get(getColumnas()[0]));
        entidad.setFecha((Date)table.get(getColumnas()[1]));
        entidad.setEstado((String)table.get(getColumnas()[2]));
        entidad.setEstudiante((Integer)table.get(getColumnas()[3]));
    }

    public void setValores(){
        Object[] vs = new Object[]{
            getId(),getFecha(),getEstado(),getEstudiante()
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
        return "Asistencia{" +
                "id=" + getId() +
                ", fecha=" + fecha +
                ", estado='" + estado + '\'' +
                '}';
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(int estudiante) {
        this.estudiante = estudiante;
    }
    
}
