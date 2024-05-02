package duck.model;

import java.net.URL;
import java.io.Serializable;

/**
 * What duck a window has and other info about it.
 */
public class DuckWindowState implements Serializable
{
	private URL duckURL;
	
	public DuckWindowState(URL duckURL)
	{
		this.duckURL = duckURL;
	}
	
	public URL getDuckURL()
	{
		return this.duckURL;
	}
}
