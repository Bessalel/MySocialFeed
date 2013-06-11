/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mysocialfeed.services.repository;

import java.sql.Timestamp;
import java.util.List;
import org.joda.time.DateTime;

/**
 *
 * @author Vincent
 */
public interface UserPostsService {
   
   // equivalent to constructor 
   public void loadPosts(int userID, List<Integer>postsID, List<Integer> accountID, List<String> accountType, 
           List<String> content, List<DateTime> timeStamp);
    
   // get message infos and content 
   public int getUserID();
   public int getPostID(int index);
   public int getAccountID(int index);
   public String getAccountType(int index);
   public String getContent(int index);
   public int getMaxIndex();
   public DateTime getTimeStamp(int index);
   public boolean hasPost();
   
   //set message info and content
   public void addPost(int accountID, String accountType, String post, DateTime dt);
   
}
