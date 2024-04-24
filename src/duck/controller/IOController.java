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

public class IOController
{
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
	
	public static ImageIcon readImageIconFromUrl(Controller app, URL url)
	{
		ImageIcon imageIcon = null;
		
		try
		{
			BufferedImage image = ImageIO.read(url);
			
			if (image.getHeight() > 500 || image.getWidth() > 500)
			{
				Image temp = image.getScaledInstance(500, -1, Image.SCALE_SMOOTH);
				BufferedImage resized = new BufferedImage(500, 500, BufferedImage.TYPE_INT_RGB);
				Graphics2D imageGraphics = resized.createGraphics();
				imageGraphics.drawImage(temp, 0, 0, null);
				imageGraphics.dispose();
				
				image = resized;
			}
			
			imageIcon = new ImageIcon(image);
		}
		catch (IOException error)
		{
			app.handleError(error);
		}
		
		return imageIcon;
	}
}
