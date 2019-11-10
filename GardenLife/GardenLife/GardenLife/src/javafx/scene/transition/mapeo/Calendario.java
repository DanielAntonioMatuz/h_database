package javafx.scene.transition.mapeo;

import java.io.Serializable;

public class Calendario implements Serializable {

    private Integer id;
    private String idProducto;
    private String fechaIngreso;


    public Calendario() {
    }

    public Calendario(String fechaIngreso, String idProducto) {
        this.fechaIngreso = fechaIngreso;
        this.idProducto = idProducto;
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

    public String getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(String idProducto){this.idProducto = idProducto; }


}
