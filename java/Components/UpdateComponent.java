package Components;

import Events.CollideEvent;

public abstract class UpdateComponent {
	UpdateComponent()
	{
		
	}
	
	/**
	 * 在Update前被调用
	 */
	abstract public void Start();
	
	/**
	 * 画面更新及逻辑更新等
	 */
	abstract public void Update(float deltaTime);
	
	/**
	 * 碰撞回调
	 */
	abstract public void collide(CollideEvent event);
}
