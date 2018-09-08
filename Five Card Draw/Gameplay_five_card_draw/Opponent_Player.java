/*
 Patrick O'Connell
 CS 342: Software Design
 Program 1
 oconne16
 667792610
 */




public class Opponent_Player {	//All computer player methods are here (determining cpu hand, sorting the hand for later display, printing hands)
	
		//This function sorts the player hands according to rank in ascending order
		public static void sort_cpu_hands(int numCPUs, int[] cpucardsArray) {
			int h, i;														//loop vars
			int temp;														//temp swap variable
			int continueVar = 1;											//if continueVar is one, the sort will iterate 1 or more times
		      for (h = 0; h < numCPUs; h++) {
		    	  continueVar = 1;
			      while (continueVar == 1) {								//hand is not yet sorted, continue swaps
			    	  continueVar = 0;
		    		  for (i = 0; i < 4; i++) {
				    	  if (cpucardsArray[5*h+i] > cpucardsArray[5*h+i+1]) {
				    		  continueVar = 1;								//swap will occur, another iteration required
				    		  temp = cpucardsArray[5*h+i+1];
				    		  cpucardsArray[5*h+i+1] = cpucardsArray[5*h+i];
				    		  cpucardsArray[5*h+i] = temp;					//finish swap
				    	  }
				      } 
			      }
		      }
		}
		
		
		//Determines the value of the hands of the players given the player hand array
		public static void determine_cpu_hands(int[] player1cardsArray, int[] cpucardsArray, int numCPUs, int[] handValues) {
			int diff, i, j;																//loop var
			int totalDuplicates = 0;													//counts the number of matches in the hands
			int similarityCount = 0;													//counts the total number of cards with the same rank
			int maxSim = 0;																//used to determine hand rank
			int lastSimilar = 0;														//gatekeeper var
			int flush = 1;																//flush tracker var
			int straight = 1; 															//straight tracker var
			for (j = 1; j < numCPUs+1; j++) {
				totalDuplicates = 0;
				similarityCount = 0;
				maxSim = 0;
				lastSimilar = 0;
				flush = 1;
				straight = 1;
				diff = ((cpucardsArray[4 + 5*(j-1)]/10)-(cpucardsArray[0+ 5*(j-1)]/10));
				if (diff == 4) {														//condition necessary for a straight to exist
					for(i = 0; i < 4; i++) {
						if ((cpucardsArray[i+ 5*(j-1)]/10 - cpucardsArray[i+ 5*(j-1)+1]/10)!= -1) {
							straight = 0;												//if code executes, the hand is not a straight
						}
					}
				}
				else {
					straight = 0;														//if code executes, the hand is not a straight
				}
				for (i = 0; i < 4; i++) {
					if ((cpucardsArray[i+ 5*(j-1)]%10 != cpucardsArray[i+ 5*(j-1)+1]%10)) {
						flush = 0;														//if code executes, the hand is not a flush
					}
				}
				
				
				
				for (i = 0; i < 4; i++) {
					if ((cpucardsArray[i+ 5*(j-1)]/10 == cpucardsArray[i+ 5*(j-1)+1]/10)) {
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
					handValues[j] = 8;														//hand is a straight flush
				}
				else if (totalDuplicates == 3 && maxSim == 3) {
					handValues[j] = 7;														//hand is four of a kind
				}
				else if (totalDuplicates == 3 && maxSim == 2) {
					handValues[j] = 6;														//hand is a full house
				}
				else if (flush == 1) {
					handValues[j] = 5;														//hand is a flush
				}
				else if (straight == 1) {
					handValues[j] = 4;														//hand is a straight
				}
				else if (totalDuplicates == 2 && maxSim == 2) {
					handValues[j] = 3;														//hand is three of a kind
				}
				else if (totalDuplicates == 2 && maxSim == 1) {
					handValues[j] = 2;														//hand is a two pair
				}
				else if (totalDuplicates == 1 && maxSim == 1) {
					handValues[j] =1;														//hand is a pair
				}
				
				else {
					handValues[j] = 0;														//hand is a high card
				}
			}
			
			
		}
		
		
		//prints the computer hands given the associated arrays and the number of CPUs in the game
		public static void print_cpu_hand(int[] cpucardsArray, String[] numsArray, String[] suitsArray, int numCPUs, int[] handValues) {
			int comp = numCPUs + 1;											//increment cpus by one for printing purposes
			System.out.println("Computer #" +comp+ "'s hand:	1) "+numsArray[(cpucardsArray[0+5*numCPUs]/10)-2]+suitsArray[(cpucardsArray[0+5*numCPUs]%10)-1]+ " 2) "+numsArray[(cpucardsArray[1+5*numCPUs]/10)-2]+suitsArray[(cpucardsArray[1+5*numCPUs]%10)-1]+ " 3) "+numsArray[(cpucardsArray[2+5*numCPUs]/10)-2]+suitsArray[(cpucardsArray[2+5*numCPUs]%10)-1]+ " 4) "+numsArray[(cpucardsArray[3+5*numCPUs]/10)-2]+suitsArray[(cpucardsArray[3+5*numCPUs]%10)-1]+ " 5) "+numsArray[(cpucardsArray[4+5*numCPUs]/10)-2]+suitsArray[(cpucardsArray[4+5*numCPUs]%10)-1]);

			if (handValues[comp] == 8) {
				System.out.println("				(Straight Flush)");		//cpu hand rank will print
				
			}
			else if (handValues[comp] == 7) {
				System.out.println("				(Four of a kind)");		//cpu hand rank will print
			
			}
			else if (handValues[comp] == 6) {
				System.out.println("				(Full house)");			//cpu hand rank will print
				
			}
			else if (handValues[comp] == 5) {
				System.out.println("				(Flush)");				//cpu hand rank will print
		
			}
			else if (handValues[comp] == 4) {
				System.out.println("				(Straight)");			//cpu hand rank will print
			
			}
			else if (handValues[comp] == 3) {
				System.out.println("				(Three of a kind)");	//cpu hand rank will print
			
			}
			else if (handValues[comp] == 2) {
				System.out.println("				(Two Pair)");			//cpu hand rank will print
				
			}
			else if (handValues[comp] == 1) {
				System.out.println("				(One Pair)");			//cpu hand rank will print
				
			}
			else {
				System.out.println("				(High Card)");			//cpu hand rank will print
			}
		}
}
