/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.mysocialfeed.models;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.util.HashMap;

/**
 *
 * @author Vincent
 */
public class BuildAndFillDatabase {

    public static HashMap<String, Object> ctx = null;
	static {
                ctx = new HashMap<>();
                
                com.mysql.jdbc.jdbc2.optional.MysqlDataSource dsMySQL = new MysqlDataSource();
                dsMySQL.setServerName("localhost");
                dsMySQL.setPortNumber(3306);
                dsMySQL.setDatabaseName("mysocialfeed");

            	ctx.put("jdbc/MySociaFeed", dsMySQL);
	}
	
	// Create statements (skipped, if already done !)
	public static final String CREATE_TABLE_USERS_SQL = "" +
                "CREATE TABLE IF NOT EXISTS users (" +
		"id INT NOT NULL AUTO_INCREMENT," +
		"username VARCHAR(255), " +
                "password VARCHAR(255), " +
                "firstname VARCHAR(255), " +
                "lastname VARCHAR(255), " +
                "email VARCHAR(255), " +
                "facebook TINYINT, " +
                "twitter TINYINT, " +
                "googleplus TINYINT, " +
                "pinterest TINYINT, "
                + "PRIMARY KEY (id)" +
		");";
    
        public static final String CREATE_TABLE_FACEBOOK_SQL = "" +
                "CREATE TABLE IF NOT EXISTS facebook (" +
		"accountid INT PRIMARY KEY NOT NULL AUTO_INCREMENT, " +
                "userid INT, " +
                "firstname VARCHAR(255), " +
                "lastname VARCHAR(255), " +
                "email VARCHAR(255), " +
                "friends INT, " +
                "CONSTRAINT users_fk FOREIGN KEY (userid) REFERENCES users (id) ON DELETE CASCADE" +
		");";
        
        public static final String CREATE_TABLE_TWITTER_SQL = "" +
                "CREATE TABLE IF NOT EXISTS twitter (" +
                "accountid INT PRIMARY KEY NOT NULL AUTO_INCREMENT, " +
		"userid INT, " +
                "username VARCHAR(255), " +
                "firstname VARCHAR(255), " +
                "lastname VARCHAR(255), " +
                "email VARCHAR(255), " +
                "followers INT, " +
                "following INT, " +
                "FOREIGN KEY (userid) REFERENCES users (id) ON DELETE CASCADE" +
		");";
        
        public static final String CREATE_TABLE_GOOGLEPLUS_SQL = "" +
                "CREATE TABLE IF NOT EXISTS googleplus (" +
                "accountid INT PRIMARY KEY NOT NULL AUTO_INCREMENT, " +
		"userid INT," +
                "firstname VARCHAR(255), " +
                "lastname VARCHAR(255), " +
                "email VARCHAR(255), " +
                "friends INT, " +
                "FOREIGN KEY (userid) REFERENCES users (id) ON DELETE CASCADE" +
		");";
        
        public static final String CREATE_TABLE_PINTEREST_SQL = "" +
                "CREATE TABLE IF NOT EXISTS pinterest (" +
                "accountid INT PRIMARY KEY NOT NULL AUTO_INCREMENT, " +
		"userid INT, " +
                "firstname VARCHAR(255), " +
                "lastname VARCHAR(255), " +
                "email VARCHAR(255), " +
                "friends INT, " +
                "FOREIGN KEY (userid) REFERENCES users (id) ON DELETE CASCADE" +
		");";
        
        
        // Insert statements
        public static final String INSERT_USER = "" + 
                "INSERT INTO users(" +
                "username, password, firstname, lastname, email," +
                " facebook, twitter, googleplus, pinterest" +
                ")" +
                " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
        
        public static final String INSERT_FACEBOOK_ACCOUNT = "" + 
                "INSERT INTO facebook(" +
                "userid, firstname, lastname, email, friends" +
                ")" +
                " VALUES (?, ?, ?, ?, ?);";
        
        public static final String INSERT_TWITTER_ACCOUNT = "" + 
                "INSERT INTO twitter(" +
                "userid, username, firstname, lastname, email, " +
                "followers, following" +
                ")" +
                " VALUES (?, ?, ?, ?, ?);";
        
        public static final String INSERT_GOOGLEPLUS_ACCOUNT = "" + 
                "INSERT INTO googleplus(" +
                "userid, firstname, lastname, email, friends" +
                ")" +
                " VALUES (?, ?, ?, ?, ?);";
        
        public static final String INSERT_PINTEREST_ACCOUNT = "" + 
                "INSERT INTO pinterest(" +
                "userid, firstname, lastname, email, friends" +
                ")" +
                " VALUES (?, ?, ?, ?, ?);";
          
        
        // Listing statements
        public static final String LIST_USER = 
                "SELECT * FROM users WHERE username = ?";  
        public static final String LIST_USER_FACEBOOK_ACCOUNT = 
                "SELECT * FROM facebook WHERE userid = ?";
        public static final String LIST_USER_TWITTER_ACCOUNT = 
                "SELECT * FROM twitter WHERE userid = ?";
        public static final String LIST_USER_GOOGLEPLUS_ACCOUNT = 
                "SELECT * FROM googleplus WHERE userid = ?";
        public static final String LIST_USER_PINTEREST_ACCOUNT = 
                "SELECT * FROM pinterest WHERE userid = ?";
        
        
        // Delete statements
        public static final String DELETE_USER = 
                "DELETE FROM users WHERE id = ?";
        public static final String DELETE_USERS_TABLE = 
                "DROP TABLE IF EXISTS users";
        public static final String DELETE_FACEBOOK_TABLE = 
                "DROP TABLE IF EXISTS facebook";
        public static final String DELETE_TWITTER_TABLE = 
                "DROP TABLE IF EXISTS twitter";
        public static final String DELETE_GOOGLEPLUS_TABLE = 
                "DROP TABLE IF EXISTS googleplus";
        public static final String DELETE_PINTEREST_TABLE = 
                "DROP TABLE IF EXISTS pinterest";
        public static final String DELETE_USER_FACEBOOK_ACCOUNT = 
                "DELETE FROM facebook WHERE userid = ? AND id = ?";
        public static final String DELETE_USER_TWITTER_ACCOUNT = 
                "DELETE FROM twitter WHERE userid = ? AND id = ?";
        public static final String DELETE_USER_GOOGLEPLUS_ACCOUNT = 
                "DELETE FROM googleplus WHERE userid = ? AND id = ?";
        public static final String DELETE_USER_PINTEREST_ACCOUNT = 
                "DELETE FROM pinterest WHERE userid = ? AND id = ?";
}
