package duck.controller;

import javax.swing.JOptionPane;

import duck.model.InternetDuck;
import duck.view.DuckFrame;
import duck.view.DuckPopupFrame;

import java.net.URL;
import java.net.MalformedURLException;
import java.util.ArrayList;

public class Controller
{
	private String duckURLBase;
	private DuckFrame window;
	
	// A list of all the duck popup windows.
	private ArrayList<DuckPopupFrame> duckWindows;
	
	public Controller()
	{
		this.duckURLBase = "https://random-d.uk/api/";
		this.window = new DuckFrame(this);
		this.duckWindows = new ArrayList<DuckPopupFrame>();
	}
	
	public void start()
	{
	}
	
	/**
	 * Shows a error message in a popup.
	 * @param error The error that will be shown in the popup.
	 */
	public void handleError(Exception error)
	{
		JOptionPane.showMessageDialog(window, error.getMessage(), "woopsy", JOptionPane.ERROR_MESSAGE);
	}
	
	/**
	 * Gets a random url to a duck image.
	 * @return The url.
	 */
	public URL getRandomDuckURL()
	{
		InternetDuck duck = (InternetDuck)IOController.readSingleJSON(this, duckURLBase, "random?format=json", "duck");
		URL duckURL = null;
		
		try
		{
			duckURL = new URL(duck.url());
		}
		catch (MalformedURLException error)
		{
			handleError(error);
		}
		
		return duckURL;
	}
	
	/**
	 * Adds a window to the list of duck windows.
	 * @param duckWindow
	 */
	public void addDuckWindow(DuckPopupFrame duckWindow)
	{
		duckWindows.add(duckWindow);
	}
	
	/**
	 * Removes the duckWindow from duckWindows if its in there.
	 * @param duckWindow The window to remove.
	 */
	public void removeDuckWindow(DuckPopupFrame duckWindow)
	{
		duckWindows.remove(duckWindow);
	}
}
