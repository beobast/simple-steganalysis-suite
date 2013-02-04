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
		
		red *= 8;
		green *= 8;
		blue *= 8;
		
		/*if(red > 600)
		{
			red = 255;
			green = 255;
			blue = 0;
		}
		
		if(red < 40)
		{
			red =0 ;
			green = 0;
			blue = 0;
		}*/
		
		if(red > 255) red = 255;
		if(red < 0) red = 0;
		if(green > 255) green = 255;
		if(green < 0) green = 0;
		if(blue > 255) blue = 255;
		if(blue < 0) blue = 0;
		
		
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
