package Components.ViewComponents;

import Components.ViewComponent;
import Prefabs.GameObject;
import Systems.SystemManager;

public class TextView extends ViewComponent {
    public String text;
    public float size;
    public int color;
    public int width=10;


    public TextView(GameObject gameObject,String text) {
        super(gameObject);
        this.text=text;
        SystemManager.getViewSystem().addViewComponent(this);
    }
}