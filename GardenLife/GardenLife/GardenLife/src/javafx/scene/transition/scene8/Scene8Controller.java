package javafx.scene.transition.scene8;

import conector.Connector;
import galeria.ImageGallery;
import galeria.PhotoGallery;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Scene8Controller extends Connector implements Initializable {

	@FXML
	private TableView<Imagenes> imagenes;
    @FXML 
	private TableColumn  <?,?> nombreQ;
	@FXML 
	private TableColumn  <?,?> urlQ;
	
	@FXML
	private ComboBox<String> filtro;
	@FXML
	private Button buscar,menu,sinFiltro,adelante,atras,eliminar;
	@FXML
    private AnchorPane container;
	@FXML
	private ImageView img1;
	@FXML
	private TextField idProductoT,urlDatos,Teliminar;
	@FXML
	private ComboBox<String> productosComb;
	@FXML
	private ListView<String> list,list2;
	 ObservableList<Imagenes> data = FXCollections.observableArrayList();
	 ObservableList<String> dataT = FXCollections.observableArrayList();
	 ObservableList<String> dataT2 = FXCollections.observableArrayList();
	 ObservableList<String> productos = FXCollections.observableArrayList();

	
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	try {
    	nombreQ.setCellValueFactory(new PropertyValueFactory<>("Pnombre"));
    	urlQ.setCellValueFactory(new PropertyValueFactory<>("Purl"));

    	String sSQL = "SELECT * FROM historialFotografia";
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
                        data.add(new Imagenes(Integer.toString(rs.getInt("idProducto")),rs.getString("fotografia")));
        				System.out.println(rs.getString("idFoto"));
        				System.out.println(rs.getString("fotografia"));
     }   
        
    
      
        
    } catch (Exception e) { 
      
        System.out.println(e.getMessage());
    } 
    	
    	try {

        	String sSQL = "SELECT Distinct idProducto FROM historialFotografia";
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
                            productos.add(Integer.toString(rs.getInt("idProducto")));
         }   
            
            productosComb.setItems(productos);
            
        
          
            
        } catch (Exception e) { 
          
            System.out.println(e.getMessage());
        }
	
     imagenes.setItems(data);
     imagenes.getSelectionModel().selectedItemProperty().addListener(new ChangeListener()
     {
         @Override
         public void changed(ObservableValue observableValue, Object oldValue, Object newValue)
         {
             if(imagenes.getSelectionModel().getSelectedItem() != null) 
             {    
                Imagenes imagen = imagenes.getSelectionModel().getSelectedItem();
                
                 urlDatos.setText(imagen.getPurl());
                 Teliminar.setText(imagen.getPnombre());
                 File file = new File(urlDatos.getText());
                 Image image = new Image(file.toURI().toString());
                 img1.setImage(image);

             }
         }
     });
     
 	String  sSQL = "SELECT Distinct * FROM producto";
 	String mg="";
	try {
		mg = manejador();
	} catch (IOException e) {
		e.printStackTrace();
	}
    ResultSet rs=null;
    
    if(mg.equals("MySQL")) {
    	try {
			rs = ResultSet(sSQL);
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
	}
    else {
    	if(mg.equals("SQL-Server")) {
    		try {
				rs = ResultSetSQL(sSQL);
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
    		
    	}
    	
    }
 	
      
     try {
		while (rs.next()) {
		             dataT.add(rs.getString("nombre"));
		             dataT2.add(rs.getString("idProducto"));
  }
		list.setItems(dataT);
		list2.setItems(dataT2);
	} catch (SQLException e) {
		e.printStackTrace();
	}

    }
    
    @FXML
    public void buscarDatos(ActionEvent event) {
    	try {
    	data.clear();
    	String sSQL = "SELECT * FROM historialFotografia WHERE idProducto ="+productosComb.getSelectionModel().getSelectedItem()+"";
    	
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
                        data.add(new Imagenes(Integer.toString(rs.getInt("idFoto")),rs.getString("fotografia")));
        				System.out.println(rs.getString("idFoto"));
        				System.out.println(rs.getString("fotografia"));
     }   
      
        
    } catch (Exception e) { 
      
        System.out.println(e.getMessage());
    } 
	
     imagenes.setItems(data);
    }
    
    @FXML
    public void buscar(ActionEvent event) throws SQLException {}
    
    public void executeQuery(String query) {}
    int contador = 0;
    @FXML
    public void galeria() throws IOException, SQLException, ClassNotFoundException {
    	ResultSet rs = null;
		String[] urls = new String[10];
		
		String mg = manejador();
    	String query = "SELECT * FROM historialFotografia WHERE ="+productosComb.getSelectionModel().getSelectedItem()+"";
		String img = null;
		if(mg.equals("MySQL")) {
			       
		        rs = ResultSet(query);
		}

		
		while(rs.next())
	        {
			 System.out.println(rs.getString(1));
			 urls[contador++] = rs.getString(3);
			 System.out.println(contador);
			  
	        }
		
		File file = new File(urls[1]);
        Image image = new Image(file.toURI().toString());
        img1.setImage(image);

    }
    
    
    
    @FXML
	private void eliminar(ActionEvent event) throws IOException {
		String mg = "MySQL";
		int val;
		
		try {
			
			val = Integer.parseInt(Teliminar.getText());
				if(mg.equals("MySQL")) {
					String query = "DELETE FROM historialFotografia WHERE idFoto="+val+"";
					executeQuery(query);
				}

		}catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
        	alert.setTitle("Error Dialog");
        	alert.setHeaderText(e.getMessage()+"");
        	alert.setContentText("Ooops, intente de nuevo");

        	alert.showAndWait();
        	return;
		}

			ObservableList <Imagenes>	list = getPersonList();
			imagenes.getItems().setAll(list);
			final ObservableList <Imagenes> productoList = imagenes.getSelectionModel().getSelectedItems();

	}
    
    public ObservableList<Imagenes> getPersonList() throws IOException {
		ObservableList<Imagenes> productoList = FXCollections.observableArrayList();
		ResultSet rs=null;
		String query = "SELECT idFoto,fotografia FROM historialFotografia";
		String mg = "MySQL";
		
		
		
		try {
			
			if(mg.equals("MySQL")) {
				rs = ResultSet(query);
			}

			
			Imagenes imagen;
			while(rs.next()) {
				imagen = new Imagenes(rs.getString("idFoto"),rs.getString("fotografia"));
				productoList.add(imagen);
				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		imagenes.getItems().setAll(productoList);
		return productoList;
		
		
	}
    
    
    
    @FXML
    public void visor() throws IOException, SQLException, ClassNotFoundException {

    	Stage stage = new Stage();
		ResultSet rs = null;
		String[] urls = new String[100];
		int contador = 0;
		String mg = manejador();
		String query = "SELECT fotografia FROM historialFotografia WHERE idProducto ="+productosComb.getSelectionModel().getSelectedItem()+""; 
		String img = null;
		if(mg.equals("MySQL")) {
			       
		        rs = ResultSet(query);
		}
		else {
			if(mg.equals("SQL-Server")) {
				
				rs = ResultSetSQL(query);
			}
		}
		
		while(rs.next())
	        {
			 System.out.println(rs.getString(3));
			 urls[contador++] = rs.getString(3);
			 System.out.println(contador);
	        }


		final HBox hBox = new HBox();
		hBox.setStyle("-fx-background-color: grey");
		hBox.setAlignment(Pos.CENTER);

		final List<PhotoGallery> photos = new ArrayList<PhotoGallery>();
		for (String url : urls) {
			photos.add(new PhotoGallery(url));
		}

		final Button button = new Button("Abrir galería");
		button.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {

			@Override
			public void handle(final ActionEvent arg0) { 
				final ImageGallery gallery = new ImageGallery(stage, photos, 0.5);
				gallery.show();
			}

		});

		hBox.getChildren().addAll(button);
		
		final StackPane root = new StackPane();
		root.getChildren().add(hBox);

		final Scene scene = new Scene(root);
		stage.setScene(scene);

		stage.setWidth(1920);
		stage.setHeight(1000);
		stage.setTitle("Visor de imágenes | GardenLife view");
		stage.show();
		

	
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
    
    
    
    
    public class Imagenes {

    	private final SimpleStringProperty Pnombre;
    	private final SimpleStringProperty Purl;

    	
    	public Imagenes(String Pnombre,String Purl) {
    		this.Pnombre = new SimpleStringProperty(Pnombre);
    		this.Purl = new SimpleStringProperty(Purl);
    		
    	}


    	public String getPnombre() {
    		return Pnombre.get();
    	}


    	public String getPurl() {
    		return Purl.get();
    	}

    }
    
    public class tipo {

    	private final SimpleStringProperty PnombreT;
    	private final SimpleStringProperty idT;

    	
    	public tipo(String PnombreT,String idT) {
    		this.PnombreT = new SimpleStringProperty(PnombreT);
    		this.idT = new SimpleStringProperty(idT);
    		
    	}


    	public String getPnombre() {
    		return PnombreT.get();
    	}


    	public String getIdT() {
    		return idT.get();
    	}

    }

}


