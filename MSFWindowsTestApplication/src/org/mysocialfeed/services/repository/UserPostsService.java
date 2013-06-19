/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mysocialfeed.services.repository;

import java.util.List;
import org.joda.time.DateTime;

/**
 *
 * @author Vincent
 */
public interface UserPostsService {
   
   // equivalent to constructor 
   public void loadPosts(final int userID, final List<Integer>postsID, final List<Integer> accountID, 
           final List<String> accountType, final List<String> content, final List<DateTime> timeStamp);
    
   // get message infos and content 
   public int getUserID();
   public int getPostID(final int index);
   public int getAccountID(final int index);
   public String getAccountType(final int index);
   public String getContent(final int index);
   public int getMaxIndex();
   public DateTime getTimeStamp(final int index);
   public boolean hasPost();
   
   //set message info and content
   public int refreshUserPosts();
   public void addPost(final int accountID, final String accountType, final String post, final DateTime dt);
   
}
