package Components;

import Events.CollideEvent;
import Prefabs.GameObject;
import Systems.SystemManager;

public abstract class UpdateComponent extends Component{
	UpdateComponent(GameObject gameObject)
	{
		super(gameObject);
		SystemManager.scriptSystem.addToUpdateList(this);
	}

	/**
	 * 程序开始时调用
	 */
	abstract public void Start();
	
	/**
	 * 每帧更新调用
	 */
	abstract public void Update(float deltaTime);

	abstract public void onDestroy();

	/**
	 * 被碰撞时调用
	 * @param event
	 */
	abstract public void collide(CollideEvent event);

	@Override
	public void onRemove() {
		SystemManager.scriptSystem.removeFromUpdateList(this);
	}
}
