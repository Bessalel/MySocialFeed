/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mysocialfeed.services;

import org.mysocialfeed.services.implementations.MainUserService;
import org.mysocialfeed.services.implementations.SetupMySQLService;
import org.mysocialfeed.services.interfaces.MySQLService;
import org.mysocialfeed.services.interfaces.UserService;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import org.mysocialfeed.services.repository.ManageUserDataService;
import org.mysocialfeed.services.repository.UserDataService;
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
    }
    
}
