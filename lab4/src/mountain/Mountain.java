package mountain;

import java.util.HashMap;

import fractal.Fractal;
import fractal.TurtleGraphics;

public class Mountain extends Fractal {
	private Point p1;
	private Point p2;
	private Point p3;
	private double dev;
	HashMap<Side, Point> hashMap = new HashMap<Side, Point>();
	
	public Mountain(Point p1, Point p2, Point p3, double dev) {
		super();
		this.p1 = p1;
		this.p2 = p2;
		this.p3 = p3;
		this.dev = dev;
	}

	@Override
	public String getTitle() {
		return "Bergmassiv";
	}

	@Override
	public void draw(TurtleGraphics turtle) {
		turtle.moveTo(p1.getX(), p1.getY());
		fractalTriangle(turtle, order, p1, p2, p3, dev);
	}
	
	/* 
	 * Recursive method: Draws a recursive line. 
	 */
	private void fractalTriangle(TurtleGraphics turtle, int order, Point p1, Point p2, Point p3, double dev) {
		if (order == 0) {
			turtle.moveTo(p1.getX(), p1.getY());
			
			// Ritar en triangel med hörn p1, p2, p3.
			turtle.forwardTo(p2.getX(), p2.getY());
			turtle.forwardTo(p3.getX(), p3.getY());
			turtle.forwardTo(p1.getX(), p1.getY());
		} else {
			Side s1 = new Side(p1, p2);
			Side s2 = new Side(p2, p3);
			Side s3 = new Side(p3, p1);
			
			// Räknar ut de tre mittpunkterna som justeras i y-led
			Point a = calculatePoint(p1, p2, s1, dev);
			Point b = calculatePoint(p2, p3, s2, dev);
			Point c = calculatePoint(p3, p1, s3, dev);
			
			hashMap.put(s1, a);
			hashMap.put(s2, b);
			hashMap.put(s3, c);
			
			// Ritar fyra nya trianglar i den triangeln man är i.
			fractalTriangle(turtle, order-1, p1, a, c, dev/2);
			fractalTriangle(turtle, order-1, a, p2, b, dev/2);
			fractalTriangle(turtle, order-1, b, p3, c, dev/2);
			fractalTriangle(turtle, order-1, c, a, b, dev/2);
		}
	}
	
	private Point calculatePoint(Point p1, Point p2, Side s, double dev) {
		if(hashMap.containsKey(s)) {
			return hashMap.remove(s);
		} else {
			int x = p1.getX() + (p2.getX() - p1.getX())/2;
			int y = p1.getY() + (p2.getY() - p1.getY())/2 + (int)RandomUtilities.randFunc(dev);
			return new Point(x,y);
		}
		
	}
	
}