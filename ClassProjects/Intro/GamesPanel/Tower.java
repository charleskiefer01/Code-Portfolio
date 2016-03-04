import java.awt.Rectangle;
import java.util.ArrayList;

public class Tower {
	private int towerX, towerY, towerHeight;
	private ArrayList<Rectangle>disks;
	
	public Tower (int x, int y, int z)
	{
		towerX = x;
		towerY = y;
		towerHeight = z;
		disks = new ArrayList<Rectangle>();		
	}

	public int getx()
	{
		return towerX;
	}
	
	public int gety()
	{
		return towerY;
	}
	
	public int getHeight()
	{
		return towerHeight;
	}
	
	public void addDisksArray(ArrayList<Rectangle> d)
	{
		disks=d;
	}
	
	public ArrayList<Rectangle>  getDisksArray()
	{
		return disks;
	}
	
	public Rectangle removeDisk()
	{
		return disks.remove(0);
	}
	
	public void addDisk(Rectangle d)
	{
		int x=towerX -((int)d.getWidth()/2 - 5);
		Rectangle disk = new Rectangle(x, (int)d.getY(),(int)d.getWidth(), (int) d.getHeight());
		disks.add(0, disk);
	}
	
	public void resetDisks()
	{
		disks.clear();
	}
	
	
	
}
