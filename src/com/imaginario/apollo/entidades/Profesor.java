/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imaginario.apollo.entidades;

import java.util.ArrayList;

/**
 *
 * @author Ismael
 */
public class Profesor extends Entidad {
    
    private String nombre;
    private String correo;
    private String usuario;
    private String contrasenia;
    private ArrayList<Integer> periodos;

    public Profesor(int _id, String _nombre, String _correo, String _usuario, String _contrasenia){
        setId(_id);
        setNombre(_nombre);
        setCorreo(_correo);
        setUsuario(_usuario);
        setContrasenia(_contrasenia);
        setPeriodos(new ArrayList<Integer>());
        setColumnas(new String[]{"id","nombre","correo","usuario","contrasenia"});
    }

    public Profesor(){
        setColumnas(new String[]{"id","nombre","correo","usuario","contrasenia"});
    }

    @Override
    public String toString() {
        return "Profesor{" +
                "id=" + getId() +
                ", nombre='" + nombre + '\'' +
                ", correo='" + correo + '\'' +
                ", usuario='" + usuario + '\'' +
                ", contrasenia='" + contrasenia + '\'' +
                '}';
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public ArrayList<Integer> getPeriodos() {
        return periodos;
    }

    public void setPeriodos(ArrayList<Integer> periodos) {
        this.periodos = periodos;
    }
    
}
