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
import com.codename1.ui.Dialog;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.events.DataChangedListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.imaginario.apollo.entidades.Asistencia;
import com.imaginario.apollo.entidades.Estudiante;
import com.imaginario.apollo.entidades.InstanciaCurso;
import com.imaginario.apollo.entidades.Recordatorio;
import com.imaginario.apollo.utilidades.Deposito;
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
    
    public DetalleCurso(Form _parent, Profesor profesor, InstanciaCurso instancia){
        iniciarForm(instancia.toString(), _parent, profesor);
        
        Container subTitulo = new Container(new BorderLayout());
        loadPantallaRecordatorios(subTitulo, instancia);
        getCurrent().addComponent(BorderLayout.CENTER, subTitulo);
        getCurrent().show();
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
                dlg.setDisposeWhenPointerOutOfBounds(true);
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
        final Container subContenido = new Container(new BorderLayout());
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
        getCurrent().animateLayout(0);
    }
    
    private void guardarLockedColumnsNotas(int idInstancia, Vector<Boolean> cols){
        if(!Storage.getInstance().exists("lockedColumnsNotas")){
            Storage.getInstance().writeObject("lockedColumnsNotas", new Hashtable());
        }
        Hashtable table = (Hashtable)Storage.getInstance().readObject("lockedColumnsNotas");
        table.put(idInstancia, cols);
        Storage.getInstance().writeObject("lockedColumnsNotas", table);
    }

    private Vector<Boolean> getLockedColumnsNotas(int idInstancia, int tamanio) {
        if(!Storage.getInstance().exists("lockedColumnsNotas")){
            Storage.getInstance().writeObject("lockedColumnsNotas", new Hashtable());
        }
        Hashtable table = (Hashtable)Storage.getInstance().readObject("lockedColumnsNotas");
        if(!table.containsKey(idInstancia)){
            Vector<Boolean> boolVector = new Vector<Boolean>();
            for(int i = 0; i < tamanio; i++){
                boolVector.add(false);
            }
            table.put(idInstancia, boolVector);
        }
        return (Vector<Boolean>)table.get(idInstancia);
    }
    
    public void llenarAsistencias(final Container cont, final InstanciaCurso instancia){
        cont.removeAll();
        ArrayList<Estudiante> estudiantes = Deposito.getEstudiantesByInstancia(instancia.getId());
        
        Container head = new Container(new GridLayout(1, 2));
        head.addComponent(new Label("Estudiantes"));
        Container subHead = new Container(new BoxLayout(BoxLayout.X_AXIS));
        subHead.setScrollableX(true);
        if(!estudiantes.isEmpty()){
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy");
            for(Asistencia a : Deposito.getAsistenciasByEstudiante(estudiantes.get(0).getId())){
                Label lblFecha = new Label(sdf.format(a.getFecha()));
                lblFecha.setPreferredW(65);
                subHead.addComponent(lblFecha);
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
                            dialogoEdicionEstudiante("Editar estudiante", e.getId(), instancia);
                            dlg.dispose();
                        }
                    });
                    dlg.addComponent(btnEditar);
                    Button btnEliminar = new Button("Eliminar");
                    btnEliminar.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                            final Dialog dlgConfirmar = new Dialog("�Est� seguro?");
                            dlgConfirmar.setDisposeWhenPointerOutOfBounds(true);
                            dlgConfirmar.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
                            dlgConfirmar.addComponent(new Label("Si acepta perder� todos los datos de este estudiante."));
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
        for(Estudiante e : estudiantes){
            asistencias.add(Deposito.getAsistenciasByEstudiante(e.getId()));
        }
        if(!estudiantes.isEmpty()){
            for(int iA = 0; iA < asistencias.get(0).size(); iA++){
                Container columna = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                for(int iE = 0; iE < estudiantes.size(); iE++){
                    final Asistencia asis = asistencias.get(iE).get(iA);
                    final Button btnEstado = new Button(" ");
                    btnEstado.setPreferredW(65);
                    btnEstado.setText(asis.getEstado());
                    final int iX = iA;
                    btnEstado.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                            if(!getLockedColumnsAsistencia(instancia.getId(), asistencias.get(0).size()).get(iX)){
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
                dialogoEdicionEstudiante("Nuevo estudiante", -1, instancia);
            }
        });
        barraInferior.addComponent(btnAgregarEstudiante);
        Container subBarraInferior = new Container(new BoxLayout(BoxLayout.X_AXIS));
        subBarraInferior.setScrollableX(true);
        if(!estudiantes.isEmpty()){
            for(int iA = 0; iA < asistencias.get(0).size(); iA++){
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
    
    private void recargarAsistencias(InstanciaCurso instancia){
        Container cont = (Container)((Container)((Container)((Container)getCurrent().getComponentAt(1)).getComponentAt(1)).getComponentAt(1)).getComponentAt(1);
        cont.removeAll();
        llenarAsistencias(cont, instancia);
    }
    
    private void dialogoEdicionEstudiante(String titulo, int idEstudiante, final InstanciaCurso instancia){
        final Estudiante estudiante = (idEstudiante != -1) ? Deposito.getEstudianteById(idEstudiante) :
                new Estudiante(idEstudiante, "", "", "", instancia.getId());
        
        final Dialog dlg = new Dialog(titulo);
        dlg.setDisposeWhenPointerOutOfBounds(true);
        dlg.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        dlg.addComponent(new Label("Nombre:"));
        final TextField txtNombre = new TextField(estudiante.getNombre());
        dlg.addComponent(txtNombre);
        dlg.addComponent(new Label("Carn�:"));
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
                recargarAsistencias(instancia);
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
    
    private void guardarLockedColumnsAsistencia(int idInstancia, Vector<Boolean> cols){
        if(!Storage.getInstance().exists("lockedColumnsAsistencia")){
            Storage.getInstance().writeObject("lockedColumnsAsistencia", new Hashtable());
        }
        Hashtable table = (Hashtable)Storage.getInstance().readObject("lockedColumnsAsistencia");
        table.put(idInstancia, cols);
        Storage.getInstance().writeObject("lockedColumnsAsistencia", table);
    }

    private Vector<Boolean> getLockedColumnsAsistencia(int idInstancia, int tamanio) {
        if(!Storage.getInstance().exists("lockedColumnsAsistencia")){
            Storage.getInstance().writeObject("lockedColumnsAsistencia", new Hashtable());
        }
        Hashtable table = (Hashtable)Storage.getInstance().readObject("lockedColumnsAsistencia");
        if(!table.containsKey(idInstancia)){
            Vector<Boolean> boolVector = new Vector<Boolean>();
            for(int i = 0; i < tamanio; i++){
                boolVector.add(false);
            }
            table.put(idInstancia, boolVector);
        }
        return (Vector<Boolean>)table.get(idInstancia);
    }
    
    private String siguienteEstado(String estado){
        String[] estados = {"","Presente","Ausente","Tarde"};
        int indice = 0;
        try{
            while(!estado.equals(estados[indice]))
                indice++;
        }catch(Exception e){}
        indice = (indice == estados.length - 1) ? 0 : ++indice;
        return estados[indice];
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
