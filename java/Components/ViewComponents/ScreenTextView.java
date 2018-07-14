package Components.ViewComponents;

import Components.ViewComponent;
import Prefabs.GameObject;
import Systems.SystemManager;

public class ScreenTextView extends ViewComponent {
    public String text;
    public float size;
    public int color;
    public int width=10;
    public ScreenTextView(GameObject gameObject,String text) {
        super(gameObject);
        this.text=text;
        SystemManager.getViewSystem().addViewComponent(this);
    }

    public ScreenTextView(GameObject gameObject,String text,int color,float size, int deep) {
        super(gameObject, deep);
        this.text=text;
        this.color=color;
        this.size=size;
        SystemManager.getViewSystem().addViewComponent(this);
    }

    public ScreenTextView(GameObject gameObject,String text,int x,int y,float degree,int color,float size, int deep) {
        super(gameObject, deep);
        this.text=text;
        this.color=color;
        this.size=size;
        this.position.point.x=x;
        this.position.point.y=y;
        this.position.degree=degree;
        SystemManager.getViewSystem().addViewComponent(this);
    }
}
