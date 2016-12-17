/************************************************************************
 * 																	  	*
 * 																	  	*
 * 																	  	*
 * 		Name: Patrick Leonard										  	*
 * 		Class: CS202												  	*
 * 		Assignment: Program #4-5									  	*
 * 		Filename: Ghost.java											*
 * 		Date: 11/29/2012												*
 * 																		*
 * 		This file implements the Ghost class. This class is derived from*
 * 	the Monster base class. This is a Wizard type monster which means   *
 * 	the significant statistics are based on the same as the Wizard   	*
 * 	class. The constructor assigns the base statistics to the Ghost, and*
 *  also implements the functions to create the hit and damage scores.  *
 *  It uses a Random class object to give some randomness to those 		*
 *  scores. 															*
 ***********************************************************************/


package edupdxleonard8;

//Imports Random to use a Random class object
import java.util.Random;

//Derived from the Monster base class
public class Ghost extends Monster {

	//Constructor that creates a Ghost object with the given level argument. 
	//Receives an integer as the argument.
	public Ghost(int playerLevel)
	{
		super();  //Call the base class constructor. 
		
		//Assigns the vital statistics of the Bear object
		this.monsterName = "Lingering Ghost";
		
		this.strength = 5;
		
		this.vitality = 5;
		
		this.dexterity = 13;
		
		this.agility = 15;
		
		this.intelligence = 20;
		
		this.wisdom = 19;
		
		this.currentHP = this.maxHP = 39;
		
		this.currentTP = this.maxTP = 19;
		
		this.level = 1;
		
		this.expYield = 20;
		
		//Creates a Random class object. 
		this.random = new Random();
		
		//Levels up the monster to the given level input argument
		levelComparable(playerLevel);
		
	}
	
	//Returns the hit score of the Ghost when in combat with the player. Uses the Random classes nextInt() to vary the hit score.
	//This function uses the the intelligence and wisdom of the Ghost to determine hit due to being a Wizard type monster.
	@Override
	public int getHitScore() {
		// TODO Auto-generated method stub
		int hitScore = this.random.nextInt(Math.round((this.intelligence + this.wisdom)/this.randomnessFactor)) + (this.wisdom/2);
									//Random based on Intelligence and Wisdom				Wisdom divided by two as the base
		return hitScore;
	}

	//Returns the damage score of the Ghost when in combat with the player. Uses the Random classes nextInt() to vary the hit score.
	//This function uses the the intelligence and wisdom of the Ghost to determine hit due to being a Wizard type monster.
	@Override
	public int getDamageScore() {
		// TODO Auto-generated method stub
		int damageScore = this.random.nextInt(Math.round((this.intelligence + this.wisdom)/this.randomnessFactor)) + (this.intelligence/2);
									//Random based on Intelligence and Wisdom				Wisdom divided by two as the base
		return damageScore;
	}
	
}
