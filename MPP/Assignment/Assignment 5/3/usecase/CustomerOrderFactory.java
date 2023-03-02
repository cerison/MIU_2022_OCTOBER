package usecase;

import java.time.LocalDate;

public class CustomerOrderFactory {

    private CustomerOrderFactory() {
    }

    public static Customer addCustomer(String name){
        return new Customer(name);
    }

    public static Order newOrder(Customer customer, LocalDate date) {
        if (customer == null) throw new NullPointerException("Null customer");
        Order order = new Order(date);
        customer.addOrder(order);
        return order;
    }
}
