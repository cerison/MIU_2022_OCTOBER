package assignment4_Lab3;

class Triangle {
	private double base;
	private double height;
	
	public Triangle(double base, double height) {
		this.base = base;
		this.height = height;
	}

	public double getBase() {
		 return base;
	}
	public double getHeigth() {
		 return height;
	}
	public double computeArea() {
		return (base*height)/2;
	}
}

class Rectangle {
	private double width;
	private double length;
	
	public Rectangle(double width, double length) {
		this.width = width;
		this.length = length;
	}
	public double getWidth() {
		 return width;
	}
	public double getLength() {
		 return length;
	}
	public double computeArea() {
		return width*length;
	}
}

class Circle {
	private double radius;
	
	public Circle(double radius) {
		this.radius = radius;
	}
	public double getRadius() {
		 return radius;
	}
	public double computeArea() {
		return 3.14*radius*radius;
	}
}
public class TestShape {

	public static void main(String[] args) {
		Triangle tr = new Triangle(3,5);
		Rectangle rt = new Rectangle(5,8);
		Circle cr = new Circle(5);
	    System.out.println("The Area of Tringle is "+tr.computeArea());
	    System.out.println("The Area of Rectangle is "+rt.computeArea());
	    System.out.println("The Area of Circle is "+cr.computeArea());
	}
}
