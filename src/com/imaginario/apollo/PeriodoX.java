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
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.imaginario.apollo.entidades.Periodo;
import com.imaginario.apollo.entidades.Profesor;
import com.imaginario.apollo.utilidades.Deposito;
import com.imaginario.apollo.utilidades.MenuHamburguesa;
import java.io.IOException;
import java.util.Calendar;


/**
 *
 * @author Ismael
 */
public class PeriodoX extends BaseForm {
    
    public PeriodoX(Form _parent, final Profesor profesor, final Periodo periodo) {
        if(periodo == null){
            iniciarForm("Nuevo periodo", _parent, profesor);
        }
        else{
            iniciarForm("Editar periodo", _parent, profesor);
        }
        // Cambiar color a la barra de titulo
        Container titulo = (Container)((Container)getCurrent().getComponentAt(1)).getComponentAt(0);
        titulo.setUIID("ContainerTituloAzul");
        titulo.getComponentAt(1).setUIID("LabelTituloAzul");
        
        Container contenido = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        contenido.setUIID("ContainerFondoGris");
        final ComboBox cbTipo = new ComboBox(new String[]{"Semestral","Cuatrimestral","Trimestral","Bimestral","Mensual"});
        cbTipo.setUIID("ComboBoxBlanco");
        final ComboBox cbNumero = new ComboBox(new Integer[]{1,2});
        cbNumero.setUIID("ComboBoxBlanco");
        cbTipo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                for(int i = cbNumero.getModel().getSize() - 1; i >= 0; i--){
                    cbNumero.getModel().removeItem(i);
                }
                switch(cbTipo.getSelectedIndex()){
                    case 0:
                        for(int i = 1; i <= 2; i++){
                            cbNumero.getModel().addItem(i);
                        }
                        break;
                    case 1:
                        for(int i = 1; i <= 3; i++){
                            cbNumero.getModel().addItem(i);
                        }
                        break;
                    case 2:
                        for(int i = 1; i <= 4; i++){
                            cbNumero.getModel().addItem(i);
                        }
                        break;
                    case 3:
                        for(int i = 1; i <= 6; i++){
                            cbNumero.getModel().addItem(i);
                        }
                        break;
                    case 4:
                        for(int i = 1; i <= 12; i++){
                            cbNumero.getModel().addItem(i);
                        }
                        break;
                }
            }
        });
        cbTipo.setSelectedIndex(0);
        contenido.addComponent(cbTipo);
        Label lblNumero = new Label("Periodo número");
        lblNumero.setUIID("LabelFondoGris");
        contenido.addComponent(lblNumero);
        contenido.addComponent(cbNumero);
        Label lblInstitucion = new Label("Institución");
        lblInstitucion.setUIID("LabelFondoGris");
        contenido.addComponent(lblInstitucion);
        final TextField txtInstitucion = new TextField();
        txtInstitucion.setUIID("TextFieldBlanco");
        contenido.addComponent(txtInstitucion);
        Label lblAnio = new Label("Año");
        lblAnio.setUIID("LabelFondoGris");
        contenido.addComponent(lblAnio);
        final TextField txtAnio = new TextField(Calendar.getInstance().get(Calendar.YEAR) + "");
        txtAnio.setUIID("TextFieldBlanco");
        contenido.addComponent(txtAnio);
        Container contBotones = new Container(new GridLayout(1, 2));
        contBotones.setPreferredH((int)(((double)Display.getInstance().getDisplayHeight())/460*35));
        Button btnGuardar = new Button("Guardar");
        btnGuardar.setUIID("ButtonVerde");
        btnGuardar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                Periodo periodoGuardar = new Periodo();
                if(periodo == null){
                    periodoGuardar.setId(-1);
                }
                else{
                    periodoGuardar.setId(periodo.getId());
                    periodoGuardar.setInstancias(periodo.getInstancias());
                }
                periodoGuardar.setAnio(Short.parseShort(txtAnio.getText()));
                periodoGuardar.setNumero(Byte.parseByte(cbNumero.getSelectedItem().toString()));
                if (!"".equals(txtInstitucion.getText()) || !"".equals(txtAnio.getText())) {
                     periodoGuardar.setInstitucion(txtInstitucion.getText());
                periodoGuardar.setTipo(cbTipo.getSelectedItem().toString());
                periodoGuardar.setProfesor(profesor.getId());
                periodoGuardar.guardarEnStorage();
                Dialog dlg = new Dialog();
                dlg.addComponent(new Label("Periodo guardado exitosamente."));
                dlg.setTimeout(2000);                
                dlg.setDisposeWhenPointerOutOfBounds(true);
                dlg.show();
                MenuHamburguesa.mostrar(getCurrent(), profesor);                    
                } else {
                Dialog nullField= new Dialog();
                    nullField.addComponent(
                                    new Label("Debe llenar todos los campos"));
                    nullField.setTimeout(2000);                    
                    nullField.setDisposeWhenPointerOutOfBounds(true);
                    nullField.show();
                }
               
            }
        });
        contBotones.addComponent(btnGuardar);
        Button btnEliminar = new Button("Eliminar");
        btnEliminar.setUIID("ButtonRojo");
        btnEliminar.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt) {
                final Dialog dlg = new Dialog();
                Container btnContainer = new Container(new GridLayout(1, 2));
                Button btnAceptar= new Button("Aceptar");
                btnAceptar.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent evt) {
                 Deposito.eliminarPeriodo(periodo.getId());
                 dlg.dispose();
                    }
                });
                Button btnCancelar= new Button("Cancelar");
                btnCancelar.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent evt) {
                 
                 dlg.dispose();
                    }
                });
                
                btnContainer.addComponent(btnCancelar);
                btnContainer.addComponent(btnAceptar);
                dlg.addComponent(new Label("¿Esta seguro que desea eliminar el periodo?"));
                dlg.addComponent(btnContainer);
                
                           
                dlg.setDisposeWhenPointerOutOfBounds(true);
                dlg.showStretched(BorderLayout.CENTER, true);
                
                getParent().show();
            }
        });
        contBotones.addComponent(btnEliminar);
        contenido.addComponent(contBotones);
        getCurrent().addComponent(BorderLayout.CENTER, contenido);
       
        if(periodo != null){
            cbTipo.setSelectedIndex(0);
            cbNumero.setSelectedIndex(0);
            while(!periodo.getTipo().equals(cbTipo.getSelectedItem().toString())){
                cbTipo.setSelectedIndex(cbTipo.getSelectedIndex() + 1);
            }
            ((ActionListener)cbTipo.getActionListeners().get(0)).actionPerformed(null);
            while(periodo.getNumero() != cbNumero.getSelectedIndex() + 1){
                cbNumero.setSelectedIndex(cbNumero.getSelectedIndex() + 1);
            }
            txtAnio.setText(periodo.getAnio() + "");
            txtInstitucion.setText(periodo.getInstitucion());
        }
        
        getCurrent().show();
    }
    
}
