package Interfaces;

import Bases.Vector2;
import Prefabs.GameObject;

/**
 * ʵ�ִ˽ӿڵ��࣬Ӧ�����л�����Ϸ����ʱ�ٳ�ʼ��
 * @author Administrator
 *
 */
public interface EngineAndControl {
	/**
	 * ��ȡ��������
	 * @return
	 */
	Vector2 getLeftMotion();
	
	/**
	 * �ж������Ƿ���
	 * @return ����Ϊ�棬����Ϊ��
	 */
	boolean getRightMotion();
	
	/**
	 * ������ƶ���
	 * @param obj ��ʵ��CanCollide�ӿ�
	 */
	void addToCollideDetect(CanCollide obj);
	
	/**
	 * �ӿ��ƶ������Ƴ�
	 * @param obj ��ʵ��CanCollide�ӿ�
	 */
	void removeFromCollideDetect(CanCollide obj);
	
	/**
	 * �¼�����Ϸ�����ܿ�
	 * @param obj
	 */
	void addToControlList(GameObject obj);
	
	/**
	 * ���ܿ����Ƴ���Ϸ����
	 * @param obj
	 */
	void removeFromControlList(GameObject obj);
}
