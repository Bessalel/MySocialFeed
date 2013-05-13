/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mysocialfeed.models;

/**
 *
 * @author Windows
 */
public class UserPosts {
  private int userID;
  private int accountID;
  private String accountType;
  private String content; // should be String[]

  public UserPosts(int userID, int accountID, String accountType, String content) {
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

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accnt_type) {
        this.accountType = accnt_type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
