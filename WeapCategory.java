/************************************************************************
 * 																	  	*
 * 																	  	*
 * 																	  	*
 * 		Name: Patrick Leonard										  	*
 * 		Class: CS202												  	*
 * 		Assignment: Program #4-5									  	*
 * 		Filename: WeapCategory.java										*
 * 		Date: 12/03/2012												*
 * 																		*
 * 		This file implements the WeapCategory class. This class is a    *
 * 	category node in the Weapon Forest data structure. It is a tree of  *
 * 	weapons of different materials that are within the categories.   	*
 * 	It allows for the addition of particular weapons in the category,   *
 *  but doesn't allow for removal as the weapons will not need to be    *
 *  removed for the life of the game.									*
 ***********************************************************************/


package edupdxleonard8;

public class WeapCategory {
	
	//category is the key for the binary search
	//and references to the Weapon root node, and the category
	//left and right nodes
	private String category;
	private WeapCategory leftChild;
	private WeapCategory rightChild;
	private Weapon root;
	
	//Constructor sets the key and nulls the references
	public WeapCategory(String cat)
	{
		this.category = cat;
		
		this.leftChild = null;
		
		this.rightChild = null;
		
		this.root = null;
	}
	
	//Return the category key
	public String getCategory()
	{
		return this.category;
	}
	
	//Compre the key to the given input
	public int compareString(String input)
	{
		return this.category.compareTo(input);
	}
	
	//Sets the left child node of the category tree
	public void setLeftChild(WeapCategory left)
	{
		this.leftChild = left;
	}
	
	//Sets the right child node of the category tree
	public void setRightChild(WeapCategory right)
	{
		this.rightChild = right;
	}
	
	//Returns the left child node of the category tree
	public WeapCategory getLeftChild()
	{
		return this.leftChild;
	}
	
	//Returns the right child node of the category tree
	public WeapCategory getRightChild()
	{
		return this.rightChild;
	}
	
	//Add a weapon to the weapon tree, with the Weapon as the input argument
	public void addWeapon(Weapon newWeapon)
	{
		Weapon parent = null;
		
		//Start at the top of the tree, with a reference that follows behind
		Weapon current = this.root;
		
		//If weapon tree is empty, assign input to root
		if(current == null)
		{
			this.root = newWeapon;
		}
		else
		{
			while(current != null) //While not off the end of the weapon tree
			{
				
				parent = current;
				
				if(current.compareString(newWeapon.getMaterial()) < 0) //If material is less, go left
				{
					current = current.getLeftChild();
				}
				else if(current.compareString(newWeapon.getMaterial()) > 0) //If material is more, go right
				{
					current = current.getRightChild();
				}
				else   //If matches then already exists
				{
					System.out.println("Node already exists!");
					
					return;
				}
				
			}
			
			//If material is less than parent material, attach to the left child
			if(parent.compareString(newWeapon.getMaterial()) < 0)
			{
				parent.setLeftChild(newWeapon);
			}
			else //Else attach to the left child
			{
				parent.setRightChild(newWeapon);
			}

		}
	}
	
	//Search the tree and return the proper weapon that matches the input material String
	public Weapon findWeapon(String key)
	{
		//Start at the beginning
		Weapon current = this.root;
		
		while(current != null) //While not off the end of the tree
		{
			if(current.compareString(key) < 0) //If less go left
			{
				current = current.getLeftChild();
			}
			else if(current.compareString(key) > 0) //If more go right
			{
				current = current.getRightChild();
			}
			else //If match then break
			{
				break;
			}
		}
		
		//If went off the tree, weapon is not in tree
		if(current == null)
		{
			System.out.println("Weapon not found!");
		}
		
		//return the Weapon reference, migh be null
		return current;
	}
	
}