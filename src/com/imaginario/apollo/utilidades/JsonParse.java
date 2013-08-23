/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imaginario.apollo.utilidades;

import com.imaginario.apollo.entidades.InstanciaCurso;
import com.imaginario.apollo.entidades.Periodo;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

/**
 *
 * @author Daniel
 */
public class JsonParse {
   

    public JsonParse(Periodo periodo) throws JSONException {
        JSONObject obj= new JSONObject();
        JSONArray listCursos= new JSONArray();
        
        
            for (InstanciaCurso instancia : Deposito.getInstanciasByPeriodo(periodo.getId())) {                
            listCursos.put(instancia.toString());            
            }
         obj.put(periodo.toString(), listCursos);
        
        System.out.println(obj);   
    }
   
    
    
}
