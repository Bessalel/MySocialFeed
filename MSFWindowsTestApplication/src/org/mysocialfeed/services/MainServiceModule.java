/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mysocialfeed.services;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

import org.mysocialfeed.services.implementations.MainUserService;
import org.mysocialfeed.services.implementations.SetupMySQLService;
import org.mysocialfeed.services.interfaces.MySQLService;
import org.mysocialfeed.services.interfaces.UserService;

import org.mysocialfeed.services.interfaces.SocialAccountService;
import org.mysocialfeed.services.repository.ManageSocialAccountService;
import org.mysocialfeed.services.repository.ManageUserDataService;
import org.mysocialfeed.services.repository.ManageUserPostsService;
import org.mysocialfeed.services.repository.UserDataService;
import org.mysocialfeed.services.repository.UserPostsService;
/**
 *
 * @author Vincent
 */
public class MainServiceModule extends AbstractModule{

    @Override
    protected void configure() {
        bind(UserService.class).to(MainUserService.class).in(Singleton.class);
        bind(MySQLService.class).to(SetupMySQLService.class).in(Singleton.class);
        bind(UserDataService.class).to(ManageUserDataService.class).in(Singleton.class);
        bind(UserPostsService.class).to(ManageUserPostsService.class).in(Singleton.class);
        bind(SocialAccountService.class).to(ManageSocialAccountService.class).in(Singleton.class);
    }
    
}
