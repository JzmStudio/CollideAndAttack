package World;

import java.util.ArrayList;

import Prefabs.GameObject;

public class GameWorld {
    protected ArrayList<GameObject> objects;
    public GameWorld()
    {
        objects=new ArrayList<>();
    }

    public void addGameObject(GameObject object) {objects.add(object);}
    public void removeGameObject(GameObject object) {objects.remove(object);}

    public void releaseThisWorld()
    {
        for(GameObject obj:objects){
            obj.destroy();
        }
    }
}
