package assignment4;
/* CRITTERS Main.java
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

import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
import java.lang.reflect.Method;

/*
 * Usage: java <pkgname>.Main <input file> test
 * input file is optional.  If input file is specified, the word 'test' is optional.
 * May not use 'test' argument without specifying input file.
 */
public class Main {

    static Scanner kb;	// scanner connected to keyboard input, or input file
    private static String inputFile;	// input file, used instead of keyboard input if specified
    static ByteArrayOutputStream testOutputString;	// if test specified, holds all console output
    private static String myPackage;	// package of Critter file.  Critter cannot be in default pkg.
    private static boolean DEBUG = false; // Use it or not, as you wish!
    static PrintStream old = System.out;	// if you want to restore output to console


    // Gets the package name.  The usage assumes that Critter and its subclasses are all in the same package.
    static {
        myPackage = Critter.class.getPackage().toString().split(" ")[1];
    }

    /**
     * Main method.
     * @param args args can be empty.  If not empty, provide two parameters -- the first is a file name, 
     * and the second is test (for test output, where all output to be directed to a String), or nothing.
     */
    public static void main(String[] args) {
        if (args.length != 0) {
            try {
                inputFile = args[0];
                kb = new Scanner(new File(inputFile));			
            } catch (FileNotFoundException e) {
                System.out.println("USAGE: java Main OR java Main <input file> <test output>");
                e.printStackTrace();
            } catch (NullPointerException e) {
                System.out.println("USAGE: java Main OR java Main <input file>  <test output>");
            }
            if (args.length >= 2) {
                if (args[1].equals("test")) { // if the word "test" is the second argument to java
                    // Create a stream to hold the output
                    testOutputString = new ByteArrayOutputStream();
                    PrintStream ps = new PrintStream(testOutputString);
                    // Save the old System.out.
                    old = System.out;
                    // Tell Java to use the special stream; all console output will be redirected here from now
                    System.setOut(ps);
                }
            }
        } else { // if no arguments to main
            kb = new Scanner(System.in); // use keyboard and console
        }

        /* Do not alter the code above for your submission. */
        /* Write your code below. */
        int condition =1;

        while(condition==1){
            int invalid =1;
            String line = kb.nextLine();

           Scanner line_scanner = new Scanner(line);


            String input = line_scanner.next();

            if(input.equals("show")){
                if(line.equals("show")){
                invalid =0;
                Critter.displayWorld();
                }
            }
            else if(input.equals("quit")){
                if(line.equals("quit")){
                invalid =0;
                condition=0;
                }
            }
            else if(input.equals("make")){

                if(line_scanner.hasNext()) {
                   // String critter_type = "assignment4." + line_scanner.next();
                    String critter_type =  line_scanner.next();
                    int count = 1;
                    if (line_scanner.hasNextInt())
                        count = line_scanner.nextInt();

                    if (!line_scanner.hasNext()) {
                        invalid = 0;

                        for (int i = 0; i < count; i++) {
                            try {
                                Critter.makeCritter(critter_type);
                            } catch (InvalidCritterException e) {
                                //e.printStackTrace();
                                invalid=1;
                                break;
                            }
                        }
                    }
                }
            }
            else if(input.equals("seed")){
                if(line_scanner.hasNextLong()){
                    long seed = line_scanner.nextLong();

                    if(!line_scanner.hasNext()){
                        invalid=0;
                        Critter.setSeed(seed);
                    }
                }
            }
            else if(input.equals("step")){
                int count =1;
                if(line_scanner.hasNextInt())
                    count = line_scanner.nextInt();

                if(!line_scanner.hasNext()){
                    invalid=0;
                    for (int i=0; i<count;i++){
                        Critter.worldTimeStep();
                    }
                }


            }
            else if(input.equals("stats")){
                if(line_scanner.hasNext()){
                    //makes sure we have another input word

                    String type1= line_scanner.next();
                     String   type = "assignment4."+type1;
                    if(!line_scanner.hasNext()){
                        //makes sure no extra words
                        invalid=0;
                        try {
                            Class c = Class.forName(type);
                            Method method = null;
                            try {
                                method = c.getDeclaredMethod("runStats", java.util.List.class);
                            } catch (NoSuchMethodException e) {
                                //e.printStackTrace();
                                invalid=1;
                            }
                            try {
                                method.invoke(null,Critter.getInstances(type1));
                            } catch (InvocationTargetException|InvalidCritterException|NullPointerException e) {
                                //e.printStackTrace();
                                invalid=1;
                            }
                        }
                        catch (ClassNotFoundException|IllegalAccessException e)
                        {
                            try {
                                throw new InvalidCritterException(type);
                            } catch (InvalidCritterException e1) {
                                //e1.printStackTrace();
                                invalid=1;
                            }
                        }
                    }
                }
            }
            else {
                invalid=0;
                System.out.println("invalid command: "+line);
            }

            if(invalid==1){
                System.out.println("error processing: "+line);
            }

        }
        
        // System.out.println("GLHF");
        
        /* Write your code above */
        System.out.flush();

    }
}
