package duck.view;

import javax.swing.JPanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import duck.controller.Controller;
import duck.controller.IOController;

public class DuckPopupPanel extends JPanel
{
	private Controller app;
	
	public DuckPopupPanel(Controller app)
	{
		super();
		this.app = app;
		
		setupPanel();
		setupListeners();
		setupLayout();
	}
	
	private void setupPanel()
	{
		
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
		
	}
}
