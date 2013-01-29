package fr.steganalysis;
import java.awt.Color;
import java.awt.image.BufferedImage;


public class LsbEnhancement {
	
	
	private static Color lsbEnhancement(Color color)
	{
		int red, green, blue;
		
		red = color.getRed();
		green = color.getGreen();
		blue = color.getBlue();
		
		/*int total = (red & 0x03) + (green & 0x03) + (blue & 0x03);
		if (total > 6.6)
		{
			red = 255;
			green = 0;
			blue = 0;
		}
		else if(total > 3.3)
		{
			red = 0;
			green = 255;
			blue = 0;
		}
		else
		{
			red = 0;
			green = 0;
			blue = 255;
		}*/
		
		if((red & 0x01) == 1)
		{
			red = 255;
		}
		else red = 0;
		if((green & 0x01) == 1)
		{
			green = 255;
		}
		else green = 0;
		if((blue & 0x01) == 1)
		{
			blue = 255;
		}
		else blue = 0;
		
		Color result = new Color(red, green, blue);
		return result;
		
	}
	
	public static BufferedImage lsbEnhancementAttack(BufferedImage image)
	{
		int width = image.getWidth();
		int height = image.getHeight();
		int pixel;
		Color color;
		BufferedImage resultImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		
		for(int i=0; i<width; i++)
		{
			for(int j=0; j<height; j++)
			{
				pixel = image.getRGB(i, j);
				color = new Color(pixel, true);
				resultImage.setRGB(i, j, lsbEnhancement(color).getRGB());
			}
		}
		
		return resultImage;
	}

}
