package PrimeThread;

public class Main {
	public static void main(String[] args) {
		
		long num2 = 8268479;
		long num1 = 2719;

		//long num1 = 3;
		//long num2 = 2;
		
		//long num1 = 2;
		//long num2 = 4;
		
		PrimeFinder f1 = new PrimeFinder(num1);
		PrimeFinder f2 = new PrimeFinder(num2);
		
		Thread t1 = new Thread(f1);
		Thread t2 = new Thread(f2);
		
		t1.start();
		t2.start();
	
//remove...
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		
		System.out.println(num1 + " prime? " + f1.getResult());
		System.out.println(num2 + " prime? " + f2.getResult());
		
		
	}
}
