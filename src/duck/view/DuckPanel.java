package duck.view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SpringLayout;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

import java.awt.Color;
import java.awt.Image;
import java.awt.GridLayout;
import java.awt.MouseInfo;
import java.awt.Point;

import duck.model.DuckWindowState;
import duck.controller.Controller;
import duck.controller.IOController;
import javax.swing.SwingUtilities;

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
	private JButton saveButton;
	private JButton loadButton;
	
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
		this.saveButton = new JButton("Save");
		this.loadButton = new JButton("Load");
		
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
		menuPanel.add(saveButton);
		menuPanel.add(loadButton);
	}
	
	/**
	 * Sets up all the listeners for buttons and other things.
	 */
	private void setupListeners()
	{
		duckButton.addActionListener(click -> loadRandomDuck());
		newWindowButton.addActionListener(click -> openDuckWindow());
		saveButton.addActionListener(click -> saveWindows());
		loadButton.addActionListener(click -> loadWindows());
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
	
	private void saveWindows()
	{
		JFileChooser fileChooser = new JFileChooser(".");
		
		int result = fileChooser.showSaveDialog(this);
		
		if (result == JFileChooser.APPROVE_OPTION)
		{
			String filePath = fileChooser.getSelectedFile().getPath();
			app.saveDuckWindows(filePath);
		}
	}
	
	private void loadWindows()
	{
		JFileChooser fileChooser = new JFileChooser(".");
		
		int result = fileChooser.showOpenDialog(this);
		
		if (result == JFileChooser.APPROVE_OPTION)
		{
			String filePath = fileChooser.getSelectedFile().getPath();
			app.loadDuckWindows(filePath);
		}
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
	public void loadDuck(URL duckURL)
	{
		this.duckURL = duckURL;
		ImageIcon duckIcon = IOController.readImageIconFromURL(app, duckURL, 640);
		displayLabel.setIcon(duckIcon);
	}
	
	/**
	 * Gets the window state for saving.
	 * @return The window state.
	 */
	public DuckWindowState toDuckWindowState()
	{
		JFrame window = (JFrame)SwingUtilities.windowForComponent(this);
		DuckWindowState windowState = new DuckWindowState(duckURL, window.getLocation());
		
		return windowState;
	}
	
	/**
	 * Sets things about the window to the information in the window state.
	 * @param windowState The state to load.
	 */
	public void loadDuckWindowState(DuckWindowState windowState)
	{
		duckURL = null;
		displayLabel.setIcon(null);
		
		if (windowState.getDuckURL() != null)
		{
			loadDuck(windowState.getDuckURL());
		}
		
		// Set window location.
		JFrame window = (JFrame)SwingUtilities.windowForComponent(this);
		window.setLocation(windowState.getLocation());
	}
}
