package duck.controller;

import javax.swing.JOptionPane;

import duck.model.InternetDuck;
import duck.model.DuckWindowState;
import duck.view.DuckFrame;
import duck.view.DuckPanel;
import duck.view.DuckPopupFrame;
import duck.view.DuckPopupPanel;

import java.net.URL;
import java.net.MalformedURLException;
import java.util.ArrayList;

/**
 * The controller for the app since it uses MVC design.
 */
public class Controller
{
	private String duckURLBase;
	private DuckFrame window;
	
	// A list of all the duck popup windows.
	private ArrayList<DuckPopupFrame> duckWindows;
	
	/**
	 * Initializes all the data members and opens a window.
	 */
	public Controller()
	{
		this.duckURLBase = "https://random-d.uk/api/";
		this.window = new DuckFrame(this);
		this.duckWindows = new ArrayList<DuckPopupFrame>();
	}
	
	/**
	 * Called after the controller is created.
	 */
	public void start()
	{
	}
	
	/**
	 * Shows an error message in a popup.
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
	
	/**
	 * Saves all the windows to a file.
	 * @param fileName The file to save to.
	 */
	public void saveDuckWindows(String fileName)
	{
		ArrayList<DuckWindowState> windowStates = new ArrayList<DuckWindowState>();
		
		// Add main window.
		windowStates.add(((DuckPanel)window.getContentPane()).toDuckWindowState());
		
		// Add popup windows.
		for (DuckPopupFrame duckWindow : duckWindows)
		{
			windowStates.add(duckWindow.getPanel().toDuckWindowState());
		}
		
		// Save to file.
		IOController.saveWindowStates(this, fileName, windowStates);
	}
	
	public void loadDuckWindows(String fileName)
	{
		ArrayList<DuckWindowState> windowStates = IOController.loadWindowStates(this, fileName);
		
		if (windowStates == null || windowStates.size() < 1)
		{
			JOptionPane.showMessageDialog(window, "Issue loading window states", "woooopsy", JOptionPane.ERROR_MESSAGE);
			return; // Return early.
		}
		
		// Main window.
		((DuckPanel)window.getContentPane()).loadDuckWindowState(windowStates.get(0));
		
		// Other windows.
		duckWindows.clear();
		
		for (int index = 1; index < windowStates.size(); index++)
		{
			DuckPopupFrame popupWindow = new DuckPopupFrame(this);
			popupWindow.getPanel().loadDuckWindowState(windowStates.get(index));
			duckWindows.add(popupWindow);
		}
	}
}
