package usecase;

public class Item {
	String name;

	Item(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}
}
