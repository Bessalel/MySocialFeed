/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mysocialfeed.services.repository;

import com.google.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
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
    private UserDataService userDataService;
    
    @Inject
    public ManageUserPostsService(UserDataService userDataService, MySQLService mySQLService) {
        this.userDataService = userDataService;
        this.mySQLService = mySQLService;
    }
    
    private Connection accessSQLService(){
        return this.conn = this.mySQLService.connectToDatabase();
    }
    
    @Override
    public void loadPosts(final int userID, final List<Integer>postsID, final List<Integer> accountID, 
            final List<String> accountType, final List<String> content, final List<DateTime> DateTime) {
        this.userID = userID;
        this.postsID = postsID;
        this.accountID = accountID;
        this.accountType = accountType;
        this.content = content;
        this.timeStamp = DateTime;
    }
    
    @Override
    public int refreshUserPosts() {
        int newIndex = -1;
        try {
            if (accessSQLService().isClosed() == true) {
               accessSQLService(); // putting a while here would freeze the program if always false... so only one attempt for the momment
            } else {
                try(PreparedStatement getUserNewPosts =
                        this.conn.prepareStatement(
                        this.mySQLService.getLIST_ALL_POSTS())) {
                            getUserNewPosts.setInt(1, this.userDataService.getUserID());
                            ResultSet rs = getUserNewPosts.executeQuery();
                            
                            while (rs.next()) {
                                for (int id : this.postsID) {
                                    if (id == rs.getInt(1)) {
                                        rs.next(); // will skip any found postID in ArrayList
                                    } else {
                                        if (!(newIndex == -1)) {
                                            newIndex = rs.getInt(1);
                                        }
                                        this.postsID.add(rs.getInt(1));
                                        this.accountID.add(rs.getInt(3));
                                        this.accountType.add(rs.getString(4));
                                        this.content.add(rs.getString(5));
                                        this.timeStamp.add(new DateTime(rs.getTimestamp(6)));
                                    }
                                }
                            }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newIndex;
    }
    

    @Override
    public int getUserID() {
        return this.userID;
    }

    @Override
    public int getPostID(final int index) {
        return this.postsID.get(index);
    }
    
    @Override
    public int getAccountID(final int index) {
        return this.accountID.get(index);
    }

    @Override
    public String getAccountType(final int index) {
        return this.accountType.get(index);
    }

    @Override
    public String getContent(final int index) {
        return this.content.get(index);
    }
    
    @Override
    public int getMaxIndex() {
        return this.content.size();
    }

    @Override
    public DateTime getTimeStamp(final int index) {
        return this.timeStamp.get(index);
    }
    
    @Override
    public boolean hasPost() {
        return (this.userID != 0 && !(this.accountID.isEmpty()) && !(this.accountType.isEmpty()) 
                && !(this.content.isEmpty()) && !(this.postsID.isEmpty()) && !(this.timeStamp.isEmpty()));
    }
    
    @Override
    public void addPost(final int accountID, final String accountType, final String post, final DateTime dt) {
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
                        this.mySQLService.getINSERT_POST(), Statement.RETURN_GENERATED_KEYS)) {
                            insertUserPost.setInt(1, this.userDataService.getUserID());
                            insertUserPost.setInt(2, accountID);
                            insertUserPost.setString(3, accountType);
                            insertUserPost.setString(4, post);
                            insertUserPost.setTimestamp(5, new Timestamp(dt.toDate().getTime()));
                            insertUserPost.execute();
                            ResultSet rs = insertUserPost.getGeneratedKeys();
                            if (rs.next()) {
                                this.postsID.add(rs.getInt(1));
                            }
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
