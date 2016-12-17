/************************************************************************
 * 																	  	*
 * 																	  	*
 * 																	  	*
 * 		Name: Patrick Leonard										  	*
 * 		Class: CS202												  	*
 * 		Assignment: Program #4-5									  	*
 * 		Filename: Thief.java											*
 * 		Date: 11/29/2012												*
 * 																		*
 * 	   This file implements the Thief class. This class is derived from *
 * 	the Player base class. The Thief's prominent statistics are         *
 * 	Dexterity and Agility, and have weaker mental statistics. The   	*
 * 	constructor assigns the base statistics to the Thief. This also     *
 *  implements the functions to create the hit and damage scores. It    *
 *  uses a Random class object to give some randomness to those scores	*
 ***********************************************************************/

package edupdxleonard8;

//Imports Random to use a Random class object
import java.util.Random;

//Derived from the Player base class
public class Thief extends Player {

	//Constructor that creates a Thief object with the given String argument. 
	//Receives integer arguments for the starting position. Includes password String argument
	public Thief(String name, int lati, int longi, int alti, String pass)
	{
		super(name, lati, longi, alti, pass);  //Call the base class constructor. 
		
		//Assigns the vital statistics of the Bear object
		this.className = "Thief";
		
		this.strength = 17;
		
		this.vitality = 16;
		
		this.dexterity = 24;
		
		this.agility = 21;
		
		this.intelligence = 10;
		
		this.wisdom = 12;
		
		this.currentHP = this.maxHP = 49;
		
		this.currentTP = this.maxTP = 22;
		
		this.level = 1;
		
		this.currentXP = 0;
		
		this.toNextLevel = 100;
		
		//Creates a Random class object. 
		this.random = new Random();
		
	}
	
	//Overloaded constructor that allows player to resume game at same 
	//level and in the position the game was saved at and the same experience acquired
	public Thief(String name, int lati, int longi, int alti, int level, int exp, String pass)
	{
		this(name, lati, longi, alti, pass);
		
		for(int i = 1; i < level; ++i)
		{
			levelUp(true);  //Silent level up for resuming game
		}
		
		this.currentXP = exp;
		
	}

	//Returns the hit score of the player when in combat with the monster. Uses the Random classes nextInt() to vary the hit score.
	//This function uses the agility and dexterity of the Thief to determine hit.	
	@Override
	public int getHitScore() {
		// TODO Auto-generated method stub
		int hitScore = this.random.nextInt(Math.round((this.agility + this.dexterity)/this.randomnessFactor)) + (this.dexterity/2);
		 							 	//Random based on Agility and Dexterity					Dexterity divided by two as the base
		return this.weapon.adjustHit(hitScore); //Return hit score adjusted by the equipped weapon
	}

	//Returns the damage score of the player when in combat with the monster. Uses the Random classes nextInt() to vary the hit score.
	//This function uses the agility and dexterity of the Thief to determine hit.	
	@Override
	public int getDamageScore() {
		// TODO Auto-generated method stub
		int damageScore = this.random.nextInt(Math.round((this.agility + this.dexterity)/this.randomnessFactor)) + (this.agility/2);
														//Random based on Agility and Dexterity		Agility divided by two as the base
		return this.weapon.adjustDmg(damageScore);  //Return damage score adjusted by the equipped weapon
	}

	//Returns a random category of weapon for the monster to drop after battle
	@Override
	public String getWeaponCategory()
	{
		int category = random.nextInt(3);
		
		if(category == 0)
		{
			return "Dirk";
		}
		else if(category == 1)
		{
			return "Dagger";
		}
		else
		{
			return "Short Sword";
		}
		
	}	
	
	//Returns a material based on the input String category, and the level of the player
	@Override
	public String getWeaponMaterial(String category)
	{
		String material = new String();
		
		if((category.equals("Dirk")) || (category.equals("Dagger")) || (category.equals("Short Sword")))
		{
			if((this.level >= 1) && (this.level < 6))
			{
				material = "Iron";
			}
			else if((this.level >= 6) && (this.level < 11))
			{
				material = "Bronze";
			}
			else if((this.level >= 11) && (this.level < 16))
			{
				material = "Steel";
			}
			else if((this.level >= 16) && (this.level < 21))
			{
				material = "Platinum";
			}
			else if((this.level >= 21))
			{
				material = "Orichalcum";
			}
		}
		
		return material;
	}
	
}
