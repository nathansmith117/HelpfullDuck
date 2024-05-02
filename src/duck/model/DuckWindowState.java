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
	
	public DuckWindowState(URL duckURL, Point location)
	{
		this.duckURL = duckURL;
		this.location = location;
	}
	
	public URL getDuckURL()
	{
		return this.duckURL;
	}
	
	public Point getLocation()
	{
		return this.location;
	}
}
