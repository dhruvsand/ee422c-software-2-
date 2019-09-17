package thread_counter;

public class Counter implements Runnable {
	private int counter = 0;
	
	public int getCounter() {
		return counter;
	}

	@Override
	public void run() {
		for (int i=0; i < 10000; i++)
			synchronized (this) {
				counter++; //counter = counter + 1;
			}

	}
	

}
