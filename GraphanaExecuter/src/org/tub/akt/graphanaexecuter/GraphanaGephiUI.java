/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tub.akt.graphanaexecuter;

import graphana.UserInterface;
import graphana.graphs.GraphLibrary;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import view.VisualizingUserInterface;
import visualizations.stdlayout.ArrangeException;

/**
 *
 * @author Progga
 */
public class GraphanaGephiUI extends VisualizingUserInterface {

/**
	 * Prints the given message in the console.
	 * @param onlyConsole Determines, whether the output shall always be only in the console and never additional in a file.
	 */
	@Override
	public void derivedUserOutput(String message)
	{
		System.out.print(message);
	}
	
	/**
	 * Awaits a console user input and returns it.
	 */
	@Override
	public String userInput()
	{
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                return reader.readLine();
    	} catch (IOException e) {
            return "";
        }
	}

    @Override
    public void mainLoop() {
        
    }

    @Override
    public void startInitialization() {
        
    }

    @Override
    public void initializeMessage() {
       
    }

    @Override
    public void signalUserInput(String string) {
      
    }
    
    
    
}
