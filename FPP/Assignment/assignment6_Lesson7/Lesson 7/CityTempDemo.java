package assignment6;

import java.util.Arrays;

class City implements Comparable<City> {
	private String cityName;
	private double temp;

	public City(String cityName, Double temp) {
		super();
		this.cityName = cityName;
		this.temp = temp;
	}

	public String getCityName() {
		return cityName;
	}

	public double getTemp() {
		return temp;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public void setTemp(double temp) {
		this.temp = temp;
	}
	//=======================over rides compareTo of co
	@Override
	public int compareTo(City other) {
		City otherCity = (City) other;
		if (temp < otherCity.temp)
			return -1;
		else if (temp > otherCity.temp)
			return 1;
		else
			return 0;
	}
	public static void sortArray(Comparable[] comparable) {
		Arrays.sort(comparable);
		System.out.println(Arrays.toString(comparable));
	}
	public String toString() {
		return "CITYNAME: " + this.cityName+ " TEMPRATURE: "+ this.temp;
	}
}

public class CityTempDemo {

	public static void main(String[] args) {
		Comparable[] arr = new City[5];
		arr[0]=new City("NY",20.5);
		arr[1]=new City("CHG",19.5);
		arr[2]=new City("WA",20.5);
		arr[3]=new City("LA",16.5);
		arr[4]=new City("DC",12.5);
		
		City.sortArray(arr);
		
		
	}

}
