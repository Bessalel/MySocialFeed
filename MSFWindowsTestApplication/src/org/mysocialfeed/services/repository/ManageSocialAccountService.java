/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mysocialfeed.services.repository;

import com.google.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.mysocialfeed.services.interfaces.MySQLService;
import org.mysocialfeed.services.interfaces.SocialAccountService;

/**
 *
 * @author Vincent
 */
public class ManageSocialAccountService implements SocialAccountService {
    
    private Connection conn;
    
    private final UserDataService userDataService;
    private final MySQLService mySQLService;
    
    @Inject
    public ManageSocialAccountService (UserDataService userDataService, MySQLService mySQLService) {
        this.userDataService = userDataService;
        this.mySQLService = mySQLService;
    }
    
    private Connection accessSQLService(){
        return this.conn = this.mySQLService.connectToDatabase();
    }
    
    @Override
    public boolean insertNewAccountIntoDatabase(
            final String firstName, final String lastName, final String emailAddr, final String type) {
        try {
            if (accessSQLService().isClosed() == true) {
               accessSQLService(); // putting a while here would freeze the program if always false... so only one attempt for the momment
            } else {
                switch (type) {
                    case "Fb" :
                        try(PreparedStatement insertNewAccount =
                        this.conn.prepareStatement(
                            this.mySQLService.getINSERT_FACEBOOK_ACCOUNT())) {
                                insertNewAccount.setInt(1, this.userDataService.getUserID());
                                insertNewAccount.setString(2, firstName);
                                insertNewAccount.setString(3, lastName);
                                insertNewAccount.setString(4, emailAddr);
                                insertNewAccount.execute();
                        }
                        break;
                    case "G+" :
                        try(PreparedStatement insertNewAccount =
                        this.conn.prepareStatement(
                            this.mySQLService.getINSERT_GOOGLEPLUS_ACCOUNT())) {
                                insertNewAccount.setInt(1, this.userDataService.getUserID());
                                insertNewAccount.setString(2, firstName);
                                insertNewAccount.setString(3, lastName);
                                insertNewAccount.setString(4, emailAddr);
                                insertNewAccount.execute();
                        }
                        break;
                    case "Tw" :
                        try(PreparedStatement insertNewAccount =
                        this.conn.prepareStatement(
                            this.mySQLService.getINSERT_TWITTER_ACCOUNT())) {
                                insertNewAccount.setInt(1, this.userDataService.getUserID());
                                insertNewAccount.setString(2, firstName);
                                insertNewAccount.setString(3, lastName);
                                insertNewAccount.setString(4, emailAddr);
                                insertNewAccount.execute();
                        }
                        break;
                    case "Pin" :
                        try(PreparedStatement insertNewAccount =
                        this.conn.prepareStatement(
                            this.mySQLService.getINSERT_PINTEREST_ACCOUNT())) {
                                insertNewAccount.setInt(1, this.userDataService.getUserID());
                                insertNewAccount.setString(2, firstName);
                                insertNewAccount.setString(3, lastName);
                                insertNewAccount.setString(4, emailAddr);
                                insertNewAccount.execute();
                        }
                        break;
                } 
                this.conn.commit();
                this.conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
