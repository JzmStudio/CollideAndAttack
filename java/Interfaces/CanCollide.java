package Interfaces;

import Events.CollideEvent;

public interface CanCollide {
	/**
	 * 碰撞回调函数
	 */
	void collide(CollideEvent collide);
}