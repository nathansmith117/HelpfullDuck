package duck.view;

import javax.swing.JPanel;
import javax.swing.event.HyperlinkListener;
import javax.swing.event.HyperlinkEvent;
import javax.swing.text.html.HTMLFrameHyperlinkEvent;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.Document;
import javax.swing.SpringLayout;
import javax.swing.JTextField;
import javax.swing.JEditorPane;
import javax.swing.JScrollPane;
import javax.swing.JButton;

import java.awt.GridLayout;

import java.io.IOException;
import java.util.Stack;
import java.lang.NullPointerException;

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
	
	private JPanel menuPanel;
	private JButton refreshButton;
	private JButton backButton;
	private JButton nextButton;
	private JButton frogFindButton;
	private JButton wibyButton;
	
	// Used for handling back and next.
	private Stack<String> backPageStack;
	private Stack<String> nextPageStack;
	
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
		
		this.menuPanel = new JPanel(new GridLayout(1, 0));
		this.refreshButton = new JButton("refresh");
		this.backButton = new JButton("back");
		this.nextButton = new JButton("next");
		this.frogFindButton = new JButton("frog");
		this.wibyButton = new JButton("wiby");
		
		this.backPageStack = new Stack<String>();
		this.nextPageStack = new Stack<String>();
		
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
		
		// Menu panel.
		this.add(menuPanel);
		menuPanel.add(refreshButton);
		menuPanel.add(backButton);
		menuPanel.add(nextButton);
		menuPanel.add(frogFindButton);
		menuPanel.add(wibyButton);
		
		setWebPage("http://frogfind.com");
	}
	
	/**
	 * Sets up the listeners.
	 */
	private void setupListeners()
	{
		addressBar.addActionListener(event -> setWebPage(addressBar.getText()));
		webPane.addHyperlinkListener(event -> hyperLinkAction(event));
		
		refreshButton.addActionListener(event -> reloadWebPage());
		backButton.addActionListener(event -> goBackOnePage());
		nextButton.addActionListener(event -> goForwardOnePage());
		frogFindButton.addActionListener(event -> setWebPage("http://frogfind.com"));
		wibyButton.addActionListener(event -> setWebPage("https://wiby.me"));
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
		
		layout.putConstraint(SpringLayout.NORTH, menuPanel, 0, SpringLayout.SOUTH, addressBar);
		layout.putConstraint(SpringLayout.WEST, menuPanel, 0, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.EAST, menuPanel, 0, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.SOUTH, menuPanel, 30, SpringLayout.SOUTH, addressBar);
		
		layout.putConstraint(SpringLayout.NORTH, webScrollPane, 0, SpringLayout.SOUTH, menuPanel);
		layout.putConstraint(SpringLayout.WEST, webScrollPane, 0, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.EAST, webScrollPane, 0, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.SOUTH, webScrollPane, 0, SpringLayout.SOUTH, this);
	}
	
	private void goBackOnePage()
	{
		if (!backPageStack.empty())
		{
			// Add to forward stack.
			if (webPane.getPage() != null)
			{
				nextPageStack.add(webPane.getPage().toString());
			}
			
			String url = backPageStack.pop();
			setWebPage(url);
		}
	}
	
	private void goForwardOnePage()
	{
		if (!nextPageStack.empty())
		{
			
			// Add to back stack.
			if (webPane.getPage() != null)
			{
				backPageStack.add(webPane.getPage().toString());
			}
			
			String url = nextPageStack.pop();
			setWebPage(url);
		}
	}
	
	/**
	 * Reloads the current web page.
	 */
	private void reloadWebPage()
	{
		try
		{
			// This will force reload the current page.
			Document doc = webPane.getDocument();
			String url = webPane.getPage().toString();
			doc.putProperty(Document.StreamDescriptionProperty, null);
			webPane.setPage(url);
		}
		catch (IOException error)
		{
			app.handleError(error);
		}
		catch (NullPointerException error)
		{
			app.handleError(error);
		}
		
		backPageStack.clear();
		nextPageStack.clear();
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
			addressBar.setText(url);
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
			// Add page to stack.
			if (webPane.getPage() != null)
			{
				backPageStack.add(webPane.getPage().toString());
			}
			
			nextPageStack.clear();
			
			String url = event.getURL().toString();
			setWebPage(url);
		}
	}
}