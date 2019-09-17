package deliver;


import java.util.List;

public class Main {

	public static void main(String[] args) {
		System.out.println("hello world");
		
		Location l1 = new Location(2,2);
		Location l2 = new Location();
		
		System.out.println("drive " + l1.getDriveDistance(l2));
		System.out.println("straight " + l1.getStraightDistance(l2));
		
		try {
			for (int i = 0; i < 10; i++)
				Vehicle.makeVehicle("deliver.Truck");
			
			Vehicle.makeVehicle("deliver.Moped");
			Vehicle.makeVehicle("deliver.Truck");
			Vehicle.makeVehicle("deliver.Drone");
			
			
			//work on job validation later
			Location loc1 = new Location(2,2);
			Location loc2 = new Location(7,5);
			Job j = new Job(loc1,loc2,2);
			System.out.println(j);
			
			if (Vehicle.handleJob(j))
				System.out.println("Success");
			else
				System.out.println("fail");
			
			List<Vehicle> list = Vehicle.getVehicles();
			for (Vehicle v: list) {
				System.out.println(v);
			}
			
		}
		catch (InvalidVehicleException e) {
			
		}
		

	}

}
