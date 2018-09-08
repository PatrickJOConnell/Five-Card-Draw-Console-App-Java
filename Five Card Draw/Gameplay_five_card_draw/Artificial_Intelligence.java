/*
 Patrick O'Connell
 CS 342: Software Design
 Program 1
 oconne16
 667792610
 */
import java.util.Random;
import java.util.Scanner;


public class Artificial_Intelligence {	//This class holds all of the 'intelligent' CPU methods used for drawing cards depending on the CPU hand
		
		//given the tie array, this method is called when there are multiple winners
		public static void tie(int[] tieArray) {
			System.out.println("The following players have tied:");			//potentially all four players could tie (theoretically)
			if (tieArray[0] == 1) {											//any of the following could execute depending on the number of players with identical hands
				System.out.println("	User");
			}
			if (tieArray[1] == 1) {
				System.out.println("	CPU 1");
			}
			if (tieArray[2] == 1) {
				System.out.println("	CPU 2");
			}
			if (tieArray[3] == 1) {
				System.out.println("	CPU 3");
			}
		}
		
		
		//given the tie array, this method is called when there is a single winner
		public static void win(int[] tieArray) {
			
			if (tieArray[0] == 1) {												//only one of the following will execute because we only have one winner
				System.out.println("User has won the hand!");					
			}
			if (tieArray[1] == 1) {
				System.out.println("CPU 1 has won the hand.  Try again.");
			}
			if (tieArray[2] == 1) {
				System.out.println("CPU 2 has won the hand.  Try again.");
			}
			if (tieArray[3] == 1) {
				System.out.println("CPU 3 has won the hand.  Try again.");
			}
			System.out.println("" );
	    	System.out.println("-------------------------------------------------------------------" );
	    	System.out.println("" );
		}
	
		
		//this helper method is called by the determine winner method and will either call the tie or win method depending on the payer hands
		public static void tiebreak_helper(double userHand,double cpu1Hand,double cpu2Hand,double cpu3Hand, int[] tieArray, double maxHandVal) {
			int sum = 0;							//int used to see how many players have tied
			maxHandVal = userHand;					//records the maximum value of player hands
			if (tieArray[0] == 0) {
				userHand = 0;						//if player doesn't have one of the top hands, set hand to value 0
			}
			if (tieArray[1] == 0) {
				cpu1Hand = 0;						//if player doesn't have one of the top hands, set hand to value 0
			}
			if (tieArray[2] == 0) {
				cpu2Hand = 0;						//if player doesn't have one of the top hands, set hand to value 0
			}
			if (tieArray[3] == 0) {					
				cpu3Hand = 0;						//if player doesn't have one of the top hands, set hand to value 0
			}
			if (cpu1Hand > maxHandVal) {
				tieArray[0] = 0;
				maxHandVal = cpu1Hand;				//update max hand value to determine winner
			}
			else if (cpu1Hand == maxHandVal) {
				//do nothing
			}
			else {
				tieArray[1] = 0;
			}
			if (cpu2Hand > maxHandVal) {			//check for better hand in CPU2
				tieArray[0] = 0;
				tieArray[1] = 0;
				maxHandVal = cpu2Hand;
			}
			else if (cpu2Hand == maxHandVal) {
				//do nothing
			}
			else {
				tieArray[2] = 0;
			}
			if (cpu3Hand > maxHandVal) {			//check for better hand in CPU3
				tieArray[0] = 0;
				tieArray[1] = 0;
				tieArray[2] = 0;
				maxHandVal = cpu3Hand;
			}
			else if (cpu3Hand == maxHandVal) {
				//do nothing
			}
			else {
				tieArray[3] = 0;
			}
			if (maxHandVal == userHand) {			//count all of the hands that are still tied
				sum++;
			}
			if (maxHandVal == cpu1Hand) {
				sum++;
			}
			if (maxHandVal == cpu2Hand) {
				sum++;
			}
			if (maxHandVal == cpu3Hand) {
				sum++;
			}
			if (sum > 1) {
				tie(tieArray);						//we have a tie, call tie method
			}
			else {
				win(tieArray);						//we have a winner, call winner method
			}		
		}
		
	
		//method called when there are multiple players with the same hand rank
		public static void tie_break(int[] player1cardsArray, int[] cpucardsArray, int numCPUs, int[] cardsArrayInts, int[] cardsUsed, int[] handValues, int[] tieArray) {
			
			int i;																				//loop var
			int max = -1;																		//initialize max var
			double userHand = 0;																//user hand double to be compared with other hands
			double cpu1Hand = 0;																//cpu1 hand double to be compared with other hands
			double cpu2Hand = 0;																//cpu2 hand double to be compared with other hands
			double cpu3Hand = 0;																//cpu3 hand double to be compared with other hands
			double maxHandVal = 0;
			for (i = 0; i < numCPUs; i++) {
				if (handValues[i] > max) {														//find max hand value 
					max = handValues[i];
				}
			}
			for (i = 0; i < 5; i++) {															//initialize all hand values for later editing and comparisons
				userHand = userHand + (player1cardsArray[i]/10)*(Math.pow(10, (2*i)));			
				cpu1Hand = cpu1Hand + (cpucardsArray[i]/10)*(Math.pow(10, (2*i)));
				cpu2Hand = cpu2Hand + (cpucardsArray[i+5]/10)*(Math.pow(10, (2*i)));
				cpu3Hand = cpu3Hand + (cpucardsArray[i+10]/10)*(Math.pow(10, (2*i)));
			}
			
			if (max == 0 || max == 4 || max == 5 || max == 8) {									//High cards, straights, flushes, and straight flushes are all handled the same
				tiebreak_helper(userHand,cpu1Hand,cpu2Hand,cpu3Hand, tieArray, maxHandVal);
			}
			else if (max == 7) {																//if for of a kind, bring the four to the front of the double
				if ((int)(userHand%100) == ((int)(userHand/100))%100) {							//four of a kind is at start of hand
					userHand = userHand%100000000;
				}
				else {
					userHand = (int)userHand/100;												//four of a kind is at end of hand
				}
				
				if ((int)(cpu1Hand%100) == ((int)(cpu1Hand/100))%100) {							//four of a kind is at start of hand
					cpu1Hand = cpu1Hand%100000000;
				}
				else {
					cpu1Hand = (int)cpu1Hand/100;												//four of a kind is at end of hand
				}
				
				if ((int)(cpu2Hand%100) == ((int)(cpu2Hand/100))%100) {							//four of a kind is at start of hand
					cpu2Hand = cpu2Hand%100000000;
				}
				else {
					cpu2Hand = (int)cpu2Hand/100;												//four of a kind is at end of hand
				}
				
				if ((int)(cpu3Hand%100) == ((int)(cpu3Hand/100))%100) {							//four of a kind is at start of hand
					cpu3Hand = cpu3Hand%100000000;
				}
				else {
					cpu3Hand = (int)cpu3Hand/100;												//four of a kind is at end of hand
				}
				tiebreak_helper(userHand,cpu1Hand,cpu2Hand,cpu3Hand, tieArray, maxHandVal);		//determine winner
			}
			else if (max == 6 || max ==3) {														//full house and three of a kind will never tie with another of the same rank
				userHand = (int)(userHand%1000000)/10000;										//need to only compare the middle cards of the sorted hands
				cpu1Hand = (int)(cpu1Hand%1000000)/10000;
				cpu2Hand = (int)(cpu2Hand%1000000)/10000;
				cpu3Hand = (int)(cpu3Hand%1000000)/10000;
				tiebreak_helper(userHand,cpu1Hand,cpu2Hand,cpu3Hand, tieArray, maxHandVal);		//determine winner
			}
			else if (max == 1) {																//if there is a pair
				if ((int)(userHand%100) == ((int)(userHand/100))%100) {							//pair is the last two cards in the hand
					userHand = (int)(userHand%100)*1000000 + (int)(userHand/10000);
				}
				else if (((int)(userHand/100))%100 == ((int)(userHand/10000))%100){				//pair is the 3rd and 4th cards in the hand
					userHand = (int)((userHand/100)%100)*1000000 + (int)(userHand%100) + (int)(userHand/10000);
				}
				else if (((int)(userHand/10000))%100 == ((int)(userHand/1000000))%100){			//pair is the 2nd and 3rd cards in the hand
					userHand = (int)((userHand/10000)%100)*1000000 + (int)(userHand%10000) + (int)(userHand/10000);
				}
				else if (((int)(userHand/1000000))%100 == ((int)(userHand/100000000))%100){		//pair is the 1st and 2nd cards in the hand
					
				}
				if ((int)(cpu1Hand%100) == ((int)(cpu1Hand/100))%100) {							//pair is the last two cards in the hand
					cpu1Hand = (int)(cpu1Hand%100)*1000000 + (int)(cpu1Hand/10000);
				}
				else if (((int)(cpu1Hand/100))%100 == ((int)(cpu1Hand/10000))%100){				//pair is the 3rd and 4th cards in the hand
					cpu1Hand = (int)((cpu1Hand/100)%100)*1000000 + (int)(cpu1Hand%100) + (int)(cpu1Hand/10000);
				}
				else if (((int)(cpu1Hand/10000))%100 == ((int)(cpu1Hand/1000000))%100){			//pair is the 2nd and 3rd cards in the hand
					cpu1Hand = (int)((cpu1Hand/10000)%100)*1000000 + (int)(cpu1Hand%10000) + (int)(cpu1Hand/10000);
				}
				else if (((int)(cpu1Hand/1000000))%100 == ((int)(cpu1Hand/100000000))%100){		//pair is the 1st and 2nd cards in the hand
					
				}
				
				if ((int)(cpu2Hand%100) == ((int)(cpu2Hand/100))%100) {							//pair is the last two cards in the hand
					cpu2Hand = (int)(cpu2Hand%100)*1000000 + (int)(cpu2Hand/10000);
				}
				else if (((int)(cpu2Hand/100))%100 == ((int)(cpu2Hand/10000))%100){				//pair is the 3rd and 4th cards in the hand
					cpu2Hand = (int)((cpu2Hand/100)%100)*1000000 + (int)(cpu2Hand%100) + (int)(cpu2Hand/10000);
				}
				else if (((int)(cpu2Hand/10000))%100 == ((int)(cpu2Hand/1000000))%100){			//pair is the 2nd and 3rd cards in the hand
					cpu2Hand = (int)((cpu2Hand/10000)%100)*1000000 + (int)(cpu2Hand%10000) + (int)(cpu2Hand/10000);
				}
				else if (((int)(cpu2Hand/1000000))%100 == ((int)(cpu2Hand/100000000))%100){		//pair is the 1st and 2nd cards in the hand
					
				}
				
				if ((int)(cpu3Hand%100) == ((int)(cpu3Hand/100))%100) {							//pair is the last two cards in the hand
					cpu3Hand = (int)(cpu3Hand%100)*1000000 + (int)(cpu3Hand/10000);
				}
				else if (((int)(cpu3Hand/100))%100 == ((int)(cpu3Hand/10000))%100){				//pair is the 3rd and 4th cards in the hand
					cpu3Hand = (int)((cpu3Hand/100)%100)*1000000 + (int)(cpu3Hand%100) + (int)(cpu3Hand/10000);
				}
				else if (((int)(cpu3Hand/10000))%100 == ((int)(cpu3Hand/1000000))%100){			//pair is the 2nd and 3rd cards in the hand
					cpu3Hand = (int)((cpu3Hand/10000)%100)*1000000 + (int)(cpu3Hand%10000) + (int)(cpu3Hand/10000);
				}
				else if (((int)(cpu3Hand/1000000))%100 == ((int)(cpu3Hand/100000000))%100){		//pair is the 1st and 2nd cards in the hand
					
				}
				tiebreak_helper(userHand,cpu1Hand,cpu2Hand,cpu3Hand, tieArray, maxHandVal);
			}
			
			else if (max == 2) {																																//Two pairs
				if (((int)(userHand%100) == ((int)(userHand/100))%100) && (((int)(userHand/1000000))%100 == ((int)(userHand/100000000))%100)) {					//the one unmatched card is card three
					userHand = (int)(userHand%10000)*100 + ((int)(userHand/1000000))*1000000 + ((int)(userHand/1000))%100 ; 
				}
				else if (((int)(userHand%100) == ((int)(userHand/100))%100) && (((int)(userHand/1000000))%100 != ((int)(userHand/100000000))%100)){				//the one unmatched card is card one
					userHand = (int)(userHand/100000000) + ((int)(userHand%100000000))*100; 
				}
				
				if (((int)(cpu1Hand%100) == ((int)(cpu1Hand/100))%100) && (((int)(cpu1Hand/1000000))%100 == ((int)(cpu1Hand/100000000))%100)) {					//the one unmatched card is card three
					cpu1Hand = (int)(cpu1Hand%10000)*100 + ((int)(cpu1Hand/1000000))*1000000 + ((int)(cpu1Hand/1000))%100 ; 
				}
				else if (((int)(cpu1Hand%100) == ((int)(cpu1Hand/100))%100) && (((int)(cpu1Hand/1000000))%100 != ((int)(cpu1Hand/100000000))%100)){				//the one unmatched card is card one
					cpu1Hand = (int)(cpu1Hand/100000000) + ((int)(cpu1Hand%100000000))*100; 
				}
				
				if (((int)(cpu2Hand%100) == ((int)(cpu2Hand/100))%100) && (((int)(cpu2Hand/1000000))%100 == ((int)(cpu2Hand/100000000))%100)) {					//the one unmatched card is card three
					cpu2Hand = (int)(cpu2Hand%10000)*100 + ((int)(cpu2Hand/1000000))*1000000 + ((int)(cpu2Hand/1000))%100 ; 
				}
				else if (((int)(cpu2Hand%100) == ((int)(cpu2Hand/100))%100) && (((int)(cpu2Hand/1000000))%100 != ((int)(cpu2Hand/100000000))%100)){				//the one unmatched card is card one
					cpu2Hand = (int)(cpu2Hand/100000000) + ((int)(cpu2Hand%100000000))*100; 
				}
				
				if (((int)(cpu3Hand%100) == ((int)(cpu3Hand/100))%100) && (((int)(cpu3Hand/1000000))%100 == ((int)(cpu3Hand/100000000))%100)) {					//the one unmatched card is card three
					cpu3Hand = (int)(cpu3Hand%10000)*100 + ((int)(cpu3Hand/1000000))*1000000 + ((int)(cpu3Hand/1000))%100 ; 
				}
				else if (((int)(cpu3Hand%100) == ((int)(cpu3Hand/100))%100) && (((int)(cpu3Hand/1000000))%100 != ((int)(cpu3Hand/100000000))%100)){				//the one unmatched card is card one
					cpu3Hand = (int)(cpu3Hand/100000000) + ((int)(cpu3Hand%100000000))*100; 
				}
				
				tiebreak_helper(userHand,cpu1Hand,cpu2Hand,cpu3Hand, tieArray, maxHandVal);																		//tie break the winning hands
			}
			
		}
		
		
		//this method takes the cpu cards array and according to the directions of the assignment, draws certain cards to maximize the cpu chance of winning
		public static void artificial_Intelligence(int[] cpucardsArray, int numCPUs, int[] cardsArrayInts, int[] cardsUsed, int[] handValues, Scanner scan) {
			
			Random rand1 = new Random();
			int random1;																		//int used for drawing cards
			int cont = 0;																		//gatekeeper var
			int comp;																			//computer integer used for printing cpu number
			int cpu = 0;
			int i, j, k, l;																		//loop vars
			int smallStraight = 0;																//records of a small straight exists
			int[] numOfEachSuit = {0,0,0,0};													//used for looking for small straight, counts number of cards in each suit
			int[] discardOrSave = {0, 0, 0, 0, 0};												//array used to determine which cards to discard
			for (i = 0; i < numCPUs; i++) {
				cpu = i+1;
				
				cont = 0;
				for (k = 0; k < 5; k++) {														//reintialize array to all 0s
					discardOrSave[k] = 0;
				}
				if (handValues[i+1] > 3) {														//cpu has a hand with which they would not want to draw a card
					comp = i+1;
					System.out.println("Computer "+comp+ " is not drawing any cards.");
					continue;
				}
				if (handValues[i+1] == 1) {														//cpu has a pair
					for (j = 0; j < 4; j++) {
						if (cpucardsArray[j+ 5*(i)]/10 == cpucardsArray[j+1 + 5*(i)]/10) {
							discardOrSave[j] = 1;
							discardOrSave[j+1] = 1;												//set pair cards to save
							
							
						}
						
						
					}
					for (k = 0; k < 5; k++) {
						if (discardOrSave[k] == 0) {
							random1 = rand1.nextInt(52) + 0;
					    	while (cardsUsed[random1] != 0) {									//draw the random cards
					    		random1 = rand1.nextInt(52) + 0;
					    	}
					    	cardsUsed[random1] = 1;
					    	cpucardsArray[i*5 + k] = cardsArrayInts[random1];
						}
					}
					comp = i+1;
					System.out.println("Computer "+comp+ " drew 3 cards.");						//cpu drew three cards
					continue;
				}
				
				if (handValues[i+1] == 2) {														//cpu has two pair
					
					for (j = 0; j < 4; j++) {
						if (cpucardsArray[j+ 5*(i)]/10 == cpucardsArray[j+1 + 5*(i)]/10);		//set pair cards to save
						discardOrSave[j] = 1;
						discardOrSave[j+1] = 1;
					}
					for (k = 0; k < 5; k++) {
						if (discardOrSave[k] == 0) {
							random1 = rand1.nextInt(52) + 0;									//draw the random cards
					    	while (cardsUsed[random1] != 0) {
					    		random1 = rand1.nextInt(52) + 0;
					    	}
					    	cardsUsed[random1] = 1;
					    	cpucardsArray[i*5 + k] = cardsArrayInts[random1];					//set the new cards in cpu hand
						}
					}
					comp = i+1;
					System.out.println("Computer "+comp+ " drew 1 card.");						//user only drew one card with two pair
					continue;
				}
				
				if (handValues[i+1] == 3) {														//cpu has three of a kind
					for (j = 0; j < 4; j++) {
						if (cpucardsArray[j+ 5*(i)]/10 == cpucardsArray[j+1 + 5*(i)]/10);
						discardOrSave[j] = 1;													//set pair cards to save
						discardOrSave[j+1] = 1;
					}
					for (k = 0; k < 5; k++) {
						if (discardOrSave[k] == 0) {
							random1 = rand1.nextInt(52) + 0;
					    	while (cardsUsed[random1] != 0) {									//draw the random cards
					    		random1 = rand1.nextInt(52) + 0;
					    	}
					    	cardsUsed[random1] = 1;
					    	cpucardsArray[i*5 + k] = cardsArrayInts[random1];					//set the new cards in cpu hand
						}
					}
					comp = i+1;
					System.out.println("Computer "+comp+ " drew 2 cards.");						//user only drew two cards with three of a kind
					continue;
				}
				
				
				for (k = 0; k < 4; k++){
					numOfEachSuit[k] = 0;														//set suit array to all zero
				}
				
				for (j = 0; j < 5; j++) {
					numOfEachSuit[((cpucardsArray[j+ 5*(i)]-1)%10)]++;							//count the number of all suits in the specified hand
				}
				for (k = 0; k < 4; k++) {
					if (numOfEachSuit[k] == 4) {												//four of the same suit detected
						for (l = 0; l < 5; l++) {
							if ((cpucardsArray[l+ 5*(i)]-1)%10 != k) {
																								//draw one card
						    	random1 = rand1.nextInt(52) + 0;
						    	  while (cardsUsed[random1] != 0) {
						    		  random1 = rand1.nextInt(52) + 0;							
						    	  }
						    	  cardsUsed[random1] = 1;										//set card to used
						    	  cpucardsArray[i*5 + l] = cardsArrayInts[random1];
						    	  cont = 1;														//set cont to continue
							}
						}
					}
				}
				if (cont == 1) {
					comp = i+1;
					System.out.println("Computer "+comp+ " drew 1 card.");						//cpu only drew a single card, hoping for a flush
					continue;
				}
				for(k = 0; k < 3; k++) {
					if ((cpucardsArray[i*5 + k]/10 - cpucardsArray[i*5 + k + 1]/10)== -1) {
						smallStraight++;														//check for small straight at begin
					}
				}
				if (smallStraight == 3) {														//small straight detected, drawing one
					random1 = rand1.nextInt(52) + 0;	
			    	  while (cardsUsed[random1] != 0) {							
			    		  random1 = rand1.nextInt(52) + 0;										//get next card from deck
			    	  }
			    	  cardsUsed[random1] = 1;													//set card to used
			    	  cpucardsArray[i*5 + 4] = cardsArrayInts[random1];
			    	  comp = i+1;
			    	  System.out.println("Computer "+comp+ " drew 1 card.");
			    	  continue;
				}
				smallStraight = 0;
				for(k = 1; k < 4; k++) {
					if ((cpucardsArray[i*5 + k]/10 - cpucardsArray[i*5 + k + 1]/10)== -1) {
						smallStraight++;														//check for small straight at end
					}
				}
				if (smallStraight == 3) {														//small straight detected, drawing one
					random1 = rand1.nextInt(52) + 0;
			    	  while (cardsUsed[random1] != 0) {
			    		  random1 = rand1.nextInt(52) + 0;										//get next card from deck
			    	  }
			    	  cardsUsed[random1] = 1;													//set card to used
			    	  cpucardsArray[i*5] = cardsArrayInts[random1];
			    	  comp = i+1;
			    	  System.out.println("Computer "+comp+ " drew 1 card.");					//user only drew one card hoping for a straight
			    	  continue;
				}
				if (cpucardsArray[i*5 + 4]/10 == 14) {											//check for an ace
					for (k = 0; k < 4; k++) {
						random1 = rand1.nextInt(52) + 0;										//replace four cards if ace is present
				    	  while (cardsUsed[random1] != 0) {
				    		  random1 = rand1.nextInt(52) + 0;									//get next card from deck
				    	  }
				    	  cardsUsed[random1] = 1;												//set card to used
				    	  cpucardsArray[i*5 + k] = cardsArrayInts[random1];
				    	  
					}
					comp = i+1;
					System.out.println("Computer "+comp+ " has an ace and drew 4 cards.");		//ace, drew four cards
					continue;									
				}
				else {																			//every other hand will draw three cards
					for (k = 0; k < 3; k++) {
						random1 = rand1.nextInt(52) + 0;
				    	  while (cardsUsed[random1] != 0) {										//get next card from deck
				    		  random1 = rand1.nextInt(52) + 0;
				    	  }
				    	  cardsUsed[random1] = 1;
				    	  cpucardsArray[i*5 + k] = cardsArrayInts[random1];						//set card to used
				    	  
					}
				}
				comp = i+1;	
				System.out.println("Computer "+comp+ " drew 3 cards.");							//drew three cards
				
			}
		}
		
		//This method determines if there is a single winner or if a tie break is necessary
		public static void determine_winner(int[] player1cardsArray, int[] cpucardsArray, int numCPUs, int[] cardsArrayInts, int[] cardsUsed, int[] handValues) {
			int i;								//loop var
			int sum = 0;						//sums the number of players with the same ranked hand
			
			int maxVal = -1;					//maxVal stores the max hand rank value
			int index = -1;						//index variable stores the location of the max hand rank
			int[] tieArray = {0,0,0,0};			//tie array keeps track of leading ranked hands
			for (i = 0; i < 1+numCPUs; i++) {
				if (maxVal < handValues[i]) {
					maxVal = handValues[i];
					index = i;					//update index to reflect that the max hand rank is stored here
				}
			}
			tieArray[index] = 1;
			for (i = 0; i < 1+numCPUs; i++) {
				if(handValues[i] == maxVal) {
					tieArray[i] = 1;			//this is a potentially winning hand, alter the tie array accordingly
					sum++;
				}
			}
			if (sum == 1) {
				 win(tieArray);					//there is a single winner, call the win function
			}
			else {								//there may be more than one winner, use the tiebreak method
				tie_break(player1cardsArray, cpucardsArray, numCPUs, cardsArrayInts, cardsUsed, handValues, tieArray);
				
			}
		}
		
		
}
