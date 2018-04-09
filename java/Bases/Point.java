package Bases;

public class Point {
	public float x=0;
	public float y=0;
	
	public static final Point zero=new Point(0,0);
	
	public Boolean isCollide=false; //如果所在边有碰撞则为true
	
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
