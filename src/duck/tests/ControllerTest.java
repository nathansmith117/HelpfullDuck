package duck.tests;

import duck.controller.Controller;

import java.lang.reflect.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ControllerTest
{
	private Controller testedController;

	@BeforeEach
	void setUp() throws Exception
	{
		this.testedController = new Controller();
	}

	@AfterEach
	void tearDown() throws Exception
	{
		this.testedController  = null;
	}

	@Test
	void testDataMembers()
	{
		Field [] fields = testedController.getClass().getDeclaredFields();
		assertTrue(fields.length >= 3, "You need at least 3 data members in your Controller");
		for (Field field : fields)
		{
			assertTrue(Modifier.isPrivate(field.getModifiers()), "All data members must be private!");
		}
	}

	@Test
	void testControllerMethods()
	{
		Method [] methods = testedController.getClass().getDeclaredMethods();
		assertTrue(methods.length >= 5, "You need at least 5 methods in the controller");
	}


	@Test
	void testStartMethod()
	{
		Method [] methods = testedController.getClass().getDeclaredMethods();

		boolean hasStart = false;

		for (Method method : methods)
		{
			Type[] types = method.getGenericParameterTypes();

			if (method.getName().equals("start"))
			{
				hasStart = true;

				assertTrue(Modifier.isPublic(method.getModifiers()), "The " + method.getName()+ " method must be public");
				assertTrue(types.length == 0, "Start has no parameters!");
				assertTrue(method.getReturnType().equals(Void.TYPE), "The " + method.getName()+ " method needs to be a void method!");
			}
		}

		assertTrue(hasStart, "The Controller needs a start method");

	}
}