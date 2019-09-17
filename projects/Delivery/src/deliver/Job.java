package deliver;

public class Job {
	private Location pickUp;
	private Location dropOff;
	
	private int weight;
	
	public Job(Location pickUp, Location destination, int weight) {
		this.pickUp = pickUp;
		dropOff = destination;

		this.weight = weight;
	}
	
	public Location getPickup() {
		return pickUp;
	}
	
	public Location getDropOff() {
		return dropOff;
	}

	public int getWeight() {
		return weight;
	}
	
	public String toString() {
		return "from: " + pickUp + " to:" + dropOff + " w" + weight;
	}

}
