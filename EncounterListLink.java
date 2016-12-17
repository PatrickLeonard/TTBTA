/************************************************************************
 * 																	  	*
 * 																	  	*
 * 																	  	*
 * 		Name: Patrick Leonard										  	*
 * 		Class: CS202												  	*
 * 		Assignment: Program #4-5									  	*
 * 		Filename: EncounterListLink.java		     					*
 * 		Date: 11/29/2012												*
 * 																		*
 * 		This file implements the EncounterListLink class. It is a link  *
 * 	in the "flexible array" that keeps an array of visited encounters   *
 * 	from the Game class. With a reference to the next link, it adds     *
 * 	Encounters to it's array with addEncounter(), searches the array    *
 *  with searchArray(), and sets and gets the reference to the next     *
 *  link. Removing Encounters is not required for this game, and so a 	*
 *  removal function is not implemented. There is also no purpose to	*
 *  display all the Encounters, so that is not implemented either. When *
 *  required, after resting, the list will be removed and a new one    *
 *  created. This creates the effect of a changing map over time        *
 *  resting.                                                            *
 *  ********************************************************************/

package edupdxleonard8;

public class EncounterListLink {
	
	//A next reference, and array of Encounters, and the size of the array
	private EncounterListLink next;
	private Encounter encounterArray[];
	private static final int SIZE = 10;
	
	//Create the array, and set the references to null. 
	public EncounterListLink()
	{
		this.next = null;
		
		this.encounterArray = new Encounter[SIZE];
		
		for(int i = 0; i < SIZE; ++i)
		{
			this.encounterArray[i] = null;
		}
		
	}
	
	//Add an Encounter to the array, return true if it's added, return false if not
	public boolean addEncounter(Encounter newEncounter)
	{
		//Start at the beginning of the array
		int index = 0;
		
		//While not passed the end of the array, and while the reference is null
		while((index != SIZE) && (this.encounterArray[index] != null))
		{
			++index;  //increase index until an empty spot or passed the array
		}
		
		//If not passed the array, assign the element to the input Encounter
		if(index < SIZE)
		{
			this.encounterArray[index] = newEncounter;
			
			return true; //Then return true
		}
		else  //If passed the array, then it's full and return false
		{
			return false;
		}
	}
	
	//Search the array with given integer input
	public Encounter searchArray(int key)
	{
		//Start at the beginning of the array
		int index = 0;
		
		//While not passed the array, the element isn't null, test element for match
		while((index != SIZE) && (this.encounterArray[index] != null) && 
					(this.encounterArray[index].idCompare(key) == false))
		{
			++index; //Step to next element
		}
		
		//If not passed the array, return the element (may be null)
		if(index < SIZE)
		{
			return this.encounterArray[index];
		}
		else //If passed the array, it's not here, return null
		{
			return null;
		}
	}
	
	//Sets the next reference to the input EncounterListLink reference
	public void setNext(EncounterListLink nextLink)
	{
		this.next = nextLink;
	}
	
	//Returns a reference to the next EncounterListLink
	public EncounterListLink getNext()
	{
		return this.next;
	}
	
}
