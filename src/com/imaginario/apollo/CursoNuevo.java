/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imaginario.apollo;

import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.imaginario.apollo.entidades.Curso;
import com.imaginario.apollo.entidades.InstanciaCurso;
import com.imaginario.apollo.entidades.Periodo;
import com.imaginario.apollo.entidades.Profesor;
import com.imaginario.apollo.utilidades.Deposito;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Daniel
 */
public class CursoNuevo extends BaseForm {

    public CursoNuevo(final Periodo _periodo, final Profesor profesor, final Form _parent) {
        final Container container = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        final ComboBox cbPlantilla = new ComboBox();
        iniciarForm("Nuevo curso", _parent, profesor);
        Label lblPlantilla = new Label("Seleccione una plantilla");
        cbPlantilla.setSelectedIndex(0);
        cbPlantilla.addItem("");
        cbPlantilla.addItem("+ Crear plantilla...");

        cbPlantilla.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (cbPlantilla.getSelectedIndex() == 1) {
                    //CursoPlantillaMngr curso=new CursoPlantillaMngr(getCurrent(), profesor); 
                    Container contPlantilla = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                    final Dialog dia = new Dialog("Nueva plantilla de curso");
                    Label lblNombrePlantilla = new Label("Nombre: ");
                    final TextField txtNombre = new TextField();
                    Label lblDescripcionPlantilla = new Label("Desripcion: ");
                    final TextArea txtDescripcion = new TextArea();
                    contPlantilla.addComponent(lblNombrePlantilla);
                    contPlantilla.addComponent(txtNombre);
                    contPlantilla.addComponent(lblDescripcionPlantilla);
                    contPlantilla.addComponent(txtDescripcion);
                    cbPlantilla.repaint();
                    Container btnContainer = new Container(new GridLayout(1, 2));
                    Button btnAceptar = new Button("Aceptar");
                    btnAceptar.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {

                            Curso curso = new Curso(-1,txtNombre.getText(),txtDescripcion.getText(),profesor.getId());
                            curso.guardarEnStorage();
                            cbPlantilla.repaint();
                            dia.dispose();
                        }
                    });
                    Button btnCancelar = new Button("Cancelar ");
                    btnCancelar.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                            dia.dispose();
                        }
                    });
                    btnContainer.addComponent(btnCancelar);
                    btnContainer.addComponent(btnAceptar);
                    contPlantilla.addComponent(btnContainer);
                    dia.addComponent(contPlantilla);
                    dia.setDisposeWhenPointerOutOfBounds(true);
                    dia.show();


                }
            }
        });
                for (Curso c : Deposito.getCursosByProfesor(profesor.getId())) {
                cbPlantilla.addItem(c);
            }        
        final Button btnPLantillaMngr = new Button("Administrar Plantilla");
        btnPLantillaMngr.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                //   new CursoPlantillaMngr(getCurrent(), profesor);                
                final Dialog dia = new Dialog();

                Container diaContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                Label lblnombre = new Label("Nombre: ");
                final TextField txtNombre = new TextField();
                txtNombre.setText(cbPlantilla.getSelectedItem().toString());
                final Label lblDescripcion = new Label("Desripcion: ");
                final TextArea txtDescripcion = new TextArea(((Curso) cbPlantilla.getSelectedItem()).getDescripcion());
                diaContainer.addComponent(new Label("Editar plantilla"));
                diaContainer.addComponent(new Label(""));
                diaContainer.addComponent(lblnombre);
                diaContainer.addComponent(txtNombre);
                diaContainer.addComponent(lblDescripcion);
                diaContainer.addComponent(txtDescripcion);
                Button btnCancelar = new Button("Cancelar ");
                btnCancelar.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        dia.dispose();
                    }
                });
                Container btnContainer = new Container(new GridLayout(1, 2));
                btnContainer.addComponent(btnCancelar);

                Button btnAceptar = new Button("Aceptar");
                btnAceptar.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {

                        Curso curso = ((Curso) cbPlantilla.getSelectedItem());
                        curso.setNombre(txtNombre.getText());
                        curso.setDescripcion(txtDescripcion.getText());
                        curso.guardarEnStorage();
                        dia.dispose();
                    }
                });
                btnContainer.addComponent(btnAceptar);
                dia.addComponent(diaContainer);
                dia.addComponent(btnContainer);

                dia.setDisposeWhenPointerOutOfBounds(true);
                dia.show();
            }
        });
        Label lblFechaIncio = new Label("Fecha de inicio");
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        final TextField txtFechaInicio = new TextField(
                cal.get(Calendar.YEAR) + "-"
                + (cal.get(Calendar.MONTH) + 1) + "-"
                + cal.get(Calendar.DAY_OF_MONTH));
        Label lblCantSemanas = new Label("Cantidad de semanas");
        final TextField txtCantSemanas = new TextField();
        container.addComponent(lblCantSemanas);
        container.addComponent(txtCantSemanas);
        container.addComponent(lblFechaIncio);
        container.addComponent(txtFechaInicio);
        container.addComponent(lblPlantilla);
        container.addComponent(cbPlantilla);
        container.addComponent(btnPLantillaMngr);
        final Button btnAceptar = new Button("Aceptar");
        btnAceptar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                SimpleDateFormat textFormat = new SimpleDateFormat("yyyy-MM-DD");
                String strFecha = txtFechaInicio.getText();
                Date fecha = null;
                try {
                    fecha = textFormat.parse(strFecha);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                InstanciaCurso instanciaCurso = new InstanciaCurso(-1, fecha, txtCantSemanas.getText().getBytes()[0], 
                                                ((Curso) cbPlantilla.getSelectedItem()).getId(),_periodo.getId());
                instanciaCurso.guardarEnStorage();
                new DetalleCurso(getCurrent(), profesor, instanciaCurso);
            }
        });
        Button btnCancelar = new Button("Cancelar ");
        Container btnContainer = new Container(new GridLayout(1, 2));
        btnContainer.addComponent(btnAceptar);
        btnContainer.addComponent(btnCancelar);


        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                //dia.dispose();
            }
        });
        container.addComponent(btnContainer);

        getCurrent().addComponent(BorderLayout.CENTER, container);
        getCurrent().show();
    }
}
