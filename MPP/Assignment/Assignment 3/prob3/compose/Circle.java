package lesson3.labsolns.prob3.compose;

public class Circle {
	private Cylinder cylinder;
	public Circle(double radius) {
		cylinder = new Cylinder(radius, 0);
	}
	public double getRadius() {
		return cylinder.getRadius();
	}
	public double computeArea() {
		return cylinder.getRadius() * cylinder.getRadius() * Math.PI;
	}
}
