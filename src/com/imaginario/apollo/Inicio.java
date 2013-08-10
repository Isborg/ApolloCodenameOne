/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imaginario.apollo;

import com.codename1.ui.Form;
import com.imaginario.apollo.entidades.Periodo;
import com.imaginario.apollo.entidades.Profesor;
import java.io.IOException;

/**
 *
 * @author Ismael
 */
public class Inicio extends BaseForm {
    
    public Inicio(Profesor profesor) {
        iniciarForm("Inicio", null, profesor);
        getCurrent().show();
    }
    
}
