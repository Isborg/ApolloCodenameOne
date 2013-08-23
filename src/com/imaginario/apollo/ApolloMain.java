package com.imaginario.apollo;


import com.codename1.io.Storage;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.imaginario.apollo.entidades.Asignacion;
import com.imaginario.apollo.entidades.Asistencia;
import com.imaginario.apollo.entidades.Curso;
import com.imaginario.apollo.entidades.Estudiante;
import com.imaginario.apollo.entidades.Horario;
import com.imaginario.apollo.entidades.InstanciaCurso;
import com.imaginario.apollo.entidades.Materia;
import com.imaginario.apollo.entidades.Nota;
import com.imaginario.apollo.entidades.Periodo;
import com.imaginario.apollo.entidades.Profesor;
import com.imaginario.apollo.entidades.Recordatorio;
import com.imaginario.apollo.utilidades.Deposito;
import com.imaginario.apollo.utilidades.JsonParse;
import com.imaginario.apollo.utilidades.MenuHamburguesa;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;
import org.codehaus.jettison.json.JSONException;
import userclasses.StateMachine;

public class ApolloMain {
   
    private Form current;
    private boolean hamburguesaOpen;

    public void init(Object context) {
    }

    public void start() throws JSONException {
        // Set theme
        try{
            UIManager.getInstance().setThemeProps(Resources.open(Display.getInstance().getResourceAsStream(getClass(), "/theme.res")).getTheme("Theme 2"));
        }catch(IOException e){}
        //Storage.getInstance().clearStorage();
        if(Storage.getInstance().readObject("depositoProfesores") == null){
            Storage.getInstance().writeObject("depositoAsignaciones", new Hashtable());
            Storage.getInstance().writeObject("depositoAsistencias", new Hashtable());
            Storage.getInstance().writeObject("depositoCursos", new Hashtable());
            Storage.getInstance().writeObject("depositoEstudiantes", new Hashtable());
            Storage.getInstance().writeObject("depositoHorarios", new Hashtable());
            Storage.getInstance().writeObject("depositoInstancias", new Hashtable());
            Storage.getInstance().writeObject("depositoMaterias", new Hashtable());
            Storage.getInstance().writeObject("depositoNotas", new Hashtable());
            Storage.getInstance().writeObject("depositoPeriodos", new Hashtable());
            Storage.getInstance().writeObject("depositoProfesores", new Hashtable());
            Storage.getInstance().writeObject("depositoRecordatorios", new Hashtable());
        }
        /*
        final Profesor profesor = new Profesor(1,"Ismael Baum","ismael.baum@gmail.com","isborg","claveclave");
        profesor.guardarEnStorage();        
        Periodo periodo1 = new Periodo(1,(short)2013,"Cuatrimestral",(byte)2,"Universidad Latina",1);
        periodo1.guardarEnStorage();
        Periodo periodo2 = new Periodo(2,(short)2014,"Bimestral",(byte)3,"TEC",1);
        periodo2.guardarEnStorage();
        Curso curso1 = new Curso(1,"Proyecto IV","Desarrollo de aplicaciones móviles.",1);
        curso1.guardarEnStorage();
        Curso curso2 = new Curso(2,"Cálculo I","Límites, optimización, derivadas, integrales.",1);
        curso2.guardarEnStorage();
        Curso curso3 = new Curso(3,"Seminario","...",1);
        curso3.guardarEnStorage();
        Curso curso4 = new Curso(4,"Física I","Movimiento parabólico.",1);
        curso4.guardarEnStorage();
        Curso curso5 = new Curso(5,"Programación II","Introducción a objetos.",1);
        curso5.guardarEnStorage();
        InstanciaCurso instancia1 = new InstanciaCurso(1, new Date(), (byte)4, 1, 1);
        instancia1.guardarEnStorage();
        Horario horario1 = new Horario(1, "Martes", (byte)12, (byte)0, (byte)14, (byte)30, 1);
        horario1.guardarEnStorage();
        Horario horario2 = new Horario(2, "Jueves", (byte)15, (byte)0, (byte)17, (byte)0, 1);
        horario2.guardarEnStorage();
        */
        
        Profesor profesor = Deposito.getProfesorById(1);
        new Inicio(profesor);
    }

    public void stop() {
        current = Display.getInstance().getCurrent();
    }
    
    public void destroy() {
    }
}
