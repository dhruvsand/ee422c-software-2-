// insert header here
package assignment6;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Theater {

    private int numRows, seatsPerRow, tickits_sold, total_tickits;
    private String show;
    int[][] seats;
    List<Ticket> log = new ArrayList<Ticket>();

    private int soldout=0;
	/*
	 * Represents a seat in the theater
	 * A1, A2, A3, ... B1, B2, B3 ...
	 */
	static class Seat {
		private int rowNum;
		private int seatNum;

		public Seat(int rowNum, int seatNum) {
			this.rowNum = rowNum;
			this.seatNum = seatNum;
		}

		public int getSeatNum() {
			return seatNum;
		}

		public int getRowNum() {
			return rowNum;
		}

		@Override
		public String toString() {
			String out="";
			int rows= rowNum;

			while(rows>0){

			    out= (char)('A'+(rows-1)%26)+out;
			    rows= (rows-1)/26;
            }
                return out+seatNum;
		}

	}

  /*
	 * Represents a ticket purchased by a client
	 */
	static class Ticket {
		private String show;
		private String boxOfficeId;
		private Seat seat;
	  private int client;

		public Ticket(String show, String boxOfficeId, Seat seat, int client) {
			this.show = show;
			this.boxOfficeId = boxOfficeId;
			this.seat = seat;
			this.client = client;
		}

		public Seat getSeat() {
			return seat;
		}

		public String getShow() {
			return show;
		}

		public String getBoxOfficeId() {
			return boxOfficeId;
		}

		public int getClient() {
			return client;
		}

		@Override
		public String toString() {

            String str1=  "| Show: "+getShow();
            str1 = String.format("%-30s", str1);
            str1+="|\n";
            String str2=  "| Box Office ID: "+getBoxOfficeId();
            str2 = String.format("%-30s", str2);
            str2+="|\n";
            String str3=  "| Seat: "+getSeat();
            str3 = String.format("%-30s", str3);
            str3+="|\n";
            String str4=  "| Client: "+getClient();
            str4 = String.format("%-30s", str4);
            str4+="|\n";


            String out= "-------------------------------" +"\n"+str1+str2+str3+str4+
                        "-------------------------------";
            return out;
		}
	}

	public Theater(int numRows, int seatsPerRow, String show) {

        this.numRows=numRows;
        this.seatsPerRow=seatsPerRow;
        this.show=show;
        total_tickits= numRows*seatsPerRow;
        tickits_sold=0;
        seats = new int[numRows][seatsPerRow];

	}

	/*
	 * Calculates the best seat not yet reserved
	 *
 	 * @return the best seat or null if theater is full
   */
	public Seat bestAvailableSeat() {

//        int row = (tickits_sold)/seatsPerRow +1;
//        int seatnum= (tickits_sold)%seatsPerRow  +1;
//        if(row>numRows)
//        return null;
//
//        Seat seat = new Seat(row,seatnum);
//
//        return seat;

		synchronized (this) {
			if (tickits_sold >= total_tickits)
				return null;

			for (int i = 0; i < numRows; i++) {
				for (int j = 0; j < seatsPerRow; j++) {
					if (seats[i][j] == 0) {
						seats[i][j] = 1;
						tickits_sold = tickits_sold + 1;
						if (tickits_sold >= total_tickits)
							soldout = 1;
						return new Seat(i + 1, j + 1);
					}
				}
			}

			return null;
		}
	}

	/*
	 * Prints a ticket for the client after they reserve a seat
   * Also prints the ticket to the console
	 *
   * @param seat a particular seat in the theater
   * @return a ticket or null if a box office failed to reserve the seat
   */
	public  Ticket  printTicket(String boxOfficeId, Seat seat, int client) {
synchronized (this) {
//        if(tickits_sold>=total_tickits)
//        return null;
//
//        if(seats[seat.getRowNum()-1][seat.getSeatNum()-1]==1)
//            return null;
//
//        tickits_sold=tickits_sold+1;
//        seats[seat.getRowNum()-1][seat.getSeatNum()-1]=1;

	if (seat == null)
		return null;

	Theater.Ticket ticket = new Theater.Ticket(show, boxOfficeId, seat, client);
	log.add(ticket);
	System.out.println(ticket.toString());
//        try {
//            Random random = new Random();
//            Thread.sleep(random.nextInt(500));
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
	return ticket;
}

	}

	/*
	 * Lists all tickets sold for this theater in order of purchase
	 *
   * @return list of tickets sold
   */
	public List<Ticket> getTransactionLog() {

        return log;
	}
	public int getSoldout(){

	    return soldout;

    }
}
