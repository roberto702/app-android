package cl.estfel.siac;

/**
 * Created by robma on 09/06/2017.
 */


public class Asistencia {

    private String nombreClaseAsistencia_json, nombreAlumnoAsistencia_json, fechaAlumnoAsistencia_json;

    public Asistencia(String nombreClaseAsistencia_json, String nombreAlumnoAsistencia_json, String fechaAlumnoAsistencia_json){
        this.setNombreClaseAsistencia_json(nombreClaseAsistencia_json);
        this.setNombreAlumno_json(nombreAlumnoAsistencia_json);
        this.setFechaAsistencia_json(fechaAlumnoAsistencia_json);
    }


    public String getNombreClaseAsistencia_json(){
        return nombreClaseAsistencia_json;

    }

    public void setNombreClaseAsistencia_json(String nombreClaseAsistencia_json){
        this.nombreClaseAsistencia_json = nombreClaseAsistencia_json;
    }


    public String getNombreAlumno_json() {
        return nombreAlumnoAsistencia_json;
    }

    public void setNombreAlumno_json(String nombreAlumnoAsistencia_json) {
        this.nombreAlumnoAsistencia_json = nombreAlumnoAsistencia_json;
    }

    public String getFechaAsistencia_json() {
        return fechaAlumnoAsistencia_json;
    }

    public void setFechaAsistencia_json(String fechaAlumnoAsistencia_json) {
        this.fechaAlumnoAsistencia_json = fechaAlumnoAsistencia_json;
    }


}

