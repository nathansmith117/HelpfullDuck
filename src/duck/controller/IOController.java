package duck.controller;

import java.io.IOException;
import java.net.URL;
import javax.swing.ImageIcon;
import com.fasterxml.jackson.databind.ObjectMapper;
import duck.model.InternetDuck;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.Image;

/**
 * A controller for holding IO with the file system and web.
 */
public class IOController
{
	/**
	 * Gets a object from a piece of json data on the internet.
	 * @param app The controller of the app.
	 * @param urlBase The base url.
	 * @param appended Any other options used for the url.
	 * @param type The type of the object. Currently only has "duck".
	 * @return A java object that you can cast to whatever you need or null.
	 */
	public static Object readSingleJSON(Controller app, String urlBase, String appended, String type)
	{
		ObjectMapper mapper = new ObjectMapper();
		
		try
		{
			if (type.equals("duck"))
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
	
	/**
	 * Gets a ImageIcon from a url of a image on the internet.
	 * @param app The controller of the app.
	 * @param url The url to the image.
	 * @param iconSize The size the icon will be scaled to.
	 * @return A ImageIcon or null.
	 */
	public static ImageIcon readImageIconFromURL(Controller app, URL url, int iconSize)
	{
		ImageIcon imageIcon = null;
		
		try
		{
			BufferedImage image = ImageIO.read(url);
			
			// Scale the icon.
			Image temp = image.getScaledInstance(iconSize, -1, Image.SCALE_SMOOTH);
			BufferedImage resized = new BufferedImage(iconSize, iconSize, BufferedImage.TYPE_INT_RGB);
			Graphics2D imageGraphics = resized.createGraphics();
			imageGraphics.drawImage(temp, 0, 0, null);
			imageGraphics.dispose();
			
			image = resized;
			
			imageIcon = new ImageIcon(image);
		}
		catch (IOException error)
		{
			app.handleError(error);
		}
		
		return imageIcon;
	}
}
