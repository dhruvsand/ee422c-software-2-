

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        int[] arr= {1,2,3,4,5,6,7,8,9,10};

        System.out.println(sum(5,arr));
        ReverseGreeting r1= new ReverseGreeting(49,1);

        Thread t = new Thread(r1);
        t.start();
    }

    public static int sum(int n,int[] a) {  // many threads version
        int threadCount = n;   // what number is best?
        int len = (int) Math.ceil(1.0 * a.length / threadCount);
        SumArray[] summers = new SumArray[threadCount];
        Thread[] threads = new Thread[threadCount];
        for (int i = 0; i < threadCount; i++) {
            summers[i] = new SumArray(a, i * len, (i + 1) * len);
            threads[i] = new Thread(summers[i]);
            threads[i].start();
        }
        try {
            for (Thread t : threads) {
                t.join();
            }
        } catch (InterruptedException ie) {
        }

        int total = 0;
        for (SumArray summer : summers) {
            total += summer.getSum();
        }
        return total;
    }
}