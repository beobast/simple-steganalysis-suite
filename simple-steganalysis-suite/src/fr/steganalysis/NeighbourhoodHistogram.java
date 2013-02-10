/*
 * This class implements neighbourhood histogram attack used to break "Hide" software
 * The details can be found in :
 * 
 * Detecting Low Embedding Rates
 * Andreas Westfeld
 * 
 */

package fr.steganalysis;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.TreeSet;

public class NeighbourhoodHistogram{
	
	private static class Node implements Comparable<Node>{
		int red, green, blue;
		
		public Node(int r, int g, int b)
		{
			red = r;
			green = g;
			blue = b;
		}

		@Override
		public int compareTo(Node n) {
			if(this.red != n.red)
			{
				return this.red-n.red;
			}
			else if(this.green != n.green)
			{
				return this.green-n.green;
			}
			else if(this.blue != n.blue)
			{
				return this.blue-n.blue;
			}
			else return 0;
		}		
	}
	
	public static void neighborHistogramAttack(BufferedImage image, double[] x, double[] y)
	{
		TreeSet<Node> ts = new TreeSet<Node>();
		int count = 0;
		int width = image.getWidth();
		int height = image.getHeight();
		int red, green, blue;
		Node node;
		
		for(int i=0; i<x.length; i++)
		{
			x[i] = i;
			y[i] = 0;
		}
		
		for(int i=0; i<width; i++)
		{
			for(int j=0; j<height; j++)
			{
				red = new Color(image.getRGB(i, j)).getRed();
				green = new Color(image.getRGB(i, j)).getGreen();
				blue = new Color(image.getRGB(i, j)).getBlue();
				
				node = new Node(red, green, blue);
				ts.add(node);
			}
		}	
		
		for(Node n : ts)
		{
			if(ts.contains(new Node(n.red-1, n.green-1, n.blue-1))) count++;
			if(ts.contains(new Node(n.red-1, n.green-1, n.blue))) count++;
			if(ts.contains(new Node(n.red-1, n.green-1, n.blue+1))) count++;
			if(ts.contains(new Node(n.red-1, n.green, n.blue-1))) count++;
			if(ts.contains(new Node(n.red-1, n.green, n.blue))) count++;
			if(ts.contains(new Node(n.red-1, n.green, n.blue+1))) count++;
			if(ts.contains(new Node(n.red-1, n.green+1, n.blue-1))) count++;
			if(ts.contains(new Node(n.red-1, n.green+1, n.blue))) count++;
			if(ts.contains(new Node(n.red-1, n.green+1, n.blue+1))) count++;
			
			if(ts.contains(new Node(n.red, n.green-1, n.blue-1))) count++;
			if(ts.contains(new Node(n.red, n.green-1, n.blue))) count++;
			if(ts.contains(new Node(n.red, n.green-1, n.blue+1))) count++;
			if(ts.contains(new Node(n.red, n.green, n.blue-1))) count++;
			if(ts.contains(new Node(n.red, n.green, n.blue+1))) count++;
			if(ts.contains(new Node(n.red, n.green+1, n.blue-1))) count++;
			if(ts.contains(new Node(n.red, n.green+1, n.blue))) count++;
			if(ts.contains(new Node(n.red, n.green+1, n.blue+1))) count++;
			
			if(ts.contains(new Node(n.red+1, n.green-1, n.blue-1))) count++;
			if(ts.contains(new Node(n.red+1, n.green-1, n.blue))) count++;
			if(ts.contains(new Node(n.red+1, n.green-1, n.blue+1))) count++;
			if(ts.contains(new Node(n.red+1, n.green, n.blue-1))) count++;
			if(ts.contains(new Node(n.red+1, n.green, n.blue))) count++;
			if(ts.contains(new Node(n.red+1, n.green, n.blue+1))) count++;
			if(ts.contains(new Node(n.red+1, n.green+1, n.blue-1))) count++;
			if(ts.contains(new Node(n.red+1, n.green+1, n.blue))) count++;
			if(ts.contains(new Node(n.red+1, n.green+1, n.blue+1))) count++;

			y[count]++;
			count = 0;
		}
	}
}
