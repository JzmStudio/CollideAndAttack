package Components.ViewComponents;

import android.graphics.Bitmap;
import android.util.Log;

import Bases.Position;
import Components.ViewComponent;
import Prefabs.GameObject;
import Systems.SystemManager;

/**
 * 图像position.point定义图片左上角坐标,position.degree为图像旋转角度
 */
public class BitmapView extends ViewComponent{
    public Bitmap bitmap;
    private int bitmapId;
    public boolean isVisible=true;

    public BitmapView(GameObject gameObject, int bitmapId, Bitmap.Config config)
    {
        super(gameObject);
        this.bitmapId=bitmapId;
        bitmap= SystemManager.getStorage().getBitmapFromAssets(bitmapId,config);
        SystemManager.getViewSystem().addViewComponent(this);
    }

    public BitmapView(GameObject gameObject, int bitmapId, Bitmap.Config config,int deep)
    {
        super(gameObject,deep);
        this.bitmapId=bitmapId;
        bitmap= SystemManager.getStorage().getBitmapFromAssets(bitmapId,config);
        SystemManager.getViewSystem().addViewComponent(this);
    }

    /**
     *
     * @param gameObject
     * @param bitmapId
     * @param config
     * @param position 在本地坐标系下的坐标与角度
     * @param deep
     */
    public BitmapView(GameObject gameObject, int bitmapId, Bitmap.Config config, Position position,int deep)
    {
        super(gameObject,position,deep);
        this.bitmapId=bitmapId;
        bitmap= SystemManager.getStorage().getBitmapFromAssets(bitmapId,config);
        SystemManager.getViewSystem().addViewComponent(this);
    }
}
