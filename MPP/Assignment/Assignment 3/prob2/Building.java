package lesson3.labsolns.prob2;

import java.util.List;

public class Building {
	private List<Apartment> apartments;
	private double maintCosts;
	public Building(double maintCosts,
           List<Apartment> apts) {
		this.maintCosts = maintCosts;
		apartments = apts;
	}
	public double calcProfit() {
		double grossProfit = 0.0;
		for(Apartment a : apartments) {
			grossProfit += a.getRent();
		}
		return grossProfit - maintCosts;
	}
}

