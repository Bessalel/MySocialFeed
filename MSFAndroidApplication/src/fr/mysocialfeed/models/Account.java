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
    private String _token;
    private String _tokenSecret;
    private String _type;
    private int _userId;
    
    /**
     * Class constructor.
     */
    public Account() {
    	this._id = 0;
    	this._name = null;
    	this._token = null;
    	this._tokenSecret = null;
    	this._type = null;
    	this._userId = 1;
    }

	public int getId() {
		return _id;
	}

	public void setId(int _id) {
		this._id = _id;
	}

	public String getName() {
		return _name;
	}

	public void setName(String _name) {
		this._name = _name;
	}

	public String getToken() {
		return _token;
	}

	public void setToken(String _token) {
		this._token = _token;
	}

	public String getTokenSecret() {
		return _tokenSecret;
	}

	public void setTokenSecret(String _tokenSecret) {
		this._tokenSecret = _tokenSecret;
	}

	public String getType() {
		return _type;
	}

	public void setType(String _type) {
		this._type = _type;
	}

	public int getUserId() {
		return _userId;
	}

	public void setUserId(int _userId) {
		this._userId = _userId;
	}
    
    
}