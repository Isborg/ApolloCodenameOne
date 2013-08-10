/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imaginario.apollo.entidades;

import com.imaginario.apollo.utilidades.Deposito;
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
        setNombreDeposito("depositoRecordatorios");
        setColumnas(new String[]{"id","fecha","texto","instanciacurso"});
    }

    public Recordatorio(){
        setNombreDeposito("depositoRecordatorios");
        setColumnas(new String[]{"id","fecha","texto","instanciacurso"});
    }
    
    public Recordatorio(Hashtable table){
        setNombreDeposito("depositoRecordatorios");
        setColumnas(new String[]{"id","fecha","texto","instanciacurso"});
        setId((Integer)table.get(getColumnas()[0]));
        setFecha((Date)table.get(getColumnas()[1]));
        setTexto((String)table.get(getColumnas()[2]));
        setInstanciaCurso((Integer)table.get(getColumnas()[3]));
    }

    public void setValores(){
        Object[] vs = new Object[]{
            getId(),getFecha(),getTexto(),getInstanciaCurso()
        };
        setValores(vs);
    }
    
    @Override
    public void guardarEnStorage(){
        InstanciaCurso entidad = Deposito.getInstanciaById(getInstanciaCurso());
        if(!entidad.getRecordatorios().contains(getId())){
            entidad.getRecordatorios().add(getId());
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
