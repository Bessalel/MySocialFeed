/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mysocialfeed.services.repository;

import com.google.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.joda.time.DateTime;
import org.mysocialfeed.services.interfaces.MySQLService;

/**
 *
 * @author Vincent
 */
public class ManageUserPostsService implements UserPostsService{

    private Connection conn;
    
    private int userID;
    private List<Integer> postsID = new ArrayList<Integer>();
    private List<Integer> accountID = new ArrayList<Integer>();
    private List<String> accountType = new ArrayList<String>();
    private List<String> content = new ArrayList<String>();
    private List<DateTime> timeStamp = new ArrayList<DateTime>();

    private final MySQLService mySQLService;
    
    @Inject
    public ManageUserPostsService(MySQLService mySQLService) {
        this.mySQLService = mySQLService;
    }
    
    private Connection accessSQLService(){
        return this.conn = this.mySQLService.connectToDatabase();
    }
    
    @Override
    public void loadPosts(int userID, List<Integer>postsID, List<Integer> accountID, List<String> accountType, 
    List<String> content, List<DateTime> DateTime) {
        this.userID = userID;
        this.postsID = postsID;
        this.accountID = accountID;
        this.accountType = accountType;
        this.content = content;
        this.timeStamp = DateTime;
    }

    @Override
    public int getUserID() {
        return this.userID;
    }

    @Override
    public int getPostID(int index) {
        return this.postsID.get(index);
    }
    
    @Override
    public int getAccountID(int index) {
        return this.accountID.get(index);
    }

    @Override
    public String getAccountType(int index) {
        return this.accountType.get(index);
    }

    @Override
    public String getContent(int index) {
        return this.content.get(index);
    }

    @Override
    public DateTime getTimeStamp(int index) {
        return this.timeStamp.get(index);
    }
    
    @Override
    public boolean hasPost() {
        return (this.userID != 0 && !(this.accountID.isEmpty()) && !(this.accountType.isEmpty()) 
                && !(this.content.isEmpty()) && !(this.postsID.isEmpty()) && !(this.timeStamp.isEmpty()));
    }
    
    @Override
    public void addPost(int accountID, String accountType, String post, DateTime dt) {
        this.accountID.add(accountID);
        this.accountType.add(accountType);
        this.content.add(post);
        this.timeStamp.add(dt);
        try {
            if (accessSQLService().isClosed() == true) {
               accessSQLService(); // putting a while here would freeze the program if always false... so only one attempt for the momment
            } else {
                try(PreparedStatement insertUserPost =
                        this.conn.prepareStatement(
                        this.mySQLService.getINSERT_POST())) {
                            insertUserPost.setInt(1, userID);
                            insertUserPost.setInt(2, accountID);
                            insertUserPost.setString(3, accountType);
                            insertUserPost.setString(4, post);
                            insertUserPost.setTimestamp(5, new Timestamp(dt.toDate().getTime()));
                            insertUserPost.execute();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                this.conn.commit();
                this.conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }            
    }
}
