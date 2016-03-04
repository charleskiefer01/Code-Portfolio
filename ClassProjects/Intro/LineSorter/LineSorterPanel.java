import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class LineSorterPanel extends JPanel
{

	private ArrayList<LineObject> LineList;
	private Timer timer;
	private int index;

	public LineSorterPanel()
	{
		LineList = new ArrayList<LineObject>();
		index = 0;
			
			for (int i = 5; i < 500; i += 20)
			{
				LineObject l = new LineObject(i, (int)(Math.random()*100+10));
				LineList.add(l);
			}

		setPreferredSize(new Dimension(500, 150));
		setBackground(Color.black);

		setLayout (new FlowLayout());
		timer = new Timer (300, new TimeListener());
		timer.start();
		

		
	}


	public void paintComponent(Graphics page)
	{
		super.paintComponent(page);

		page.setColor(Color.red);
		
		for (LineObject lineUsing : LineList)
		{
			page.drawLine(lineUsing.x, 0, lineUsing.x, lineUsing.y);
		}
		
		if (index == LineList.size()-1)
		{
			page.drawString("All done!", 10, 100);
		}

		
		
		

	}

	   private class TimeListener implements ActionListener
	   {
	      //--------------------------------------------------------------
	      //  Move the dots in the pointList
	      //--------------------------------------------------------------
	      public void actionPerformed (ActionEvent event)
	      {
	    	int minValue;
	    	
	    	 if (index < LineList.size()-1)
	    	 {
	    		 minValue = index;
	    		 LineObject first = LineList.get(index);
	    		 
	    		 LineObject minObject = LineList.get(minValue);
	    		 for (int i = index+1; i<LineList.size(); i++)
	    		 {
	    			 LineObject currentObject = LineList.get(i);
	    			 
	    			 if (currentObject.y < minObject.y)
	    			 {
	    				 minObject = currentObject;
	    			 }
	    		 }
	    		 
	    		 minValue = minObject.y;
	    		 minObject.y = first.y;
	    		 first.y = minValue;
	    		 
	    		 
	    		 index++;
	    	 }
	    	 
	         repaint();
	         
	      }
	   }

}