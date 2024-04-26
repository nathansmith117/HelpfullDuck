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

public class DuckPopupPanel extends JPanel
{
	private Controller app;
	
	private SpringLayout layout;
	private JLabel displayLabel;
	
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
	
	private void setupPanel()
	{
		setBackground(Color.CYAN);
		setLayout(layout);
		
		this.add(displayLabel);
	}
	
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
	
	private void setupLayout()
	{

		layout.putConstraint(SpringLayout.NORTH, displayLabel, 0, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, displayLabel, 0, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.EAST, displayLabel, 0, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.SOUTH, displayLabel, 0, SpringLayout.SOUTH, this);
	}
	
	/**
	 * Loads a random duck into the display label.
	 */
	public void loadRandomDuck()
	{
		ImageIcon duckIcon = IOController.readImageIconFromURL(app, app.getRandomDuckURL(), 480);
		displayLabel.setIcon(duckIcon);
	}
}
