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
import com.codename1.ui.Dialog;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.events.DataChangedListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.imaginario.apollo.entidades.InstanciaCurso;
import com.imaginario.apollo.entidades.Recordatorio;
import com.imaginario.apollo.utilidades.Deposito;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Ismael
 */
public class DetalleCurso extends BaseForm {
    
    public DetalleCurso(Form _parent, Profesor profesor, InstanciaCurso instancia){
        iniciarForm(instancia.toString(), _parent, profesor);
        
        Container subTitulo = new Container(new BorderLayout());
        loadPantallaRecordatorios(subTitulo, instancia);
        getCurrent().addComponent(BorderLayout.CENTER, subTitulo);
        getCurrent().show();
    }

    public DetalleCurso() {
    }
    
    
    public void loadPantallaRecordatorios(final Container cont, final InstanciaCurso instancia){
        cont.removeAll();
        
        Container contTitle = new Container(new BorderLayout());
        Button btnLeft = new Button("<--");
        btnLeft.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                loadPantallaMateria(cont, instancia);
            }
        });
        contTitle.addComponent(BorderLayout.WEST, btnLeft);
        Label lblTitle = new Label("Recordatorios");
        contTitle.addComponent(BorderLayout.CENTER, lblTitle);
        Button btnRight = new Button("-->");
        btnRight.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                loadPantallaEstudiantes(cont, instancia);
            }
        });
        contTitle.addComponent(BorderLayout.EAST, btnRight);
        cont.addComponent(BorderLayout.NORTH, contTitle);
        
        Container contenido = new Container(new BorderLayout());
        llenarRecordatorios(contenido, instancia);
        cont.addComponent(BorderLayout.CENTER, contenido);
        
        getCurrent().animateLayout(0);
    }
    
    public void llenarRecordatorios(final Container cont, InstanciaCurso instancia){
        cont.removeAll();
        Button btnNuevo = new Button("Agregar nuevo recordatorio");
        btnNuevo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                Dialog dlg = new Dialog();
                TextArea txt = new TextArea();
                dlg.addComponent(txt);
                final Calendar cal = Calendar.getInstance();
                cal.setTime(new Date());
                Container datePicker = new Container(new GridLayout(3, 3));
                Button masDia = new Button("+");
                final TextField txtDia = new TextField(cal.get(Calendar.DAY_OF_MONTH)+"");
                txtDia.setEditable(false);
                Button menosDia = new Button("-");
                masDia.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        int tempMonth = cal.get(Calendar.MONTH);
                        cal.add(Calendar.DATE, 1);
                        if(tempMonth != cal.get(Calendar.MONTH)){
                            cal.add(Calendar.DATE, -1);
                            cal.set(Calendar.DAY_OF_MONTH, 1);
                        }
                        txtDia.setText(cal.get(Calendar.DAY_OF_MONTH)+"");
                    }
                });
                menosDia.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if(cal.get(Calendar.DAY_OF_MONTH) != 1){
                            cal.add(Calendar.DATE, -1);
                        }
                        else{
                            cal.add(Calendar.MONTH, 1);
                            cal.add(Calendar.DATE, -1);
                        }
                        txtDia.setText(cal.get(Calendar.DAY_OF_MONTH)+"");
                    }
                });
                Button masMes = new Button("+");
                final TextField txtMes = new TextField(cal.get(Calendar.MONTH)+1+"");
                txtMes.setEditable(false);
                Button menosMes = new Button("-");
                masMes.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if(cal.get(Calendar.MONTH) != 12){
                            cal.add(Calendar.MONTH, 1);
                        }
                        else{
                            cal.set(Calendar.MONTH, 1);
                        }
                        txtMes.setText(cal.get(Calendar.MONTH)+1+"");
                    }
                });
                menosMes.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if(cal.get(Calendar.MONTH) != 1){
                            cal.add(Calendar.MONTH, -1);
                        }
                        else{
                            cal.set(Calendar.MONTH, 12);
                        }
                        txtMes.setText(cal.get(Calendar.MONTH)+1+"");
                    }
                });
                Button masAnio = new Button("+");
                final TextField txtAnio = new TextField(cal.get(Calendar.YEAR)+"");
                txtAnio.setEditable(false);
                Button menosAnio = new Button("-");
                masAnio.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        cal.add(Calendar.YEAR, 1);
                        txtAnio.setText(cal.get(Calendar.YEAR)+"");
                    }
                });
                menosAnio.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        cal.add(Calendar.YEAR, -1);
                        txtAnio.setText(cal.get(Calendar.YEAR)+"");
                    }
                });
                datePicker.addComponent(masDia);
                datePicker.addComponent(masMes);
                datePicker.addComponent(masAnio);
                datePicker.addComponent(txtDia);
                datePicker.addComponent(txtMes);
                datePicker.addComponent(txtAnio);
                datePicker.addComponent(menosDia);
                datePicker.addComponent(menosMes);
                datePicker.addComponent(menosAnio);
                dlg.addComponent(datePicker);
                dlg.show();
            }
        });
        cont.addComponent(BorderLayout.SOUTH, btnNuevo);
        Container subCont = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        ArrayList<Recordatorio> recordatorios = Deposito.getRecordatoriosByInstancia(instancia.getId());
        for(Recordatorio rec : recordatorios){
            TextArea txtRec = new TextArea(rec.getTexto());
            txtRec.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    
                }
            });
            subCont.addComponent(txtRec);
        }
        cont.addComponent(BorderLayout.CENTER, subCont);
    }
    
    public void loadPantallaEstudiantes(final Container cont, final InstanciaCurso instancia){
        cont.removeAll();
        
        Container contTitle = new Container(new BorderLayout());
        Button btnLeft = new Button("<--");
        btnLeft.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                loadPantallaRecordatorios(cont, instancia);
            }
        });
        contTitle.addComponent(BorderLayout.WEST, btnLeft);
        Label lblTitle = new Label("Estudiantes");
        contTitle.addComponent(BorderLayout.CENTER, lblTitle);
        Button btnRight = new Button("-->");
        btnRight.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                loadPantallaAsignaciones(cont, instancia);
            }
        });
        contTitle.addComponent(BorderLayout.EAST, btnRight);
        cont.addComponent(BorderLayout.NORTH, contTitle);
        
        Container contenido = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        final Container subContenido = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Container contSubTitle = new Container(new GridLayout(1, 2));
        Button tabNotas = new Button("Notas");
        tabNotas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                llenarNotas(subContenido, instancia);
            }
        });
        contSubTitle.addComponent(tabNotas);
        Button tabAsistencia = new Button("Asistencia");
        tabAsistencia.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                llenarAsistencias(subContenido, instancia);
            }
        });
        contSubTitle.addComponent(tabAsistencia);
        contenido.addComponent(contSubTitle);
        contenido.addComponent(subContenido);
        
        llenarNotas(subContenido, instancia);
        
        cont.addComponent(BorderLayout.CENTER, contenido);
        
        getCurrent().animateLayout(0);
    }
    
    public void llenarNotas(final Container cont, final InstanciaCurso instancia){
        cont.removeAll();
        cont.addComponent(new Label("Notas"));
        getCurrent().animateLayout(0);
    }
    
    public void llenarAsistencias(final Container cont, final InstanciaCurso instancia){
        cont.removeAll();
        cont.addComponent(new Label("Asistencia"));
        getCurrent().animateLayout(0);
    }
    
    public void loadPantallaAsignaciones(final Container cont, final InstanciaCurso instancia){
        cont.removeAll();
        
        Container contTitle = new Container(new BorderLayout());
        Button btnLeft = new Button("<--");
        btnLeft.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                loadPantallaEstudiantes(cont, instancia);
            }
        });
        contTitle.addComponent(BorderLayout.WEST, btnLeft);
        Label lblTitle = new Label("Asignaciones");
        contTitle.addComponent(BorderLayout.CENTER, lblTitle);
        Button btnRight = new Button("-->");
        btnRight.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                loadPantallaMateria(cont, instancia);
            }
        });
        contTitle.addComponent(BorderLayout.EAST, btnRight);
        cont.addComponent(BorderLayout.NORTH, contTitle);
        
        Container contenido = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        contenido.addComponent(new Label("Asignacioneees"));
        cont.addComponent(BorderLayout.CENTER, contenido);
        
        getCurrent().animateLayout(0);
    }
    
    public void loadPantallaMateria(final Container cont, final InstanciaCurso instancia){
        cont.removeAll();
        
        Container contTitle = new Container(new BorderLayout());
        Button btnLeft = new Button("<--");
        btnLeft.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                loadPantallaAsignaciones(cont, instancia);
            }
        });
        contTitle.addComponent(BorderLayout.WEST, btnLeft);
        Label lblTitle = new Label("Materia");
        contTitle.addComponent(BorderLayout.CENTER, lblTitle);
        Button btnRight = new Button("-->");
        btnRight.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                loadPantallaRecordatorios(cont, instancia);
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
