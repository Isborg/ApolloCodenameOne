package com.imaginario.apollo;


import com.codename1.io.Storage;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
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
import com.imaginario.apollo.utilidades.MenuHamburguesa;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Vector;
import userclasses.StateMachine;

public class ApolloMain {
   
    private Form current;

    public void init(Object context) {
    }

    public void start() {/*
        if(current != null){
            current.show();
            return;
        }*/
        new StateMachine("/theme");
        
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
        
        current = new Form();
        
        Profesor profesor = new Profesor(1,"Ismael Baum","ismael.baum@gmail.com","isborg","claveclave");
        Deposito.guardarProfesor(profesor);
        Periodo periodo1 = new Periodo(1,(short)2013,"Cuatrimestral",(byte)2,"Universidad Latina",1);
        Deposito.guardarPeriodo(periodo1);
        
        MenuHamburguesa.agregar(current, profesor);
        current.show();
    }

    public void stop() {
        current = Display.getInstance().getCurrent();
    }
    
    public void destroy() {
    }
}
