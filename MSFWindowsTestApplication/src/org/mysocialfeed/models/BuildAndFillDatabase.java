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
    
        public static final String CREATE_TABLE_FACEBOO_SQL = "" +
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
        
        // Insert statements
        public static final String INSERT_USER = "" + 
                "INSERT INTO users(" +
                "username, password, firstname, lastname, email," +
                " facebook, twitter, googleplus, pinterest" +
                ")" +
                " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
        
        // Listing statements
        public static final String LIST_USER = "SELECT * FROM users WHERE username = ?";  
        
        // Delete statements
        public static final String DELETE_USER = "DELETE FROM users WHERE id = ?";
        public static final String DELETE_USERSTABLES = "DROP TABLE IF EXISTS users";
}
