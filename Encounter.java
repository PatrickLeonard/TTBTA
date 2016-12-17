/************************************************************************
 * 																	  	*
 * 																	  	*
 * 																	  	*
 * 		Name: Patrick Leonard										  	*
 * 		Class: CS202												  	*
 * 		Assignment: Program #4-5									  	*
 * 		Filename: Encounter.java	        	     					*
 * 		Date: 11/29/2012												*
 * 		Last Updated: 12/05/2012										*
 * 																		*
 * 		This file implements the Encounter class. This class manages    *
 * 	the possible encounters that a player will have on the map grid.    *
 * 	A battle will happen only 40% of the time, while there will not be  *
 * 	a monster there the rest of the time. This class also creates a     *
 *  random monster, if there is one, and it will choose between the     *
 *  monster types. This class also has a location ID to keep track of 	*
 *  it in the PreviousEncountersList. It also knows if the encounter was*
 *  completed. It also manages battles between the player and monsters. *
 *  This class resolves the encounter and displays a possible ending to *
 *  the encounter.                                                      *
 *  ********************************************************************/

package edupdxleonard8;

//To use Scanner class for user input
import java.util.Scanner;
//To a Random class object
import java.util.Random;

public class Encounter {

	//References to a player and monster, and a Random class object
	private Player player;
	private Monster monster; 
	private Random random;
	private boolean completed;  //If the encounter was completed
	private int locationID;    //integer ID for the encounter
	private String battleResult; //String for the result of the battle
	private Scanner scanner;   //Reference to Scanner object
	private WeapForest weapons; //Reference to the Weapon Forest (WeapForest) object
	
	//Constructor, creates the initial battle conditions and determine
	//if there is a monster in this encounter. Takes the player as it's 
	//argument, and the WeapForest (Weapon Forest) as the second argument
	//Includes a third boolean argument for if it is the Boss encounter
	public Encounter(Player character, WeapForest weap, boolean boss)
	{
		//Random object to determine different random numbers
		this.random = new Random();  
		
		this.player = character;
		
		this.weapons = weap;
		
		//Create Scanner object using the system input
		this.scanner = new Scanner(System.in);
		
		//Create a locationID based on where the player is on the mapGrid
		this.locationID = ((this.player.playerPosition.getLatitude() * 10) +
					 this.player.playerPosition.getLongitude());
		
		//Start not completed
		this.completed = false; 
		
		//String object for battle result
		this.battleResult = new String();
		
		//Determine if there is a monster
		int fightChance = random.nextInt(10);
		
		//If not the boss battle, then randomly generate a monster
		if(!boss)
		{
			//If within the bottom 60% then there is no monster
			if(fightChance < 6)
			{
				this.monster = null;
			}
			else //If top 40% then there is a monster
			{
				this.monster = randomMonster();
			}
		
			//If there is no monster, the use that battle result
			if(this.monster == null)
			{
				this.battleResult = "NO BATTLE";
			
				completeEncounter(this.battleResult); //Complete the encounter with no monster
			}
			else  	//If there is no monster, then begin the battle
			{
				battle(this.player, this.monster);  //Battle between player and monster
			}
		}
		else  //If it is the boss battle create a Boss class monster, and enter battle
		{
			this.monster = new Boss();
			
			battle(this.player, this.monster);
		}
	}
	
	//Returns if this encounter has been completed
	public boolean isCompleted()
	{
		return this.completed;
	}
	
	//Creates a random monster of the three types using a random number
	//between 0 and 2.
	public Monster randomMonster()
	{
		int possibleMonster = this.random.nextInt(3);
		
		Monster tempMonster;
		
		if(possibleMonster == 0) //0 creates a rat
		{
			tempMonster = new Rat(this.player.getPlayerLevel()); 
		}
		else if(possibleMonster == 1) //1 creates a bear
		{
			tempMonster = new Bear(this.player.getPlayerLevel());
		}
		else //2 creates a Ghost
		{
			tempMonster = new Ghost(this.player.getPlayerLevel());
		}
		
		return tempMonster;  //return a reference to the monster
	}
	
	//Manages the battle giving the player different choices
	//Receives a Player and a Monster as arguments
	public void battle(Player character, Monster fiend)
	{
		String choice = new String();
		
		//Bool for if the player has escaped or not
		boolean escape = false;
		
		//Keep fighting unless escaped, player or monster is dead
		do
		{
			//Display the battle stats of the player and the monster
			character.fightDisplay();
			
			System.out.println("\nENEMY");
			
			fiend.fightDisplay();
			
			choice = "0";
			
			escape = false;
			
			//Keep asking for a selection until proper input
			do
			{
				System.out.println("\n1. Fight");
				System.out.println("2. Run");
				System.out.print("Battle Selection: ");
				choice = this.scanner.nextLine();
			
			}while(confirmation(choice));
			
			//Switch statement for battle decision
			switch(choice)
			{
			
			case "1":
			{
				//If fight is selected begin the turn based combat
				System.out.println();
				
				//Player fights first
				System.out.println(character.playerName + " attacks the " + fiend.monsterName + "!");
				
				//If the player hits the monsters, then it takes damage
				if(fiend.isHit(character.getHitScore()) == "HIT")
				{
					fiend.takeDamage(character.getDamageScore());
					
					System.out.println();
				}
				
				//If the monster is dead it can't hit back
				if(fiend.isDead())
				{
					break;
				}
				
				//If the monster hits the player, then the player takes damage
				System.out.println(fiend.monsterName + " attacks " + character.playerName + "!");
				if(character.isHit(fiend.getHitScore()) == "HIT")
				{

					character.takeDamage(fiend.getDamageScore());
					
					System.out.println();
				}
				
				break;
			}
			
			case "2":
			{
				int run = 0;
				
				//Determine if the player can run
				run = character.getRunScore() - fiend.getRunScore();
				
				//If the player fails the run attempt, the monster gets a free attack
				if(run < 0)
				{
					System.out.println();
					
					System.out.println("You fail to run away.\n");
					
					if(character.isHit(fiend.getHitScore()) == "HIT")
					{
						character.takeDamage(fiend.getDamageScore());
					}
					
					System.out.println();
				}
				else  //If the player runs then escape is true and battle ends
				{
					escape = true;
				}
				
				break;
			}
			
			}
			
			//If battle continues, give a pause so the player can assess this
			//round's outcome.
			if(!escape)
			{
				System.out.println("---/--/---Press ENTER to Continue---/--/---");
				
				this.scanner.nextLine();
				
			}
			
		}while(!character.isDead() && !fiend.isDead() && !escape);
		
		//Depending on how the battle ended, call complete with the proper String input
		if(character.isDead())
		{
			this.battleResult = "BATTLE LOST";
			
			completeEncounter(this.battleResult);
		}
		else if(fiend.isDead())
		{
			this.player.earnExp(this.monster.yieldExp());  //If player wins award experience points
			
			System.out.println("Treize gained " + this.monster.yieldExp() + " experience points.");
			
			determineDrop();
			
			this.battleResult = "BATTLE WON";
			
			completeEncounter(this.battleResult);
		}
		else if(escape)
		{
			this.battleResult = "RUN";
			
			completeEncounter(this.battleResult);
		}
		
	}
	
	//Determine if the enemy drops a weapon, and allow the player to choose to equip (or not)
	public void determineDrop()
	{
		int drop = random.nextInt(100);   //random integer from 0 to 99 (percentage possible outcome)
		
		if((drop >= 0) && (drop < 15))    //15% drop rate of weapons
		{
			String category = player.getWeaponCategory();   //Gets appropriate category of weapon
			
			String material = player.getWeaponMaterial(category);  //Gets appropriate material weapon
			
			String equip = new String();  //To select equip or not
			
			Weapon temp = this.weapons.findWeapon(category, material);   //Get weapon from the Weapon Forest
			
			System.out.println("\nThe enemy drops a: ");
			
			temp.displayWeapon();   //Show player the weapon
			
			//If player has a weapon equipped, display to compare
			if(player.weapon != null)
			{
				System.out.println("\nCurrently equipped weapon: ");
				player.weapon.displayWeapon();
			}
			
			//prompt until receive valid input
			do
			{
				
				//Allow player to choose to equip the weapon
				System.out.print("\nWould you like to equip? (Y/N): ");
			
				equip = scanner.nextLine();
		
			}while(equipConfirmation(equip));
			
			//If player equips, assign new weapon to player.weapon
			if(equip.equals("Y") || equip.equals("y"))
			{
				player.weapon = temp;
			}
		}
	}
	
	//Input validation function, returns proper boolean for given String input
	public boolean confirmation(String input)
	{
		if(!input.equals("1") && !input.equals("2"))
		{
			System.out.println("Invalid input!");
			
			return true;
		}
		else
		{
			return false;
		}
	}
	
	//Input validation function, returns proper boolean for given String input
	public boolean equipConfirmation(String input)
	{
		if(!input.equals("Y") && !input.equals("y") && !input.equals("N") && !input.equals("n"))
		{
			System.out.println("Invalid input!");
			
			return true;
		}
		else
		{
			return false;
		}
	}
	
	
	//Completes the encounter with given result and sets completed 
	public void completeEncounter(String outcome)
	{
		if(outcome.equals("NO BATTLE"))  //No battle occurred
		{
			System.out.println("\nThis area of the cave seems to be empty. There is nothing of interest here.");
			
			this.completed = true;			
		}
		else if(outcome.equals("BATTLE WON")) //Player won the battle
		{
			
			System.out.println("\nThe remnants of your slain enemy are strewn on the ground of the cave.");
			
			this.completed = true;
		}
		else if(outcome.equals("BATTLE LOST"))  //Player is dead, this is game over
		{
			System.out.println("\nYou were bested on this day. Songs will be sung of your heroism as you" +
								" cross the river into Sto'Vo'Kor.");
			
			System.out.println("\n\n---/---/--THANKS FOR PLAYING (*^_^)y--/---/---\n\n");
			
			this.completed = false;
		}
		else
		{
			System.out.println("\nYou succeed in running away, but the monster still lives!");
			
			this.completed = false;   //If you run and come back to this encounter the monster
		}								//will still be alive
		
	}
	
	//If the encounter has been visited this is called with Player as an argument
	//If its completed go straight to result, if monster is still alive then start
	//battle again.
	public void revisited(Player character)
	{
		if(this.completed == true)
		{
			completeEncounter(battleResult);
		}
		else
		{
			battle(character, this.monster);
		}
	}
	
	//Returns if the input integer matches the location ID data member
	public boolean idCompare(int key)
	{
		return (this.locationID == key);
	}
	
}
