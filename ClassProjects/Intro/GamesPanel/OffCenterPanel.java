import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;


public class OffCenterPanel extends JPanel
{

	private Point p;
	private ArrayList <Point> myList = new ArrayList<Point>();

	public OffCenterPanel()
	{
		JButton b1=new JButton("Clear");
		add (b1);

		addMouseListener(new myListener());

		setPreferredSize(new Dimension(500, 500));
		setBackground(Color.black);

		setLayout (new FlowLayout());



		ButtonListener buttonListener = new ButtonListener();
		b1.addActionListener(buttonListener);
	}

	public void paintComponent(Graphics page)
	{
		super.paintComponent(page);

		page.setColor(Color.red);

		page.drawOval(245,245,10,10);

		for (Point p : myList)
		{

		if (p!=null)
			page.drawLine(p.x,p.y,250,250);

		}
	}

	private class myListener extends MouseAdapter
	{
		public void mouseClicked(MouseEvent event)
		{
			p = event.getPoint();
			myList.add(p);
			repaint();

		}
	}

	private class ButtonListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
				{
					myList.clear();
					repaint();
				}

	}

}