package Interfaces;

import Events.CollideEvent;

public interface CanCollide {
	/**
	 * 在此函数中检测碰撞
	 */
	void collide(CollideEvent collide);
}