package Interfaces;

import android.graphics.Bitmap;

public interface Graphics {
	/**
	 * ����Ļָ��λ�û�ͼ
	 * @param bitmap ͼƬ
	 * @param x ͼƬ���Ͻ�x����
	 * @param y ͼƬ���Ͻ�y����
	 * @param degree ͼƬ˳ʱ����ת�Ƕ�
	 */
	public void drawPicture(Bitmap bitmap,int x,int y,float degree);
}
