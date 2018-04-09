package Interfaces;

import Bases.Vector2;
import Prefabs.GameObject;

/**
 * 实现此接口的类，应该在切换到游戏场景时再初始化
 * @author Administrator
 *
 */
public interface EngineAndControl {
	/**
	 * 获取左手向量
	 * @return
	 */
	Vector2 getLeftMotion();
	
	/**
	 * 判断右手是否按下
	 * @return 按下为真，否则为假
	 */
	boolean getRightMotion();
	
	/**
	 * 加入控制队列
	 * @param obj 需实现CanCollide接口
	 */
	void addToCollideDetect(CanCollide obj);
	
	/**
	 * 从控制队列中移除
	 * @param obj 需实现CanCollide接口
	 */
	void removeFromCollideDetect(CanCollide obj);
	
	/**
	 * 新加入游戏对象到总控
	 * @param obj
	 */
	void addToControlList(GameObject obj);
	
	/**
	 * 从总控中移除游戏对象
	 * @param obj
	 */
	void removeFromControlList(GameObject obj);
}
