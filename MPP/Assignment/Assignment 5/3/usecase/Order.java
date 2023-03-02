package usecase;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class Order {
	private LocalDate orderDate;
	private List<Item> items;
	Order(LocalDate orderDate) {
		this.orderDate = orderDate;
		items = new ArrayList<>();
	}

	public void addItem(String name){
		items.add(new Item(name));
	}
	@Override
	public String toString() {
		return orderDate + ": " + 
	              items.toString();
	}
}
