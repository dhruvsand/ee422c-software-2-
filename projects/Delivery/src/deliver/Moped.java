package deliver;

public class Moped extends Vehicle {
	
	
	private static int count = 0;
	private String idNum;
	final private double fuelUse = 0.3; //units per mile
	
	public Moped() {
		//create constants for these
		count++;
		idNum = "M" + count;
		
		capacity = 50;
		weight = 75;
		fuel = 3;
	}
	
	public void setBid(Job j) {
		
		double fuelCost = calcFuelCost(loc.getDriveDistance(j.getPickup()));
		fuelCost += calcFuelCost(j.getPickup().getDriveDistance(j.getDropOff()));		
		
		if(fuelCost < fuel && j.getWeight() < capacity)
			setCurrentBid(fuelCost);
		else
			setCurrentBid(REFUSAL_BID);
		
		System.out.println(idNum + "  " + fuelCost + " " + getCurrentBid());
	}
	
	public double calcFuelCost(double distance) {
		return distance*fuelUse;
	}
	
	public void makeDelivery(Job j) {
		
	}
	
	public String toString() {
		return idNum + " " +loc;
	}
	
}
