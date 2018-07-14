package Components.ViewComponents;

import android.graphics.Color;

import Components.ViewComponent;
import Prefabs.GameObject;
import Systems.SystemManager;

public class ScreenCircleView extends ViewComponent{
    public float radius=0;
    public boolean isFill=false;
    public int color= Color.BLACK;

    public ScreenCircleView(GameObject gameObject)
    {
        super(gameObject);
        SystemManager.getViewSystem().addViewComponent(this);
    }

    public ScreenCircleView(GameObject gameObject,int deep,float x,float y,float radius,int color,boolean isFill) {
        super(gameObject,deep);
        this.radius = radius;
        this.color=color;
        this.isFill=isFill;
        this.position.point.x=x;
        this.position.point.y=y;
        SystemManager.getViewSystem().addViewComponent(this);
    }
}
