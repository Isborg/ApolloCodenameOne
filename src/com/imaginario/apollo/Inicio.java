/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imaginario.apollo;

import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.imaginario.apollo.entidades.Profesor;

/**
 *
 * @author Ismael
 */
public class Inicio extends BaseForm {
    
    public Inicio(Profesor profesor) {
        iniciarForm("Inicio", null, profesor);
        Container c=new Container(new BoxLayout(BoxLayout.Y_AXIS));
                
                getCurrent().addComponent(BorderLayout.CENTER,c);
        getCurrent().show();
    }
    
}
