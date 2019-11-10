package javafx.scene.transition.mapeo;

import java.io.Serializable;

public class Producto implements Serializable {

    private Integer id;
    private Integer idProducto;
    private String condicion;
    private String fechaIngreso;
    private String nombre;
    private tipoProducto tipoProducto;

    public Producto() {
    }

    public Producto( int idProducto, String fechaIngreso, String condicion, String nombre) {
        this.idProducto = idProducto;
        this.fechaIngreso = fechaIngreso;
        this.condicion = condicion;
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(String fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getCondicion() {
        return condicion;
    }

    public void setCondicion(String condicion){this.condicion = condicion; }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public tipoProducto getTipoProducto() {
        return tipoProducto;
    }

    public void setTipoProducto(tipoProducto tipoProducto) {
        this.tipoProducto = tipoProducto;
    }

}
