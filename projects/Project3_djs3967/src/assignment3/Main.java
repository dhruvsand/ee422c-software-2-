/* WORD LADDER Main.java
 * EE422C Project 3 submission by
 * Replace <...> with your actual data.
 * Dhruv Sandesara
 * djs3967
 * 15495
 *
 *
 * Slip days used: <0>
 * Git URL:
 * Fall 2017
 */


package assignment3;
import com.sun.tools.doclets.formats.html.SourceToHTMLConverter;

import javax.xml.soap.SOAPPart;
import java.util.*;
import java.io.*;

public class Main {
	
	// static variables and constants only here.
	
	public static void main(String[] args) throws Exception {
		
		Scanner kb;	// input Scanner for commands
		PrintStream ps;	// output file, for student testing and grading only
		// If arguments are specified, read/write from/to files instead of Std IO.
		if (args.length != 0) {
			kb = new Scanner(new File(args[0]));
			ps = new PrintStream(new File(args[1]));
			System.setOut(ps);			// redirect output to ps
		} else {
			kb = new Scanner(System.in);// default input from Stdin
			ps = System.out;			// default output to Stdout
		}
		initialize();

        int break_flag=0;




		ArrayList<String> Input= new ArrayList<String>();

		while(true){
		    Input=parse(kb);

		    if(Input.contains("/quit"))
		        break_flag=1;

		    if(break_flag==1)
		        break;
		    else {

		        String start= Input.get(0);
		        String end= Input.get(1);


               ArrayList<String> BFS_word_ladder = new ArrayList<String>();
               ArrayList<String> DFS_word_ladder = new ArrayList<String>();

               BFS_word_ladder=  getWordLadderBFS(start,end);
               DFS_word_ladder=  getWordLadderDFS(start,end);

               printLadder(BFS_word_ladder);
               printLadder(DFS_word_ladder);
               Input.clear();

            }
        }

		// TODO methods to read in words, output ladder
	}
	
	public static void initialize() {
		// initialize your static variables or constants here.
		// We will call this method before running our JUNIT tests.  So call it 
		// only once at the start of main.
	}
	
	/**
	 * @param keyboard Scanner connected to System.in
	 * @return ArrayList of Strings containing start word and end word. 
	 * If command is /quit, return empty ArrayList. 
	 */
	public static ArrayList<String> parse(Scanner keyboard) {


        ArrayList<String> Input = new ArrayList<String>();
        Input.add(keyboard.next().toLowerCase());
        if(Input.contains("/quit"))
        return Input;

        Input.add(keyboard.next().toLowerCase());
        return Input;



		// TO DO
		//return null;
	}

    /**
     * This is our main DFS engine whichc will call the other recursive helper recurssively
     * @param start String which defines our starting word
     * @param end String which defines our ending word for
     * @return ArrayList of Strings containing start word, middle words and end word
     */
	public static ArrayList<String> getWordLadderDFS(String start, String end) {
		
		// Returned list should be ordered start to end.  Include start and end.
		// If ladder is empty, return list with just start and end.
		// TODO some code
		Set<String> dict = makeDictionary();
		// TODO more code
        ArrayList<String> DFSlist = new ArrayList<String>();
        DFSlist.add(start);

        if(start.equals(end)){
            DFSlist.add(end);
            return DFSlist;

        }
        DFSlist=DFS_Recursion(start,end,DFSlist,dict);

        if(DFSlist==null){//we got that there is no link
            DFSlist = new ArrayList<String>();
            DFSlist.add(start);
            DFSlist.add(end);

            //reset values to print stuff
        }

        return DFSlist;




		//return null; // replace this line later with real return
	}

    /**
     * This is our main DFS engine whichc will call the other recursive helper recurssively
     * @param start String which defines our starting word
     * @param end String which defines our ending word for
     * @param explored ArrayList of String we have already looked at which we can refer incase we either need to backtrace our path or we found a valid path for reaching destination
     * @param dict Set of String which defines our remaing words
     * @return ArrayList of Strings containing start word, middle words and end word if path found elese null
     */
	public static ArrayList<String> DFS_Recursion(String start,String end, ArrayList<String> explored, Set<String> dict){

	    //another base case to avoid overflow

        if(explored.size()>=1000)
            return null;


	    //base case is we find the word
        if(start.equals(end)){

            return explored;

        }

        //now this is the case when we are given a new start. First we want to remove it from the dictonary so it is no longer removable

        dict.remove(start);

        for(int i=0;i<start.length();i++){
            String start_temp=start;
            if(start_temp.charAt(i)!=end.charAt(i)) {//this makes sure words already matching are not changed to foolish letters
                for (char j = 96; j <= 'z'; j++) {

                    if(j==96){//adding efficieny we are just checking if changing one charachter is a valid word
                        start_temp = start_temp.substring(0, i) + end.charAt(i) + start_temp.substring(i + 1);


                    }
                    else {


                        // start_temp=start_temp.replace(start_temp.charAt(i),j);
                        start_temp = start_temp.substring(0, i) + j + start_temp.substring(i + 1);

                    }


                    if (dict.contains(start_temp) && !explored.contains(start_temp)) {

                        explored.add(start_temp);
                        dict.remove(start_temp);

                        ArrayList<String> partial_explored = new ArrayList<String>();
                        partial_explored = DFS_Recursion(start_temp, end, explored, dict);

                        if (partial_explored != null) {

                            return partial_explored;//link found in the later recurrsions
                        } else {
                            explored.remove(start_temp);

                        }

                    }

                }
            }

        }





	    return null;//there is no link found
    }


    /**
     * This is our main BFS engines which stores each layer or distance away from the start as a block and then computes next layers until the end is found or not found
     * @param start String which defines our starting word
     * @param end String which defines our ending word for
     * @return ArrayList of Strings containing start word, middle words and end word
     */
    public static ArrayList<String> getWordLadderBFS(String start, String end) {
		
		// TODO some code
		Set<String> dict = makeDictionary();
		// TODO more code

        ArrayList<String> path = new ArrayList<String>();// this will return our list of the path taken
        ArrayList<String> reference_copy_of_to_be_explored_words = new ArrayList<String>();// this will keep track of the nodes even after the are removed from the queue
        ArrayList<String> parent_of_to_be_explored_words = new ArrayList<String>(); //this will keep track of parents of to be explored nodes so we may back track our steps
        Queue<String> to_be_explored_words= new LinkedList<String>();// this will keep track of the words to be dealt with

        to_be_explored_words.add(start);//initial queue has only the starting element
        reference_copy_of_to_be_explored_words.add(start);//as we are currently at that point
        parent_of_to_be_explored_words.add(null);//as parent of the start node DNE. this will help us end
        dict.remove(start);//this ensures that if any one elemnt is added to anylist, it will not be redundant

        String current="";// this is the head

        while (!to_be_explored_words.isEmpty()){

            current=to_be_explored_words.poll();//get the head element
            //explored_words.add(current);

            if(current.equals(end))
                break;// this means our path is now complete as we have reached destination

            //in this case we have not met the final node, thus we must add the next layer of nodes.



            for (int i=0;i<start.length();i++){
                String temp=current;
                for (char j = 'a'; j <= 'z'; j++){

                    temp = temp.substring(0, i) + j + temp.substring(i + 1);// this gets a version of a one letter difference string

                    if(dict.contains(temp)){
                        dict.remove(temp);
                        to_be_explored_words.add(temp);//add the current element to the que to be explored
                        reference_copy_of_to_be_explored_words.add(temp);//so we can check the index and get parent of string node
                        parent_of_to_be_explored_words.add(current);


                    }


                }



            }

        }

        if(current.equals(end)){

            while (current!=null) {
                path.add(current);
                current=parent_of_to_be_explored_words.get(reference_copy_of_to_be_explored_words.indexOf(current));
            }
           // Collection.reverse(path);
            ArrayList<String> reverse_path = new ArrayList<String>();// this will return our list of the path taken
            for (int i=path.size()-1;i>=0;i--)
                reverse_path.add(path.get(i));

            return reverse_path;



        }

        path.clear();
        path.add(start);
        path.add(end);







		return path; // replace this line later with real return
	}

    /**
     * This basicaly takes a word ladder and prints it if there are more than 2 elemnts else it says no path found
     * @param ladder ArrayList of Strings which has our wordladder
     *
     *
     */
	public static void printLadder(ArrayList<String> ladder) {

	    if(ladder.size()==2){
            System.out.println("no word ladder can be found between " + ladder.get(0) + " and " + ladder.get(1) + ".");
        }
        else {

	        System.out.println("a "+(ladder.size()-2)+"-rung word ladder exists between " + ladder.get(0) + " and " + ladder.get(ladder.size()-1) + ".");
        for (int i=0;i<ladder.size();i++)
            System.out.println(ladder.get(i));


        }
		
	}
	// TODO
	// Other private static methods here


	/* Do not modify makeDictionary cause it makes dictionary */
	public static Set<String>  makeDictionary () {
		Set<String> words = new HashSet<String>();
		Scanner infile = null;
		try {
			infile = new Scanner (new File("five_letter_words.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("Dictionary File not Found!");
			e.printStackTrace();
			System.exit(1);
		}
		while (infile.hasNext()) {
			words.add(infile.next().toLowerCase());
		}
		return words;
	}
}
