package Interfaces;

import java.util.List;

import android.graphics.Bitmap;
import android.graphics.Point;
import Bases.*;

public interface AndroidBased {
	/**
	 * ??????????????
	 * @param obj ?????CanUpdate???
	 */
	void addToUpdate(CanUpdate obj);
	
	/**
	 * ?????????????
	 * @param obj ?????CanUpdate???
	 */
	void removeFromUpdate(CanUpdate obj);
	
	/**
	 * ??????????
	 * @param screen
	 */
	void changeScreen(Screen screen);
	
	/**
	 * ???????????
	 * @param name ??????0
	 * @return
	 */
	Bitmap getImageByPath(String name);
	
	/**
	 * ??????
	 * @param bitmap ????????
	 * @param point ???е?????
	 * @param degree ????????
	 */
	void drawImage(Bitmap bitmap,Point point,float degree);
	
	/**
	 * ????????
	 * @return List?????TouchEvent??
	 */
	List getTouchEvent();
}
