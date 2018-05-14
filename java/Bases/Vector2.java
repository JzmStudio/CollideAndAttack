package Bases;

public class Vector2 {
	
	public float x;
	public float y;
	
	public static final Vector2 zero=new Vector2();
	public static final Vector2 Y=new Vector2(0, 1);
	public static final Vector2 X=new Vector2(1, 0);
	
	public Vector2()
	{
		x=0;
		y=0;
	}
	
	public Vector2(float ix,float iy)
	{
		this.x=ix;
		this.y=iy;
	}
	
	public static Vector2 vectorFromPoint(Point p1,Point p2)
	{
		return new Vector2(p2.x-p1.x, p2.y-p1.y);
	}
	
	public static Vector2 newVector2(float ix,float iy)
	{
		return new Vector2(ix,iy);
	}
	
	public static Vector2 plusVector2(Vector2 v1,Vector2 v2)
	{
		return new Vector2(v1.x+v2.x, v1.y+v2.y);
	}
	
	public static Vector2 minusVector2(Vector2 v1,Vector2 v2)
	{
		return new Vector2(v1.x-v2.x,v1.y-v2.y);
	}
	
	/**
	 * 求向量的正交单位向量
	 * @param v
	 * @return
	 */
	public static Vector2 normalVector2(Vector2 v)
	{
		Vector2 p=new Vector2(-v.y, v.x);
		Vector2.unitVector2(p);
		return p;
	}
	
	public Vector2 plus(Vector2 v2)
	{
		return new Vector2(this.x+v2.x, this.y+v2.y);
	}
	
	public Vector2 minus(Vector2 v2)
	{
		return new Vector2(this.x-v2.x, this.y-v2.y);
	}
	
	public float length()
	{
		return (float) Math.sqrt(this.x*this.x+this.y*this.y);
	}
	
	/**
	 * 将此向量变为对应的单位向量
	 * @param v
	 * @return
	 */
	public static void unitVector2(Vector2 v)
	{
		float length=v.length();
		v.x=v.x/length;
		v.y=v.y/length;
	}
	
	/**
	 * 求反
	 * @param 
	 * @return
	 */
	public void changeNegative()
	{
		this.x=-this.x;
		this.y=-this.y;
	}
	
	public void multiply(float z)
	{
		this.x=this.x*z;
		this.y=this.y*z;
	}
	
	/**
	 * 向量点乘
	 * @param v1, v2
	 * @return
	 */
	public static float multiplication(Vector2 v1, Vector2 v2)
	{
		return (v1.x*v2.x + v1.y*v2.y);
	}
}
