
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class PongPanel extends JPanel
{
		private ImageIcon image;
		private int x, y, moveX, moveY, paddle_x;
		private int again;
		private int velocity;
		private int bounceHit = 0;
		private Timer timer;
		boolean gameStart = false;
		boolean gameInitialize = false;
		private ImageIcon backgroundimage;
		private String playerName;
		JLabel lblBounces = new JLabel("Bounces: " + bounceHit);
		JLabel lblPlayerName = new JLabel("Player Name: " + playerName);

		public PongPanel()
		{
			velocity = 3;
			timer=new Timer(20, new myListener());
			addMouseMotionListener(new myListener());
			addMouseListener(new myListener());
			image=new ImageIcon("happyFace.gif");
			backgroundimage = new ImageIcon("AM.gif");
			x = 40;
			paddle_x = 200;
			y = 40;
			moveX=moveY=3;
			setPreferredSize(new Dimension(500,500));
			setBackground(Color.black);
			timer.start();
			
			JPanel panel = new JPanel();
			panel.setPreferredSize(new Dimension(500,30));
			panel.setBackground(Color.GRAY);
			panel.setForeground(Color.WHITE);
			add(panel);
			
			panel.add(lblPlayerName);
			panel.add(lblBounces);
			
			Button playButton = new Button("Play");
			playButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
				}
			});
			playButton.setForeground(Color.DARK_GRAY);
			playButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if (gameInitialize == false)
					{
					playerName = JOptionPane.showInputDialog("Enter player name: ");
					lblPlayerName.setText("Player Name: " + playerName);
					gameInitialize = true;
					timer.setDelay(20);
					timer.start();
					repaint();
					}
					
					
				}
			});
			panel.add(playButton);
			
		}

		public void paintComponent(Graphics page)
		{
			super.paintComponent(page);
			backgroundimage.paintIcon(this, page, 0,0);
			image.paintIcon(this, page, x, y);
			page.setColor(Color.yellow);
			
			if (paddle_x > 400)
				page.fillRect(400, 450, 100, 10);
			
			else
				page.fillRect(paddle_x, 450, 100, 10);
			
			if (gameStart == false)
			{
				if (gameInitialize == false)
				{
					page.drawString("Click Play button to begin!", 200, 250);
					repaint();
				}
				else
				{
					page.drawString("Paused, click to begin", 200, 250);
					repaint();
				}
			}
			
		}

  private class myListener implements ActionListener, MouseMotionListener, MouseListener
  {
	  public void actionPerformed(ActionEvent e)
	  {
		  if (gameStart == true)
		  {
		  
		  x+=moveX;
		  y+=moveY;

		  if(y<=30)
		  	moveY=moveY * (-1);

		  if(x>465 || x<=0)
		  	moveX=moveX * (-1);
		  
		  
		  Rectangle ball = new Rectangle(x, y, image.getIconWidth(), image.getIconHeight());
		  Rectangle paddle = new Rectangle(paddle_x, 450, 100, 10);
		  
		  if(ball.intersects(paddle))
		  	{
			  	bounceHit++;
			  	lblBounces.setText("Bounces: " + bounceHit);
			  
			  	moveY=moveY * (-1);
			  	if (velocity < 20)
			  	{
			  		velocity++; 
			  		if (moveX > 0)
			  			moveX++;
			  		else
			  			moveX--;
			  		
			  		if (moveY > 0)
			  			moveY++;
			  		else
			  			moveY--;
			  	}
			  	System.out.println(moveX + " " + moveY);
	  		}
		  
		  if (y>500)
		  {
			  timer.stop();
			  again = JOptionPane.showConfirmDialog(null, "Play again?");
			  
			  if(again == JOptionPane.NO_OPTION)
				{
					timer.stop();
					setBackground(Color.red);

					repaint();
				}
			  if(again == JOptionPane.YES_OPTION)
				{
					x = 40;
					paddle_x = 200;
					y = 40;
					moveX = 5;
					moveY = 5;
					bounceHit = 0;
					lblBounces.setText("Bounces: " + bounceHit);
					
					timer.start();
				}
		  }
		  
		  repaint();
		 }
	  }
	  		//MouseMotionListener methods
			public void mouseMoved(MouseEvent event)
			{
				if (gameStart == true)
					paddle_x = event.getX();
				
				repaint();
				
			}
			
			public void mouseDragged(MouseEvent event){}
			
			//MouseListener methods
			
			public void mousePressed(MouseEvent event){}
			public void mouseReleased(MouseEvent event){}
			public void mouseEntered(MouseEvent event){}
			public void mouseClicked(MouseEvent event){
			if (gameInitialize==true)
			{
				if (gameStart == false)
				{
					gameStart = true;
					repaint();
				}
				else
				{
					gameStart = false;
				}
			}
			}
			
			public void mouseExited(MouseEvent event){}
			
			
			
			
		}
  }
