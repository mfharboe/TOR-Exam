/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Connection;

/**
 *
 * @author Morten
 */
public class DB_Connection {
    
    private static final String SERVER_NAME = "vestergade.no-ip.org";
    private static final String DATABASE_INSTANCE = "SQLEXPRESS";
    private static final int PORTNO = 64877;
    private static final String DATABASE_NAME = "TOR DB";
    private static final String USERNAME = "Tor"; 
    private static final String PASSWORD = "qwerty";
    
    private Connection m_connection;
    
    private static DB_Connection m_instance = null;

    /**
     * Establishes a connection to the database
     */
    private  DB_Connection()  {
        SQLServerDataSource ds = new SQLServerDataSource();

        ds.setServerName(SERVER_NAME);
        ds.setDatabaseName(DATABASE_NAME);
        ds.setInstanceName(DATABASE_INSTANCE);
        ds.setPortNumber(PORTNO);
        ds.setUser(USERNAME);
        ds.setPassword(PASSWORD);
        try { 
            m_connection = ds.getConnection();
            System.out.println("online");
        } catch (SQLServerException ex) {
        }
    }
    
    /**
     * return the connection
     * @return m_connection
     */
    public Connection getConnection(){
        return m_connection;
    }

    /**
     * Check if connection is 'cached' (singleton)
     * @return m_instance 
     */
    public static DB_Connection getInstance(){
        if (m_instance == null) 
            m_instance = new DB_Connection();

        return m_instance;
    }
    
}
