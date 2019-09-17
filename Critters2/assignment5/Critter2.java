package assignment5;

import javafx.scene.paint.Color;

public class Critter2 extends Critter.TestCritter{

    //Can tactically choose where to run to avoid fighting as much as possible
    //

    private static int successfulRun = 0;
    private static int brainDamage = 0;

    @Override
    public String toString(){return "2";}

    @Override
    public void doTimeStep() {
        this.setEnergy(this.getEnergy() - 1);
        brainDamage++;
    }

    @Override
    public boolean fight(String opponent) {
        int decision = this.advancedCritLook(this,2);
        if(decision == -1) {
            return true;
        }
        run(decision);
        successfulRun++;
        return false;
    }

    public static String runStats(java.util.List<Critter> rabbitCritters){
        String result ="Successful Run Attempts: " + successfulRun+"\n";
        result+="Total brain damage: " + brainDamage+"\n";
        return result;
    }

    @Override
    public CritterShape viewShape() { return CritterShape.TRIANGLE; }

    @Override
    public javafx.scene.paint.Color viewOutlineColor() { return Color.YELLOW; }


}
