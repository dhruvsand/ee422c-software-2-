package PrimeThread;

public class PrimeFinder implements Runnable {
	private long number = 0;
	boolean prime = true;
	
	public PrimeFinder(long num) {
		number = num;
	}
	
	public boolean getResult() {
		return prime;
	}

	@Override
	public void run() {
		isPrime();
	}
	
	private void isPrime() {
		if (number % 2 == 0)
			prime = false;
		else
			for (int i = 3; i <= Math.sqrt(number); i+=2) {
System.out.println(Thread.currentThread().getId() + "   " + i);
				if (number % i == 0)
					prime = false;
			}

	}
	

}
