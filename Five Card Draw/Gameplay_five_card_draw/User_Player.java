/*
 Patrick O'Connell
 CS 342: Software Design
 Program 1
 oconne16
 667792610
 */




public class User_Player {	//All user player methods are here (drawing, determining user hand, sorting the user hand for display, printing hand)
		
		//This function sorts the user hand according to rank in ascending order
		public static void sort_user_hand(int[] player1cardsArray) {
			int i;															//loop var
			int continueVar = 1;											//if continueVar is one, the sort will iterate 1 or more times
		      int temp;														//temp swap variable
		      while (continueVar == 1) {
		    	  continueVar = 0;
		    	  for (i = 0; i < 4; i++) {
			    	  if (player1cardsArray[i] > player1cardsArray[i+1]) {	//swap cards if they are out of order
			    		  continueVar = 1;									//swap will occur, another iteration required
			    		  temp = player1cardsArray[i+1];	
			    		  player1cardsArray[i+1] = player1cardsArray[i];
			    		  player1cardsArray[i] = temp;
			    	  }
			      }
		      }
		}
		
		
		//Determines the value of the hand of the user given the user hand array
		public static void determine_hands(int[] player1cardsArray, int[] cpucardsArray, int numCPUs, int[] handValues) {
			int diff, i;																//loop var
			int totalDuplicates = 0;													//counts the number of matches in the hands
			int similarityCount = 0;													//counts the total number of cards with the same rank
			int maxSim = 0;																//used to determine hand rank
			int lastSimilar = 0;														//gatekeeper var
			int flush = 1;																//flush tracker var
			int straight = 1; 															//straight tracker var
			
			diff = ((player1cardsArray[4]/10)-(player1cardsArray[0]/10));
			if (diff == 4) {															//condition necessary for a straight to exist
				for(i = 0; i < 4; i++) {
					if ((player1cardsArray[i]/10 - player1cardsArray[i+1]/10)!= -1) {
						straight = 0;													//if code executes, the hand is not a straight
					}
				}
			}
			else {
				straight = 0;															//if code executes, the hand is not a straight
			}
			for (i = 0; i < 4; i++) {
				if ((player1cardsArray[i]%10 != player1cardsArray[i+1]%10)) {			//if code executes, the hand is not a flush
					flush = 0;
				}
			}
			
			
			
			for (i = 0; i < 4; i++) {
				if ((player1cardsArray[i]/10 == player1cardsArray[i+1]/10)) {			//counts total similarities and card pairs
					if(lastSimilar != 0) {
						totalDuplicates++;												//total pairs regardless of rank
						similarityCount++;												//total cards that have pairs
						lastSimilar++;													//gatekeeper var incremented
					}
					else {
						totalDuplicates++;												//increment number of pairs counter
						similarityCount++;												//increment pair counter
						lastSimilar = 1;
					}
					if (similarityCount > maxSim) {
						maxSim = similarityCount;										//set maxSimilarity to similarity count
					}
				}
				else {
					lastSimilar = 0;													//last two cards compared were not a pair
					similarityCount = 0;
				}
				
			}
			if (flush == 1 && straight == 1) {
				handValues[0] = 8;														//hand is a straight flush
				return;
			}
			else if (totalDuplicates == 3 && maxSim == 3) {
				handValues[0] = 7;														//hand is four of a kind
				return;
			}
			else if (totalDuplicates == 3 && maxSim == 2) {
				handValues[0] = 6;														//hand is a full house
				return;
			}
			else if (flush == 1) {
				handValues[0] = 5;														//hand is a flush
				return;
			}
			else if (straight == 1) {
				handValues[0] = 4;														//hand is a straight
				return;
			}
			else if (totalDuplicates == 2 && maxSim == 2) {
				handValues[0] = 3;														//hand is three of a kind
				return;
			}
			else if (totalDuplicates == 2 && maxSim == 1) {
				handValues[0] = 2;														//hand is two pair
				return;
			}
			else if (totalDuplicates == 1 && maxSim == 1) {
				handValues[0] = 1;														//hand is a pair
				return;
			}
			
			else {
				handValues[0] = 0;														//hand is a high card
				return;
			}
			
		}
		
		
		//prints the user hand given the user hand array and the string arrays
		public static void print_user_hand(int[] player1cardsArray, String[] numsArray, String[] suitsArray) {
			System.out.println("The cards in your hand are: 1) " +numsArray[(player1cardsArray[0]/10)-2]+suitsArray[(player1cardsArray[0]%10)-1]+ " 2) "+numsArray[(player1cardsArray[1]/10)-2]+suitsArray[(player1cardsArray[1]%10)-1]+ " 3) "+numsArray[(player1cardsArray[2]/10)-2]+suitsArray[(player1cardsArray[2]%10)-1]+ " 4) "+numsArray[(player1cardsArray[3]/10)-2]+suitsArray[(player1cardsArray[3]%10)-1]+ " 5) "+numsArray[(player1cardsArray[4]/10)-2]+suitsArray[(player1cardsArray[4]%10)-1]);
			System.out.println("");
		}
		
		//given the hand values array, this function prints the rank of the users hand to output
		public static void print_user_hand_val(int[] handValues) {
			int comp = 0;
			if (handValues[comp] == 8) {
				System.out.println("				(Straight Flush)");		//user hand rank will print
				
			}
			else if (handValues[comp] == 7) {
				System.out.println("				(Four of a kind)");		//user hand rank will print
			
			}
			else if (handValues[comp] == 6) {
				System.out.println("				(Full house)");			//user hand rank will print
				
			}
			else if (handValues[comp] == 5) {
				System.out.println("				(Flush)");				//user hand rank will print
		
			}
			else if (handValues[comp] == 4) {
				System.out.println("				(Straight)");			//user hand rank will print
			
			}
			else if (handValues[comp] == 3) {
				System.out.println("				(Three of a kind)");	//user hand rank will print
			
			}
			else if (handValues[comp] == 2) {
				System.out.println("				(Two Pair)");			//user hand rank will print
				
			}
			else if (handValues[comp] == 1) {
				System.out.println("				(One Pair)");			//user hand rank will print
				
			}
			else {
				System.out.println("				(High Card)");			//user hand rank will print
			}
		}
}
