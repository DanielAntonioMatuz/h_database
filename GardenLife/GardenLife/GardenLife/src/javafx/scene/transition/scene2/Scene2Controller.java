package javafx.scene.transition.scene2;

import conector.Connector;
import galeria.ImageGallery;
import galeria.PhotoGallery;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
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
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.scene.transition.controladorDao.controller;
import javafx.scene.transition.mapeo.tipoProducto;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.util.Duration;
import productos.Producto;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;


public class Scene2Controller extends Connector implements Initializable {

    @FXML
    private Button registrar,regresar,actualizar,editar,generarCodigo,imagen,editarImagen,editarTipo,generarId,crearTipo,filtrar,imagenes,calendario;
    @FXML
    private AnchorPane container;
    @FXML
    private TextField fechaIngreso,condicion,nombreProducto,codigo, idEliminar,idIngresar,Pcantidad,idTipo,nombreTipo,nuevoTipo,idNuevo;
    Connection conn;
    @FXML
    ImageView imgProd;
    @FXML
	private TableView<Producto> table;
    @FXML 
	private TableColumn  <?,?> codigoP;
	@FXML 
	private TableColumn  <?,?> productoP;
	@FXML 
	private TableColumn <?,?> fechaIngresoP;
	@FXML 
	private TableColumn <?,?> condicionP; 
	@FXML 
	private TableColumn <?,?> tipoP;
	@FXML 
	private TableColumn <?,?> idTipoP; 
    @FXML
	private ComboBox<String> combTipo;
    @FXML
	private ComboBox<Integer> comIdTipoQ;
    @FXML
    private Text confirmarEdicion,confirmarCrear;
    @FXML
	private ComboBox<String> comIdTipo;
    @FXML
    private DatePicker fecha;
    ObservableList<String> tipo = FXCollections.observableArrayList();
    ObservableList<Integer> tipoIdQ = FXCollections.observableArrayList();
    
    @FXML
	private void insertButton() throws SQLException, IOException, ClassNotFoundException {

		controller.registro(Integer.parseInt(codigo.getText()), fecha.getValue().toString(),condicion.getText(), nombreProducto.getText(), combTipo.getSelectionModel().getSelectedItem().toString());

		ObservableList <Producto>	list = getPersonList();
		table.getItems().setAll(list);
		
		final ObservableList <Producto> data = table.getSelectionModel().getSelectedItems();
		table.refresh();
		idEliminar.clear();
		idIngresar.clear();
		Pcantidad.clear();
		nombreProducto.clear();
		codigo.clear();
		fechaIngreso.clear();
		condicion.clear();
		idTipo.clear();
	}
	
	public void tipoProducto() throws SQLException, IOException, ClassNotFoundException {
		List empList1 = controller.getTipos();
		for (Iterator iterator =
			 empList1.iterator(); iterator.hasNext();){
			 tipoProducto dao = (tipoProducto) iterator.next();
			 System.out.println(dao.getClasificacion() + "AA");
			 tipo.add(dao.getClasificacion());
		}

        combTipo.setItems(tipo);
        comIdTipo.setItems(tipo);
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
	
	@FXML
	public void crearTipo(ActionEvent event) throws SQLException, IOException, ClassNotFoundException {
    	controller.registroTipo(nuevoTipo.getText());
		confirmarCrear.setText("El producto se ha creado correctamente");
		nuevoTipo.clear();
		idNuevo.clear();
		tipo.clear();
		tipoProducto();
	}
	
	@FXML
	public void actualizarTipo() throws SQLException, IOException, ClassNotFoundException {
		controller.editarTipoProducto(comIdTipo.getSelectionModel().getSelectedItem(), nombreTipo.getText());
		confirmarEdicion.setText("El tipo producto se ha actualizado");
		nombreTipo.clear();
		tipo.clear();
		tipoProducto();
	}
	
	public void confirmarTipo() throws SQLException, IOException, ClassNotFoundException {
		String mg = manejador();
		ResultSet rs=null;
		if(mg.equals("MySQL")) {
			String query3 = "SELECT id FROM public.tipoProducto WHERE clasificacion = '"+combTipo.getSelectionModel().getSelectedItem()+"'";	
	        rs = ResultSet(query3);
		}
		else {
			if(mg.equals("SQL-Server")) {
				String query3 = "SELECT id FROM tipoProducto WHERE clasificacion = '"+combTipo.getSelectionModel().getSelectedItem()+"'";
				rs = ResultSetSQL(query3);
			}
		}
		
          
        while (rs.next()) {
        	idTipo.setText(Integer.toString(rs.getInt("id")));  
     }
	}
	
	@FXML
	private void imagen() throws FileNotFoundException {
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Images Files", "*.png", "*.jpg","*.jpeg"));
		File file;
		file = fileChooser.showOpenDialog(new Stage());
		if (file != null) {		
			String query = " UPDATE public.productos SET imagen = ?"
					+ " WHERE codigo = '" + codigo.getText() + "'";
			try {
				Connection conn = conector.Connector.getConnection();
				PreparedStatement pst = conn.prepareStatement(query);
				
				FileInputStream fis = new FileInputStream(file);
				pst.setBinaryStream(1, fis);
				
				pst.executeUpdate();
				
				fis.close();
				
				imagen.setVisible(false);
				editar.setVisible(false);
				registrar.setVisible(true);
				generarCodigo.setVisible(true);
				fechaIngreso.setEditable(true);
				condicion.setEditable(true);
				nombreProducto.setEditable(true);
				fechaIngreso.clear();
				condicion.clear();
				codigo.clear();
				nombreProducto.clear();
				
			}
			catch (Exception e) {
				Alert alert = new Alert(AlertType.ERROR);
	        	alert.setTitle("Error Dialog");
	        	alert.setHeaderText("Error en subir la imagen");
	        	alert.setContentText("Ooops, there was an error!");

	        	alert.showAndWait();
	        	
	        	return;
			}
			Alert alert = new Alert(AlertType.INFORMATION);
        	alert.setTitle("Message Dialog");
        	alert.setHeaderText("Imagen cargada!");
        	alert.setContentText("Felicidades");

        	alert.showAndWait();
        	
        	return;
		}
		else{
			Alert alert = new Alert(AlertType.ERROR);
        	alert.setTitle("Error Dialog");
        	alert.setHeaderText("Error en subir la imagen");
        	alert.setContentText("No seleccionaste ninguna imagen");

        	alert.showAndWait();
		}
	}
	
	@FXML
	public void editImage() throws FileNotFoundException
	{
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Images Files", "*.png", "*.jpg","*.jpeg"));
		File file;
		file = fileChooser.showOpenDialog(new Stage());
		if (file != null) {		
			String query = " UPDATE public.productos SET imagen = ?"
					+ " WHERE codigo = '" + codigo.getText() + "'";
			try {
				Connection conn = conector.Connector.getConnection();
				PreparedStatement pst = conn.prepareStatement(query);
				
				FileInputStream fis = new FileInputStream(file);
				pst.setBinaryStream(1, fis);
				
				pst.executeUpdate();
				
				fis.close();
	
			}
			catch (Exception e) {
				Alert alert = new Alert(AlertType.ERROR);
	        	alert.setTitle("Error Dialog");
	        	alert.setHeaderText("Error en subir la imagen");
	        	alert.setContentText("Ooops, there was an error!");

	        	alert.showAndWait();
	        	
	        	return;
			}
			Alert alert = new Alert(AlertType.INFORMATION);
        	alert.setTitle("Message Dialog");
        	alert.setHeaderText("Imagen cargada!");
        	alert.setContentText("Felicidades");

        	alert.showAndWait();
        	
        	return;
		}
		else{
			Alert alert = new Alert(AlertType.ERROR);
        	alert.setTitle("Error Dialog");
        	alert.setHeaderText("Error en subir la imagen");
        	alert.setContentText("No seleccionaste ninguna imagen");

        	alert.showAndWait();
		}
	}
	
	@FXML
	private void eliminar(ActionEvent event) throws IOException {

			controller.eliminarProducto(Integer.parseInt(codigo.getText()));
			ObservableList <Producto>	list = getPersonList();
			table.getItems().setAll(list);
			final ObservableList <Producto> productoList = table.getSelectionModel().getSelectedItems();
			
			editar.setVisible(false);
	    	registrar.setVisible(true);
	    	generarCodigo.setVisible(true);
			fechaIngreso.clear();
			condicion.clear();
			nombreProducto.clear();
			codigo.clear();
			idEliminar.clear();
			idIngresar.clear();
			Pcantidad.clear();
	}

	@FXML 
	private void updateButton() throws SQLException, IOException, ClassNotFoundException {
		controller.editarProducto(nombreProducto.getText(),fechaIngreso.getText(), condicion.getText(),Integer.parseInt(codigo.getText()));
		editar.setVisible(false);
    	registrar.setVisible(true);
    	generarCodigo.setVisible(true);
		fechaIngreso.clear();
		condicion.clear();
		nombreProducto.clear();
		codigo.clear();
		idEliminar.clear();
		idIngresar.clear();
		Pcantidad.clear();
	}  
	
	@FXML
	public void generarCodigo() {
		int numero = (int) (Math.random() * 7000000) + 1;
		codigo.setText(Integer.toString(numero));
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
	
	
	 ObservableList<Producto> data = FXCollections.observableArrayList();

	    
	    @Override
	    public void initialize(URL url, ResourceBundle rb) {
	    	try {

	    		fecha.setEditable(false);
	    		tipoProducto();
	        	codigoP.setCellValueFactory(new PropertyValueFactory<>("codigo"));
	        	productoP.setCellValueFactory(new PropertyValueFactory<>("nombre"));
	        	fechaIngresoP.setCellValueFactory(new PropertyValueFactory<>("fechaIngreso"));
	        	condicionP.setCellValueFactory(new PropertyValueFactory<>("condicion"));
	        	tipoP.setCellValueFactory(new PropertyValueFactory<>("tipo"));
	        	idTipoP.setCellValueFactory(new PropertyValueFactory<>("idTipo"));
				List empList1 = controller.getProductos();
				//
				for (Iterator iterator =
					 empList1.iterator(); iterator.hasNext();){
					Producto producto;
					javafx.scene.transition.mapeo.Producto dao = (javafx.scene.transition.mapeo.Producto) iterator.next();
					producto = new Producto(dao.getNombre(), dao.getFechaIngreso(), dao.getCondicion(), dao.getIdProducto(), dao.getTipoProducto().getClasificacion() ,dao.getTipoProducto().getId());
					data.add(producto);
					System.out.println(producto.getNombre());
				}
				table.setItems(data);

	            editar.setVisible(false);
	            imagen.setVisible(false);
	           
	        } catch (Exception e) { 
	          
	            System.out.println(e.getMessage());
	        } 
	         

			table.getSelectionModel().selectedItemProperty().addListener(new ChangeListener()
	        {
	            @Override
	            public void changed(ObservableValue observableValue, Object oldValue, Object newValue)
	            {
	                if(table.getSelectionModel().getSelectedItem() != null) 
	                {    
	                   Producto producto = table.getSelectionModel().getSelectedItem();

	                    nombreProducto.setText(producto.getNombre());
	                    fechaIngreso.setText(producto.getFechaIngreso());
	                    condicion.setText(producto.getCondicion());
	                    codigo.setText(Integer.toString(producto.getCodigo()));
	                    idTipo.setText(Integer.toString(producto.getCodigo()));
	                    idEliminar.setText(Integer.toString(producto.getCodigo()));
	                    idIngresar.setText(Integer.toString(producto.getCodigo()));
	                    
	                    registrar.setVisible(false);
	                    editar.setVisible(true);
	                    //editarImagen.setVisible(true);
	                    generarCodigo.setVisible(false);;
	                }
	            }
	        });
			
	    }    

	    @FXML
	    private void editar(ActionEvent event) throws IOException, ClassNotFoundException, SQLException {


			controller.editarProducto(nombreProducto.getText(),fechaIngreso.getText(), condicion.getText(),Integer.parseInt(codigo.getText()));

			ObservableList <Producto>	list = getPersonList();
			table.getItems().setAll(list);
			
			final ObservableList <Producto> data = table.getSelectionModel().getSelectedItems();
			table.refresh();
	    	
	    	
	    	
	    	editar.setVisible(false);
	    	registrar.setVisible(true);
	    	generarCodigo.setVisible(true);
	    	editarImagen.setVisible(false);
	    	fechaIngreso.clear();
			condicion.clear();
			nombreProducto.clear();
			codigo.clear();
			idEliminar.clear();
			idIngresar.clear();
			Pcantidad.clear();
			idTipo.clear();
			
	    }
	    
	    public ObservableList<Producto> getPersonList() throws IOException {
			ObservableList<Producto> productoList = FXCollections.observableArrayList();
			ResultSet rs=null;
			List empList1 = controller.getProductos();
			Producto producto;

			for (Iterator iterator =
				 empList1.iterator(); iterator.hasNext();){
				javafx.scene.transition.mapeo.Producto dao = (javafx.scene.transition.mapeo.Producto) iterator.next();
				producto = new Producto(dao.getNombre(), dao.getFechaIngreso(), dao.getCondicion(), dao.getIdProducto(), dao.getTipoProducto().getClasificacion() ,dao.getTipoProducto().getId());
				productoList.add(producto);
			}
			table.getItems().setAll(productoList);
			return productoList;


		}
	    
	    @FXML
	    public void start( ActionEvent e) throws Exception {
	    	Stage stage = new Stage();
			ResultSet rs = null;
			String[] urls = new String[10];
			int contador = 0;
			String mg = manejador();
			String query = "select fotografia from public.historialFotografia"; 
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
				 System.out.println(rs.getString(1));
				 urls[contador++] = rs.getString(1);
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
			stage.setTitle("Visor de imágenes en JavaFX");
			stage.show();
			

		}
}
