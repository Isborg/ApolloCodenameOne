/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imaginario.apollo;

import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
import com.imaginario.apollo.entidades.Curso;
import com.imaginario.apollo.entidades.Periodo;
import com.imaginario.apollo.entidades.Profesor;
import com.codename1.ui.Button;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.imaginario.apollo.entidades.InstanciaCurso;

/**
 *
 * @author Ismael
 */
public class DetalleCurso extends BaseForm {
    
    public DetalleCurso(Form _parent, Profesor profesor, InstanciaCurso instancia){
        iniciarForm(instancia.toString(), _parent, profesor);
        
        Container subTitulo = new Container(new BorderLayout());
        loadRecordatorios(subTitulo);
        getCurrent().addComponent(BorderLayout.CENTER, subTitulo);
        getCurrent().show();
    }
    
    public void loadRecordatorios(final Container cont){
        cont.removeAll();
        
        Container contTitle = new Container(new BorderLayout());
        Button btnLeft = new Button("<--");
        btnLeft.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                loadMateria(cont);
            }
        });
        contTitle.addComponent(BorderLayout.WEST, btnLeft);
        Label lblTitle = new Label("Recordatorios");
        contTitle.addComponent(BorderLayout.CENTER, lblTitle);
        Button btnRight = new Button("-->");
        btnRight.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                loadEstudiantes(cont);
            }
        });
        contTitle.addComponent(BorderLayout.EAST, btnRight);
        cont.addComponent(BorderLayout.NORTH, contTitle);
        
        Container contenido = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        contenido.addComponent(new Label("Recordatoriooos"));
        cont.addComponent(BorderLayout.CENTER, contenido);
        
        getCurrent().animateLayout(0);
    }
    
    public void loadEstudiantes(final Container cont){
        cont.removeAll();
        
        Container contTitle = new Container(new BorderLayout());
        Button btnLeft = new Button("<--");
        btnLeft.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                loadRecordatorios(cont);
            }
        });
        contTitle.addComponent(BorderLayout.WEST, btnLeft);
        Label lblTitle = new Label("Estudiantes");
        contTitle.addComponent(BorderLayout.CENTER, lblTitle);
        Button btnRight = new Button("-->");
        btnRight.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                loadAsignaciones(cont);
            }
        });
        contTitle.addComponent(BorderLayout.EAST, btnRight);
        cont.addComponent(BorderLayout.NORTH, contTitle);
        
        Container contenido = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        contenido.addComponent(new Label("Estudianteees"));
        cont.addComponent(BorderLayout.CENTER, contenido);
        
        getCurrent().animateLayout(0);
    }
    
    public void loadAsignaciones(final Container cont){
        cont.removeAll();
        
        Container contTitle = new Container(new BorderLayout());
        Button btnLeft = new Button("<--");
        btnLeft.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                loadEstudiantes(cont);
            }
        });
        contTitle.addComponent(BorderLayout.WEST, btnLeft);
        Label lblTitle = new Label("Asignaciones");
        contTitle.addComponent(BorderLayout.CENTER, lblTitle);
        Button btnRight = new Button("-->");
        btnRight.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                loadMateria(cont);
            }
        });
        contTitle.addComponent(BorderLayout.EAST, btnRight);
        cont.addComponent(BorderLayout.NORTH, contTitle);
        
        Container contenido = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        contenido.addComponent(new Label("Asignacioneees"));
        cont.addComponent(BorderLayout.CENTER, contenido);
        
        getCurrent().animateLayout(0);
    }
    
    public void loadMateria(final Container cont){
        cont.removeAll();
        
        Container contTitle = new Container(new BorderLayout());
        Button btnLeft = new Button("<--");
        btnLeft.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                loadAsignaciones(cont);
            }
        });
        contTitle.addComponent(BorderLayout.WEST, btnLeft);
        Label lblTitle = new Label("Materia");
        contTitle.addComponent(BorderLayout.CENTER, lblTitle);
        Button btnRight = new Button("-->");
        btnRight.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                loadRecordatorios(cont);
            }
        });
        contTitle.addComponent(BorderLayout.EAST, btnRight);
        cont.addComponent(BorderLayout.NORTH, contTitle);
        
        Container contenido = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        contenido.addComponent(new Label("Materiaaa"));
        cont.addComponent(BorderLayout.CENTER, contenido);
        
        getCurrent().animateLayout(0);
    }
    
}
