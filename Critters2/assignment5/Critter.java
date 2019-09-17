package assignment5;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.GridPane;

import java.util.List;

public abstract class Critter {
	/* NEW FOR PROJECT 5 */
	public enum CritterShape {
		CIRCLE,
		SQUARE,
		TRIANGLE,
		DIAMOND,
		STAR
	}
	
	/* the default color is white, which I hope makes critters invisible by default
	 * If you change the background color of your View component, then update the default
	 * color to be the same as you background 
	 * 
	 * critters must override at least one of the following three methods, it is not 
	 * proper for critters to remain invisible in the view
	 * 
	 * If a critter only overrides the outline color, then it will look like a non-filled 
	 * shape, at least, that's the intent. You can edit these default methods however you 
	 * need to, but please preserve that intent as you implement them. 
	 */
	public javafx.scene.paint.Color viewColor() { 
		return javafx.scene.paint.Color.WHITE; 
	}
	
	public javafx.scene.paint.Color viewOutlineColor() { return viewColor(); }
	public javafx.scene.paint.Color viewFillColor() { return viewColor(); }
	
	public abstract CritterShape viewShape(); 
	
	private static String myPackage;
	private	static List<Critter> population = new java.util.ArrayList<Critter>();
	private static List<Critter> babies = new java.util.ArrayList<Critter>();
	private boolean mFlag = false;
	private boolean lookFlag = false;
	// Gets the package name.  This assumes that Critter and its subclasses are all in the same package.
	static {
		myPackage = Critter.class.getPackage().toString().split(" ")[1];
	}
	

	
	/* rest is unchanged from Project 4 */
	
	
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
            critter_class_name= "assignment5."+critter_class_name;
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

            String result2 = "assignment5."+ critter_class_name;
            if (c.getClass().getName().equalsIgnoreCase(result2))
                result.add(c);
        }


        return result;
    }


    /**
     * Prints out how many Critters of each type there are on the board.
     * @param critters List of Critters.
     */
    public static String runStats(List<Critter> critters) {
        String result="" + critters.size() + " critters as follows -- ";
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
            result+=prefix + s + ":" + critter_count.get(s);
            prefix = ", ";
        }
        result+="\n";
        return result;
    }

	
	/* the TestCritter class allows some critters to "cheat". If you want to 
	 * create tests of your Critter model, you can create subclasses of this class
	 * and then use the setter functions contained here. 
	 * 
	 * NOTE: you must make sure thath the setter functions work with your implementation
	 * of Critter. That means, if you're recording the positions of your critters
	 * using some sort of external grid or some other data structure in addition
	 * to the x_coord and y_coord functions, then you MUST update these setter functions
	 * so that they correctup update your grid/data structure.
	 */
	static abstract class TestCritter extends Critter {
		protected void setEnergy(int new_energy_value) {
			super.energy = new_energy_value;
		}
		
		protected void setX_coord(int new_x_coord) {
			super.x_coord = new_x_coord;
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

    protected final String look(int direction, boolean steps) {
        int orig_index = population.indexOf(this);
        this.energy -= Params.look_energy_cost;
        int speed = 1;
        if(steps == true) speed = 2;
        Critter s = movement(speed, direction, this);
        for (int i = 0; i < population.size(); i++) {
            if((s.x_coord == population.get(i).x_coord && s.y_coord == population.get(i).y_coord)&&(i!=orig_index)&&(population.get(i).energy>0)){
                return population.get(i).toString();
            }
        }
        return null;
    }




    public static void displayWorld(Object pane) {

        GridPane myGrid = (GridPane) pane;

        double width = myGrid.getWidth()/Params.world_width;
        double height = myGrid.getHeight()/Params.world_height;

        for(Critter c: population){
            Canvas canvas = Critter_to_Shape(c, width, height);
            myGrid.add(canvas, c.x_coord, c.y_coord);
        }


    }

   private static Canvas Critter_to_Shape(Critter c, double width, double height){
        double w= width;
        double h= height;


       Canvas canvas = new Canvas(w, h);
       GraphicsContext gc = canvas.getGraphicsContext2D();
       CritterShape my_critterShape = c.viewShape();
       switch(my_critterShape){
           case SQUARE:


               if(Math.min(w, h) == h){
                   gc.setFill(c.viewFillColor());
                   gc.fillRect(w/2-h/2, 0, h, h);
                   gc.setStroke(c.viewOutlineColor());
                   gc.strokeRect(w/2-h/2, 0, h, h);
               }
               else{
                   gc.setFill(c.viewFillColor());
                   gc.fillRect(0, h/2-w/2, w, w);
                   gc.setStroke(c.viewOutlineColor());
                   gc.strokeRect(0, h/2-w/2, w, w);
               }
               break;
           case CIRCLE:
               if(Math.min(w, h) == h){
                   gc.setFill(c.viewFillColor());
                   gc.fillOval(w/2-h/2, 0, h, h);
                   gc.setStroke(c.viewOutlineColor());
                   gc.strokeOval(w/2-h/2, 0, h, h);
               }
               else{
                   gc.setFill(c.viewFillColor());
                   gc.fillOval(0, h/2-w/2, w, w);
                   gc.setStroke(c.viewOutlineColor());
                   gc.strokeOval(0, h/2-w/2, w, w);
               }
               break;
           case TRIANGLE:
               double triangle_xpoints[] = {w/2, w/4, 0.75*w};
               double triangle_ypoints[] = {0, h, h};
               gc.setFill(c.viewFillColor());
               gc.fillPolygon(triangle_xpoints, triangle_ypoints, triangle_xpoints.length);
               gc.setStroke(c.viewOutlineColor());
               gc.strokePolygon(triangle_xpoints, triangle_ypoints, triangle_xpoints.length);
               break;
           case DIAMOND:
               double diamond_xpoints[] = {w/2, 0.25*w, w/2, 0.75*w};
               double diamond_ypoints[] = {0, h/2, h, h/2};
               gc.setFill(c.viewFillColor());
               gc.fillPolygon(diamond_xpoints, diamond_ypoints, diamond_xpoints.length);
               gc.setStroke(c.viewOutlineColor());
               gc.strokePolygon(diamond_xpoints, diamond_ypoints, diamond_xpoints.length);
               break;
           case STAR:
               double star_xpoints[] = {w/2, w/3, 0, w/4, w/8, w/2, 7*(w/8), 3*(w/4), w, 2*(w/3)};
               double star_ypoints[] = {0, h/3, h/3, 2*(h/3), h, 3*(h/4), h, 2*(h/3), h/3, h/3};
               gc.setFill(c.viewFillColor());
               gc.fillPolygon(star_xpoints, star_ypoints, star_xpoints.length);
               gc.setStroke(c.viewOutlineColor());
               gc.strokePolygon(star_xpoints, star_ypoints, star_xpoints.length);
               break;
       }


       return canvas;



   }

}
