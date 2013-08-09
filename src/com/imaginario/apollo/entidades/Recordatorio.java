/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imaginario.apollo.entidades;

import java.util.Date;
import java.util.Hashtable;

/**
 *
 * @author Ismael
 */
public class Recordatorio extends Entidad {
    
    private Date fecha;
    private String texto;
    private int instanciaCurso;

    public Recordatorio(int _id, Date _fecha, String _texto, int _instanciaCurso){
        setId(_id);
        setFecha(_fecha);
        setTexto(_texto);
        setInstanciaCurso(_instanciaCurso);
        setColumnas(new String[]{"id","fecha","texto","instanciacurso"});
    }

    public Recordatorio(){
        setColumnas(new String[]{"id","fecha","texto","instanciacurso"});
    }
    
    public Recordatorio(Hashtable table){
        Recordatorio entidad = new Recordatorio();
        entidad.setId((Integer)table.get(getColumnas()[0]));
        entidad.setFecha((Date)table.get(getColumnas()[1]));
        entidad.setTexto((String)table.get(getColumnas()[2]));
        entidad.setInstanciaCurso((Integer)table.get(getColumnas()[3]));
    }

    public void setValores(){
        Object[] vs = new Object[]{
            getId(),getFecha(),getTexto(),getInstanciaCurso()
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
        return "Recordatorio{" +
                "id=" + getId() +
                ", fecha=" + fecha +
                ", texto='" + texto + '\'' +
                '}';
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public int getInstanciaCurso() {
        return instanciaCurso;
    }

    public void setInstanciaCurso(int instanciaCurso) {
        this.instanciaCurso = instanciaCurso;
    }
    
}
