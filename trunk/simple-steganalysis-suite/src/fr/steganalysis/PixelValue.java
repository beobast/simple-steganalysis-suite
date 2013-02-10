/*
 * This is an idea I had about neighbour pixels value difference
 * Need to be improved
 */

package fr.steganalysis;
import java.awt.Color;
import java.awt.image.BufferedImage;


public class PixelValue {
	
	
	private static Color pixelValue(int i, int j, BufferedImage image)
	{
		int redPixel, greenPixel, bluePixel;
		int red = 0, green = 0, blue = 0;
		
		redPixel = new Color(image.getRGB(i,j)).getRed();
		greenPixel = new Color(image.getRGB(i,j)).getGreen();
		bluePixel = new Color(image.getRGB(i,j)).getBlue();
		
		for(int m=i-1; m<i+2; m++)
		{
			for(int n=j-1; n<j+2; n++)
			{
				red += Math.abs((new Color(image.getRGB(m, n)).getRed()) - redPixel);
				green += Math.abs((new Color(image.getRGB(m, n)).getGreen()) - greenPixel);
				blue += Math.abs((new Color(image.getRGB(m, n)).getBlue()) - bluePixel);
			}
		}
		
		if(red > 50) red = 255;
		else if(red > 20) red = 150;
		else if(red > 4) red = 80;
		else red = 0;
		
		if(green > 50) green = 255;
		else if(green > 20)  green = 150;
		else if(green > 4) green = 80;
		else green = 0;
		
		if(blue > 50) blue = 255;
		else if(blue > 20) blue = 150;
		else if(blue > 4) blue = 80;
		else blue = 0;
		
		Color result = new Color(red, green, blue);
		return result;		
	}
	
	public static BufferedImage pixelValueAttack(BufferedImage image)
	{
		int width = image.getWidth();
		int height = image.getHeight();

		BufferedImage resultImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		
		for(int i=1; i<width-1; i++)
		{
			for(int j=1; j<height-1; j++)
			{
				resultImage.setRGB(i, j, pixelValue(i,j,image).getRGB());
			}
		}
		
		return resultImage;
	}

}
