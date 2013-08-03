/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imaginario.apollo.utilidades;

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
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

/**
 *
 * @author Ismael
 */
public class HashtableControls {
    
    public static Hashtable AsignacionToHashtable(Asignacion entidad){
        Hashtable table = new Hashtable();
        table.put("id", entidad.getId());
        table.put("nombre", entidad.getNombre());
        table.put("descripcion", entidad.getDescripcion());
        table.put("porcentaje", entidad.getPorcentaje());
        table.put("fechaEntrega", entidad.getFechaEntrega());
        Vector<Integer> notas = new Vector<Integer>();
        for(int i = 0; i < entidad.getNotas().size(); i++){
            notas.add(entidad.getNotas().get(i));
        }
        table.put("notas", notas);
        table.put("curso", entidad.getCurso());
        return table;
    }
    
    public static Hashtable AsistenciaToHashtable(Asistencia entidad){
        Hashtable table = new Hashtable();
        table.put("id", entidad.getId());
        table.put("fecha", entidad.getFecha());
        table.put("estado", entidad.getEstado());
        table.put("estudiante", entidad.getEstudiante());
        return table;
    }
    
    public static Hashtable CursoToHashtable(Curso entidad){
        Hashtable table = new Hashtable();
        table.put("id", entidad.getId());
        table.put("nombre", entidad.getNombre());
        table.put("descripcion", entidad.getDescripcion());
        Vector<Integer> asignaciones = new Vector<Integer>();
        for(int i = 0; i < entidad.getAsignaciones().size(); i++){
            asignaciones.add(entidad.getAsignaciones().get(i));
        }
        table.put("asignaciones", asignaciones);
        Vector<Integer> materias = new Vector<Integer>();
        for(int i = 0; i < entidad.getMaterias().size(); i++){
            materias.add(entidad.getMaterias().get(i));
        }
        table.put("materias", materias);
        Vector<Integer> instancias = new Vector<Integer>();
        for(int i = 0; i < entidad.getInstancias().size(); i++){
            instancias.add(entidad.getInstancias().get(i));
        }
        table.put("instancias", instancias);
        table.put("periodo", entidad.getPeriodo());
        return table;
    }
    
    public static Hashtable EstudianteToHashtable(Estudiante entidad){
        Hashtable table = new Hashtable();
        table.put("id", entidad.getId());
        table.put("nombre", entidad.getNombre());
        table.put("carne", entidad.getCarne());
        table.put("correo", entidad.getCorreo());
        Vector<Integer> notas = new Vector<Integer>();
        for(int i = 0; i < entidad.getNotas().size(); i++){
            notas.add(entidad.getNotas().get(i));
        }
        table.put("notas", notas);
        Vector<Integer> asistencias = new Vector<Integer>();
        for(int i = 0; i < entidad.getAsistencias().size(); i++){
            asistencias.add(entidad.getAsistencias().get(i));
        }
        table.put("asistencias", asistencias);
        table.put("instanciaCurso", entidad.getInstanciaCurso());
        return table;
    }
    
    public static Hashtable HorarioToHashtable(Horario entidad){
        Hashtable table = new Hashtable();
        table.put("id", entidad.getId());
        table.put("dia", entidad.getDia());
        table.put("entradaHora", entidad.getEntradaHora());
        table.put("entradaMinuto", entidad.getEntradaMinuto());
        table.put("salidaHora", entidad.getSalidaHora());
        table.put("salidaMinuto", entidad.getSalidaMinuto());
        table.put("instanciaCurso", entidad.getInstanciaCurso());
        return table;
    }
    
    public static Hashtable InstanciaToHashtable(InstanciaCurso entidad){
        Hashtable table = new Hashtable();
        table.put("id", entidad.getId());
        table.put("fechaInicio", entidad.getFechaInicio());
        table.put("cantidadSemanas", entidad.getCantidadSemanas());
        Vector<Integer> estudiantes = new Vector<Integer>();
        for(int i = 0; i < entidad.getEstudiantes().size(); i++){
            estudiantes.add(entidad.getEstudiantes().get(i));
        }
        table.put("estudiantes", estudiantes);
        Vector<Integer> horarios = new Vector<Integer>();
        for(int i = 0; i < entidad.getHorarios().size(); i++){
            horarios.add(entidad.getHorarios().get(i));
        }
        table.put("horarios", horarios);
        Vector<Integer> recordatorios = new Vector<Integer>();
        for(int i = 0; i < entidad.getRecordatorios().size(); i++){
            recordatorios.add(entidad.getRecordatorios().get(i));
        }
        table.put("recordatorios", recordatorios);
        table.put("curso", entidad.getCurso());
        return table;
    }
    
    public static Hashtable MateriaToHashtable(Materia entidad){
        Hashtable table = new Hashtable();
        table.put("id", entidad.getId());
        table.put("numeroSemana", entidad.getNumeroSemana());
        table.put("descripcion", entidad.getDescripcion());
        table.put("curso", entidad.getCurso());
        return table;
    }
    
    public static Hashtable NotaToHashtable(Nota entidad){
        Hashtable table = new Hashtable();
        table.put("id", entidad.getId());
        table.put("valor", entidad.getValor());
        table.put("asignacion", entidad.getAsignacion());
        table.put("estudiante", entidad.getEstudiante());
        return table;
    }
    
    public static Hashtable PeriodoToHashtable(Periodo entidad){
        Hashtable table = new Hashtable();
        table.put("id", entidad.getId());
        table.put("anio", entidad.getAnio());
        table.put("tipo", entidad.getTipo());
        table.put("numero", entidad.getNumero());
        table.put("institucion", entidad.getInstitucion());
        Vector<Integer> cursos = new Vector<Integer>();
        for(int i = 0; i < entidad.getCursos().size(); i++){
            cursos.add(entidad.getCursos().get(i));
        }
        table.put("cursos", cursos);
        table.put("profesor", entidad.getProfesor());
        return table;
    }
    
    public static Hashtable ProfesorToHashtable(Profesor entidad){
        Hashtable table = new Hashtable();
        table.put("id", entidad.getId());
        table.put("nombre", entidad.getNombre());
        table.put("correo", entidad.getCorreo());
        table.put("usuario", entidad.getUsuario());
        table.put("contrasenia", entidad.getContrasenia());
        Vector<Integer> periodos = new Vector<Integer>();
        for(int i = 0; i < entidad.getPeriodos().size(); i++){
            periodos.add(entidad.getPeriodos().get(i));
        }
        table.put("periodos", periodos);
        return table;
    }
    
    public static Hashtable RecordatorioToHashtable(Recordatorio entidad){
        Hashtable table = new Hashtable();
        table.put("id", entidad.getId());
        table.put("fecha", entidad.getFecha());
        table.put("texto", entidad.getTexto());
        table.put("instanciaCurso", entidad.getInstanciaCurso());
        return table;
    }
    
    public static Asignacion HashtableToAsignacion(Hashtable table){
        Asignacion entidad = new Asignacion();
        entidad.setId((Integer)table.get("id"));
        entidad.setNombre((String)table.get("nombre"));
        entidad.setDescripcion((String)table.get("descripcion"));
        entidad.setPorcentaje((Byte)table.get("porcentaje"));
        entidad.setFechaEntrega((Date)table.get("fechaEntrega"));
        for(int i = 0; i < ((Vector<Integer>)table.get("notas")).size(); i++){
            entidad.getNotas().add(((Vector<Integer>)table.get("notas")).get(i));
        }
        entidad.setCurso((Integer)table.get("curso"));
        return entidad;
    }
    
    public static Asistencia HashtableToAsistencia(Hashtable table){
        Asistencia entidad = new Asistencia();
        entidad.setId((Integer)table.get("id"));
        entidad.setFecha((Date)table.get("fecha"));
        entidad.setEstado((String)table.get("estado"));
        entidad.setEstudiante((Integer)table.get("estudiante"));
        return entidad;
    }
    
    public static Curso HashtableToCurso(Hashtable table){
        Curso entidad = new Curso();
        entidad.setId((Integer)table.get("id"));
        entidad.setNombre((String)table.get("nombre"));
        entidad.setDescripcion((String)table.get("descripcion"));
        for(int i = 0; i < ((Vector<Integer>)table.get("asignaciones")).size(); i++){
            entidad.getAsignaciones().add(((Vector<Integer>)table.get("asignaciones")).get(i));
        }
        for(int i = 0; i < ((Vector<Integer>)table.get("materias")).size(); i++){
            entidad.getMaterias().add(((Vector<Integer>)table.get("materias")).get(i));
        }
        for(int i = 0; i < ((Vector<Integer>)table.get("instancias")).size(); i++){
            entidad.getInstancias().add(((Vector<Integer>)table.get("instancias")).get(i));
        }
        entidad.setPeriodo((Integer)table.get("periodo"));
        return entidad;
    }
    
    public static Estudiante HashtableToEstudiante(Hashtable table){
        Estudiante entidad = new Estudiante();
        entidad.setId((Integer)table.get("id"));
        entidad.setNombre((String)table.get("nombre"));
        entidad.setCarne((String)table.get("carne"));
        entidad.setCorreo((String)table.get("correo"));
        for(int i = 0; i < ((Vector<Integer>)table.get("notas")).size(); i++){
            entidad.getNotas().add(((Vector<Integer>)table.get("notas")).get(i));
        }
        for(int i = 0; i < ((Vector<Integer>)table.get("asistencias")).size(); i++){
            entidad.getAsistencias().add(((Vector<Integer>)table.get("asistencias")).get(i));
        }
        entidad.setInstanciaCurso((Integer)table.get("instanciaCurso"));
        return entidad;
    }
    
    public static Horario HashtableToHorario(Hashtable table){
        Horario entidad = new Horario();
        entidad.setId((Integer)table.get("id"));
        entidad.setDia((String)table.get("dia"));
        entidad.setEntradaHora((Byte)table.get("entradaHora"));
        entidad.setEntradaMinuto((Byte)table.get("entradaMinuto"));
        entidad.setSalidaHora((Byte)table.get("salidaHora"));
        entidad.setSalidaMinuto((Byte)table.get("salidaMinuto"));
        entidad.setInstanciaCurso((Integer)table.get("instanciaCurso"));
        return entidad;
    }
    
    public static InstanciaCurso HashtableToInstancia(Hashtable table){
        InstanciaCurso entidad = new InstanciaCurso();
        entidad.setId((Integer)table.get("id"));
        entidad.setFechaInicio((Date)table.get("fechaInicio"));
        entidad.setCantidadSemanas((Byte)table.get("cantidadSemanas"));
        for(int i = 0; i < ((Vector<Integer>)table.get("estudiantes")).size(); i++){
            entidad.getEstudiantes().add(((Vector<Integer>)table.get("estudiantes")).get(i));
        }
        for(int i = 0; i < ((Vector<Integer>)table.get("horarios")).size(); i++){
            entidad.getHorarios().add(((Vector<Integer>)table.get("horarios")).get(i));
        }
        for(int i = 0; i < ((Vector<Integer>)table.get("recordatorios")).size(); i++){
            entidad.getRecordatorios().add(((Vector<Integer>)table.get("recordatorios")).get(i));
        }
        entidad.setCurso((Integer)table.get("curso"));
        return entidad;
    }
    
    public static Materia HashtableToMateria(Hashtable table){
        Materia entidad = new Materia();
        entidad.setId((Integer)table.get("id"));
        entidad.setNumeroSemana((Byte)table.get("numeroSemana"));
        entidad.setDescripcion((String)table.get("descripcion"));
        entidad.setCurso((Integer)table.get("curso"));
        return entidad;
    }
    
    public static Nota HashtableToNota(Hashtable table){
        Nota entidad = new Nota();
        entidad.setId((Integer)table.get("id"));
        entidad.setValor((Byte)table.get("valor"));
        entidad.setAsignacion((Integer)table.get("asignacion"));
        entidad.setEstudiante((Integer)table.get("estudiante"));
        return entidad;
    }
    
    public static Periodo HashtableToPeriodo(Hashtable table){
        Periodo entidad = new Periodo();
        entidad.setId((Integer)table.get("id"));
        entidad.setAnio((Short)table.get("anio"));
        entidad.setTipo((String)table.get("tipo"));
        entidad.setNumero((Byte)table.get("numero"));
        entidad.setInstitucion((String)table.get("institucion"));
        for(int i = 0; i < ((Vector<Integer>)table.get("cursos")).size(); i++){
            entidad.getCursos().add(((Vector<Integer>)table.get("cursos")).get(i));
        }
        entidad.setProfesor((Integer)table.get("profesor"));
        return entidad;
    }
    
    public static Profesor HashtableToProfesor(Hashtable table){
        Profesor entidad = new Profesor();
        entidad.setId((Integer)table.get("id"));
        entidad.setNombre((String)table.get("nombre"));
        entidad.setCorreo((String)table.get("correo"));
        entidad.setUsuario((String)table.get("usuario"));
        entidad.setContrasenia((String)table.get("contrasenia"));
        for(int i = 0; i < ((Vector<Integer>)table.get("periodos")).size(); i++){
            entidad.getPeriodos().add(((Vector<Integer>)table.get("periodos")).get(i));
        }
        return entidad;
    }
    
    public static Recordatorio HashtableToRecordatorio(Hashtable table){
        Recordatorio entidad = new Recordatorio();
        entidad.setId((Integer)table.get("id"));
        entidad.setFecha((Date)table.get("fecha"));
        entidad.setTexto((String)table.get("texto"));
        entidad.setInstanciaCurso((Integer)table.get("instanciaCurso"));
        return entidad;
    }
    
}
