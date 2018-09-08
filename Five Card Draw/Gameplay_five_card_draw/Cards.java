/*
 Patrick O'Connell
 CS 342: Software Design
 Program 1
 oconne16
 667792610
 */


import java.util.Random;

public class Cards {	//This class contains methods that shuffle and deal cards
		// Takes the deck array and assigns the associated integer values for every card to the card array.
		public static void initialize_deck(int[] cardsUsed, String[] cardsArray, String[] numsArray, String[] suitsArray, int[] suitsArrayInts, int[] numsArrayInts, int[] cardsArrayInts){
			int h, i;																		//loop variables
			for (h= 0; h < 4; h++) {
		    	  for (i = 0; i < 13; i++) {
		    		  cardsArray[i + h*13] =  numsArray[i] + suitsArray[h];					//initializes the cards array with the associated strings
		    		  cardsArrayInts[i+h*13] = numsArrayInts[i]*10 + suitsArrayInts[h];		//initializes the integer cards array with the associated integers
		    		  cardsUsed[i+h*13] = 0;												//initializes the used cards array to 0
			      }
		      }
		}
		
		
		//This function takes the deck array and deals cards to the players
		public static void deal_to_user_and_cpus(int[] cardsUsed, int[] player1cardsArray, int[] cardsArrayInts, int numCPUs, int[] cpucardsArray) {
			int random;												//used for randomizing deck order
		      Random rand = new Random();
		      int i;												//loop var
		      for (i = 0; i < 5; i++) {
		    	  random = rand.nextInt(52) + 0;					//next random card
		    	  while (cardsUsed[random] != 0) {
		    		  random = rand.nextInt(52) + 0;
		    	  }
		    	  cardsUsed[random] = 1;							//card x is dealt, don't deal it to anyone else
		    	  player1cardsArray[i] = cardsArrayInts[random];	//deal cards to the user
		      }
		      
		      for (i = 0; i < 5*numCPUs; i++) {						//deal cards to all of the CPUs
		    	  random = rand.nextInt(52) + 0;
		    	  while (cardsUsed[random] != 0) {
		    		  random = rand.nextInt(52) + 0;
		    	  }
		    	  cardsUsed[random] = 1;							//card x is dealt, don't deal it to anyone else
		    	  cpucardsArray[i] = cardsArrayInts[random];
		      }
		}
}
