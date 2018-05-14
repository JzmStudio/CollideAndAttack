package Components.ViewComponents;

import java.util.ArrayList;
import java.util.Arrays;

import Bases.Point;
import Components.ViewComponent;
import Prefabs.GameObject;

/**
 * 由坐标点描述的封闭图形
 */
public class ClosePointView extends ViewComponent {
    public ArrayList<Point> points;
    public int color=0xFF000000;
    public int width=10;
    /**
     * 若线段为圆滑曲线,则此值为圆滑角度,否则为0
     */
    public float radius;
    public ClosePointView(GameObject gameObject)
    {
        super(gameObject);
        points=new ArrayList<>(6);
    }

    public ClosePointView(GameObject gameObject,Point...fs)
    {
        super(gameObject);
        points=new ArrayList<>();
        points.addAll(Arrays.asList(fs));
    }

    public ClosePointView(GameObject gameObject,int color,int width,int radius,Point...fs)
    {
        super(gameObject);
        points=new ArrayList<>();
        points.addAll(Arrays.asList(fs));
        this.color=color;
        this.width=width;
        this.radius=radius;
    }

    public void addPoints(Point...fs)
    {
        points.addAll(Arrays.asList(fs));
    }

    @Override
    public void onRemove() {

    }
}
