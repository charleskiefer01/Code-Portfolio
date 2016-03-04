import java.util.ArrayList;
import java.awt.*;
public class TowersOfHanoi {
	private int totalDisks;
	private static ArrayList<Point> moves;
	
	public ArrayList<Point> getSolution()
	{
		return moves;
	}
	
	public static void clearMoves()
	{
		moves.clear();
	}
	
	public TowersOfHanoi(int numDisks)
	{
		totalDisks=numDisks;
		moves=new ArrayList<Point>();
		moveTower(totalDisks, 0, 2, 1);
	}
	public void moveTower(int disk, int source, int dest, int spare)
	{
		if(disk==1)
		{
			Point p = new Point(source,dest);
			moves.add(p);
		}
		else
		{
			moveTower(disk-1, source, spare, dest);
			Point p = new Point(source, dest);
			moves.add(p);
			moveTower(disk-1,spare, dest, source);
		}
	}
}
