/*
 * The most famous visual attack, described in :
 * Attacks on Steganographic Systems
 * Andreas Westfeld and Andreas Pfitzmann
 * 
 * Just extract the LSB of each pixel
 * (or LSB of red, green and blue value for RGB images)
 * If LSB = 1 then set pixel value to 255
 * If LSB = 0 then set pixel value to 0
 */

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
