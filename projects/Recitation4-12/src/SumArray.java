public class SumArray implements Runnable {

    private int[] a;
    private int min, max, sum;

    public SumArray(int[] a, int min, int max) {
        this.a = a;
        this.min = min;
        this.max = Math.min(max, a.length);
    }

    public int getSum() {
        return sum;
    }

    public void run() {
        sum = sumRange(a, min, max);
    }



    public static int sumRange(int[] a, int min, int max) {
        int total = 0;
        for (int i = min; i < max; i++) {
            total += a[i];
        }
        return total;
    }

}
