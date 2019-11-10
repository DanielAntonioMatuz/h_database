package javafx.scene.transition.mapeo;

import java.io.Serializable;
import java.util.List;

public class tipoProducto implements Serializable {

    private Integer id;
    private String clasificacion;
    private List<Producto> productos;


    public tipoProducto() {
    }

    public tipoProducto(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto>productos) {
        this.productos = productos;
    }


}
