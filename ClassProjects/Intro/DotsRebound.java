
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JSlider;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class DotsRebound
{
	private static JTextField textField;
	private static JTextField textField_1;
	private static DotsReboundPanel dotsReboundPanel;
	private static JSlider slider;
   //-----------------------------------------------------------------
   //  Creates and displays the application frame.
   //-----------------------------------------------------------------
   public static void main (String[] args)
   {
      JFrame frame = new JFrame ("Dots");
      frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
      
      //myDots=new DotsReboundPanel();

      dotsReboundPanel = new DotsReboundPanel();
      frame.getContentPane().add (dotsReboundPanel, BorderLayout.NORTH);
      
      JLabel lblNewLabel = new JLabel("Dot Size");
      dotsReboundPanel.add(lblNewLabel);
      
      textField = new JTextField();
      dotsReboundPanel.add(textField);
      textField.setColumns(10);
      
      JLabel lblNewLabel_1 = new JLabel("Dot Speed");
      dotsReboundPanel.add(lblNewLabel_1);
      
      textField_1 = new JTextField();
      dotsReboundPanel.add(textField_1);
      textField_1.setColumns(10);
      
      JButton btnNewButton = new JButton("Make Changes");
      dotsReboundPanel.add(btnNewButton); 
      
      slider = new JSlider();
      slider.addChangeListener(new ChangeListener() {
      	public void stateChanged(ChangeEvent arg0) {
      		dotsReboundPanel.setDelay(slider.getValue());
      	}
      });
      slider.setPaintLabels(true);
      slider.setPaintTicks(true);
      slider.setMinorTickSpacing(10);
      slider.setMajorTickSpacing(20);
      dotsReboundPanel.add(slider);

      frame.pack();
      frame.setVisible(true);
   }
   
   
}
