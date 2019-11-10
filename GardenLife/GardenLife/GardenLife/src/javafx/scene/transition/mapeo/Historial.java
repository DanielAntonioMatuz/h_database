package javafx.scene.transition.mapeo;

import java.io.Serializable;

public class Historial implements Serializable {

    private Integer idProducto;
    private String fotografia;
    private Integer id;


    public Historial() {
    }

    public Historial(String fotografia) {
        this.fotografia = fotografia;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public String getFotografia() {
        return fotografia;
    }

    public void setFotografia(String fotografia) {
        this.fotografia = fotografia;
    }

    public String getId() {
        return fotografia;
    }

    public void setId(Integer id) {
        this.id = id;
    }


}
