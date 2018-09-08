/*
 Patrick O'Connell
 CS 342: Software Design
 Program 1
 oconne16
 667792610
 */
import java.util.Random;
import java.util.Scanner;


public class Discard_Pile {	//This class keeps track of all used cards so that they are not redealt in the same hand (creates a discard pile).	
	
		//This function is used when the user wants to draw cards
		public static void discard_and_draw( int[] player1cardsArray, int[] cpucardsArray, int[] cardsArrayInts, int[] cardsUsed, int numCPUs, Scanner scan) {
			
			int maxDraw = 3;
			int i;
			if (player1cardsArray[4]/10 == 14) {
				System.out.println("Since you have an Ace you can keep the Ace and discard the other four cards.");
				maxDraw = 4;
			}
			System.out.println("How many cards are you going to draw? Enter a number from 0 to " +maxDraw);

		    int cardNum = scan.nextInt();
		    while(cardNum<0 || cardNum>maxDraw) {
	    		System.out.println("Enter a valid number of cards to discard.");
	    		cardNum = scan.nextInt();
	    	}
		    if (cardNum == 0) {	    
		    	return;
		    }
		    else {
		    	System.out.println("List the number(s) of the " +cardNum+ " card(s) that you would like to discard (values from 1-5 accepted):");
			    int random;																					//int used for random card from shuffled deck
			    Random rand = new Random();
			    int cardIndex;																				//index that is being updated in the user hand
			    for (i = 0; i < cardNum; i++) {
			    	cardIndex = scan.nextInt();
			    	while(cardIndex<1 || cardIndex>5) {
			    		System.out.println("Enter a valid card index number... 1, 2, 3, 4, or 5.");
			    		cardIndex = scan.nextInt();
			    	}
			    	random = rand.nextInt(52) + 0;
			    	  while (cardsUsed[random] != 0) {														//look for an unused card
			    		  random = rand.nextInt(52) + 0;
			    	  }
			    	  cardsUsed[random] = 1;																//card used, don't use again during the hand
			    	  player1cardsArray[cardIndex-1] = cardsArrayInts[random];								//set user hand array to new card
			    }
			
			    User_Player.sort_user_hand(player1cardsArray);												//after drawing, sort the new user hand in ascending order
		    }
		    
		}
}
