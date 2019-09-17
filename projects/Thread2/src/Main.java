
public class Main {

	public static void main(String[] args) {

		Thread t1 = new Thread(new Foo("one"));
		Thread t2 = new Thread(new Foo("two"));
		Thread t3 = new Thread(new Foo("three"));
		Thread t4 = new Thread(new Foo("four"));
		
		t1.start();
		t2.start();
		t3.start();
		t4.start();

	}

}
