package galeria;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import conector.Connector;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class SimpleGallerySnippet extends  Application {
	Connection conn;

	//INSERT INTO historialFotografia (idFoto, fotografia, idProducto, fecha) VALUES ('14753369', 'D:/FotosBD/girasol.jpg', '1247858', '2019-07-24');

	public void start(final Stage stage) throws Exception {
		try {
		ResultSet rs = null;
		String[] urls = new String[10];
		int contador = 0;
		String mg = manejador();
		String query = "select fotografia from historialFotografia"; 
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
			 //contador++;
			 System.out.println(rs.getString(1));
			 urls[contador++] = rs.getString(1);
			 //System.out.println(""+rs.getString(1)+"" + contador++);
			 System.out.println(contador);
			 //img+= rs.getString(2);
	        }

		//final String[] urls = new String[] {"D:/FotosBD/girasol.jpg"};

		final HBox hBox = new HBox();
		hBox.setStyle("-fx-background-color: grey");
		hBox.setAlignment(Pos.CENTER);

		final List<PhotoGallery> photos = new ArrayList<PhotoGallery>();
		for (String url : urls) {
			photos.add(new PhotoGallery(url));
		}

		final Button button = new Button("Abrir galer√≠a");
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
		stage.setTitle("Visor de im·genes en JavaFX");
		stage.show();
		}catch(Exception e) {
			
		}

	}

	public static void main(final String[] args) {
		launch(args);
	}
	
	public String manejador() throws IOException {
		String manejadorC = null;
		String url = "D:\\Documentos\\eclipse-workspace\\GardenLife\\src\\settings.txt";
		File file = new File(url);
		FileReader fileR = null;
		BufferedReader file2 = null;
		
		fileR = new FileReader(file);
	    file2 = new BufferedReader(fileR);
	    
	    String lines = "";
	    while( ( lines = file2.readLine()) != null) {
	    	
	    	String case1 = lines;
			String foo = case1;
		       String[] bar = foo.split("(?=\\s)");
		       bar = foo.split(" ");
		       String[]var = new String[100];
		       int aux =0;
		       int contador = 0;
		        for (int i = 0; i < bar.length;i++ ){
		        var[aux++] = bar[i];
		        } 
		        aux --;
		        for(int j = aux; j >=0;j--){
		        	if(var[aux-j].equals("MySQL")) {
		        		System.out.println(var[aux-j]);
		        		System.out.println("Esta usando MySQL como manejador");
		        		manejadorC = var[aux-j];
		        	}
		        	else {
		        		if(var[aux-j].equals("SQL-Server")) {
			        		System.out.println(var[aux-j]);
			        		System.out.println("Esta usando SQL-Server como manejador");
			        		manejadorC = var[aux-j];
			        	}
		        	}
		        }
	    }
	    return manejadorC;
	}
	
	//MySQL
		public java.sql.ResultSet ResultSet(String query) throws SQLException {
			String urlq = "jdbc:mysql://mx70.hostgator.mx/upworksw_vivero?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC"; 
	        conn = DriverManager.getConnection(urlq,"upworksw_admin","danieludo"); 
			 Statement st = conn.createStatement(); 
		     ResultSet rs = st.executeQuery(query);
		     
		     return rs;
		}
		
		
		//SQL SERVER
		public java.sql.ResultSet ResultSetSQL(String query) throws SQLException, ClassNotFoundException {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;" +
					"databaseName=vivero;","sa","123");
			 Statement st = conn.createStatement(); 
		        
		     ResultSet rs = st.executeQuery(query);
		     
		     return rs;
		}

}
