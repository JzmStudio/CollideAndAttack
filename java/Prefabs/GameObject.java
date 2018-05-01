package Prefabs;

import java.util.ArrayList;

import Bases.*;
import Components.Component;
import Interfaces.EngineAndControl;

public class GameObject {
	protected Point objectPosition;//GameObject世界坐标
	protected ArrayList<Component> components;
	protected String tag = "";

	/**
	 *
	 * @param engine
	 */
	public GameObject(EngineAndControl engine)
	{
		components = new ArrayList<Component>();
		engine.addToControlList(this);
	}
	
	public void setTag(String tag)
	{
		this.tag = tag;
	}
	
	public String getTag()
	{
		return this.tag;
	}
}
