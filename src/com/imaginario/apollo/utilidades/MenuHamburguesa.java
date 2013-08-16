/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imaginario.apollo.utilidades;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.imaginario.apollo.PeriodoX;
import com.imaginario.apollo.entidades.Curso;
import com.imaginario.apollo.entidades.InstanciaCurso;
import com.imaginario.apollo.entidades.Periodo;
import com.imaginario.apollo.entidades.Profesor;

/**
 *
 * @author Ismael
 */
public class MenuHamburguesa {
    
    public static void mostrar(final Form form, final Profesor profesor) {
        final Dialog dlg = new Dialog();
        dlg.setDialogPosition(BorderLayout.WEST);
        dlg.setDisposeWhenPointerOutOfBounds(true);
        Container contenido = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        contenido.setPreferredH(form.getHeight());
        contenido.setPreferredW(form.getWidth()/2);
        
        Button btnInicio = new Button("Inicio");
        btnInicio.setUIID("ButtonHamburguesaInicio");
        contenido.addComponent(btnInicio);
        
        Label lblPeriodos = new Label("Periodos");
        contenido.addComponent(lblPeriodos);
        
        Container contPeriodos = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        loadPeriodos(contPeriodos, profesor, form);
        contenido.addComponent(contPeriodos);
        
        Button btnAgregarPeriodo = new Button("+");
        btnAgregarPeriodo.setUIID("ButtonHamburguesaPeriodoUnselected");
        btnAgregarPeriodo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                new PeriodoX(form, profesor, null);
            }
        });
        contenido.addComponent(btnAgregarPeriodo);
        
        Label lblCursos = new Label("Cursos");
        contenido.addComponent(lblCursos);
        
        Container contCursos = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        try{
            Periodo primerPeriodo = Deposito.getPeriodoById(profesor.getPeriodos().get(0));
            loadCursos(contCursos, primerPeriodo);
        } catch(Exception e){}
        contenido.addComponent(contCursos);
        
        Button btnAgregarCurso = new Button("+");
        btnAgregarCurso.setUIID("ButtonHamburguesaNuevoCurso");
        btnAgregarCurso.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt) {
               
            }
        });
        contenido.addComponent(btnAgregarCurso);
        
        Button btnHistorial = new Button("Historial");
        btnHistorial.setUIID("ButtonHamburguesaInicio");
        contenido.addComponent(btnHistorial);
        
        Button btnConfiguracion = new Button("Configuración");
        btnConfiguracion.setUIID("ButtonHamburguesaConfiguracion");
        contenido.addComponent(btnConfiguracion);
        
        Button btnReportar = new Button("Reportar fallas");
        btnReportar.setUIID("ButtonHamburguesaConfiguracion");
        contenido.addComponent(btnReportar);
        
        dlg.addComponent(contenido);
        dlg.show();
    }
    
    private static void loadPeriodos(final Container contPeriodos, final Profesor profesor, final Form form){
        contPeriodos.removeAll();
        for (int idPeriodo : profesor.getPeriodos()) {
            Container row = new Container(new BorderLayout());
            final Periodo periodo = Deposito.getPeriodoById(idPeriodo);
            final Button btnPeriodo = new Button(periodo.toString());
            btnPeriodo.setUIID("ButtonHamburguesaPeriodoUnselected");
            btnPeriodo.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    loadCursos((Container)contPeriodos.getParent().getComponentAt(5), periodo);
                    for(int i = 0; i < contPeriodos.getComponentCount(); i++){
                        contPeriodos.getComponentAt(i).setUIID("ButtonHamburguesaPeriodoUnselected");
                    }
                    btnPeriodo.setUIID("ButtonHamburguesaPeriodoSelected");
                }
            });
            row.addComponent(BorderLayout.CENTER, btnPeriodo);
            Button btnEditar = new Button("e");
            btnEditar.setUIID("ButtonHamburguesaPeriodoUnselected");
            btnEditar.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    new PeriodoX(form, profesor, periodo);
                }
            });
            row.addComponent(BorderLayout.EAST, btnEditar);
            contPeriodos.addComponent(row);
        }
    }
    
    private static void loadCursos(Container contCursos, Periodo periodo){
        contCursos.removeAll();
        try{
            for(int idCurso : periodo.getCursos()){
                Curso curso = Deposito.getCursoById(idCurso);
                for(int idInstancia : curso.getInstancias()){
                    InstanciaCurso instancia = Deposito.getInstanciaById(idInstancia);
                    Button btnInstancia = new Button(instancia.toString());
                    btnInstancia.setUIID("ButtonHamburguesaCurso");
                    contCursos.addComponent(btnInstancia);
                }
            }
        }
        catch(Exception e){}
        contCursos.animateLayout(0);
    }
    
}
