import usecase.Customer;
import usecase.CustomerOrderFactory;
import usecase.Order;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        System.out.println("**************** GROUP # 11 - LAB # 5 (Problem # 3) ****************");

        Customer customer = CustomerOrderFactory.addCustomer("Bob");
        Order order = CustomerOrderFactory.newOrder(customer, LocalDate.now());
        order.addItem("Shirt");
        order.addItem("Laptop");

        order = CustomerOrderFactory.newOrder(customer, LocalDate.now());
        order.addItem("Pants");
        order.addItem("Knife set");

        System.out.println("Customer Name: "+ customer.getName());
        System.out.println("Customer Order(s): "+ customer.getOrders());
    }
}