package Components.PhysicsComponents;

import Bases.Position;
import Components.PhysicsComponent;
import Prefabs.GameObject;

/**
 * 组件形式的Position
 */
public class Transform extends PhysicsComponent {
    public Position position;
    public Transform(GameObject gameObject) {
        super(gameObject);
        position=new Position();
    }
    public Transform(GameObject gameObject,Position position)
    {
        super(gameObject);
        this.position=position;
    }
}
