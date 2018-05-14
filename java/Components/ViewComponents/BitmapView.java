package Components.ViewComponents;

import android.graphics.Bitmap;

import Components.ViewComponent;
import Prefabs.GameObject;
import Systems.SystemManager;

/**
 * 图像position.point定义图片左上角坐标,position.degree为图像旋转角度
 */
public class BitmapView extends ViewComponent{
    public Bitmap bitmap;
    private int bitmapId;

    public BitmapView(GameObject gameObject, int bitmapId, Bitmap.Config config)
    {
        super(gameObject);
        this.bitmapId=bitmapId;
        bitmap= SystemManager.getStorage().getBitmapFromAssets(bitmapId,config);
    }
}
