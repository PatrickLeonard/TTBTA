/************************************************************************
 * 																	  	*
 * 																	  	*
 * 																	  	*
 * 		Name: Patrick Leonard										  	*
 * 		Class: CS202												  	*
 * 		Assignment: Program #4-5									  	*
 * 		Filename: Bear.java											  	*
 * 		Date: 11/29/2012												*
 * 																		*
 * 		This file implements the Bear class. This class is derived from *
 * 	the Monster base class. This is a Fighter type monster which means  *
 * 	the significant statistics are based on the same as the Fighter 	*
 * 	class. The constructor assigns the base statistics to the Bear, and *
 *  also implements the functions to create the hit and damage scores.  *
 *  It uses a Random class object to give some randomness to those 		*
 *  scores. 															*
 ***********************************************************************/



package edupdxleonard8;

//Imports Random to use a Random class object
import java.util.Random;

//Derived from the Monster base class
public class Bear extends Monster {

	//Constructor that creates a Bear object with the given level argument. 
	//Receives and integer as the argument.
	public Bear(int playerLevel)
	{
		super();       //Call the base class constructor. 
		
		//Assigns the vital statistics of the Bear object
		this.monsterName = "Cave Bear";
		
		this.strength = 18;
		
		this.vitality = 20;
		
		this.dexterity = 15;
		
		this.agility = 13;
		
		this.intelligence = 5;
		
		this.wisdom = 5;
		
		this.currentHP = this.maxHP = 38;
		
		this.currentTP = this.maxTP = 19;
		
		this.level = 1;
		
		this.expYield = 20;
		
		//Creates a Random class object. 
		this.random = new Random();
		
		//Levels up the monster to the given level input argument
		levelComparable(playerLevel);
	}
	
	//Returns the hit score of the Bear when in combat with the player. Uses the Random classes nextInt() to vary the hit score.
	//This function uses the the strength of the Bear to determine hit due to being a fighter type monster.
	@Override
	public int getHitScore() {
		// TODO Auto-generated method stub
		int hitScore = this.random.nextInt(Math.round((this.agility + this.dexterity)/this.randomnessFactor)) + (this.strength/2);   
												//Random based on Dexterity and Agility			Strength divided by two as the base
		return hitScore;
	}

	//Returns the damage score of the Bear when in combat with the player. Uses the Random classes nextInt() to vary the hit score.
	//This function uses the the strength and vitality of the Bear to determine hit due to being a fighter type monster.
	@Override
	public int getDamageScore() {
		// TODO Auto-generated method stub
		int damageScore = this.random.nextInt(Math.round((this.strength + this.vitality)/this.randomnessFactor)) + (this.strength/2);
											//Random based on Strength and Vitality                Strength divided by two as the base
		return damageScore;
	}

}