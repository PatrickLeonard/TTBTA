/************************************************************************
 * 																	  	*
 * 																	  	*
 * 																	  	*
 * 		Name: Patrick Leonard										  	*
 * 		Class: CS202												  	*
 * 		Assignment: Program #4-5									  	*
 * 		Filename: Player.java											*
 * 		Date: 11/29/2012												*
 * 																		*
 * 		This file implements the Player abstract base class. Fighter,   *
 * 	Thief, and Wizard classes derive from this class. This class has    *
 * 	all the data members for the player, and the constructor creates the*
 * 	randomnessFactor integer that is used to scale the randomness as    *
 *  the levels of the monsters increase. It also manages statistic      *
 *  growth from gaining levels, determines if the player is dead, and	*
 *  defensive combat functions (running, dodging, and taking damage).	*
 *  Displays both a fightStatus and a full Status display. This class   *
 *  also manages the player's movement on the map and the player's      *
 *  position.                                                           *
 ***********************************************************************/

package edupdxleonard8;

//To create a Random class object
import java.util.Random;
//To use the pow of the Math class
import java.lang.Math;

import java.io.File;

import java.io.FileNotFoundException;
//To save character to file
import java.io.PrintWriter;

public abstract class Player {

	//Protected data members for the player
	//Mostly the "stats" for the player, with the name,
	//class name, a Position object, and a Random class object.
	protected int currentHP;
	protected int maxHP;
	protected int currentTP;
	protected int maxTP;
	protected int strength;
	protected int vitality;
	protected int dexterity;
	protected int agility;
	protected int intelligence;
	protected int wisdom;
	protected int currentXP;
	protected int toNextLevel;
	protected int level;
	protected long randomnessFactor;
	protected String className;
	protected String playerName;
	protected String password;
	protected Position playerPosition; 
	protected Random random;
	protected Weapon weapon;
	
	//Abstract getHitScore and getDamageScore functions are implemented differently 
	//depending on the derived class. 
	public abstract int getHitScore();
	
	public abstract int getDamageScore();
	
	//Returns one of three categories of weapons appropriate to each character class
	public abstract String getWeaponCategory(); 
	
	//Returns a material of a weapon depending on the category and level of the player
	//Accepts a String argument for the category of weapon
	public abstract String getWeaponMaterial(String category);
	
	//Constructor assigns the randomnessFactor data member with a number based
	//on the level of the player, the name of the player, and the posistion of the 
	//player on the map
	public Player(String name, int lati, int longi, int alti, String pass)
	{
		this.playerName = name;
		
		this.password = pass;
		
		this.playerPosition = new Position(lati, longi, alti);
		
		this.randomnessFactor = (10 + Math.round(Math.pow(1.05, (this.level - 1.0))));
		
		this.weapon = new Weapon("Handed", "Bare", 0, 0);
	}
	
	//Adds the input integer argument, and tests to see if the currentXP is 
	//enough to level the player up and calls the levelUp() if needed.
	public void earnExp(int gainedExp) {
		// TODO Auto-generated method stub

		this.currentXP += gainedExp;
		
		while(this.currentXP >= this.toNextLevel)
		{
			levelUp(false);   //not silent level up, display status
		}
		
		return;
	}
	
	//assigns the maxHP/TP to the currentHP/TP when called
	public void refresh()
	{
		
		this.currentHP = this.maxHP;
		
		this.currentTP = this.maxTP;
		
		return;
	}
	
	//Determines and returns the run score of the player, this gets compared
	//to the monster's run score to determine if the player is able to run away 
	//from the battle. It has the agility divided by two as the base added to 
	//a random integer between 0 and the agility stat divided by randomnessFactor minus one
	public int getRunScore(){
		
		return random.nextInt(Math.round((this.agility)/randomnessFactor)) + (this.agility/2);
	}
	
	//Resolves whether or not the palyer is hit by the monster. Returns a String "HIT" if hit,
	//returns a String "MISS" if dodged. Receives and integer as an argument
	public String isHit(int enemyHitScore) {
		// TODO Auto-generated method stub
	
		//Dodge score is the same as the run score
		int dodgeScore = random.nextInt(Math.round(this.agility/randomnessFactor)) + (this.agility/2);
		
		//Subtract the dodgeScore by the input hitScore
		int resolveHit = enemyHitScore - dodgeScore;
		
		//If there is more dodge than hit, then it's a miss
		if(resolveHit <= 0)
		{
			System.out.println("The enemy's attack misses " + this.playerName + "!\n");
			return "MISS";
		}
		else   //If more hit than dodge, then it's a hit
		{
			System.out.println("The enemy's attack strikes " + this.playerName + "!");
			return "HIT";
		}
		
	}
	
	//Determines how much damage is done to the player, receives an integer as an argument
	public void takeDamage(int dmgScore) {

		//Creates a defenseScore from a random integer between 0 and the quantity of vitality plus agility plus wisdom 
		//divided by the randomnessFactor quantity minus one. This random integer is then added to a base of vitality divided by 3.
		int defenseScore = random.nextInt(Math.round((this.vitality + this.agility + this.wisdom)/randomnessFactor)) + (this.vitality/3);
		
		//Damage done is the input damage score divided by the defenseScore
		int damage = ((dmgScore - defenseScore));
		
		//If there is more defense than damage, then no damage was done
		if(damage <= 0)
		{
			System.out.println("No damage was taken!");
		}
		else   //If damage is greater than defense, then state the damage taken and subtract if from the currentHP data member
		{
			System.out.println(this.playerName + " takes " + damage + " points of damage!");
			
			currentHP -= damage;
		}
		
		return;
	}

	//Manages the growth of statistics upon level up.
	//Increases the amount needed to gain the next level
	//Announces the level gain, and calls statusDisplay()
	public void levelUp(boolean silent) {
		// TODO Auto-generated method stub
		
		if(!silent)  //If not silent, announce level up, if silent do not announce
		{
		System.out.println("\nYou've gained a level!!\n");
		}
		
		double growth = .1;
		
		this.strength += (this.strength * growth);
		
		this.vitality += (this.vitality * growth);
		
		this.dexterity += (this.dexterity * growth);
		
		this.agility += (this.agility * growth);
		
		this.intelligence += (this.intelligence * growth);
		
		this.wisdom += (this.wisdom * growth);
		
		this.currentHP = this.maxHP = (this.vitality + this.agility + this.wisdom);
		
		this.currentTP = this.maxTP = ((this.strength + this.dexterity + this.intelligence)/2);
		
		this.toNextLevel += (this.toNextLevel * 1.2);
		
		++this.level;
		
		//If not silent level up then display, else do not display
		if(!silent)
		{
		statusDisplay();
		}
	}
	
	//Return whether or not the monster is dead. True if dead, false if still alive
	public boolean isDead() {

		if(currentHP <= 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	//Display the relevant information to the player during battle
	public void fightDisplay() {

		System.out.println("\n" + playerName);
		System.out.println(className);
		System.out.println("Level: " + this.level);
		System.out.println("HP: " + this.currentHP + "/" + this.maxHP);
		System.out.println("TP: " + this.currentTP + "/" + this.maxTP);
		
	}

	//Display detailed information on the player's status
	public void statusDisplay() {

		fightDisplay();
		System.out.println("Exp: " + this.currentXP + "/" + this.toNextLevel);
		System.out.println("Strength: " + this.strength);
		System.out.println("Vitality: " + this.vitality);
		System.out.println("Dexterity: " + this.dexterity);
		System.out.println("Agility: " + this.agility);
		System.out.println("Intelligence: " + this.intelligence);
		System.out.println("Wisdom: " + this.wisdom);
		this.weapon.displayWeapon();
		
	}

	//Changes the player's position coordinates based on the input String object
	//Latitude increase means moving south, and decrease means north
	//Longitude increase means east, and decease means west
	//If there is a wall don't change position and say there is a wall.
	public void movePlayer(String direction) {
				
		if(direction.equals("WALL"))
		{
			System.out.println("\nThere is a wall preventing your movement in that direction.");
			
			return; 
		}
		else if(direction.equals("NORTH"))
		{
			this.playerPosition.setLatitude(this.playerPosition.getLatitude() - 1);
			
			return; 
		}
		else if(direction.equals("SOUTH"))
		{
			this.playerPosition.setLatitude(this.playerPosition.getLatitude() + 1);
			
			return;
		}
		else if(direction.equals("EAST"))
		{
			this.playerPosition.setLongitude(this.playerPosition.getLongitude() + 1);
			
			return;
		}
		else if(direction.equals("WEST"))
		{
			this.playerPosition.setLongitude(this.playerPosition.getLongitude() - 1);
			
			return;
		}
		else if(direction.equals("UP"))
		{
			this.playerPosition.setAltitude(this.playerPosition.getAltitude() + 1);
			
			return;
		}
		else
		{
			this.playerPosition.setAltitude(this.playerPosition.getAltitude() - 1);
			
			return;
		}
		
	}
	
	public boolean passwordCompare(String pass)
	{
		return (this.password.equals(pass));
	}
	
	//Returns the players level so that the monsters can be a comparable level
	public int getPlayerLevel()
	{
		return level;
	}

	//Writes the player's significant information to a file for saving the character
	public void savePlayer()
	{
		
		String fileName = this.playerName + ".dat";  //playerName.dat is the name of the files created
		
		File file = new File(fileName);
		
		if(file.exists())
		{
			file.delete();
		}
		
		try {
			PrintWriter outputStream = new PrintWriter(fileName);  //Open a new file with the correct name
			
			//Print the needed information to the file
			outputStream.println(this.className);
			
			outputStream.println(this.playerName);
			
			outputStream.println(this.level);
			
			outputStream.println(this.playerPosition.getLatitude());
			
			outputStream.println(this.playerPosition.getLongitude());
			
			outputStream.println(this.playerPosition.getAltitude());
			
			outputStream.println(this.weapon.getCategory());
			
			outputStream.println(this.weapon.getMaterial());
			
			outputStream.println(this.password);
			
			outputStream.println(this.currentXP);
			
			outputStream.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
