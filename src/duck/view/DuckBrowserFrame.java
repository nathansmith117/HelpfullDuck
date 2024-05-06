package duck.view;

import javax.swing.JFrame;

import duck.controller.Controller;

public class DuckBrowserFrame extends JFrame
{
	private Controller app;
	private DuckBrowserPanel panel;
	
	public DuckBrowserFrame(Controller app)
	{
		super();
		this.app = app;
		this.panel = new DuckBrowserPanel(this.app);
		
		setupFrame();
	}
	
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
