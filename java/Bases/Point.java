package Bases;

import android.util.Log;

public class Point {
	public float x;
	public float y;
	
	public static final Point zero=new Point(0,0);
	
	public Boolean isCollide=false;
	
	public Point()
	{
		x=0;
		y=0;
	}
	
	public Point(float x,float y)
	{
		this.x=x;
		this.y=y;
	}
	
	public static float distance(Point p1,Point p2)
	{
		return (float) Math.sqrt((p1.x-p2.x)*(p1.x-p2.x)+(p1.y-p2.y)*(p1.y-p2.y));
	}
}
