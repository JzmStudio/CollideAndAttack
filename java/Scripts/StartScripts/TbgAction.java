package Scripts.StartScripts;

import Components.UpdateComponent;
import Components.ViewComponents.BitmapView;
import Events.CollideEvent;
import Events.Event;
import Interfaces.EventAction;
import Prefabs.GameObject;
import Systems.EventSystem;

public class TbgAction extends UpdateComponent implements EventAction {

    private BitmapView bitmapView;
    private boolean start=false;
    public TbgAction(GameObject gameObject) {
        super(gameObject);
        bitmapView= (BitmapView) this.gameObject.getComponent("BitmapView").get(0);
        EventSystem.registerToEventList("EYEOVER",this);
        EventSystem.newEventListenerList("SHOWTEXT");
    }

    @Override
    public void Update(float deltaMillisecond) {
        if(start)
        {
            bitmapView.alpha= (int) (bitmapView.alpha-0.2f*deltaMillisecond);
            if(bitmapView.alpha<0)
            {
                bitmapView.alpha=0;
                bitmapView.draw=false;
                EventSystem.pushEvent("SHOWTEXT");
                removeThis();
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
