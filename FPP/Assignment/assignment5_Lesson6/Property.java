package assignment5_Lesson6;
public abstract class Property {
    private Address address;


    public Property() {
        super();
    }

    protected Property(Address address) {
        this.address = address;
    }

    public Address getAddress() {
        return this.address;
    }

    abstract double computeRent();

}
