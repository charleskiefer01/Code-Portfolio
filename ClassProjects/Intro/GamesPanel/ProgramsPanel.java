import javax.swing.*;
import java.io.*;
import java.util.*;

public class ProgramsPanel
{
	public static void main (String[] args) throws IOException
	{
		JFrame frame = new JFrame ("Programs!");
		frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		JTabbedPane tp = new JTabbedPane();

		tp.addTab ("Pong", new PongPanel());
		tp.addTab ("Towers of Hanoi", new ToHPanel());
		tp.addTab ("Catch the Creature", new CatchCreaturePanel());

		frame.getContentPane().add(tp);
		frame.pack();
		frame.setVisible(true);
	}
}