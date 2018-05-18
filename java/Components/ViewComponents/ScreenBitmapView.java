package Components.ViewComponents;

import android.graphics.Bitmap;

import Components.ViewComponent;
import Prefabs.GameObject;
import Systems.SystemManager;

/**
 * 在屏幕上显示,坐标为屏幕的真实坐标
 */
public class ScreenBitmapView extends ViewComponent {

    public Bitmap bitmap;
    private int bitmapId;

    public ScreenBitmapView(GameObject gameObject,int bitmapId, Bitmap.Config config) {
        super(gameObject);
        this.bitmapId=bitmapId;
        bitmap= SystemManager.getStorage().getBitmapFromAssets(bitmapId,config);
        SystemManager.getViewSystem().addViewComponent(this);
    }

    public ScreenBitmapView(GameObject gameObject,int bitmapId, Bitmap.Config config,int deep) {
        super(gameObject, deep);
        this.bitmapId=bitmapId;
        bitmap= SystemManager.getStorage().getBitmapFromAssets(bitmapId,config);
        SystemManager.getViewSystem().addViewComponent(this);
    }

    public ScreenBitmapView(GameObject gameObject,int bitmapId, Bitmap.Config config, float x, float y, int deep) {
        super(gameObject, x, y, deep);
        this.bitmapId=bitmapId;
        bitmap= SystemManager.getStorage().getBitmapFromAssets(bitmapId,config);
        SystemManager.getViewSystem().addViewComponent(this);
    }
}
