package mountain;

public class Side {
	Point p1;
	Point p2;
	
	public Side(Point p1, Point p2) {
		this.p1 = p1;
		this.p2 = p2;
	}
	
	public boolean equals(Object o) {
		Side q = (Side)o;
		
		if( (p1 == q.p1 && p2 == q.p2) || (p1 == q.p2 && p2 == q.p1) ) {
			return true;
		}
		
		return false; 
	}
	
	public int hashCode() {
		return p1.hashCode() + p2.hashCode();
	}
}
