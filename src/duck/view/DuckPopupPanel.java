package duck.view;

import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SpringLayout;
import javax.swing.SwingUtilities;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Color;

import duck.controller.Controller;
import duck.controller.IOController;
import duck.model.DuckWindowState;

import java.net.URL;

/**
 * The panel for the tiny duck popup.
 */
public class DuckPopupPanel extends JPanel
{
	private Controller app;
	
	private SpringLayout layout;
	private JLabel displayLabel;
	
	private URL duckURL;
	
	/**
	 * Creates the panel.
	 * @param app A reference to the controller.
	 */
	public DuckPopupPanel(Controller app)
	{
		super();
		this.app = app;
		
		this.layout = new SpringLayout();
		this.displayLabel = new JLabel("");
		
		this.duckURL = null;
		
		setupPanel();
		setupListeners();
		setupLayout();
	}
	
	/**
	 * Sets up the panel. All it has is a image so not much to worry about.
	 */
	private void setupPanel()
	{
		setBackground(Color.CYAN);
		setLayout(layout);
		
		this.add(displayLabel);
	}
	
	/**
	 * Sets up the listeners which isn't much since its a simple window.
	 */
	private void setupListeners()
	{
		this.addMouseListener(new MouseListener()
		{
			public void mouseClicked(MouseEvent click)
			{
				
			}
			
			public void mousePressed(MouseEvent click)
			{
				if (click.getButton() == MouseEvent.BUTTON3)
				{
					loadRandomDuck();
				}
			}
			
			public void mouseReleased(MouseEvent click)
			{
				
			}
			
			public void mouseEntered(MouseEvent click)
			{
				
			}
			
			public void mouseExited(MouseEvent click)
			{
				
			}
		}
		);
	}
	
	/**
	 * Sets the constraints for objects on screen.
	 */
	private void setupLayout()
	{

		layout.putConstraint(SpringLayout.NORTH, displayLabel, 0, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, displayLabel, 0, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.EAST, displayLabel, 0, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.SOUTH, displayLabel, 0, SpringLayout.SOUTH, this);
	}
	
	/**
	 * Loads a random duck into the display label. Its public so it can be controlled by rest of the program.
	 */
	public void loadRandomDuck()
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
		ImageIcon duckIcon = IOController.readImageIconFromURL(app, duckURL, 480);
		displayLabel.setIcon(duckIcon);
	}
	
	/**
	 * Get the window state.
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
		if (windowState.getDuckURL() != null)
		{
			loadDuck(windowState.getDuckURL());
		}
		
		// Set window location.
		JFrame window = (JFrame)SwingUtilities.windowForComponent(this);
		window.setLocation(windowState.getLocation());
	}
}
