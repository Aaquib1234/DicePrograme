package Dice_Program;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;


public class DiceProgram {

	  public static void main(String[] args) {
	        
	        Scanner sc=new Scanner(System.in);

	        System.out.println("Enter name of 1st player");
	        String firstPlayer=sc.next();
	        System.out.println("Enter name of 2nd player");
	        String secondPlayer=sc.next();

	        int[] firstPlayerDice=rollDice();
	        int[] secondPlayerDice=rollDice();  
	        String firstPlayerCombination=findCombination(firstPlayerDice);
	        String secondPlayerCombination=findCombination(secondPlayerDice);

	        System.out.println("\nBelow are dice values for " + firstPlayer + " -");
	        displayDice(firstPlayerDice);
	        System.out.println("Combination for " + firstPlayer + " is " + firstPlayerCombination + "\n");
	        
	        System.out.println("Below are dice values for " + secondPlayer + " -");
	        displayDice(secondPlayerDice);
	        System.out.println("Combination for " + secondPlayer + " is " + secondPlayerCombination + "\n");
	        
	        String winner = determineWinner(firstPlayerCombination,secondPlayerCombination, firstPlayerDice , secondPlayerDice);
	        
	        System.out.println("Winner is " + winner + " ..! !");

	        sc.close();
	    }
	    private static int[] rollDice() {
	        int[] diceValues = new int[5];
	        Random r = new Random();
	        for (int i = 0; i < 5; i++) {
	            diceValues[i] = r.nextInt(6) + 1; 
	        }
	        return diceValues;
	    }
	    private static String findCombination(int[] dice) {
	        Arrays.sort(dice); 
	        String comb;

	      
	        if (dice[0] == dice[4]) {
	            comb = "Five of a kind";
	        } else if (dice[0] == dice[3] || dice[1] == dice[4]) {
	            comb = "Four of a kind";
	        } else if ((dice[0] == dice[2] && dice[3] == dice[4]) || (dice[0] == dice[1] && dice[2] == dice[4])) {
	            comb = "Three of a kind and a pair";
	        } else if (dice[0] == dice[2] || dice[1] == dice[3] || dice[2] == dice[4]) {
	            comb = "Three of a kind";
	        } else if (dice[0] == dice[1] && dice[2] == dice[3]) {
	            comb = "A pair";
	        } else {
	            comb = "Highest number";
	        }

	        return comb;
	    }

	    private static void displayDice(int[] dice) {
	        for (int i = 0; i < dice.length; i++) {
	            System.out.print(dice[i]);
	            if (i < dice.length - 1) {
	                System.out.print(", ");
	            }
	        }
	        System.out.println();
	    }
	    private static String determineWinner(String combination1, String combination2, int[] dice1, int[] dice2) {
	        
	        int combinationComparison = compareCombinations(combination1, combination2);

	        if (combinationComparison > 0) {
	            return "Player 1";
	        } else if (combinationComparison < 0) {
	            return "Player 2";
	        } else {
	           
	            return compareDiceValues(dice1, dice2);
	        }
	    }

	    private static int compareCombinations(String combination1, String combination2) {
	        // Define the combination hierarchy
	        String[] hierarchy = {
	            "Five of a kind",
	            "Four of a kind",
	            "Three of a kind and a pair",
	            "Three of a kind",
	            "A pair",
	            "Highest number"
	        };

	        int index1 = Arrays.asList(hierarchy).indexOf(combination1);
	        int index2 = Arrays.asList(hierarchy).indexOf(combination2);
	// The compare() method of Integer class of java.lang package compares two integer values (x, y) given
	// as a parameter and returns the value zero if (x==y), if (x < y) then it returns a value less than zero and
	// if (x > y) then it returns a value greater than zero.
	        return Integer.compare(index1, index2);
	    }


	    private static String compareDiceValues(int[] dice1, int[] dice2) {
	        for (int i = 4; i >= 0; i--) {
	            if (dice1[i] > dice2[i]) {
	                return "Player 1";
	            } else if (dice1[i] < dice2[i]) {
	                return "Player 2";
	            }
	        }
	        return "It's a tie";
	    }
}
