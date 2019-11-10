package javafx.scene.transition.scene4;

import conector.Connector;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class Scene4Controller extends Connector implements Initializable {

	@FXML
	private TableView<Reporte> reportes;
    @FXML 
	private TableColumn  <?,?> nombre;
	@FXML 
	private TableColumn  <?,?> cantidad;
	@FXML 
	private TableColumn <?,?> precio;
	@FXML 
	private TableColumn <?,?> subtotal; 
	@FXML 
	private TableColumn <?,?> fecha;
	@FXML 
	private TableColumn <?,?> iva; 
	@FXML 
	private TableColumn <?,?> costoTotal; 
	@FXML
	private ComboBox<String> filtro;
	@FXML
	private Button buscar,menu,sinFiltro;
	@FXML
    private AnchorPane container;
	@FXML
    private LineChart<String, Integer> chartBarras;
	@FXML
    private AreaChart<String, Integer> line;
	@FXML
    private BarChart<String, Integer> barras,barras2;
    @FXML
    private CategoryAxis xAxis;
    @FXML
    private CategoryAxis xAxis2;

	Connection conn;

	 ObservableList<Reporte> data = FXCollections.observableArrayList();
	 ObservableList<fecha> fech = FXCollections.observableArrayList();
	 ObservableList<String> dataF = FXCollections.observableArrayList();

	
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	dataF.add("Cantidad	de productos por tipo de producto");
    	dataF.add("Cantidad de riegos por día");
    	dataF.add("Cantidad de productos por condición actual");
    	dataF.add("Cantidad de fotografías por tipo de producto");
    	filtro.setItems(dataF);
    }
    
    
    @SuppressWarnings("unchecked")
	@FXML
    private void btnGenera(ActionEvent event) throws Exception {
    	 XYChart.Series<String, Integer> dataSeries1 = new XYChart.Series<>();
    	 XYChart.Series<String, Integer> dataSeries2 = new XYChart.Series<>();
    	 XYChart.Series<String, Integer> dataSeries3 = new XYChart.Series<>();
    	 XYChart.Series<String, Integer> dataSeries4 = new XYChart.Series<>();
         ObservableList<String> leyenda =  FXCollections.observableArrayList();
         ObservableList<String> leyenda2 =  FXCollections.observableArrayList();
         ObservableList<String> leyenda3 =  FXCollections.observableArrayList();
         ObservableList<String> leyenda4 =  FXCollections.observableArrayList();
         
         leyenda.clear();
         leyenda2.clear();
         leyenda3.clear();
         leyenda4.clear();
         
    	String mg = manejador();
    	ResultSet rs = null;
    	System.out.println(filtro.getSelectionModel().getSelectedItem());
    	if(filtro.getSelectionModel().getSelectedItem().equals("Cantidad	de productos por tipo de producto")) {
    		dataSeries1.getData().removeAll();
			barras.setVisible(false);
			barras2.setVisible(false);
			line.setVisible(false);
    		String sql1 = "select tipoProducto, COUNT(*) from producto GROUP BY tipoProducto";
    		if(mg.equals("MySQL")) {
    			rs = ResultSet(sql1);
    		}
    		else {
    			if(mg.equals("SQL-Server")) {
        			rs = ResultSetSQL(sql1);
        		}
    		}
    		while (rs.next()){
	            System.out.println(rs.getString(1));
	            leyenda.add(""+rs.getString(1)); // se agrega como leyenda el campo que que quiera, debe coincidir con la serie
	            dataSeries1.getData().add(new XYChart.Data<>(""+rs.getString(1), rs.getInt(2))); // posicion 1 es la leyenda
	        
	            
	        }
				xAxis.setCategories(leyenda);  // se asigna la leyenda a la grafica
				dataSeries1.setName(filtro.getSelectionModel().getSelectedItem().toString());
			    chartBarras.getData().addAll(dataSeries1);
    	}
    		if(filtro.getSelectionModel().getSelectedItem().equals("Cantidad de productos por condición actual")) {
    	         dataSeries2.getData().removeAll();

    			chartBarras.setVisible(false);
				barras.setVisible(false);
				barras2.setVisible(false);
				line.setVisible(true);
        		String sql2 = "select condicion, COUNT(*) from producto GROUP BY condicion";
        		if(mg.equals("MySQL")) {
        			rs = ResultSet(sql2);
        		}
        		else {
        			if(mg.equals("SQL-Server")) {
            			rs = ResultSetSQL(sql2);
            		}
        		}
        		while (rs.next()){
		            System.out.println(rs.getString(1));
		            leyenda2.add(""+rs.getString(1)); // se agrega como leyenda el campo que que quiera, debe coincidir con la serie
		            dataSeries2.getData().add(new XYChart.Data<>(""+rs.getString(1), rs.getInt(2))); // posicion 1 es la leyenda
		        
		            
		        }
					xAxis.setCategories(leyenda2);  // se asigna la leyenda a la grafica
					dataSeries2.setName(filtro.getSelectionModel().getSelectedItem().toString());
			        line.getData().addAll(dataSeries2);
        	}
    		
    			if(filtro.getSelectionModel().getSelectedItem().equals("Cantidad de riegos por día")) {
 
    		         dataSeries3.getData().removeAll();
    				chartBarras.setVisible(false);
    				barras.setVisible(true);
    				barras2.setVisible(false);
    				line.setVisible(false);
            		String sql2 = "SELECT left(Dia,10) DIA, count(*) CANTIDAD from HistorialRiego GROUP by left(Dia,10)";
            		if(mg.equals("MySQL")) {
            			rs = ResultSet(sql2);
            		}
            		else {
            			if(mg.equals("SQL-Server")) {
                			rs = ResultSetSQL(sql2);
                		}
            		}
            		while (rs.next()){
    		            System.out.println(rs.getString(1));
    		            leyenda3.add(""+rs.getString(1)); // se agrega como leyenda el campo que que quiera, debe coincidir con la serie
    		            dataSeries3.getData().add(new XYChart.Data<>(""+rs.getString(1), rs.getInt(2))); // posicion 1 es la leyenda
    		        
    		            
    		        }
    					xAxis.setCategories(leyenda3);  // se asigna la leyenda a la grafica
    					dataSeries3.setName(filtro.getSelectionModel().getSelectedItem().toString());
    			        barras.getData().addAll(dataSeries3);
            	}
    				if(filtro.getSelectionModel().getSelectedItem().equals("Cantidad de fotografías por tipo de producto")) {
    			         dataSeries4.getData().removeAll();
        				chartBarras.setVisible(false);
        				barras2.setVisible(true);
        				barras.setVisible(false);
        				line.setVisible(false);
                		String sql2 = "select producto.tipoProducto, count(*) from historialFotografia, producto where historialFotografia.idProducto=producto.idProducto GROUP by producto.tipoProducto";
                		if(mg.equals("MySQL")) {
                			rs = ResultSet(sql2);
                		}
                		else {
                			if(mg.equals("SQL-Server")) {
                    			rs = ResultSetSQL(sql2);
                    		}
                		}
                		while (rs.next()){
        		            System.out.println(rs.getString(1));
        		            leyenda4.add(""+rs.getString(1)); // se agrega como leyenda el campo que que quiera, debe coincidir con la serie
        		            dataSeries4.getData().add(new XYChart.Data<>(""+rs.getString(1), rs.getInt(2))); // posicion 1 es la leyenda
        		        
        		            
        		        }
        					xAxis.setCategories(leyenda4);  // se asigna la leyenda a la grafica
        					dataSeries4.setName(filtro.getSelectionModel().getSelectedItem().toString());
        			        barras2.getData().addAll(dataSeries4);
                	}
		
    }
 
		
    
    @FXML
   	private void regresarMenu() throws IOException {
   		 Parent root = FXMLLoader.load(getClass().getResource("/javafx/scene/transition/scene1/scene1.fxml"));
   	        Scene scene = menu.getScene();
   	        root.translateXProperty().set(scene.getWidth());

   	     AnchorPane parentContainer = (AnchorPane) menu.getScene().getRoot();

   	        parentContainer.getChildren().add(root);

   	        Timeline timeline = new Timeline();
   	        KeyValue kv = new KeyValue(root.translateXProperty(), 0, Interpolator.EASE_IN);
   	        KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
   	        timeline.getKeyFrames().add(kf);
   	        timeline.setOnFinished(t -> {
   	            parentContainer.getChildren().remove(container);
   	        });
   	        timeline.play();
   	}
    
    
    
    public class Reporte {}
    
public class fecha {}

}
