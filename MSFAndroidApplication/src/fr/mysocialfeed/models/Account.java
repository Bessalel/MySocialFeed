package fr.mysocialfeed.models;

/***
 * 
 * Messages class. User timeline from his social account.
 * @author Julien
 *
 */
public class Account {
    private int _id;
	private String _name;
    private String _login;
    private String _password;
    private String _type;
    private int _userId;
    
    /**
     * Class constructor.
     */
    public Account() {
    	this._id = 0;
    	this._name = null;
    	this._login = null;
    	this._password = null;
    	this._type = null;
    	this._userId = 1;
    }
    
    public int getId() {
    	return this._id;
    }
}