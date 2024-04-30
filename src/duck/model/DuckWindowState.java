package duck.model;

import java.net.URL;

/**
 * What duck a window has and other info about it.
 */
public class DuckWindowState
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
