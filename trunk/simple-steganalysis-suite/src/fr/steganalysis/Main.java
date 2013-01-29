package fr.steganalysis;
import java.awt.Dimension;
import java.awt.EventQueue;
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

import javax.swing.JPanel;

import org.math.plot.Plot2DPanel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JRadioButtonMenuItem;


public class Main {

	private static JFileChooser fileChooser = new JFileChooser(System.getProperty("user.dir"));
	private JFrame frmSteganalysis;
	
	private BufferedImage image;
	private ImagePanel imagePanel;
	private ImagePanel resultImagePanel;
	private Plot2DPanel averageLsbPanel;
	
	private int width = 1024;
	private int height = 768;

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
	
	// ---- implementation of menu functions ----
	
	// This method returns the selected radio button in a button group
	public static JRadioButtonMenuItem getSelection(ButtonGroup group) {
	    for (Enumeration<AbstractButton> e=group.getElements(); e.hasMoreElements(); ) {
	        JRadioButtonMenuItem b = (JRadioButtonMenuItem)e.nextElement();
	        if (b.getModel() == group.getSelection()) {
	            return b;
	        }
	    }
	    return null;
	}
	
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
        frmSteganalysis.pack();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSteganalysis = new JFrame();
		frmSteganalysis.setTitle("Simple Steganalysis Suite");
		frmSteganalysis.setResizable(false);
		frmSteganalysis.setBounds(100, 100, 450, 387);
		frmSteganalysis.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSteganalysis.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frmSteganalysis.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		imagePanel = new ImagePanel();		
		JScrollPane scroller = new JScrollPane(imagePanel);
		scroller.setPreferredSize(new Dimension(width, height));
		tabbedPane.addTab("Image", scroller);
		
		resultImagePanel = new ImagePanel();
		
		JScrollPane scroller2 = new JScrollPane(resultImagePanel);
		scroller2.setPreferredSize(new Dimension(width, height));
		
		tabbedPane.addTab("LSB Enhancement", scroller2);
		
		JPanel resultPanel = new JPanel();
		tabbedPane.addTab("Result", null, resultPanel, null);
		resultPanel.setLayout(new BorderLayout(0, 0));
		
		averageLsbPanel = new Plot2DPanel();
		resultPanel.add(averageLsbPanel);
	
		JMenuBar menuBar = new JMenuBar();
		menuBar.setToolTipText("");
		frmSteganalysis.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmOpenImage = new JMenuItem("Open Image");
		mnFile.add(mntmOpenImage);
		
		JMenu mnAttack = new JMenu("Attack");
		menuBar.add(mnAttack);
		
		JMenu mnVisual = new JMenu("Visual");
		mnAttack.add(mnVisual);
		
		JMenuItem mntmLsbEnhancement = new JMenuItem("LSB enhancement");
		mnVisual.add(mntmLsbEnhancement);
		
		JMenu mnStatistical_1 = new JMenu("Statistical");
		mnAttack.add(mnStatistical_1);
		
		JMenuItem mntmAverageLsb = new JMenuItem("Average LSB");
		mnStatistical_1.add(mntmAverageLsb);
		
		JMenuItem mntmChisquare = new JMenuItem("Chi-Square");
		mnStatistical_1.add(mntmChisquare);
		
		JMenu mnOptions = new JMenu("Options");
		menuBar.add(mnOptions);
		
		JMenu mnStatistical = new JMenu("Statistical");
		mnOptions.add(mnStatistical);
		
		JMenu mnBlockSize = new JMenu("Block size");
		mnStatistical.add(mnBlockSize);
		
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
				
		final ButtonGroup groupBlockSize = new ButtonGroup();
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
		mnStatistical.add(mnOrientation);
		
		JRadioButtonMenuItem rdbtnmntmTopToBottom_1 = new JRadioButtonMenuItem("Top to Bottom");
		mnOrientation.add(rdbtnmntmTopToBottom_1);
		
		JRadioButtonMenuItem rdbtnmntmLeftToRight_1 = new JRadioButtonMenuItem("Left to Right");
		mnOrientation.add(rdbtnmntmLeftToRight_1);
		
		JRadioButtonMenuItem rdbtnmntmBottomToTop_1 = new JRadioButtonMenuItem("Bottom to Top");
		mnOrientation.add(rdbtnmntmBottomToTop_1);
		
		JRadioButtonMenuItem rdbtnmntmRightToLeft_1 = new JRadioButtonMenuItem("Right to Left");
		mnOrientation.add(rdbtnmntmRightToLeft_1);
		
		final ButtonGroup groupOrientation = new ButtonGroup();		
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
		
		mntmAverageLsb.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				int size = Integer.parseInt(getSelection(groupBlockSize).getText());
				int nbBlocks = ((3*image.getWidth()*image.getHeight())/size) - 1;
				double[] x = new double[nbBlocks];
				double[] y = new double[nbBlocks];			
				AverageLsb.averageLsbAttack(image, x, y, size);
				averageLsbPanel.removeAllPlots();
				averageLsbPanel.addScatterPlot("Average LSB", x, y);
				averageLsbPanel.addLegend("EAST");
			}
		});
		
		mntmLsbEnhancement.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				resultImagePanel.setImage(LsbEnhancement.lsbEnhancementAttack(image));
			}
		});
		
		mntmChisquare.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				int size = Integer.parseInt(getSelection(groupBlockSize).getText());
				int nbBlocks = ((3*image.getWidth()*image.getHeight())/size) - 1;
				double[] x = new double[nbBlocks];
				double[] chi = new double[nbBlocks];
				ChiSquare.chiSquareAttack(image, x, chi, size);
				averageLsbPanel.addLinePlot("Chi-Square", x, chi);
				averageLsbPanel.addLegend("EAST");
			}
		});
			
	}

}
