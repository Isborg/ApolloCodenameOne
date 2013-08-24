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
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
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
        dlg.getDialogComponent().setUIID("DialogHamburguesa");
        dlg.setDisposeWhenPointerOutOfBounds(true);
        Container contenido = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        contenido.setPreferredH(Display.getInstance().getDisplayHeight());
        contenido.setPreferredW((int)((((double)Display.getInstance().getDisplayWidth()) / 4) * 3));

        Container contBtnInicio = new Container(new BorderLayout());
        Button btnInicio = new Button("Inicio");
        btnInicio.setUIID("ButtonHamburguesaNormal");
        btnInicio.setPreferredH((int)(((double)Display.getInstance().getDisplayHeight())/460*30));
        contBtnInicio.addComponent(BorderLayout.CENTER, btnInicio);
        Button iconInicio = new Button(" ");
        iconInicio.setUIID("ButtonHamburguesaAmarillo");
        iconInicio.setPreferredW((int)(((double)Display.getInstance().getDisplayWidth())/320*30));
        contBtnInicio.addComponent(BorderLayout.EAST, iconInicio);
        contenido.addComponent(contBtnInicio);

        Label lblPeriodos = new Label("Periodos");
        lblPeriodos.setUIID("LabelTitulosHamburguesa");
        contenido.addComponent(lblPeriodos);

        Container contPeriodos = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        loadPeriodos(contPeriodos, profesor, form);
        contenido.addComponent(contPeriodos);

        Container contBtnAgregarPeriodo = new Container(new FlowLayout());
        Button btnAgregarPeriodo = new Button(" ");
        btnAgregarPeriodo.setPreferredH((int)(((double)Display.getInstance().getDisplayHeight())/460*30));
        btnAgregarPeriodo.setPreferredW((int)(((double)Display.getInstance().getDisplayHeight())/320*76));
        btnAgregarPeriodo.setUIID("ButtonHamburguesaAgregarPeriodo");
        btnAgregarPeriodo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                PeriodoX periodoX = new PeriodoX(form, profesor, null);
            }
        });
        contBtnAgregarPeriodo.addComponent(btnAgregarPeriodo);
        contenido.addComponent(contBtnAgregarPeriodo);
        Label lblCursos = new Label("Cursos");
        lblCursos.setUIID("LabelTitulosHamburguesa");
        contenido.addComponent(lblCursos);
        Container contCursos = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        try {
            Periodo primerPeriodo = Deposito.getPeriodoById(profesor.getPeriodos().get(0));
            loadCursos(contCursos, primerPeriodo, profesor);
            periodoSelect = primerPeriodo;
        } catch (Exception e) {
        }
        contenido.addComponent(contCursos);
        Container contBtnAgregarCurso = new Container(new FlowLayout());
        Button btnAgregarCurso = new Button(" ");
        btnAgregarCurso.setPreferredH((int)(((double)Display.getInstance().getDisplayHeight())/460*30));
        btnAgregarCurso.setPreferredW((int)(((double)Display.getInstance().getDisplayHeight())/320*76));
        btnAgregarCurso.setUIID("ButtonHamburguesaAgregarCurso");
        btnAgregarCurso.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                new CursoNuevo(periodoSelect, profesor, form);
            }
        });
        contBtnAgregarCurso.addComponent(btnAgregarCurso);
        contenido.addComponent(contBtnAgregarCurso);

        Container contBtnHistorial = new Container(new BorderLayout());
        ActionListener historialActionListener = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                new Historial(Display.getInstance().getCurrent(), profesor);
            }
        };
        Button btnHistorial = new Button("Historial");
        btnHistorial.addActionListener(historialActionListener);
        btnHistorial.setUIID("ButtonHamburguesaNormal");
        btnHistorial.setPreferredH((int)(((double)Display.getInstance().getDisplayHeight())/460*30));
        contBtnHistorial.addComponent(BorderLayout.CENTER, btnHistorial);
        Button iconHistorial = new Button(" ");
        iconHistorial.addActionListener(historialActionListener);
        iconHistorial.setUIID("ButtonHamburguesaRojo");
        iconHistorial.setPreferredW((int)(((double)Display.getInstance().getDisplayWidth())/320*30));
        contBtnHistorial.addComponent(BorderLayout.EAST, iconHistorial);
        contenido.addComponent(contBtnHistorial);

        dlg.addComponent(contenido);
        dlg.show();
    }

    private static void loadPeriodos(final Container contPeriodos, final Profesor profesor, final Form form) {
        contPeriodos.removeAll();
        for (int idPeriodo : profesor.getPeriodos()) {
            Container row = new Container(new BorderLayout());
            final Periodo periodo = Deposito.getPeriodoById(idPeriodo);

            final Button btnPeriodo = new Button(periodo.toString());
            btnPeriodo.setUIID("ButtonHamburguesaNormal");
            btnPeriodo.setPreferredH((int)(((double)Display.getInstance().getDisplayHeight())/460*30));
            btnPeriodo.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    loadCursos((Container)contPeriodos.getParent().getComponentAt(5), periodo, profesor);
                    for (int i = 0; i < contPeriodos.getComponentCount(); i++) {
                        contPeriodos.getComponentAt(i).setUIID("ButtonHamburguesaNormal");
                    }
                    periodoSelect = periodo;
                    // Este UIID debe ser el de SELECTED
                    btnPeriodo.setUIID("ButtonHamburguesaNormal");
                }
            });
            row.addComponent(BorderLayout.CENTER, btnPeriodo);
            Button btnEditar = new Button(" ");
            btnEditar.setPreferredW((int)(((double)Display.getInstance().getDisplayHeight())/320*30));
            btnEditar.setUIID("ButtonHamburguesaEditarPeriodo");
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
                btnInstancia.setUIID("ButtonHamburguesaNormal");
                btnInstancia.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        new DetalleCurso(Display.getInstance().getCurrent(), profesor, instancia);
                    }
                });

                Button btnEliminar = new Button(" ");
                btnEliminar.setPreferredW((int)(((double)Display.getInstance().getDisplayHeight())/320*30));
                btnEliminar.setUIID("ButtonHamburguesaEliminarCurso");
                btnEliminar.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {                        
                        final Dialog dia = new Dialog();
                        dia.getDialogComponent().setUIID("ContainerFondoGris");
                        dia.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
                        Container btnContainer = new Container(new GridLayout(1, 2));
                        btnContainer.setPreferredH((int)(((double)Display.getInstance().getDisplayHeight())/460*35));
                        btnContainer.setUIID("ContainerBotonesDialogo");
                        Button btnAceptar = new Button("Aceptar");
                        btnAceptar.setUIID("ButtonVerde");
                        btnAceptar.addActionListener(new ActionListener() {

                            public void actionPerformed(ActionEvent evt) {
                                Deposito.eliminarInstancia(instancia.getId());
                                loadCursos(contCursos, periodoSelect, profesor);
                                dia.dispose();
                            }
                        });
                        Button btnCancelar = new Button("Cancelar");
                        btnCancelar.setUIID("ButtonRojo");
                        btnCancelar.addActionListener(new ActionListener() {

                            public void actionPerformed(ActionEvent evt) {
                                dia.dispose();
                            }
                        });
                        TextArea lblConfirmacion = new TextArea("¿Seguro de que desea eliminar?");
                        lblConfirmacion.setUIID("TextAreaGrisDialogo");

                        dia.addComponent(lblConfirmacion);
                        btnContainer.addComponent(btnAceptar);
                        btnContainer.addComponent(btnCancelar);
                        dia.addComponent(btnContainer);
                        dia.setDisposeWhenPointerOutOfBounds(true);
                        dia.show((int)(((double)Display.getInstance().getDisplayHeight())/460*190),
                                (int)(((double)Display.getInstance().getDisplayHeight())/460*190),
                                (int)(((double)Display.getInstance().getDisplayWidth())/460*20),
                                (int)(((double)Display.getInstance().getDisplayWidth())/460*20));
                    }
                });
                row.addComponent(BorderLayout.EAST, btnEliminar);
                btnInstancia.setPreferredH((int)(((double)Display.getInstance().getDisplayHeight())/460*30));
                row.addComponent(BorderLayout.CENTER, btnInstancia);
                contCursos.addComponent(row);

            }
        } catch (Exception e) {
        }

        contCursos.animateLayout(0);

    }
}
