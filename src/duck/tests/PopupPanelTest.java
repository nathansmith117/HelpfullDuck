package duck.tests;

import duck.controller.Controller;
import duck.view.DuckPopupPanel;
import javax.swing.*;

import java.awt.*;
import java.lang.reflect.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PopupPanelTest
{
	private Controller testedController;
	private DuckPopupPanel testedPanel;

	@BeforeEach
	void setUp() throws Exception
	{
		this.testedController = new Controller();
		this.testedPanel = new DuckPopupPanel(testedController);
	} 

	@AfterEach
	void tearDown() throws Exception
	{
		this.testedController = null;
		this.testedPanel = null;
	}

	@Test
	void testFrameworkMethodsExist()
	{
		Method [] methods = testedPanel.getClass().getDeclaredMethods();
		assertTrue(methods.length >= 3, "You need at least 3 methods in the DuckPopupPanel");
		
		boolean hasSetupPanel = false;
		boolean hasSetupListeners = false;
		boolean hasSetupLayout = false;
		
		for (Method method : methods)
		{
			if (method.getName().equals("setupPanel"))
			{
				hasSetupPanel = true;
				assertTrue(method.getReturnType().equals(Void.TYPE), "The " + method.getName()+ " method needs to be a void method!");
			}
			else if (method.getName().equals("setupListeners"))
			{
				hasSetupListeners = true;
				assertTrue(method.getReturnType().equals(Void.TYPE), "The " + method.getName()+ " method needs to be a void method!");
				
			}
			else if (method.getName().equals("setupLayout"))
			{
				hasSetupLayout = true;
				assertTrue(method.getReturnType().equals(Void.TYPE), "The " + method.getName()+ " method needs to be a void method!");
			}
		}
		
		assertTrue(hasSetupPanel, "You need a method named setupPanel");
		assertTrue(hasSetupListeners, "You need a method named setupListeners");
		assertTrue(hasSetupLayout, "You need a method named setupLayout");
		
	}
	
	@Test
	void testPanelComponents()
	{
		Field [] fields = testedPanel.getClass().getDeclaredFields();
		assertTrue(fields.length > 2, "You need at least 3 data members in your DuckPanel");
		
		boolean hasCorrectLayout = false;
		
		if (testedPanel.getLayout() instanceof SpringLayout)
		{
			hasCorrectLayout = true;
		}
		
		assertTrue(hasCorrectLayout, "The layout needs to be a SpringLayout");
	}

}