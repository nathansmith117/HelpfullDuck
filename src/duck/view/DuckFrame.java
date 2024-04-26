package duck.view;

import duck.controller.Controller;
import javax.swing.JFrame;

/**
 * The frame for the main window.
 */
public class DuckFrame extends JFrame
{
	private Controller app;
	private DuckPanel panel;
	
	/**
	 * Creates the panel and calls the setup method.
	 * @param app A reference to the controller.
	 */
	public DuckFrame(Controller app)
	{
		super();
		this.app = app;
		this.panel = new DuckPanel(this.app);
		
		setupFrame();
	}
	
	/**
	 * Handles the setup of the frame.
	 */
	private void setupFrame()
	{
		setContentPane(panel);
		setSize(1027, 768);
		setTitle("Helpfull Duck");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
	}
}
