package producer_consumer;

import java.util.LinkedList;
import java.util.Queue;
	
public class ThreadSync {

	public Queue<Widget> myQ = new LinkedList<Widget>();
	
	public static void main(String[] args) {
		ThreadSync ts = new ThreadSync();
		
		Producer p = ts.new Producer();
		p.start();
		
		Consumer c = ts.new Consumer();
		c.start();
	}
	
	public class Widget {
		private String name;
			
		public Widget(int idNum) {
			name = "W" + idNum;
		}
		
		public String toString() {
			return name;
		}
	}
	
	private class Producer extends Thread{

		@Override
		public void run() {
			for (int i=0; i < 5; i++) {
				Widget w = new Widget(i);
				System.out.println("P - " + w);
				myQ.add(w);
			}	
		}
	}
	
	private class Consumer extends Thread {

		@Override
		public void run() {
			for (int i=0; i < 5; i++) {
				Widget w = myQ.peek();
				System.out.println("C - " + w);
				myQ.remove();
			}	
		}
	}

}


