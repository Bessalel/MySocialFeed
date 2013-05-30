/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mysocialfeed.services.implementations;

import com.google.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.mysocialfeed.services.interfaces.MySQLService;
import org.mysocialfeed.services.interfaces.UserService;
import org.mysocialfeed.services.repository.UserDataService;

/**
 *
 * @author Vincent
 */
public class MainUserService implements UserService {
    
    // SQL properties
    private boolean connectionStatus;
    private final MySQLService mySQLService;
    private Connection conn;
    
    // User Data
    private final UserDataService userDataService;
    
    
    @Inject
    public MainUserService(MySQLService mySQLService, UserDataService userDataService){
        this.mySQLService = mySQLService;
        this.userDataService = userDataService;
    }
    
    private Connection accessSQLService(){
        return this.conn = this.mySQLService.connectToDatabase();
    }
    
    private boolean loadUserData(ResultSet rs){
        try {
            userDataService.loadUserData(rs.getInt(1), rs.getString(2), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getInt(10));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
    
    
    @Override
    public boolean isAuthenticated() {
        return connectionStatus;
    }
    
    // User operations
    @Override
    public boolean authenticate(String userName, String passWord) {
      try {
           if (accessSQLService().isClosed() == true) {
               accessSQLService(); // putting a while here would freeze the program if always false... so only one try for the momment
           } else { 
               try(PreparedStatement getUserData = 
                       this.conn.prepareStatement(
                       this.mySQLService.getLIST_USER())) {
                            getUserData.setString(1, userName);
                       ResultSet rs = getUserData.executeQuery();
                       if (!rs.next()) {
                           // username not found !
                           this.conn.close();
                           return this.connectionStatus = false;
                       } else {
                           if (passWord.compareTo(rs.getString(3)) == 0) {
                               // Success -> passwords match !
                               this.connectionStatus = loadUserData(rs);
                               this.conn.close();
                               return this.connectionStatus;
                           } else {
                               // Failure -> passwords did not match !
                               this.conn.close();
                               return this.connectionStatus = false;
                           }
                       }
               } catch(SQLException e) {
                   e.printStackTrace();
                   return this.connectionStatus = false;
               }
           }
      } catch (SQLException e) {
          e.printStackTrace();
      }
      return true;
    }

    @Override
    public void userSignOff() {
        this.userDataService.unloadAllUserData();
    }
}