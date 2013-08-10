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
public class Profesor extends Entidad {
    
    private String nombre;
    private String correo;
    private String usuario;
    private String contrasenia;
    private Vector<Integer> periodos;

    public Profesor(int _id, String _nombre, String _correo, String _usuario, String _contrasenia){
        setId(_id);
        setNombre(_nombre);
        setCorreo(_correo);
        setUsuario(_usuario);
        setContrasenia(_contrasenia);
        setPeriodos(new Vector<Integer>());
        setNombreDeposito("depositoProfesores");
        setColumnas(new String[]{"id","nombre","correo","usuario","contrasenia","periodos"});
    }

    public Profesor(){
        setNombreDeposito("depositoProfesores");
        setColumnas(new String[]{"id","nombre","correo","usuario","contrasenia","periodos"});
    }
    
    public Profesor(Hashtable table){
        setNombreDeposito("depositoProfesores");
        setColumnas(new String[]{"id","nombre","correo","usuario","contrasenia","periodos"});
        setId((Integer)table.get(getColumnas()[0]));
        setNombre((String)table.get(getColumnas()[1]));
        setCorreo((String)table.get(getColumnas()[2]));
        setUsuario((String)table.get(getColumnas()[3]));
        setContrasenia((String)table.get(getColumnas()[4]));
        setPeriodos((Vector<Integer>)table.get(getColumnas()[5]));
    }

    public void setValores(){
        Object[] vs = new Object[]{
            getId(),getNombre(),getCorreo(),getUsuario(),getContrasenia(),getPeriodos()
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

    public Vector<Integer> getPeriodos() {
        return periodos;
    }

    public void setPeriodos(Vector<Integer> periodos) {
        this.periodos = periodos;
    }
    
}
