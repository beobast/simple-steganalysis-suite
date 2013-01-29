package fr.steganalysis;
import java.awt.Color;
import java.awt.image.BufferedImage;


public class AverageLsb {

	public static void averageLsbAttack(BufferedImage image, double[] x, double[] average, int size)
	{
		int width = image.getWidth();
		int height = image.getHeight();
		int block = 0;
		int nbBytes = 1;
		int lsbRed, lsbGreen, lsbBlue;
		
		for(int i=0; i<average.length; i++)
		{
			average[i] = 0;
			x[i] = i;
		}

		for(int j=0; j<height; j++)
		{
			for(int i=0; i<width; i++)
			{						
				if(block < average.length)
				{				
					lsbRed = (new Color(image.getRGB(i, j)).getRed()) & 0x01;
					average[block] += (double) lsbRed;
					nbBytes++;
					
					if(nbBytes > size)
					{
						average[block] /= (double) size;
						block++;
						nbBytes = 1;
					}
				}		
				
				if(block < average.length)
				{
					lsbGreen = (new Color(image.getRGB(i, j)).getGreen()) & 0x01;
					average[block] += (double) lsbGreen;
					nbBytes++;
					if(nbBytes > size)
					{
						
						average[block] /= (double) size;
						block++;
						nbBytes = 1;
					}
				}

				if(block < average.length)
				{
					lsbBlue = (new Color(image.getRGB(i, j)).getBlue()) & 0x01;
					average[block] += (double) lsbBlue;
					nbBytes++;
					if(nbBytes > size)
					{
						average[block] /= (double) size;
						block++;
						nbBytes = 1;
					}
				}		
			}
		}
	}	
}
