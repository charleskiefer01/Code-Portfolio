 import java.io.*;
import java.util.Scanner;
import java.awt.*;

import javax.swing.*;

import java.awt.event.*;

 public class CatchCreaturePanel extends JPanel
{
	private ImageIcon image;
	private ImageIcon image2;

	private Timer timer;

	private int catchCount = 0;
	
	private int TimeDelay;

	int again, firstClick = 0;

	private boolean gameRunning = false;
	private boolean gameInitialize = false;

	private double randX = Math.random()*350+30;
	private double randY = Math.random()*350+30;
	
	String highScore = "highScore.txt";
	private String playerName = "";
	private String topName = "";
	int topScore = 0;
	String currentLine = "";
	
	JLabel lblPlayername = new JLabel("PlayerName" + topName);
	JLabel lblTopScore = new JLabel("High Score: " + topScore);



	public CatchCreaturePanel() throws IOException
	{
		try
		{
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(new File(highScore));
		while(scan.hasNext())
		{
			String line = scan.nextLine();
			currentLine = line;
		}
		
		@SuppressWarnings("resource")
		Scanner scanLine = new Scanner(currentLine);
		scanLine.useDelimiter(" ");
		
		int pointCheck = 0;
		
		while (scanLine.hasNext())
		{
			if (pointCheck == 0)
				topName = scanLine.next();
			else
				topScore = scanLine.nextInt();
			pointCheck++;
		}

		}
		catch(Exception e){
			System.out.println("High score: 0");
			}	
		
		TimeDelay = 4000;
		setPreferredSize(new Dimension(500,500));
		setBackground(Color.black);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.GRAY);
		panel.setForeground(Color.WHITE);
		add(panel);
		
		panel.setPreferredSize(new Dimension(500,30));
		
		Button button = new Button("Play");
		button.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (gameInitialize == false)
				{
				playerName = JOptionPane.showInputDialog("Enter player name: ");
				lblPlayername.setText("Player Name: " + playerName + "    ");
				lblTopScore.setText("High Score: " + topScore + " - " + topName);
				gameInitialize = true;
				
				timer.start();
				repaint();
				}
			}
		});
		button.setForeground(Color.DARK_GRAY);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				gameRunning = true;
				repaint();
				TimeDelay = 4000;
				timer.setDelay(TimeDelay);
				timer.start();
				
			}
		});
		
		
		panel.add(lblPlayername);
		panel.add(button);
		panel.add(lblTopScore);
		timer=new Timer((TimeDelay), new myListener());

		addMouseListener(new myListener());

		image = new ImageIcon("Kitten.png");
		image2 = new ImageIcon("SadKitten.png");
		again = JOptionPane.YES_OPTION;

		timer.start();

	}

	public void printTopScore(String playerName, int catchCount, int topScore) throws IOException
	{
		FileWriter fw = new FileWriter (highScore, false);
		BufferedWriter bw = new BufferedWriter(fw);
		PrintWriter outFile = new PrintWriter(bw);

		outFile.printf(playerName + " " + catchCount);

		outFile.close();
		System.out.println("Output file has been created: " + highScore);	

		System.out.println ("High score of " + topScore + " is by " + playerName);	
		
	}
	
	public void endGame()
	{
		if (catchCount > topScore)
		{
			try {
				printTopScore(playerName, catchCount, topScore);
			} catch (IOException e1) {
				System.out.println("I suck!");
			}
		}
		
		
		again = JOptionPane.showConfirmDialog(null, "You got it " + catchCount + " times. Play again?");

		catchCount = 0;

		if(again == JOptionPane.NO_OPTION)
			{
				timer.stop();
				gameRunning = false;

				repaint();
			}
		if(again == JOptionPane.YES_OPTION)
			{
				TimeDelay = 4000;
				timer.setDelay(TimeDelay);
				timer.start();
				
			}
	}
	
	public void paintComponent(Graphics page)
	{
		super.paintComponent(page);

		page.setColor(Color.white);


		if (gameRunning)
		{
			image.paintIcon(this, page, (int)randX,(int)randY);

		}
		else
		{
			image2.paintIcon(this, page, 0, 0);
			if (gameInitialize == true)
			page.drawString("Click play button to begin!", 200, 250);
		}
		
		

	}


	private class myListener implements ActionListener, MouseListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			if (firstClick == 1 && gameRunning == true && gameInitialize == true)
			{
				timer.stop();

				System.out.println (catchCount + " " + topScore);
				
				endGame();
				

			}
			else
				firstClick = 1;
			}

		public void mousePressed (MouseEvent event)
		{

		if (gameRunning)

		{
			Point p = event.getPoint();


			if (p.x > randX && p.x < randX+100 && p.y > randY && p.y < randY+100)
			{
						catchCount++;
						randX = Math.random()*350+30;
						randY = Math.random()*350+30;
						if (TimeDelay > 1000)
							TimeDelay = TimeDelay-100;
						timer.stop(); //This resets the countdown to the move
						timer.start();
						timer.setDelay(TimeDelay);
						
						
			}

			else
			{
				timer.stop();

				System.out.println (catchCount + " " + topScore);
				
				endGame();

			}
			

			repaint();
	}

		}


		public void mouseClicked(MouseEvent event){}
		public void mouseReleased(MouseEvent event){}
		public void mouseEntered(MouseEvent event){}
		public void mouseExited(MouseEvent event){}

	}

}