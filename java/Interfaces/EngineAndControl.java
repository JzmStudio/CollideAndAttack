package Interfaces;

import Bases.Vector2;
import Prefabs.GameObject;

public interface EngineAndControl {
	/**
	 *
	 * @return
	 */
	Vector2 getLeftMotion();
	

	boolean getRightMotion();
	

	void addToCollideDetect(CanCollide obj);
	

	void removeFromCollideDetect(CanCollide obj);
	

	void addToControlList(GameObject obj);
	

	void removeFromControlList(GameObject obj);
}
