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
    
    private boolean insertAccount(
            final String firstName, final String lastName, final String emailAddr, final String table) {
        try {
            if (accessSQLService().isClosed() == true) {
               accessSQLService(); // putting a while here would freeze the program if always false... so only one attempt for the momment
            } else {
                try(PreparedStatement insertNewAccount = 
                        this.conn.prepareStatement(
                        "INSERT INTO " + table + "(" + 
                        this.mySQLService.getINSERT_ANY_ACCOUNT())) {
                            insertNewAccount.setInt(1, this.userDataService.getUserID());
                            insertNewAccount.setString(2, firstName);
                            insertNewAccount.setString(3, lastName);
                            insertNewAccount.setString(4, emailAddr);
                            insertNewAccount.execute();
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
    
    private boolean updateUserData(final String table) {
        try {
            if (accessSQLService().isClosed() == true) {
               accessSQLService(); // putting a while here would freeze the program if always false... so only one attempt for the momment
            } else {
                try(PreparedStatement updateUserData = 
                        this.conn.prepareStatement(
                        "UPDATE users SET " + table + this.mySQLService.getUPDATE_USER_ANY_NB_ACCOUNT())) {
                            
                            switch (table) {
                                case "facebook" :
                                    updateUserData.setByte(1, (byte)(this.userDataService.getNbFbAccount() + 1));
                                    break;
                                case "googleplus" :
                                    updateUserData.setByte(1, (byte)(this.userDataService.getNbGpAccount() + 1));
                                    break;
                                case "twitter" :
                                    updateUserData.setByte(1, (byte)(this.userDataService.getNbTwAccount() + 1));
                                    break;
                                case "pinterest" :
                                    updateUserData.setByte(1, (byte)(this.userDataService.getNbPnAccount() + 1));
                                    break;
                            }
                            updateUserData.setInt(2, this.userDataService.getUserID());
                            updateUserData.execute();
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
    
    @Override
    public boolean insertNewAccountIntoDatabase(
            final String firstName, final String lastName, final String emailAddr, final String table) {
        if (insertAccount(firstName, lastName, emailAddr, table)) {
            return updateUserData(table);
        }
        return false;
    }
}
