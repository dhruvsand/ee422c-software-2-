package assignment4;

public class Jesus extends Critter.TestCritter{

    //Does not reproduce
    //Resurrects once if it dies while running

    private static int resurrection = 0;
    private boolean rFlag = false;

    @Override
    public String toString(){return "J";}

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

    public static void runStats(java.util.List<Critter> disciples){
        System.out.println("Amount of times resurrected: " + resurrection);
    }

}
