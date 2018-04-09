package Events;

import Prefabs.GameObject;

public class CollideEvent extends Event{

	GameObject obj;
	public CollideEvent(String name,GameObject obj) {
		super(name);
		this.obj=obj;
		// TODO Auto-generated constructor stub
	}
	//do something
}