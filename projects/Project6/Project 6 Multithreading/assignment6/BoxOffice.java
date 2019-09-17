package assignment6;

import java.util.List;
import java.util.Random;

public class BoxOffice implements Runnable {

    String Boxofficeid;
    List<Client> line;
    int total_clients;
    Theater theater;

    public BoxOffice(String Boxofficeid, List<Client> line,int total_clients, Theater theater){
        this.Boxofficeid =Boxofficeid;
        this.line =line;
        this.total_clients=total_clients;
        this.theater=theater;
    }

    @Override
    public  void run() {
        for (int i=0; i<line.size();i++){
            if(theater.getSoldout()==0){
                synchronized (theater){
                theater.printTicket(Boxofficeid,theater.bestAvailableSeat(),line.get(i).getClient_number());}}
            else
                break;


            try {
                Random random = new Random();
                Thread.sleep(random.nextInt(500));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }
}
