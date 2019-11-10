package javafx.scene.transition;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/javafx/scene/transition/scene7/scene7.fxml"));
        stage.getIcons().add(new Image("img/icono.png"));
        Scene scene = new Scene(root,1920,1000);
        stage.setScene(scene);
        stage.setTitle("GardenLife | I D S");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
