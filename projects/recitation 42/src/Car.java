public class Car {

    private String name;
    private int velocity;


    public Car(String n, int vel){
        name =n;
        velocity=vel;


    }

    @Override
    public String toString() {
        return name + " "+velocity;
    }


}
