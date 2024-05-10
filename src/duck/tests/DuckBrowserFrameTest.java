package duck.tests;

import duck.controller.Controller;
import duck.view.DuckBrowserPanel;
import duck.view.DuckBrowserFrame;
import javax.swing.*;

import java.awt.Component;
import java.lang.reflect.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * A simple test for the popup frame.
 */
public class DuckBrowserFrameTest
{
	private Controller testedController;
	private DuckBrowserFrame testedFrame;

	/**
	 * Creates the controller and popup frame.
	 * @throws Exception
	 */
	@BeforeEach
	void setUp() throws Exception
	{
		this.testedController = new Controller();
		this.testedFrame = new DuckBrowserFrame(testedController);
	}

	/**
	 * Destroys the controller and popup frame.
	 * @throws Exception
	 */
	@AfterEach
	void tearDown() throws Exception
	{
		this.testedController = null;
		this.testedFrame = null;
	}

	/**
	 * Tests a bunch of small things about the frame.
	 */
	@Test
	void testDuckFrame()
	{
		assertTrue(testedFrame instanceof JFrame, "DuckBrowserFrame needs to extend JFrame");
		Method [] methods = testedFrame.getClass().getDeclaredMethods();
		assertTrue(methods.length == 1, "You need 1 method in the DuckBrowserFrame");
		assertTrue(testedFrame.isResizable(), "Your DuckBrowserFrame should be resizable!");
		assertTrue(testedFrame.getTitle().equals("Duck Browser"), "The title needs to be \"Duck Browser\"");
		assertTrue(testedFrame.getContentPane() instanceof DuckBrowserPanel, "Your DuckBrowserFrame needs to have a DuckBrowserPanel inside");
		assertTrue(testedFrame.getDefaultCloseOperation() == JFrame.DISPOSE_ON_CLOSE, "The close operation should be dispose on close");
	}
}