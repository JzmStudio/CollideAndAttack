package World;

import java.util.ArrayList;

import Prefabs.GameObject;

public class GameWorld {
    protected ArrayList<GameObject> objects;
    public GameWorld()
    {
        objects=new ArrayList<>();
    }

    /**
     * 返回加入的GameObject
     * @param object
     * @return
     */
    public GameObject addGameObject(GameObject object)
    {
        objects.add(object);
        return object;
    }
    public void removeGameObject(GameObject object) {objects.remove(object);}

    public void releaseThisWorld()
    {
        for(GameObject obj:objects){
            obj.destroy();
        }
    }
}
