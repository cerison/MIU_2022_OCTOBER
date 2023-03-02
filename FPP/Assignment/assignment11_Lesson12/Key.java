package assignment11_Lesson12;

public class Key {
	private String firstName;
	private String lastName;
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public Key(String f, String l) {
		this.firstName = f;
		this.lastName = l;
	}

	@Override
	public boolean equals(Object o) {
		if(o == null || !(o instanceof Key)) return false;
		Key k = (Key) o;
		return this.firstName.equals(k.getFirstName()) && this.lastName.equals(k.getLastName());
	}
	
	@Override
	public int hashCode() {
		return this.getFirstName().hashCode() + this.getLastName().hashCode();
	}
}
