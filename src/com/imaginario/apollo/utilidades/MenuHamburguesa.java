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
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
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

    private static Periodo periodoSelect;

    public static void mostrar(final Form form, final Profesor profesor) {
        final Dialog dlg = new Dialog();
        dlg.setDialogPosition(BorderLayout.WEST);
        dlg.setDisposeWhenPointerOutOfBounds(true);
        Container contenido = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        contenido.setPreferredH(form.getHeight());
        contenido.setPreferredW(form.getWidth() / 2);

        Button btnInicio = new Button("Inicio");
        btnInicio.setUIID("ButtonHamburguesaInicio");
        //btnInicio.setPreferredH(45);
        System.out.println("" + btnInicio.getPreferredH());
        System.out.println("" + btnInicio.getPreferredW());
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
        try {
            Periodo primerPeriodo = Deposito.getPeriodoById(profesor.getPeriodos().get(0));
            loadCursos(contCursos, primerPeriodo, profesor);
            periodoSelect = primerPeriodo;
        } catch (Exception e) {
        }
        contenido.addComponent(contCursos);
        Button btnAgregarCurso = new Button("+");
        //btnAgregarCurso.setPreferredH(45);
        btnAgregarCurso.setUIID("ButtonHamburguesaNuevoCurso");
        btnAgregarCurso.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                new CursoNuevo(periodoSelect, profesor, form);
            }
        });
        contenido.addComponent(btnAgregarCurso);

        Button btnHistorial = new Button("Historial");
        btnHistorial.setUIID("ButtonHamburguesaInicio");
        btnHistorial.setPreferredH(45);
        btnHistorial.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {

                new Historial(Display.getInstance().getCurrent(), profesor);

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
                new ReportarFallas(Display.getInstance().getCurrent(), profesor);
            }
        });
        btnReportar.setPreferredH(45);
        contenido.addComponent(btnReportar);

        dlg.addComponent(contenido);
        dlg.show();
    }

    private static void loadPeriodos(final Container contPeriodos, final Profesor profesor, final Form form) {
        contPeriodos.removeAll();
        for (int idPeriodo : profesor.getPeriodos()) {
            Container row = new Container(new BorderLayout());
            final Periodo periodo = Deposito.getPeriodoById(idPeriodo);

            final Button btnPeriodo = new Button(periodo.toString());
            btnPeriodo.setUIID("ButtonHamburguesaPeriodoUnselected");
            //btnPeriodo.setPreferredH(45);
            btnPeriodo.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    loadCursos((Container) contPeriodos.getParent().getComponentAt(5), periodo, profesor);
                    for (int i = 0; i < contPeriodos.getComponentCount(); i++) {
                        contPeriodos.getComponentAt(i).setUIID("ButtonHamburguesaPeriodoUnselected");
                    }
                    periodoSelect = periodo;
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

    private static void loadCursos(final Container contCursos, Periodo periodo, final Profesor profesor) {
        contCursos.removeAll();

        try {
            for (int idInstancia : periodo.getInstancias()) {
                Container row = new Container(new BorderLayout());
                final InstanciaCurso instancia = Deposito.getInstanciaById(idInstancia);
                Button btnInstancia = new Button(instancia.toString());
                btnInstancia.setUIID("ButtonHamburguesaCurso");
                btnInstancia.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        new DetalleCurso(Display.getInstance().getCurrent(), profesor, instancia);
                    }
                });

                Button btnEditar = new Button("x");
                btnEditar.setUIID("ButtonHamburguesaCurso");
                btnEditar.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                       
                        //Container diaCont= new Container(new BorderLayout());
                        
                        final Dialog dia = new Dialog("Eliminar curso: " + instancia.toString());
                        Container diaContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                        Container btnContainer = new Container(new GridLayout(1, 2));
                        Button btnAceptar = new Button("Aceptar");
                        btnAceptar.addActionListener(new ActionListener() {

                            public void actionPerformed(ActionEvent evt) {
                          Deposito.eliminarInstancia(instancia.getId());
                        loadCursos(contCursos, periodoSelect, profesor);
                        dia.dispose();
                            }
                        });
                        Button btnCancelar = new Button("Cancelar");
                        btnCancelar.addActionListener(new ActionListener() {

                            public void actionPerformed(ActionEvent evt) {
                         dia.dispose();
                            }
                        });
                        Label lblConfirmacion = new Label("¿Está seguro que desea eliminar el curso y todos sus contenidos?");

                        diaContainer.addComponent(lblConfirmacion);
                        btnContainer.addComponent(btnAceptar);
                        btnContainer.addComponent(btnCancelar);
                        diaContainer.addComponent(btnContainer);
                        dia.addComponent(diaContainer);
                        dia.setDisposeWhenPointerOutOfBounds(true);
                        dia.show();
                    }
                });
                row.addComponent(BorderLayout.EAST, btnEditar);
                //btnInstancia.setPreferredH(45);
                row.addComponent(BorderLayout.CENTER, btnInstancia);
                contCursos.addComponent(row);

            }
        } catch (Exception e) {
        }

        contCursos.animateLayout(0);

    }
}
