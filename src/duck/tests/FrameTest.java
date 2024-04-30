package duck.tests;

import duck.controller.Controller;
import duck.view.DuckPanel;
import duck.view.DuckFrame;
import javax.swing.*;

import java.awt.Component;
import java.lang.reflect.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FrameTest
{
	private Controller testedController;
	private DuckFrame testedFrame;

	@BeforeEach
	void setUp() throws Exception
	{
		this.testedController = new Controller();
		this.testedFrame = new DuckFrame(testedController);
	}

	@AfterEach
	void tearDown() throws Exception
	{
		this.testedController = null;
		this.testedFrame = null;
	}

	@Test
	void testDuckFrame()
	{
		assertTrue(testedFrame instanceof JFrame, "DuckFrame needs to extend JFrame");
		Method [] methods = testedFrame.getClass().getDeclaredMethods();
		assertTrue(methods.length == 1, "You need 1 method in the DuckFrame");
		assertTrue(methods[0].getName().equals("setupFrame"), "The DuckFrame needs to have a method named setupFrame");
		assertTrue(!testedFrame.isResizable(), "Your DuckFrame should NOT be resizable!");
		assertTrue(testedFrame.getTitle().equals("Helpfull Duck"), "The title needs to be \"Helpfull Duck\"");
		assertTrue(testedFrame.getContentPane() instanceof DuckPanel, "Your DuckFrame needs to have a DuckPanel inside");
	}
}