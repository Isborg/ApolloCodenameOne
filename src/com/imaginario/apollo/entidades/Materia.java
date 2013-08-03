/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imaginario.apollo.entidades;

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
        setColumnas(new String[]{"id","numerosemana","descripcion","curso"});
    }

    public Materia(){
        setColumnas(new String[]{"id","numerosemana","descripcion","curso"});
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
