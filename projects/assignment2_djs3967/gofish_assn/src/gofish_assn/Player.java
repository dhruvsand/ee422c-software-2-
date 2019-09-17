package gofish_assn;

import java.io.PrintWriter;
import java.util.ArrayList;

public class Player {

    ArrayList<Card> hand = new ArrayList<Card>();
    ArrayList<Card> book = new ArrayList<Card>();
    String name;


    //coverts name of player to string provided
    public Player(String name) {
        this.name = name;
    }
    //adds the provided card to hand
    public void addCardToHand(Card c) {
        hand.add(c);
    }

    //removes the specified card from hand and returns it so that the player can add it to his deck
    public Card removeCardFromHand(Card c) {
        for(int i = 0; i < hand.size(); i++) {
            if(hand.get(i).rank == c.rank){
                c = hand.get(i);
                hand.remove(i);
                return c;
            }
        }
        return c;
    }
    //return get name
    public String getName() {
        return name;
    }

    //coverts entire hand to string
    public String handToString() {
        String s = new String();
        int size = hand.size();
        for(int i = 0; i < size; i++){
            s = s + hand.get(i).toString() + " ";
        }
        return s;
    }

    //returns all bokked pairs to string
    public String bookToString() {
        String s = new String();
        int size = book.size();
        for(int i = 0; i < size; i++){
            s = s + book.get(i).toString() + " ";

        }
        return s;
    }

    //returns the size of the hand
    public int getHandSize() { return hand.size();
    }


    //returns the Book Size
    public int getBookSize() {
        return book.size()/2;
    }



    //this function will check a players hand for a pair. 
    //If a pair is found, it moves the cards to the book and returns true
    public boolean checkHandForBook(PrintWriter out) {
        if (hand.size() > 1) {
            for (int i = 0; i < hand.size() - 1; i++) {
                for (int j = i + 1; j < hand.size(); j++) {
                    if (hand.get(i).rank == hand.get(j).rank) {
                        book.add(hand.get(i));
                        book.add(hand.get(j));
                        Card temp1 = hand.get(i);
                        Card temp2 = hand.get(j);
                        String s = new String();
                        s = s + name + " books the " + hand.get(i).rankToString(hand.get(i).rank);
                        out.println(s);
                        hand.remove(temp1);
                        hand.remove(temp2);
                        return true;
                    }
                }
            }
            return false;
        }
        return false;
    }




    //uses some strategy to choose one card from the player's
    //hand so they can say "Do you have a 4?". it just picks the top one from mine
    public Card chooseCardFromHand() {
        Card c = hand.get(0);
        return c;
    }

    //Does the player have the card c in her hand?
    public boolean cardInHand(Card c) {
        for(int i = 0; i < hand.size(); i++){
            if(hand.get(i) == c){
                return true;
            }
        }
        return false;
    }

    //Does the player have a card with the same rank as c in her hand? if so returns true
    public boolean rankInHand(Card c) {
        for(int i = 0; i < hand.size(); i++){
            if(hand.get(i).rank == c.rank){
                return true;
            }
        }
        return false;
    }


    //Does the player have a card with the same rank as c in her hand?
    //e.g. will return true if the player has a 7d and the parameter is 7c

    public boolean sameRankInHand(Card c) {
        for(int i = 0; i < hand.size(); i++){
            if(hand.get(i).rank == c.rank){
                return true;
            }
        }
        return false;
    }

}
