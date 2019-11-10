package javafx.scene.transition.scene9;

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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Scene9Controller implements Initializable {

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
    private MediaView video;

	 ObservableList<Reporte> data = FXCollections.observableArrayList();
	 ObservableList<fecha> fech = FXCollections.observableArrayList();
	 ObservableList<String> dataF = FXCollections.observableArrayList();

	
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	String urlVideo= "file:/D:/Descargas/GardenLife/GardenLife/acerca.mp4";
    	
        Media media = new Media(urlVideo);
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        video.setFitHeight(1920.0);
        video.setFitWidth(1920.0);
        video.setMediaPlayer(mediaPlayer);
        mediaPlayer.play();
    }
    
    public void reiniciarTabla(ActionEvent event) {}
    
    @FXML
    public void buscar(ActionEvent event) throws SQLException {

    }
    
    public void executeQuery(String query) {}
    
    @FXML
	public void exportarPDF(ActionEvent event) {}
		
    
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
