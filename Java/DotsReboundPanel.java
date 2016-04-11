
import java.util.ArrayList;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;	// get the timer functionality

public class DotsReboundPanel extends JPanel
{
   private  int SIZE = 6;  // radius of each dot

   private Timer timer;	// timer for animation

   private  int DELAY = 33;	// 30 fps, 33 ms delay

   private ArrayList<Point> pointList;

   private int[] xvel = new int[1000], yvel = new int[1000]; // velocity x, y

   private Color[] col = new Color[1000];

      //  Constructor: Sets up this panel to listen for mouse events.
      public DotsReboundPanel()
      {
         pointList = new ArrayList<Point>();

         // initialize my velocity vectors
         for (int i=0; i < 1000; i++)
         {
         	xvel[i] = (int) (Math.random()*10-5);// random velocity between
         	yvel[i] = (int) (Math.random()*10-5);
         	col[i] = new Color(
         		(int)(Math.random()*255),
         		(int)(Math.random()*255),
         		(int)(Math.random()*255) );
         }

         timer = new Timer (DELAY, new DotTimeListener()); // create timer

         addMouseListener (new DotsListener());
         setBackground(Color.black);
         setPreferredSize (new Dimension(500, 500));

         timer.start();	// start timer
      }
      
      public void setDelay(int delay)
      {
    	  timer.setDelay(delay);
      }
      
      public void setSize(int radius)
      {
    	  SIZE = radius;
      }


      //  Draws all of the dots stored in the list.
      public void paintComponent (Graphics page)
      {
         super.paintComponent(page);

		 int i=0;
         for (Point spot : pointList)
         {
       		page.setColor (col[i]);
            page.fillOval (spot.x-SIZE, spot.y-SIZE, SIZE*2, SIZE*2);
            i++;
         }
		 page.setColor( Color.red );
         page.drawString ("Count: " + pointList.size(), 5, 15);
      }
      

   //  Represents the listener for mouse events.
   private class DotsListener implements MouseListener
   {
      //  Adds the current point to the list of points and redraws
      //  the panel whenever the mouse button is pressed.
      public void mousePressed (MouseEvent event)
      {
         pointList.add(event.getPoint());
         repaint();
      }

      //  Provide empty definitions for unused event methods.
      public void mouseClicked (MouseEvent event) {}
      public void mouseReleased (MouseEvent event) {}
      public void mouseEntered (MouseEvent event) {}
      public void mouseExited (MouseEvent event) {}
   }

   //  Represents the action listener for the timer.
   private class DotTimeListener implements ActionListener
   {
      //  Move the dots in the pointList
      public void actionPerformed (ActionEvent event)
      {
      	int i=0;
		for(Point spot : pointList)
		{
			spot.x += xvel[i];
			spot.y += yvel[i];

			// bounce my dots around
			if ((spot.x+SIZE)>getWidth() || spot.x<0)
				xvel[i] = -xvel[i];
			if (spot.y>getHeight() || spot.y<0)
				yvel[i] = -yvel[i];


			i++;
		}

         repaint();
      }
   }

}
