package Prefabs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Components.Component;
import Components.PhysicsComponents.Transform;

/**
 * 所有游戏对象默认含有一个Position组件,游戏一律用Android方式的坐标系
 */
public class GameObject {
	private Transform localCoordinate;//GameObject世界坐标
    private Map<String,ArrayList<Component>> components;
	public String tag = "";

	public GameObject()
	{
		components=new HashMap<>();
		localCoordinate=new Transform(this);
		addComponent(localCoordinate);
	}

	public GameObject(String tag)
	{
		this.tag=tag;
		components=new HashMap<>();
		localCoordinate=new Transform(this);
		addComponent(localCoordinate);
	}

	public GameObject(Transform position)
	{
		components=new HashMap<>();
		localCoordinate=new Transform(this);
		addComponent(localCoordinate);
	}

	public ArrayList getComponent(String className)
	{
		return components.get(className);
	}

	/**
	 * 销毁这个物体
	 */
	public void destroy()
	{
		for(ArrayList<Component> arrayList:components.values())
			for(Component c:arrayList)
				c.onRemove();
		components.clear();
	}

	public void addComponent(Component component)
	{
		if(!components.containsKey(component.getClass().getSimpleName()))
        {
            components.put(component.getClass().getSimpleName(),new ArrayList());
        }
        else {
			components.get(component.getClass().getSimpleName()).add(component);
		}
	}

	/**
	 * --------此函数调用需保证物体本身存在该component-----------
	 * @param component
	 */
	public void removeComponent(Component component)
	{
		ArrayList<Component> arrayList=components.get(component.getClass().getSimpleName());
		arrayList.remove(component);
	}

	public void removeComponents(String className)
	{
		components.remove(className);
	}

	public Transform getObjectPosition() {
		return localCoordinate;
	}

	public void setObjectPosition(Transform localCoordinate) {
		this.localCoordinate = localCoordinate;
	}
}
