/************************************************************************
 * 																	  	*
 * 																	  	*
 * 																	  	*
 * 		Name: Patrick Leonard										  	*
 * 		Class: CS202												  	*
 * 		Assignment: Program #4-5									  	*
 * 		Filename: Weapon.java											*
 * 		Date: 12/03/2012												*
 * 																		*
 * 		This file implements the Weapon class. This class is this node  *
 * 	in the Weapon Forest data structure (WeapForest class). It contains *
 * 	the significant statistics of the weapons that the Player class can	*
 * 	equip and use against the monsters. They are divided by category of *
 *  weapon and then by the material of the weapon based on the player   *
 *  level. This means the player will be able to equip better weapons	*
 *  at higher levels.													*
 ***********************************************************************/

package edupdxleonard8;

public class Weapon {

	//Data and references to the left and right children of the tree
	private String category;
	private String material;
	private int hitBonus;
	private int damageBonus;
	private Weapon leftChild;
	private Weapon rightChild;
	
	//Contructor that creates a weapon with a given category, material, hit bonus, and damage bonus
	public Weapon(String cat, String mat, int hit, int dmg)
	{
		this.category = cat;
		
		this.material = mat;
		
		this.hitBonus = hit;
		
		this.damageBonus = dmg;
		
		this.leftChild = null;
		
		this.rightChild = null;
	}
	
	//Compares input string to the objects material field, returns negative go left, returns positive go right
	public int compareString(String input)
	{
		return this.material.compareTo(input);
	}
	
	//Returns the string of the weapon category
	public String getCategory()
	{
		return this.category;
	}
	
	//Returns the material of the weapon
	public String getMaterial()
	{
		return this.material;
	}
	
	//Displays the weapon's name and statistics
	public void displayWeapon()
	{
		System.out.println("Weapon: " + this.material + " " + this.category + "  Hit: +" + this.hitBonus +
							" Dmg: +" + this.damageBonus);
	}
	
	//Adds the bonus to the input integer hit score
	public int adjustHit(int hitScore)
	{
		return this.hitBonus + hitScore;
	}
	
	//Adds the bonus to the input integer damage score
	public int adjustDmg(int damageScore)
	{
		return this.damageBonus + damageScore;
	}
	
	//sets the node's left child
	public void setLeftChild(Weapon left)
	{
		this.leftChild = left;
	}
	
	//sets the node's right chile
	public void setRightChild(Weapon right)
	{
		this.rightChild = right;
	}
	
	//returns the node's left child
	public Weapon getLeftChild()
	{
		return this.leftChild;
	}
	
	//returns the node's right child
	public Weapon getRightChild()
	{
		return this.rightChild;
	}
	
}
