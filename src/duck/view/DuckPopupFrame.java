package duck.view;

import javax.swing.JFrame;
import duck.controller.Controller;

/**
 * A small popup window with a duck on it.
 */
public class DuckPopupFrame extends JFrame
{
	private Controller app;
	private DuckPopupPanel panel;
	
	/**
	 * Creates the frame.
	 * @param app A reference to the controller.
	 */
	public DuckPopupFrame(Controller app)
	{
		super();
		this.app = app;
		this.panel = new DuckPopupPanel(this.app);
		
		setupFrame();
	}
	
	/**
	 * Sets things like the size of the frame, its panel...
	 */
	private void setupFrame()
	{
		setContentPane(panel);
		setSize(640, 480);
		setTitle("Another duck!");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setVisible(true);
	}

	/**
	 * Overrides the dispose so it can remove itself from the controller when closed.
	 */
	@Override
	public void dispose()
	{
		super.dispose();
		app.removeDuckWindow(this);
	}
}
