package fr.steganalysis;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;

/**
 * An ImagePanel is a Swing component that can display an BufferedImage.
 * It is constructed as a subclass of JPanel with the added functionality
 * of setting an BufferedImage that will be displayed on the surface of this
 * component.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 1.0
 */

public class ImagePanel extends JComponent
{

	private static final long serialVersionUID = 7173554271767017570L;

	// The current width and height of this panel
    private int width, height;

    // An internal image buffer that is used for painting. For
    // actual display, this image buffer is then copied to screen.
    private BufferedImage panelImage;

    /**
     * Create a new, empty ImagePanel.
     */
    public ImagePanel()
    {
        panelImage = null;
    }

    /**
     * Set the image that this panel should show.
     * 
     * @param image  The image to be displayed.
     */
    public void setImage(BufferedImage image)
    {
        if(image != null) {
            width = image.getWidth();
            height = image.getHeight();
            panelImage = image;
            repaint();
        }
    }
    
    public BufferedImage getImage()
    {
    	return panelImage;
    }
    
    /**
     * Clear the image on this panel.
     */
    public void clearImage()
    {
        Graphics imageGraphics = panelImage.getGraphics();
        imageGraphics.setColor(Color.LIGHT_GRAY);
        imageGraphics.fillRect(0, 0, width, height);
        repaint();
    }
    
    // The following methods are redefinitions of methods
    // inherited from superclasses.
    
    /**
     * Tell the layout manager how big we would like to be.
     * (This method gets called by layout managers for placing
     * the components.)
     * 
     * @return The preferred dimension for this component.
     */
    public Dimension getPreferredSize()
    {
        return new Dimension(width, height);
    }
    
    /**
     * This component needs to be redisplayed. Copy the internal image 
     * to screen. (This method gets called by the Swing screen painter 
     * every time it want this component displayed.)
     * 
     * @param g The graphics context that can be used to draw on this component.
     */
    public void paintComponent(Graphics g)
    {
        Dimension size = getSize();
        g.clearRect(0, 0, size.width, size.height);
        if(panelImage != null) {
            g.drawImage(panelImage, 0, 0, null);
        }
    }
}
