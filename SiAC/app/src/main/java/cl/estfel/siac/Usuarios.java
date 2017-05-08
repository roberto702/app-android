package cl.estfel.siac;



public class Usuarios {

    private String nombre_json, apellidos_json, usuario_json;

    public Usuarios(String nombre_json, String apellidos_json, String usuario_json){
        this.setNombre_json(nombre_json);
        this.setApellidos_json(apellidos_json);
        this.setUsuario_json(usuario_json);


    }


    public String getNombre_json() {
        return nombre_json;
    }

    public void setNombre_json(String nombre_json) {
        this.nombre_json = nombre_json;
    }

    public String getApellidos_json() {
        return apellidos_json;
    }

    public void setApellidos_json(String apellidos_json) {
        this.apellidos_json = apellidos_json;
    }

    public String getUsuario_json() {
        return usuario_json;
    }

    public void setUsuario_json(String usuario_json) {
        this.usuario_json = usuario_json;
    }
}

