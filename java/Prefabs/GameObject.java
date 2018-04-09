package Prefabs;

import java.util.ArrayList;

import Bases.*;
import Components.Component;
import Interfaces.EngineAndControl;

public class GameObject {
	protected ArrayList<Point> objectPosition;//GameObject 的 顶点坐标;
	protected ArrayList<Component> components;
	protected String tag = "";
	protected EngineAndControl engine;

	protected Point position;//中心参考点的坐标
	
	/**
	 * 任意游戏对象构造出来都会自动加入游戏总控中
	 * @param engine
	 */
	public GameObject(EngineAndControl engine)
	{
		objectPosition=new ArrayList<Point>();
		components = new ArrayList<Component>();
		position=new Point();
		this.engine=engine;
		engine.addToControlList(this);
	}
	
	/**
	 * 
	 * @param engine 游戏主控制
	 * @param fs 每个点的坐标
	 */
	public GameObject(EngineAndControl engine,Point...fs)
	{
		objectPosition=new ArrayList<Point>();
		for(Point p:fs)
		{
			objectPosition.add(p);
		}
		components = new ArrayList<Component>();
		this.engine=engine;
		engine.addToControlList(this);
	}
	
	public void addPosition(Point...fs)
	{
		for(Point p:fs)
		{
			objectPosition.add(p);
		}
	}
	
	public ArrayList<Point> getObjectPosition()
	{
		return objectPosition;
	}
	
	public void collideAction(GameObject other)
	{
		//To override
	}
	
	public void setTag(String tag)
	{
		this.tag = tag;
	}
	
	public String getTag()
	{
		return this.tag;
	}
	
	public void fixedUpdate(float deltaTime)
	{
		
	}
}
