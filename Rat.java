/************************************************************************
 * 																	  	*
 * 																	  	*
 * 																	  	*
 * 		Name: Patrick Leonard										  	*
 * 		Class: CS202												  	*
 * 		Assignment: Program #4-5									  	*
 * 		Filename: Rat.java												*
 * 		Date: 11/29/2012												*
 * 																		*
 * 		This file implements the Rat class. This class is derived from  *
 * 	the Monster base class. This is a Thief type monster which means    *
 * 	the significant statistics are based on the same as the Thief   	*
 * 	class. The constructor assigns the base statistics to the Rat, and  *
 *  also implements the functions to create the hit and damage scores.  *
 *  It uses a Random class object to give some randomness to those 		*
 *  scores. 															*
 ***********************************************************************/


package edupdxleonard8;

//Imports Random to use a Random class object
import java.util.Random;

//Derived from the Monster base class
public class Rat extends Monster {

	//Constructor that creates a Rat object with the given level argument. 
	//Receives an integer as the argument.
	public Rat(int playerLevel)
	{
		super();     //Call the base class constructor. 
	
		//Assigns the vital statistics of the Bear object
		this.monsterName = "Giant Rat";
		
		this.strength = 15;
		
		this.vitality = 13;
		
		this.dexterity = 20;
		
		this.agility = 19;
		
		this.intelligence = 5;
		
		this.wisdom = 5;
		
		this.currentHP = this.maxHP = 37;
		
		this.currentTP = this.maxTP = 20;
		
		this.level = 1;
		
		this.expYield = 20;
		
		//Creates a Random class object. 
		this.random = new Random();
		
		//Levels up the monster to the given level input argument
		levelComparable(playerLevel);
		
	}
	
	//Returns the hit score of the Rat when in combat with the player. Uses the Random classes nextInt() to vary the hit score.
	//This function uses the agility and dexterity of the Rat to determine hit due to being a Thief type monster.
	@Override
	public int getHitScore() {
		// TODO Auto-generated method stub
		int hitScore = random.nextInt(Math.round((this.agility + this.dexterity)/this.randomnessFactor)) + (this.dexterity/2);
									    //Random based on Agility and Dexterity					Dexterity divided by two as the base
		return hitScore;
	}

	//Returns the damage score of the Rat when in combat with the player. Uses the Random classes nextInt() to vary the hit score.
	//This function uses the the agility and dexterity of the Rat to determine hit due to being a Thief type monster.
	@Override
	public int getDamageScore() {
		// TODO Auto-generated method stub
		int damageScore = random.nextInt(Math.round((this.agility + this.dexterity)/this.randomnessFactor)) + (this.agility/2);
		 									//Random based on Agility and Dexterity					Agility divided by two as the base
		return damageScore;
	}

}