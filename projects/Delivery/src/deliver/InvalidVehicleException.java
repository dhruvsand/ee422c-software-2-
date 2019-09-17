package deliver;

public class InvalidVehicleException extends Exception {
	String offending_class;
	
	public InvalidVehicleException(String vehicle_class_name) {
		offending_class = vehicle_class_name;
	}
	
	public String toString() {
		return "Invalid Vehicle Class: " + offending_class;
	}
}
