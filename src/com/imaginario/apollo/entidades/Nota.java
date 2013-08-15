/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imaginario.apollo.entidades;

import com.imaginario.apollo.utilidades.Deposito;
import java.util.Hashtable;

/**
 *
 * @author Ismael
 */
public class Nota extends Entidad {
    
    private byte valor;
    private int asignacion;
    private int estudiante;

    public Nota(int _id, byte _valor, int _asignacion, int _estudiante){
        setId(_id);
        setValor(_valor);
        setAsignacion(_asignacion);
        setEstudiante(_estudiante);
        setNombreDeposito("depositoNotas");
        setColumnas(new String[]{"id","valor","asignacion","estudiante"});
    }

    public Nota(){
        setNombreDeposito("depositoNotas");
        setColumnas(new String[]{"id","valor","asignacion","estudiante"});
    }
    
    public Nota(Hashtable table){
        setNombreDeposito("depositoNotas");
        setColumnas(new String[]{"id","valor","asignacion","estudiante"});
        setId((Integer)table.get(getColumnas()[0]));
        setValor((Byte)table.get(getColumnas()[1]));
        setAsignacion((Integer)table.get(getColumnas()[2]));
        setEstudiante((Integer)table.get(getColumnas()[3]));
    }

    public void setValores(){
        Object[] vs = new Object[]{
            getId(),getValor(),getAsignacion(),getEstudiante()
        };
        setValores(vs);
    }
    
    @Override
    public void guardarEnStorage(){
        super.guardarEnStorage();
        Asignacion entidad = Deposito.getAsignacionById(getAsignacion());
        if(!entidad.getNotas().contains(getId())){
            entidad.getNotas().add(getId());
        }
        entidad.guardarEnStorage();
        Estudiante entidad2 = Deposito.getEstudianteById(getEstudiante());
        if(!entidad2.getNotas().contains(getId())){
            entidad2.getNotas().add(getId());
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
        return "Nota{" +
                "id=" + getId() +
                ", valor=" + valor +
                '}';
    }

    public byte getValor() {
        return valor;
    }

    public void setValor(byte valor) {
        this.valor = valor;
    }

    public int getAsignacion() {
        return asignacion;
    }

    public void setAsignacion(int asignacion) {
        this.asignacion = asignacion;
    }

    public int getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(int estudiante) {
        this.estudiante = estudiante;
    }
    
}
