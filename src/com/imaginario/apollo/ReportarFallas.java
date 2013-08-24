/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imaginario.apollo;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextArea;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.imaginario.apollo.entidades.Periodo;
import com.imaginario.apollo.entidades.Profesor;

/**
 *
 * @author Ricardo
 */
public class ReportarFallas extends BaseForm {
    
    public ReportarFallas(Form _parent,Profesor _profeP){

        iniciarForm("Reportar Falla", _parent,_profeP);
        Container contenido = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        final TextArea texto = new TextArea();
        contenido.addComponent(texto);
        Container botones = new Container(new GridLayout (1,2));
        Button btnAceptar = new Button("Aceptar");
        btnAceptar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                
                Dialog dlgMensaje = new Dialog("La falla ha sido reportada");
                dlgMensaje.setDisposeWhenPointerOutOfBounds(true);
                dlgMensaje.show();
                
            }
        });
        Button btnCancelar = new Button("Cancelar");
        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                
            }
        });
        botones.addComponent(btnAceptar);
        botones.addComponent(btnCancelar);
        contenido.addComponent(botones);
        getCurrent().addComponent(BorderLayout.CENTER, contenido);
        getCurrent().show();
                
    }
       
}
