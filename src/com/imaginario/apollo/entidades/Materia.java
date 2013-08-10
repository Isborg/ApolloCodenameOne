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
public class Materia extends Entidad {
    
    private byte numeroSemana;
    private String descripcion;
    private int curso;

    public Materia(int _id, byte _numeroSemana, String _descripcion, int _curso){
        setId(_id);
        setNumeroSemana(_numeroSemana);
        setDescripcion(_descripcion);
        setCurso(_curso);
        setNombreDeposito("depositoMaterias");
        setColumnas(new String[]{"id","numerosemana","descripcion","curso"});
    }

    public Materia(){
        setNombreDeposito("depositoMaterias");
        setColumnas(new String[]{"id","numerosemana","descripcion","curso"});
    }
    
    public Materia(Hashtable table){
        setNombreDeposito("depositoMaterias");
        setColumnas(new String[]{"id","numerosemana","descripcion","curso"});
        setId((Integer)table.get(getColumnas()[0]));
        setNumeroSemana((Byte)table.get(getColumnas()[1]));
        setDescripcion((String)table.get(getColumnas()[2]));
        setCurso((Integer)table.get(getColumnas()[3]));
    }

    public void setValores(){
        Object[] vs = new Object[]{
            getId(),getNumeroSemana(),getDescripcion(),getCurso()
        };
        setValores(vs);
    }
    
    @Override
    public void guardarEnStorage(){
        Curso entidad = Deposito.getCursoById(getCurso());
        if(!entidad.getMaterias().contains(getId())){
            entidad.getMaterias().add(getId());
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
        return "Materia{" +
                "id=" + getId() +
                ", numeroSemana=" + numeroSemana +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }

    public byte getNumeroSemana() {
        return numeroSemana;
    }

    public void setNumeroSemana(byte numeroSemana) {
        this.numeroSemana = numeroSemana;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCurso() {
        return curso;
    }

    public void setCurso(int curso) {
        this.curso = curso;
    }
    
}
