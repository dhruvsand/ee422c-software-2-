package assignment4;

public class megaMind extends Critter.TestCritter{

    //Can tactically choose where to run to avoid fighting as much as possible
    //

    private static int successfulRun = 0;
    private static int brainDamage = 0;

    @Override
    public String toString(){return "M";}

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

    public static void runStats(java.util.List<Critter> rabbitCritters){
        System.out.println("Successful Run Attempts: " + successfulRun);
        System.out.println("Total brain damage: " + brainDamage);
    }




}
