package duck.controller;

import javax.swing.JOptionPane;

import duck.model.InternetDuck;
import duck.view.DuckFrame;

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
		InternetDuck duck = (InternetDuck)IOController.readSingleJSON(this, duckURLBase, "random?format=json", "duck");
		System.out.println(duck);
	}
	
	public void handleError(Exception error)
	{
		JOptionPane.showMessageDialog(window, error.getMessage(), "woopsy", JOptionPane.ERROR_MESSAGE);
	}
}
