package duck.view;

import javax.swing.JFrame;

import duck.controller.Controller;

/**
 * The frame for the duck browser. Its a very simple web browser.
 */
public class DuckBrowserFrame extends JFrame
{
	private Controller app;
	private DuckBrowserPanel panel;
	
	/**
	 * Creates the duck browser frame.
	 * @param app The controller for the app.
	 */
	public DuckBrowserFrame(Controller app)
	{
		super();
		this.app = app;
		this.panel = new DuckBrowserPanel(this.app);
		
		setupFrame();
	}
	
	/**
	 * Sets up the frame.
	 */
	private void setupFrame()
	{
		setContentPane(panel);
		setSize(1027, 768);
		setTitle("Duck Browser");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setVisible(true);
	}
}
