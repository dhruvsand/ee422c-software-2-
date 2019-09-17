package gofish_assn;

import gofish_assn.Card.Suits;

import java.util.ArrayList;
import java.util.Random;

public class Deck {
	ArrayList<Card> deck = new ArrayList<Card> ();
	private int Num_Shuffles=1000;
	final int NUM_CARDS = 52;  //for this kind of deck
	private int cardsLeft = 52;
	
	//creates a new sorted deck
	public Deck() {
		for(int i = 1; i < 14; i++){
			for(int k = 1; k <5; k++){
				if(k == 1){
					Card c = new Card(i,Suits.club);
					deck.add(c);
				}
				if(k == 2){
					Card c = new Card(i,Suits.diamond);
					deck.add(c);
				}
				if(k == 3){
					Card c = new Card(i,Suits.heart);
					deck.add(c);
				}
				if(k == 4){
					Card c = new Card(i,Suits.spade);
					deck.add(c);
				}
			}
		}
	}


    //shuffles Num_Shuffles times
	public void shuffle() {
		Random rand = new Random();
		for (int i = 0; i < Num_Shuffles ; i++){
			int r1 = rand.nextInt(NUM_CARDS);
			int r2= rand.nextInt(NUM_CARDS);
			Card temp = deck.get(r1);
			deck.set(r1,deck.get(r2));
			deck.set(r2,temp);
		}
	}
	
	//prints remaning deck
	public void printDeck() {

	    for(int i=0;i<this.cardsLeft;i++){
	        System.out.println(deck.get(i));

        }

	}
	
	//dels the top card
	public Card dealCard() {
	    Card temp= new Card();
	    if(this.cardsLeft==0)
	        return temp;
		temp = deck.get(0);
		deck.remove(0);
		cardsLeft--;
		return temp;
	}
	//gets number of cards left
	public int getCardsLeft(){
		return this.cardsLeft;
	}
	

}
