/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mysocialfeed.services.interfaces;

import java.sql.Connection;

/**
 *
 * @author Vincent
 */
public interface MySQLService {
    public Connection connectToDatabase();
    public boolean buildDatabase();
    
    //getters for SQL statements:
    public String getCREATE_TABLE_USERS_SQL();
    public String getCREATE_TABLE_FACEBOOK_SQL();
    public String getCREATE_TABLE_TWITTER_SQL();
    public String getCREATE_TABLE_GOOGLEPLUS_SQL();
    public String getCREATE_TABLE_PINTEREST_SQL();
    public String getCREATE_TABLE_POSTS_SQL();
    public String getINSERT_USER();
    public String getINSERT_ANY_ACCOUNT();
    public String getINSERT_FACEBOOK_ACCOUNT();
    public String getINSERT_TWITTER_ACCOUNT();
    public String getINSERT_GOOGLEPLUS_ACCOUNT();
    public String getINSERT_PINTEREST_ACCOUNT();
    public String getINSERT_POST();
    public String getUPDATE_USER_ANY_NB_ACCOUNT();
    public String getUPDATE_USER_ACCOUNT();
    public String getLIST_USER();
    public String getLIST_USER_FACEBOOK_ACCOUNTS();
    public String getLIST_USER_TWITTER_ACCOUNT();
    public String getLIST_USER_GOOGLEPLUS_ACCOUNT();
    public String getLIST_USER_PINTEREST_ACCOUNT();
    public String getLIST_ALL_POSTS();
    public String getDELETE_USER();
    public String getDELETE_USERS_TABLE();
    public String getDELETE_FACEBOOK_TABLE();
    public String getDELETE_TWITTER_TABLE();
    public String getDELETE_GOOGLEPLUS_TABLE();
    public String getDELETE_PINTEREST_TABLE();
    public String getDELETE_USER_FACEBOOK_ACCOUNT();
    public String getDELETE_USER_TWITTER_ACCOUNT();
    public String getDELETE_USER_GOOGLEPLUS_ACCOUNT();
    public String getDELETE_USER_PINTEREST_ACCOUNT();
}
