package Prefabs;

import java.util.ArrayList;

import Bases.*;
import Components.Component;
import Interfaces.EngineAndControl;
import World.GameWorld;

public class GameObject {
	private Point objectPosition;//GameObject世界坐标
	private ArrayList<Component> components;
	private String tag = "";
	private GameWorld world;

	public GameObject(GameWorld world)
	{
		this.world=world;
		components = new ArrayList<Component>();
		world.addGameObject(this);
	}
	
	public void setTag(String tag)
	{
		this.tag = tag;
	}
	
	public String getTag()
	{
		return this.tag;
	}

	/**
	 * 销毁这个物体
	 */
	public void destroy()
	{
		for(Component com:components){
			com.onRemove();
		}
		components.clear();
		world.removeGameObject(this);
	}
}
