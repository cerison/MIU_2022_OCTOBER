package lesson3.labsolns.prob4;

public class Trailer extends Property {
	private static final double RENT = 500;
	private Address address;
	
	public Trailer(Address address) {
		this.address = address;
	}
	public double computeRent(){
		return RENT;
	}
	public Address getAddress() {
		return address;
	}
}
