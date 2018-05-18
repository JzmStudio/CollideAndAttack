package Scripts;

import Components.UpdateComponent;
import Components.ViewComponents.BitmapView;
import Events.CollideEvent;
import Events.Event;
import Interfaces.EventAction;
import Prefabs.GameObject;
import Systems.EventSystem;

public class FontScript extends UpdateComponent implements EventAction{
    BitmapView start;
    public FontScript(GameObject gameObject)
    {
        super(gameObject);
        EventSystem.registerToEventList("AnimOver",this);
        start= (BitmapView) gameObject.getComponent("BitmapView").get(0);
        start.isVisible=false;
    }

    @Override
    public void Update(float deltaMillisecond) {

    }

    @Override
    public void collide(CollideEvent event) {

    }

    @Override
    public void action(Event e) {
        start.isVisible=true;
        removeThis();
    }
}
