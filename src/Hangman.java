import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;


public class Hangman {
	
	static Scanner input = new Scanner(System.in);
	static ArrayList<Character> guessedLetters = new ArrayList<Character>();
	static File f = new File("E:\\Eclipse\\Practise\\HangmanWords.txt");
	static ArrayList<String> randomWordList = new ArrayList<String>();


	public static void main(String[] args) {
		boolean loadGame;
		try {
			GetWordsFromFile();
			loadGame = true;
			System.out.println("Game has been found...");
			//Sleep(2);
			System.out.println("Loading...");
			//Sleep(4);
			} 
		catch (IOException e) {
			System.out.println("File Does not exist?!?!");
			loadGame = false;
			}
		
		if (loadGame) {
			mainMenu();
			
		}
		else {
			System.out.println("Game wil exit now... Please create a file in order to play...");
		}
		
	}
	
	public static void GetWordsFromFile() throws IOException {	
		FileReader fr = new FileReader(f);
		BufferedReader br = new BufferedReader(fr);
		String line;
		
		while ((line = br.readLine()) != null) {
			randomWordList.add(line);
		}
		br.close();
		fr.close();
		
	}
	public static String ChooseRandomWord() {
		String chosenWord;
		Random r = new Random();
		int randomNumber = r.nextInt(randomWordList.size());
		
		
		return randomWordList.get(randomNumber);
	}
	
	
	public static void mainMenu() {
		String userChoice = "";
		
		do {
			System.out.println("-----------------------------------------------------");
			System.out.println("HANGMAN GAME");
			System.out.println("Select a letter from the list below.");
			System.out.println("A. You (Guess) vs Computer");
			System.out.println("E. Exit program");
			System.out.println("-----------------------------------------------------");
			userChoice = input.next().toUpperCase(); 
			
			if (userChoice.equals("A")) {
				PlayerGuessVsComputer();
			}
			
		} while (!userChoice.equals("E"));
		
		System.out.println("Thank you for playing. See you next time!");

	}

	
	public static void PlayerGuessVsComputer() {
		int userLives = 10;
		String chosenWord = ChooseRandomWord();
		
		
		do {
			System.out.println("My word is " + chosenWord);
			System.out.println("You have " + userLives + " guesses.");
			System.out.println("My word has " + chosenWord.length() + " letters.");
			System.out.println("Try guess it :)");
			
			showGameBoard(chosenWord);
			
			boolean CorrectUserGuess = CheckUserGuess(chosenWord);
			
			if (CorrectUserGuess) {
				
				}
			else {
				userLives--;
				}
			} while(userLives > 0);
		
		
		System.out.println("You lose hehehe... My word was '" + chosenWord + "'");
		Sleep(3);
		
		
		
		
		
	}
	

	
	public static boolean CheckUserGuess(String chosenWord) {
		char userGuess = input.next().toUpperCase().charAt(0);
		boolean letterFound = false;
		boolean guessedAlready = false;
		
		while (guessedAlready == false) {
			if(guessedLetters.contains(userGuess)) {
				guessedAlready = true;
				System.out.println("The letter " + userGuess + " has already been guessed.");
				System.out.println("Please choose another letter.");
				userGuess = input.next().toUpperCase().charAt(0);
				}
			else {
				guessedLetters.add(userGuess);
				for (int i = 0; i < chosenWord.length(); i++) {
					if (chosenWord.charAt(i) == userGuess) {
						System.out.println("The letter '" + userGuess + "' has been found in position " + (i+1));
						letterFound = true;
						}
					}
				if (letterFound == false) {
					System.out.println("The letter '" + userGuess + "' does not exist in my word");
					}
				}			
		}
		
		return letterFound;
	}
	

	
	
	public static void showGameBoard(String chosenWord) {
		for (int i = 0; i < chosenWord.length(); i++) {
			System.out.print("_ ");
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
