
package polymorphism;

public class RaceCar extends polymorphism.Car{

    public double acceleration;

    public void RaceCar(){
        acceleration=0;


    }

    public void RaceCar(double cost,double velocity, double acc, String nameProvided){


        costPerMile=cost;
        speed= velocity;
        name=nameProvided;
        acceleration=acc;

    }

    public void run(double time){

     double   distanceTravelled=getDistanceTravelled();
        distanceTravelled +=time*speed+0.5*acceleration*time*time;
        setDistanceTravelled(distanceTravelled);

    }









}
