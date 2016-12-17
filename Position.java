/************************************************************************
 * 																	  	*
 * 																	  	*
 * 																	  	*
 * 		Name: Patrick Leonard										  	*
 * 		Class: CS202												  	*
 * 		Assignment: Program #4-5									  	*
 * 		Filename: Position.java											*
 * 		Date: 11/29/2012												*
 * 																		*
 * 		This file implements the Position class. It packages the        *
 * 	player's latitude and longitude coordinates on the mapGrid. It is   *
 * 	a basic class of setters and getters to manage and track the        *
 * 	player's position on the mapGrid.                                   *
 ***********************************************************************/

package edupdxleonard8;

public class Position {

	private int latitude;
	private int longitude;
	private int altitude;
	
	//Constructor to give a starting point on the map
	public Position(int lati, int longi, int alti)
	{
		this.setLatitude(lati);
		
		this.setLongitude(longi);
		
		this.setAltitude(alti);
	}

	//Returns the longitude coordinate
	public int getLongitude() {
		return longitude;
	}

	//Sets the longitude coordinate
	public void setLongitude(int longi) {
		this.longitude = longi;
	}

	//Returns the latitude coordinate
	public int getLatitude() {
		return latitude;
	}

	//Sets the latitude coordinate
	public void setLatitude(int lati) {
		this.latitude = lati;
	}
	
	//Returns the altitude coordinate
	public int getAltitude() {
		return altitude;
	}
	
	//Sets the altitude coordinate
	public void setAltitude(int altitude) {
		this.altitude = altitude;
	}
	
}
