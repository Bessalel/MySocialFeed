/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mysocialfeed.models;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Windows
 */
public class UserPosts {
    private int userID;
    private List<Integer> accountID;
    private List<String> accountType;
    private List<String> content;
    //private List<Date> timeStamp;
    private List<Timestamp> timeStamp;
    
    public UserPosts(int userID, List<Integer> accountID, List<String> accountType, List<String> content, List<Timestamp> timeStamp) {
        this.userID = userID;
        this.accountID = accountID;
        this.accountType = accountType;
        this.content = content;
        this.timeStamp = timeStamp;
    }
    
    public void addAccountID(int accntID){
        this.accountID.add(accntID);
    }
    
    public void addAccountType(String type){
        this.accountType.add(type);
    }
    
    public void addPost(String post){
        this.content.add(post);
    }
    
    public void addTimeStamp(Timestamp timeStamp){
        this.timeStamp.add(timeStamp);
    }
    
    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public List<Integer> getAccountID() {
        return accountID;
    }

    public void setAccountID(List<Integer> accountID) {
        this.accountID = accountID;
    }

    public List<String> getAccountType() {
        return accountType;
    }

    public void setAccountType(List<String> accountType) {
        this.accountType = accountType;
    }

    public List<String> getContent() {
        return content;
    }

    public void setContent(List<String> content) {
        this.content = content;
    }
    
    public List<Timestamp> getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(List<Timestamp> timeStamp) {
        this.timeStamp = timeStamp;
    }
}
