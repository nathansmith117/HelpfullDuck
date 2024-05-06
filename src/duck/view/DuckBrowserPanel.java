package duck.view;

import javax.swing.JPanel;
import javax.swing.event.HyperlinkListener;
import javax.swing.event.HyperlinkEvent;
import javax.swing.SpringLayout;
import javax.swing.JTextField;
import javax.swing.JEditorPane;

import java.awt.GridBagLayout;

import duck.controller.Controller;

public class DuckBrowserPanel extends JPanel
{
	private Controller app;
	private SpringLayout layout;
	private JTextField addressBar;
	private JEditorPane webPane;
	
	public DuckBrowserPanel(Controller app)
	{
		super();
		this.app = app;
		
		this.layout = new SpringLayout();
		this.addressBar = new JTextField();
		this.webPane = new JEditorPane();
		
		setupPanel();
		setupListeners();
		setupLayout();
	}
	
	private void setupPanel()
	{
		setLayout(layout);
		
		// This is set to make the hyperlink listener work.
		webPane.setEditable(false);
		
		this.add(addressBar);
		this.add(webPane);
	}
	
	private void setupListeners()
	{
		
	}
	
	private void setupLayout()
	{
		layout.putConstraint(SpringLayout.NORTH, addressBar, 0, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, addressBar, 0, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.EAST, addressBar, 0, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.SOUTH, addressBar, 20, SpringLayout.NORTH, this);
		
		layout.putConstraint(SpringLayout.NORTH, webPane, 0, SpringLayout.SOUTH, addressBar);
		layout.putConstraint(SpringLayout.WEST, webPane, 0, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.EAST, webPane, 0, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.SOUTH, webPane, 0, SpringLayout.SOUTH, this);
	}
}
