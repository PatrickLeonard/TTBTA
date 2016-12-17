/************************************************************************
 * 																	  	*
 * 																	  	*
 * 																	  	*
 * 		Name: Patrick Leonard										  	*
 * 		Class: CS202												  	*
 * 		Assignment: Program #4-5									  	*
 * 		Filename: Boss.java												*
 * 		Date: 12/04/2012												*
 * 																		*
 * 		This file implements the Boss class. This class is derived from *
 * 	the Monster base class. This is a Boss type monster which means     *
 * 	the significant statistics are based on a stronger than usual beast	*
 * 	class. The constructor assigns the base statistics to the Boss, and  *
 *  also implements the functions to create the hit and damage scores.  *
 *  It uses a Random class object to give some randomness to those 		*
 *  scores. 															*
 ***********************************************************************/


package edupdxleonard8;

//Imports Random to use a Random class object
import java.util.Random;

//Derived from the Monster base class
public class Boss extends Monster {

	//Constructor that creates a Boss object with the given level argument. 
	//Receives an integer as the argument.
	public Boss()
	{
		super();     //Call the base class constructor. 
	
		//Assigns the vital statistics of the Bear object
		this.monsterName = "Tower Boss";
		
		this.strength = 17;
		
		this.vitality = 18;
		
		this.dexterity = 21;
		
		this.agility = 20;
		
		this.intelligence = 5;
		
		this.wisdom = 8;
		
		this.currentHP = this.maxHP = 45;
		
		this.currentTP = this.maxTP = 21;
		
		this.level = 1;
		
		this.expYield = 20;
		
		//Creates a Random class object. 
		this.random = new Random();
		
		//Boss for this game will be level 35
		int bossLevel = 35;
		
		for(int i = 1; i < bossLevel; ++i)
		{
			levelUp();
		}
		
	}
	
	//Returns the hit score of the Boss when in combat with the player. Uses the Random classes nextInt() to vary the hit score.
	//This function uses the agility and dexterity of the Boss to determine hit due to being a Thief type monster.
	@Override
	public int getHitScore() {
		// TODO Auto-generated method stub
		int hitScore = random.nextInt(Math.round((this.agility + this.dexterity)/this.randomnessFactor)) + (this.dexterity/2);
									    //Random based on Agility and Dexterity					Dexterity divided by two as the base
		return hitScore;
	}

	//Returns the damage score of the Boss when in combat with the player. Uses the Random classes nextInt() to vary the hit score.
	//This function uses the the agility and dexterity of the Boss to determine hit due to being a Thief type monster.
	@Override
	public int getDamageScore() {
		// TODO Auto-generated method stub
		int damageScore = random.nextInt(Math.round((this.agility + this.dexterity)/this.randomnessFactor)) + (this.agility/2);
		 									//Random based on Agility and Dexterity					Agility divided by two as the base
		return damageScore;
	}

}