package Scripts.StartScripts;

import android.util.Log;

import java.util.ArrayList;

import Components.UpdateComponent;
import Components.ViewComponents.ScreenTextView;
import Components.ViewComponents.TextView;
import Events.CollideEvent;
import Events.Event;
import Interfaces.EventAction;
import Interfaces.Screen;
import Prefabs.GameObject;
import Systems.EventSystem;
import Systems.SystemManager;
import World.Start;

public class FontAction extends UpdateComponent implements EventAction {

    ScreenTextView screenTextView;
    private boolean start=false;
    public FontAction(GameObject gameObject) {
        super(gameObject);
        screenTextView= (ScreenTextView) gameObject.getComponent("ScreenTextView").get(0);
        EventSystem.registerToEventList("SHOWTEXT",this);
    }

    @Override
    public void Update(float deltaMillisecond) {
        if(start)
        {
            screenTextView.draw=true;
            ArrayList arrayList=SystemManager.getInputSystem().getTouchList();
            if(arrayList.size()!=0)
            {
                Log.d("Touch",""+arrayList.size());
                SystemManager.changeGameWorld(new Start());
            }
        }
    }

    @Override
    public void collide(CollideEvent event) {

    }

    @Override
    public void action(Event e) {
        start=true;
    }
}
