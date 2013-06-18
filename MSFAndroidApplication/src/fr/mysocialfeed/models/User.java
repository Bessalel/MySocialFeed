package fr.mysocialfeed.models;

/***
 * 
 * User class. Data and informations about the user and his socials accounts.
 * @author Julien
 *
 */
public class User {
    private int _userID;
	private String _username;
    private String _name;
    private String _lastname;
    private String _email;
    private int _nbFacebookAccount;
    private int _nbTwitterAccount;
    private int _nbGoogleAccount;
    private boolean _hasFacebookFilter;
    private boolean _hasTwitterFilter;
    private boolean _hasGoogleFilter;
    
    /**
     * Class constructor. Variables initialization with parameters.
     */
    public User( int userId, String username, String name, String lastname, String email,
    				int nbFb, int nbTw, int nbGo ) {
    	this._userID = userId;
    	this._username = username;
    	this._name = name;
    	this._lastname = lastname;
    	this._email = email;
    	this._nbFacebookAccount = nbFb;
    	this._nbTwitterAccount = nbTw;
    	this._nbGoogleAccount = nbGo;
    	this._hasFacebookFilter = true;
    	this._hasTwitterFilter = true;
    	this._hasGoogleFilter = true;    	
    }
    
    /**
     * Class constructor. Variables initialization with parameters.
     */
    public User( int userId, String username, String name, String lastname, String email,
    				int nbFb, int nbTw, int nbGo, boolean fbFilter, boolean twFilter, boolean goFilter ) {
    	this._userID = userId;
    	this._username = username;
    	this._name = name;
    	this._lastname = lastname;
    	this._email = email;
    	this._nbFacebookAccount = nbFb;
    	this._nbTwitterAccount = nbTw;
    	this._nbGoogleAccount = nbGo;
    	this._hasFacebookFilter = fbFilter;
    	this._hasTwitterFilter = twFilter;
    	this._hasGoogleFilter = goFilter;    	
    }

	public int get_userID() {
		return _userID;
	}

	public void set_userID(int _userID) {
		this._userID = _userID;
	}

	public String get_username() {
		return _username;
	}

	public void set_username(String _username) {
		this._username = _username;
	}

	public String get_name() {
		return _name;
	}

	public void set_name(String _name) {
		this._name = _name;
	}

	public String get_lastname() {
		return _lastname;
	}

	public void set_lastname(String _lastname) {
		this._lastname = _lastname;
	}

	public String get_email() {
		return _email;
	}

	public void set_email(String _email) {
		this._email = _email;
	}

	public int get_nbFacebookAccount() {
		return _nbFacebookAccount;
	}

	public void set_nbFacebookAccount(int _nbFacebookAccount) {
		this._nbFacebookAccount = _nbFacebookAccount;
	}

	public int get_nbTwitterAccount() {
		return _nbTwitterAccount;
	}

	public void set_nbTwitterAccount(int _nbTwitterAccount) {
		this._nbTwitterAccount = _nbTwitterAccount;
	}

	public int get_nbGoogleAccount() {
		return _nbGoogleAccount;
	}

	public void set_nbGoogleAccount(int _nbGoogleAccount) {
		this._nbGoogleAccount = _nbGoogleAccount;
	}

	public boolean get_hasFacebookFilter() {
		return _hasFacebookFilter;
	}

	public void set_hasFacebookFilter(boolean _hasFacebookFilter) {
		this._hasFacebookFilter = _hasFacebookFilter;
	}

	public boolean get_hasTwitterFilter() {
		return _hasTwitterFilter;
	}

	public void set_hasTwitterFilter(boolean _hasTwitterFilter) {
		this._hasTwitterFilter = _hasTwitterFilter;
	}

	public boolean get_hasGoogleFilter() {
		return _hasGoogleFilter;
	}

	public void set_hasGoogleFilter(boolean _hasGoogleFilter) {
		this._hasGoogleFilter = _hasGoogleFilter;
	}
    
	
	
}
