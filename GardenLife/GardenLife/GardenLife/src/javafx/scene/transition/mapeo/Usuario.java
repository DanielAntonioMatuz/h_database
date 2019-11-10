package javafx.scene.transition.mapeo;

import java.io.Serializable;

public class Usuario implements Serializable {

    private Integer id;

    private String usuario;

    private String contrasenia;


    public Usuario() {
    }

    public Usuario(String nombre1, String contrasenia1) {
        this.usuario = nombre1;
        this.contrasenia = contrasenia1;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }


}
