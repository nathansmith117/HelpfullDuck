package duck.controller;

import javax.swing.JOptionPane;

import duck.model.InternetDuck;
import duck.view.DuckFrame;

import java.net.URL;
import java.net.MalformedURLException;

public class Controller
{
	private String duckURLBase;
	private DuckFrame window;
	
	public Controller()
	{
		this.duckURLBase = "https://random-d.uk/api/";
		this.window = new DuckFrame(this);
	}
	
	public void start()
	{
	}
	
	public void handleError(Exception error)
	{
		JOptionPane.showMessageDialog(window, error.getMessage(), "woopsy", JOptionPane.ERROR_MESSAGE);
	}
	
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
}
