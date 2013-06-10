/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mysocialfeed.services.repository;

import java.sql.Timestamp;
import java.util.List;

/**
 *
 * @author Vincent
 */
public class ManageUserPostService implements UserPostsService{

    private int userID;
    private List<Integer> postsID;
    private List<Integer> accountID;
    private List<String> accountType;
    private List<String> content;
    private List<Timestamp> timeStamp;

    
    @Override
    public void loadPosts(int userID, List<Integer>postsID, List<Integer> accountID, List<String> accountType, 
    List<String> content, List<Timestamp> timeStamp) {
        this.userID = userID;
        this.postsID = postsID;
        this.accountID = accountID;
        this.accountType = accountType;
        this.content = content;
        this.timeStamp = timeStamp;
    }

    @Override
    public int getUserID() {
        return this.userID;
    }

    @Override
    public int getPostID(int index) {
        return this.postsID.get(index);
    }
    
    @Override
    public int getAccountID(int index) {
        return this.accountID.get(index);
    }

    @Override
    public String getAccountType(int index) {
        return this.accountType.get(index);
    }

    @Override
    public String getContent(int index) {
        return this.content.get(index);
    }

    @Override
    public Timestamp getTimeStamp(int index) {
        return this.timeStamp.get(index);
    }

    @Override
    public void addAccountID(int id){
        this.accountID.add(id);
    }
    
    @Override
    public void addAccountType(String type) {
        this.accountType.add(type);
    }

    @Override
    public void addPost(String post) {
        this.content.add(post);
    }

    @Override
    public void addTimeStamp(Timestamp timeStamp) {
        this.timeStamp.add(timeStamp);
    }
}
