/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imaginario.apollo;

import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
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
import com.codename1.ui.layouts.GridLayout;
import com.imaginario.apollo.entidades.Curso;
import com.imaginario.apollo.entidades.InstanciaCurso;
import com.imaginario.apollo.entidades.Periodo;
import com.imaginario.apollo.entidades.Profesor;
import com.imaginario.apollo.utilidades.Deposito;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Daniel
 */
public class CursoNuevo extends BaseForm {

    public CursoNuevo(final Periodo _periodo, final Profesor profesor, final Form _parent) {
        final Container container = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        container.setUIID("ContainerFondoGris");
        final ComboBox cbPlantilla = new ComboBox();
        iniciarForm("Nuevo curso", _parent, profesor);
        Label lblPlantilla = new Label("Seleccione una plantilla");
        lblPlantilla.setUIID("LabelFondoGris");
        cbPlantilla.setSelectedIndex(0);
        cbPlantilla.addItem("");
        cbPlantilla.addItem("+ Crear plantilla...");

        cbPlantilla.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (cbPlantilla.getSelectedIndex() == 1) {
                    final Dialog dia = new Dialog();
                    dia.getDialogComponent().setUIID("ContainerFondoGris");
                    dia.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
                    Label dlgTitulo = new Label("Nueva plantilla de curso");
                    dlgTitulo.setUIID("LabelFondoGrisTitulo");
                    dia.addComponent(dlgTitulo);
                    Label lblNombrePlantilla = new Label("Nombre: ");
                    lblNombrePlantilla.setUIID("LabelFondoGris");
                    final TextField txtNombre = new TextField();
                    txtNombre.setUIID("TextFieldBlanco");
                    Label lblDescripcionPlantilla = new Label("Desripcion: ");
                    lblDescripcionPlantilla.setUIID("LabelFondoGris");
                    final TextArea txtDescripcion = new TextArea();
                    txtDescripcion.setUIID("TextAreaBlanco");
                    dia.addComponent(lblNombrePlantilla);
                    dia.addComponent(txtNombre);
                    dia.addComponent(lblDescripcionPlantilla);
                    dia.addComponent(txtDescripcion);
                    cbPlantilla.repaint();
                    Container btnContainer = new Container(new GridLayout(1, 2));
                    btnContainer.setUIID("ContainerBotonesDialogo");
                    btnContainer.setPreferredH((int)(((double)Display.getInstance().getDisplayHeight())/460*35));
                    Button btnAceptar = new Button("Aceptar");
                    btnAceptar.setUIID("ButtonVerde");
                    btnAceptar.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {

                            Curso curso = new Curso(-1,txtNombre.getText(),txtDescripcion.getText(),profesor.getId());
                            curso.guardarEnStorage();
                            for (int i =cbPlantilla.getModel().getSize(); i >= 0; i--) {
                                cbPlantilla.getModel().removeItem(i);
                            }
                           
                            for (Curso c : Deposito.getCursosByProfesor(profesor.getId())) {
                                cbPlantilla.getModel().addItem(c);
                            }
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
                   
                   
                    btnContainer.addComponent(btnAceptar);
                    btnContainer.addComponent(btnCancelar);
                    dia.addComponent(btnContainer);
                    dia.setDisposeWhenPointerOutOfBounds(true);
                    dia.show((int)(((double)Display.getInstance().getDisplayHeight())/460*125),
                                (int)(((double)Display.getInstance().getDisplayHeight())/460*125),
                                (int)(((double)Display.getInstance().getDisplayWidth())/460*20),
                                (int)(((double)Display.getInstance().getDisplayWidth())/460*20));


                }
            }
        });
                for (Curso c : Deposito.getCursosByProfesor(profesor.getId())) {
                cbPlantilla.addItem(c);
            }        
        final Button btnPLantillaMngr = new Button("Administrar Plantilla");
        btnPLantillaMngr.setUIID("ButtonAmarillo");
        btnPLantillaMngr.setPreferredH((int)(((double)Display.getInstance().getDisplayHeight())/460*35));
        btnPLantillaMngr.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if(cbPlantilla.getSelectedItem() != null){              
                    final Dialog dia = new Dialog();
                    dia.getDialogComponent().setUIID("ContainerFondoGris");
                    dia.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
                    Label dlgTitulo = new Label("Editar plantilla de curso");
                    dlgTitulo.setUIID("LabelFondoGrisTitulo");
                    dia.addComponent(dlgTitulo);
                    Label lblnombre = new Label("Nombre ");
                    lblnombre.setUIID("LabelFondoGris");
                    final TextField txtNombre = new TextField();
                    txtNombre.setUIID("TextFieldBlanco");
                    txtNombre.setText(cbPlantilla.getSelectedItem().toString());
                    final Label lblDescripcion = new Label("Desripción ");
                    lblDescripcion.setUIID("LabelFondoGris");
                    final TextArea txtDescripcion = new TextArea(((Curso) cbPlantilla.getSelectedItem()).getDescripcion());
                    txtDescripcion.setUIID("TextAreaBlanco");
                    dia.addComponent(lblnombre);
                    dia.addComponent(txtNombre);
                    dia.addComponent(lblDescripcion);
                    dia.addComponent(txtDescripcion);
                    Container btnContainer = new Container(new GridLayout(1, 2));
                    btnContainer.setUIID("ContainerBotonesDialogo");
                    btnContainer.setPreferredH((int)(((double)Display.getInstance().getDisplayHeight())/460*35));
                    Button btnAceptar = new Button("Aceptar");
                    btnAceptar.setUIID("ButtonVerde");
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
                     Button btnEliminar= new Button("Eliminar");
                     btnEliminar.setUIID("ButtonRojo");
                        btnEliminar.addActionListener(new ActionListener() {

                            public void actionPerformed(ActionEvent evt) {
                                Deposito.eliminarCurso(((Curso)cbPlantilla.getSelectedItem()).getId());
                                for (int i =cbPlantilla.getModel().getSize(); i >= 0; i--) {
                                    cbPlantilla.getModel().removeItem(i);
                                }

                                for (Curso c : Deposito.getCursosByProfesor(profesor.getId())) {
                                    cbPlantilla.getModel().addItem(c);
                                }
                                dia.dispose();
                            }
                        });
                        btnContainer.addComponent(btnEliminar);
                    dia.addComponent(btnContainer);

                    dia.setDisposeWhenPointerOutOfBounds(true);
                    dia.show((int)(((double)Display.getInstance().getDisplayHeight())/460*125),
                                    (int)(((double)Display.getInstance().getDisplayHeight())/460*125),
                                    (int)(((double)Display.getInstance().getDisplayWidth())/460*20),
                                    (int)(((double)Display.getInstance().getDisplayWidth())/460*20));
                }
            }
        });
        Label lblFechaIncio = new Label("Fecha de inicio");
        lblFechaIncio.setUIID("LabelFondoGris");
        
        final Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        Container datePicker = new Container(new GridLayout(3, 3));
        Button masDia = new Button(" ");
        masDia.setUIID("ButtonIconMas");
        final TextField txtDia = new TextField(cal.get(Calendar.DAY_OF_MONTH)+"");
        txtDia.setUIID("TextFieldBlanco");
        txtDia.setEditable(false);
        Button menosDia = new Button(" ");
        menosDia.setUIID("ButtonIconMenos");
        masDia.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                int tempMonth = cal.get(Calendar.MONTH);
                cal.add(Calendar.DATE, 1);
                if(tempMonth != cal.get(Calendar.MONTH)){
                    cal.add(Calendar.DATE, -1);
                    cal.set(Calendar.DAY_OF_MONTH, 1);
                }
                txtDia.setText(cal.get(Calendar.DAY_OF_MONTH)+"");
            }
        });
        menosDia.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if(cal.get(Calendar.DAY_OF_MONTH) != 1){
                    cal.add(Calendar.DATE, -1);
                }
                else{
                    cal.add(Calendar.MONTH, 1);
                    cal.add(Calendar.DATE, -1);
                }
                txtDia.setText(cal.get(Calendar.DAY_OF_MONTH)+"");
            }
        });
        Button masMes = new Button(" ");
        masMes.setUIID("ButtonIconMas");
        final TextField txtMes = new TextField(cal.get(Calendar.MONTH)+1+"");
        txtMes.setUIID("TextFieldBlanco");
        txtMes.setEditable(false);
        Button menosMes = new Button(" ");
        menosMes.setUIID("ButtonIconMenos");
        masMes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if(cal.get(Calendar.MONTH) != 11){
                    cal.add(Calendar.MONTH, 1);
                }
                else{
                    cal.set(Calendar.MONTH, 0);
                }
                txtMes.setText(cal.get(Calendar.MONTH)+1+"");
            }
        });
        menosMes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if(cal.get(Calendar.MONTH) != 0){
                    cal.add(Calendar.MONTH, -1);
                }
                else{
                    cal.set(Calendar.MONTH, 11);
                }
                txtMes.setText(cal.get(Calendar.MONTH)+1+"");
            }
        });
        Button masAnio = new Button(" ");
        masAnio.setUIID("ButtonIconMas");
        final TextField txtAnio = new TextField(cal.get(Calendar.YEAR)+"");
        txtAnio.setUIID("TextFieldBlanco");
        txtAnio.setEditable(false);
        Button menosAnio = new Button(" ");
        menosAnio.setUIID("ButtonIconMenos");
        masAnio.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                cal.add(Calendar.YEAR, 1);
                txtAnio.setText(cal.get(Calendar.YEAR)+"");
            }
        });
        menosAnio.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                cal.add(Calendar.YEAR, -1);
                txtAnio.setText(cal.get(Calendar.YEAR)+"");
            }
        });
        datePicker.addComponent(masDia);
        datePicker.addComponent(masMes);
        datePicker.addComponent(masAnio);
        datePicker.addComponent(txtDia);
        datePicker.addComponent(txtMes);
        datePicker.addComponent(txtAnio);
        datePicker.addComponent(menosDia);
        datePicker.addComponent(menosMes);
        datePicker.addComponent(menosAnio);
        /*
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        final TextField txtFechaInicio = new TextField(
                cal.get(Calendar.YEAR) + "-"
                + (cal.get(Calendar.MONTH) + 1) + "-"
                + cal.get(Calendar.DAY_OF_MONTH));
        txtFechaInicio.setUIID("TextFieldBlanco");
        */
        Label lblCantSemanas = new Label("Cantidad de semanas");
        lblCantSemanas.setUIID("LabelFondoGris");
        final TextField txtCantSemanas = new TextField();
        txtCantSemanas.setUIID("TextFieldBlanco");
        container.addComponent(lblCantSemanas);
        container.addComponent(txtCantSemanas);
        container.addComponent(lblFechaIncio);
        container.addComponent(datePicker);
        //container.addComponent(txtFechaInicio);
        container.addComponent(lblPlantilla);
        container.addComponent(cbPlantilla);
        container.addComponent(btnPLantillaMngr);
        final Button btnAceptar = new Button("Aceptar");
        btnAceptar.setUIID("ButtonVerde");
        btnAceptar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                /*
                SimpleDateFormat textFormat = new SimpleDateFormat("yyyy-MM-DD");
                String strFecha = txtFechaInicio.getText();
                Date fecha = null;
                try {
                    fecha = textFormat.parse(strFecha);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                */
                Calendar fecha = Calendar.getInstance();
                fecha.set(Calendar.YEAR, Integer.parseInt(txtAnio.getText()));
                fecha.set(Calendar.MONTH, Integer.parseInt(txtMes.getText()) - 1);
                fecha.set(Calendar.DAY_OF_MONTH, Integer.parseInt(txtDia.getText()));
                
                InstanciaCurso instanciaCurso;
                instanciaCurso = new InstanciaCurso(-1, fecha.getTime(), Byte.parseByte(txtCantSemanas.getText()),
                                 ((Curso) cbPlantilla.getSelectedItem()).getId(),_periodo.getId());
                instanciaCurso.guardarEnStorage();
                new DetalleCurso(getCurrent(), profesor, instanciaCurso);
            }
        });
        Button btnCancelar = new Button("Cancelar ");
        btnCancelar.setUIID("ButtonRojo");
        Container btnContainer = new Container(new GridLayout(1, 2));
        btnContainer.setPreferredH((int)(((double)Display.getInstance().getDisplayHeight())/460*35));
        btnContainer.addComponent(btnAceptar);
        btnContainer.addComponent(btnCancelar);


        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                getParent().show();
            }
        });
        container.addComponent(btnContainer);

        getCurrent().addComponent(BorderLayout.CENTER, container);
        getCurrent().show();
    }
}
