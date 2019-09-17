package deliver;


public class Location {
	private int x;
	private int y;
	
	public Location() {
		x = 0;
		y = 0;
	}
	
	public Location (int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public String toString() {
		return "Location: " + x + " " + y;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	//returns straightline distance
	public double getStraightDistance(Location loc2) {
		double dist;
		
		dist = Math.sqrt(Math.pow((x-loc2.x),2) + Math.pow(y-loc2.y, 2));

		return (dist);
	}
	
	//no diagonal driving
	public double getDriveDistance(Location loc2) {
		return (Math.abs(x-loc2.x) + Math.abs(y-loc2.x));
	}

}
