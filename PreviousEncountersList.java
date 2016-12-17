/************************************************************************
 * 																	  	*
 * 																	  	*
 * 																	  	*
 * 		Name: Patrick Leonard										  	*
 * 		Class: CS202												  	*
 * 		Assignment: Program #4-5									  	*
 * 		Filename: PreviousEncountersList.java							*
 * 		Date: 11/29/2012												*
 * 																		*
 * 		This file implements the PreviousEncountersList class. It is a  *
 * 	"flexible array" that keeps already visited encounters from the Game*
 * 	class. These encounters are stored here with addEncounter(), and    *
 * 	retrieved using searchList(). This allows the game to keep track    *
 *  what has happened in parts of the map that the player has visited.  *
 *  There is no need to remove individual encounters from this array,	*
 *  so a removal function is not implemented. There is also no purpose	*
 *  to display all the Encounters, so that is not implemented either.   *
 *  When required, after resting, the list will be removed and a new one*
 *  created. This creates the effect of a changing map over time        *
 *  resting.                                                            *
 *  ********************************************************************/

package edupdxleonard8;

public class PreviousEncountersList {

	//First link in the flexible array 
	private EncounterListLink first;
	
	//Constructor that makes the list empty to begin
	public PreviousEncountersList()
	{
		first = null;
	}
	
	
	//Adds an Encounter to the list, takes an Encounter class as an argument
	public void addEncounter(Encounter newEncounter)
	{	
		//Start at the beginning
		EncounterListLink current = first;
		
		//If the list is empty, create a new link and add the Encounter to it
		if(current == null)
		{
			first = new EncounterListLink();
			
			first.addEncounter(newEncounter);
		}
		else   //If not, keep trying to add an Encounter to the link, 
		{
			while(current.addEncounter(newEncounter) == false)
			{	
				if(current.getNext() == null)   //If the link is full and there are no empty links
				{
					EncounterListLink temp = new EncounterListLink();  //Create a new link and add it to the list
					
					current.setNext(temp);
				}
				
				current = current.getNext();  //Step to the next link
			}
		}
	}
	
	//Search through the list and return an Encounter based on the integer input
	public Encounter searchList(int key)
	{
		//Start at the beginning
		EncounterListLink current = first;
		
		//Temp Encounter used to return
		Encounter temp = null;
		
		//If the list is empty....say so
		if(current == null)
		{
			System.out.println("The list is empty~!");
			
			return temp;
		}
		else   //If not, while not at the end of the list
		{
			while(current != null && temp == null)  //And the encounter hasn't been found
			{
				temp = current.searchArray(key);  //Call searchArray() in EncounterListLink
				
				if(temp == null)
				{
					current = current.getNext(); //Step to the next link
				}
			}
			
			return temp;  //Return temp, null or not
		}
		
	}
	
}
