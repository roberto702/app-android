package cl.estfel.siac;

/**
 * Created by robma on 10/06/2017.
 */

public class Clases {

    private String nombredelaClase_json, fechacreacionClase_json;

    public Clases(String nombredelaClase_json, String fechacreacionClase_json){
        this.setNombredelaClase_json(nombredelaClase_json);
        this.setFechacreacionClase(fechacreacionClase_json);

    }


    public String getNombredelaClase_json() {
        return nombredelaClase_json;
    }

    public void setNombredelaClase_json(String nombredelaClase_json) {
        this.nombredelaClase_json = nombredelaClase_json;
    }

    public String getFechacreacionClase_json() {
        return fechacreacionClase_json;
    }

    public void setFechacreacionClase(String fechacreacionClase_json) {
        this.fechacreacionClase_json = fechacreacionClase_json;
    }

}
