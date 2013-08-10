/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imaginario.apollo.entidades;

import com.imaginario.apollo.utilidades.Deposito;
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
        setNombreDeposito("depositoAsistencias");
        setColumnas(new String[]{"id","fecha","estado","estudiante"});
    }

    public Asistencia(){
        setNombreDeposito("depositoAsistencias");
        setColumnas(new String[]{"id","fecha","estado","estudiante"});
    }
    
    public Asistencia(Hashtable table){
        setNombreDeposito("depositoAsistencias");
        setColumnas(new String[]{"id","fecha","estado","estudiante"});
        setId((Integer)table.get(getColumnas()[0]));
        setFecha((Date)table.get(getColumnas()[1]));
        setEstado((String)table.get(getColumnas()[2]));
        setEstudiante((Integer)table.get(getColumnas()[3]));
    }

    public void setValores(){
        Object[] vs = new Object[]{
            getId(),getFecha(),getEstado(),getEstudiante()
        };
        setValores(vs);
    }
    
    @Override
    public void guardarEnStorage(){
        Estudiante entidad = Deposito.getEstudianteById(getEstudiante());
        if(!entidad.getAsistencias().contains(getId())){
            entidad.getAsistencias().add(getId());
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
