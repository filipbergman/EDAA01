package mountain;

import fractal.Fractal;
import fractal.TurtleGraphics;

public class Mountain extends Fractal {
	private Point p1;
	private Point p2;
	private Point p3;
	private double dev;
	
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
		fractalLine(turtle, order, p1, p2, p3, dev);
	}
	
	/* 
	 * Recursive method: Draws a recursive line. 
	 */
	private void fractalLine(TurtleGraphics turtle, int order, Point p1, Point p2, Point p3, double dev) {
		if (order == 0) {
			turtle.moveTo(p1.getX(), p1.getY());
			
			// Ritar en triangel med hörn p1, p2, p3.
			turtle.forwardTo(p2.getX(), p2.getY());
			turtle.forwardTo(p3.getX(), p3.getY());
			turtle.forwardTo(p1.getX(), p1.getY());
		} else {
			int x1 = p1.getX() + ((p2.getX() - p1.getX())/2);
			int y1 = p1.getY() + ((p2.getY() - p1.getY())/2) + (int)RandomUtilities.randFunc(dev);
			Point a = new Point(x1,y1);
			
			int x2 = p2.getX() + ((p3.getX() - p2.getX())/2);
			int y2 = p2.getY() + ((p3.getY() - p2.getY())/2) + (int)RandomUtilities.randFunc(dev);
			Point b = new Point(x2,y2);
			
			int x3 = p3.getX() + ((p1.getX() - p3.getX())/2);
			int y3 = p3.getY() + ((p1.getY() - p3.getY())/2) + (int)RandomUtilities.randFunc(dev);
			Point c = new Point(x3,y3);
			
			// Ritar fyra nya trianglar i den triangeln man är i.
			fractalLine(turtle, order-1, p1, a, c, dev/2);
			fractalLine(turtle, order-1, a, p2, b, dev/2);
			fractalLine(turtle, order-1, b, p3, c, dev/2);
			fractalLine(turtle, order-1, c, a, b, dev/2);
		}
	}
	
}