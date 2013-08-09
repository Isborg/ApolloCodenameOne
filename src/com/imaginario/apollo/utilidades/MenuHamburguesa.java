/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imaginario.apollo.utilidades;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.imaginario.apollo.entidades.Curso;
import com.imaginario.apollo.entidades.Periodo;
import com.imaginario.apollo.entidades.Profesor;
import java.io.InputStream;

/**
 *
 * @author Ismael
 */
public class MenuHamburguesa {
    
    public static void agregar(Form form, Profesor profesor){
        Command cmdInicio = new Command("");
        Button btnInicio = new Button("Inicio");
        cmdInicio.putClientProperty("SideComponent", btnInicio);
        form.addCommand(cmdInicio);
        
        Command cmdPeriodos = new Command("");
        Label lblPeriodos = new Label("Periodos");
        cmdPeriodos.putClientProperty("SideComponent", lblPeriodos);
        form.addCommand(cmdPeriodos);
        
        Container contCursos = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        
        for (int idPeriodo : profesor.getPeriodos()) {
            Periodo periodo = Deposito.getPeriodoById(idPeriodo);
            Command cmdPeriodo = new Command("");
            Button btnPeriodo = new Button(periodo.toString());
            cmdPeriodo.putClientProperty("SideComponent", btnPeriodo);
            form.addCommand(cmdPeriodo);
        }
        
        Command cmdAgregarPeriodo = new Command("");
        Button btnAgregarPeriodo = new Button("+");
        cmdAgregarPeriodo.putClientProperty("SideComponent", btnAgregarPeriodo);
        form.addCommand(cmdAgregarPeriodo);
        
        Command cmdCursos = new Command("");
        Label lblCursos = new Label("Cursos");
        cmdCursos.putClientProperty("SideComponent", lblCursos);
        form.addCommand(cmdCursos);
        
        try{
            Periodo primerPeriodo = Deposito.getPeriodoById(profesor.getPeriodos().get(0));
            for(int idCurso : primerPeriodo.getCursos()){
                Curso curso = Deposito.getCursoById(idCurso);
                Button btnCurso = new Button(curso.toString());
                contCursos.addComponent(btnCurso);
            }
        }
        catch(Exception e){}
        Command cmdContCursos = new Command("");
        cmdContCursos.putClientProperty("SideComponent", contCursos);
        form.addCommand(cmdContCursos);
    }
    
}
