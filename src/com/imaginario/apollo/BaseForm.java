/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imaginario.apollo;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.imaginario.apollo.entidades.Curso;
import com.imaginario.apollo.entidades.Periodo;
import com.imaginario.apollo.entidades.Profesor;
import com.imaginario.apollo.utilidades.Deposito;
import com.imaginario.apollo.utilidades.MenuHamburguesa;
import java.io.IOException;

/**
 *
 * @author Ismael
 */
public class BaseForm {
    
    private Form parent;
    private Form current;
    
    public void iniciarForm(String titulo, Form _parent, final Profesor profesor) {
        setParent(_parent);
        setCurrent(new Form());
        getCurrent().setLayout(new BorderLayout());
        
        Container contTitle = new Container(new BorderLayout());
        Button btnMenu = new Button("Menú");
        btnMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                MenuHamburguesa.mostrar(getCurrent(), profesor);
            }
        });
        contTitle.addComponent(BorderLayout.WEST, btnMenu);
        Label lblTitle = new Label(titulo);
        contTitle.addComponent(BorderLayout.CENTER, lblTitle);
        getCurrent().addComponent(BorderLayout.NORTH, contTitle);
    }

    public Form getParent() {
        return parent;
    }

    public void setParent(Form parent) {
        this.parent = parent;
    }

    public Form getCurrent() {
        return current;
    }

    public void setCurrent(Form current) {
        this.current = current;
    }
    
}
