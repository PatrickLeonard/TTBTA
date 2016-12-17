/************************************************************************
 * 																	  	*
 * 																	  	*
 * 																	  	*
 * 		Name: Patrick Leonard										  	*
 * 		Class: CS202												  	*
 * 		Assignment: Program #4-5									  	*
 * 		Filename: Game.java	        	     					        *
 * 		Date: 11/29/2012												*
 * 		Last Updated: 12/05/2012										*
 * 																		*
 * 		This file implements the Game class. This class manages         *
 * 	the mapGrid and the choices the player makes in playing the game.   *
 * 	The data flow of the class begins and ends in the constructor. It   *
 * 	manages the character creation, the adventure of moving, and manages*
 *  inserting Encounters into the PreviousEncountersList and searching  *
 *  for previously visited encounters. It also keeps track of the    	*
 *  special parts of the mapGrid, the starting point and exit point.    *
 *  ********************************************************************/

package edupdxleonard8;

//To load the map file
import java.io.File;
import java.io.FileNotFoundException;
//To use the Scanner object for user input
import java.util.Scanner;

public class Game {

	//Represents the map in a two dimensional array, 0 is a wall, 1 is unvisited,
	//-1 is the starting point, and 3 is the exit
	//Read in from File
	private int mapGrid[][][];
	//Number of floors in the tower
	private static final int FLOORS = 30;
	//Size of the square map grid
	private static final int GRID_SIZE = 10;
	//Most of the integers are to use fewer literal numbers
	private int startLati;
	private int startLongi;
	private int startAlti;
	private Player player;   //Reference to a Player object
	private Scanner scanner; //Reference to a Scanner object
	private PreviousEncountersList previousEncounters;  //Reference to a PreviousEncountersList
	private WeapForest weapons;     //Reference to a WeapForest object
	private boolean exit;  //If exited the map
	private int visited;
	private int unvisited;
	private int startingPoint;
	private int exitPoint;
	
	public Game()
	{
		//Used to create the Player object at the start position
		this.startAlti = 0;
		
		this.startLati = 8;
		
		this.startLongi = 1;
		
		//Create a Scanner object
		this.scanner = new Scanner(System.in);
		
		//Create a PreviousEncountersList
		this.previousEncounters = new PreviousEncountersList();
		
		//Create a Weapforest (holds all the weapons for the game)
		this.weapons = new WeapForest();
		
		this.mapGrid = new int[FLOORS][GRID_SIZE][GRID_SIZE];
		
		loadMap();
		
		this.exit = false;
		
		this.startingPoint = -1;
		
		this.visited = 2;
		
		this.unvisited = 1;
		
		this.exitPoint = 3;
		
		//A little bit of story for the player is how the game begins
		System.out.println("\n\nYour mission is to head to the very top of this tower and defeat the Tower Boss!\n" 
							+ "There is only enough light to see your immediate surroundings. It's dark, dank, and smells\n"+
							  "awful. Begin with creating your character...\n");
		
		//Create a character
		characterCreation();
		
		//What's next?
		System.out.println("\n\nYou can't stay in here for the rest of your life. You've gotta find a way out...\n");
		
		//Begin the adventure!~
		adventure();
		
	}
	
	public Game(Player play, String weaponCategory, String weaponMaterial)
	{
		//Assign loaded player to player reference
		this.player = play;
		
		//Create a Scanner object
		this.scanner = new Scanner(System.in);
		
		//Create a PreviousEncountersList
		this.previousEncounters = new PreviousEncountersList();
		
		//Create a Weapforest (holds all the weapons for the game)
		this.weapons = new WeapForest();
		
		this.mapGrid = new int[FLOORS][GRID_SIZE][GRID_SIZE];
		
		loadMap();
		
		this.exit = false;
		
		this.startingPoint = -1;
		
		this.visited = 2;
		
		this.unvisited = 1;
		
		this.exitPoint = 3;
		
		//Equip player with the save weapon, if equipped with one
		if(!weaponCategory.equals("Handed"))
		{
		this.player.weapon = weapons.findWeapon(weaponCategory, weaponMaterial);
		}
		
		//What's next?
		System.out.println("\n\nWelcome back. You've gotta find a way out...\n");
		
		//Begin the adventure!~
		adventure();
		
	}
	
	public void loadMap()
	{
		try {
			Scanner mapScan = new Scanner( new File("map.mp"));
			
			while(mapScan.hasNext())
			{
				for(int i = 0; i < FLOORS; ++i)
				{
					for(int j = 0; j < GRID_SIZE; ++j)
					{
						for(int k = 0; k < GRID_SIZE; ++k)
						{
							mapGrid[i][j][k] = mapScan.nextInt();
						}
					}
				}
			}
			
			mapScan.close();
		} catch (FileNotFoundException e) {
			
			System.out.println("Map not loaded properly!");
			
			System.exit(1);

		}	
		
	}
		
	//User input to create the player's character
	public void characterCreation()
	{
		String nameInput = new String();
		
		String choice = new String();
		
		String calling = new String();
		
		String confirmation = new String();
		
		String passwordFirst = new String();
		
		String passwordSecond = new String();
		
		boolean confirm = false;
		
		//Create a character until the player is satisfied
		do
		{
			
			System.out.println("\nCreate your character!");
		
			System.out.print("\nPlease name your character: ");
		
			nameInput = this.scanner.nextLine();
			
			//Ask for password, and ask again to confirm, continue to prompt until passwords match.
			do
			{
				System.out.print("\nPlease enter a password: ");
			
				passwordFirst = this.scanner.nextLine();
			
				System.out.print("\nRe-enter password to confirm: ");
			
				passwordSecond = this.scanner.nextLine();
				
				if(!passwordFirst.equals(passwordSecond))
				{
					System.out.println("Passwords do not match! Try again!");
				}
				
			}while(!passwordFirst.equals(passwordSecond));
			
			confirm = false;

			System.out.println("\nPlease choose your class: ");
			System.out.println("\n1. Fighter");
			System.out.println("Fighters are masters of close combat. They have the highest vitality\n" +
								"and strength, although are slow of both foot and wits. (Beginner)");
			System.out.println("\n2. Thief");
			System.out.println("Thieves are masters of evasion and quickness. They have the highest dexterity\n" +
								"and agility, but aren't the strongest nor are they the brightest. (Intermediate)");
			System.out.println("\n3. Wizard");
			System.out.println("Wizards are masters of magic and reasoning. They have the highest intelligence\n" +
			                   "and wisom, but are the weakest and most frail. (Advanced)");
			
			//Keep prompting user until give an acceptable answer 	
			do
			{
					
				System.out.print("\nClass selection: ");
			
				choice = this.scanner.nextLine();
			
			}while(choiceValidation(choice));
			
			switch(choice)
			{
			
			case "1": 
			{
				
				calling = "Fighter";
				
				break;
			}
			
			case "2":
			{
				calling = "Thief";
				
				break;
			}
			
			case "3":
			{
				calling = "Wizard";
				
				break;
			}
			
			}
			
			System.out.println("\nYou have chosen: ");
			System.out.println("Name: " + nameInput);
			System.out.println("Class: " + calling);
			
			//Prompt user until proper confirmation answer
			do
			{
				System.out.print("OK? (Y/N): ");
			
				confirmation = scanner.nextLine();
			}while(confirmationValidation(confirmation));
			
			//If confirmed, create the character object with the given name
			//and proper character class.
			if(confirmation.equals("Y") || confirmation.equals("y"))
			{
				createPlayer(nameInput, calling, passwordFirst);
				
				confirm = true;
			}
				
		}while(!confirm);
		
	}
	
	//Creates a player object with String input for name and character class
	public void createPlayer(String nameInput, String calling, String password)
	{
		if(calling.equals("Fighter"))
		{
			this.player = new Fighter(nameInput, this.startLati, this.startLongi, this.startAlti, password);
		}
		else if(calling.equals("Thief"))
		{
			this.player = new Thief(nameInput, this.startLati, this.startLongi, this.startAlti, password);
		}
		else if(calling.equals("Wizard"))
		{
			this.player = new Wizard(nameInput, this.startLati, this.startLongi, this.startAlti, password);
		}
	}
	
	//A function of high adventure! User input for decisions regarding movement,
	//character status, resting, or quiting the game
	public void adventure()
	{
		
		String adventureChoice = new String();
		
		//Keep adventuring until the leaving the map, or death
		do
		{
			
			System.out.println("\nWhat would you like to do?");
			System.out.println("1. Move");
			System.out.println("2. Rest");
			System.out.println("3. View Status");
			System.out.println("4. Save and Quit the Adventure");
			
			//Keep prompting until a proper answer is given
			do
			{
				System.out.print("Selection: ");
				
				adventureChoice = this.scanner.nextLine();
				
			}while(fourChoiceValidation(adventureChoice));
			
			adventureDecision(adventureChoice);
			
		}while(!player.isDead() && !this.exit);
	}
	
	//Reconcile the choices in the adventure() with a String as an argument
	public void adventureDecision(String decision)
	{
		switch(decision)
		{
		
		case "1": 
		{
			
			System.out.println("\n\nWhich direction would you like to move?");
			
			encounterManagement(movement());  //Move the player and determine an encounter
			
			break;
		}
		
		case "2":   //Resting the character erases all previous encounters as the map continues it's "life"
		{

			System.out.println("\n\nYou gain a much needed respite, all your HP and TP have been restored. Be\n" +
									"on guard though...danger lurks everywhere.");
			
			this.player.refresh();
			
			//Where the player rests doesn't change
			int locationID = ((this.player.playerPosition.getLatitude() * 10) + (this.player.playerPosition.getLongitude()));
			
			//Assign the current encounter to a reference for Encounter
			Encounter currentLocation = previousEncounters.searchList(locationID);
			
			//Create a new PreviousEncountersList, and add the current Encounter to the list
			this.previousEncounters = new PreviousEncountersList();
			
			this.previousEncounters.addEncounter(currentLocation);
			
			//Rest the floor to visited upon rest
			resetFloor();
			
			//Set this location as visited because the player is still there
			this.mapGrid[this.player.playerPosition.getAltitude()][this.player.playerPosition.getLatitude()]
													[this.player.playerPosition.getLongitude()] = this.unvisited;
			
			break;
		}
		
		case "3":
		{
			
			System.out.println("\nCharacter Status");
			System.out.println("----------------");
			
			//Display the character's detailed status screen
			this.player.statusDisplay();
			
			break;
		}
		
		case "4":
		{
			
			String quitChoice = new String();
			
			//Prompt user until acceptable choice is made
			do
			{
				System.out.print("Are you sure you would like to save and quit the game? (Y/N):");
			
				quitChoice = this.scanner.nextLine();
				
			}while(confirmationValidation(quitChoice));
			
			//Thank for playing if chosen to quit, and set exit to true
			if(quitChoice.equals("Y") || quitChoice.equals("y"))
			{
				this.player.savePlayer();
				
				this.exit = true;
			
				System.out.println("\n\n---/---/--THANKS FOR PLAYING (*^_^)y--/---/---\n\n");
				
			}
			
			break;
		}
		
		}
	}
	
	//Returns an integer from the mapGrid to decided on which Encounter happens 
	//Get user to decide which direction the character moves
	public int movement()
	{
		String moveChoice = new String();
		
		//get the current latitude and longitude of the player
		int currentLati = this.player.playerPosition.getLatitude();
		
		int currentLongi = this.player.playerPosition.getLongitude();
		
		int currentAlti = this.player.playerPosition.getAltitude();
		
		int gridEvent = -1;
		
		System.out.println("1. North");
		System.out.println("2. South");
		System.out.println("3. East");
		System.out.println("4. West");
		System.out.println("5. Cancel");
		
		//Keep prompting until an acceptable is given
		do
		{
			System.out.print("Direction selection: ");
			
			moveChoice = this.scanner.nextLine();
			
		}while(moveChoiceValidation(moveChoice));
		
		switch(moveChoice)
		{
		
		//NORTH
		case "1":
		{
			//If to the north is a wall or the edge of the mapGrid, pass WALL into movePlayer()
			if(((currentLati - 1) < 0) || (this.mapGrid[currentAlti][currentLati - 1][currentLongi] == 0))
			{
				this.player.movePlayer("WALL");
				
				gridEvent = 0;
			}
			else //If it isn't a wall or edge, move the character there
			{
				this.player.movePlayer("NORTH");
				
				gridEvent = this.mapGrid[currentAlti][currentLati - 1][currentLongi];
				
				//If it's not the staircase, or starting point, set the mapGrid element to visited
				if((this.mapGrid[currentAlti][currentLati - 1][currentLongi] != this.startingPoint) && 
					(this.mapGrid[currentAlti][currentLati - 1][currentLongi] != this.exitPoint))
				{
					this.mapGrid[currentAlti][currentLati - 1][currentLongi] = this.visited;
				}
			}
			
			break;
		}
		//SOUTH
		case "2":
		{
			//If to the south is a wall or the edge of the mapGrid, pass WALL into movePlayer()
			if(((currentLati + 1) > (GRID_SIZE - 1)) || (this.mapGrid[currentAlti][currentLati + 1][currentLongi] == 0))
			{
				this.player.movePlayer("WALL");
				
				gridEvent = 0;
			}
			else  //If it isn't a wall or edge, move the character there
			{
				this.player.movePlayer("SOUTH");
				
				gridEvent = this.mapGrid[currentAlti][currentLati + 1][currentLongi];
				
				//If it's not the staircase, or starting point, set the mapGrid element to visited
				if((this.mapGrid[currentAlti][currentLati + 1][currentLongi] != this.startingPoint) &&
						(this.mapGrid[currentAlti][currentLati + 1][currentLongi] != this.exitPoint))
					{
						this.mapGrid[currentAlti][currentLati + 1][currentLongi] = visited;
					}
			}
			
			break;
		}
		//EAST
		case "3":
		{
			//If to the east is a wall or the edge of the mapGrid, pass WALL into movePlayer()
			if(((currentLongi + 1) > (GRID_SIZE - 1)) || (this.mapGrid[currentAlti][currentLati][currentLongi + 1] == 0))
			{
				this.player.movePlayer("WALL");
				
				gridEvent = 0;
			}
			else    //If it isn't a wall or edge, move the character there
			{
				this.player.movePlayer("EAST");
				
				gridEvent = this.mapGrid[currentAlti][currentLati][currentLongi + 1];
				
				//If it's not the staircase, or starting point, set the mapGrid element to visited
				if((this.mapGrid[currentAlti][currentLati][currentLongi + 1] != this.startingPoint) && 
						(this.mapGrid[currentAlti][currentLati][currentLongi + 1] != this.exitPoint))
					{
						this.mapGrid[currentAlti][currentLati][currentLongi + 1] = visited;
					}
			}
			
			break;
		}
		//WEST
		case "4":
		{
			//If to the west is a wall or the edge of the mapGrid, pass WALL into movePlayer()
			if(((currentLongi - 1) < 0) || (this.mapGrid[currentAlti][currentLati][currentLongi - 1] == 0))
			{
				this.player.movePlayer("WALL");
				
				gridEvent = 0;
			}
			else     //If it isn't a wall or edge, move the character there
			{
				this.player.movePlayer("WEST");
				
				gridEvent = this.mapGrid[currentAlti][currentLati][currentLongi - 1];
				
				//If it's not the staircase, or starting point, set the mapGrid element to visited
				if((this.mapGrid[currentAlti][currentLati][currentLongi - 1] != this.startingPoint) && 
						(this.mapGrid[currentAlti][currentLati][currentLongi - 1] != this.exitPoint))
					{
						this.mapGrid[currentAlti][currentLati][currentLongi - 1] = visited;
					}
			}
			
			break;
		}
		//CANCEL
		case "5":
		{
			gridEvent = this.mapGrid[currentAlti][currentLati][currentLongi];
		}
		}
		
		return gridEvent;
		
	}
	
	//Handles the encounter depending on the given integer argument
	public void encounterManagement(int gridEvent)
	{
		//There is a downward staircase (or at the entrance of the tower) call downwardStaircase()
		if(gridEvent == -1)
		{
			downwardStaircase();
			
			return;
		}
		else if(gridEvent == 1)  //If unvisited, created a new Encounter
		{
			//Not the boss battle
			boolean boss = false;
			
			Encounter newEvent = new Encounter(this.player, this.weapons, boss);
			
			this.previousEncounters.addEncounter(newEvent);  //Add it to the PreviousEncountersList
			
			return;
		}
		else if(gridEvent == 2)  //If visited find in the list with eventKey
		{
			int eventKey = ((this.player.playerPosition.getLatitude() * 10) +
					 this.player.playerPosition.getLongitude());
			
			Encounter previousEvent = this.previousEncounters.searchList(eventKey);
			
			previousEvent.revisited(this.player); //replay event if needed
			
			return;
		}
		else if(gridEvent == 3)  //There is an upward staircase call upwardStairCase
		{
			upwardStaircase();
			
			return;
		}
		else if(gridEvent == 4)  //Boss Battle!! Can beat and exit the game here!
		{
			//Is the boss battle
			boolean boss = true;
			
			Encounter bossBattle = new Encounter(this.player, this.weapons, boss);
			
			this.previousEncounters.addEncounter(bossBattle);
			
			if(bossBattle.isCompleted())
			{
				//If beat the game, congratulate and berate the player at the same time
				System.out.println("\n\nCongratulations! You have beaten the Tower Boss! You have won at life!");
				
				//Reset Player's position to the beginning of the Tower
				this.player.playerPosition.setAltitude(this.startAlti);
				
				this.player.playerPosition.setLatitude(this.startLati);
				
				this.player.playerPosition.setLongitude(this.startLongi);
				
				//Save player for continued play
				this.player.savePlayer();
				
				//Set exit to true so the game will end
				this.exit = true;
				
				//Thanks for playing ^^
				System.out.println("\n---/---/--THANKS FOR PLAYING (*^_^)y--/---/---\n\n");
			}
			
		}
		else  //represents WALL no event occurs
		{
			return;
		}
	}
	
	public void downwardStaircase()
	{
		String stairCaseChoice = new String();
		
		//If on the first floor, player can't go downard
		if(this.player.playerPosition.getAltitude() == 0)
		{
			System.out.println("You're at the entrance of the tower. Are you going around in circles?");
			
			return;
		}
		else //If above ask if player wants to go below
		{
			//Keep prompting user until acceptable answer is given
			do
			{
				System.out.print("There is a stair case leading to the floor below. Would \n" +
								"you like to go down the stair case? (Y/N):");
		
				stairCaseChoice = scanner.nextLine();
		
			}while(confirmationValidation(stairCaseChoice));
			
			if((stairCaseChoice.equals("Y")) || (stairCaseChoice.equals("y")))
			{
				resetFloor();
				
				this.player.movePlayer("DOWN");	
				
				return;
			}
			else
			{
				return;
			}
		}
		
	}
	
	//Allows player to choose to leave the cave when at the exit
	public void upwardStaircase()
	{
		String stairCaseChoice = new String();
		
		
		//Keep prompting user until acceptable answer is given
		do
		{
			System.out.print("There is a stair case leading to the floor above. Would \n" +
							"you like to go up the stair case? (Y/N):");
		
			stairCaseChoice = scanner.nextLine();
		
		}while(confirmationValidation(stairCaseChoice));
		
		if((stairCaseChoice.equals("Y")) || (stairCaseChoice.equals("y")))
		{
			resetFloor();
			
			this.player.movePlayer("UP");	
			
			return;
		}
		else
		{
			return;
		}
		
	}
	
	//Resets the the floor to unvisited areas
	public void resetFloor()
	{
		//Reset the mapGrid to unvisited if visited
		for(int i = 0; i < GRID_SIZE; ++i)
		{
			for(int j = 0; j < GRID_SIZE; ++j)
			{
				if(this.mapGrid[this.player.playerPosition.getAltitude()][i][j] == this.visited)
				{
					this.mapGrid[this.player.playerPosition.getAltitude()][i][j] = this.unvisited;
				}
			}
		}
	}
	
	/*The next four functions are input validation functions that return true when unacceptable
	 * String inputs are given, and false when acceptable inputs are given. */
	
	
	public boolean choiceValidation(String input)
	{
		if(!input.equals("1") && !input.equals("2") && !input.equals("3"))
		{
			System.out.println("Invalid input!");
			
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public boolean confirmationValidation(String input)
	{
		if(!input.equals("y") && !input.equals("Y") && !input.equals("n") && !input.equals("N"))
		{
			System.out.println("Invalid input!");
			
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public boolean fourChoiceValidation(String input)
	{
		if(!input.equals("1") && !input.equals("2") && !input.equals("3") && !input.equals("4"))
		{
			System.out.println("Invalid input!");
			
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public boolean moveChoiceValidation(String input)
	{
		if(!input.equals("1") && !input.equals("2") && !input.equals("3") 
												    && !input.equals("4") && !input.equals("5"))
		{
			System.out.println("Invalid input!");
			
			return true;
		}
		else
		{
			return false;
		}
	}
}
