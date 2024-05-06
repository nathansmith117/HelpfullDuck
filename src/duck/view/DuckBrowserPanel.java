package duck.view;

import javax.swing.JPanel;
import javax.swing.event.HyperlinkListener;
import javax.swing.event.HyperlinkEvent;
import javax.swing.text.html.HTMLFrameHyperlinkEvent;
import javax.swing.text.html.HTMLDocument;
import javax.swing.SpringLayout;
import javax.swing.JTextField;
import javax.swing.JEditorPane;
import javax.swing.JScrollPane;

import java.io.IOException;

import duck.controller.Controller;

/**
 * The panel for the simple web browser.
 * Resources I used to get this working:
 * - https://gist.github.com/masnagam/ec6fd335b75bbe87aea7
 * - https://docs.oracle.com/javase/8/docs/api/javax/swing/JEditorPane.html
 */
public class DuckBrowserPanel extends JPanel
{
	private Controller app;
	
	private SpringLayout layout;
	private JTextField addressBar;
	private JScrollPane webScrollPane;
	private JEditorPane webPane;
	
	/**
	 * Creates the duck browser panel.
	 * @param app The controller for the app.
	 */
	public DuckBrowserPanel(Controller app)
	{
		super();
		this.app = app;
		
		this.layout = new SpringLayout();
		this.addressBar = new JTextField();
		this.webScrollPane = new JScrollPane();
		this.webPane = new JEditorPane();
		
		setupPanel();
		setupListeners();
		setupLayout();
	}
	
	/**
	 * Sets up the pane.
	 */
	private void setupPanel()
	{
		setLayout(layout);
		
		// This is set to make the hyperlink listener work.
		webPane.setEditable(false);
		
		// Setup the scroll bar.
		webScrollPane.setViewportView(webPane);
		webScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		webScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		this.add(addressBar);
		this.add(webScrollPane);
		
		setWebPage("http://frogfind.com");
	}
	
	/**
	 * Sets up the listeners.
	 */
	private void setupListeners()
	{
		addressBar.addActionListener(event -> setWebPage(addressBar.getText()));
		webPane.addHyperlinkListener(event -> hyperLinkAction(event));
	}
	
	/**
	 * Sets up the layout.
	 */
	private void setupLayout()
	{
		layout.putConstraint(SpringLayout.NORTH, addressBar, 0, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, addressBar, 0, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.EAST, addressBar, 0, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.SOUTH, addressBar, 20, SpringLayout.NORTH, this);
		
		layout.putConstraint(SpringLayout.NORTH, webScrollPane, 0, SpringLayout.SOUTH, addressBar);
		layout.putConstraint(SpringLayout.WEST, webScrollPane, 0, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.EAST, webScrollPane, 0, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.SOUTH, webScrollPane, 0, SpringLayout.SOUTH, this);
	}
	
	/**
	 * Sets the web page url. It will load in the editor.
	 * @param url The url to load.
	 */
	private void setWebPage(String url)
	{
		try
		{
			webPane.setPage(url);
		}
		catch (IOException error)
		{
			app.handleError(error);
		}
	}
	
	/**
	 * Handles a hyperlink action.
	 * @param event The even to handle.
	 */
	private void hyperLinkAction(HyperlinkEvent event)
	{
		if (event.getEventType() != HyperlinkEvent.EventType.ACTIVATED)
		{
			return;
		}
		
		if (event instanceof HTMLFrameHyperlinkEvent)
		{
			HTMLDocument doc = (HTMLDocument)webPane.getDocument();
			doc.processHTMLFrameHyperlinkEvent((HTMLFrameHyperlinkEvent)event);
		}
		else
		{
			String url = event.getURL().toString();
			addressBar.setText(url);
			setWebPage(url);
		}
	}
}
