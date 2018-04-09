package Events;

import Bases.Point;

public class TouchEvent extends Event{

	public static final int Touch_Down=0;
	public static final int Touch_Up=1;
	public static final int Touch_Move=3;
	
	public int type=-1;
	public Point position;
	public int ID=-1;
	
	public TouchEvent(String name)
	{
		super(name);
		position=new Point();
	}
}
