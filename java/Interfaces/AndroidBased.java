package Interfaces;

import java.util.List;

import android.graphics.Bitmap;
import android.graphics.Point;
import Bases.*;

public interface AndroidBased {
	/**
	 * 加入每帧更新的队列
	 * @param obj 需实现CanUpdate接口
	 */
	void addToUpdate(CanUpdate obj);
	
	/**
	 * 从更新队列中移除
	 * @param obj 需实现CanUpdate接口
	 */
	void removeFromUpdate(CanUpdate obj);
	
	/**
	 * 转换游戏场景
	 * @param screen
	 */
	void changeScreen(Screen screen);
	
	/**
	 * 将图片调入内存
	 * @param name 图片名称0
	 * @return
	 */
	Bitmap getImageByPath(String name);
	
	/**
	 * 绘制图片
	 * @param bitmap 要绘制的图片
	 * @param point 图片中点坐标
	 * @param degree 图片旋转角度
	 */
	void drawImage(Bitmap bitmap,Point point,float degree);
	
	/**
	 * 获取点击状态
	 * @return List对象为TouchEvent类
	 */
	List getTouchEvent();
}
