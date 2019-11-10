package javafx.scene.transition;

import java.io.Serializable;

public class Usuario implements Serializable {

    private Integer id;

    private String nombre;

    private String sexo;

    private Integer edad;

    private Ciudad ciudad;

    public Usuario() {
    }

    public Usuario(String firstname, String lastname, Integer edad) {
        this.nombre = firstname;
        this.sexo = lastname;
        this.edad = edad;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

}
