/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imaginario.apollo.entidades;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Vector;

/**
 *
 * @author Ismael
 */
public class Estudiante extends Entidad {
    
    private String nombre;
    private String carne;
    private String correo;
    private Vector<Integer> notas;
    private Vector<Integer> asistencias;
    private int instanciaCurso;

    public Estudiante(int _id, String _nombre, String _carne, String _correo, int _instanciaCurso){
        setId(_id);
        setNombre(_nombre);
        setCarne(_carne);
        setCorreo(_correo);
        setNotas(new Vector<Integer>());
        setAsistencias(new Vector<Integer>());
        setInstanciaCurso(_instanciaCurso);
        setColumnas(new String[]{"id","nombre","carne","correo","notas","asistencias","instanciacurso"});
    }

    public Estudiante(){
        setColumnas(new String[]{"id","nombre","carne","correo","notas","asistencias","instanciacurso"});
    }
    
    public Estudiante(Hashtable table){
        Estudiante entidad = new Estudiante();
        entidad.setId((Integer)table.get(getColumnas()[0]));
        entidad.setNombre((String)table.get(getColumnas()[1]));
        entidad.setCarne((String)table.get(getColumnas()[2]));
        entidad.setCorreo((String)table.get(getColumnas()[3]));
        entidad.setNotas((Vector<Integer>)table.get(getColumnas()[4]));
        entidad.setAsistencias((Vector<Integer>)table.get(getColumnas()[5]));
        entidad.setInstanciaCurso((Integer)table.get(getColumnas()[6]));
    }

    public void setValores(){
        Object[] vs = new Object[]{
            getId(),getNombre(),getCarne(),getCorreo(),getNotas(),getAsistencias(),getInstanciaCurso()
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
        return "Estudiante{" +
                "id=" + getId() +
                ", nombre='" + nombre + '\'' +
                ", carne='" + carne + '\'' +
                ", correo='" + correo + '\'' +
                '}';
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCarne() {
        return carne;
    }

    public void setCarne(String carne) {
        this.carne = carne;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Vector<Integer> getNotas() {
        return notas;
    }

    public void setNotas(Vector<Integer> notas) {
        this.notas = notas;
    }

    public Vector<Integer> getAsistencias() {
        return asistencias;
    }

    public void setAsistencias(Vector<Integer> asistencias) {
        this.asistencias = asistencias;
    }

    public int getInstanciaCurso() {
        return instanciaCurso;
    }

    public void setInstanciaCurso(int instanciaCurso) {
        this.instanciaCurso = instanciaCurso;
    }
    
}
