/************************************************************************
 * 																	  	*
 * 																	  	*
 * 																	  	*
 * 		Name: Patrick Leonard										  	*
 * 		Class: CS202												  	*
 * 		Assignment: Program #4-5									  	*
 * 		Filename: Fighter.java											*
 * 		Date: 11/29/2012												*
 * 																		*
 * 	 This file implements the Fighter class. This class is derived from *
 * 	the Player base class. The Fighter's prominent statistics are       *
 * 	Strength and Vitality, with weaker mental statistics. The		  	*
 * 	constructor assigns the base statistics to the Fighter. This also   *
 *  implements the functions to create the hit and damage scores. It    *
 *  uses a Random class object to give some randomness to those scores	*
 ***********************************************************************/

package edupdxleonard8;

//Imports Random to use a Random class object
import java.util.Random;

//Derived from the Player base class
public class Fighter extends Player {
	
	//Constructor that creates a Fighter object with the given String argument. 
	//Receives integer arguments for the starting position. Includes password String argument
	public Fighter(String name, int lati, int longi, int alti, String pass)
	{
		super(name, lati, longi, alti, pass);   //Call the base class constructor. 
		
		//Assigns the vital statistics of the Bear object
		this.className = "Fighter";
		
		this.strength = 21;
		
		this.vitality = 24;
		
		this.dexterity = 17;
		
		this.agility = 16;
		
		this.intelligence = 10;
		
		this.wisdom = 12;
		
		this.currentHP = this.maxHP = 61;
		
		this.currentTP = this.maxTP = 22;
		
		this.level = 1;
		
		this.currentXP = 0;
		
		this.toNextLevel = 100;
		
		//Creates a Random class object. 
		random = new Random();
		
	}
	
	//Overloaded constructor that allows player to resume game at same 
	//level and in the position the game was saved at and experience acquired
	public Fighter(String name, int lati, int longi, int alti, int level, int exp, String pass)
	{
		this(name, lati, longi, alti, pass);
		
		for(int i = 1; i < level; ++i)
		{
			levelUp(true);  //Silent level up for resuming game
		}
		
		this.currentXP = exp;
		
	}

	//Returns the hit score of the Fighter when in combat with the monster. Uses the Random classes nextInt() to vary the hit score.
	//This function uses the the Dexterity and Agility of the Fighter, but with Strength as base, to determine hit score.
	@Override
	public int getHitScore() {
		// TODO Auto-generated method stub
		int hitScore = random.nextInt(Math.round((this.dexterity + this.agility)/this.randomnessFactor)) + (this.strength/2);
											//Random based on Agility and Dexterity					Strength divided by two as the base
		return this.weapon.adjustHit(hitScore); //Return hit score adjusted by the equipped weapon
	}

	//Returns the damage score of the Fighter when in combat with the monster. Uses the Random classes nextInt() to vary the hit score.
	//This function uses the the Vitality and Strength to determine damage score.
	@Override
	public int getDamageScore() {
		// TODO Auto-generated method stub
		int damageScore = random.nextInt(Math.round((this.vitality + this.strength)/this.randomnessFactor)) + (this.strength/2);
												//Random based on Vitality and Strength					Strength divided by two as the base
		return this.weapon.adjustDmg(damageScore);  //Return damage score adjusted by the equipped weapon
	}
	
	//Returns a random category of weapon for the monster to drop after battle
	@Override
	public String getWeaponCategory()
	{
		int category = random.nextInt(3);
		
		if(category == 0)
		{
			return "Long Sword";   //Hit focused
		}
		else if(category == 1)
		{
			return "Two-handed Sword";  //Balanced 
		}
		else
		{
			return "Battle Axe";   //Damage focused
		}
		
	}	
	
	//Returns a material based on the input String category, and the level of the player
	@Override
	public String getWeaponMaterial(String category)
	{
		String material = new String();
		
		if((category.equals("Long Sword")) || (category.equals("Two-handed Sword")) || (category.equals("Battle Axe")))
		{
			if((this.level >= 1) && (this.level < 6)) //Tier 1 weapon
			{
				material = "Iron";
			}
			else if((this.level >= 6) && (this.level < 11))  //Tier 2
			{
				material = "Bronze";
			}
			else if((this.level >= 11) && (this.level < 16))  //Tier 3
			{
				material = "Steel";
			}
			else if((this.level >= 16) && (this.level < 21))  //Tier 4
			{
				material = "Platinum";
			}
			else if((this.level >= 21))   //Tier 5 (max)
			{
				material = "Orichalcum";
			}
		}
		
		return material;
	}
	

}
