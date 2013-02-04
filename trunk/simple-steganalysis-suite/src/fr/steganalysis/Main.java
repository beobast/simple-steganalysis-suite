package fr.steganalysis;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Toolkit;

import javax.swing.JFrame;
import java.awt.BorderLayout;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Enumeration;

import org.math.plot.Plot2DPanel;
import org.math.plot.plotObjects.BaseLabel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSeparator;
import javax.swing.JLabel;


public class Main {

	private static JFileChooser fileChooser = new JFileChooser(System.getProperty("user.dir"));
	private JFrame frmSteganalysis;
	
	private BufferedImage image;
	private ImagePanel imagePanel;
	private ImagePanel resultImagePanel;
	private Plot2DPanel chiSquarePanel;
	private Plot2DPanel neighboursPanel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
					window.frmSteganalysis.setLocation(d.width/2 - window.frmSteganalysis.getWidth()/2, d.height/2 - window.frmSteganalysis.getHeight()/2);
					window.frmSteganalysis.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
	}
	
	/* ---- implementation of menu functions ---- */
	
	/**
	 * This method returns the selected radio button in a button group 
	 */
	public static JRadioButtonMenuItem getSelection(ButtonGroup group) {
	    for (Enumeration<AbstractButton> e=group.getElements(); e.hasMoreElements(); ) {
	        JRadioButtonMenuItem b = (JRadioButtonMenuItem)e.nextElement();
	        if (b.getModel() == group.getSelection()) {
	            return b;
	        }
	    }
	    return null;
	}
	
	/**
	 * Open an image to analysis
	 */
	public void openSourceImage()
	{
        int returnVal = fileChooser.showOpenDialog(frmSteganalysis);

        if(returnVal != JFileChooser.APPROVE_OPTION) {
            return;  // cancelled
        }
        File selectedFile = fileChooser.getSelectedFile();
        image = ImageFileManager.loadImage(selectedFile);

        if(image == null) {   // image file was not a valid image
            JOptionPane.showMessageDialog(frmSteganalysis,
                    "The file was not in a recognized image file format.",
                    "Image Load Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        imagePanel.setImage(image);
        imagePanel.setVisible(true);
        //frmSteganalysis.pack();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		/* Main frame */
		frmSteganalysis = new JFrame();
		frmSteganalysis.setTitle("Simple Steganalysis Suite");		
		frmSteganalysis.setMinimumSize(new Dimension(400,400));
		frmSteganalysis.setExtendedState(Frame.MAXIMIZED_BOTH);
		frmSteganalysis.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSteganalysis.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frmSteganalysis.getContentPane().add(tabbedPane, BorderLayout.CENTER);
	
		/* Panels */
		imagePanel = new ImagePanel();		
		JScrollPane scroller = new JScrollPane(imagePanel);
		tabbedPane.addTab("Image", scroller);
		
		resultImagePanel = new ImagePanel();		
		JScrollPane scroller2 = new JScrollPane(resultImagePanel);
		tabbedPane.addTab("LSB Enhancement", scroller2);
		
		chiSquarePanel = new Plot2DPanel();
		
		neighboursPanel = new Plot2DPanel();
		
		tabbedPane.addTab("Chi-Square", null, chiSquarePanel, null);		
		tabbedPane.addTab("Neighbours", null, neighboursPanel, null);

		/* Menu */
		JMenuBar menuBar = new JMenuBar();
		menuBar.setToolTipText("");
		frmSteganalysis.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmOpenImage = new JMenuItem("Open Image");
		mnFile.add(mntmOpenImage);
		
		JMenu mnAttack = new JMenu("Attack");
		menuBar.add(mnAttack);
		
		JLabel lblVisual = new JLabel("Visual");
		mnAttack.add(lblVisual);
		
		JMenuItem mntmLsbEnhancement = new JMenuItem("LSB enhancement");
		mnAttack.add(mntmLsbEnhancement);
		
		JMenuItem mntmPixelValue = new JMenuItem("Pixel Value");
		mnAttack.add(mntmPixelValue);
		
		JSeparator separator = new JSeparator();
		mnAttack.add(separator);
		
		JLabel lblStatisticalAttacks = new JLabel("Statistical");
		mnAttack.add(lblStatisticalAttacks);
		
		JMenuItem mntmChisquare = new JMenuItem("Chi-Square");
		mnAttack.add(mntmChisquare);
		
		JMenuItem mntmNeighborHistogram = new JMenuItem("Neighbor Histogram");
		mnAttack.add(mntmNeighborHistogram);
		
		JMenu mnOptions = new JMenu("Options");
		menuBar.add(mnOptions);
				
		final ButtonGroup groupBlockSize = new ButtonGroup();
		
		final ButtonGroup groupOrientation = new ButtonGroup();		
		
		JLabel lblChiSquare = new JLabel("Chi-Square");
		mnOptions.add(lblChiSquare);
		
		JMenu mnBlockSize = new JMenu("Block size");
		mnOptions.add(mnBlockSize);
		
		JRadioButtonMenuItem radioButtonMenuItem = new JRadioButtonMenuItem("32");
		mnBlockSize.add(radioButtonMenuItem);				
		JRadioButtonMenuItem radioButtonMenuItem_1 = new JRadioButtonMenuItem("64");
		mnBlockSize.add(radioButtonMenuItem_1);			
		JRadioButtonMenuItem radioButtonMenuItem_2 = new JRadioButtonMenuItem("128");
		mnBlockSize.add(radioButtonMenuItem_2);				
		JRadioButtonMenuItem radioButtonMenuItem_3 = new JRadioButtonMenuItem("256");
		mnBlockSize.add(radioButtonMenuItem_3);				
		JRadioButtonMenuItem radioButtonMenuItem_4 = new JRadioButtonMenuItem("512");
		mnBlockSize.add(radioButtonMenuItem_4);				
		JRadioButtonMenuItem radioButtonMenuItem_5 = new JRadioButtonMenuItem("1024");
		mnBlockSize.add(radioButtonMenuItem_5);
		radioButtonMenuItem_5.setSelected(true);				
		JRadioButtonMenuItem radioButtonMenuItem_6 = new JRadioButtonMenuItem("2048");
		mnBlockSize.add(radioButtonMenuItem_6);				
		JRadioButtonMenuItem radioButtonMenuItem_7 = new JRadioButtonMenuItem("4096");
		mnBlockSize.add(radioButtonMenuItem_7);						
		JRadioButtonMenuItem radioButtonMenuItem_8 = new JRadioButtonMenuItem("8192");
		mnBlockSize.add(radioButtonMenuItem_8);
		groupBlockSize.add(radioButtonMenuItem);
		groupBlockSize.add(radioButtonMenuItem_1);
		groupBlockSize.add(radioButtonMenuItem_2);
		groupBlockSize.add(radioButtonMenuItem_3);
		groupBlockSize.add(radioButtonMenuItem_4);
		groupBlockSize.add(radioButtonMenuItem_5);
		groupBlockSize.add(radioButtonMenuItem_6);
		groupBlockSize.add(radioButtonMenuItem_7);
		groupBlockSize.add(radioButtonMenuItem_8);
				
		JMenu mnOrientation = new JMenu("Orientation");
		mnOptions.add(mnOrientation);
		
		JRadioButtonMenuItem rdbtnmntmTopToBottom_1 = new JRadioButtonMenuItem("Top to Bottom");
		rdbtnmntmTopToBottom_1.setSelected(true);
		mnOrientation.add(rdbtnmntmTopToBottom_1);	
		JRadioButtonMenuItem rdbtnmntmLeftToRight_1 = new JRadioButtonMenuItem("Left to Right");
		mnOrientation.add(rdbtnmntmLeftToRight_1);		
		JRadioButtonMenuItem rdbtnmntmBottomToTop_1 = new JRadioButtonMenuItem("Bottom to Top");
		mnOrientation.add(rdbtnmntmBottomToTop_1);		
		JRadioButtonMenuItem rdbtnmntmRightToLeft_1 = new JRadioButtonMenuItem("Right to Left");
		mnOrientation.add(rdbtnmntmRightToLeft_1);
		groupOrientation.add(rdbtnmntmTopToBottom_1);
		groupOrientation.add(rdbtnmntmLeftToRight_1);
		groupOrientation.add(rdbtnmntmBottomToTop_1);
		groupOrientation.add(rdbtnmntmRightToLeft_1);
		
		/* Action performed on menu items */
		
		mntmOpenImage.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				openSourceImage();
			}
		});
		
		
		mntmLsbEnhancement.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				resultImagePanel.setImage(LsbEnhancement.lsbEnhancementAttack(image));
			}
		});
		
		mntmPixelValue.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				resultImagePanel.setImage(PixelValue.pixelValueAttack(image));
			}
		});
		
		mntmChisquare.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				String orientation = getSelection(groupOrientation).getText();
				int size = Integer.parseInt(getSelection(groupBlockSize).getText());
				int nbBlocks = ((3*image.getWidth()*image.getHeight())/size) - 1;
				double[] x = new double[nbBlocks];
				double[] chi = new double[nbBlocks];
				double[] averageLSB = new double[nbBlocks];
				
				if(orientation.equals("Top to Bottom"))
				{
					AverageLsb.averageLsbAttackTopToBottom(image, x, averageLSB, size);
					ChiSquare.chiSquareAttackTopToBottom(image, x, chi, size);
				}
				else if(orientation.equals("Left to Right"))
				{
					AverageLsb.averageLsbAttackLeftToRight(image, x, averageLSB, size);
					ChiSquare.chiSquareAttackLeftToRight(image, x, chi, size);
				}
				else if(orientation.equals("Bottom to Top")) 
				{
					AverageLsb.averageLsbAttackBottomToTop(image, x, averageLSB, size);
					ChiSquare.chiSquareAttackBottomToTop(image, x, chi, size);
				}
				else 
				{
					AverageLsb.averageLsbAttackRightToLeft(image, x, averageLSB, size);
					ChiSquare.chiSquareAttackRightToLeft(image, x, chi, size);
				}
				
				chiSquarePanel.removeAllPlots();
				chiSquarePanel.addScatterPlot("Average LSB", x, averageLSB);
				BaseLabel title = new BaseLabel("Chi-Square and Average LSB",null, 0.5, 1.1);
				title.setFont(new Font("Courier", Font.BOLD, 20));
				chiSquarePanel.addPlotable(title);
				chiSquarePanel.addLinePlot("Chi-Square", x, chi);
				chiSquarePanel.addLegend("EAST");
				chiSquarePanel.setAxisLabel(0, size+"-byte data block");
				chiSquarePanel.setAxisLabel(1, "Average LSB value (blue)\np-value of Chi-Square test (red)");
			}
		});
		
		mntmNeighborHistogram.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				double[] x = new double[27];
				double[] y = new double[27];
				NeighborHistogram.neighborHistogramAttack(image, x, y);
				neighboursPanel.removeAllPlots();
				neighboursPanel.addBarPlot("Neighbor", x, y);
				neighboursPanel.addLegend("SOUTH");
				neighboursPanel.setAxisLabel(0, "Neighbours");
				neighboursPanel.setAxisLabel(1, "Frequency");
				BaseLabel title = new BaseLabel("Neighbours Histogram",null, 0.5, 1.1);
				title.setFont(new Font("Courier", Font.BOLD, 20));
				neighboursPanel.addPlotable(title);	
			}
		});
			
	}

}
