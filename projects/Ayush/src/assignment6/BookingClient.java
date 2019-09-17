// Insert header here
package assignment6;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.lang.Thread;

public class BookingClient {

    Map<String, Integer> office;
    Theater theater;
  /*
   * @param office maps box office id to number of customers in line
   * @param theater the theater where the show is playing
   */
  public BookingClient(Map<String, Integer> office, Theater theater) {
    // TODO: Implement this constructor
      this.office=office;
      this.theater=theater;
  }

  /*
   * Starts the box office simulation by creating (and starting) threads
   * for each box office to sell tickets for the given theater
   *
   * @return list of threads used in the simulation,
   *         should have as many threads as there are box offices
   */
	public List<Thread> simulate() {
		//TODO: Implement this method
        List<Thread> threads = new ArrayList<>();

        for (String officeID : office.keySet()){
            int client_num = office.get(officeID);
            Thread thread= new Thread(new BoxOffice(officeID,client_num,theater));
            threads.add(thread);
        }

        for (Thread thread:threads)
            thread.start();

        for (Thread thread:threads) {
            while (thread.isAlive());
        }


        System.out.println();


        if(theater.getTotalsold()>=(theater.getNumRows()*theater.getSeatsPerRow()))
            System.out.println("Sorry, we are sold out!");

        return threads;
	}

	public static void main(String[] args){
        Map<String, Integer> office= new HashMap<>();
        office.put("BX1",3);
        office.put("BX3",3);
        office.put("BX2",4);
        office.put("BX5",3);
        office.put("BX4",3);
        Theater theater = new Theater(3, 5, "Ouija");

        BookingClient bookingClient= new BookingClient(office,theater);
        bookingClient.simulate();
    }
}
