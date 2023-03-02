package dataaccess;

import java.io.Serializable;


/*
 * This is a Java class called User that implements the Serializable interface. This means that objects of this class can be converted to 
 * a sequence of bytes that can be stored in a file or sent over a network. The class defines three fields: id, password, and authorization, 
 * which are all private. The class has a constructor that sets the values of these fields when a new User object is created. 
 * The class also has getter methods for each field, and an overridden toString() method that returns 
 * a string representation of the object in the format "[id:password, authorization]". The class also has a serial version UID, 
 * which is a unique identifier that ensures that the class can be serialized and deserialized consistently across different versions of the class.
 */
final public class User implements Serializable {
	
	private static final long serialVersionUID = 5147265048973262104L;

	private String id;
	
	private String password;
	private Auth authorization;
	User(String id, String pass, Auth  auth) {
		this.id = id;
		this.password = pass;
		this.authorization = auth;
	}
	
	public String getId() {
		return id;
	}
	public String getPassword() {
		return password;
	}
	public Auth getAuthorization() {
		return authorization;
	}
	@Override
	public String toString() {
		return "[" + id + ":" + password + ", " + authorization.toString() + "]";
	}
	
}
