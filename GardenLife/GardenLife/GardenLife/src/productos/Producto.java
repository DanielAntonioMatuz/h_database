package productos;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Producto {

	private final SimpleStringProperty nombre;
	private final SimpleIntegerProperty codigo;
	private final SimpleStringProperty condicion;
	private final SimpleStringProperty fechaIngreso;
	private final SimpleStringProperty tipo;
	private final SimpleIntegerProperty idTipo;
	
	public Producto(String Pnombre,String PfechaIngreso, String Pcondicion, int Pcodigo, String Ptipo, int PidTipo) {
		this.nombre = new SimpleStringProperty(Pnombre);
		this.codigo = new SimpleIntegerProperty(Pcodigo);
		this.condicion = new SimpleStringProperty(Pcondicion);
		this.fechaIngreso = new SimpleStringProperty(PfechaIngreso);
		this.tipo = new SimpleStringProperty(Ptipo);
		this.idTipo = new SimpleIntegerProperty(PidTipo);

	}

	public int getCodigo() {
		return codigo.get();
	}
	
	public void setCodigo(int Pcodigo) {
		codigo.set(Pcodigo);
	}
	
	public int getIdTipo() {
		return idTipo.get();
	}
	
	public void setIdTipo(int PidTipo) {
		idTipo.set(PidTipo);
	}
	
	public String getFechaIngreso() {
		return fechaIngreso.get();
	}
	
	public void setTipo(String Ptipo) {
		tipo.set(Ptipo);
	}
	
	public String getTipo() {
		return tipo.get();
	}
	
	public void setCondicion(String Pcondicion) {
		condicion.set(Pcondicion);
	}
	
	public String getCondicion() {
		return condicion.get();
	}
	
	public void setFechaIngreso(String PfechaIngreso) {
		fechaIngreso.set(PfechaIngreso);
	}
	
	public String getNombre() {
		return nombre.get();
	}
	
	public void setNombre(String Pnombre) {
		nombre.set(Pnombre);
	}
		
		
	
}
