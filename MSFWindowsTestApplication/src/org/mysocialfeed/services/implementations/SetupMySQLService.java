/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mysocialfeed.services.implementations;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import org.mysocialfeed.services.interfaces.MySQLService;

/**
 *
 * @author Vincent
 */
public class SetupMySQLService implements MySQLService {
    
    private Connection conn;
    
    @Override
    public Connection connectToDatabase() {
        try {
            this.conn = DriverManager.getConnection("jdbc:mysql://localhost/mysocialfeed","root","");
            this.conn.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return(this.conn);
    }   
    
    @Override
    public boolean builDatabase() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static HashMap<String, Object> getCtx() {
        return ctx;
    }

    @Override
    public String getCREATE_TABLE_USERS_SQL() {
        return CREATE_TABLE_USERS_SQL;
    }

    @Override
    public String getCREATE_TABLE_FACEBOOK_SQL() {
        return CREATE_TABLE_FACEBOOK_SQL;
    }

    @Override
    public String getCREATE_TABLE_TWITTER_SQL() {
        return CREATE_TABLE_TWITTER_SQL;
    }

    @Override
    public String getCREATE_TABLE_GOOGLEPLUS_SQL() {
        return CREATE_TABLE_GOOGLEPLUS_SQL;
    }

    @Override
    public String getCREATE_TABLE_PINTEREST_SQL() {
        return CREATE_TABLE_PINTEREST_SQL;
    }

    @Override
    public String getCREATE_TABLE_POSTS_SQL() {
        return CREATE_TABLE_POSTS_SQL;
    }

    @Override
    public String getINSERT_USER() {
        return INSERT_USER;
    }

    @Override
    public String getINSERT_FACEBOOK_ACCOUNT() {
        return INSERT_FACEBOOK_ACCOUNT;
    }

    @Override
    public String getINSERT_TWITTER_ACCOUNT() {
        return INSERT_TWITTER_ACCOUNT;
    }

    @Override
    public String getINSERT_GOOGLEPLUS_ACCOUNT() {
        return INSERT_GOOGLEPLUS_ACCOUNT;
    }

    @Override
    public String getINSERT_PINTEREST_ACCOUNT() {
        return INSERT_PINTEREST_ACCOUNT;
    }

    @Override
    public String getINSERT_POST() {
        return INSERT_POST;
    }

    @Override
    public String getUPDATE_USER_ACCOUNT() {
        return UPDATE_USER_ACCOUNT;
    }

    @Override
    public String getLIST_USER() {
        return LIST_USER;
    }

    @Override
    public String getLIST_USER_FACEBOOK_ACCOUNTS() {
        return LIST_USER_FACEBOOK_ACCOUNTS;
    }

    @Override
    public String getLIST_USER_TWITTER_ACCOUNT() {
        return LIST_USER_TWITTER_ACCOUNT;
    }

    @Override
    public String getLIST_USER_GOOGLEPLUS_ACCOUNT() {
        return LIST_USER_GOOGLEPLUS_ACCOUNT;
    }

    @Override
    public String getLIST_USER_PINTEREST_ACCOUNT() {
        return LIST_USER_PINTEREST_ACCOUNT;
    }

    @Override
    public String getLIST_ALL_POSTS() {
        return LIST_ALL_POSTS;
    }

    @Override
    public String getDELETE_USER() {
        return DELETE_USER;
    }

    @Override
    public String getDELETE_USERS_TABLE() {
        return DELETE_USERS_TABLE;
    }

    @Override
    public String getDELETE_FACEBOOK_TABLE() {
        return DELETE_FACEBOOK_TABLE;
    }

    @Override
    public String getDELETE_TWITTER_TABLE() {
        return DELETE_TWITTER_TABLE;
    }

    @Override
    public String getDELETE_GOOGLEPLUS_TABLE() {
        return DELETE_GOOGLEPLUS_TABLE;
    }

    @Override
    public String getDELETE_PINTEREST_TABLE() {
        return DELETE_PINTEREST_TABLE;
    }

    @Override
    public String getDELETE_USER_FACEBOOK_ACCOUNT() {
        return DELETE_USER_FACEBOOK_ACCOUNT;
    }

    @Override
    public String getDELETE_USER_TWITTER_ACCOUNT() {
        return DELETE_USER_TWITTER_ACCOUNT;
    }

    @Override
    public String getDELETE_USER_GOOGLEPLUS_ACCOUNT() {
        return DELETE_USER_GOOGLEPLUS_ACCOUNT;
    }

    @Override
    public String getDELETE_USER_PINTEREST_ACCOUNT() {
        return DELETE_USER_PINTEREST_ACCOUNT;
    }
    
    
    
    public static HashMap<String, Object> ctx = null;
	static {
                ctx = new HashMap<>();
                
                com.mysql.jdbc.jdbc2.optional.MysqlDataSource dsMySQL = new MysqlDataSource();
                dsMySQL.setServerName("localhost");
                dsMySQL.setPortNumber(3306);
                dsMySQL.setDatabaseName("mysocialfeed");

            	ctx.put("jdbc/MySociaFeed", dsMySQL);
	}
	
	// Create statements (skipped, if already done)
	private final String CREATE_TABLE_USERS_SQL = ""
                + "CREATE TABLE IF NOT EXISTS users ("
		+ "id INT UNSIGNED NOT NULL AUTO_INCREMENT,"
		+ "username VARCHAR(255), "
                + "password VARCHAR(255), "
                + "firstname VARCHAR(255), "
                + "lastname VARCHAR(255), "
                + "email VARCHAR(255), "
                + "facebook TINYINT UNSIGNED, "
                + "twitter TINYINT UNSIGNED, "
                + "googleplus TINYINT UNSIGNED, "
                + "pinterest TINYINT UNSIGNED, "
                + "PRIMARY KEY (id)"
		+ ");";
    
        private final String CREATE_TABLE_FACEBOOK_SQL = ""
                + "CREATE TABLE IF NOT EXISTS facebook ("
		+ "accountid INT PRIMARY KEY NOT NULL AUTO_INCREMENT, "
                + "userid INT UNSIGNED, "
                + "firstname VARCHAR(255), "
                + "lastname VARCHAR(255), "
                + "email VARCHAR(255), "
                + "CONSTRAINT users_fk FOREIGN KEY (userid) REFERENCES users (id) ON DELETE CASCADE ON UPDATE CASCADE"
		+ ");";
        
        private final String CREATE_TABLE_TWITTER_SQL = ""
                + "CREATE TABLE IF NOT EXISTS twitter ("
                + "accountid INT PRIMARY KEY NOT NULL AUTO_INCREMENT, "
		+ "userid INT, "
                + "username VARCHAR(255), "
                + "firstname VARCHAR(255), "
                + "lastname VARCHAR(255), "
                + "email VARCHAR(255), "
                + "FOREIGN KEY (userid) REFERENCES users (id) ON DELETE CASCADE ON UPDATE CASCADE"
		+ ");";
        
        private final String CREATE_TABLE_GOOGLEPLUS_SQL = ""
                + "CREATE TABLE IF NOT EXISTS googleplus ("
                + "accountid INT PRIMARY KEY NOT NULL AUTO_INCREMENT, "
		+ "userid INT,"
                + "firstname VARCHAR(255), "
                + "lastname VARCHAR(255), "
                + "email VARCHAR(255), "
                + "FOREIGN KEY (userid) REFERENCES users (id) ON DELETE CASCADE ON UPDATE CASCADE"
		+ ");";
        
        private final String CREATE_TABLE_PINTEREST_SQL = ""
                + "CREATE TABLE IF NOT EXISTS pinterest ("
                + "accountid INT PRIMARY KEY NOT NULL AUTO_INCREMENT, "
		+ "userid INT, "
                + "firstname VARCHAR(255), "
                + "lastname VARCHAR(255), "
                + "email VARCHAR(255), "
                + "FOREIGN KEY (userid) REFERENCES users (id) ON DELETE CASCADE ON UPDATE CASCADE"
		+ ");";
        
        private final String CREATE_TABLE_POSTS_SQL = ""
                + "CREATE TABLE IF NOT EXISTS posts ("
                + "postid INT PRIMARY KEY NOT NULL AUTO_INCREMENT, "
                + "userid INT, "
                + "accountid INT, " // cannot be a constraint foreign key because of undefined origin !!
                + "type ENUM('Fb', 'Tw', 'G+', 'Pin'), "
                + "content LONGTEXT, "
                + "timestamp DATETIME, "
                + "FOREIGN KEY (userid) REFERENCES users (id) ON DELETE CASCADE ON UPDATE CASCADE"
                + ");";
        
        
        // Insert statements
        private final String INSERT_USER = ""
                + "INSERT INTO users("
                + "username, password, firstname, lastname, email,"
                + "facebook, twitter, googleplus, pinterest"
                + ")"
                + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
        
        private final String INSERT_FACEBOOK_ACCOUNT = "" 
                + "INSERT INTO facebook("
                + "userid, firstname, lastname, email"
                + ")"
                + " VALUES (?, ?, ?, ?);";
        
        private final String INSERT_TWITTER_ACCOUNT = ""
                + "INSERT INTO twitter("
                + "userid, username, firstname, lastname, email, "
                + ")"
                + " VALUES (?, ?, ?, ?, ?);";
        
        private final String INSERT_GOOGLEPLUS_ACCOUNT = ""
                + "INSERT INTO googleplus("
                + "userid, firstname, lastname, email"
                + ")"
                + " VALUES (?, ?, ?, ?);";
        
        private final String INSERT_PINTEREST_ACCOUNT = ""
                + "INSERT INTO pinterest("
                + "userid, firstname, lastname, email"
                + ")"
                + " VALUES (?, ?, ?, ?);";
        
        private final String INSERT_POST = ""
                + "INSERT INTO posts("
                + "userid, accountid, "
                + "type, content, timestamp"
                + ")"
                + " VALUES (?, ?, ?, ?, ?);";
        
        
        // Update statements
        private final String UPDATE_USER_ACCOUNT = "" // no password change !
                + "UPDATE users "
                + "SET "
                + "username = ?, firstname = ?, "
                + "lastname = ?, email = ?, "
                + "facebook = ?, twitter = ?, "
                + "googleplus = ?, pinterest = ? "
                + "WHERE id = ?";
        
        
        // Listing statements
        private final String LIST_USER = 
                "SELECT * FROM users WHERE username = ?";  
        private final String LIST_USER_FACEBOOK_ACCOUNTS = 
                "SELECT * FROM facebook WHERE userid = ?";
        private final String LIST_USER_TWITTER_ACCOUNT = 
                "SELECT * FROM twitter WHERE userid = ?";
        private final String LIST_USER_GOOGLEPLUS_ACCOUNT = 
                "SELECT * FROM googleplus WHERE userid = ?";
        private final String LIST_USER_PINTEREST_ACCOUNT = 
                "SELECT * FROM pinterest WHERE userid = ?";
        private final String LIST_ALL_POSTS =
                "SELECT * FROM posts WHERE userid = ?";
        
        
        // Delete statements
        private final String DELETE_USER = 
                "DELETE FROM users WHERE id = ?";
        private final String DELETE_USERS_TABLE = 
                "DROP TABLE IF EXISTS users";
        private final String DELETE_FACEBOOK_TABLE = 
                "DROP TABLE IF EXISTS facebook";
        private final String DELETE_TWITTER_TABLE = 
                "DROP TABLE IF EXISTS twitter";
        private final String DELETE_GOOGLEPLUS_TABLE = 
                "DROP TABLE IF EXISTS googleplus";
        private final String DELETE_PINTEREST_TABLE = 
                "DROP TABLE IF EXISTS pinterest";
        private final String DELETE_USER_FACEBOOK_ACCOUNT = 
                "DELETE FROM facebook WHERE userid = ? AND id = ?";
        private final String DELETE_USER_TWITTER_ACCOUNT = 
                "DELETE FROM twitter WHERE userid = ? AND id = ?";
        private final String DELETE_USER_GOOGLEPLUS_ACCOUNT = 
                "DELETE FROM googleplus WHERE userid = ? AND id = ?";
        private final String DELETE_USER_PINTEREST_ACCOUNT = 
                "DELETE FROM pinterest WHERE userid = ? AND id = ?";
}
