/************************************************************************
 * 																	  	*
 * 																	  	*
 * 																	  	*
 * 		Name: Patrick Leonard										  	*
 * 		Class: CS202												  	*
 * 		Assignment: Program #4-5									  	*
 * 		Filename: Main.java	        	     					        *
 * 		Date: 11/29/2012												*
 * 		Last Updated: 12/05/2012										*
 * 		This file implements the Main class. This static class manages  *
 * 	begins the game at the players choice, and exits with player's,     *
 * 	choice as well. Allows user input to choose. Has another static     *
 * 	function for input validation to ensure an acceptable answer.       *
 *  Very simple Main class                                              *
 ***********************************************************************/

package edupdxleonard8;

import java.util.Scanner;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String input = new String();
		
		//Creates a Scanner object
		Scanner scanner = new Scanner(System.in);
		
		boolean replay = false;
		
		//Keep prompting until program is told to exit
		do
		{
		
		System.out.println("\n\nTerrible Turn-based Tower Ascension v.6!");
		System.out.println("-----------------------------------------------");
		
		System.out.println("1. Play Game");
		System.out.println("2. Load Game");
		System.out.println("3. Exit");
		
		//Keep prompting until user give valid input
		do
		{
		
			System.out.print("Selection: ");
		
			input = scanner.nextLine();
		
		}while(replayConfirmation(input));
		
		switch(input)
		{
		
		case "1":  //If play the create a new Game object
		{
			Game game = new Game();
			
			break;
		}
		
		case "2": //Load data from save file and continue where player left off
		{
			
			ResumeGame resume = new ResumeGame();
			
			resume.displaySavedCharacters();
			
			Player player = resume.loadPlayer();
			
			if(player != null) //If a player is properly loaded and password verified
			{
			
				String weaponCategory = resume.resumeWeaponCategory();
			
				String weaponMaterial = resume.resumeWeaponMaterial();
			
				Game game = new Game(player, weaponCategory, weaponMaterial);
			
				break;
			
			}
			else  //If not loaded properly or password incorrect
			{
				break;
			}
		}
		
		case "3":  //If exit, then end program and close the scanner
		{
			replay = true;
			
			break;
		}
		
		}
			
			
		}while(!replay);
		
		scanner.close();
			
	}
	
	//Input validation, returns true if invalid input is given, and false for valid input
	public static boolean replayConfirmation(String input)
	{
		if(!input.equals("1") && !input.equals("2") && !input.equals("3"))
		{
			System.out.println("Invalid Input!");
			
			return true;
		}
		
		else
		{
			return false;
		}
	}
}