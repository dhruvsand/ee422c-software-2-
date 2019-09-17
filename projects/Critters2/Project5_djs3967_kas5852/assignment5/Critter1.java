package assignment5;

import javafx.scene.paint.Color;

public class Critter1 extends Critter.TestCritter{

    //Does not reproduce
    //Resurrects once if it dies while running

    private static int resurrection = 0;
    private boolean rFlag = false;

    @Override
    public String toString(){return "1";}

    @Override
    public void doTimeStep() {
        walk(getRandomInt(8));
    }

    @Override
    public boolean fight(String opponent) {
        if(this.getEnergy() > 100) return true;
        run(getRandomInt(8));
        if(!this.rFlag && this.getEnergy() <= 0){
            this.setEnergy(10);
            resurrection++;
            this.rFlag = true;
        }
        return false;
    }

    public static String runStats(java.util.List<Critter> disciples){
        return "Amount of times resurrected: " + resurrection+"\n";
    }

    @Override
    public CritterShape viewShape() { return CritterShape.CIRCLE; }

    @Override
    public javafx.scene.paint.Color viewOutlineColor() { return Color.RED; }

}
