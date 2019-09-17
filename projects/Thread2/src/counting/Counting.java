package counting;

public class Counting {
	public static void main(String [] args) {
		System.out.println("Starting");
		CountingThread ct1 = new CountingThread(1, 10);
		CountingThread ct2 = new CountingThread(11, 20);
		CountingThread ct3 = new CountingThread(21, 30);
		ct1.start();
		ct2.start();
		ct3.start();
		System.out.println("Ending");
	}
}

class CountingThread extends Thread {
	private int start;
	private int end;
	public CountingThread(int start, int end) {
		this.start = start;
		this.end = end;
	}
	public void run() {
		for (int i=start; i <= end; i++) {
			System.out.println(i + " ");
		}
	}
}
