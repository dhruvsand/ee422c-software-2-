package assignment4;
/* CRITTERS Critter.java
 * EE422C Project 4 submission by
 * Replace <...> with your actual data.
 * Dhruv Sandesara
 * djs3967
 * 15455
 * Karim Sabar
 * kas5852
 * 15455
 * Slip days used: <0>
 * Fall 2016
 */


import java.util.List;

/* see the PDF for descriptions of the methods and fields in this class
 * you may add fields, methods or inner classes to Critter ONLY if you make your additions private
 * no new public, protected or default-package code or data can be added to Critter
 */


public abstract class Critter {
	private static String myPackage;
	private	static List<Critter> population = new java.util.ArrayList<Critter>();
	private static List<Critter> babies = new java.util.ArrayList<Critter>();
	private boolean mFlag = false;
	private boolean lookFlag = false;
//	private static String[][] arr = new String[Params.world_height+2][Params.world_width+2];



	// Gets the package name.  This assumes that Critter and its subclasses are all in the same package.
	static {
		myPackage = Critter.class.getPackage().toString().split(" ")[1];
	}
	
	private static java.util.Random rand = new java.util.Random();
	public static int getRandomInt(int max) {
		return rand.nextInt(max);
	}
	
	public static void setSeed(long new_seed) {
		rand = new java.util.Random(new_seed);
	}
	
	
	/* a one-character long string that visually depicts your critter in the ASCII interface */
	public String toString() { return ""; }
	
	private int energy = 0;
	protected int getEnergy() { return energy; }
	
	private int x_coord;
	private int y_coord;
	/**
	 * Lets us know if it is safe to move in this direction or is it occupied
	 * @param speed This is how many units you want to move
	 * @param dir This is the direction you want to go in
	 * @param c This is the critter whoes position you want to change
	 * @return int dir This is whether if it is valid to move in that direction
	 */
	private static int critLook(Critter c, int speed, int dir){
		int orig_index = population.indexOf(c);

			Critter s = movement(speed, dir, c);

			for (int i = 0; i < population.size(); i++) {
				if((s.x_coord == population.get(i).x_coord && s.y_coord == population.get(i).y_coord)&&(i!=orig_index)&&(population.get(i).energy>0)){
					return -1;
				}
			}
			return dir;


	}



	/**
	 * Does a movemtn of the specified critter in the specified direction for specified units
	 * @param i This is how many units you want to move
	 * @param direction This is the direction you want to go in
	 * @param c This is the critter whoes position you want to change
	 * @return c This is the critter with the changed position
	 */
	private static Critter movement(int i, int direction, Critter c){
		if(direction == 0) c.x_coord += i;
		if(direction == 1) c.x_coord+=i; c.y_coord-=i;
		if(direction == 2) c.y_coord-=i;
		if(direction == 3) c.x_coord-=i; c.y_coord-=i;
		if(direction == 4) c.x_coord-=i;
		if(direction == 5) c.x_coord-=i; c.y_coord+=i;
		if(direction == 6) c.y_coord+=i;
		if(direction == 7) c.x_coord+=i; c.y_coord+=i;

//		if(c.x_coord > Params.world_width - 1) c.x_coord -= Params.world_width - 1;
//		if(c.y_coord > Params.world_height - 1) c.y_coord -= Params.world_height -1;

		c.x_coord= (c.x_coord+Params.world_width)%Params.world_width;
		c.y_coord= (c.y_coord+Params.world_height)%Params.world_height;
		return c;

	}
	/**
	 * Checks to walk 1 units away
	 * @param direction which direction you want your children to go to
	 */
	protected final void walk(int direction) {
		this.energy -= Params.walk_energy_cost;
		if(!this.mFlag && !lookFlag){
			Critter c = movement(1, direction, this);
			this.x_coord = c.x_coord;
			this.y_coord = c.y_coord;
			this.mFlag = true;
		}
		else if(!this.mFlag && lookFlag){
			if(critLook(this, 1,direction)!= -1){
				Critter c = movement(1, direction, this);
				this.x_coord = c.x_coord;
				this.y_coord = c.y_coord;
				this.lookFlag = false;
				this.mFlag = true;
			}
			this.lookFlag = true;
		}

	}
	/**
	 * Checks to run 2 units away
	 * @param direction which direction you want your children to go to
	 */
	protected final void run(int direction) {
		this.energy -= Params.run_energy_cost;
		if(!this.mFlag && !lookFlag){
			Critter c = movement(2, direction, this);
			this.x_coord = c.x_coord;
			this.y_coord = c.y_coord;
			this.mFlag = true;
		}
		else if(!this.mFlag && lookFlag){
			if(critLook(this, 2,direction)!= -1){
				Critter c = movement(2, direction, this);
				this.x_coord = c.x_coord;
				this.y_coord = c.y_coord;
				this.lookFlag = false;
				this.mFlag = false;
			}
			this.lookFlag = false;
		}
	}
	/**
	 * Adds the baby to to its direction
	 * @param offspring Wwho the offspring is who is supposed to be added.
	 * @param direction which direction you want your children to go to
	 */
	protected final void reproduce(Critter offspring, int direction) {
		if(this.energy >= Params.min_reproduce_energy){
			offspring.energy = this.energy/2;
			this.energy = Math.round(this.energy/2);
			Critter c = movement(1, direction, offspring);
			offspring.x_coord = c.x_coord;
			offspring.y_coord = c.y_coord;
			babies.add(offspring);
		}
	}

	public abstract void doTimeStep();
	public abstract boolean fight(String oponent);
	
	/**
	 * create and initialize a Critter subclass.
	 * critter_class_name must be the unqualified name of a concrete subclass of Critter, if not,
	 * an InvalidCritterException must be thrown.
	 * (Java weirdness: Exception throwing does not work properly if the parameter has lower-case instead of
	 * upper. For example, if craig is supplied instead of Craig, an error is thrown instead of
	 * an Exception.)
	 * @param critter_class_name
	 * @throws InvalidCritterException
	 */
	public static void makeCritter(String critter_class_name) throws InvalidCritterException {

        try {
        	critter_class_name= "assignment4."+critter_class_name;
            Class c = Class.forName(critter_class_name);

            Critter crit = (Critter) c.newInstance();
            crit.energy= Params.start_energy;
            crit.x_coord= getRandomInt(Params.world_width);
            crit.y_coord= getRandomInt(Params.world_height);


            population.add(crit);

        }
        catch (ClassNotFoundException|IllegalAccessException|InstantiationException|NoClassDefFoundError e)
        {
            throw new InvalidCritterException(critter_class_name);
        }

	}
	
	/**
	 * Gets a list of critters of a specific type.
	 * @param critter_class_name What kind of Critter is to be listed.  Unqualified class name.
	 * @return List of Critters.
	 * @throws InvalidCritterException
	 */
	public static List<Critter> getInstances(String critter_class_name) throws InvalidCritterException {
		List<Critter> result = new java.util.ArrayList<Critter>();

        for (Critter c: population) {

        	String result2 = "assignment4."+ critter_class_name;
            if (c.getClass().getName().equalsIgnoreCase(result2))
                result.add(c);
        }

	
		return result;
	}
	
	/**
	 * Prints out how many Critters of each type there are on the board.
	 * @param critters List of Critters.
	 */
	public static void runStats(List<Critter> critters) {
		System.out.print("" + critters.size() + " critters as follows -- ");
		java.util.Map<String, Integer> critter_count = new java.util.HashMap<String, Integer>();
		for (Critter crit : critters) {
			String crit_string = crit.toString();
			Integer old_count = critter_count.get(crit_string);
			if (old_count == null) {
				critter_count.put(crit_string,  1);
			} else {
				critter_count.put(crit_string, old_count.intValue() + 1);
			}
		}
		String prefix = "";
		for (String s : critter_count.keySet()) {
			System.out.print(prefix + s + ":" + critter_count.get(s));
			prefix = ", ";
		}
		System.out.println();		
	}
	
	/* the TestCritter class allows some critters to "cheat". If you want to 
	 * create tests of your Critter model, you can create subclasses of this class
	 * and then use the setter functions contained here. 
	 * 
	 * NOTE: you must make sure that the setter functions work with your implementation
	 * of Critter. That means, if you're recording the positions of your critters
	 * using some sort of external grid or some other data structure in addition
	 * to the x_coord and y_coord functions, then you MUST update these setter functions
	 * so that they correctly update your grid/data structure.
	 */
	static abstract class TestCritter extends Critter {
		protected void setEnergy(int new_energy_value) {
			super.energy = new_energy_value;
		}

		protected static int advancedCritLook(Critter c, int speed){
			int orig_index = population.indexOf(c);
			for(int dir = 0; dir < 8; dir++) {
				boolean flag = true;
				Critter s = movement(speed, dir, c);
				for (int i = 0; i < population.size(); i++) {
					if((s.x_coord == population.get(i).x_coord && s.y_coord == population.get(i).y_coord)&&(orig_index!=i)&&(population.get(i).energy>0)){
						flag = false;
					}
				}
				if(flag) return dir;
			}
			return -1;
		}
		protected void setX_coord(int new_x_coord) {
			super.x_coord = new_x_coord;
		}
		
		protected void setY_coord(int new_y_coord) {
			super.y_coord = new_y_coord;
		}
		
		protected int getX_coord() {
			return super.x_coord;
		}
		
		protected int getY_coord() {
			return super.y_coord;
		}
		

		/*
		 * This method getPopulation has to be modified by you if you are not using the population
		 * ArrayList that has been provided in the starter code.  In any case, it has to be
		 * implemented for grading tests to work.
		 */
		protected static List<Critter> getPopulation() {
			return population;
		}
		
		/*
		 * This method getBabies has to be modified by you if you are not using the babies
		 * ArrayList that has been provided in the starter code.  In any case, it has to be
		 * implemented for grading tests to work.  Babies should be added to the general population 
		 * at either the beginning OR the end of every timestep.
		 */
		protected static List<Critter> getBabies() {
			return babies;
		}
	}

	/**
	 * Clear the world of all critters, dead and alive
	 */
	public static void clearWorld() {
		// Complete this method.
        population.clear();
        babies.clear();

	}


	/**
	 * Does one time step for entire world and updates the world
	 */
	public static void worldTimeStep() {
		// Complete this method.
		//doing the time steps
        for (Critter c: population) {

			c.doTimeStep();
		}




        //doing encounters
        for (int i=0;i<population.size();i++){
            for (int j=i+1; j<population.size();j++){

                if((population.get(i).y_coord==population.get(j).y_coord )&&(population.get(i).x_coord==population.get(j).x_coord )&&(population.get(i).energy>0)&&(population.get(j).energy>0)){
                    //implement the actual fight
                    //fight is between i an j
                    //dont remove the dead body yet as it will mess up the order in the List


                    Critter a= population.get(i);
                    Critter b= population.get(j);

                    a.lookFlag=true;
                    b.lookFlag=true;

                    Boolean a_fight = a.fight(b.toString());
                    Boolean b_fight = b.fight(a.toString());
                    if((a.y_coord==b.y_coord )&&(a.x_coord==b.x_coord )&&(a.energy>0)&&(b.energy>0)) {
                        //both are alive and at the same position
                        //now we check for the rolls depending on whether they wanted to fight
                        int a_dice_roll = 0;
                        int b_dice_roll = 0;

                        if (a_fight)
                            a_dice_roll = getRandomInt(a.energy);
                        if (b_fight)
                            b_dice_roll = getRandomInt(b.energy);


                        if(a_dice_roll>=b_dice_roll){
                            a.energy+=b.energy/2;
                            b.energy =0;
                        }
                        else {
                            b.energy+=a.energy/2;
                            a.energy =0;
                        }


                    }
                }
            }
        }


		//doing the rest step
		for (Critter c: population){

			c.energy =c.energy -Params.rest_energy_cost;
		}


        //remove dead bodies
        for(int i=0; i<population.size();i++){
            if(population.get(i).energy<=0) {
				population.remove(i);
				i--;
			}

        }

		//add babies
		population.addAll(babies);

		babies.clear();
		//converted all the babies to real population so they can fight

        //add the additional algea

        for(int i=0;i<Params.refresh_algae_count;i++){
            try {
                makeCritter("Algae");
            } catch (InvalidCritterException e) {
                e.printStackTrace();
            }

        }

        //reseting the flags
		for(int i=0; i<population.size();i++){
			population.get(i).mFlag=false;
			population.get(i).lookFlag=false;
		}

	}
	/**
	 * Diplays the world
	 */
	public static void displayWorld() {
		// Complete this method.
		String[][] arr = new String[Params.world_height+2][Params.world_width+2];

		int rows= Params.world_height+2;
		int cols= Params.world_width+2;

		arr[0][0]="+";
		arr[0][cols-1]="+";
		arr[rows-1][0]="+";
		arr[rows-1][cols-1]="+";

		for(int i=1; i<rows-1;i++){
			arr[i][0]="|";
			arr[i][cols-1]="|";

		}
		for(int i=1; i<cols-1;i++){
			arr[0][i]="-";
			arr[rows-1][i]="-";

		}

		//System.out.println(arr);
		// include the location conversion here
		for(Critter c:population){



			arr[c.y_coord+1][c.x_coord+1]= c.toString();

		}



		for(int i=0;i<rows;i++){
			String output = "";

			for (int j=0; j<cols;j++){
				if(arr[i][j]==null)
					output+=" ";
				else
					output+= arr[i][j];

			}
			System.out.println(output);
		}



	}

}






















