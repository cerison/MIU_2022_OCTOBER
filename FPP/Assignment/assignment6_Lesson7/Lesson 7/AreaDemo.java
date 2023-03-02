package assignment6;

// Interface Shape =========================================================================================================
interface Shape {
	public double area();
}

// Circle implements Shape ==================================================================================================
class Circle implements Shape {
	private double radius;

	public Circle(double radius) {
		this.radius = radius;
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

	@Override
	public double area() {
		return Math.PI * (radius * radius);
	}
}

// Class Rectangle ==========================================================================================================
class Rectangle implements Shape {
	private double length;
	private double width;

	public Rectangle(double length, double width) {
		this.length = length;
		this.width = width;
	}

	public double getLength() {
		return length;
	}

	public void setLength(double length) {
		this.length = length;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	@Override
	public double area() {
		return length * width;
	}

}

public class AreaDemo {

	public static void main(String[] args) {
		Circle c = new Circle(4);
		Rectangle r = new Rectangle(4, 3);
		ShowArea(c);
		ShowArea(r);
	}

	public static void ShowArea(Shape s) {
		double area = s.area();
		System.out.println("The area of the shape is " + area);

	}

}
