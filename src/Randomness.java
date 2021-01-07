import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;
public class Randomness {
	
	static Scanner input = new Scanner(System.in);
	static Random random = new Random();
	static int minRange = -50; //Minimum range for guessing
	static int maxRange = 100; //Maximum range for guessing
	static int easyMinRange = 0;
	static int easyMaxRange = 10;
	static int computerLives = 3;
	static ArrayList<Integer> computersGuessedList = new ArrayList<Integer>();
	
	public static void main(String[] args) {
		String userChoice = "";
		
		do {
			System.out.println("-----------------------------------------------------");
			System.out.println("GUESS THE NUMBER GAME");
			System.out.println("Select a letter from the list below.");
			System.out.println("A. You(Guess) vs Computer");
			System.out.println("B. You vs Computer(Guess) - EASY MODE");
			System.out.println("C. You vs Computer(Guess) - HARD MODE");
			System.out.println("E. Exit program");
			System.out.println("-----------------------------------------------------");
			userChoice = input.next().toUpperCase(); 
			
			if (userChoice.equals("A")) {
				PlayerVsComputer(randomNumberGenerator());
			}
			
			if (userChoice.equals("B")) {
				computerVsUserEasyMode();
			}
			
		} while (!userChoice.equals("E"));
		
		System.out.println("Thank you for playing. See you next time!");
	}
	
	
	
	//Generates a random number from the minimum and maximum number range variable
	public static int randomNumberGenerator() {
		return random.nextInt(maxRange+1) + minRange;
	}
	
	public static void PlayerVsComputer(int computersNumber) {
		int userLives = 7;
		boolean win = false;
		
		System.out.println("I'm thinking of a number between " + minRange + " to " + maxRange + ".");
		
		do {
			System.out.println("You have " + userLives + " guesses.");
			int userGuess = userNumberValidation();
			
			win = IsItCorrect(userGuess, computersNumber, win);
			
			if (win == false) {
				System.out.println(HigherOrLower(userGuess, computersNumber));
				userLives--;
				}
			} while (userLives > 0 && win == false);
		
		
		
		if (userLives == 0) {
			System.out.println(loseState(computersNumber));
			}
		}
	
	public static boolean IsItCorrect(int userGuess, int computersNumber, boolean win) {
		if (userGuess == computersNumber) {
			System.out.println(winState(userGuess));
			win = true;
		}
		else {
			win = false;
		}
		
		return win;
		

	}
	
	public static String winState(int userGuess) {
		return "You win. You guessed my number " + userGuess + "." ;
	}
	
	public static String loseState(int computersNumber) {
		return "Game Over! You lose... My number was " + computersNumber;
	}
	
	public static String HigherOrLower(int userGuess, int computersNumber) {
		String output = "";
		if (userGuess > computersNumber) {
			output = "Hehe WRONG! My number is lower than " + userGuess + "...";
		}
		else{
			output = "Hehehehehhehehe WRONG! My number is higher than " + userGuess + "...";
		}
		return output;
	}
	
	public static int userNumberValidation() {
		int userNumber = 0;
		boolean isANumber = false;
		boolean numberWithinRange = false;
		
		do {
			
			if (input.hasNextInt()) {
				isANumber = true;
				userNumber = input.nextInt();
				
				if (userNumber >= minRange && userNumber <= maxRange) {
					numberWithinRange = true;
				}
				else {
					System.out.println("Hey, please enter a number within the ranges of " + minRange + " and " + maxRange + "...");
					numberWithinRange = false;
				}	
			}
			
			else {
				System.out.println("Sorry, invalid input type. Please choose a NUMBER between " + minRange + " and " +  maxRange);
				isANumber = false;
				input.next();
			}
			
		} while(!isANumber || !numberWithinRange);
		
		return userNumber;
	}
	
	
		public static void computerVsUserEasyMode() {
			boolean isANumber = false;
			boolean numberWithinRange = false;
			int userNumber = 0;
			

			System.out.println("Please enter your secret number from " + easyMinRange + " to " +  easyMaxRange);
			do {
				
				if (input.hasNextInt()) {
					isANumber = true;
					userNumber = input.nextInt();
					
					if (userNumber >= easyMinRange && userNumber <= easyMaxRange) {
						numberWithinRange = true;
					}
					else {
						System.out.println("Hey, please enter a number within the ranges of " + easyMinRange + " and " + easyMaxRange + "...");
						numberWithinRange = false;
					}	
				}
				
				else {
					System.out.println("Sorry, invalid input type. Please choose a NUMBER between " + easyMinRange + " and " +  easyMaxRange);
					isANumber = false;
					input.next();
				}
				
			} while(!isANumber || !numberWithinRange);
			
			
			do {
				int computerGuessEasy = computerValidation();
				checkComputerGuess(computerGuessEasy, userNumber, computerLives);
			} while(computerLives > 0);
			
		}
		
		public static int computerValidation() {
			boolean duplicateComputerGuess = true;
			int computerGenerateRandomGuess = random.nextInt(easyMaxRange + 1) + easyMinRange;
			
			do {
				if (computersGuessedList.contains(computerGenerateRandomGuess)) {
					System.out.println("hmm.. I have chosen " + computerGenerateRandomGuess + " already... but it's not that..." );
					computerGenerateRandomGuess = random.nextInt(easyMaxRange + 1) + easyMinRange;
					Sleep(2);
				}
				else {
					duplicateComputerGuess = false;
					computersGuessedList.add(computerGenerateRandomGuess);
				}
				
				
			}while(duplicateComputerGuess == true);
			
			return computerGenerateRandomGuess;
		}
		public static void checkComputerGuess(int computerGuessEasy, int userNumber, int computerLives) {
			if (computerGuessEasy == userNumber) {
				System.out.println("YES! I FOUND IT! YOUR NUMBER IS " + userNumber + "... hehee that was too easy buddy");
			}
			else {
				System.out.println("Oh dam... Maybe it is not " + computerGuessEasy + "... WAIT LET ME TRY AGAIN!");
				computerLives--;
				computerValidation();
			}
		}
		
	
	

	

	
	public static void Sleep(int Sleep) {
		try {
			TimeUnit.SECONDS.sleep(Sleep);
		} catch (InterruptedException e) {
			System.out.println("Nani?");
			e.printStackTrace();
		}
	}

	
	

}
