package duck.view;

import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SpringLayout;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Color;

import duck.controller.Controller;
import duck.controller.IOController;

/**
 * The panel for the tiny duck popup.
 */
public class DuckPopupPanel extends JPanel
{
	private Controller app;
	
	private SpringLayout layout;
	private JLabel displayLabel;
	
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
		ImageIcon duckIcon = IOController.readImageIconFromURL(app, app.getRandomDuckURL(), 480);
		displayLabel.setIcon(duckIcon);
	}
}
