/*
 * Names: Justin Peng and Stephen Hamlin
 * Section Leader: Katherine Erdman
 * This program reads a given file of names of pop stars and their popularity rankings throughout the decades and lets the user input a name of a pop artist and graphs a line of their popularity trend with a line graph.
 * The name and their ranking is labeled at each decade
 * The user can then choose to clear the canvas or input another name that is graphed with a different color in iterations of 4.
 */

import acm.program.*;
import java.awt.event.*;
import javax.swing.*;

public class NameSurfer extends Program implements NameSurferConstants {
	private JTextField userEntry;
	private NameSurferDatabase namesData;
	private NameSurferGraph namesGraph;
	/*
	 * Puts buttons and textfields onto the canvas while allowing them to be interacted with
	 * In addition, it scans the data file that is given in the problem
	 */
	public void init() {
		JLabel name = new JLabel("Name: ");
		add(name, NORTH);
		userEntry = new JTextField(TEXT_FIELD_WIDTH);
		userEntry.addActionListener(this);
		userEntry.setActionCommand("Graph");
		add(userEntry, NORTH);
		JButton graph = new JButton("Graph");
		add(graph, NORTH);
		JButton clear = new JButton("Clear");
		add(clear, NORTH);
		addActionListeners();
		namesData = new NameSurferDatabase(NAMES_DATA_FILE);
		namesGraph = new NameSurferGraph();
		add(namesGraph);
		namesGraph.update();
	}
	/*
	 * Performs actions based off of interactions with the buttons and textfields
	 * Hitting the clear button prints clear
	 * Hitting enter or pressing graph takes the text in the textfield, puts it to lowercase, and finds the value of the key of the name and prints it out
	 * precondition: The clear button, graph button, or enter was pressed
	 * postcondition: The action that goes with each action is performed
	 */
	public void actionPerformed(ActionEvent event) {
		if (event.getActionCommand().equals("Clear")) {
			namesGraph.clear();
			namesGraph.update();
		}else if (event.getActionCommand().equals("Graph")){
			String name = userEntry.getText();
			name = name.toLowerCase();
			NameSurferEntry namePop = namesData.findEntry(name);
			namesGraph.addEntry(namePop);
			namesGraph.update();
		}
	}
}

