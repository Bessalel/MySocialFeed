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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import org.joda.time.DateTime;
import org.mysocialfeed.services.interfaces.MySQLService;
import org.mysocialfeed.services.interfaces.UserService;
import org.mysocialfeed.services.repository.UserDataService;
import org.mysocialfeed.services.repository.UserPostsService;

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
    
    // User Posts
    private final UserPostsService userPostsService;
    
    @Inject
    public MainUserService(MySQLService mySQLService, UserDataService userDataService, UserPostsService userPostsService){
        this.mySQLService = mySQLService;
        this.userDataService = userDataService;
        this.userPostsService = userPostsService;
    }
    
    private Connection accessSQLService(){
        return this.conn = this.mySQLService.connectToDatabase();
    }
    
    private boolean loadUserData(ResultSet rs){
        try {
            userDataService.loadUserData(rs.getInt(1), rs.getString(2), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getInt(10));
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    private boolean loadUserPosts(){
        ResultSet rs;
        int tempUserID = this.userDataService.getUserID();
        List<Integer> postsID = new ArrayList<Integer>();
        List<Integer> accountID = new ArrayList<Integer>();
        List<String> accountType = new ArrayList<String>();
        List<String> content = new ArrayList<String>();
        List<DateTime> timeStamp = new ArrayList<DateTime>();
        
        try(PreparedStatement getUserPosts = 
               this.conn.prepareStatement(this.mySQLService.getLIST_ALL_POSTS())) {
                    getUserPosts.setInt(1, this.userDataService.getUserID());

                    rs = getUserPosts.executeQuery();
                    
                    if (rs.next()) {
                        postsID.add(rs.getInt(1));
                        accountID.add(rs.getInt(3));
                        accountType.add(rs.getString(4));
                        content.add(rs.getString(5));
                        timeStamp.add(new DateTime(rs.getTimestamp(6)));

                        int it = 0;
                        while (rs.next()) {
                            postsID.add(rs.getInt(++it));
                            it++;
                            accountID.add(rs.getInt(++it));
                            accountType.add(rs.getString(++it));
                            content.add(rs.getString(++it));
                            timeStamp.add(new DateTime(rs.getTimestamp(++it)));
                            it = 0;
                        }
                        this.userPostsService.loadPosts(tempUserID, postsID, accountID, accountType, content, timeStamp);
                    }
                    rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
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
                               this.connectionStatus = loadUserPosts();
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