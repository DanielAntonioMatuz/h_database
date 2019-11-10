package javafx.scene.transition.scene7;

import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Text;
import javafx.scene.transition.scene2.Scene2Controller;
import javafx.util.Duration;
import productos.Producto;


public class Scene7Controller implements Initializable {

    @FXML
    private MediaView video;

    @FXML
    private AnchorPane container;
    @FXML
    private Button iniciarSesion;


    @Override
    public void initialize(URL url, ResourceBundle rb)   {
    String urlVideo= "file:/D:/Descargas/GardenLife/GardenLife/Inicio.mp4";
    Media media = new Media(urlVideo);
    MediaPlayer mediaPlayer = new MediaPlayer(media);
    video.setFitHeight(1920.0);
    video.setFitWidth(1920.0);
    video.setMediaPlayer(mediaPlayer);
    mediaPlayer.play();
    
    	
    }

 
    
    
    public void inicioSesion(ActionEvent event) throws IOException {
    	 Parent root = FXMLLoader.load(getClass().getResource("/javafx/scene/transition/scene5/scene5.fxml"));
         Scene scene = iniciarSesion.getScene();
         root.translateXProperty().set(scene.getWidth());

         AnchorPane parentContainer = (AnchorPane) iniciarSesion.getScene().getRoot();

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
		

}
