package javafx.scene.transition.scene6;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class Scene6Controller implements Initializable {

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
    controller controlador = new controller();
    @Override
    public void initialize(URL url, ResourceBundle rb)   {
    	String urlServer = "upworks.website";
    	int puerto = 2083;

    	try {
    		@SuppressWarnings("resource")
			Socket s = new Socket(urlServer,puerto);

    		if(s.isConnected()) {
    			mensaje.setText("CONECTADO AL SERVIDOR, ACCESO A LA RED");
    			
    		}
    		
    	}catch(Exception e) {
    		mensaje.setText("DESCONECTADO DE LA RED | NO HAY ACCESO AL SERVIDOR");
           controlador.inicio();
    	}
    	
    }

    @FXML
    public void iniciarSesion(ActionEvent event) throws SQLException, IOException {
    	
    	
    	
    	String SSQL="SELECT * FROM usuario WHERE nombre='"+usuario.getText()+"' AND contrasenia = '"+contrasenia.getText()+"'";

        Connection conect = null;


            conect = conector.Connector.getConnection();
            Statement st = conect.createStatement();
            ResultSet rs = st.executeQuery(SSQL);

            if(rs.next()){

            	//System.out.println("USUARIO: " + rs.getString(1) + " CONTRASEÑA: " + rs.getString(2));
            	
            	 Parent root = FXMLLoader.load(getClass().getResource("/javafx/scene/transition/scene1/scene1.fxml"));
                 Scene scene = iniciarSesion.getScene();
                 root.translateXProperty().set(scene.getWidth());

                 StackPane parentContainer = (StackPane) iniciarSesion.getScene().getRoot();

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
    
    
    public void executeQuery(String query) {
		Connection conn = conector.Connector.getConnection();
		Statement st;
		try {
		st = conn.createStatement();
		st.executeUpdate(query);
		} catch (Exception e) {
		e.printStackTrace();
		}
		}

}
