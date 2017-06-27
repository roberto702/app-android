package cl.estfel.siac;


public class Clases {

    private String nombredelaClase_json;

    private String idClase_json;


    public Clases(String nombredelaClase_json, String idClase_json) {
        this.nombredelaClase_json = nombredelaClase_json;
        this.idClase_json = idClase_json;
    }

    public String getNombredelaClase_json() {
        return nombredelaClase_json;
    }

    public void setNombredelaClase_json(String nombredelaClase_json) {
        this.nombredelaClase_json = nombredelaClase_json;
    }

    public String getIdClase_json() {
        return idClase_json;
    }

    public void setIdClase_json(String idClase_json) {
        this.idClase_json = idClase_json;
    }
}
