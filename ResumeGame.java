/************************************************************************
 * 																	  	*
 * 																	  	*
 * 																	  	*
 * 		Name: Patrick Leonard										  	*
 * 		Class: CS202												  	*
 * 		Assignment: Program #4-5									  	*
 * 		Filename: ResumeGame.java	         					        *
 * 		Date: 11/29/2012												*
 * 																		*
 * 		This file implements the ResumeGame class. This class manages   *
 * 	reading character data from save files in the same folder and       *
 * 	returning those characters to the Main class for input into Game    *
 * 	class overloaded constructor. Creates a correct character class     *
 *  object, with the correct level, position, experience points, and    *
 *  equipped weapon from the last successful save.    	                *
 *  ********************************************************************/

package edupdxleonard8;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.util.Scanner;

public class ResumeGame {

	//Data members to receive data from the saved character files
	private Player player;
	private String characterName;
	private String className;
	private String name;
	private int level;
	private int lati;
	private int longi;
	private int alti;
	private int exp;
	private String weaponCategory;
	private String weaponMaterial;
	private String passwordFromFile;
	private String inputPassword;
	private Scanner scanner;
	
	//Constructor, assigns dummy values and instantiates a Scanner object
	public ResumeGame()
	{
		this.player = null;
		
		this.characterName = new String();
		
		this.className = null;
		
		this.name = null;
		
		this.level = 0;
		
		this.lati = -1;
		
		this.longi = -1;
		
		this.alti = -1;
		
		this.exp = 0;
		
		this.weaponCategory = null;
		
		this.weaponMaterial = null;
		
		this.passwordFromFile = null;
		
		this.inputPassword = new String();
		
		this.scanner = new Scanner(System.in);
	
	}
	
	//Display a list of the player character files for the player to choose from
	public void displaySavedCharacters()
	{
		//List all of the character files in the current directory
		File dir = new File(".");
		File [] files = dir.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(".dat");
			}
		});
	
		//Nice formatting
		System.out.println("\nCharacter List: ");
		System.out.println("---------------");
	
		for (File xmlfile : files)
		{
		
			String fileName = xmlfile.getName(); //Place file name into a String object
		
			int length = fileName.length();   //Get length of the fileName String
		
			String displayName = fileName.substring(0, (length - 4)); //eliminated .dat so only character name
															      //is displayed
			System.out.println(displayName);
		}
	}
	
	//Load the player character into a new Player object and then return the object to Main
	public Player loadPlayer()
	{
		//Prompt for character name, which is the file name
		System.out.print("\nPlease enter the name of your character: ");
	
		this.characterName = scanner.nextLine();
	
		System.out.print("\nPlease enter the password for this character: ");
	
		this.inputPassword = scanner.nextLine();
	
		try
		{
			Scanner fileScan = new Scanner(new File(this.characterName + ".dat")); //add file extension and open
		
			while(fileScan.hasNext())  //While the file has something to read
			{
				//Read in all necessary data
				this.className = fileScan.nextLine();
			
				this.name = fileScan.nextLine();
			
				this.level = fileScan.nextInt();
			
				this.lati = fileScan.nextInt();
			
				this.longi = fileScan.nextInt();
				
				this.alti = fileScan.nextInt();
			
				fileScan.nextLine();
			
				this.weaponCategory = fileScan.nextLine();
			
				this.weaponMaterial = fileScan.nextLine();
			
				this.passwordFromFile = fileScan.nextLine();
			
				this.exp = fileScan.nextInt();
			
			}
		
			fileScan.close();
		
			//catch file not found exception, announce file was not found and break from switch
		} catch (FileNotFoundException e) {
		
			System.out.println("\n\n---/---/--File not found!--/---/---\n");
		
			return null;

		}
		
		//If the saved Player is a fighter then create a new fighter object, and so on respectively
		if(className.equals("Fighter"))
		{
			this.player = new Fighter(this.name, this.lati, this.longi, this.alti, this.level, 
										this.exp, this.passwordFromFile);
		}
		else if(className.equals("Thief"))
		{
			this.player = new Thief(this.name, this.lati, this.longi, this.alti, this.level, 
										this.exp, this.passwordFromFile);
		}
		else
		{
			this.player = new Wizard(this.name, this.lati, this.longi, this.alti, this.level, 
										this.exp, this.passwordFromFile);
		}
		
		//Compare the password protection for loading characters
		if(!this.player.passwordCompare(inputPassword))
		{
			System.out.println("Password does not match!");
		
			return null;
		}
		
		return this.player;
	}
	
	//Returns the saved weapon category to create a new weapon object
	public String resumeWeaponCategory()
	{
		return this.weaponCategory;
	}
	
	//Returns the saved weapon material to create a new weapon object
	public String resumeWeaponMaterial()
	{
		return this.weaponMaterial;
	}
}
