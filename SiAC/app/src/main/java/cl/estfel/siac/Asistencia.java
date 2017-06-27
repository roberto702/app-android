package cl.estfel.siac;

/**
 * Created by robma on 26/06/2017.
 */

public class Asistencia {

    private String nombreAlumno_json;
    private String apellidoAlumno_json;
    private String idclaseAlumno_json;
    private String rutAlumno_json;

    public Asistencia(String nombreAlumno_json, String apellidoAlumno_json, String idclaseAlumno_json, String rutAlumno_json) {
        this.nombreAlumno_json = nombreAlumno_json;
        this.apellidoAlumno_json = apellidoAlumno_json;
        this.idclaseAlumno_json = idclaseAlumno_json;
        this.rutAlumno_json = rutAlumno_json;
    }

    public Asistencia(){}

    public String getNombreAlumno_json() {
        return nombreAlumno_json;
    }

    public void setNombreAlumno_json(String nombreAlumno_json) {
        this.nombreAlumno_json = nombreAlumno_json;
    }

    public String getApellidoAlumno_json() {
        return apellidoAlumno_json;
    }

    public void setApellidoAlumno_json(String apellidoAlumno_json) {
        this.apellidoAlumno_json = apellidoAlumno_json;
    }

    public String getIdclaseAlumno_json() {
        return idclaseAlumno_json;
    }

    public void setIdclaseAlumno_json(String idclaseAlumno_json) {
        this.idclaseAlumno_json = idclaseAlumno_json;
    }

    public String getRutAlumno_json() {
        return rutAlumno_json;
    }

    public void setRutAlumno_json(String rutAlumno_json) {
        this.rutAlumno_json = rutAlumno_json;
    }
}
