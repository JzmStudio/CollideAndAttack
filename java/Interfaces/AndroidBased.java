package Interfaces;

import java.util.List;

import android.graphics.Bitmap;
import android.graphics.Point;
import Bases.*;

public interface AndroidBased {
	/**
	 * ����ÿ֡���µĶ���
	 * @param obj ��ʵ��CanUpdate�ӿ�
	 */
	void addToUpdate(CanUpdate obj);
	
	/**
	 * �Ӹ��¶������Ƴ�
	 * @param obj ��ʵ��CanUpdate�ӿ�
	 */
	void removeFromUpdate(CanUpdate obj);
	
	/**
	 * ת����Ϸ����
	 * @param screen
	 */
	void changeScreen(Screen screen);
	
	/**
	 * ��ͼƬ�����ڴ�
	 * @param name ͼƬ����0
	 * @return
	 */
	Bitmap getImageByPath(String name);
	
	/**
	 * ����ͼƬ
	 * @param bitmap Ҫ���Ƶ�ͼƬ
	 * @param point ͼƬ�е�����
	 * @param degree ͼƬ��ת�Ƕ�
	 */
	void drawImage(Bitmap bitmap,Point point,float degree);
	
	/**
	 * ��ȡ���״̬
	 * @return List����ΪTouchEvent��
	 */
	List getTouchEvent();
}
