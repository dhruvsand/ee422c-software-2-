package assignment5;

import javafx.animation.AnimationTimer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.CacheHint;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {
	
	private ObservableList<CheckMenuItem> list = FXCollections.observableArrayList();//list will be for the make critter
	public static ObservableList<CheckMenuItem> bugs = FXCollections.observableArrayList();//will store the checklist for runstats
	@FXML
	private Button tSB, rSB, seedB, mCB, runB, pauseB, quitB;
	@FXML
	private MenuButton rSMB;
	@FXML
	private TextField speedB, mCBox, seedBox, tSTF;
	@FXML
	private Slider sSlider;
	@FXML
	private ChoiceBox mCCB;
	@FXML
	private GridPane Grid;
	@FXML
	private TextArea TA;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {	
		//Set the min and max values for the Speed of Animation
		sSlider.setMax(100);
		sSlider.setMin(1);
		
		//AnimationTimer for the display of the grid.

		Grid.setCache(true);
		Grid.setCacheShape(true);
		Grid.setCacheHint(CacheHint.SPEED);
		Grid.setGridLinesVisible(true);


		AnimationTimer GridDisplay = new AnimationTimer(){
			private long previous = 0;
			@Override
			public void handle(long now) {
				long slideval = 0;
				try{
					slideval = Integer.parseInt(speedB.getText());
					if(slideval <= 0)
						slideval = (long)sSlider.getValue();
					if(sSlider.isValueChanging()){
						speedB.setText(Integer.toString((int)sSlider.getValue()));
					}
					else{
						sSlider.setValue((double)slideval);
					}
				}
				catch(Exception e){
					slideval = (long)sSlider.getValue();
					if(sSlider.isValueChanging())
						speedB.setText(Integer.toString((int)sSlider.getValue()));

				}
				long delay = 1_000_000_000/slideval;
				// TODO Auto-generated method stub
				if(now - previous >= delay){
					Critter.worldTimeStep();
					Grid_Display();
					previous = now;
				}
			}
		};
		
		//Initialize what critters we have in our folders.
		ArrayList<String> critters = getBugs();
		Critter.clearWorld();
		for(String bug : critters){
			list.add(new CheckMenuItem(bug));
			mCCB.getItems().add(bug);
			//System.out.println(bug);
		}
		rSMB.getItems().addAll(list);

		mCB.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				try{

					if(!mCBox.getText().equals("")){
						int i = Integer.parseInt(mCBox.getText());
						for(int j = 0; j < i; j++){
							Critter.makeCritter(mCCB.getValue().toString());
						}
					}
					else{
						Critter.makeCritter(mCCB.getValue().toString());
					}

				}catch(Exception e){

				}

			}
		});

		rSB.setOnAction((event) -> {
			bugs.clear();
			for(MenuItem item: rSMB.getItems()){
				CheckMenuItem cMenuItem = (CheckMenuItem) item;
				if(cMenuItem.isSelected()){
					bugs.add(cMenuItem);
				}
			}
		});

		tSB.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					if (!tSTF.getText().equals("")) {
						int i = Integer.parseInt(tSTF.getText());
						for (int j = 0; j < i; j++) {
							Critter.worldTimeStep();
						}
					} else {
						Critter.worldTimeStep();
					}
					Grid_Display();
				}catch(Exception e){

				}
			}
		});

		seedB.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try{

					if(seedBox.getText().equals("")){}
					else{
						int i = Integer.parseInt(seedBox.getText());
						Critter.setSeed(i);
					}

				}catch(Exception e){

				}
			}
		});



		quitB.setOnAction(new EventHandler <ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				System.exit(0);
			}
		});

		runB.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event) {
				tSB.setDisable(true);
				rSB.setDisable(true);
				seedB.setDisable(true);
				mCB.setDisable(true);
				rSMB.setDisable(true);
				runB.setDisable(true);
				pauseB.setDisable(false);
				mCBox.setDisable(true);
				seedBox.setDisable(true);
				tSTF.setDisable(true);
				mCCB.setDisable(true);
				GridDisplay.start();
			}
		});

		pauseB.setOnAction(new EventHandler <ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				tSB.setDisable(false);
				rSB.setDisable(false);
				seedB.setDisable(false);
				mCB.setDisable(false);
				rSMB.setDisable(false);
				runB.setDisable(false);
				pauseB.setDisable(true);
				mCBox.setDisable(false);
				seedBox.setDisable(false);
				tSTF.setDisable(false);
				mCCB.setDisable(false);
				GridDisplay.stop();
			}
		});

		//Set up the Grid
        Grid_Display();


        
        //Handles the RunStats Display
        TA.setCache(true);
        TA.setCacheShape(true);
        TA.setCacheHint(CacheHint.SPEED);
        AnimationTimer timer = new AnimationTimer(){
			@Override
			public void handle(long now) {
				TA.clear();
	              for(MenuItem item : Controller.bugs){

                	  List<Critter> critters = new ArrayList<>();
                	  String name = item.getText();

                	  try{
  	            		critters = Critter.getInstances(name);
	  	            	}
	  	            	catch(Exception e){
	  	            	}
	  	            	Class<?> myCritter = null;
	  	        		try {
	  	        			myCritter = Class.forName("assignment5." + name); 	// Class object of specified name
	  	        		} catch (ClassNotFoundException e) {
	  	        		}
	  	        		try{
	  	        			Method method = myCritter.getMethod("runStats", List.class);
	  	        			TA.appendText((String) method.invoke(null, critters));
	  	        		}
	  	        		catch(Exception e){
	  	        		}
	              }
			}  
        };
        timer.start();
	}

	private void Grid_Display(){
		//Set up the Grid
		Node node = Grid.getChildren().get(0);
		Grid.getChildren().clear();
		Grid.getChildren().add(0,node);

		Grid.getColumnConstraints().clear();
		Grid.getRowConstraints().clear();



		int numCols = Params.world_width;
		int numRows = Params.world_height;
		for (int i = 0; i < numCols; i++) {
			ColumnConstraints colConst = new ColumnConstraints();
			colConst.setPercentWidth(100.0 / (double)numCols);
			Grid.getColumnConstraints().add(colConst);
		}
		for (int i = 0; i < numRows; i++) {
			RowConstraints rowConst = new RowConstraints();
			rowConst.setPercentHeight(100.0 / (double)numRows);
			Grid.getRowConstraints().add(rowConst);
		}

		Critter.displayWorld(Grid);

	}

	/**
	 * Gets names of Critters in src.
	 * @return List of valid Critters
	 */
	private ArrayList<String> getBugs(){
		ArrayList<String> bugs_list = new ArrayList<String>();

		String working_dir = System.getProperty("user.dir");
		String dir = working_dir+"/"+"src"+"/"+"assignment5";
			File folder = new File(dir);
			for(File file: folder.listFiles()){
				if(file.isFile()){
					String name = file.getName();
					name = name.replaceFirst("[.].*", "");
					if(name != "Critter"){
						try{
							Critter.makeCritter(name);
						}
						catch(Exception e){
							//System.out.println(e.getMessage());
							continue;
						}
						bugs_list.add(name);
					}
				}
			}

		return bugs_list;
	}

}
