package Interfaces;

import android.graphics.Bitmap;

public interface Graphics {
	/**
	 * 在屏幕指定位置画图
	 * @param bitmap 图片
	 * @param x 图片左上角x坐标
	 * @param y 图片左上角y坐标
	 * @param degree 图片顺时针旋转角度
	 */
	public void drawPicture(Bitmap bitmap,int x,int y,float degree);
}
