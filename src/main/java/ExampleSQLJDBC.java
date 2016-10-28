import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class ExampleSQLJDBC {

    public static void main(String[] args) throws ClassNotFoundException {

        String resourceName = "config.properties";
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Properties props = new Properties();

        try (InputStream resourceStream = loader.getResourceAsStream(resourceName)) {
            props.load(resourceStream);
        } catch (Exception e) {
            e.printStackTrace();
        }


        String hostName = props.getProperty("HOST_NAME");
        String dbName = props.getProperty("DB_NAME");
        String user = props.getProperty("USER");
        String password = props.getProperty("PASSWORD");
        String isToEncrypt = props.getProperty("ENCRYPT");

        String url = String.format("jdbc:sqlserver://%s.database.windows.net:1433;" +
                "database=%s;" +
                "user=%s;" +
                "password=%s;" +
                "encrypt=%s;" +
                "hostNameInCertificate=*.database.windows.net;" +
                "loginTimeout=30;", hostName, dbName, user, password, isToEncrypt);

        System.out.println(url);

        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        try (Connection conn = DriverManager.getConnection(url, user, password)) {

            System.out.println("== connected ==");

            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery("SELECT 1+1 AS SUM");

            while (rs.next()) {
                System.out.println("== result == " + rs.getInt("SUM"));
            }
            rs.close();
            stat.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("== disconnected ==");
    }
}