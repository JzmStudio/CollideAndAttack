package Components.ViewComponents;

import Components.ViewComponent;
import Prefabs.GameObject;
import Systems.SystemManager;

public class ScreenRectView extends ViewComponent {
    public float width;
    public float height;
    public float radius;    //圆角矩形的弧度
    public int color;
    public boolean isFill;
    /**
     * 若不填充,此为线条粗细
     */
    public float strockWidth=18;
    public ScreenRectView(GameObject gameObject) {
        super(gameObject);
        SystemManager.getViewSystem().addViewComponent(this);
    }
    public ScreenRectView(GameObject gameObject,float left,float top,int deep,float width,float height,int color,float radius,boolean isFill)
    {
        super(gameObject,left,top,deep);
        this.width=width;
        this.height=height;
        this.color=color;
        this.radius=radius;
        this.isFill=isFill;
        SystemManager.getViewSystem().addViewComponent(this);
    }

    public ScreenRectView(GameObject gameObject, int deep) {
        super(gameObject, deep);
        SystemManager.getViewSystem().addViewComponent(this);
    }
}
