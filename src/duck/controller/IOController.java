package duck.controller;

import java.io.IOException;
import java.net.URL;

import com.fasterxml.jackson.databind.ObjectMapper;

import duck.model.InternetDuck;

public class IOController
{
	public static Object readSingleJSON(Controller app, String urlBase, String appended)
	{
		ObjectMapper mapper = new ObjectMapper();
		
		try
		{
			if (urlBase.contains("random-d"))
			{
				InternetDuck duck = mapper.readValue(new URL(urlBase + appended), InternetDuck.class);
				return duck;
			}
		}
		catch (IOException error)
		{
			app.handleError(error);
		}
		
		return null;
	}
}
