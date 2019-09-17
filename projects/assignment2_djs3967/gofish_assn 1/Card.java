package gofish_assn;

public class Card {
	
	public enum Suits {club, diamond, heart, spade};
	
	static int TOP_RANK = 13; //King
	static int LOW_RANK = 1; //Ace
	
	int rank;  //1 is an Ace
	Suits suit;

	//default cunstructor
	public Card() {
		rank = 1;
		suit = Suits.spade;
	}
	
	public Card(int r, char s) {
		rank = r;
		suit = toSuit(s);
	}
	
	public Card(int r, Suits s) {
		rank = r;
		suit = s;
	}
	//compares to ascii values
	private Suits toSuit(char c) {
		if(c == 0x63){
			return Suits.club;
		}
		else if(c == 0x64){
			return Suits.diamond;
		}
		else if(c == 0x68){
			return Suits.heart;
		}
		else {
			return Suits.spade;
		}

	}
	//case structure to name string
	public String suitToString(Suits s){
		if(s == Suits.club){
			return "c";
		}
		else if(s == Suits.diamond){
			return "d";
		}
		else if(s == Suits.heart){
			return "h";
		}
		else {
			return "s";
		}
	}

	//converts rank to string
	public String rankToString(int r)
	{
		if(r == 1){
			return "A";
		}
		if(r == 2){
			return "2";
		}
		if(r == 3){
			return "3";
		}
		if(r == 4){
			return "4";
		}
		if(r == 5){
			return "5";
		}
		if(r == 6){
			return "6";
		}
		if(r == 7){
			return "7";
		}
		if(r == 8){
			return "8";
		}
		if(r == 9){
			return "9";
		}
		if(r == 10){
			return "10";
		}
		if(r == 11){
			return "J";
		}
		if(r == 12){
			return "Q";
		}else {
			return "K";
		} }
		
	//gets rank
	public int getRank() {
		return rank;
	}
	//gets suit
	public Suits getSuit() {
		return suit;
	}
	//converts to stirng
	public String toString() {
		String s = "";
		
		s = s + rankToString(getRank()) + suitToString(getSuit());
		
		return s;
	}
}
