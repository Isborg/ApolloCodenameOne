/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imaginario.apollo;

import com.codename1.io.Storage;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
import com.imaginario.apollo.entidades.Curso;
import com.imaginario.apollo.entidades.Periodo;
import com.imaginario.apollo.entidades.Profesor;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Component;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.events.DataChangedListener;
import com.codename1.ui.events.FocusListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.imaginario.apollo.entidades.Asignacion;
import com.imaginario.apollo.entidades.Asistencia;
import com.imaginario.apollo.entidades.Estudiante;
import com.imaginario.apollo.entidades.InstanciaCurso;
import com.imaginario.apollo.entidades.Materia;
import com.imaginario.apollo.entidades.Nota;
import com.imaginario.apollo.entidades.Recordatorio;
import com.imaginario.apollo.utilidades.Deposito;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

/**
 *
 * @author Ismael
 */
public class DetalleCurso extends BaseForm {

    private int semanaActualMateria=1;

    public DetalleCurso(Form _parent, Profesor profesor, InstanciaCurso instancia) {
        iniciarForm(instancia.toString(), _parent, profesor);

        Container subTitulo = new Container(new BorderLayout());
        loadPantallaRecordatorios(subTitulo, instancia);
        getCurrent().addComponent(BorderLayout.CENTER, subTitulo);
        getCurrent().show();
    
    
    }

    public void loadPantallaRecordatorios(final Container cont, final InstanciaCurso instancia) {
        cont.removeAll();

        Container contTitle = new Container(new BorderLayout());
        contTitle.setUIID("ContainerSubTituloGris");
        contTitle.setPreferredH((int)(((double)Display.getInstance().getDisplayHeight())/460*40));
        Button btnLeft = new Button(" ");
        btnLeft.setUIID("ButtonIconoLeft");
        btnLeft.setPreferredW((int)(((double)Display.getInstance().getDisplayWidth())/320*36));
        btnLeft.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                loadPantallaMateria(cont, instancia);
            }
        });
        contTitle.addComponent(BorderLayout.WEST, btnLeft);
        Label lblTitle = new Label("Recordatorios");
        lblTitle.setUIID("LabelSubTituloGris");
        contTitle.addComponent(BorderLayout.CENTER, lblTitle);
        Button btnRight = new Button(" ");
        btnRight.setUIID("ButtonIconoRight");
        btnRight.setPreferredW((int)(((double)Display.getInstance().getDisplayWidth())/320*36));
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
    
    public void llenarRecordatorios(final Container cont, final InstanciaCurso instancia){
        cont.removeAll();
        Button btnNuevo = new Button(" ");
        btnNuevo.setUIID("ButtonAgregarInferior");
        btnNuevo.setPreferredH((int)(((double)Display.getInstance().getDisplayHeight())/460*40));
        btnNuevo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                dialogoEditarRecordatorio(instancia, null, cont);
            }
        });
        cont.addComponent(BorderLayout.SOUTH, btnNuevo);
        Container subCont = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        ArrayList<Recordatorio> recordatorios = Deposito.getRecordatoriosByInstancia(instancia.getId());
        for(final Recordatorio rec : recordatorios){
            Container fila = new Container(new BorderLayout());
            fila.setPreferredH((int)(((double)Display.getInstance().getDisplayHeight())/460*40));
            TextArea txtRec = new TextArea(rec.getTexto());
            txtRec.setUIID("ButtonRecordatorio");
            txtRec.addFocusListener(new FocusListener() {
                public void focusGained(Component cmp) {
                    dialogoEditarRecordatorio(instancia, rec, cont);
                }
                public void focusLost(Component cmp) {}
            });
            fila.addComponent(BorderLayout.CENTER, txtRec);
            Button btnEliminar = new Button(" ");
            btnEliminar.setUIID("ButtonEliminarRecordatorio");
            btnEliminar.setPreferredW((int)(((double)Display.getInstance().getDisplayWidth())/320*40));
            btnEliminar.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    final Dialog dlgConfirmar = new Dialog("");
                    dlgConfirmar.getDialogComponent().setUIID("ContainerFondoGris");
                    dlgConfirmar.setDisposeWhenPointerOutOfBounds(true);
                    dlgConfirmar.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
                    TextArea lblEliminar = new TextArea("¿Seguro de que desea eliminar?");
                    lblEliminar.setEditable(false);
                    lblEliminar.setUIID("TextAreaGrisDialogo");
                    dlgConfirmar.addComponent(lblEliminar);
                    Container contBotones = new Container(new GridLayout(1, 2));
                    contBotones.setUIID("ContainerBotonesDialogo");
                    contBotones.setPreferredH((int)(((double)Display.getInstance().getDisplayHeight())/460*35));
                    Button btnAceptar = new Button("Aceptar");
                    btnAceptar.setUIID("ButtonVerde");
                    btnAceptar.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                            Deposito.eliminarRecordatorio(rec.getId());
                            dlgConfirmar.dispose();
                            llenarRecordatorios(cont, instancia);
                        }
                    });
                    contBotones.addComponent(btnAceptar);
                    Button btnCancelar = new Button("Cancelar");
                    btnCancelar.setUIID("ButtonRojo");
                    btnCancelar.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                            dlgConfirmar.dispose();
                        }
                    });
                    contBotones.addComponent(btnCancelar);
                    dlgConfirmar.addComponent(contBotones);
                    dlgConfirmar.show((int)(((double)Display.getInstance().getDisplayHeight())/460*190),
                        (int)(((double)Display.getInstance().getDisplayHeight())/460*190),
                        (int)(((double)Display.getInstance().getDisplayWidth())/460*20),
                        (int)(((double)Display.getInstance().getDisplayWidth())/460*20));
                }
            });
            fila.addComponent(BorderLayout.WEST, btnEliminar);
            subCont.addComponent(fila);
        }
        cont.addComponent(BorderLayout.CENTER, subCont);
    }
    
    private void dialogoEditarRecordatorio(final InstanciaCurso instancia, final Recordatorio recordatorio, final Container contPadre){
        final Dialog dlg = new Dialog();
        dlg.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        dlg.getDialogComponent().setUIID("ContainerFondoGris");
        final TextArea txt = new TextArea();
        txt.setPreferredH((int)(((double)Display.getInstance().getDisplayHeight())/460*80));
        txt.setUIID("TextAreaAzul");
        dlg.addComponent(txt);
        final Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        if(recordatorio != null){
            cal.setTime(recordatorio.getFecha());
        }
        Container pickers = new Container(new GridLayout(1, 2));
        pickers.setUIID("ContainerPickers");
        Container datePicker = new Container(new GridLayout(3, 3));
        Button masDia = new Button(" ");
        masDia.setUIID("ButtonIconMas");
        final TextField txtDia = new TextField(cal.get(Calendar.DAY_OF_MONTH)+"");
        txtDia.setUIID("TextFieldCampoRecordatorio");
        txtDia.setEditable(false);
        Button menosDia = new Button(" ");
        menosDia.setUIID("ButtonIconMenos");
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
        Button masMes = new Button(" ");
        masMes.setUIID("ButtonIconMas");
        final TextField txtMes = new TextField(cal.get(Calendar.MONTH)+1+"");
        txtMes.setUIID("TextFieldCampoRecordatorio");
        txtMes.setEditable(false);
        Button menosMes = new Button(" ");
        menosMes.setUIID("ButtonIconMenos");
        masMes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if(cal.get(Calendar.MONTH) != 11){
                    cal.add(Calendar.MONTH, 1);
                }
                else{
                    cal.set(Calendar.MONTH, 0);
                }
                txtMes.setText(cal.get(Calendar.MONTH)+1+"");
            }
        });
        menosMes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if(cal.get(Calendar.MONTH) != 0){
                    cal.add(Calendar.MONTH, -1);
                }
                else{
                    cal.set(Calendar.MONTH, 11);
                }
                txtMes.setText(cal.get(Calendar.MONTH)+1+"");
            }
        });
        Button masAnio = new Button(" ");
        masAnio.setUIID("ButtonIconMas");
        final TextField txtAnio = new TextField(cal.get(Calendar.YEAR)+"");
        txtAnio.setUIID("TextFieldCampoRecordatorio");
        txtAnio.setEditable(false);
        Button menosAnio = new Button(" ");
        menosAnio.setUIID("ButtonIconMenos");
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
        pickers.addComponent(datePicker);

        if(recordatorio == null){
            cal.set(Calendar.HOUR_OF_DAY, 12);
            cal.set(Calendar.MINUTE, 0);
        }
        Container timePicker = new Container(new GridLayout(3, 3));
        timePicker.setUIID("ContainerTimePicker");
        Button masHora = new Button(" ");
        masHora.setUIID("ButtonIconMas");
        final TextField txtHora = new TextField("12");
        txtHora.setUIID("TextFieldCampoRecordatorio");
        txtHora.setEditable(false);
        Button menosHora = new Button(" ");
        menosHora.setUIID("ButtonIconMenos");
        masHora.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if(Integer.parseInt(txtHora.getText()) < 12){
                    txtHora.setText(Integer.parseInt(txtHora.getText()) + 1 + "");
                }else{
                    txtHora.setText("1");
                }
                cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(txtHora.getText()));
            }
        });
        menosHora.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if(Integer.parseInt(txtHora.getText()) > 1){
                    txtHora.setText(Integer.parseInt(txtHora.getText()) - 1 + "");
                }else{
                    txtHora.setText("12");
                }
                cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(txtHora.getText()));
            }
        });
        Button masMinuto = new Button(" ");
        masMinuto.setUIID("ButtonIconMas");
        final TextField txtMinuto = new TextField("00");
        txtMinuto.setUIID("TextFieldCampoRecordatorio");
        txtMinuto.setEditable(false);
        Button menosMinuto = new Button(" ");
        menosMinuto.setUIID("ButtonIconMenos");
        masMinuto.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if(Integer.parseInt(txtMinuto.getText()) < 59){
                    txtMinuto.setText(Integer.parseInt(txtMinuto.getText()) + 1 + "");
                }else{
                    txtMinuto.setText("00");
                }
                cal.set(Calendar.MINUTE, Integer.parseInt(txtMinuto.getText()));
            }
        });
        menosMinuto.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if(Integer.parseInt(txtMinuto.getText()) > 0){
                    txtMinuto.setText(Integer.parseInt(txtMinuto.getText()) - 1 + "");
                }else{
                    txtMinuto.setText("59");
                }
                cal.set(Calendar.MINUTE, Integer.parseInt(txtMinuto.getText()));
            }
        });
        final Button btnAmPm = new Button("PM");
        btnAmPm.setUIID("ButtonAmPm");
        btnAmPm.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if(btnAmPm.getText().equals("AM")){
                    btnAmPm.setText("PM");
                }else{
                    btnAmPm.setText("AM");
                }
            }
        });
        timePicker.addComponent(masHora);
        timePicker.addComponent(masMinuto);
        Label lbl1 = new Label();
        lbl1.setUIID("LabelFondoGris");
        timePicker.addComponent(lbl1);
        timePicker.addComponent(txtHora);
        timePicker.addComponent(txtMinuto);
        timePicker.addComponent(btnAmPm);
        timePicker.addComponent(menosHora);
        timePicker.addComponent(menosMinuto);
        Label lbl2 = new Label();
        lbl2.setUIID("LabelFondoGris");
        timePicker.addComponent(lbl2);
        pickers.addComponent(timePicker);
        dlg.addComponent(pickers);

        if(recordatorio != null){
            txt.setText(recordatorio.getTexto());
            Calendar c = Calendar.getInstance();
            c.setTime(recordatorio.getFecha());
            txtAnio.setText(c.get(Calendar.YEAR)+"");
            txtMes.setText(c.get(Calendar.MONTH)+1+"");
            txtDia.setText(c.get(Calendar.DAY_OF_MONTH)+"");
            int hora = c.get(Calendar.HOUR_OF_DAY);
            if(hora > 12){
                hora -= 12;
                btnAmPm.setText("PM");
            }else{
                btnAmPm.setText("AM");
            }
            txtHora.setText(hora+"");
            txtMinuto.setText(c.get(Calendar.MINUTE)+"");
        }
        
        Container contBotones = new Container(new GridLayout(1, 2));
        contBotones.setUIID("ContainerBotonesDialogo");
        contBotones.setPreferredH((int)(((double)Display.getInstance().getDisplayHeight())/460*35));
        Button btnAceptar = new Button("Aceptar");
        btnAceptar.setUIID("ButtonVerde");
        btnAceptar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                Calendar fechaRec = Calendar.getInstance();
                int plusHora = (btnAmPm.getText().equals("AM")) ? 0 : 12;
                fechaRec.set(Calendar.YEAR, Integer.parseInt(txtAnio.getText()));
                fechaRec.set(Calendar.MONTH, Integer.parseInt(txtMes.getText()) - 1);
                fechaRec.set(Calendar.DAY_OF_MONTH, Integer.parseInt(txtDia.getText()));
                fechaRec.set(Calendar.HOUR_OF_DAY, Integer.parseInt(txtHora.getText()) + plusHora);
                fechaRec.set(Calendar.MINUTE, Integer.parseInt(txtMinuto.getText()));
                Recordatorio rec;
                if(recordatorio == null){
                    rec = new Recordatorio();
                    rec.setId(-1);
                }else{
                    rec = recordatorio;
                }
                rec.setFecha(fechaRec.getTime());
                rec.setTexto(txt.getText());
                rec.setInstanciaCurso(instancia.getId());
                rec.guardarEnStorage();
                dlg.dispose();
                llenarRecordatorios(contPadre, instancia);
            }
        });
        contBotones.addComponent(btnAceptar);
        Button btnCancelar = new Button("Cancelar");
        btnCancelar.setUIID("ButtonRojo");
        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                dlg.dispose();
            }
        });
        contBotones.addComponent(btnCancelar);
        dlg.addComponent(contBotones);

        dlg.setDisposeWhenPointerOutOfBounds(true);
        dlg.show((int)(((double)Display.getInstance().getDisplayHeight())/460*100),
                (int)(((double)Display.getInstance().getDisplayHeight())/460*110),
                (int)(((double)Display.getInstance().getDisplayWidth())/460*20),
                (int)(((double)Display.getInstance().getDisplayWidth())/460*20));
    }

    public void loadPantallaEstudiantes(final Container cont, final InstanciaCurso instancia) {
        cont.removeAll();

        Container contTitle = new Container(new BorderLayout());
        contTitle.setUIID("ContainerSubTituloGris");
        contTitle.setPreferredH((int)(((double)Display.getInstance().getDisplayHeight())/460*40));
        Button btnLeft = new Button(" ");
        btnLeft.setUIID("ButtonIconoLeft");
        btnLeft.setPreferredW((int)(((double)Display.getInstance().getDisplayWidth())/320*36));
        btnLeft.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                loadPantallaRecordatorios(cont, instancia);
            }
        });
        contTitle.addComponent(BorderLayout.WEST, btnLeft);
        Label lblTitle = new Label("Estudiantes");
        lblTitle.setUIID("LabelSubTituloGris");
        contTitle.addComponent(BorderLayout.CENTER, lblTitle);
        Button btnRight = new Button(" ");
        btnRight.setUIID("ButtonIconoRight");
        btnRight.setPreferredW((int)(((double)Display.getInstance().getDisplayWidth())/320*36));
        btnRight.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                loadPantallaAsignaciones(cont, instancia);
            }
        });
        contTitle.addComponent(BorderLayout.EAST, btnRight);
        cont.addComponent(BorderLayout.NORTH, contTitle);

        Container contenido = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        final Container subContenido = new Container(new BorderLayout());
        Container contSubTitle = new Container(new GridLayout(1, 2));
        contSubTitle.setUIID("ContainerTabs");
        contSubTitle.setPreferredH((int)(((double)Display.getInstance().getDisplayHeight())/460*35));
        final Button tabNotas = new Button("Notas");
        tabNotas.setUIID("ButtonNaranja");
        final Button tabAsistencia = new Button("Asistencia");
        tabAsistencia.setUIID("ButtonAmarillo");
        tabNotas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                tabNotas.setUIID("ButtonNaranja");
                tabAsistencia.setUIID("ButtonAmarillo");
                llenarNotas(subContenido, instancia);
            }
        });
        contSubTitle.addComponent(tabNotas);
        tabAsistencia.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                tabNotas.setUIID("ButtonAmarillo");
                tabAsistencia.setUIID("ButtonNaranja");
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

    public void llenarNotas(final Container cont, final InstanciaCurso instancia) {
        cont.removeAll();
        ArrayList<Estudiante> estudiantes = Deposito.getEstudiantesByInstancia(instancia.getId());
        
        Container head = new Container(new GridLayout(1, 2));
        head.setUIID("ContainerFondoGris");
        Label lblTituloEstudiantes = new Label("Estudiantes");
        lblTituloEstudiantes.setUIID("LabelFondoGris");
        head.addComponent(lblTituloEstudiantes);
        Container subHead = new Container(new BoxLayout(BoxLayout.X_AXIS));
        subHead.setScrollableX(true);
        if(!estudiantes.isEmpty()){
            for(Asignacion a : Deposito.getAsignacionesByCurso(instancia.getCurso())){
                Label lblAsignacion = new Label(a.getNombre());
                lblAsignacion.setUIID("LabelFondoGris");
                lblAsignacion.setPreferredW(65);
                subHead.addComponent(lblAsignacion);
            }
        }
        head.addComponent(subHead);
        cont.addComponent(BorderLayout.NORTH, head);
        
        Container lista = new Container(new GridLayout(1, 2));
        Container colEstudiantes = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        for(final Estudiante e : estudiantes){
            Button btnEstudiante = new Button(e.getNombre());
            btnEstudiante.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    final Dialog dlg = new Dialog(e.getNombre() + " [" + e.getCarne() + "]");
                    dlg.setDisposeWhenPointerOutOfBounds(true);
                    dlg.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
                    Button btnEditar = new Button("Editar");
                    btnEditar.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                            dialogoEdicionEstudiante("Editar estudiante", e.getId(), instancia, true);
                            dlg.dispose();
                        }
                    });
                    dlg.addComponent(btnEditar);
                    Button btnEliminar = new Button("Eliminar");
                    btnEliminar.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                            final Dialog dlgConfirmar = new Dialog();
                            dlgConfirmar.getDialogComponent().setUIID("ContainerFondoGris");
                            dlgConfirmar.setDisposeWhenPointerOutOfBounds(true);
                            dlgConfirmar.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
                            TextArea lblConfirmacion = new TextArea("¿Seguro de que desea eliminar?");
                            lblConfirmacion.setUIID("TextAreaGrisDialogo");
                            dlgConfirmar.addComponent(lblConfirmacion);
                            Container contBotones = new Container(new GridLayout(1, 2));
                            contBotones.setPreferredH((int)(((double)Display.getInstance().getDisplayHeight())/460*35));
                            contBotones.setUIID("ContainerBotonesDialogo");
                            Button btnAceptar = new Button("Aceptar");
                            btnAceptar.setUIID("ButtonVerde");
                            btnAceptar.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent evt) {
                                    Deposito.eliminarEstudiante(e.getId());
                                    recargarAsistencias(instancia);
                                    dlgConfirmar.dispose();
                                    dlg.dispose();
                                }
                            });
                            contBotones.addComponent(btnAceptar);
                            Button btnCancelar = new Button("Cancelar");
                            btnCancelar.setUIID("ButtonRojo");
                            btnCancelar.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent evt) {
                                    dlgConfirmar.dispose();
                                }
                            });
                            contBotones.addComponent(btnCancelar);
                            dlgConfirmar.addComponent(contBotones);
                            dlgConfirmar.show((int)(((double)Display.getInstance().getDisplayHeight())/460*190),
                                (int)(((double)Display.getInstance().getDisplayHeight())/460*190),
                                (int)(((double)Display.getInstance().getDisplayWidth())/460*20),
                                (int)(((double)Display.getInstance().getDisplayWidth())/460*20));
                        }
                    });
                    dlg.addComponent(btnEliminar);
                    Button btnCancelar = new Button("Cancelar");
                    btnCancelar.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                            dlg.dispose();
                        }
                    });
                    dlg.addComponent(btnCancelar);
                    dlg.show();
                }
            });
            colEstudiantes.addComponent(btnEstudiante);
        }
        lista.addComponent(colEstudiantes);
        Container contCampos = new Container(new BoxLayout(BoxLayout.X_AXIS));
        contCampos.setScrollableX(true);
        lista.addComponent(contCampos);
        final ArrayList<ArrayList<Nota>> notas = new ArrayList<ArrayList<Nota>>();
        for(Estudiante e : estudiantes){
            notas.add(Deposito.getNotasByEstudiante(e.getId()));
        }
        if(!estudiantes.isEmpty()){
            for(int iN = 0; iN < notas.get(0).size(); iN++){
                Container columna = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                for(int iE = 0; iE < estudiantes.size(); iE++){
                    final Nota nota = notas.get(iE).get(iN);
                    final TextField txtNota = new TextField(" ");
                    txtNota.setPreferredW(65);
                    txtNota.setText(nota.getValor()+"");
                    final int iX = iN;
                    txtNota.addFocusListener(new FocusListener() {
                        public void focusGained(Component cmp) {
                            if(getLockedColumnsNotas(instancia.getId(), notas.get(0).size()).get(iX)){
                                txtNota.setEditable(false);
                            }else{
                                txtNota.setEditable(true);
                            }
                        }
                        public void focusLost(Component cmp) {}
                    });
                    txtNota.addDataChangeListener(new DataChangedListener() {
                        public void dataChanged(int type, int index) {
                            try{
                                nota.setValor(Byte.parseByte(txtNota.getText()));
                                nota.guardarEnStorage();
                            }catch(Exception e){}
                        }
                    });
                    columna.addComponent(txtNota);
                }
                contCampos.addComponent(columna);
            }
        }
        cont.addComponent(BorderLayout.CENTER, lista);
        
        Container barraInferior = new Container(new GridLayout(1, 2));
        Button btnAgregarEstudiante = new Button("Agregar estudiante");
        btnAgregarEstudiante.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                dialogoEdicionEstudiante("Nuevo estudiante", -1, instancia, true);
            }
        });
        barraInferior.addComponent(btnAgregarEstudiante);
        Container subBarraInferior = new Container(new BoxLayout(BoxLayout.X_AXIS));
        subBarraInferior.setScrollableX(true);
        if(!estudiantes.isEmpty()){
            for(int iN = 0; iN < notas.get(0).size(); iN++){
                Button btnLockColumn = new Button("Lock");
                btnLockColumn.setPreferredW(65);
                final int iX = iN;
                btnLockColumn.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        Vector<Boolean> lockedCols = getLockedColumnsNotas(instancia.getId(), notas.get(0).size());
                        lockedCols.set(iX, !lockedCols.get(iX));
                        guardarLockedColumnsNotas(instancia.getId(), lockedCols);
                    }
                });
                subBarraInferior.addComponent(btnLockColumn);
            }
        }
        barraInferior.addComponent(subBarraInferior);
        cont.addComponent(BorderLayout.SOUTH, barraInferior);
        
        getCurrent().animateLayout(0);
    }

    private void guardarLockedColumnsNotas(int idInstancia, Vector<Boolean> cols) {
        if (!Storage.getInstance().exists("lockedColumnsNotas")) {
            Storage.getInstance().writeObject("lockedColumnsNotas", new Hashtable());
        }
        Hashtable table = (Hashtable) Storage.getInstance().readObject("lockedColumnsNotas");
        table.put(idInstancia, cols);
        Storage.getInstance().writeObject("lockedColumnsNotas", table);
    }

    private Vector<Boolean> getLockedColumnsNotas(int idInstancia, int tamanio) {
        if (!Storage.getInstance().exists("lockedColumnsNotas")) {
            Storage.getInstance().writeObject("lockedColumnsNotas", new Hashtable());
        }
        Hashtable table = (Hashtable) Storage.getInstance().readObject("lockedColumnsNotas");
        if (!table.containsKey(idInstancia)) {
            Vector<Boolean> boolVector = new Vector<Boolean>();
            for (int i = 0; i < tamanio; i++) {
                boolVector.add(false);
            }
            table.put(idInstancia, boolVector);
        }
        return (Vector<Boolean>) table.get(idInstancia);
    }

    public void llenarAsistencias(final Container cont, final InstanciaCurso instancia) {
        cont.removeAll();
        ArrayList<Estudiante> estudiantes = Deposito.getEstudiantesByInstancia(instancia.getId());

        Container head = new Container(new GridLayout(1, 2));
        head.setUIID("ContainerFondoGris");
        Label lblTituloEstudiantes = new Label("Estudiantes");
        lblTituloEstudiantes.setUIID("LabelFondoGris");
        head.addComponent(lblTituloEstudiantes);
        Container subHead = new Container(new BoxLayout(BoxLayout.X_AXIS));
        subHead.setScrollableX(true);
        if (!estudiantes.isEmpty()) {
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy");
            for (Asistencia a : Deposito.getAsistenciasByEstudiante(estudiantes.get(0).getId())) {
                Label lblFecha = new Label(sdf.format(a.getFecha()));
                lblFecha.setUIID("LabelFondoGris");
                lblFecha.setPreferredW(65);
                subHead.addComponent(lblFecha);
            }
        }
        head.addComponent(subHead);
        cont.addComponent(BorderLayout.NORTH, head);

        Container lista = new Container(new GridLayout(1, 2));
        Container colEstudiantes = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        for (final Estudiante e : estudiantes) {
            Button btnEstudiante = new Button(e.getNombre());
            btnEstudiante.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    final Dialog dlg = new Dialog(e.getNombre() + " [" + e.getCarne() + "]");
                    dlg.setDisposeWhenPointerOutOfBounds(true);
                    dlg.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
                    Button btnEditar = new Button("Editar");
                    btnEditar.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                            dialogoEdicionEstudiante("Editar estudiante", e.getId(), instancia, false);
                            dlg.dispose();
                        }
                    });
                    dlg.addComponent(btnEditar);
                    Button btnEliminar = new Button("Eliminar");
                    btnEliminar.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                            final Dialog dlgConfirmar = new Dialog("¿Está seguro?");
                            dlgConfirmar.setDisposeWhenPointerOutOfBounds(true);
                            dlgConfirmar.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
                            dlgConfirmar.addComponent(new Label("Si acepta perderá todos los datos de este estudiante."));
                            Container contBotones = new Container(new GridLayout(1, 2));
                            Button btnAceptar = new Button("Aceptar");
                            btnAceptar.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent evt) {
                                    Deposito.eliminarEstudiante(e.getId());
                                    recargarAsistencias(instancia);
                                    dlgConfirmar.dispose();
                                    dlg.dispose();
                                }
                            });
                            contBotones.addComponent(btnAceptar);
                            Button btnCancelar = new Button("Cancelar");
                            btnCancelar.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent evt) {
                                    dlgConfirmar.dispose();
                                }
                            });
                            contBotones.addComponent(btnCancelar);
                            dlgConfirmar.addComponent(contBotones);
                            dlgConfirmar.show();
                        }
                    });
                    dlg.addComponent(btnEliminar);
                    Button btnCancelar = new Button("Cancelar");
                    btnCancelar.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                            dlg.dispose();
                        }
                    });
                    dlg.addComponent(btnCancelar);
                    dlg.show();
                }
            });
            colEstudiantes.addComponent(btnEstudiante);
        }
        lista.addComponent(colEstudiantes);
        Container contCampos = new Container(new BoxLayout(BoxLayout.X_AXIS));
        contCampos.setScrollableX(true);
        lista.addComponent(contCampos);
        final ArrayList<ArrayList<Asistencia>> asistencias = new ArrayList<ArrayList<Asistencia>>();
        for (Estudiante e : estudiantes) {
            asistencias.add(Deposito.getAsistenciasByEstudiante(e.getId()));
        }
        if (!estudiantes.isEmpty()) {
            for (int iA = 0; iA < asistencias.get(0).size(); iA++) {
                Container columna = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                for (int iE = 0; iE < estudiantes.size(); iE++) {
                    final Asistencia asis = asistencias.get(iE).get(iA);
                    final Button btnEstado = new Button(" ");
                    btnEstado.setPreferredW(65);
                    btnEstado.setText(asis.getEstado());
                    final int iX = iA;
                    btnEstado.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                            if (!getLockedColumnsAsistencia(instancia.getId(), asistencias.get(0).size()).get(iX)) {
                                btnEstado.setText(siguienteEstado(btnEstado.getText()));
                                asis.setEstado(btnEstado.getText());
                                asis.guardarEnStorage();
                            }
                        }
                    });
                    columna.addComponent(btnEstado);
                }
                contCampos.addComponent(columna);
            }
        }
        cont.addComponent(BorderLayout.CENTER, lista);

        Container barraInferior = new Container(new GridLayout(1, 2));
        Button btnAgregarEstudiante = new Button("Agregar estudiante");
        btnAgregarEstudiante.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                dialogoEdicionEstudiante("Nuevo estudiante", -1, instancia, false);
            }
        });
        barraInferior.addComponent(btnAgregarEstudiante);
        Container subBarraInferior = new Container(new BoxLayout(BoxLayout.X_AXIS));
        subBarraInferior.setScrollableX(true);
        if (!estudiantes.isEmpty()) {
            for (int iA = 0; iA < asistencias.get(0).size(); iA++) {
                Button btnLockColumn = new Button("Lock");
                btnLockColumn.setPreferredW(65);
                final int iX = iA;
                btnLockColumn.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        Vector<Boolean> lockedCols = getLockedColumnsAsistencia(instancia.getId(), asistencias.get(0).size());
                        lockedCols.set(iX, !lockedCols.get(iX));
                        guardarLockedColumnsAsistencia(instancia.getId(), lockedCols);
                    }
                });
                subBarraInferior.addComponent(btnLockColumn);
            }
        }
        barraInferior.addComponent(subBarraInferior);
        cont.addComponent(BorderLayout.SOUTH, barraInferior);

        getCurrent().animateLayout(0);
    }

    private void recargarAsistencias(InstanciaCurso instancia) {
        Container cont = (Container) ((Container) ((Container) ((Container) getCurrent().getComponentAt(1)).getComponentAt(1)).getComponentAt(1)).getComponentAt(1);
        cont.removeAll();
        llenarAsistencias(cont, instancia);
    }
    
    private void recargarNotas(InstanciaCurso instancia){
        Container cont = (Container)((Container)((Container)((Container)getCurrent().getComponentAt(1)).getComponentAt(1)).getComponentAt(1)).getComponentAt(1);
        cont.removeAll();
        llenarNotas(cont, instancia);
    }
    
    private void dialogoEdicionEstudiante(String titulo, int idEstudiante, final InstanciaCurso instancia, final boolean tabNotas){
        final Estudiante estudiante = (idEstudiante != -1) ? Deposito.getEstudianteById(idEstudiante) :
                new Estudiante(idEstudiante, "", "", "", instancia.getId());
        
        final Dialog dlg = new Dialog(titulo);
        dlg.setDisposeWhenPointerOutOfBounds(true);
        dlg.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        dlg.addComponent(new Label("Nombre:"));
        final TextField txtNombre = new TextField(estudiante.getNombre());
        dlg.addComponent(txtNombre);
        dlg.addComponent(new Label("Carné:"));
        final TextField txtCarne = new TextField(estudiante.getCarne());
        dlg.addComponent(txtCarne);
        dlg.addComponent(new Label("Correo:"));
        final TextField txtCorreo = new TextField(estudiante.getCorreo());
        dlg.addComponent(txtCorreo);
        Container contBotones = new Container(new GridLayout(1, 2));
        Button btnGuardar = new Button("Guardar");
        btnGuardar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                estudiante.setNombre(txtNombre.getText());
                estudiante.setCarne(txtCarne.getText());
                estudiante.setCorreo(txtCorreo.getText());
                estudiante.guardarEnStorage();
                if(tabNotas){
                    recargarNotas(instancia);
                }else{
                    recargarAsistencias(instancia);
                }
                dlg.dispose();
            }
        });
        contBotones.addComponent(btnGuardar);
        Button btnCancelar = new Button("Cancelar");
        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                dlg.dispose();
            }
        });
        contBotones.addComponent(btnCancelar);
        dlg.addComponent(contBotones);
        dlg.show();
    }

    private void guardarLockedColumnsAsistencia(int idInstancia, Vector<Boolean> cols) {
        if (!Storage.getInstance().exists("lockedColumnsAsistencia")) {
            Storage.getInstance().writeObject("lockedColumnsAsistencia", new Hashtable());
        }
        Hashtable table = (Hashtable) Storage.getInstance().readObject("lockedColumnsAsistencia");
        table.put(idInstancia, cols);
        Storage.getInstance().writeObject("lockedColumnsAsistencia", table);
    }

    private Vector<Boolean> getLockedColumnsAsistencia(int idInstancia, int tamanio) {
        if (!Storage.getInstance().exists("lockedColumnsAsistencia")) {
            Storage.getInstance().writeObject("lockedColumnsAsistencia", new Hashtable());
        }
        Hashtable table = (Hashtable) Storage.getInstance().readObject("lockedColumnsAsistencia");
        if (!table.containsKey(idInstancia)) {
            Vector<Boolean> boolVector = new Vector<Boolean>();
            for (int i = 0; i < tamanio; i++) {
                boolVector.add(false);
            }
            table.put(idInstancia, boolVector);
        }
        return (Vector<Boolean>) table.get(idInstancia);
    }

    private String siguienteEstado(String estado) {
        String[] estados = {"", "Presente", "Ausente", "Tarde"};
        int indice = 0;
        try {
            while (!estado.equals(estados[indice])) {
                indice++;
            }
        } catch (Exception e) {
        }
        indice = (indice == estados.length - 1) ? 0 : ++indice;
        return estados[indice];
    }

    public void loadPantallaAsignaciones(final Container cont, final InstanciaCurso instancia) {
        cont.removeAll();

        Container contTitle = new Container(new BorderLayout());
        contTitle.setUIID("ContainerSubTituloGris");
        contTitle.setPreferredH((int)(((double)Display.getInstance().getDisplayHeight())/460*40));
        Button btnLeft = new Button(" ");
        btnLeft.setUIID("ButtonIconoLeft");
        btnLeft.setPreferredW((int)(((double)Display.getInstance().getDisplayWidth())/320*36));
        btnLeft.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                loadPantallaEstudiantes(cont, instancia);
            }
        });
        contTitle.addComponent(BorderLayout.WEST, btnLeft);
        Label lblTitle = new Label("Asignaciones");
        lblTitle.setUIID("LabelSubTituloGris");
        contTitle.addComponent(BorderLayout.CENTER, lblTitle);
        Button btnRight = new Button(" ");
        btnRight.setUIID("ButtonIconoRight");
        btnRight.setPreferredW((int)(((double)Display.getInstance().getDisplayWidth())/320*36));
        btnRight.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                loadPantallaMateria(cont, instancia);
            }
        });
        contTitle.addComponent(BorderLayout.EAST, btnRight);
        cont.addComponent(BorderLayout.NORTH, contTitle);

        Container contenido = new Container(new BorderLayout());
        
        cargarAsignaciones(contenido, instancia);
        
        cont.addComponent(BorderLayout.CENTER, contenido);

        getCurrent().animateLayout(0);
    }
    
    public void cargarAsignaciones(final Container cont, final InstanciaCurso instancia){
        cont.removeAll();
        Container subCont = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        
        ArrayList<Asignacion> asignaciones = Deposito.getAsignacionesByCurso(instancia.getCurso());
        
        if(!asignaciones.isEmpty()){
            for(final Asignacion asignacion:asignaciones){

            Button btnNombre = new Button("[" + asignacion.getPorcentaje() + "%] " + asignacion.getNombre()); 
            btnNombre.setUIID("ButtonAsignacion");
            btnNombre.setPreferredH((int)(((double)Display.getInstance().getDisplayHeight())/460*40));
            btnNombre.addActionListener(new ActionListener(){
                public void actionPerformed (ActionEvent evt){
                    
                    final Dialog dlgEditar = new Dialog();
                    dlgEditar.getDialogComponent().setUIID("ContainerFondoGris");
                    Label lblTituloDialogo = new Label("Editar asignación");
                    lblTituloDialogo.setUIID("LabelFondoGrisTitulo");
                    dlgEditar.addComponent(lblTituloDialogo);
                    dlgEditar.setDisposeWhenPointerOutOfBounds(true);
                    dlgEditar.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
                    
                    Container botones = new Container(new GridLayout(1,2));
                    botones.setUIID("ContainerBotonesDialogo");
                    botones.setPreferredH((int)(((double)Display.getInstance().getDisplayHeight())/460*35));
                    Label lblNombre = new Label("Nombre");
                    lblNombre.setUIID("LabelFondoGris");
                    final TextField nombre = new TextField(asignacion.getNombre());
                    nombre.setUIID("TextFieldBlanco");
                    Label lblFecha = new Label("Fecha"); 
                    lblFecha.setUIID("LabelFondoGris");
                    
                    final Calendar cal = Calendar.getInstance();
                    cal.setTime(asignacion.getFechaEntrega());
                    Container datePicker = new Container(new GridLayout(3, 3));
                    Button masDia = new Button(" ");
                    masDia.setUIID("ButtonIconMas");
                    final TextField txtDia = new TextField(cal.get(Calendar.DAY_OF_MONTH)+"");
                    txtDia.setUIID("TextFieldBlanco");
                    txtDia.setEditable(false);
                    Button menosDia = new Button(" ");
                    menosDia.setUIID("ButtonIconMenos");
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
                    Button masMes = new Button(" ");
                    masMes.setUIID("ButtonIconMas");
                    final TextField txtMes = new TextField(cal.get(Calendar.MONTH)+1+"");
                    txtMes.setUIID("TextFieldBlanco");
                    txtMes.setEditable(false);
                    Button menosMes = new Button(" ");
                    menosMes.setUIID("ButtonIconMenos");
                    masMes.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                            if(cal.get(Calendar.MONTH) != 11){
                                cal.add(Calendar.MONTH, 1);
                            }
                            else{
                                cal.set(Calendar.MONTH, 0);
                            }
                            txtMes.setText(cal.get(Calendar.MONTH)+1+"");
                        }
                    });
                    menosMes.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                            if(cal.get(Calendar.MONTH) != 0){
                                cal.add(Calendar.MONTH, -1);
                            }
                            else{
                                cal.set(Calendar.MONTH, 11);
                            }
                            txtMes.setText(cal.get(Calendar.MONTH)+1+"");
                        }
                    });
                    Button masAnio = new Button(" ");
                    masAnio.setUIID("ButtonIconMas");
                    final TextField txtAnio = new TextField(cal.get(Calendar.YEAR)+"");
                    txtAnio.setUIID("TextFieldBlanco");
                    txtAnio.setEditable(false);
                    Button menosAnio = new Button(" ");
                    menosAnio.setUIID("ButtonIconMenos");
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
                    /*
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(asignacion.getFechaEntrega());
                    final TextField fecha = new TextField(cal.get(Calendar.YEAR)+"-"+cal.get(Calendar.MONTH)+1+"-"+cal.get(Calendar.DAY_OF_MONTH));        
                    fecha.setUIID("TextFieldBlanco");
                    */
                    Label lblPorcentaje = new Label("Porcentaje");
                    lblPorcentaje.setUIID("LabelFondoGris");
                    final TextField porcentaje = new TextField("" + asignacion.getPorcentaje());
                    porcentaje.setUIID("TextFieldBlanco");
                    Label lblDescripcion = new Label("Descripción");
                    lblDescripcion.setUIID("LabelFondoGris");
                    final TextArea descripcion = new TextArea(asignacion.getDescripcion());
                    descripcion.setUIID("TextAreaBlanco");
                    
                    Button btnEliminar = new Button("Eliminar");
                    btnEliminar.setUIID("ButtonRojo");
                    btnEliminar.addActionListener(new ActionListener(){                
                        public void actionPerformed(ActionEvent evt){
                        
                            final Dialog dlgEliminar = new Dialog();
                            dlgEliminar.getDialogComponent().setUIID("ContainerFondoGris");
                            dlgEliminar.setDisposeWhenPointerOutOfBounds(true);
                            dlgEliminar.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
                            Container botones = new Container(new GridLayout(1,2));
                            botones.setUIID("ContainerBotonesDialogo");
                            botones.setPreferredH((int)(((double)Display.getInstance().getDisplayHeight())/460*35));
                            TextArea lblAdvertencia = new TextArea("¿Seguro de que desea eliminar?");
                            lblAdvertencia.setUIID("TextAreaGrisDialogo");
                            Button btnAceptar = new Button("Aceptar");
                            btnAceptar.setUIID("ButtonVerde");
                            btnAceptar.addActionListener(new ActionListener(){                            
                                public void actionPerformed(ActionEvent evt){
                                    Deposito.eliminarAsignacion(asignacion.getId());
                                    dlgEliminar.dispose();
                                    dlgEditar.dispose();
                                    cargarAsignaciones(cont, instancia);
                                }                            
                            });
                            Button btnCancelar = new Button("Cancelar");
                            btnCancelar.setUIID("ButtonRojo");
                            btnCancelar.addActionListener(new ActionListener(){                            
                                public void actionPerformed(ActionEvent evt){
                                    dlgEliminar.dispose();
                                }                            
                            });
                            botones.addComponent(btnAceptar);
                            botones.addComponent(btnCancelar);
                            dlgEliminar.addComponent(lblAdvertencia);
                            dlgEliminar.addComponent(botones);

                            dlgEliminar.show((int)(((double)Display.getInstance().getDisplayHeight())/460*190),
                                (int)(((double)Display.getInstance().getDisplayHeight())/460*190),
                                (int)(((double)Display.getInstance().getDisplayWidth())/460*20),
                                (int)(((double)Display.getInstance().getDisplayWidth())/460*20));
                        }
                    });
                    
                    Button btnAceptar = new Button("Aceptar");
                    btnAceptar.setUIID("ButtonVerde");
                    btnAceptar.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent evt){
                            Calendar fecha = Calendar.getInstance();
                            fecha.set(Calendar.YEAR, Integer.parseInt(txtAnio.getText()));
                            fecha.set(Calendar.MONTH, Integer.parseInt(txtMes.getText()) - 1);
                            fecha.set(Calendar.DAY_OF_MONTH, Integer.parseInt(txtDia.getText()));
                            
                            asignacion.setNombre(nombre.getText());
                            /*
                            SimpleDateFormat textFormat = new SimpleDateFormat("yyyy-MM-DD");
                            String strFecha = fecha.getText();
                            Date fecha = null;
                            try { fecha = textFormat.parse(strFecha); }
                            catch (Exception e) { e.printStackTrace(); }
                            */
                            asignacion.setFechaEntrega(fecha.getTime());
                            asignacion.setPorcentaje(Byte.parseByte(porcentaje.getText()));
                            asignacion.setDescripcion(descripcion.getText());
                            asignacion.guardarEnStorage();
                            dlgEditar.dispose();
                            cargarAsignaciones(cont, instancia);
                        }
                       
                    });
                    
                    dlgEditar.addComponent(lblFecha);
                    dlgEditar.addComponent(datePicker);
                    dlgEditar.addComponent(lblNombre);
                    dlgEditar.addComponent(nombre);
                    dlgEditar.addComponent(lblPorcentaje);
                    dlgEditar.addComponent(porcentaje);
                    dlgEditar.addComponent(lblDescripcion);
                    dlgEditar.addComponent(descripcion);
                    botones.addComponent(btnAceptar);
                    botones.addComponent(btnEliminar);
                    dlgEditar.addComponent(botones);
                    dlgEditar.show((int)(((double)Display.getInstance().getDisplayHeight())/460*40),
                                    (int)(((double)Display.getInstance().getDisplayHeight())/460*40),
                                    (int)(((double)Display.getInstance().getDisplayWidth())/460*20),
                                    (int)(((double)Display.getInstance().getDisplayWidth())/460*20));
                }
            });
            
            subCont.addComponent(btnNombre);

            }
        
        }
        
        Button btnAgregar = new Button(" ");
        btnAgregar.setUIID("ButtonAgregarInferior");
        btnAgregar.setPreferredH((int)(((double)Display.getInstance().getDisplayHeight())/460*40));
        btnAgregar.addActionListener(new ActionListener(){                            
            public void actionPerformed(ActionEvent evt){
                                
                    final Dialog dlgAgregar = new Dialog();
                    Label lblTituloDlg = new Label("Crear nueva asignación");
                    lblTituloDlg.setUIID("LabelFondoGrisTitulo");
                    dlgAgregar.addComponent(lblTituloDlg);
                    dlgAgregar.getDialogComponent().setUIID("ContainerFondoGris");
                    dlgAgregar.setDisposeWhenPointerOutOfBounds(true);
                    dlgAgregar.setLayout(new BoxLayout(BoxLayout.Y_AXIS));                    
                    Container botones = new Container(new GridLayout(1,2));
                    botones.setUIID("ContainerBotonesDialogo");
                    botones.setPreferredH((int)(((double)Display.getInstance().getDisplayHeight())/460*35));
                    Label lblNombre = new Label("Nombre");
                    lblNombre.setUIID("LabelFondoGris");
                    final TextField nombre = new TextField();       
                    nombre.setUIID("TextFieldBlanco");
                    Label lblFecha = new Label("Fecha");            
                    lblFecha.setUIID("LabelFondoGris");
                    /*
                    final TextField fecha = new TextField();  
                    fecha.setUIID("TextFieldBlanco");
                    * */
                    final Calendar cal = Calendar.getInstance();
                    cal.setTime(new Date());
                    Container datePicker = new Container(new GridLayout(3, 3));
                    Button masDia = new Button(" ");
                    masDia.setUIID("ButtonIconMas");
                    final TextField txtDia = new TextField(cal.get(Calendar.DAY_OF_MONTH)+"");
                    txtDia.setUIID("TextFieldBlanco");
                    txtDia.setEditable(false);
                    Button menosDia = new Button(" ");
                    menosDia.setUIID("ButtonIconMenos");
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
                    Button masMes = new Button(" ");
                    masMes.setUIID("ButtonIconMas");
                    final TextField txtMes = new TextField(cal.get(Calendar.MONTH)+1+"");
                    txtMes.setUIID("TextFieldBlanco");
                    txtMes.setEditable(false);
                    Button menosMes = new Button(" ");
                    menosMes.setUIID("ButtonIconMenos");
                    masMes.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                            if(cal.get(Calendar.MONTH) != 11){
                                cal.add(Calendar.MONTH, 1);
                            }
                            else{
                                cal.set(Calendar.MONTH, 0);
                            }
                            txtMes.setText(cal.get(Calendar.MONTH)+1+"");
                        }
                    });
                    menosMes.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                            if(cal.get(Calendar.MONTH) != 0){
                                cal.add(Calendar.MONTH, -1);
                            }
                            else{
                                cal.set(Calendar.MONTH, 11);
                            }
                            txtMes.setText(cal.get(Calendar.MONTH)+1+"");
                        }
                    });
                    Button masAnio = new Button(" ");
                    masAnio.setUIID("ButtonIconMas");
                    final TextField txtAnio = new TextField(cal.get(Calendar.YEAR)+"");
                    txtAnio.setUIID("TextFieldBlanco");
                    txtAnio.setEditable(false);
                    Button menosAnio = new Button(" ");
                    menosAnio.setUIID("ButtonIconMenos");
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
                    
                    Label lblPorcentaje = new Label("Porcentaje");
                    lblPorcentaje.setUIID("LabelFondoGris");
                    final TextField porcentaje = new TextField();   
                    porcentaje.setUIID("TextFieldBlanco");
                    Label lblDescripcion = new Label("Descripción");
                    lblDescripcion.setUIID("LabelFondoGris");
                    final TextArea descripcion = new TextArea();
                    descripcion.setUIID("TextAreaBlanco");
                    Button btnAceptar = new Button("Aceptar");  
                    btnAceptar.setUIID("ButtonVerde");
                    btnAceptar.addActionListener(new ActionListener(){                            
                                public void actionPerformed(ActionEvent evt){
                                    /*
                                    SimpleDateFormat textFormat = new SimpleDateFormat("yyyy-MM-DD");
                                    String strFecha = fecha.getText();
                                    Date fechaFormat = null;
                                    try { fechaFormat = textFormat.parse(strFecha); }
                                    catch (Exception e) { e.printStackTrace(); }
                                    * */
                                    Calendar fecha = Calendar.getInstance();
                                    fecha.set(Calendar.YEAR, Integer.parseInt(txtAnio.getText()));
                                    fecha.set(Calendar.MONTH, Integer.parseInt(txtMes.getText()) - 1);
                                    fecha.set(Calendar.DAY_OF_MONTH, Integer.parseInt(txtDia.getText()));

                                    Asignacion asignacionNueva = new Asignacion(-1, nombre.getText(), descripcion.getText(), Byte.parseByte(porcentaje.getText()), fecha.getTime(), instancia.getCurso());
                                    asignacionNueva.guardarEnStorage();
                                    dlgAgregar.dispose();
                                    cargarAsignaciones(cont, instancia);
                                }                            
                            });
                    Button btnCancelar = new Button("Cancelar");
                    btnCancelar.setUIID("ButtonRojo");
                    btnCancelar.addActionListener(new ActionListener(){                            
                                public void actionPerformed(ActionEvent evt){
                                    dlgAgregar.dispose();
                                }                            
                            });                    
                    botones.addComponent(btnAceptar);
                    botones.addComponent(btnCancelar);
                    dlgAgregar.addComponent(lblNombre);
                    dlgAgregar.addComponent(nombre);
                    dlgAgregar.addComponent(lblFecha);
                    dlgAgregar.addComponent(datePicker);
                    dlgAgregar.addComponent(lblPorcentaje);
                    dlgAgregar.addComponent(porcentaje);
                    dlgAgregar.addComponent(lblDescripcion);
                    dlgAgregar.addComponent(descripcion);
                    dlgAgregar.addComponent(botones);
                    dlgAgregar.show((int)(((double)Display.getInstance().getDisplayHeight())/460*40),
                                    (int)(((double)Display.getInstance().getDisplayHeight())/460*40),
                                    (int)(((double)Display.getInstance().getDisplayWidth())/460*20),
                                    (int)(((double)Display.getInstance().getDisplayWidth())/460*20));
            }                            
        });
        cont.addComponent(BorderLayout.SOUTH, btnAgregar);
        
        cont.addComponent(BorderLayout.CENTER, subCont);
        getCurrent().animateLayout(0);
    }
    
    public void loadPantallaMateria(final Container cont, final InstanciaCurso instancia){
        cont.removeAll();
        Container contTitle = new Container(new BorderLayout());
        contTitle.setUIID("ContainerSubTituloGris");
        contTitle.setPreferredH((int)(((double)Display.getInstance().getDisplayHeight())/460*40));
        Button btnLeft = new Button(" ");
        btnLeft.setUIID("ButtonIconoLeft");
        btnLeft.setPreferredW((int)(((double)Display.getInstance().getDisplayWidth())/320*36));
        btnLeft.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                loadPantallaAsignaciones(cont, instancia);
            }
        });
        contTitle.addComponent(BorderLayout.WEST, btnLeft);
        Label lblTitle = new Label("Materia");
        lblTitle.setUIID("LabelSubTituloGris");
        contTitle.addComponent(BorderLayout.CENTER, lblTitle);
        Button btnRight = new Button(" ");
        btnRight.setUIID("ButtonIconoRight");
        btnRight.setPreferredW((int)(((double)Display.getInstance().getDisplayWidth())/320*36));
        btnRight.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                loadPantallaRecordatorios(cont, instancia);
            }
        });
        contTitle.addComponent(BorderLayout.EAST, btnRight);
        cont.addComponent(BorderLayout.NORTH, contTitle);

        Container contenido = new Container(new BorderLayout());
        contenido.setUIID("ContainerFondoGris");

        Container contTitleMateria = new Container(new BorderLayout());
        contTitleMateria.setUIID("ContainerTituloVerde");
        contTitleMateria.setPreferredH((int)(((double)Display.getInstance().getDisplayHeight())/460*40));
        final Container preContenidoMateria = new Container(new BorderLayout());
        final Label lblTitulo= new Label();
        lblTitulo.setUIID("LabelTituloVerde");
        Button previousSemanaMateria = new Button(" ");
        previousSemanaMateria.setUIID("ButtonIconoVerdeLeft");
        previousSemanaMateria.setPreferredW((int)(((double)Display.getInstance().getDisplayWidth())/320*36));
        previousSemanaMateria.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (semanaActualMateria > 1) {
                    loadMateria(preContenidoMateria, instancia, semanaActualMateria - 1);
                    semanaActualMateria--;
                    lblTitulo.setText("Semana "+ semanaActualMateria);
                }
            }
        });

        contTitleMateria.addComponent(BorderLayout.WEST, previousSemanaMateria);
        Button nextSemanaMateria = new Button(" ");
        nextSemanaMateria.setUIID("ButtonIconoVerdeRight");
        nextSemanaMateria.setPreferredW((int)(((double)Display.getInstance().getDisplayWidth())/320*36));
        nextSemanaMateria.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (semanaActualMateria < instancia.getCantidadSemanas()) {
                    loadMateria(preContenidoMateria, instancia, semanaActualMateria + 1);
                    semanaActualMateria++;
                    lblTitulo.setText("Semana "+ semanaActualMateria);
                }
            }
        });
        
        lblTitulo.setText("Semana "+ semanaActualMateria);
        contTitleMateria.addComponent(BorderLayout.CENTER,lblTitulo);
        contTitleMateria.addComponent(BorderLayout.EAST, nextSemanaMateria);
        contenido.addComponent(BorderLayout.SOUTH, contTitleMateria);
        contenido.addComponent(BorderLayout.CENTER, preContenidoMateria);
        cont.addComponent(BorderLayout.CENTER, contenido);

        loadMateria(preContenidoMateria, instancia, 1);

        getCurrent().animateLayout(0);
    }

    private void loadMateria(final Container cont, final InstanciaCurso instancia, final int semana) {
        cont.removeAll();

        final Container contenidoMateria = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        contenidoMateria.setUIID("ContainerFondoGris");

        ArrayList<Materia> materiasSemanaActual = new ArrayList<Materia>();
        for (Materia materia : Deposito.getMateriasByCurso(instancia.getCurso())) {
            if (materia.getNumeroSemana() == semana) {
                materiasSemanaActual.add(materia);
            }
        }
        for (final Materia materia : materiasSemanaActual) {
            final Container contMateriaNueva = new Container(new BorderLayout());
            contMateriaNueva.setUIID("ContainerRojo");
            contMateriaNueva.setPreferredH((int)(((double)Display.getInstance().getDisplayHeight())/460*40));
            final TextField txtMateria = new TextField(materia.getDescripcion());
            txtMateria.setUIID("TextFieldMateria");

            txtMateria.addDataChangeListener(new DataChangedListener() {
                public void dataChanged(int type, int index) {
                    materia.setDescripcion(txtMateria.getText());
                    materia.guardarEnStorage();
                }
            });
            Button eliminar = new Button(" ");
            eliminar.setUIID("ButtonEliminarMateria");
            eliminar.setPreferredW((int)(((double)Display.getInstance().getDisplayWidth())/320*40));
            eliminar.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent evt) {
                     Deposito.eliminarMateria(materia.getId());
                     loadMateria(cont, instancia, semana);
                }
            });
                   
            contMateriaNueva.addComponent(BorderLayout.WEST, eliminar);
            CheckBox check = new CheckBox();
            check.setUIID("CheckBoxMateria");
            contMateriaNueva.addComponent(BorderLayout.EAST, check);
            contMateriaNueva.addComponent(BorderLayout.CENTER, txtMateria);
            contenidoMateria.addComponent(contMateriaNueva);
            getCurrent().animateLayout(0);
        }
        Button btnAgregarMateria = new Button(" ");
        btnAgregarMateria.setUIID("ButtonAgregarInferior");
        btnAgregarMateria.setPreferredH((int)(((double)Display.getInstance().getDisplayHeight())/460*40));
        btnAgregarMateria.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                final Container contMateriaNueva = new Container(new BorderLayout());
                contMateriaNueva.setUIID("ContainerRojo");
                contMateriaNueva.setPreferredH((int)(((double)Display.getInstance().getDisplayHeight())/460*40));
                final TextField txtMateria = new TextField();
                txtMateria.setUIID("TextFieldMateria");
                Materia materiaNueva = new Materia(-1, (byte) semana, txtMateria.getText(), instancia.getCurso());
                materiaNueva.guardarEnStorage();

                loadMateria(cont, instancia, semana);
                Button eliminar = new Button(" ");
                eliminar.setUIID("ButtonEliminarMateria");
                eliminar.setPreferredW((int)(((double)Display.getInstance().getDisplayWidth())/320*40));
                CheckBox check = new CheckBox();
                check.setUIID("CheckBoxMateria");
                contMateriaNueva.addComponent(BorderLayout.WEST, eliminar);
                contMateriaNueva.addComponent(BorderLayout.EAST, check);
                contMateriaNueva.addComponent(BorderLayout.CENTER, txtMateria);
                contenidoMateria.addComponent(contMateriaNueva);
                getCurrent().animateLayout(0);
            }
        });

        cont.addComponent(BorderLayout.SOUTH, btnAgregarMateria);
        cont.addComponent(BorderLayout.CENTER, contenidoMateria);

        getCurrent().animateLayout(0);
    }
}
