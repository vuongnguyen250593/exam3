package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectToDB {
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Connection connection = null;
        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/exam3?useSSL=false","root","123456789");
        return connection;
    }
}
