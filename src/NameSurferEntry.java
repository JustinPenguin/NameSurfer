/*
 * This file detects the name of the pop artist from a line of data and stores their rankings into an arraylist. It also merges the name and the ranks together into a string for the label.
 */

import java.util.*;

public class NameSurferEntry implements NameSurferConstants {

	private String name;
	private ArrayList<Integer> popRatings;
	/*
	 * Scans the line that was given by the next line of the nameSuferDatabase and sorts the line into the name and the ratings. It sets the name as the name of the line and puts the ratings into the arraylist
	 * precondition: The next line of the given file has been read
	 * postcondition: The name is the name on that file and the ratings are put into an arraylist
	 */
	public NameSurferEntry(String dataLine) {
		Scanner data = new Scanner(dataLine);
		name = data.next();
		popRatings = new ArrayList<>();
		while (data.hasNextInt()){
			popRatings.add(data.nextInt());
		}
		data.close();
	}

	/*
	 * Returns the name to the name popularity hashmap in NameSurferDatabase
	 * precondition: The scanner is trying to put the name of the people and the line with that name into a hashmap
	 * postcondition: The name of the line is given
	 */
	public String getName() {
		return name;
	}
  
	/*
	 * Gets the rank of a person at a specific decade
	 * precondition: getRank is called on in the addLine method to find the rank at a specific decade
	 * postcondition: The rank is given
	 */
	public int getRank(int decadesSince1900) {
		int rank = popRatings.get(decadesSince1900);
		return rank;	
	}

	/*
	 * This turns the ratings into a string and adds the name before it
	 * precondition: The rating and name are separate
	 * postcondition: The rating and name are put into a single string
	 */
	public String toString() {
		String popArray = popRatings.toString();
		String entryString = name + " " + popArray;
		return entryString;
	}
}

