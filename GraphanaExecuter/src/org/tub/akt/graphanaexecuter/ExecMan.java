/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tub.akt.graphanaexecuter;

import graphana.UserInterface;

/**
 *
 * @author Progga
 */
public class ExecMan {
    
    protected UserInterface userInterface;
	protected int algorithmTime;
	protected boolean algoTimerActive;
	protected boolean cachingOn;
	protected long algorithmTimeout;
	protected long scriptTimeout;
	
	public ExecMan(UserInterface userInterface) {System.out.println("start");
		this.userInterface = userInterface;
		algorithmTime = 0;
		algoTimerActive = false;
		algorithmTimeout = 10000;
		scriptTimeout = Integer.MAX_VALUE;System.out.println("end");
	}
	
	/**
	 * Returns the user interface which handles user input and output.
	 * @return the user interface.
	 */
	public UserInterface getUserInterface()
	{
		return this.userInterface;
	}
	
	/**
	 * Returns the maximum algorithm computation time.
	 * @return the algorithm timeout.
	 */
	public long getAlgorithmTimeout()
	{
		return this.algorithmTimeout;
	}
	
	/**
	 * Sets the maximum computation time for an algorithm. If a computation exceeds this time, then it will be aborted.
	 * @param timeOut the maximum computation time for algorithms.
	 */
	public void setAlgorithmTimeout(long timeOut)
	{
		this.algorithmTimeout = timeOut;
	}
	
	/**
	 * Activates the algorithm timer. The algorithm timer will then increase whenever an algorithm is executed.
	 */
	public void startAlgorithmTimer()
	{
		algoTimerActive = true;
		algorithmTime = 0;
	}

	/**
	 * Returns the current timer value of the algorithm timer. The timer keeps running afterwards.
	 * @return the current algorithm timer value.
	 */
	public int getAlgorithmTime()
	{
		return this.algorithmTime;
	}
	
	/**
	 * Increments the algorithm timer by the given time value.
	 * @param value the value by which the algorithm timer shall be increased.
	 */
	public void incAlgorithmTimer(long value)
	{
		algorithmTime += value;
	}
	
	/**
	 * Tells whether or not the algorithm timer is running.
	 * @return true iff the algorithm timer is running.
	 */
	public boolean isAlgoTimerActive()
	{
		return this.algoTimerActive;
	}
	
	/**
	 * Activates or deactivates the algorithm timer without setting it to zero.
	 * @param active if true, the algorithm timer is activated
	 */
	public void setAlgoTimerActive(boolean active)
	{
		this.algoTimerActive = active;
	}
	
	/**
	 * Tells whether or not caching of algorithm results is activated.
	 * @return true if and only if algorithm caching is activated.
	 */
	public boolean isCachingOn()
	{
		return this.cachingOn;
	}
	
	/**
	 * Enables or disables caching of algorithm results.
	 * @param cachingOn Sets caching on or off, respectively.
	 */
	public void setCachingOn(boolean cachingOn)
	{
		this.cachingOn = cachingOn;
	}
    
}
