package Components;

import Events.CollideEvent;
import Prefabs.GameObject;
import Systems.SystemManager;

/**
 * -------一定在所有组件里最后添加脚本---------
 */
public abstract class UpdateComponent extends Component{

	UpdateComponent(GameObject gameObject)
	{
		super(gameObject);
	}
	
	/**
	 * 每帧更新调用
	 */
	abstract public void Update(float deltaTime);

	/**
	 * 所依附的游戏对象被摧毁时调用
	 */
	public void onDestroy() {}

	/**
	 * 被碰撞时调用
	 * @param event
	 */
	abstract public void collide(CollideEvent event);

	@Override
	public void onRemove() {
		onDestroy();
	}

	@Override
	public void removeThis()
	{
		super.removeThis();
	}
}
