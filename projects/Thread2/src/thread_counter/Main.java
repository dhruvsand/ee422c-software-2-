package thread_counter;

public class Main {

	public static void main(String[] args) {
		Counter c1 = new Counter();
		
		Thread t1 = new Thread(c1);
		Thread t2 = new Thread(c1);
		t1.start();
		t2.start();
		
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		System.out.println("result - " + c1.getCounter());
	
	}

}
