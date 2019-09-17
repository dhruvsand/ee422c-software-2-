package producer_consumer2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
	
public class ThreadSync {
	
	protected myQueue mq = new myQueue();

	public static void main(String[] args) {
		ThreadSync ts = new ThreadSync();
		
		Producer p = ts.new Producer();
		p.start();
		
		Consumer c = ts.new Consumer();
		c.start();
	}
	
	public class myQueue {
		private Queue<Widget> myQ = new LinkedList<Widget>();
		boolean getFlag = false;
		
		public synchronized void put(Widget w)
		{
			while (getFlag) {
				try {wait(); } catch(Exception e) {}
			}
			System.out.println("put - " + w);
			myQ.add(w);
			getFlag = true;
			notify();
		}
		
		public synchronized Widget get() {
			while (!getFlag) {
				try {wait();} catch(Exception e) {}
			}
			Widget w = myQ.peek();
			System.out.println("get - " + w);
			myQ.remove();
			getFlag = false;
			notify();
			return w;
		}
		
		
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
			int i = 0;
			while (true) {
				Widget w = new Widget(i);
				mq.put(w);
				i++;
				try {
					sleep(1100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}	
		}
	}
	
	private class Consumer extends Thread {

		@Override
		public void run() {
			while (true) {
				Widget w = mq.get();
				try {
					sleep(300);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}	
		}
	}

}