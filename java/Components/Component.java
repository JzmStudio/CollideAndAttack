package Components;

import Prefabs.GameObject;

public abstract class Component {
	protected GameObject gameObject;

	public Component(GameObject gameObject)
	{
		this.gameObject=gameObject;
	}

	/**
	 * 组件被移除时调用,子类需根据需要重写
	 */
	public abstract void onRemove();
}