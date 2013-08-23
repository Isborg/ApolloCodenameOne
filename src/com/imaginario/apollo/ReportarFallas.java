/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imaginario.apollo;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.TextArea;
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
        TextArea texto = new TextArea();
        contenido.addComponent(texto);
        Container botones = new Container(new GridLayout (1,2));
        Button btnAceptar = new Button("Aceptar");
        Button btnCancelar = new Button("Cancelar");
        botones.addComponent(btnAceptar);
        botones.addComponent(btnCancelar);
        contenido.addComponent(botones);
        getCurrent().addComponent(BorderLayout.CENTER, contenido);
        getCurrent().show();
                
    }
       
}
