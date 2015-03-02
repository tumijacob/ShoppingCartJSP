package pgdswd.shoppingcart;
import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import javax.naming.InitialContext;

/**
 *
 * @author Tumi
 */
public class ConnectionPool {
    private static ConnectionPool pool = null;
    private static DataSource dataSource = null;
    
    private ConnectionPool(){
        try{
            InitialContext ic = new InitialContext();
            dataSource = (DataSource) ic.lookup("java:/comp/env/jdbc/remedies");
        }catch(Exception e){
            e.printStackTrace();
        }
    } 
    
    public static ConnectionPool getInstance(){
        if(pool == null){
           pool = new ConnectionPool();
        }
        return pool;
    }
    
    public static Connection getConnection(){
        try{
            return dataSource.getConnection();
        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }
    
    public static void releaseConnection(Connection c){
        try {
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
