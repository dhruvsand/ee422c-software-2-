
package polymorphism;

public class Car {


    public double speed, costPerMile;
    public String name;
    private double distanceTravelled;


    public void Car(){

        speed=0;
        costPerMile=0;
        name="";



    }
    public void Car(double cost, double velocity, String nameSupplied){

        costPerMile=cost;
        speed= velocity;
        name=nameSupplied;



    }


    protected void setDistanceTravelled(double distanceTravelled1){

        distanceTravelled=distanceTravelled1;

    }
    protected double getDistanceTravelled(){

        return distanceTravelled;

    }





    public void run(double time){

        distanceTravelled+=time*speed;

    }

    public double getTotalCost(){

        return  costPerMile*distanceTravelled;

    }



}
