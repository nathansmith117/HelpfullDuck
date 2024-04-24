package duck.view;

import duck.controller.Controller;
import javax.swing.JFrame;

public class DuckFrame extends JFrame
{
	private Controller app;
	private DuckPanel panel;
	
	public DuckFrame(Controller app)
	{
		super();
		this.app = app;
		this.panel = new DuckPanel(this.app);
		
		setupFrame();
	}
	
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
