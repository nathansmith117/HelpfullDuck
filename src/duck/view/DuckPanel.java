package duck.view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SpringLayout;

import java.awt.Color;
import java.awt.Image;
import java.awt.GridLayout;

import duck.controller.Controller;

public class DuckPanel extends JPanel
{
	private Controller app;
	
	private SpringLayout layout;
	private JLabel displayLabel;
	private JPanel menuPanel;
	private JButton duckButton;
	
	public DuckPanel(Controller app)
	{
		super();
		this.app = app;
		
		this.layout = new SpringLayout();
		this.displayLabel = new JLabel("Debugging Duck");
		this.menuPanel = new JPanel(new GridLayout(0, 1));
		this.duckButton = new JButton("Duck");
		
		setupPanel();
		setupListeners();
		setupLayout();
	}
	
	private void setupPanel()
	{
		setBackground(Color.CYAN);
		setLayout(layout);
		
		//this.add(displayLabel);
		this.add(menuPanel);
		
		menuPanel.add(duckButton);
	}
	
	private void setupListeners()
	{
		
	}
	
	private void setupLayout()
	{
		layout.putConstraint(SpringLayout.NORTH, menuPanel, 10, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, menuPanel, 10, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.EAST, menuPanel, 100, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.SOUTH, menuPanel, 500, SpringLayout.NORTH, this);
	}
}
