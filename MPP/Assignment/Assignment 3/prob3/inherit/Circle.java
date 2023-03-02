package lesson3.labsolns.prob3.inherit;
/**
 * 
 */

/**
 * Circle inherits a computeVolume method that
 * is not useful.
 * 
 * Circle inherits the setHeight method. This
 * means a circle can acquire a nonzero height. (Bad)
 *
 */
public class Circle extends Cylinder {
	
	public Circle(double radius){
		super(radius, 0);
	}
	
	public double computeArea() {
		return getRadius() * getRadius() * Math.PI;
	}
	
	

}
