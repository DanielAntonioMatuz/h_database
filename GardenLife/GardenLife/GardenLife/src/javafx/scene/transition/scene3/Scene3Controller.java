package javafx.scene.transition.scene3;

import Riego.Riego;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class Scene3Controller extends Connector  implements Initializable {


	@FXML
	private Button regresar,buscarBoton,buscarBoton1,vender,borrarSeleccion,galery,generarId,Filtro,noFiltrar,generarId1,insertFoto,calendario;
	
	@FXML
	private TextField IdRiego,URLImage,IdFoto,DiaR;
	
	@FXML
	private TableView<Riego> venta;
    @FXML 
	private TableColumn  <?,?> codigoRiego;
	@FXML 
	private TableColumn  <?,?> productoNombre;
	@FXML 
	private TableColumn <?,?>  RiegoFecha;
	@FXML 
	private TableColumn <?,?> RiegoDia; 
	@FXML
	private ImageView imagenP,imagen;
	@FXML
    private AnchorPane container;
	Connection conn;
	@FXML
	private ComboBox BuscarRiego;
	@FXML
	private ComboBox ProductNombre;
	@FXML
	private ComboBox FechaFiltro;
	@FXML
	private ComboBox IdProducto;
	@FXML
	private ComboBox<String> ComboDia;
	@FXML
	private DatePicker FechaRiego;
	@FXML
	private DatePicker FechaFoto;
	int valorFinal = 0;
	
	 ObservableList<Riego> data = FXCollections.observableArrayList();
	 ObservableList<String> product = FXCollections.observableArrayList();
	 ObservableList<String> riegoIds = FXCollections.observableArrayList();
	 ObservableList<String> riegoFch = FXCollections.observableArrayList();
	 ObservableList<String> IdProductF = FXCollections.observableArrayList();
	 ObservableList<String> DiaRegar = FXCollections.observableArrayList();
	 @FXML
		public void generarCodigo() {
			int numero = (int) (Math.random() * 7000000) + 1;
			IdRiego.setText(Integer.toString(numero));
		}
	 
	 public void RiegoReg() throws IOException, SQLException, ClassNotFoundException {
		 String sSQL = "SELECT idRiego FROM HistorialRiego";
			ResultSet rs = null;
			String mg = manejador();
	        if(mg.equals("MySQL")) {
	        	 rs = ResultSet(sSQL);
	        }
	        else {
	        	if(mg.equals("SQL-Server")) {
	           	 rs = ResultSetSQL(sSQL);
	           }
	        }
	        
	        
	        while (rs.next()) {
	        	riegoIds.add(rs.getString("idRiego"));
	     }
	        BuscarRiego.setItems(riegoIds);
		}

	 public void nombreProductos() throws IOException, SQLException, ClassNotFoundException {
		 String sSQL = "SELECT nombre FROM producto";
			ResultSet rs = null;
			String mg = manejador();
	        if(mg.equals("MySQL")) {
	        	 rs = ResultSet(sSQL);
	        }
	        else {
	        	if(mg.equals("SQL-Server")) {
	           	 rs = ResultSetSQL(sSQL);
	           }
	        }
	        
	        
	        while (rs.next()) {
	        	product.add(rs.getString("nombre"));
	     }
	        ProductNombre.setItems(product);
		}
	 public void fechafiltro() throws IOException, SQLException, ClassNotFoundException {
		 String sSQL = "SELECT Distinct FechaRiego from HistorialRiego";
			ResultSet rs = null;
			String mg = manejador();
	        if(mg.equals("MySQL")) {
	        	 rs = ResultSet(sSQL);
	        }
	        else {
	        	if(mg.equals("SQL-Server")) {
	           	 rs = ResultSetSQL(sSQL);
	           }
	        }
	        
	        
	        while (rs.next()) {
	        	riegoFch.add(rs.getString("FechaRiego"));
		     }
		        FechaFiltro.setItems(riegoFch);
		}
	 @FXML
	public void insertarRiego() throws IOException {
		 	String v=FechaRiego.getValue().toString();
			int val;
	    	String mg = manejador();
	    	String query;
			try {
				System.out.print(v);
				val=Integer.valueOf(IdRiego.getText());
				query="INSERT INTO `HistorialRiego`(`idRiego`, `NombreProducto`, `FechaRiego`,`Dia`) VALUES ("+val+", '"+ProductNombre.getSelectionModel().getSelectedItem()+"', '"+FechaRiego.getValue().toString()+"', '"+ComboDia.getSelectionModel().getSelectedItem()+"')";
				
		        if(mg.equals("MySQL")) {
		        	executeQuery(query);
		        }
		        	 
		        else {
		        	if(mg.equals("SQL-Server")) {
		        		String query2 = "INSERT INTO HistorialRiego (idRiego, NombreProducto, FechaRiego,Dia) VALUES ("+val+", '"+ProductNombre.getSelectionModel().getSelectedItem()+"', '"+FechaRiego.getValue().toString()+"', '"+ComboDia.getSelectionModel().getSelectedItem()+"')";
		           	 executeQuerySQL(query2);
		        }
		        Alert alert = new Alert(AlertType.INFORMATION);
	        	alert.setTitle("Confirmacion");
	        	alert.setHeaderText("Se ha añadido el riego correctamente");
	        	alert.setContentText("Gracias");
	        	alert.showAndWait();
		    }
		      }
			
			catch(Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
        	alert.setTitle("Error Dialog");
        	alert.setHeaderText("Compruebe si sus datos son correctos");
        	alert.setContentText("Ooops, intente de nuevo");
        	alert.showAndWait();
		}
	initialize(null, null);
	}
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	try { 
    		venta.getItems().removeAll();
    		data.clear();
    		product.clear();
    		riegoIds.clear();
    		riegoFch.clear();
    		IdRiego.clear();
    		fechafiltro();
    		RiegoReg();
    		nombreProductos();
    		IdsProducts();
    		codigoRiego.setCellValueFactory(new PropertyValueFactory<>("codigo"));
    		productoNombre.setCellValueFactory(new PropertyValueFactory<>("nombreP"));
    		RiegoFecha.setCellValueFactory(new PropertyValueFactory<>("fechaR"));
    		RiegoDia.setCellValueFactory(new PropertyValueFactory<>("DiaR"));
            String sSQL = "SELECT * FROM HistorialRiego";
            String ssSQL = "SELECT nombre FROM producto";
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
                            //txtResultado.appendText(rs.getString(1) + " " + rs.getString(2) + "\n");
                            data.add(new Riego(rs.getInt("idRiego"),rs.getString("NombreProducto"),rs.getString("FechaRiego"),rs.getString("Dia")));
            				//System.out.println(rs.getString(2));
         }   
          
            
        } catch (Exception e) { 
          
            System.out.println(e.getMessage());
        } 
    	
         venta.setItems(data);
         
         
    }
    
    @FXML
    private void verificar(ActionEvent event) {
    	int val = 0;
    	try { 
    		
            String sSQL = "SELECT DISTINCT idProducto FROM producto WHERE nombre = '"+ProductNombre.getSelectionModel().getSelectedItem()+"'";
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
            	val = rs.getInt("idProducto");
         }   
          
            
        } catch (Exception e) { 
          
            System.out.println(e.getMessage());
        }
    	
    	
    	try { 
    		DiaRegar.clear();
            String sSQL = "SELECT DISTINCT diaRiego FROM calendario WHERE idProducto = '"+val+"'";
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
            	DiaRegar.add(rs.getString("diaRiego"));
         }

            buscarBoton.setVisible(true);
            ComboDia.setItems(DiaRegar);
          
            
        } catch (Exception e) { 
          
            System.out.println(e.getMessage());
        } 

    }
    
    
    @FXML
    private void buscar() throws IOException {
    	int val;
    	String mg = manejador();
		try {
			val = Integer.parseInt((String) BuscarRiego.getSelectionModel().getSelectedItem());
			String query = "DELETE FROM HistorialRiego WHERE idRiego="+val+"";
			
	        if(mg.equals("MySQL")) {
	        	executeQuery(query);
	        }
	        	 
	        else {
	        	if(mg.equals("SQL-Server")) {
	        		String query2 = "DELETE FROM HistorialRiego WHERE idRiego="+val+"";
	           	 executeQuerySQL(query2);
	           }
	        }
				
			
			
			
			}catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Dialog");
			alert.setHeaderText(e.getMessage()+"");
			alert.setContentText("Ooops, intente de nuevo");

			alert.showAndWait();
			return;
			}
		initialize(null, null);
    }

    
 
    @FXML
	private void regresarMenu() throws IOException {
		 Parent root = FXMLLoader.load(getClass().getResource("/javafx/scene/transition/scene1/scene1.fxml"));
	        Scene scene = regresar.getScene();
	        root.translateXProperty().set(scene.getWidth());

	        AnchorPane parentContainer = (AnchorPane) regresar.getScene().getRoot();

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
   	private void galeria() throws IOException {
   		 Parent root = FXMLLoader.load(getClass().getResource("/javafx/scene/transition/scene8/scene8.fxml"));
   	        Scene scene = galery.getScene();
   	        root.translateXProperty().set(scene.getWidth());

   	        AnchorPane parentContainer = (AnchorPane) galery.getScene().getRoot();

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
   	private void calendarioP() throws IOException {
   		 Parent root = FXMLLoader.load(getClass().getResource("/javafx/scene/transition/scene10/scene10.fxml"));
   	        Scene scene = calendario.getScene();
   	        root.translateXProperty().set(scene.getWidth());

   	        AnchorPane parentContainer = (AnchorPane) calendario.getScene().getRoot();

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
        
	private void imprimirImagen(String codigo) throws FileNotFoundException, SQLException, IOException {
		Connection conn = conector.Connector.getConnection();
		Statement statement = conn.createStatement();
		String query = "Select imagen from productos "
				+ "where codigo = " + codigo;
		ResultSet rs = statement.executeQuery(query);
		
		File file = new File("imagenProducto");
		FileOutputStream fos = new FileOutputStream(file);
		
		if (rs.next()) {
			try {
			InputStream input = (InputStream) rs.getBinaryStream("imagen");
			
			byte[] buffer = new byte[1024];
			while (input.read(buffer) > 0) {
				
				fos.write(buffer);
			}
			
			input.close();
			}catch(Exception e) {
				//System.out.println("IMAGEN NULL");
			}
		}
		fos.close();
		
		Image image = new Image(file.toURI().toString(), 100, 150, true, true);
		imagenP.setImage(image);
	}
    @FXML
    public void Filtrar() {
    	data.clear();
    	venta.getItems().removeAll();
    	try {
    		
    	 String sSQL ="SELECT * FROM HistorialRiego WHERE FechaRiego= '"+FechaFiltro.getSelectionModel().getSelectedItem()+"'";
			ResultSet rs = null;
			String mg = manejador();
	        if(mg.equals("MySQL")) {
	        	 rs = ResultSet(sSQL);
	        }
	        else {
	        	if(mg.equals("SQL-Server")) {
	           	 rs = ResultSetSQL(sSQL);
	           }
	        }
	        
	        
	        while (rs.next()) {
	        	 data.add(new Riego(rs.getInt("idRiego"),rs.getString("NombreProducto"),rs.getString("FechaRiego"),rs.getString("Dia")));
	     }
	        
		}catch(Exception e) {
			 System.out.println(e.getMessage());
		}
    venta.setItems(data);

    }
    @FXML
    public void NoFiltrar() {
    	initialize(null, null);
    }
    @FXML
	public void generarCodigoFoto() {
		int numero = (int) (Math.random() * 7000000) + 1;
		IdFoto.setText(Integer.toString(numero));
	}

    public void IdsProducts() throws IOException, SQLException, ClassNotFoundException {
    	IdProductF.clear();
		 String sSQL = "SELECT Distinct nombre from producto";
			ResultSet rs = null;
			String mg = manejador();
	        if(mg.equals("MySQL")) {
	        	 rs = ResultSet(sSQL);
	        }
	        else {
	        	if(mg.equals("SQL-Server")) {
	           	 rs = ResultSetSQL(sSQL);
	           }
	        }
	        
	        
	        while (rs.next()) {
	        	IdProductF.add(rs.getString("nombre"));
		     }
		        IdProducto.setItems(IdProductF);
		}
	public void insertarFoto() throws IOException {
	 
		int val;
    	String mg = manejador();
    	String query;
		try {
			val=Integer.valueOf(IdFoto.getText());
			
			ResultSet rs = null;
			query = "SELECT idProducto FROM producto WHERE nombre = '"+IdProducto.getSelectionModel().getSelectedItem()+"'";
	        if(mg.equals("MySQL")) {
	        	
	        	
	        	rs = ResultSet(query);
	        	int id=0;
	        	while (rs.next()) {
	        		
		        	id = rs.getInt("idProducto");
		        	System.out.println("I D  PRODUCTO: "+id);
			     }
				query="INSERT INTO `historialFotografia`(`idFoto`, `fotografia`, `idProducto`, `fecha`) VALUES ("+val+", '"+URLImage.getText()+"', '"+id+"', '"+FechaFoto.getValue().toString()+"')";

	        	executeQuery(query);
	        }
	        	 
	        else {
	        	if(mg.equals("SQL-Server")) {
	        		rs = ResultSetSQL(query);
	        		int id=0;
		        	while (rs.next()) {
		        		
			        	id = rs.getInt("codigo");
			        	System.out.println("I D  PRODUCTO: "+id);
				     }
	        		String query2 = "INSERT INTO historialFotografia (idFoto, fotografia, idProducto, fecha) VALUES ("+val+", '"+URLImage.getText()+"', '"+id+"', '"+FechaFoto.getValue().toString()+"')";
	           	 executeQuerySQL(query2);
	           }
	        }
	        Alert alert = new Alert(AlertType.INFORMATION);
        	alert.setTitle("Confirmacion");
        	alert.setHeaderText("Se ha añadido el contenido correctamente");
        	alert.setContentText("Gracias");
        	alert.showAndWait();
	    }
		
		catch(Exception e) {
		Alert alert = new Alert(AlertType.ERROR);
    	alert.setTitle("Error Dialog");
    	alert.setHeaderText("Compruebe si sus datos son correctos");
    	alert.setContentText("Ooops, intente de nuevo");
    	alert.showAndWait();
	}
    IdFoto.clear();
    URLImage.clear();
	}
	@SuppressWarnings("deprecation")
	public void imagen() throws IOException {
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Images Files", "*.png", "*.jpg","*.jpeg"));
		File file;
		file = fileChooser.showOpenDialog(new Stage());
		System.out.println(file.toURI().toString());
		URLImage.setText(file.toURL().getPath());
		
	}
}
