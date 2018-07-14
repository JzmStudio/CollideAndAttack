package Scripts;

import android.text.method.Touch;
import android.util.Log;

import java.util.ArrayList;

import Components.UpdateComponent;
import Components.ViewComponents.BitmapView;
import Events.CollideEvent;
import Events.Event;
import Events.TouchEvent;
import Interfaces.EventAction;
import Prefabs.GameObject;
import Systems.EventSystem;
import Systems.SystemManager;
import World.GameWorld;
import World.Start;

public class FontScript extends UpdateComponent implements EventAction{
    BitmapView start;
    boolean isStart=false;
    public FontScript(GameObject gameObject)
    {
        super(gameObject);
        EventSystem.registerToEventList("AnimOver",this);
        start= (BitmapView) gameObject.getComponent("BitmapView").get(0);
        start.draw=false;
    }

    @Override
    public void Update(float deltaMillisecond) {
        if(isStart)
        {
            ArrayList<TouchEvent> list= SystemManager.getInputSystem().getTouchList();
            if(list.size()!=0)
            {
                GameWorld next=new Start();
                SystemManager.changeGameWorld(next);
            }
        }
    }

    @Override
    public void collide(CollideEvent event) {

    }

    @Override
    public void action(Event e) {
        start.draw=true;
        isStart=true;
    }
}
