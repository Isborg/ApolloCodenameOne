/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imaginario.apollo;

import com.codename1.ui.Button;
import com.codename1.ui.Calendar;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.imaginario.apollo.entidades.Profesor;

/**
 *
 * @author Daniel
 */
public class CursoPlantillaMngr extends BaseForm {

    public CursoPlantillaMngr(Form _parent, final Profesor profesor) {
        iniciarForm("Administrador de plantillas", _parent, profesor);
        Container diaContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));

        Label lblnombre = new Label("Nombre: ");
        TextField txtNombre = new TextField();
        Label lblDescripcion = new Label("Desripcion: ");
        TextField txtDescripcion = new TextField();

        diaContainer.addComponent(lblnombre);
        diaContainer.addComponent(txtNombre);
        diaContainer.addComponent(lblDescripcion);
        diaContainer.addComponent(txtDescripcion);

        Button btnEditar = new Button("Editar");
        btnEditar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                Dialog dia = new Dialog();

                Container diaContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                Label lblnombre = new Label("Nombre: ");
                TextField txtNombre = new TextField();
                Label lblDescripcion = new Label("Desripcion: ");
                TextField txtDescripcion = new TextField();
                diaContainer.addComponent(new Label("Editar plantilla"));
                diaContainer.addComponent(new Label(""));
                diaContainer.addComponent(lblnombre);
                diaContainer.addComponent(txtNombre);
                diaContainer.addComponent(lblDescripcion);
                diaContainer.addComponent(txtDescripcion);
                dia.addComponent(diaContainer);
                dia.setDisposeWhenPointerOutOfBounds(true);
                dia.show();
            }
        });

        diaContainer.addComponent(btnEditar);


        getCurrent().addComponent(BorderLayout.CENTER, diaContainer);
        getCurrent().show();
    }
}
