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
public interface UserPostsService {
   
   // equivalent to constructor 
   public void loadPosts(int userID, List<Integer>postsID, List<Integer> accountID, List<String> accountType, 
           List<String> content, List<Timestamp> timeStamp);
    
   // get message infos and content 
   public int getUserID();
   public int getPostID(int index);
   public int getAccountID(int index);
   public String getAccountType(int index);
   public String getContent(int index);
   public Timestamp getTimeStamp(int index);
   
   
   //set message info and cotnent
   public void addAccountID(int id);
   public void addAccountType(String type);
   public void addPost(String post);
   public void addTimeStamp(Timestamp timeStamp);
   
}
