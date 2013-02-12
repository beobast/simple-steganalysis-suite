/*
 * Simple pixel difference histogram
 * 
 * Denote the intensity value of the image I at the position (i,j) as I(i,j), 
 * and the difference image is defined as D(i,j) = I(i,j) - I(i,j+1). 
 * The difference image histogram is defined as the histogram of the difference image D. 
 * It is generally believed that the difference image follows a generalized Gaussian distribution.
 */

package fr.steganalysis;

import java.awt.Color;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

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
