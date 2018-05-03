package Components.ViewComponents;

import java.util.ArrayList;

import Bases.Point;
import Components.Component;
import Prefabs.GameObject;

public class PointDescribe extends Component {
    ArrayList<Point> points;
    public PointDescribe(GameObject gameObject)
    {
        super(gameObject);
        points=new ArrayList<>(6);
    }

    public PointDescribe(GameObject gameObject,Point...fs)
    {
        super(gameObject);
        points=new ArrayList<>();
        for(Point p:fs)
        {
            points.add(p);
        }
    }

    public void addPoints(Point...fs)
    {
        for(Point p:fs)
        {
            points.add(p);
        }
    }

    @Override
    public void onRemove() {

    }
}
