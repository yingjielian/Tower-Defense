package game;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

/**
 * This application is going to draw a moving staff along with the 
 * visible path on the screen. (Not final goal yet, will put more 
 * in this class).
 * 
 * @author Peter Jensen and <<Yingjie Lian & Nan Ying>>
 * @version 04/16/2017
 * @class CS-1410
 */
public class TowerDefense extends JPanel implements Runnable, ActionListener,
															  MouseListener,
															  MouseMotionListener
{
	// This constant avoids an obnoxious warning, but it is totally unused by us.
	//   It would only be relevant if we were using object serialization.
	private static final long serialVersionUID = 42L;
	
	// Create a variable that can hold one GameState object.
	GameState game;
	JButton start;
	
	/**
	 * The application entry point.
	 * 
	 * @param args unused
	 */
	public static void main (String[] args)
	{
		// Main runs in the 'main' execution thread, and the GUI
		//   needs to be built by the GUI execution thread.
		//   Ask the GUI thread to run our 'run' method (at some
		//   later time).

		SwingUtilities.invokeLater(new TowerDefense());

		// Done.  Let the main thread of execution finish.  All the
		//   remaining work will be done by the GUI thread.
	}

	/**
	 * Builds the GUI for this application and the other initialization code
	 * such as loading stuff, setting up the timer, listeners, etc.
	 */
	public void run ()
	{
		
		game = new GameState(); // Call the GameState class

		JFrame f = new JFrame("Path Tester");
//		f.setLayout(null);
//		f.setSize(game.getWidth() + 200, game.getHeight());
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		f.setContentPane(this);
		
		
		
		// Expand the width by 200 in order to draw a menu bar on the panel.
		Dimension panelSize = new Dimension(game.getWidth() + 200, game.getHeight());
		
//		start = new JButton("Start");
//		start.setBounds(650, 450, 60, 30);
//		start.setEnabled(true);
//		start.setVisible(true);
//		this.add(start);
//		
//		start.addActionListener(this);
		
		this.setMinimumSize(panelSize);
		this.setPreferredSize(panelSize);
		this.setMaximumSize(panelSize);
		
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		
		f.pack();
		f.setLocationRelativeTo(null);  // Centers window
		f.setVisible(true);

		Timer t = new Timer(17, this);
		t.start();
	}

	// Methods
  	/**
  	 * This method will call the draw method inside GameState class
  	 * 
  	 * @param g the Graphics object
  	 */
	public void paint (Graphics g)
	{
		game.draw((Graphics2D) g);
	}

	/**
	 * The actionPerformed method is called (from the GUI event loop)
	 * whenever an action event occurs that this object is lisening to.
	 * 
	 * Also, this method will call the update method inside GameState class.
	 * @param e the event object 
	 */
	public void actionPerformed (ActionEvent e)
	{
		// Call from
		game.update();
		repaint();
	}

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	/**
	 * When mouse pressed on the path, get the x and y for that point, 
	 * and print out on console panel in order to build the path.
	 */
	public void mousePressed(MouseEvent e) 
	{
		game.setMousePressed();
	}
	@Override
	public void mouseReleased(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
	
	@Override
	/**
	 * Invoked when a mouse button is pressed on a component and then dragged.
	 * Also, set the point.
	 */
	public void mouseDragged(MouseEvent e) 
	{
		game.setMousePos(e.getPoint());
	}
	@Override
	public void mouseMoved(MouseEvent e) {}
}
