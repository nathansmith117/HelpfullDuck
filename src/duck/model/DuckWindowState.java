package duck.model;

import java.net.URL;
import java.io.Serializable;

import java.awt.Point;

/**
 * What duck a window has and other info about it.
 */
public class DuckWindowState implements Serializable
{
	private URL duckURL;
	private Point location;
	
	/**
	 * Creates the class and passes info to it that will not be changed later.
	 * @param duckURL A url to a duck.
	 * @param location The window locatoin.
	 */
	public DuckWindowState(URL duckURL, Point location)
	{
		this.duckURL = duckURL;
		this.location = location;
	}
	
	/**
	 * Gets the duck url.
	 * @return A url to the duck.
	 */
	public URL getDuckURL()
	{
		return this.duckURL;
	}
	
	/**
	 * Gets the window location.
	 * @return The window location.
	 */
	public Point getLocation()
	{
		return this.location;
	}
}
