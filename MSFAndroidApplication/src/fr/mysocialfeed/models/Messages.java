package fr.mysocialfeed.models;

/***
 * 
 * Messages class. User timeline from his social account.
 * @author Julien
 *
 */
public class Messages {
    private int _id;
	private String _date;
    private String _accountName;
    private String _message;
    private String _sender;
    private String _share;
    private int _like;		// number of like/share
    private int _iLike;		// 1 if the user like/share this message
    private String _type;
    
    /**
     * Class constructor.
     */
    public Messages() {
    	this._id = 0;
    	this._date = null;
    	this._accountName = null;
    	this._message = null;
    	this._sender = null;
    	this._share = null;
    	this._like = 0;
    	this._iLike = 0;
    	this._type = null;
    }

	public int getId() {
		return _id;
	}

	public void setId(int _id) {
		this._id = _id;
	}

	public String getDate() {
		return _date;
	}

	public void setDate(String _date) {
		this._date = _date;
	}

	public String getAccountName() {
		return _accountName;
	}

	public void setAccountName(String _accountName) {
		this._accountName = _accountName;
	}

	public String getMessage() {
		return _message;
	}

	public void setMessage(String _message) {
		this._message = _message;
	}

	public String getSender() {
		return _sender;
	}

	public void setSender(String _sender) {
		this._sender = _sender;
	}

	public String getShare() {
		return _share;
	}

	public void setShare(String _share) {
		this._share = _share;
	}

	public int getLike() {
		return _like;
	}

	public void setLike(int _like) {
		this._like = _like;
	}

	public int getILike() {
		return _iLike;
	}

	public void setILike(int _iLike) {
		this._iLike = _iLike;
	}

	public String getType() {
		return _type;
	}

	public void setType(String _type) {
		this._type = _type;
	}
}
