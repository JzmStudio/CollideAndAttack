package Scripts.StartScripts;

import Components.UpdateComponent;
import Components.ViewComponents.ScreenTextView;
import Components.ViewComponents.TextView;
import Events.CollideEvent;
import Events.Event;
import Interfaces.EventAction;
import Interfaces.Screen;
import Prefabs.GameObject;
import Systems.EventSystem;

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
