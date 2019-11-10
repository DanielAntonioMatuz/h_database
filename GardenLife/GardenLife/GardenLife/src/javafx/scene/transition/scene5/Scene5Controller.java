package javafx.scene.transition.scene5;

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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.scene.transition.controladorDao.controller;
import javafx.util.Duration;

import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class Scene5Controller extends Connector implements Initializable {

    @FXML
    private ImageView imagenV;
    @FXML
    private Button producto,venta,reportes,iniciarSesion;
    @FXML
    private AnchorPane anchorRoot;
    @FXML
    private StackPane parentContainer;
    @FXML
    private AnchorPane container;
    @FXML
    private TextField usuario;
    @FXML
    private PasswordField contrasenia;
    @FXML
    private Text mensaje;
    @FXML
	private ComboBox<String> manejador;
    ObservableList<String> tipo = FXCollections.observableArrayList();
    controller controlador = new controller();
    @Override
    public void initialize(URL url, ResourceBundle rb)   {
    	tipo.add("MySQL");
    	//tipo.add("SQL-Server");
    	manejador.setItems(tipo);
		//getConnectionQ();
    	String urlServer = "https://www.upchiapas.edu.mx/";
    	int puerto = 2083;
        controlador.inicio();
    	try {
    		@SuppressWarnings("resource")
			Socket s = new Socket(urlServer,puerto);
    		
    		if(s.isConnected()) {
    			mensaje.setText("CONECTADO AL SERVIDOR, ACCESO A LA RED");
    			
    		}
    		
    	}catch(Exception e) {
    		mensaje.setText("DESCONECTADO DE LA RED | NO HAY ACCESO AL SERVIDOR");

           
    	}
    	
    }

    @FXML
    public void iniciarSesion(ActionEvent event) throws SQLException, IOException, ClassNotFoundException {
        boolean acceso = controller.getUsuario( usuario.getText(),  contrasenia.getText());

           if(acceso == true){

            	
            	 Parent root = FXMLLoader.load(getClass().getResource("/javafx/scene/transition/scene1/scene1.fxml"));
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
            else {
            	mensaje.setText("Usuario o contraseña incorrecta");
            	usuario.clear();;
            	contrasenia.clear();
            }


    }
    
    

}
