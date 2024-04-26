package duck.view;

import javax.swing.JFrame;
import duck.controller.Controller;

public class DuckPopupFrame extends JFrame
{
	private Controller app;
	private DuckPopupPanel panel;
	
	public DuckPopupFrame(Controller app)
	{
		super();
		this.app = app;
		this.panel = new DuckPopupPanel(this.app);
		
		setupFrame();
	}
	
	private void setupFrame()
	{
		setContentPane(panel);
		setSize(640, 480);
		setTitle("Another duck!");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(true);
		setVisible(true);
	}
}
