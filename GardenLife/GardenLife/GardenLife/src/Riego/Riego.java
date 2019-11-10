package Riego;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Riego {

	private final SimpleStringProperty nombreP;
	private final SimpleIntegerProperty codigo;

	private final SimpleStringProperty fechaR;
	private final SimpleStringProperty DiaR;

	
	public Riego(int Rcodigo ,String Pnombre,String Rfecha,String RDia) {
		this.nombreP = new SimpleStringProperty(Pnombre);
		this.codigo = new SimpleIntegerProperty(Rcodigo);
		
		this.fechaR = new SimpleStringProperty(Rfecha);
		this.DiaR= new SimpleStringProperty(RDia);
	}


	public String getDiaR() {
		return DiaR.get();
	}


	public String getNombreP() {
		return nombreP.get();
	}


	public int getCodigo() {
		return codigo.get();
	}


	public String getFechaR() {
		return fechaR.get();
	}

}
		
		
