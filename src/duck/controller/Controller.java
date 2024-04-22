package duck.controller;

import javax.swing.JOptionPane;

public class Controller
{
	public Controller()
	{
		
	}
	
	public void start()
	{
		
	}
	
	public void handleErrror(Exception error)
	{
		JOptionPane.showMessageDialog(null, error.getMessage(), "woopsy", JOptionPane.ERROR_MESSAGE);
	}
}
