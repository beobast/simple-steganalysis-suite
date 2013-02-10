/*
 * Simple pixel difference histogram
 */

package fr.steganalysis;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class PixelDifferenceHistogram {
	
	public static void histogram(BufferedImage image, double[] x, double[] y)
	{
		int width = image.getWidth();
		int height = image.getHeight();
		int red, green, blue;
		int red2, green2, blue2;
		int redDiff, greenDiff, blueDiff;
		
		for(int i=0; i<x.length; i++)
		{
			x[i] = i-255;
		}
		
		for(int j=0; j<height; j++)
		{
			for(int i=0; i<width; i+=2)
			{
				if( (i+1) < width)
				{
					red = new Color(image.getRGB(i,j)).getRed();
					green = new Color(image.getRGB(i,j)).getGreen();
					blue = new Color(image.getRGB(i,j)).getBlue();
					
					red2 = new Color(image.getRGB(i+1,j)).getRed();
					green2 = new Color(image.getRGB(i+1,j)).getGreen();
					blue2 = new Color(image.getRGB(i+1,j)).getBlue();
					
					redDiff = red - red2;
					greenDiff = green - green2;
					blueDiff = blue - blue2;
					
					y[redDiff + 255]++;
					y[greenDiff + 255]++;
					y[blueDiff + 255]++;
				}
			}
		}
	}

}
