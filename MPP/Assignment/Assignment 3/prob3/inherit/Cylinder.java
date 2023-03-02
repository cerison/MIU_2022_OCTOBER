package lesson3.labsolns.prob3.inherit;

public class Cylinder  {
	private double height;
	private double radius;
	
	public Cylinder(double height, double radius) {
		this.height = height;
		this.radius = radius;
	}

	
	public double getHeight() {
		return height;
	}
	public void setHeight(double h) {
		height = h;
	}
	public double getRadius() {
		return radius;
	}
	public void setRadius(double r) {
		radius = r;
	}
	public double computeVolume() {
		return Math.PI * radius * radius * height;
	}

}
