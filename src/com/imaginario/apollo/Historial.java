/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imaginario.apollo;

import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.imaginario.apollo.entidades.InstanciaCurso;
import com.imaginario.apollo.entidades.Periodo;
import com.imaginario.apollo.entidades.Profesor;
import com.imaginario.apollo.utilidades.Deposito;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

/**
 *
 * @author Daniel
 */
public class Historial extends BaseForm{

    public Historial(Form _parent,Profesor _Profesor) {
         final Container container = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        iniciarForm("Historial", _parent, _Profesor);        
        ComboBox cbPeriodos= new  ComboBox();
        for (Periodo periodo : Deposito.getPeriodosByProfesor(_Profesor.getId())) {
            cbPeriodos.addItem(periodo.toString());
            
    }
        
        container.addComponent(cbPeriodos);
        getCurrent().addComponent(BorderLayout.CENTER, container);
       getCurrent().show();
         }
//    public static void JsonParse (Periodo periodo) throws JSONException{
//        JSONObject obj= new JSONObject();
//        JSONArray listCursos= new JSONArray();        
//       for (InstanciaCurso instancia : Deposito.getInstanciasByPeriodo(periodo.getId())) {                
//            listCursos.put(instancia.toString());            
//            }
//         obj.put(periodo.toString(), listCursos);
//        
//         System.out.println(obj);
//    }
}
        
   
    
    

    
    

