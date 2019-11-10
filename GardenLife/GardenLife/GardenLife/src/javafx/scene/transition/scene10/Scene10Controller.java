package javafx.scene.transition.scene10;

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
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Scene10Controller extends Connector implements Initializable {

    @FXML
    private Button menu;

    @FXML
    private ComboBox<String> producto;

    @FXML
    private CheckBox lunes;

    @FXML
    private CheckBox martes;

    @FXML
    private CheckBox miercoles;

    @FXML
    private CheckBox jueves;

    @FXML
    private CheckBox viernes;

    @FXML
    private CheckBox sabado;

    @FXML
    private CheckBox domingo;

    @FXML
    private Button confirmar;

    @FXML
    private TextArea resultados;
	@FXML
    private AnchorPane container;

	 ObservableList<String> productos = FXCollections.observableArrayList();


	
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	try {
        	String sSQL = "SELECT Distinct nombre FROM producto";
            String mg = manejador();
            ResultSet rs=null;         
            if(mg.equals("MySQL")) {
            	rs = ResultSet(sSQL);      	
    		}
            else {
            	if(mg.equals("SQL-Server")) {
            		rs = ResultSetSQL(sSQL);           		
            	}           	
            }   
            while (rs.next()) {
            productos.add(rs.getString("nombre"));
         }               
            producto.setItems(productos);
 
        } catch (Exception e) { 
          
            System.out.println(e.getMessage());
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
    
    @FXML
    private void confirmar(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
    	String[] dias = new String[7];
    	int contador = 0;
    	String mg = manejador();
    	String query;
    	
    	String product = producto.getSelectionModel().getSelectedItem();
    	int idProduct = 0;
    	String sSQL = "SELECT idProducto FROM producto WHERE nombre = '"+product+"'";
		ResultSet rs = null;
        if(mg.equals("MySQL")) {
        	 rs = ResultSet(sSQL);
        }
        else {
        	if(mg.equals("SQL-Server")) {
           	 rs = ResultSetSQL(sSQL);
           }
        }
        
        
        while (rs.next()) {
        	idProduct = rs.getInt("idProducto");
     }
    	
    	if(lunes.isSelected()) {
    		dias[0] = "lunes";
    		System.out.println("1");
    	}
    	
    	if(martes.isSelected()) {
    		dias[1] = "martes";
    	}
    	
    	if(miercoles.isSelected()) {
    		dias[2] = "miercoles";
    		System.out.println("1");
    	}
    	
    	if(jueves.isSelected()) {
    		dias[3] = "jueves";
    	}
    	
    	if(viernes.isSelected()) {
    		dias[4] = "viernes";
    	}
    	
    	if(sabado.isSelected()) {
    		dias[5] = "sabado";
    	}
    	
    	if(domingo.isSelected()) {
    		dias[6] = "domigo";
    	}
		
    	String datos = "";
    	int numero = (int) (Math.random() * 7000000) + 1;
    	for(int i=0;i<dias.length;i++) {
    		if(dias[i]!=null) {
    			query = "INSERT INTO calendario (idProducto, idCalendario, diaRiego) VALUES ('"+idProduct+"','"+ numero +"','"+dias[i] +"')";
    			datos += dias[i]+"\n";
    			
    			try {    				
    		        if(mg.equals("MySQL")) {
    		        	executeQuery(query);
    		        }
    		        	 
    		        else {
    		        	if(mg.equals("SQL-Server")) {
    		           	 executeQuerySQL(query);
    		        }
    		        
    		    }
    		      }
    			
    			catch(Exception e) {
    			Alert alert = new Alert(AlertType.ERROR);
            	alert.setTitle("Error Dialog");
            	alert.setHeaderText("Compruebe si sus datos son correctos");
            	alert.setContentText("Ooops, intente de nuevo");
            	alert.showAndWait();
    		}
    		}
    		
    	}
    	resultados.setText(datos + "\n" + "Datos registrados correctamente");
    }
    

}
