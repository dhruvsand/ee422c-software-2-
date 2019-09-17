package deliver;

public class Drone extends Vehicle {
	
	//anything different about the drone?
	private static int count = 0;
	private String idNum;
	final private double fuelUse = 0.3; //units per mile
	
	public Drone() {
		//create constants for these
		count++;
		idNum = "D" + count;
		
		capacity = 3;
		weight = 25;
		fuel = 5;
	}
	
	public void setBid(Job j) {
		
		double fuelCost = calcFuelCost(loc.getStraightDistance(j.getPickup()));
		fuelCost += calcFuelCost(j.getPickup().getStraightDistance(j.getDropOff()));		
		
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
		return idNum + " " + loc;
	}
	

}
