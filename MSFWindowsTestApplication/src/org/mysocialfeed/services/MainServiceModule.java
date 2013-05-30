/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mysocialfeed.supportingfiles;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

/**
 *
 * @author Vincent
 */
public class MainServiceModule extends AbstractModule{

    @Override
    protected void configure() {
        bind(UserService.class).to(DatabaseUserService.class).in(Singleton.class);
        bind(MySQLService.class).to(SetupMySQLService.class).in(Singleton.class);
    }
    
}
