package dataaccess;

import java.io.Serializable;

public enum Auth implements Serializable {
	LIBRARIAN, ADMIN, BOTH;  //The enumeration implements the Serializable interface, which means that its instances can be serialized and deserialized.
}
