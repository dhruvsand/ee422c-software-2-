package assignment5;

import javafx.scene.paint.Color;

public class Critter4 extends Critter.TestCritter {

    //Gets +10 energy every turn
    //Helps it avoid fights

    private static int totalEnergy = 0;
    private static int fightsAvoided = 0;
    private static int fights = 0;
    @Override
    public void doTimeStep() {
        run(getRandomInt(8));
        this.setEnergy(this.getEnergy() + 10);
        totalEnergy++;
    }

    @Override
    public boolean fight(String oponent) {
       if(this.getEnergy() < 100){
           this.fightsAvoided++;
           run(getRandomInt(8));
           return false;
       }
       fights++;
       return true;
    }

    @Override
    public String toString(){return "4";}

    public static String runStats(java.util.List<Critter> EnergyCritter){
        String result="Fights avoided: " + fightsAvoided+"\n";
        result+="Fights completed: " + fights+"\n";
        result+="Total Energy Gathered: " + totalEnergy+"\n";
        return result;
    }

    @Override
    public CritterShape viewShape() { return CritterShape.STAR; }

    @Override
    public javafx.scene.paint.Color viewOutlineColor() { return Color.GREEN; }
}
