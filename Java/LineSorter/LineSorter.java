import javax.swing.*;

public class LineSorter
{
	public static void main (String[] args)
	{
		JFrame frame = new JFrame ("Programs!");
		frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		JTabbedPane tp = new JTabbedPane();

		tp.add("LineSorter", new LineSorterPanel());

		frame.getContentPane().add(tp);
		frame.pack();
		frame.setVisible(true);
	}
}