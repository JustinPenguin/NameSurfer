/*
 * Graphs the line of the user inputted name onto the canvas and cycles through colors for each additional line. It also updates the canvas after each line created.
 */

import acm.graphics.*;
import java.awt.event.*;
import java.util.*;
import java.awt.*;

public class NameSurferGraph extends GCanvas
	implements NameSurferConstants, ComponentListener {
	private HashMap<String, NameSurferEntry> storedValues;
	private GLine baseLine;
	private GLine topLine;
	private HashMap<String, Integer> colorMap;
	private int startColor = -1;
	public NameSurferGraph() {
		addComponentListener(this);
		storedValues = new HashMap<>();
		colorMap = new HashMap<>();
	}
	
	/*
	 * Clears the hashmaps and the graph
	 * precondition: The user presses clear
	 * postcondition: The graph is cleared and the hashmaps are cleared
	 */
	public void clear() {
		removeAll();
		storedValues.clear();
		colorMap.clear();
		startColor = -1;
	}
	
	/*
	 * Adds an iteration to startColor in order to change colors after every time a line is drawn and stores the name of the person and the entry into a hashmap. 
	 * Also, it adds the names and the colors into another hashmap
	 */
	public void addEntry(NameSurferEntry entry) {
			startColor++;
			String name = entry.getName();
			storedValues.put(name, entry);
			colorMap.put(name, startColor);
		}
	
	
	/*
	 * Adds the new line to the graph
	 * precondition: The graph button was pressed or enter was entered
	 * postcondition: The lines are readded and the new line is inserted
	 */
	public void update() {
		removeAll();
		baseLine = new GLine (0, getHeight() - GRAPH_MARGIN_SIZE, getWidth(), getHeight() - GRAPH_MARGIN_SIZE);
		topLine = new GLine (0, GRAPH_MARGIN_SIZE, getWidth(), GRAPH_MARGIN_SIZE);
		add(baseLine);
		add(topLine);
		createVertLines();
		createJLabels();
		addLine();
	}
	/*
	 * Creates the vertical lines in between each decade in the graph as well as the upper and lower horizontal lines
	 * precondition: The canvas is being updated or the program is starting
	 * postcondition: The lines are drawn
	 */
	private void createVertLines(){
		int iterations = 0;
		for (int i=0; i < NUM_DECADES; i++){
			GLine vertLine = new GLine (iterations, topLine.getY(), iterations, baseLine.getY());
			add(vertLine);
			iterations+=getWidth()/NUM_DECADES;
		}
	}
	/*
	 * The JLabels are being created
	 * precondition: The canvas is being updated or the program just started
	 * postcondition: There are JLabels on the canvas
	 */
	private void createJLabels(){
		int decade = 1900;
		int iterations = 0;
		for (int i=0; i < NUM_DECADES; i++){
			GLabel printedText = new GLabel("" + decade, iterations, getHeight()- DECADE_LABEL_MARGIN_SIZE);
			add(printedText);
			decade+=10;
			iterations+=getWidth()/NUM_DECADES;
		}
	}
	/*
	 * Draws a line throughout the decades between the ranks at each decade for the name of the person that was inputted by the user
	 * It also changes color everytime a new line is drawn in and cycles between a set of 4 colors
	 * precondition: the graph button or enter was pressed
	 * postcondition: There's a new line between the ranks at each decade for a inputted person
	 */
	private void addLine(){
		for (String key: storedValues.keySet()){
		NameSurferEntry entry = storedValues.get(key);
		int entryRank = entry.getRank(0);
		double percentageOfGraph = entryRank/1000.0;
		double graphHeight = baseLine.getY() - topLine.getY();
		int firstGraphPlacement = (int) (percentageOfGraph * graphHeight);
		if(entryRank == 0) {
			firstGraphPlacement = (int) getHeight() - GRAPH_MARGIN_SIZE;
			String graphLabel =  entry.getName() + "*";
			GLabel firstLabel = new GLabel (graphLabel, 0, firstGraphPlacement);
			if(colorMap.get(key)%4 == 0) {
				firstLabel.setColor(Color.BLACK);
			} else if(colorMap.get(key)%4 ==1) {
				firstLabel.setColor(Color.BLUE);
			} else if(colorMap.get(key)%4 ==2) {
				firstLabel.setColor(Color.RED);
			} else if(colorMap.get(key)%4 ==3) {
				firstLabel.setColor(Color.MAGENTA);
			}
			add(firstLabel);
		} else {
			String graphLabel = entry.getName() + " " + entry.getRank(0);
			GLabel firstLabel = new GLabel (graphLabel, 0, firstGraphPlacement + GRAPH_MARGIN_SIZE);
			if(colorMap.get(key)%4 == 0) {
				firstLabel.setColor(Color.BLACK);
			} else if(colorMap.get(key)%4 ==1) {
				firstLabel.setColor(Color.BLUE);
			} else if(colorMap.get(key)%4 ==2) {
				firstLabel.setColor(Color.RED);
			} else if(colorMap.get(key)%4 ==3) {
				firstLabel.setColor(Color.MAGENTA);
			}
			add(firstLabel);
		}
		for (int i = 1; i < NUM_DECADES; i++){
			entryRank = entry.getRank(i);
			int prevRank = entry.getRank(i-1);
			percentageOfGraph = entryRank/1000.0;
			double prevPercent = prevRank/1000.0;
			int graphPlacement = (int) (percentageOfGraph * graphHeight);
			int lastGraphPlacement = (int) (prevPercent * graphHeight); // Makes it so that you can draw a line between the rank of the previous decade and the current one
			if(prevRank == 0) {
				lastGraphPlacement = getHeight() - 2*GRAPH_MARGIN_SIZE;
			}
			int iterations = i*getWidth()/NUM_DECADES;
			int prevIter = (i-1)*getWidth()/NUM_DECADES;
			if(entryRank == 0) {
				graphPlacement = (int) getHeight() - 2*GRAPH_MARGIN_SIZE;
				String graphLabel =  entry.getName() + "*";
				GLabel currentLabel = new GLabel (graphLabel, iterations, graphPlacement + GRAPH_MARGIN_SIZE);
				if(colorMap.get(key)%4 == 0) {
					currentLabel.setColor(Color.BLACK);
				} else if(colorMap.get(key)%4 ==1) {
					currentLabel.setColor(Color.BLUE);
				} else if(colorMap.get(key)%4 ==2) {
					currentLabel.setColor(Color.RED);
				} else if(colorMap.get(key)%4 ==3) {
					currentLabel.setColor(Color.MAGENTA);
				}
				add(currentLabel);
			} else {
				String graphLabel = entry.getName() + entry.getRank(i);
				GLabel currentLabel = new GLabel (graphLabel, iterations, graphPlacement + GRAPH_MARGIN_SIZE);
				if(colorMap.get(key)%4 == 0) {
					currentLabel.setColor(Color.BLACK);
				} else if(colorMap.get(key)%4 ==1) {
					currentLabel.setColor(Color.BLUE);
				} else if(colorMap.get(key)%4 ==2) {
					currentLabel.setColor(Color.RED);
				} else if(colorMap.get(key)%4 ==3) {
					currentLabel.setColor(Color.MAGENTA);
				}
				add(currentLabel);
			}
			GLine nameGraph = new GLine(prevIter ,lastGraphPlacement + GRAPH_MARGIN_SIZE, iterations, graphPlacement + GRAPH_MARGIN_SIZE);
			if(colorMap.get(key)%4 == 0) {
				nameGraph.setColor(Color.BLACK);
			} else if (colorMap.get(key)%4 == 1) {
				nameGraph.setColor(Color.BLUE);
			} else if (colorMap.get(key)%4== 2) {
				nameGraph.setColor(Color.RED);
			} else if (colorMap.get(key)%4 == 3) {
				nameGraph.setColor(Color.MAGENTA);
			}
			add(nameGraph);
		}
	}
}
	
	/* Implementation of the ComponentListener interface for updating when the window is resized */
	public void componentHidden(ComponentEvent e) { }
	public void componentMoved(ComponentEvent e) { }
	public void componentResized(ComponentEvent e) { update(); }
	public void componentShown(ComponentEvent e) { }
}
