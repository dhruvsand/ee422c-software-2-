public class ReverseGreeting implements Runnable {


    private static int threadsalive=0;
    private int totalcount, currentcount;

    public ReverseGreeting(int total, int current) {
        totalcount=total;
        currentcount=current;
        threadsalive++;


    }


    public void run() {
        if(currentcount>totalcount){
            System.out.println("Hello from Thread "+currentcount);

        return;
        }
        else {
        ReverseGreeting r1= new ReverseGreeting(49,currentcount+1);

        Thread t = new Thread(r1);
        t.start();
        while (t.isAlive());

        System.out.println("Hello from Thread "+currentcount);

        return;}

    }
}
