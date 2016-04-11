import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

@SuppressWarnings("serial")
public class ToHPanel extends JPanel {
	
	private int numDisks, towerSpacing;
	private final int DISK_HEIGHT, WIDTH, HEIGHT, TOWER_WIDTH;
	private Tower[] towers;
	private ArrayList<Rectangle> disks;
	private Timer timer;
	public ArrayList<Point> solution;
	private boolean animationPlaying = false;
	String numDisksString = "";
	
			public ToHPanel()
		{
				
			numDisks = 3;
			animationPlaying = false;
			DISK_HEIGHT=20;
			TOWER_WIDTH=10;
			WIDTH=500;
			HEIGHT=500;
			towerSpacing=WIDTH/2;

			timer = new Timer(1000, new TimerListener());
			
			runTowers();
				
				JPanel panel = new JPanel();
				add(panel);
				panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
				
				JButton btnPlaypause = new JButton("Play/Pause");
				panel.add(btnPlaypause);
				
				final JSlider slider = new JSlider();
				panel.add(slider);
				slider.addChangeListener(new ChangeListener() {
					public void stateChanged(ChangeEvent arg0) 
					{
						numDisks = slider.getValue();
						runTowers();
						timer.start();
						repaint();
					}
				});
				slider.setSnapToTicks(true);
				slider.setPaintLabels(true);
				slider.setPaintTicks(true);
				slider.setToolTipText("Number of Disks");
				slider.setValue(numDisks);
				slider.setMinorTickSpacing(1);
				slider.setMajorTickSpacing(1);
				slider.setMinimum(1);
				slider.setMaximum(10);
				
				
				btnPlaypause.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) 
					{
						if (animationPlaying == true)
						{
							timer.stop();
							animationPlaying = false;
							repaint();
						}
						else
						{
							timer.start();
							animationPlaying = true;
						}
					}
				});
				
				JPanel panel_1 = new JPanel();
				add(panel_1);
				
				JButton btnReset = new JButton("Reset");
				btnReset.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) 
					{
						runTowers();
						repaint();
						timer.start();
					}
				});
				panel_1.add(btnReset);
				
				JLabel lblNumberOfDisks = new JLabel("Number of Disks                                         Speed");
				add(lblNumberOfDisks);
				
				final JSlider slider_1 = new JSlider();
				slider_1.setPaintLabels(true);
				slider_1.setPaintTicks(true);
				panel.add(slider_1);
				slider_1.setMajorTickSpacing(500);
				slider_1.setMaximum(2000);
				slider_1.setMinimum(0);
				slider_1.addChangeListener(new ChangeListener() {
					public void stateChanged(ChangeEvent arg0) {
						timer.setDelay(slider_1.getValue());
					}
				});
				
				timer.start();
		}
		
	public void paintComponent(Graphics page)
	{
		super.paintComponent(page);
		page.setColor(Color.gray);
		for(Tower t : towers)
		{
			page.setColor(Color.gray);
			page.fillRect(t.getx(),t.gety(),TOWER_WIDTH,t.getHeight());
			page.setColor(Color.black);
			ArrayList<Rectangle> d=t.getDisksArray();
			 if(d!=null)
			 {
				 for(Rectangle tempD :d)
				 {
					 page.fillRect((int)tempD.getX(),(int)tempD.getY(),(int)tempD.getWidth(),(int)tempD.getHeight());
				 }
			 }
		}
		
		if (!animationPlaying)
		{
			page.drawString("Animation paused", 200, 400);
		}
			
		
		
	}
	
	
	private class TimerListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if (animationPlaying == true)
			{
				if(solution.isEmpty() == false)
				{
					int removeFrom = (int)solution.get(0).getX();
					//System.out.println("Remove from: " + removeFrom);
					Rectangle d = towers[removeFrom].removeDisk();
					int addTo=(int)solution.get(0).getY();
				
					towers[addTo].addDisk(d);
					solution.remove(0);
					repaint();
				}
				else
				{
					//animationPlaying = false;
					timer.stop();
				}
			}
		}
		
		
	}
	
	public void runTowers()
	{
		towers= new Tower[3];
		for (int i = 0; i < 3; i++)
		{
			int towerHeight = numDisks*DISK_HEIGHT;
			int towerX = (i + 1)*towerSpacing/2 - TOWER_WIDTH/2;
			int towerY = 300 - (numDisks*DISK_HEIGHT);

			towers[i]= new Tower(towerX, towerY, towerHeight);
		}
		
		disks = new ArrayList<Rectangle>();
		int diskX=towers[0].getx() - 5;
		int diskY=towers[0].gety(); 
		int diskWidth= 20;

		for(int i = 0; i < numDisks; i++)
			{
				Rectangle d= new Rectangle(diskX, diskY,diskWidth,DISK_HEIGHT);
				
				disks.add(d);
				
				diskWidth+=10;
				diskX-=5;
				diskY+=DISK_HEIGHT;

			}
		
		towers[0].addDisksArray(disks);
		setPreferredSize(new Dimension(WIDTH,HEIGHT));
		
		TowersOfHanoi solveTowers = new TowersOfHanoi(numDisks);
		
		solution = solveTowers.getSolution();
	}
	
	
}
