package Prefabs;

import java.util.ArrayList;

import Bases.*;
import Components.Component;
import Interfaces.EngineAndControl;

public class GameObject {
	protected ArrayList<Point> objectPosition;//GameObject �� ��������;
	protected ArrayList<Component> components;
	protected String tag = "";
	protected EngineAndControl engine;

	protected Point position;//���Ĳο��������
	
	/**
	 * ������Ϸ��������������Զ�������Ϸ�ܿ���
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
	 * @param engine ��Ϸ������
	 * @param fs ÿ���������
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
