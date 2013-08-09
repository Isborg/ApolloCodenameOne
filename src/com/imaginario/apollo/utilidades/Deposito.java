/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imaginario.apollo.utilidades;

import com.codename1.io.Storage;
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
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Vector;

/**
 *
 * @author Ismael
 */
public class Deposito {
    
    public static void guardarAsignacion(Asignacion entidad){
        Hashtable entidades = (Hashtable)Storage.getInstance().readObject("depositoAsignaciones");
        entidades.put(entidad.getId(), entidad.toHashtable());
        Storage.getInstance().writeObject("depositoAsignaciones", entidades);
    }
    
    public static void guardarAsistencia(Asistencia entidad){
        Hashtable entidades = (Hashtable)Storage.getInstance().readObject("depositoAsistencias");
        entidades.put(entidad.getId(), entidad.toHashtable());
        Storage.getInstance().writeObject("depositoAsistencias", entidades);
    }
    
    public static void guardarCurso(Curso entidad){
        Hashtable entidades = (Hashtable)Storage.getInstance().readObject("depositoCursos");
        entidades.put(entidad.getId(), entidad.toHashtable());
        Storage.getInstance().writeObject("depositoCursos", entidades);
    }
    
    public static void guardarEstudiante(Estudiante entidad){
        Hashtable entidades = (Hashtable)Storage.getInstance().readObject("depositoEstudiantes");
        entidades.put(entidad.getId(), entidad.toHashtable());
        Storage.getInstance().writeObject("depositoEstudiantes", entidades);
    }
    
    public static void guardarHorario(Horario entidad){
        Hashtable entidades = (Hashtable)Storage.getInstance().readObject("depositoHorarios");
        entidades.put(entidad.getId(), entidad.toHashtable());
        Storage.getInstance().writeObject("depositoHorarios", entidades);
    }
    
    public static void guardarInstancia(InstanciaCurso entidad){
        Hashtable entidades = (Hashtable)Storage.getInstance().readObject("depositoInstancias");
        entidades.put(entidad.getId(), entidad.toHashtable());
        Storage.getInstance().writeObject("depositoInstancias", entidades);
    }
    
    public static void guardarMateria(Materia entidad){
        Hashtable entidades = (Hashtable)Storage.getInstance().readObject("depositoMaterias");
        entidades.put(entidad.getId(), entidad.toHashtable());
        Storage.getInstance().writeObject("depositoMaterias", entidades);
    }
    
    public static void guardarNota(Nota entidad){
        Hashtable entidades = (Hashtable)Storage.getInstance().readObject("depositoNotas");
        entidades.put(entidad.getId(), entidad.toHashtable());
        Storage.getInstance().writeObject("depositoNotas", entidades);
    }
    
    public static void guardarPeriodo(Periodo entidad){
        Hashtable entidades = (Hashtable)Storage.getInstance().readObject("depositoPeriodos");
        entidades.put(entidad.getId(), entidad.toHashtable());
        Storage.getInstance().writeObject("depositoPeriodos", entidades);
    }
    
    public static void guardarProfesor(Profesor entidad){
        Hashtable entidades = (Hashtable)Storage.getInstance().readObject("depositoProfesores");
        entidades.put(entidad.getId(), entidad.toHashtable());
        Storage.getInstance().writeObject("depositoProfesores", entidades);
    }
    
    public static void guardarRecordatorio(Recordatorio entidad){
        Hashtable entidades = (Hashtable)Storage.getInstance().readObject("depositoRecordatorios");
        entidades.put(entidad.getId(), entidad.toHashtable());
        Storage.getInstance().writeObject("depositoRecordatorios", entidades);
    }
    
    public static Asignacion getAsignacionById(int id){
        Hashtable table = (Hashtable)((Hashtable)Storage.getInstance().readObject("depositoAsignaciones")).get(id);
        return new Asignacion(table);
    }
    
    public static Asistencia getAsistenciaById(int id){
        Hashtable table = (Hashtable)((Hashtable)Storage.getInstance().readObject("depositoAsistencias")).get(id);
        return new Asistencia(table);
    }
    
    public static Curso getCursoById(int id){
        Hashtable table = (Hashtable)((Hashtable)Storage.getInstance().readObject("depositoCursos")).get(id);
        return new Curso(table);
    }
    
    public static Estudiante getEstudianteById(int id){
        Hashtable table = (Hashtable)((Hashtable)Storage.getInstance().readObject("depositoEstudiantes")).get(id);
        return new Estudiante(table);
    }
    
    public static Horario getHorarioById(int id){
        Hashtable table = (Hashtable)((Hashtable)Storage.getInstance().readObject("depositoHorarios")).get(id);
        return new Horario(table);
    }
    
    public static InstanciaCurso getInstanciaById(int id){
        Hashtable table = (Hashtable)((Hashtable)Storage.getInstance().readObject("depositoInstancias")).get(id);
        return new InstanciaCurso(table);
    }
    
    public static Materia getMateriaById(int id){
        Hashtable table = (Hashtable)((Hashtable)Storage.getInstance().readObject("depositoMaterias")).get(id);
        return new Materia(table);
    }
    
    public static Nota getNotaById(int id){
        Hashtable table = (Hashtable)((Hashtable)Storage.getInstance().readObject("depositoNotas")).get(id);
        return new Nota(table);
    }
    
    public static Periodo getPeriodoById(int id){
        Hashtable table = (Hashtable)((Hashtable)Storage.getInstance().readObject("depositoPeriodos")).get(id);
        return new Periodo(table);
    }
    
    public static Profesor getProfesorById(int id){
        Hashtable table = (Hashtable)((Hashtable)Storage.getInstance().readObject("depositoProfesores")).get(id);
        return new Profesor(table);
    }
    
    public static Recordatorio getRecordatorioById(int id){
        Hashtable table = (Hashtable)((Hashtable)Storage.getInstance().readObject("depositoRecordatorios")).get(id);
        return new Recordatorio(table);
    }
    
    public static ArrayList<Asignacion> getAsignacionesByCurso(int idCurso){
        ArrayList<Asignacion> entidades = new ArrayList<Asignacion>();
        for (int idAsignacion : getCursoById(idCurso).getAsignaciones()) {
            entidades.add(getAsignacionById(idAsignacion));
        }
        return entidades;
    }
    
    public static ArrayList<Asistencia> getAsistenciasByEstudiante(int idEstudiante){
        ArrayList<Asistencia> entidades = new ArrayList<Asistencia>();
        for (int idAsistencia : getEstudianteById(idEstudiante).getAsistencias()) {
            entidades.add(getAsistenciaById(idAsistencia));
        }
        return entidades;
    }
    
    public static ArrayList<Curso> getCursosByPeriodo(int idPeriodo){
        ArrayList<Curso> entidades = new ArrayList<Curso>();
        for (int idCurso : getPeriodoById(idPeriodo).getCursos()) {
            entidades.add(getCursoById(idCurso));
        }
        return entidades;
    }
    
    public static ArrayList<Estudiante> getAsignacionesByInstancia(int idInstancia){
        ArrayList<Estudiante> entidades = new ArrayList<Estudiante>();
        for (int idEstudiante : getInstanciaById(idInstancia).getEstudiantes()) {
            entidades.add(getEstudianteById(idEstudiante));
        }
        return entidades;
    }
    
    public static ArrayList<Horario> getHorariosByInstancia(int idInstancia){
        ArrayList<Horario> entidades = new ArrayList<Horario>();
        for (int idHorario : getInstanciaById(idInstancia).getHorarios()) {
            entidades.add(getHorarioById(idHorario));
        }
        return entidades;
    }
    
    public static ArrayList<InstanciaCurso> getInstanciasByCurso(int idCurso){
        ArrayList<InstanciaCurso> entidades = new ArrayList<InstanciaCurso>();
        for (int idInstancia : getCursoById(idCurso).getInstancias()) {
            entidades.add(getInstanciaById(idInstancia));
        }
        return entidades;
    }
    
    public static ArrayList<Materia> getMateriasByCurso(int idCurso){
        ArrayList<Materia> entidades = new ArrayList<Materia>();
        for (int idMateria : getCursoById(idCurso).getMaterias()) {
            entidades.add(getMateriaById(idMateria));
        }
        return entidades;
    }
    
    public static ArrayList<Nota> getNotasByAsignacion(int idAsignacion){
        ArrayList<Nota> entidades = new ArrayList<Nota>();
        for (int idNota : getAsignacionById(idAsignacion).getNotas()) {
            entidades.add(getNotaById(idNota));
        }
        return entidades;
    }
    
    public static ArrayList<Nota> getNotasByEstudiante(int idEstudiante){
        ArrayList<Nota> entidades = new ArrayList<Nota>();
        for (int idNota : getEstudianteById(idEstudiante).getNotas()) {
            entidades.add(getNotaById(idNota));
        }
        return entidades;
    }
    
    public static ArrayList<Periodo> getPeriodosByProfesor(int idProfesor){
        ArrayList<Periodo> entidades = new ArrayList<Periodo>();
        for (int idPeriodo : getProfesorById(idProfesor).getPeriodos()) {
            entidades.add(getPeriodoById(idPeriodo));
        }
        return entidades;
    }
    
    public static ArrayList<Recordatorio> getRecordatoriosByInstancia(int idInstancia){
        ArrayList<Recordatorio> entidades = new ArrayList<Recordatorio>();
        for (int idRecordatorio : getInstanciaById(idInstancia).getRecordatorios()) {
            entidades.add(getRecordatorioById(idRecordatorio));
        }
        return entidades;
    }
    
    public static void eliminarAsignacion(Integer id){
        Asignacion asignacion = getAsignacionById(id);
        for (int nota : asignacion.getNotas()) {
            eliminarNota(nota);
        }
        Hashtable asignaciones = (Hashtable)Storage.getInstance().readObject("depositoAsignaciones");
        asignaciones.remove(id);
        Storage.getInstance().writeObject("depositoAsignaciones", asignaciones);
        Curso curso = getCursoById(asignacion.getCurso());
        curso.getAsignaciones().remove(id);
        Hashtable cursos = (Hashtable)Storage.getInstance().readObject("depositoCursos");
        cursos.put(curso.getId(), curso);
        Storage.getInstance().writeObject("depositoCursos", cursos);
    }
    
    public static void eliminarAsistencia(Integer id){
        Asistencia asistencia = getAsistenciaById(id);
        Hashtable asistencias = (Hashtable)Storage.getInstance().readObject("depositoAsistencias");
        asistencias.remove(id);
        Storage.getInstance().writeObject("depositoAsistencias", asistencias);
        Estudiante estudiante = getEstudianteById(asistencia.getEstudiante());
        estudiante.getAsistencias().remove(id);
        Hashtable estudiantes = (Hashtable)Storage.getInstance().readObject("depositoEstudiantes");
        estudiantes.put(estudiante.getId(), estudiante);
        Storage.getInstance().writeObject("depositoEstudiantes", estudiantes);
    }
    
    public static void eliminarCurso(Integer id){
        Curso curso = getCursoById(id);
        for (int asignacion : curso.getAsignaciones()) {
            eliminarAsignacion(asignacion);
        }
        for (int materia : curso.getMaterias()) {
            eliminarMateria(materia);
        }
        for (int instancia : curso.getInstancias()) {
            eliminarInstancia(instancia);
        }
        Hashtable cursos = (Hashtable)Storage.getInstance().readObject("depositoCursos");
        cursos.remove(id);
        Storage.getInstance().writeObject("depositoCursos", cursos);
        Periodo periodo = getPeriodoById(curso.getPeriodo());
        periodo.getCursos().remove(id);
        Hashtable periodos = (Hashtable)Storage.getInstance().readObject("depositoPeriodos");
        periodos.put(periodo.getId(), periodo);
        Storage.getInstance().writeObject("depositoPeriodos", periodos);
    }
    
    public static void eliminarEstudiante(Integer id){
        Estudiante estudiante = getEstudianteById(id);
        for (int nota : estudiante.getNotas()) {
            eliminarNota(nota);
        }
        for (int asistencia : estudiante.getAsistencias()) {
            eliminarAsistencia(asistencia);
        }
        Hashtable estudiantes = (Hashtable)Storage.getInstance().readObject("depositoEstudiantes");
        estudiantes.remove(id);
        Storage.getInstance().writeObject("depositoEstudiantes", estudiantes);
        InstanciaCurso instancia = getInstanciaById(estudiante.getInstanciaCurso());
        instancia.getEstudiantes().remove(id);
        Hashtable instancias = (Hashtable)Storage.getInstance().readObject("depositoInstancias");
        instancias.put(instancia.getId(), instancia);
        Storage.getInstance().writeObject("depositoInstancias", instancias);
    }
    
    public static void eliminarHorario(Integer id){
        Horario horario = getHorarioById(id);
        Hashtable horarios = (Hashtable)Storage.getInstance().readObject("depositoHorarios");
        horarios.remove(id);
        Storage.getInstance().writeObject("depositoHorarios", horarios);
        InstanciaCurso instancia = getInstanciaById(horario.getInstanciaCurso());
        instancia.getHorarios().remove(id);
        Hashtable instancias = (Hashtable)Storage.getInstance().readObject("depositoInstancias");
        instancias.put(instancia.getId(), instancia);
        Storage.getInstance().writeObject("depositoInstancias", instancias);
    }
    
    public static void eliminarInstancia(Integer id){
        InstanciaCurso instancia = getInstanciaById(id);
        for (int estudiante : instancia.getEstudiantes()) {
            eliminarEstudiante(estudiante);
        }
        for (int horario : instancia.getHorarios()) {
            eliminarHorario(horario);
        }
        for (int recordatorio : instancia.getRecordatorios()) {
            eliminarRecordatorio(recordatorio);
        }
        Hashtable instancias = (Hashtable)Storage.getInstance().readObject("depositoInstancias");
        instancias.remove(id);
        Storage.getInstance().writeObject("depositoInstancias", instancias);
        Curso curso = getCursoById(instancia.getCurso());
        curso.getInstancias().remove(id);
        Hashtable cursos = (Hashtable)Storage.getInstance().readObject("depositoCursos");
        cursos.put(curso.getId(), curso);
        Storage.getInstance().writeObject("depositoCursos", cursos);
    }
    
    public static void eliminarMateria(Integer id){
        Materia materia = getMateriaById(id);
        Hashtable materias = (Hashtable)Storage.getInstance().readObject("depositoMaterias");
        materias.remove(id);
        Storage.getInstance().writeObject("depositoMaterias", materias);
        Curso curso = getCursoById(materia.getCurso());
        curso.getMaterias().remove(id);
        Hashtable cursos = (Hashtable)Storage.getInstance().readObject("depositoCursos");
        cursos.put(curso.getId(), curso);
        Storage.getInstance().writeObject("depositoCursos", cursos);
    }
    
    public static void eliminarNota(Integer id){
        Nota nota = getNotaById(id);
        Hashtable notas = (Hashtable)Storage.getInstance().readObject("depositoNotas");
        notas.remove(id);
        Storage.getInstance().writeObject("depositoNotas", notas);
        Asignacion asignacion = getAsignacionById(nota.getAsignacion());
        asignacion.getNotas().remove(id);
        Hashtable asignaciones = (Hashtable)Storage.getInstance().readObject("depositoAsignaciones");
        asignaciones.put(asignacion.getId(), asignacion);
        Storage.getInstance().writeObject("depositoAsignaciones", asignaciones);
        Estudiante estudiante = getEstudianteById(nota.getEstudiante());
        estudiante.getNotas().remove(id);
        Hashtable estudiantes = (Hashtable)Storage.getInstance().readObject("depositoEstudiantes");
        estudiantes.put(estudiante.getId(), estudiante);
        Storage.getInstance().writeObject("depositoEstudiantes", estudiantes);
    }
    
    public static void eliminarPeriodo(Integer id){
        Periodo periodo = getPeriodoById(id);
        for (int curso : periodo.getCursos()) {
            eliminarCurso(curso);
        }
        Hashtable periodos = (Hashtable)Storage.getInstance().readObject("depositoPeriodos");
        periodos.remove(id);
        Storage.getInstance().writeObject("depositoPeriodos", periodos);
        Profesor profesor = getProfesorById(periodo.getProfesor());
        profesor.getPeriodos().remove(id);
        Hashtable profesores = (Hashtable)Storage.getInstance().readObject("depositoProfesores");
        profesores.put(profesor.getId(), profesor);
        Storage.getInstance().writeObject("depositoProfesores", profesores);
    }
    
    public static void eliminarProfesor(Integer id){
        Profesor profesor = getProfesorById(id);
        for (int periodo : profesor.getPeriodos()) {
            eliminarPeriodo(periodo);
        }
        Hashtable profesores = (Hashtable)Storage.getInstance().readObject("depositoProfesores");
        profesores.remove(id);
        Storage.getInstance().writeObject("depositoProfesores", profesores);
    }
    
    public static void eliminarRecordatorio(Integer id){
        Recordatorio recordatorio = getRecordatorioById(id);
        Hashtable recordatorios = (Hashtable)Storage.getInstance().readObject("depositoRecordatorios");
        recordatorios.remove(id);
        Storage.getInstance().writeObject("depositoRecordatorios", recordatorios);
        InstanciaCurso instancia = getInstanciaById(recordatorio.getInstanciaCurso());
        instancia.getRecordatorios().remove(id);
        Hashtable instancias = (Hashtable)Storage.getInstance().readObject("depositoInstancias");
        instancias.put(instancia.getId(), instancia);
        Storage.getInstance().writeObject("depositoInstancias", instancias);
    }
    
}
