/************************************************************************
 * 																	  	*
 * 																	  	*
 * 																	  	*
 * 		Name: Patrick Leonard										  	*
 * 		Class: CS202												  	*
 * 		Assignment: Program #4-5									  	*
 * 		Filename: Wizard.java											*
 * 		Date: 11/29/2012												*
 * 																		*
 * 	  This file implements the Wizard class. This class is derived from *
 * 	the Player base class. The Wizard's prominent statistics are        *
 * 	Intelligence and Wisdom, with weaker physical statistics. The   	*
 * 	constructor assigns the base statistics to the Wizard. This also    *
 *  implements the functions to create the hit and damage scores. It    *
 *  uses a Random class object to give some randomness to those scores	*
 ***********************************************************************/

package edupdxleonard8;

//Imports Random to use a Random class object
import java.util.Random;

//Derived from the Player base class
public class Wizard extends Player {

	//Constructor that creates a Wizard object with the given String argument. 
	//Receives integer arguments for the starting position. Includes password String argument
	public Wizard(String name, int lati, int longi, int alti, String pass)
	{
		super(name, lati, longi, alti, pass);  //Call the base class constructor. 
		
		//Assigns the vital statistics of the Bear object
		this.className = "Wizard";
		
		this.strength = 12;
		
		this.vitality = 18;
		
		this.dexterity = 10;
		
		this.agility = 16;
		
		this.intelligence = 26;
		
		this.wisdom = 24;
		
		this.currentHP = this.maxHP = 56;
		
		this.currentTP = this.maxTP = 24;
		
		this.level = 1;
		
		this.currentXP = 0;
		
		this.toNextLevel = 100;
		
		//Creates a Random class object. 
		random = new Random();
		
	}

	//Overloaded constructor that allows player to resume game at same 
	//level and in the position the game was saved at and the same experience acquired
	public Wizard(String name, int lati, int longi, int alti, int level, int exp, String pass)
	{
		this(name, lati, longi, alti, pass);
		
		for(int i = 1; i < level; ++i)
		{
			levelUp(true);    //Silent level up for resuming game
		}
		
		this.currentXP = exp;
		
	}
	
	//Returns the hit score of the Wizard when in combat with the monster. Uses the Random classes nextInt() to vary the hit score.
	//This function uses the the intelligence and wisdom of the Wizard to determine hit score.
	@Override
	public int getHitScore() {
		// TODO Auto-generated method stub
		int hitScore = random.nextInt(Math.round((this.intelligence + this.wisdom)/this.randomnessFactor)) + (this.wisdom/2);
												//Random based on Intelligence and Wisdom				Wisdom divided by two as the base
		return this.weapon.adjustHit(hitScore); //Return hit score adjusted by the equipped weapon
	}

	//Returns the damage score of the player when in combat with the monster. Uses the Random classes nextInt() to vary the hit score.
	//This function uses the intelligence and wisdom of the Wizard to determine damage score.	
	@Override
	public int getDamageScore() {
		// TODO Auto-generated method stub
		int damageScore = random.nextInt(Math.round((this.intelligence + this.wisdom)/this.randomnessFactor)) + (this.intelligence/2);
											//Random based on Intelligence and Wisdom				Wisdom divided by two as the base
		return this.weapon.adjustDmg(damageScore);  //Return damage score adjusted by the equipped weapon
	}
	
	//Returns a random category of weapon for the monster to drop after battle
	@Override
	public String getWeaponCategory()
	{
		int category = random.nextInt(3);
		
		if(category == 0)
		{
			return "Amulet";
		}
		else if(category == 1)
		{
			return "Staff";
		}
		else
		{
			return "Wand";
		}
		
	}	
	
	//Returns a material based on the input String category, and the level of the player
	@Override
	public String getWeaponMaterial(String category)
	{
		String material = new String();
		
		if((category.equals("Staves")) || (category.equals("Wands")))
		{
			if((this.level >= 1) && (this.level < 6))
			{
				material = "Maple";
			}
			else if((this.level >= 6) && (this.level < 11))
			{
				material = "Willow";
			}
			else if((this.level >= 11) && (this.level < 16))
			{
				material = "Oak";
			}
			else if((this.level >= 16) && (this.level < 21))
			{
				material = "Elm";
			}
			else if((this.level >= 21))
			{
				material = "Ebony";
			}
		}
		else
		{
			if((this.level >= 1) && (this.level < 6))
			{
				material = "Zircon";
			}
			else if((this.level >= 6) && (this.level < 11))
			{
				material = "Opal";
			}
			else if((this.level >= 11) && (this.level < 16))
			{
				material = "Ruby";
			}
			else if((this.level >= 16) && (this.level < 21))
			{
				material = "Sapphire";
			}
			else if((this.level >= 21))
			{
				material = "Diamond";
			}
		}
		
		return material;
	}

}
