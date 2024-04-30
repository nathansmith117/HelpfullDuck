package duck.view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SpringLayout;
import javax.swing.ImageIcon;

import java.awt.Color;
import java.awt.Image;
import java.awt.GridLayout;
import java.awt.MouseInfo;
import java.awt.Point;

import duck.controller.Controller;
import duck.controller.IOController;

import java.net.URL;

/**
 * The panel for the main window.
 */
public class DuckPanel extends JPanel
{
	private Controller app;
	
	private SpringLayout layout;
	private JLabel displayLabel;
	
	private JPanel menuPanel;
	private JButton duckButton;
	private JButton newWindowButton;
	
	private URL duckURL;
	
	/**
	 * Creates the panel and all the widgets.
	 * @param app A reference to the controller.
	 */
	public DuckPanel(Controller app)
	{
		super();
		this.app = app;
		
		this.layout = new SpringLayout();
		this.displayLabel = new JLabel("Debugging Duck");
		this.menuPanel = new JPanel(new GridLayout(0, 1));
		this.duckButton = new JButton("Duck");
		this.newWindowButton = new JButton("New window");
		
		this.duckURL = null;
		
		setupPanel();
		setupListeners();
		setupLayout();
	}
	
	/**
	 * Handles the setup of the panel to make the constructor smaller.
	 */
	private void setupPanel()
	{
		setBackground(Color.CYAN);
		setLayout(layout);
		
		this.add(displayLabel);
		this.add(menuPanel);
		
		menuPanel.add(duckButton);
		menuPanel.add(newWindowButton);
	}
	
	/**
	 * Sets up all the listeners for buttons and other things.
	 */
	private void setupListeners()
	{
		duckButton.addActionListener(click -> loadRandomDuck());
		newWindowButton.addActionListener(click -> openDuckWindow());
	}
	
	/**
	 * Sets the constraints for widgets and panels.
	 */
	private void setupLayout()
	{
		layout.putConstraint(SpringLayout.NORTH, menuPanel, 10, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, menuPanel, 10, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.EAST, menuPanel, 100, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.SOUTH, menuPanel, 500, SpringLayout.NORTH, this);
		
		layout.putConstraint(SpringLayout.NORTH, displayLabel, 10, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, displayLabel, 10, SpringLayout.EAST, menuPanel);
		layout.putConstraint(SpringLayout.EAST, displayLabel, 10, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.SOUTH, displayLabel, 10, SpringLayout.SOUTH, this);
	}
	
	/**
	 * Opens a popup duck window and adds it to the controller.
	 */
	private void openDuckWindow()
	{
		DuckPopupFrame duckWindow = new DuckPopupFrame(app);
		
		// Moves the window to the cursor position.
		Point mousePoint = MouseInfo.getPointerInfo().getLocation();
		duckWindow.setLocation(mousePoint.x, mousePoint.y);
		
		// Loads a duck on open.
		duckWindow.getPanel().loadRandomDuck();
		
		app.addDuckWindow(duckWindow);
	}
	
	/**
	 * Loads a random duck into the display label.
	 */
	private void loadRandomDuck()
	{
		duckURL = app.getRandomDuckURL();
		ImageIcon duckIcon = IOController.readImageIconFromURL(app, duckURL, 640);
		displayLabel.setIcon(duckIcon);
	}
	
	/**
	 * Loads a duck on screen from a url.
	 * @param duckURL The url to the duck image.
	 */
	private void loadDuck(URL duckURL)
	{
		this.duckURL = duckURL;
		ImageIcon duckIcon = IOController.readImageIconFromURL(app, duckURL, 640);
		displayLabel.setIcon(duckIcon);
	}
	
	/**
	 * Gets the url of the current duck.
	 * @return The url or null.
	 */
	private URL getDuckURL()
	{
		return this.duckURL;
	}
}
