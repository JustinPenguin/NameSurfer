/*
 * This file searches for the name inputted by the user in the hashmap and returns the value of the key. It also sets the name and the line that contains it into a hashmap
 */

import java.io.*;
import java.util.*;

public class NameSurferDatabase implements NameSurferConstants {
	
	HashMap<String, NameSurferEntry> namePopularity;
	/*
	 * Gets the filename that is inputted into it and scans each line.
	 * It takes the name and the line that contains it and puts it into a hashmap with the name as they key and the line as the value
	 * precondition: The scanner is opened to scan the given file
	 * postcondition: The name is set as the key and the line is set as the value in a hashmap
	 */
	public NameSurferDatabase(String filename) {
		namePopularity = new HashMap<>();
		try{
			String fileLine = "";
			Scanner popDatabase = new Scanner(new File(filename));
			while (popDatabase.hasNextLine()){
				fileLine = popDatabase.nextLine();
				NameSurferEntry nameLine = new NameSurferEntry(fileLine);
				namePopularity.put(nameLine.getName(), nameLine);
			}
			popDatabase.close();
		}catch(IOException e){
		}
	}
	
	/*
	 * Takes the name of the user inputted name from the textfield, capitalizes the first letter, and searches for it in the hashmap and returns the value of the key (name)
	 * precondition: The findEntry method is called on by the actionPerformed method 
	 * postcondition: The value of the key is returned to the actionPerformed method
	 */
	public NameSurferEntry findEntry(String name) {
		String newName = "";
		for (int i = 0; i < name.length(); i++){
			char currentChar = name.charAt(i);
			newName += currentChar;
			if (i == 0){
				newName = newName.toUpperCase();
			}
		}
		NameSurferEntry entry = namePopularity.get(newName);
		return entry;
	}
}

