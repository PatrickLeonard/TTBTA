/************************************************************************
 * 																	  	*
 * 																	  	*
 * 																	  	*
 * 		Name: Patrick Leonard										  	*
 * 		Class: CS202												  	*
 * 		Assignment: Program #4-5									  	*
 * 		Filename: WeapForest.java										*
 * 		Date: 12/03/2012												*
 * 																		*
 * 		This file implements the WeapForest class. This class is a      *
 * 	Forest data structure (an unbalanced Binary Search Tree (BST) of    *
 * 	unbalanced BST's). This structure holds the categories and weapons	*
 * 	within those categories, and manages the searching and returning of *
 *  these weapons for use with the player class in equipping them.      *
 *  Once weapons are added they are not removed, so there is no purpose *
 *  in implementing algorithms to removed weapons from the Forest. 		*
 ***********************************************************************/

package edupdxleonard8;

public class WeapForest {

	private WeapCategory root;
	
	//Constructor, set the root category node to null, and then populate the forest with weapons 
	public WeapForest()
	{
		this.root = null;
		
		populateForest();
	}
	
	//Function to add a weapon to the forest, first find category then add weapon to that category tree
	public void addWeapon(Weapon newWeapon)
	{
		WeapCategory temp = findWeapCategory(newWeapon.getCategory());
		
		temp.addWeapon(newWeapon);
	}
	
	//Search the forest for a particular weapon, first category tree then material tree
	public Weapon findWeapon(String cat, String mat)
	{
		WeapCategory temp = findWeapCategory(cat);
		
		return temp.findWeapon(mat);
	}
	
	//Create a weapon category node, accepts a WeapCategory node as argument
	public void addWeapCategory(WeapCategory newWeapCategory)
	{
		WeapCategory parent = null;
		
		//Start at the beginning and use a reference to the parent node
		WeapCategory current = this.root;
		
		//If category tree is empty, create a new one
		if(current == null)
		{
			this.root = newWeapCategory;
		}
		else
		{
			while(current != null)   //While the current isn't off the end of the tree
			{
				
				parent = current;  //Keep the parent reference one step behind the current
				
				if(current.compareString(newWeapCategory.getCategory()) < 0) //If the key is less, go left
				{
					current = current.getLeftChild();
				}
				else if(current.compareString(newWeapCategory.getCategory()) > 0) //If the key is more, go right
				{
					current = current.getRightChild();
				}
				else
				{
					System.out.println("Node already exists!");  //If it matches, then the node already exists
					
					return;
				}
				
			}
			
			//If the given key is less than parent than add node to the left
			if(parent.compareString(newWeapCategory.getCategory()) < 0)
			{
				parent.setLeftChild(newWeapCategory);
			}
			else //Else add to the right
			{
				parent.setRightChild(newWeapCategory);
			}

		}
	}
	
	//Find the weapon category with the String key input
	public WeapCategory findWeapCategory(String key)
	{
		//Start at the beginning
		WeapCategory current = this.root;
		
		while(current != null)   //while not off the end of the tree
		{
			if(current.compareString(key) < 0)  //if key less then go left
			{
				current = current.getLeftChild();
			}
			else if(current.compareString(key) > 0) //if key is more go right
			{
				current = current.getRightChild();
			}
			else  //If match then break
			{
				break;
			}
		}
		
		//If not found say so
		if(current == null)
		{
			System.out.println("WeapCategory not found!");
		}
		
		return current;
	}
	
	//creates and adds category nodes for the given weapon categories
	public void populateForest()
	{
		WeapCategory daggers = new WeapCategory("Dagger");
		
		addWeapCategory(daggers);
		
		WeapCategory dirks = new WeapCategory("Dirk");
		
		addWeapCategory(dirks);
		
		WeapCategory shortSwords = new WeapCategory("Short Sword");
		
		addWeapCategory(shortSwords);
		
		WeapCategory longSwords = new WeapCategory("Long Sword");
		
		addWeapCategory(longSwords);
		
		WeapCategory twoHanders = new WeapCategory("Two-handed Sword");
		
		addWeapCategory(twoHanders);
		
		WeapCategory battleAxes = new WeapCategory("Battle Axe");
		
		addWeapCategory(battleAxes);
		
		WeapCategory amulets = new WeapCategory("Amulet");
		
		addWeapCategory(amulets);
		
		WeapCategory staves = new WeapCategory("Staff");
		
		addWeapCategory(staves);
		
		WeapCategory wands = new WeapCategory("Wand");
		
		addWeapCategory(wands);
		
		populateWeapons();
	}
	
	//Function to call all the add weapon functions, that add weapons of the particular
	//category, material, and bonuses
	public void populateWeapons()
	{
		addWands();
		
		addStaves();
		
		addAmulets();
		
		addBattleAxes();
		
		addTwoHandedSwords();
		
		addLongSwords();
		
		addShortSwords();
		
		addDaggers();
		
		addDirks();
	}
	
	//All the functions below, create weapons and add them to their particular category
	//to populate the Weapon Forest data structure
	public void addWands()
	{
		WeapCategory wands = findWeapCategory("Wand");
		
		Weapon mapleWand = new Weapon("Wand", "Maple", 0, 2);
		
		wands.addWeapon(mapleWand);
		
		Weapon willowWand = new Weapon("Wand", "Willow", 1, 4);
		
		wands.addWeapon(willowWand);
		
		Weapon oakWand = new Weapon("Wand", "Oak", 2, 8);
		
		wands.addWeapon(oakWand);
		
		Weapon elmWand = new Weapon("Wand", "Elm", 4, 16);
		
		wands.addWeapon(elmWand);
		
		Weapon ebonyWand = new Weapon("Wand", "Ebony", 8, 32);
		
		wands.addWeapon(ebonyWand);


	}
	
	public void addStaves()
	{
		WeapCategory staves = findWeapCategory("Staff");
		
		Weapon mapleStaff = new Weapon("Staff", "Maple", 1, 1);
		
		staves.addWeapon(mapleStaff);
		
		Weapon willowStaff = new Weapon("Staff", "Willow", 2, 2);
		
		staves.addWeapon(willowStaff);
		
		Weapon oakStaff = new Weapon("Staff", "Oak", 4, 4);
		
		staves.addWeapon(oakStaff);
		
		Weapon elmStaff = new Weapon("Staff", "Elm", 8, 8);
		
		staves.addWeapon(elmStaff);
		
		Weapon ebonyStaff = new Weapon("Staff", "Ebony", 16, 16);
		
		staves.addWeapon(ebonyStaff);

	}

	public void addAmulets()
	{
		WeapCategory amulets = findWeapCategory("Amulet");
		
		Weapon zirconAmulet = new Weapon("Amulet", "Zircon", 2, 0);
		
		amulets.addWeapon(zirconAmulet);
		
		Weapon opalAmulet = new Weapon("Amulet", "Opal", 4, 1);
		
		amulets.addWeapon(opalAmulet);
		
		Weapon rubyAmulet = new Weapon("Amulet", "Ruby", 8, 2);
		
		amulets.addWeapon(rubyAmulet);
		
		Weapon sapphireAmulet = new Weapon("Amulet", "Sapphire", 16, 4);
		
		amulets.addWeapon(sapphireAmulet);
		
		Weapon diamondAmulet = new Weapon("Amulet", "Diamond", 32, 8);
		
		amulets.addWeapon(diamondAmulet);

	}
	
	public void addBattleAxes()
	{
		WeapCategory battleAxes = findWeapCategory("Battle Axe");
		
		Weapon ironBattleAxe = new Weapon("Battle Axe", "Iron", 0, 2);
		
		Weapon bronzeBattleAxe = new Weapon("Battle Axe", "Bronze", 1, 4);
		
		Weapon steelBattleAxe = new Weapon("Battle Axe", "Steel", 2, 8);
		
		Weapon platinumBattleAxe = new Weapon("Battle Axe", "Platinum", 4, 16);
		
		Weapon orichalcumBattleAxe = new Weapon("Battle Axe", "Orichalcum", 8, 32);
		
		battleAxes.addWeapon(bronzeBattleAxe);
		
		battleAxes.addWeapon(steelBattleAxe);
		
		battleAxes.addWeapon(platinumBattleAxe);
		
		battleAxes.addWeapon(orichalcumBattleAxe);

		battleAxes.addWeapon(ironBattleAxe);
	}
	
	public void addTwoHandedSwords()
	{
		WeapCategory twoHandedSwords = findWeapCategory("Two-handed Sword");
		
		Weapon ironTwoHandedSword = new Weapon("Two-handed Sword", "Iron", 1, 1);
		
		Weapon bronzeTwoHandedSword = new Weapon("Two-handed Sword", "Bronze", 2, 2);
		
		Weapon steelTwoHandedSword = new Weapon("Two-handed Sword", "Steel", 4, 4);
		
		Weapon platinumTwoHandedSword = new Weapon("Two-handed Sword", "Platinum", 8, 8);
		
		Weapon orichalcumTwoHandedSword = new Weapon("Two-handed Sword", "Orichalcum", 16, 16);
		
		twoHandedSwords.addWeapon(bronzeTwoHandedSword);
		
		twoHandedSwords.addWeapon(steelTwoHandedSword);
		
		twoHandedSwords.addWeapon(platinumTwoHandedSword);
		
		twoHandedSwords.addWeapon(orichalcumTwoHandedSword);
		
		twoHandedSwords.addWeapon(ironTwoHandedSword);

	}
	
	public void addLongSwords()
	{
		WeapCategory longSwords = findWeapCategory("Long Sword");
		
		Weapon ironLongSword = new Weapon("Long Sword", "Iron", 2, 0);
		
		Weapon bronzeLongSword = new Weapon("Long Sword", "Bronze", 4, 1);
		
		Weapon steelLongSword = new Weapon("Long Sword", "Steel", 8, 2);
		
		Weapon platinumLongSword = new Weapon("Long Sword", "Platinum", 16, 4);
		
		Weapon orichalcumLongSword = new Weapon("Long Sword", "Orichalcum", 32, 8);
		
		longSwords.addWeapon(bronzeLongSword);
		
		longSwords.addWeapon(steelLongSword);
		
		longSwords.addWeapon(platinumLongSword);
		
		longSwords.addWeapon(orichalcumLongSword);
		
		longSwords.addWeapon(ironLongSword);

	}

	public void addShortSwords()
	{
		WeapCategory shortSwords = findWeapCategory("Short Sword");
		
		Weapon ironShortSword = new Weapon("Short Sword", "Iron", 0, 2);
		
		Weapon bronzeShortSword = new Weapon("Short Sword", "Bronze", 1, 4);
		
		Weapon steelShortSword = new Weapon("Short Sword", "Steel", 2, 8);
		
		Weapon platinumShortSword = new Weapon("Short Sword", "Platinum", 4, 16);
		
		Weapon orichalcumShortSword = new Weapon("Short Sword", "Orichalcum", 8, 32);
		
		shortSwords.addWeapon(bronzeShortSword);
		
		shortSwords.addWeapon(steelShortSword);
		
		shortSwords.addWeapon(platinumShortSword);
		
		shortSwords.addWeapon(orichalcumShortSword);
		
		shortSwords.addWeapon(ironShortSword);

	}

	public void addDaggers()
	{
		WeapCategory daggers = findWeapCategory("Dagger");
		
		Weapon ironDagger = new Weapon("Dagger", "Iron", 1, 1);
		
		Weapon bronzeDagger = new Weapon("Dagger", "Bronze", 2, 2);
		
		Weapon steelDagger = new Weapon("Dagger", "Steel", 4, 4);
		
		Weapon platinumDagger = new Weapon("Dagger", "Platinum", 8, 8);
		
		Weapon orichalcumDagger = new Weapon("Dagger", "Orichalcum", 16, 16);
		
		daggers.addWeapon(bronzeDagger);
		
		daggers.addWeapon(steelDagger);
		
		daggers.addWeapon(platinumDagger);
		
		daggers.addWeapon(orichalcumDagger);
		
		daggers.addWeapon(ironDagger);

	}

	public void addDirks()
	{
		WeapCategory dirks = findWeapCategory("Dirk");
		
		Weapon ironDirk = new Weapon("Dirk", "Iron", 2, 0);
		
		Weapon bronzeDirk = new Weapon("Dirk", "Bronze", 4, 1);
		
		Weapon steelDirk = new Weapon("Dirk", "Steel", 8, 2);
		
		Weapon platinumDirk = new Weapon("Dirk", "Platinum", 16, 4);
		
		Weapon orichalcumDirk = new Weapon("Dirk", "Orichalcum", 32, 8);
		
		dirks.addWeapon(bronzeDirk);
		
		dirks.addWeapon(steelDirk);
		
		dirks.addWeapon(platinumDirk);
		
		dirks.addWeapon(orichalcumDirk);
		
		dirks.addWeapon(ironDirk);

	}

}
