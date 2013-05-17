/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mysocialfeed.models;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Windows
 */
public class UserPosts {
    private int userID;
    private List<Integer> accountID; // = new ArrayList<Integer>();
    private List<String> accountType; // = new ArrayList<String>();
    private List<String> content;// = new ArrayList<String>();

    public UserPosts(int userID, List<Integer> accountID, List<String> accountType, List<String> content) {
        this.userID = userID;
        this.accountID = accountID;
        this.accountType = accountType;
        this.content = content;
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
}
