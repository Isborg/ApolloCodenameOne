/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imaginario.apollo;

import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
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
import com.imaginario.apollo.utilidades.MenuHamburguesa;
import java.io.IOException;
import java.util.Calendar;


/**
 *
 * @author Ismael
 */
public class PeriodoX extends BaseForm {
    
    public PeriodoX(Form _parent, Profesor profesor, Periodo periodo) {
        if(periodo == null){
            iniciarForm("Nuevo periodo", _parent, profesor);
        }
        else{
            iniciarForm("Editar periodo", _parent, profesor);
        }
        
        Container contenido = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        final ComboBox cbTipo = new ComboBox(new String[]{"Semestral","Cuatrimestral","Trimestral","Bimestral","Mensual"});
        final ComboBox cbNumero = new ComboBox(new Integer[]{1,2});
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
        contenido.addComponent(lblNumero);
        contenido.addComponent(cbNumero);
        Label lblInstitucion = new Label("Institución");
        contenido.addComponent(lblInstitucion);
        TextField txtInstitucion = new TextField();
        contenido.addComponent(txtInstitucion);
        Label lblAnio = new Label("Año");
        contenido.addComponent(lblAnio);
        TextField txtAnio = new TextField(Calendar.getInstance().get(Calendar.YEAR) + "");
        contenido.addComponent(txtAnio);
        Container contBotones = new Container(new GridLayout(1, 2));
        Button btnGuardar = new Button("Guardar");
        contBotones.addComponent(btnGuardar);
        Button btnEliminar = new Button("Eliminar");
        contBotones.addComponent(btnEliminar);
        contenido.addComponent(contBotones);
        getCurrent().addComponent(BorderLayout.CENTER, contenido);
        
        getCurrent().show();
    }
    
}
