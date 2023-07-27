
package dataaccesslayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * A singleton data source to establish a connection to the server.
 * @author Morgan Bakelmun
 */
public class DataSource {

    private Connection connection = null;
    //http://localhost:4848/common/index.jsf the link to the payara server overview
    private String url = "jdbc:mysql://localhost:3306/peertutor?useSSL=false&allowPublicKeyRetrieval=true";
    private String username = "CST8288"; //this only works because I made this user. Consider using the root account if you don't care about safety
    private String password = "CST8288";
    
    /**
     * Constructor for this data source
     */
    public DataSource() {
    }
    
    /**
     * Return the connection that is already created
     * @return the connection
     */
    public Connection createConnection() {
        try {
            if (connection != null) {
                System.out.println("Cannot create new connection, one exists already");
            } else {
                connection = DriverManager.getConnection(url, username, password);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return connection;
    }   
}
