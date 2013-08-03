/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imaginario.apollo.entidades;

import java.util.Date;

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
