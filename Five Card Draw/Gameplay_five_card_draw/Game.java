/*
 Patrick O'Connell
 CS 342: Software Design
 Program 1
 oconne16
 667792610
 */

import java.util.Scanner;
import java.util.Random;

public class Game {//This class contains main and all calls to the classes necessary for a full hand of 5 card draw.

	public static void main(String args[]) {
		
		int i;																				//loop var
		int continueVar = 1;																//gatekeeper var-> continues while loop
		
		String[] numsArray = {"2","3","4","5","6","7","8","9","T","J","Q","K","A"};			//array used for printing hands
		String[] suitsArray = {"C","D","H","S"};											//array used for printing suits
	    int[] numsArrayInts = {2,3,4,5,6,7,8,9,10,11,12,13,14};								//integers associated with every card
	    int[] player1cardsArray = new int[5];												//player hand array
	    String[] cardsArray = new String[52];												//deck array with string values
	    int[] cardsArrayInts = new int[52];													//deck array with int values
	    int[] cpucardsArray = new int[15];													//cpu hands
	    int[] suitsArrayInts = {1,2,3,4};													//suit integer array
	    int[] cardsUsed = new int[52];														//discarded pile
	    int[] handValues = new int[4];														//array that stores hand ranks for all players
	    
	    System.out.println("WELCOME TO FIVE CARD DRAW!"); 									//begin game dialogue
	    System.out.println("How many computer opponents would you like to play against?");
	    System.out.println("(Please enter 1, 2, or 3):");
	      
	    Scanner scan = new Scanner(System.in); 
	    int numCPUs = scan.nextInt();														//user inputs number of desired computer opponents
	    while(numCPUs > 3 || numCPUs < 1) {
	    	  System.out.println("(Please enter a valid number of CPUs... 1, 2, or 3):");
	    	  numCPUs = scan.nextInt();
	    }
	    
	    System.out.println("You are playing against " +numCPUs +" player(s)." );
	    
	    while (continueVar == 1) {															//game loop executes so long as user still wants to play
	    	System.out.println("" );
	    	System.out.println("-------------------------------------------------------------------" );
	    	System.out.println("" );
	    	System.out.println("Shuffle up and deal! <Enter>");
	    	scan.nextLine();
	    	scan.nextLine();
	    	Cards.initialize_deck(cardsUsed, cardsArray, numsArray, suitsArray, suitsArrayInts, numsArrayInts, cardsArrayInts);		//cards shuffled
		    Cards.deal_to_user_and_cpus(cardsUsed, player1cardsArray, cardsArrayInts, numCPUs, cpucardsArray);						//cards dealt to players
		    System.out.println("The cards have now been dealt and the computers are now choosing which cards to discard.");
		    System.out.println("Pick up your hand to begin. <Enter>");
		   
		    
		    scan.nextLine();
		    User_Player.sort_user_hand(player1cardsArray); 																			//sort user hand
		    Opponent_Player.sort_cpu_hands(numCPUs, cpucardsArray);																	//sort computer hands
		    User_Player.print_user_hand(player1cardsArray, numsArray, suitsArray);													//display user hand to user
		    Opponent_Player.determine_cpu_hands(player1cardsArray, cpucardsArray, numCPUs, handValues);								//determine hand ranks of the cpus
		    User_Player.determine_hands(player1cardsArray, cpucardsArray, numCPUs, handValues);										//determine the hand rank of the user
		    Discard_Pile.discard_and_draw(player1cardsArray, cpucardsArray, cardsArrayInts, cardsUsed, numCPUs, scan);				//allow user to draw/discard cards
		    User_Player.print_user_hand(player1cardsArray, numsArray, suitsArray);													//show resulting draw to user
		    User_Player.determine_hands(player1cardsArray, cpucardsArray, numCPUs, handValues);										//redeterimine hand ranks
		    System.out.println("");
			System.out.println("It is now the CPU's turn to act. <Enter>");	
			scan.nextLine();
			scan.nextLine();
		   
			
			Artificial_Intelligence.artificial_Intelligence(cpucardsArray, numCPUs, cardsArrayInts, cardsUsed, handValues, scan);	//computers now able to draw based on designated strategy
		    Opponent_Player.sort_cpu_hands(numCPUs, cpucardsArray);																	//resort cpu hands after draws
		    User_Player.determine_hands(player1cardsArray, cpucardsArray, numCPUs, handValues);										//redetermine hands after draw
		    Opponent_Player.determine_cpu_hands(player1cardsArray, cpucardsArray, numCPUs, handValues);								//redetermine cpu hands after draw
	        System.out.println("Ladies and gentlemen, reveal your hands to see who won. <Enter>");
	       
	        
	        scan.nextLine();
	        System.out.println("Your Hand:              1) " +numsArray[(player1cardsArray[0]/10)-2]+suitsArray[(player1cardsArray[0]%10)-1]+ " 2) "+numsArray[(player1cardsArray[1]/10)-2]+suitsArray[(player1cardsArray[1]%10)-1]+ " 3) "+numsArray[(player1cardsArray[2]/10)-2]+suitsArray[(player1cardsArray[2]%10)-1]+ " 4) "+numsArray[(player1cardsArray[3]/10)-2]+suitsArray[(player1cardsArray[3]%10)-1]+ " 5) "+numsArray[(player1cardsArray[4]/10)-2]+suitsArray[(player1cardsArray[4]%10)-1]);
	        User_Player.print_user_hand_val(handValues);																			//print user and player hands with their associated hand ranks also plrint
	        System.out.println("");
	        for (i = 0; i < numCPUs; i++) {
	        	Opponent_Player.print_cpu_hand(cpucardsArray, numsArray, suitsArray, i, handValues);
	      	  System.out.println("");
	        }
	        Artificial_Intelligence.determine_winner(player1cardsArray, cpucardsArray, numCPUs, cardsArrayInts, cardsUsed, handValues);	//print winner or print winners if a tie has occurred
	        System.out.println("Enter 1 to continue playing.  Enter any other number to terminate the game:" );							//user may continue by entering 1
	        continueVar = scan.nextInt();
	     }
	    
	   }

	

}
