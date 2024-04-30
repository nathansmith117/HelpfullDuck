package duck.tests;

import duck.controller.Controller;
import duck.view.DuckPopupPanel;
import duck.view.DuckPopupFrame;
import javax.swing.*;

import java.awt.Component;
import java.lang.reflect.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PopupFrameTest
{
	private Controller testedController;
	private DuckPopupFrame testedFrame;

	@BeforeEach
	void setUp() throws Exception
	{
		this.testedController = new Controller();
		this.testedFrame = new DuckPopupFrame(testedController);
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
		assertTrue(testedFrame instanceof JFrame, "DuckPopupFrame needs to extend JFrame");
		Method [] methods = testedFrame.getClass().getDeclaredMethods();
		assertTrue(methods.length == 3, "You need 3 methods in the DuckPopupFrame");
		assertTrue(!testedFrame.isResizable(), "Your DuckPopupFrame should NOT be resizable!");
		assertTrue(testedFrame.getTitle().equals("Another duck!"), "The title needs to be \"Helpfull Duck\"");
		assertTrue(testedFrame.getContentPane() instanceof DuckPopupPanel, "Your DuckPopupFrame needs to have a DuckPopupPanel inside");
		assertTrue(testedFrame.getDefaultCloseOperation() == JFrame.DISPOSE_ON_CLOSE, "The close operation should be dispose on close");
	}
}