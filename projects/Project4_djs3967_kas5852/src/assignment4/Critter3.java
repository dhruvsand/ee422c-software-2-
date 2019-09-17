package assignment4;

public class Critter3 extends Critter.TestCritter{

    private static int rabbitBabies = 0;

    @Override
    public void doTimeStep() {
        Critter3 r = new Critter3();
        if(this.getEnergy() >= Params.min_reproduce_energy) {
            reproduce(r, getRandomInt(8));
            this.setEnergy(this.getEnergy()*2);
            reproduce(r, getRandomInt(8));
            rabbitBabies += 2;
        }

    }

    @Override
    public boolean fight(String opponent) {
        run(getRandomInt(8));
        return false;
    }

    @Override
    public String toString () {
        return "3";
    }

    public static void runStats(java.util.List<Critter> rabbitCritters){
        System.out.println("Number of babies produced: " + rabbitBabies);
    }

}
