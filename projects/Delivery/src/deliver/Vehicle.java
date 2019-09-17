package deliver;

import java.util.ArrayList;
import java.util.List;


public abstract class Vehicle {
	private static String myPackage;
	
	private static List<Vehicle> fleet = new ArrayList<Vehicle>();
	
	protected static final int REFUSAL_BID = -1;
	
	public Vehicle() {
		loc = new Location(StartVals.startX, StartVals.startY);
	}
	
	protected Location loc;
	
	protected double tankSize;
	protected double fuel;
	protected int weight;
	
	protected int capacity;
	
	private double currentBid;
	
	//gets the second argument of the string
	static {
		myPackage = Vehicle.class.getPackage().toString().split(" ")[1];
	}
	
	public double getCurrentBid() {
		return currentBid;
	}
	
	protected void setCurrentBid(double bid) {
		currentBid = bid;
	}
	
	public String toString() {
		return "";
	}
	
	public Location getLocation() {
		return loc;
	}
	
	protected final void move(Location Loc2) {
		
	}
	
	public abstract double calcFuelCost(double distance);
	
	//a negative bid is a refusal
	public abstract void setBid(Job j);
	
	public abstract void makeDelivery(Job j);
	
	public static boolean handleJob(Job j) {
		
		boolean success = false;
		
		for (Vehicle v: fleet) {
			v.setBid(j);
		}
		
		double lowBid = Integer.MAX_VALUE;
		Vehicle best = null;
		for(Vehicle v: fleet) {
			if (v.currentBid < lowBid && v.getCurrentBid() > 0) {
				lowBid = v.currentBid;
				best = v;
			}
		}
		
		if (best != null && best.getCurrentBid() >= 0) { //REFUSAL_BID = -1
			best.makeDelivery(j);
	System.out.println(best);
			success = true;
		}
		
		return success;
		
	}
	
	
	public static void makeVehicle (String vehicle_class_name) throws InvalidVehicleException {
		try {
			Class <?> c = Class.forName(vehicle_class_name);
			
			Vehicle v = (Vehicle) c.newInstance();
			
			fleet.add(v);
			
		}
		catch (ClassNotFoundException e)
		{
			throw new InvalidVehicleException(vehicle_class_name);
		}
		catch (IllegalAccessException e) {
			throw new InvalidVehicleException(vehicle_class_name);
			
		}
		catch (InstantiationException e) {
			throw new InvalidVehicleException(vehicle_class_name);
			
		}
	}
	
	public static List<Vehicle> getVehicles() {
		List<Vehicle> result = new java.util.ArrayList<Vehicle>();
		
		for (Vehicle v: fleet) {
			//Vehicle v = new Vehicle(c);
			result.add(v);			
		}
	
		return result;
	}
	
	public static List<Vehicle> getInstances(String vehicle_class_name) throws InvalidVehicleException {
		List<Vehicle> result = new java.util.ArrayList<Vehicle>();
		
		for (Vehicle c: fleet) {
			if (c.getClass().getName().equalsIgnoreCase(vehicle_class_name))
				result.add(c);			
		}
	
		return result;
	}
	
	public static void displayWorld() {
	
	}

}
