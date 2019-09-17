// Insert header here

package assignment6;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.lang.Thread;



import assignment6.Theater;


public class BookingClient {
    Map<String, Integer> office;
    Theater theater;
    List<Thread> threads = new ArrayList<>();

    /*
     * @param office maps box office id to number of customers in line
     * @param theater the theater where the show is playing
     */
    public BookingClient(Map<String, Integer> office, Theater theater) {

        this.office =office;
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


        int client_num=1;
        for (String key : office.keySet()){
            int value = office.get(key);
            List<Client> list = new ArrayList<>();
            for (int i=0;i<value;i++){

                list.add(new Client(client_num));
                client_num++;
            }
            Thread thread= new Thread(new BoxOffice(key,list,value,theater));
            threads.add(thread);
        }

        for (int i=0;i<threads.size();i++)
            threads.get(i).start();

//        threads.get(0).run();
//        threads.get(1).run();
//        threads.get(2).run();
//        threads.get(3).run();
//        threads.get(4).run();

        for (int i=0;i<threads.size();i++){
            while (threads.get(i).isAlive());
        }
        System.out.println();
        if(theater.getSoldout()==1)
            System.out.println("Sorry, we are sold out!");

        return threads;
    }

    public static void main(String[] args) {


        Theater theater = new Theater(500, 2, "Ouija");
        Map<String, Integer> office= new HashMap<>();
        office.put("B01",400);
        office.put("B02",600);
        //office.put("BX2",4);
        //office.put("BX5",3);
        //office.put("BX4",3);

        BookingClient b= new BookingClient(office,theater);
        b.simulate();



        //Theater.Seat seat = new Theater.Seat(2, 2);
        //Theater.Ticket ticket= new Theater.Ticket("Ouija","BX1",seat,4);
//        System.out.println(ticket.toString());
//        for (int i = 1; i <= 1000; i++) {
//            //seat.setRowNum(i);
//            //System.out.println(seat.toString());
//            //System.out.println(seat.getRowNum());
//        }



    }
}