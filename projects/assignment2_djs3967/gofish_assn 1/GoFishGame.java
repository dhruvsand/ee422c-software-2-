package gofish_assn;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class GoFishGame {
	public GoFishGame() {
	}




	public void GoFishMain() {
		Deck myDeck = new Deck();
		myDeck.shuffle();
		Player p1 = new Player("Dhruv");
		Player p2 = new Player("Harsh");


        PrintWriter out = null;
        try {
            out = new PrintWriter(new FileWriter("GoFish.txt"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }





		//Deal Cards
		for (int i = 0; i < 7; i++) {
			p1.addCardToHand(myDeck.dealCard());
			p2.addCardToHand(myDeck.dealCard());
		}

		//Check for initial pairs
        while (p2.checkHandForBook(out)) ;
        if (p2.hand.size() == 0) {
            p2.addCardToHand(myDeck.dealCard());
        }

        while (p1.checkHandForBook(out)) ;
		if (p1.hand.size() == 0) {
			p1.addCardToHand(myDeck.dealCard());
		}



		int stopFlag = 0;
		while (myDeck.getCardsLeft() != 0 && stopFlag != 1) {
			int p1Flag = 1;
			int p2Flag = 1;
			while (p1Flag == 1) {
				if (p1.hand.size() == 0 && myDeck.getCardsLeft() > 0) {
					p1.addCardToHand(myDeck.dealCard());
				}

				if (p1.hand.size() != 0) {
					Card c = p1.chooseCardFromHand();
					String s = new String();
					s = s + p1.getName() + " asks - Do you have a "
							+ c.rankToString(p1.hand.get(0).rank);
					out.println(s);
					if (p2.rankInHand(c)) {
						p1.addCardToHand(p2.removeCardFromHand(c));
						if (p2.hand.size() == 0 && myDeck.getCardsLeft() > 0) {
							p2.addCardToHand(myDeck.dealCard());
						} else if (p2.hand.size() == 0 && myDeck.getCardsLeft() <= 0) {
							p1.checkHandForBook(out);
							stopFlag = 1;
						} else {
							p1.checkHandForBook(out);
						}
						p1Flag = 2;
						if (p1.hand.size() == 0 && myDeck.getCardsLeft() > 0) {
							p1.addCardToHand(myDeck.dealCard());
						} else if (p1.hand.size() == 0 && myDeck.getCardsLeft() <= 0) {
							stopFlag = 1;
						}
					} else if (myDeck.getCardsLeft() > 0) {
						if (myDeck.getCardsLeft() > 0) {
							p1.addCardToHand(myDeck.dealCard());
							p1.checkHandForBook(out);
						}
					}
				}
				p1Flag--;
			}

			while (p2Flag == 1) {
				if (p2.hand.size() == 0 && myDeck.getCardsLeft() > 0) {
					p2.addCardToHand(myDeck.dealCard());
				}
				String s = new String();

				if (p2.hand.size() > 0) {
					Card c = p2.chooseCardFromHand();
					s = s + p2.getName() + " asks - Do you have a "
							+ c.rankToString(c.rank);
					out.println(s);
					if (p1.rankInHand(c)) {
						p2.addCardToHand(p1.removeCardFromHand(c));
						if (p1.hand.size() == 0 && myDeck.getCardsLeft() > 0) {
							p1.addCardToHand(myDeck.dealCard());
						} else if (p1.hand.size() == 0 && myDeck.getCardsLeft() <= 0) {
							p2.checkHandForBook(out);
							stopFlag = 1;
						}
						p2.checkHandForBook(out);
						p2Flag = 2;
						if (p2.hand.size() == 0 && myDeck.getCardsLeft() > 0) {
							p2.addCardToHand(myDeck.dealCard());
						}
					} else if (myDeck.getCardsLeft() > 0) {
						if (myDeck.getCardsLeft() > 0) {
							p2.addCardToHand(myDeck.dealCard());
							p2.checkHandForBook(out);
						}
					}
				}
				p2Flag--;
			}
		}



		int result=0;
		Player pwinner=p1,ploser=p2;

		if (p1.getBookSize() > p2.getBookSize()) {
		    result=1;

		} else if (p2.getBookSize() > p1.getBookSize()) {
            pwinner=p2;
            ploser=p1;
            result=1;
		}

		if(result==1){

            String s = new String();
            s = s + pwinner.getName()+" wins with " + pwinner.getBookSize() + " booked pairs!";
            out.println(s);
            out.println(pwinner.bookToString());
            String j = new String();
            j = j+ ploser.getName() + " has " + ploser.getBookSize() + " booked pairs!";
            out.println(j);
            out.println(ploser.bookToString());

        }
        else if(result==0) {
            out.println("The Game is a Draw!");//result=0 is a draw
        }


        out.close();

	}




}
