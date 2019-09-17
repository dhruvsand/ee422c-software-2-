import java.util.Random;

//Runnable interface contains run() method
public class Foo implements Runnable {
	
  String name;
  int time;
  Random r = new Random();
  
  public Foo(String name){
      this.name = name;
      time = r.nextInt(999); //between 0-1 second
  }
  
  //this runs when you start thread
  public void run(){
      try {
          System.out.printf("%s is sleeping for %d\n", name, time);
          Thread.sleep(time); //how long do u want to sleep for?
          System.out.printf("%s is done\n", name);
      }
      catch(Exception e){}
  }
}
