package assignment6;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BoxOffice implements Runnable {

    String Boxofficeid;
    int total_clients;
    Theater theater;
    static AtomicInteger atomicInteger = new AtomicInteger(1);
    static Lock lock = new ReentrantLock();

    public BoxOffice(String Boxofficeid, int total_clients, Theater theater){
        this.Boxofficeid =Boxofficeid;
        this.total_clients=total_clients;
        this.theater=theater;
    }

    @Override
    public  void run() {
        for (int i=0; i<total_clients;i++){
            lock.lock();
                if (theater.getTotalsold() < (theater.getNumRows() * theater.getSeatsPerRow())) {

                    theater.printTicket(Boxofficeid, theater.bestAvailableSeat(), atomicInteger.getAndIncrement());
                } else
                    break;

            lock.unlock();

            try {
                Random random = new Random();
                Thread.sleep(random.nextInt(500));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
