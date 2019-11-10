package conector;

import java.io.IOException;
import java.sql.*;

public class Connector {

    Connection conn;
    String manejadorC = "";

    public String manejador() throws IOException {
        String url = "MySQL";
       /* File file = new File(url);
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
                    //System.out.println(var[aux-j]);
                    //System.out.println("Esta usando MySQL como manejador");
                    manejadorC = var[aux-j];
                }
                else {
                    if(var[aux-j].equals("SQL-Server")) {
                        //System.out.println(var[aux-j]);
                        //System.out.println("Esta usando SQL-Server como manejador");
                        manejadorC = var[aux-j];
                    }
                }
            }
        }*/
        manejadorC = "MySQL";
        return manejadorC;
    }


    public static Connection getConnection() {

        Connection conn;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/garden?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","danieludo");
            return conn;
        }catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    //C:/Users/danie/.m2/repository/org/slf4j/slf4j-api/1.6.1/slf4j-api-1.6.1-sources.jar
  /*  public static void getConnectionQ() {

        String connectionUrl = "jdbc:sqlserver://localhost:1433;" +
                "databaseName=vivero;"+"sa"+"123";

        // Declare the JDBC objects.
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            // Establish the connection.
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;" +
                    "databaseName=vivero;","sa","123");
            // System.out.println("CONECTO A SQL SERVER");
            // Create and execute an SQL statement that returns some data.
            //String SQL = "SELECT TOP 10 * FROM Person.Contact";
            stmt = con.createStatement();
            //rs = stmt.executeQuery(SQL);


        }

        // Handle any errors that may have occurred.
        catch (Exception e) {
            //System.out.println("NO CONECTADO A SQL SERVER");
            e.printStackTrace();
        }
    } */

    //MySQL
    public java.sql.ResultSet ResultSet(String query) throws SQLException {
        String urlq = "jdbc:mysql://localhost:3306/garden?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        conn = DriverManager.getConnection(urlq,"root","danieludo");
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

    public boolean buscar(int val) {
        try {

            ResultSet rs=null;
            String mg = manejador();
            String query = "Select * from productos "
                    + "where codigo = " + val;
            if(mg.equals("MySQL")) {
                rs = ResultSet(query);
                Connection conn = conector.Connector.getConnection();
                Statement st = conn.createStatement();
                rs = st.executeQuery(query);
            }
            else {
                if(mg.equals("SQL-Server")) {
                    rs =ResultSetSQL(query);
                    //System.out.println("Usando SQL");
                }

            }

            //rs = st.executeQuery(query);
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
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

    public void executeQuerySQL(String query) throws ClassNotFoundException, SQLException {
        try {
            //String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=vivero;"+"sa"+"123";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;" +
                    "databaseName=vivero;","sa","123");
            Statement stmt = null;
            try {
                stmt = conn.createStatement();
                stmt.executeQuery(query);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }catch(Exception e) {
            System.out.println("");
        }
    }
}
