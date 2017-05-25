package hospitalmanagementsystem;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author oshan
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;  
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConnect {
    private String host = "jdbc:mysql://localhost:3306/HospitalManagement?autoReconnect=true&useSSL=false";
    private String username = "root";
    private String password = "zaq123ZAQ!@#";
    Connection con;
    Statement stmt;
    ResultSet rs;

    public void setHost(String host) {
        this.host = host;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
    
    

    
    public boolean connectdb() {
        
        
        try {
            
            con = DriverManager.getConnection( host, username, password );
            System.out.println("db connected succesfully");
            stmt = con.createStatement( );
            String SQL = "SELECT DATABASE();";
            rs= stmt.executeQuery( SQL );
            rs.next();
            String database =rs.getString("DATABASE()");
            System.out.println("currently connected to database \""+database+"\"." );
            return true;
        }
        catch ( SQLException err ) {    
            System.out.println( err.getMessage( ) );
            return false;
        }
        
    }
    public void executeSQL(String sql) { 
        try {
            rs= stmt.executeQuery( sql );
        } catch (SQLException ex) {
            System.out.println( ex.getMessage( ) );
            
        }
        
    }
    
    
    
    
}
