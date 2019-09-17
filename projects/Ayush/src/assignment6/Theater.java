// insert header here
package assignment6;

import java.util.ArrayList;
import java.util.List;

public class Theater {

	List<Ticket> tickets = new ArrayList<>();



	int numRows;

	int seatsPerRow;
	String show;


	int totalsold =0;


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
			// TODO: Implement this method to return the full Seat location ex: A1
			String seat=""+seatNum;

			for(int i=rowNum;i>0;i=(i-1)/26){
				seat = ((char)(65+((i-1)%26)))+seat;
			}

			return seat;
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
			// TODO: Implement this method to return a string that resembles a ticket
			String showSeat=  "| Seat: "+seat;
			showSeat = String.format("%-30s", showSeat)+"|\n";
			String showClient=  "| Client: "+client;
			showClient = String.format("%-30s", showClient)+"|\n";
			String showStr=  "| Show: "+show;
			showStr = String.format("%-30s", showStr)+"|\n";
			String showBox=  "| Box Office ID: "+boxOfficeId;
			showBox = String.format("%-30s", showBox)+"|\n";

			String out= "-------------------------------" +"\n"
					+showStr+showBox+showSeat+showClient+
					"-------------------------------";
			return out;
		}
	}

	public Theater(int numRows, int seatsPerRow, String show) {
		// TODO: Implement this constructor
		this.numRows=numRows;
		this.seatsPerRow=seatsPerRow;
		this.show=show;
	}

	/*
	 * Calculates the best seat not yet reserved
	 *
 	 * @return the best seat or null if theater is full
   */
	public synchronized Seat bestAvailableSeat() {
		//TODO: Implement this method

		if(totalsold>= (numRows*seatsPerRow))
			return null;

		Seat seat = new Seat(totalsold/seatsPerRow+1,(totalsold%seatsPerRow)+1);
		totalsold+=1;

		return seat;

	}

	/*
	 * Prints a ticket for the client after they reserve a seat
   * Also prints the ticket to the console
	 *
   * @param seat a particular seat in the theater
   * @return a ticket or null if a box office failed to reserve the seat
   */
	public synchronized Ticket printTicket(String boxOfficeId, Seat seat, int client) {
		//TODO: Implement this method

		if(seat!=null) {
			Ticket ticket = new Ticket(show, boxOfficeId, seat, client);
			System.out.println(ticket.toString());
			tickets.add(ticket);
			return ticket;
		}
		return null;
	}

	/*
	 * Lists all tickets sold for this theater in order of purchase
	 *
   * @return list of tickets sold
   */
	public List<Ticket> getTransactionLog() {
		//TODO: Implement this method
		return tickets;
	}
	public int getSeatsPerRow() {
		return seatsPerRow;
	}

	public void setSeatsPerRow(int seatsPerRow) {
		this.seatsPerRow = seatsPerRow;
	}
	public int getTotalsold() {
		return totalsold;
	}

	public void setTotalsold(int totalsold) {
		this.totalsold = totalsold;
	}
	public int getNumRows() {
		return numRows;
	}

	public void setNumRows(int numRows) {
		this.numRows = numRows;
	}
}
