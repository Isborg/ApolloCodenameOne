/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imaginario.apollo.utilidades;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.imaginario.apollo.CursoNuevo;
import com.imaginario.apollo.DetalleCurso;
import com.imaginario.apollo.Historial;
import com.imaginario.apollo.PeriodoX;
import com.imaginario.apollo.ReportarFallas;
import com.imaginario.apollo.entidades.InstanciaCurso;
import com.imaginario.apollo.entidades.Periodo;
import com.imaginario.apollo.entidades.Profesor;

import org.codehaus.jettison.json.JSONException;


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
        //btnInicio.setPreferredH(45);
        System.out.println(""+btnInicio.getPreferredH());
        System.out.println(""+btnInicio.getPreferredW());
        contenido.addComponent(btnInicio);
        
        Label lblPeriodos = new Label("Periodos");
        contenido.addComponent(lblPeriodos);
        
        Container contPeriodos = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        loadPeriodos(contPeriodos, profesor, form);
        contenido.addComponent(contPeriodos);
        
        Button btnAgregarPeriodo = new Button("+");
        //btnAgregarPeriodo.setPreferredH(45);
        btnAgregarPeriodo.setUIID("ButtonHamburguesaPeriodoUnselected");
        btnAgregarPeriodo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                PeriodoX periodoX = new PeriodoX(form, profesor, null);
            }
        });
        contenido.addComponent(btnAgregarPeriodo);        
        Label lblCursos = new Label("Cursos");
        contenido.addComponent(lblCursos);        
        Container contCursos = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        try{
            Periodo primerPeriodo = Deposito.getPeriodoById(profesor.getPeriodos().get(0));
            loadCursos(contCursos, primerPeriodo,profesor);
        } catch(Exception e){}
        contenido.addComponent(contCursos);        
        Button btnAgregarCurso = new Button("+");
        //btnAgregarCurso.setPreferredH(45);
        btnAgregarCurso.setUIID("ButtonHamburguesaNuevoCurso");
        btnAgregarCurso.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt) {
                   new CursoNuevo(null, profesor, form);
            }
        });
        contenido.addComponent(btnAgregarCurso);
        
        Button btnHistorial = new Button("Historial");
        btnHistorial.setUIID("ButtonHamburguesaInicio");
        btnHistorial.setPreferredH(45);
        btnHistorial.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt) {               
                
                    new Historial(Display.getInstance().getCurrent(),profesor);
                
            }
        });
        contenido.addComponent(btnHistorial);
        
        Button btnConfiguracion = new Button("Configuración");
        btnConfiguracion.setUIID("ButtonHamburguesaConfiguracion");
        //btnConfiguracion.setPreferredH(45);
        contenido.addComponent(btnConfiguracion);
        
        Button btnReportar = new Button("Reportar fallas");
        btnReportar.setUIID("ButtonHamburguesaConfiguracion");
        btnReportar.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt) {
             new ReportarFallas(Display.getInstance().getCurrent(),profesor);
            }
        });
        btnReportar.setPreferredH(45);
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
            //btnPeriodo.setPreferredH(45);
            btnPeriodo.addActionListener(new ActionListener() 
{                public void actionPerformed(ActionEvent evt) {
                    loadCursos((Container)contPeriodos.getParent().getComponentAt(5), periodo, profesor);
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
    
    private static void loadCursos(final Container contCursos, Periodo periodo, final Profesor profesor){
        contCursos.removeAll();
        try{     
                for(int idInstancia : periodo.getInstancias()){
                    final InstanciaCurso instancia = Deposito.getInstanciaById(idInstancia);
                    Button btnInstancia = new Button(instancia.toString());
                    btnInstancia.setUIID("ButtonHamburguesaCurso");
                    btnInstancia.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                            new DetalleCurso(Display.getInstance().getCurrent(),profesor,instancia);
                        }
                    });
                    //btnInstancia.setPreferredH(45);
                    contCursos.addComponent(btnInstancia);
            
        }}
        catch(Exception e){}
        contCursos.animateLayout(0);
    
    }
}
