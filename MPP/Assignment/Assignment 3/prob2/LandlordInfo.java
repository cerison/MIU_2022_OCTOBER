package lesson3.labsolns.prob2;

import java.util.List;

public class LandlordInfo {
	private List<Building> buildings;
	public LandlordInfo(List<Building> b) {
		buildings = b;
	}
	public double calcProfits() {
		double profit = 0.0;
		for(Building build : buildings) {
			profit += build.calcProfit();
		}
		return profit;
	}
}
