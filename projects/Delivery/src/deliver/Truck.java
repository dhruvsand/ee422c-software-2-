package deliver;

public class Truck extends Vehicle {
	
	//need something specific to truck
	private static int count = 0;
	private String idNum;
	final private double mpg = 5.0;
	
	public Truck() {
		count++;
		idNum = "T" + count;
		
		//create constants for these
		capacity = 4000;
		weight = 20000;
		fuel = 40;
	}
	
	public double calcFuelCost(double distance) {
		return distance/mpg; 
	}
	
	
	public void setBid(Job j) {
		
		double fuelCost = calcFuelCost(loc.getDriveDistance(j.getPickup()));
		fuelCost += calcFuelCost(j.getPickup().getDriveDistance(j.getDropOff()));		
		
		if(fuelCost < fuel && j.getWeight() <= capacity)
			setCurrentBid(fuelCost);
		else
			setCurrentBid(REFUSAL_BID);
		
	System.out.println(idNum + "  " + fuelCost + " " + getCurrentBid());
	}
	
	
	
	public void makeDelivery(Job j) {
		
	}
	
	public String toString() {
		return idNum + " " + loc;
	}

}
