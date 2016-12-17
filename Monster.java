/************************************************************************
 * 																	  	*
 * 																	  	*
 * 																	  	*
 * 		Name: Patrick Leonard										  	*
 * 		Class: CS202												  	*
 * 		Assignment: Program #4-5									  	*
 * 		Filename: Monster.java											*
 * 		Date: 11/29/2012												*
 * 																		*
 * 		This file implements the Monster abstract base class. Bear, Rat,*
 * 	and Ghost classes derive from this class. This class has all the    *
 * 	members for the monster, and the constructor creates the 			*
 * 	randomnessFactor integer that is used to scale the randomness as    *
 *  the levels of the monsters increase. It also manages statistic      *
 *  growth from gaining levels, determines if the monster is dead, and	*
 *  defensive combat functions (running, dodging, and taking damage).	*
 *  Also has a function to level the monster to an appropriate level to *
 *  keep the battle competitive and a function to yield experience      *
 *  to the player after the monster is defeated.                        *
 ***********************************************************************/

package edupdxleonard8;

//To create a Random class object
import java.util.Random;
//To use the Math class
import java.lang.Math;

public abstract class Monster {

	//Protected data members for the Monsters
	//Mostly the "stats" for the monsters, with the name
	//and a Random class object.
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
	protected int level;
	protected int expYield;
	protected long randomnessFactor;
	protected String monsterName;
	protected Random random;
	
	//Abstract getHitScore and getDamageScore functions are implemented differently 
	//depending on the derived class. 
	public abstract int getHitScore();
	
	public abstract int getDamageScore();
	
	//Constructor assigns the randomnessFactor data member with a number based
	//on the level of the monster
	public Monster()
	{
		this.randomnessFactor = (10 + Math.round(Math.pow(1.05,  (this.level - 1.0))));
	}
	
	//Determines and returns the run score of the monsters, this gets compared
	//to the player's run score to determine if the player is able to run away 
	//from the battle. It has the agility divided by two as the base added to 
	//a random integer between 0 and the agility stat divided by randomnessFactor minus one
	public int getRunScore(){
		
		return random.nextInt(Math.round((this.agility)/randomnessFactor)) + (this.agility/2);
	}
	
	//Resolves whether or not the monster is hit by the player. Returns a String "HIT" if hit,
	//returns a String "MISS" if dodged. Receives and integer as an argument
	public String isHit(int enemyHitScore) {
		// TODO Auto-generated method stub
		
		//Dodge score is the same as the run score
		int dodgeScore = random.nextInt(Math.round(this.agility/this.randomnessFactor)) + (this.agility/2);
		
		//Subtract the dodgeScore by the input hitScore
		int resolveHit = enemyHitScore - dodgeScore;
		
		//If there is more dodge than hit, then it's a miss
		if(resolveHit < 0)
		{
			System.out.println(this.monsterName + " dodges the attack!");
			return "MISS";
		}
		else  //If more hit than dodge, then it's a hit
		{
			System.out.println(this.monsterName + " is stuck by the attack!");
			return "HIT";
		}
		
	}
	
	//Determines how much damage is done to the monster, receives an integer as an argument
	public void takeDamage(int dmgScore) {

		//Creates a defenseScore from a random integer between 0 and the quantity of vitality plus agility plus wisdom 
		//divided by the randomnessFactor quantity minus one. This random integer is then added to a base of vitality divided by 3.
		int defenseScore = random.nextInt(Math.round((this.vitality + this.agility + this.wisdom)/this.randomnessFactor)) + (this.vitality/3);
		
		//Damage done is the input damage score divided by the defenseScore
		int damage = ((dmgScore - defenseScore));
		
		//If there is more defense than damage, then no damage was done
		if(damage <= 0)
		{
			System.out.println("No damage was taken!");
		}
		else //If damage is greater than defense, then state the damage taken and subtract if from the currentHP data member
		{
			System.out.println(this.monsterName + " takes " + damage + " points of damage!");
			
			currentHP -= damage;
		}
		
		return;
	}

	//Levels the monster up to a comparable level (+/- 1) of the integer argument
	public void levelComparable(int playerLevel)
	{
		
		int monsterLevel = 0;
		
		int levelRange = 2;
		
		//Get a random number between 0 and (the levelRange - 1)
		int levelDifference = random.nextInt(levelRange);
		
		//Get a random true or false
		boolean plusMinus = random.nextBoolean();
		
		//Use the random true or false to determine the + or - 
		//If minus the level is below the player, if plus the level
		//is above the player
		if(plusMinus == true)
		{
			monsterLevel = playerLevel + levelDifference;
		}
		else
		{
			monsterLevel = playerLevel - levelDifference;
		}
		
		//Level the monster to the correct level as needed. 
		for(int i = 1; i < monsterLevel; ++i)
		{
			levelUp();
		}
		
	}
	
	//Returns the experience points for the defeated monster
	//the expYield data member
	public int yieldExp()
	{
		return this.expYield;
	}

	//Manages the growth of statistics upon level up.
	//Increases statistics as well as experience points yield
	public void levelUp() {
		// TODO Auto-generated method stub

		double growth = .1;
		
		this.strength += (this.strength * growth);
		
		this.vitality += (this.vitality * growth);
		
		this.dexterity += (this.dexterity * growth);
		
		this.agility += (this.agility * growth);
		
		this.intelligence += (this.intelligence * growth);
		
		this.wisdom += (this.wisdom * growth);
		
		this.currentHP = this.maxHP = (this.vitality + this.agility + this.wisdom);
		
		this.currentTP = this.maxTP = ((this.dexterity + this.intelligence + this.strength)/2);
		
		this.expYield += (this.expYield * .25);
		
		++this.level;
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

		System.out.println(monsterName);
		System.out.println("Level: " + this.level);
		System.out.println("HP: " + this.currentHP + "/" + this.maxHP);
		System.out.println("TP: " + this.currentTP + "/" + this.maxTP);
		
	}
}
