package Events;

import Bases.Point;

public class TouchEvent extends Event{

	public static final int Touch_Down=0;
	public static final int Touch_Up=1;
	public static final int Touch_Move=3;
	
	public int type=-1;
	public Point point;
	/**
	 * ID>0为有效值
	 */
	public int ID=-1;
	
	public TouchEvent()
	{
		super("AndroidTouch");
		point=new Point();
	}
}
