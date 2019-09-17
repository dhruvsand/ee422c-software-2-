package assignment7;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import javafx.scene.control.Label;
import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

public class cheaters extends Application{

    static ArrayList<ArrayList<String>> connections;
    static ArrayList<Integer> common_words;
    static Set<String> detected_submissions;

    public static void main(String[] args) {
        //System.out.println("Hello World!");
        //System.out.println("got here");

//        System.out.println(args[0]);
//        File temp_folder = new File(args[0]);
//        System.out.println(args[1]);
//        int n = Integer.parseInt(args[1]);
//        System.out.println(args[2]);
//        int bound = Integer.parseInt(args[2]);
//
        try
        {
            connections = new ArrayList<>();
            common_words = new ArrayList<>();
            detected_submissions = new HashSet<>();

            String cur_dir = System.getProperty("user.dir");
            File folder = new File(cur_dir+"/big_doc_set/");
            File[] listOfFiles = folder.listFiles();

            ArrayList<File> files = new ArrayList<>();
            ArrayList<String> words = new ArrayList<>();
            ArrayList<submission> submissions = new ArrayList<>();
            BufferedReader in;

            for (File file : listOfFiles) {
                if (file.isFile()) {
                    //System.out.println(file.getName());
                    files.add(file);
                }
            }


            for(int j=0; j<files.size();j++) {
                in = new BufferedReader(new InputStreamReader(new FileInputStream(files.get(j)), "Cp1252"));
                //System.out.println("File " + j + " open successful!");
                //System.out.println(files.get(j).getName());

                for (String x = in.readLine(); x != null; x = in.readLine()) {
                    String[] tokens = x.split(" ");
                    for (int i = 0; i < tokens.length; i++)
                        words.add(tokens[i]);
                }

                submissions.add(new submission(files.get(j).getName(), words,7));
                words.clear();
            }

            //ArrayList<ArrayList<String>> connections= new ArrayList<>();
              //ArrayList<Set<String>> connections = new ArrayList<>();


            for(int i=0;i<files.size()-1;i++) {
                String s1= files.get(i).getName();

                for(int j=i+1;j<files.size();j++){

                    String s2= files.get(j).getName();
                    int common= submissions.get(i).comparasion(submissions.get(j));
                        if(common>200) {

                            System.out.println(s1 + "," + s2 + " : " + common);
                            ArrayList<String> stringSet = new ArrayList<>();
                            //Set<String> stringSet = new HashSet<>();
                            stringSet.add(s1);
                            stringSet.add(s2);
                            detected_submissions.add(s1);
                            detected_submissions.add(s2);

                            connections.add(stringSet);
                            common_words.add(common);
                        }

                }

            }
            System.out.println("Connections found : " +connections.size());
            System.out.println("Suspicious Groups:   ");

//            for (int i=0;i<connections.size();i++){
//                for(int j=i+1;j<connections.size();j++){
//                    String s="";
//                    for(int x = 0; x< connections.get(i).size();x++)
//                        s = connections.get(i).get(x);
//                        if(connections.get(j).contains(s)) {
//
//                            connections.get(i).addAll(connections.get(j));
////                            for (int y=0;y<connections.get(j).size();y++) {
////                                if (!connections.get(i).contains(connections.get(j).get(y)))
////                                    connections.get(i).add(connections.get(j).get(y));
////                            }
//                            connections.remove(j);
//                            j--;
//                        }
//                }
//            }
//            for(int i=0;i<connections.size();i++){
//                Set<String> temp = new HashSet<>();
//                temp.addAll(connections.get(i));
//                connections.get(i).clear();
//                connections.get(i).addAll(temp);
//
//            }

            ArrayList<ArrayList<String>> finalconnections = new ArrayList<>();
            //ArrayList<Set<String>> finalconnections = new ArrayList<>();
            int x=0;
            for( int i=connections.size()-1;i>=0;i--){
                ArrayList<String> temp = new ArrayList<>();
                String str1= connections.get(i).get(0);
                String str2 =connections.get(i).get(1);

                int added=0;
                for(int j=0; j<finalconnections.size();j++){
                    boolean case1= finalconnections.get(j).contains(str1);
                    boolean case2= finalconnections.get(j).contains(str2);

                    if(case1||case2){
                        added=1;
                        if(!case1)
                            finalconnections.get(j).add(str1);
                        if(!case2)
                            finalconnections.get(j).add(str2);
                    }
                }
                if(added==0){
                    temp.add(str1);
                    temp.add(str2);
                    finalconnections.add(temp);
                }
            }



            for (ArrayList s:finalconnections){
                System.out.println(s);

            }


        }
        catch (IOException e)
        {
            System.out.println("File I/O error!");
        }

        launch(args);




    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        //System.out.println(common_words);
        //System.out.println(connections);
        //System.out.println(detected_submissions);
        javafx.scene.canvas.Canvas canvas = new Canvas(300, 250);

        List<String> list = new ArrayList<>(detected_submissions);
        StackPane centeredLabel=null;


        for (int i=0; i<detected_submissions.size();i++){

            Rectangle rectangle = new Rectangle(10,10);
            Label label = new Label(list.get(i));
            label.setStyle("-fx-background-color: coral; -fx-padding: 10px;");

            centeredLabel = new StackPane(label);


        }
        centeredLabel.getChildren().add(canvas);


        primaryStage.setScene(new Scene(centeredLabel));

        primaryStage.show();

    }
}
