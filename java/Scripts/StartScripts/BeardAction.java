package Scripts.StartScripts;

import android.util.Log;

import Components.UpdateComponent;
import Components.ViewComponents.BitmapView;
import Events.CollideEvent;
import Events.Event;
import Interfaces.EventAction;
import Prefabs.GameObject;
import Systems.EventSystem;
import Systems.SystemManager;

public class BeardAction extends UpdateComponent implements EventAction {
    private boolean front=true;
    private float startx;
    private float dis=20;
    private float delta=120; //动画播放时间
    private float per;

    private float sum;  //预加载等待时间

    private boolean start1=true;
    private boolean start2=false;

    private BitmapView bitmapView;

    public BeardAction(GameObject gameObject) {
        super(gameObject);
        per=dis/delta/2;
        Log.d("per",""+per);
        startx=gameObject.getObjectPosition().position.point.x;
        EventSystem.newEventListenerList("BEARDOVER");
        EventSystem.registerToEventList("EYEOVER",this);
        bitmapView= (BitmapView) gameObject.getComponent("BitmapView").get(0);
    }

    @Override
    public void Update(float deltaMillisecond) {
        if(sum<500)
        {
            sum+=deltaMillisecond;
            return;
        }
        if(start1)
        {
            if(front)
            {
                gameObject.getObjectPosition().position.point.x+=per*deltaMillisecond;
                if(gameObject.getObjectPosition().position.point.x>dis+startx) {
                    gameObject.getObjectPosition().position.point.x = 10;
                    front=false;
                }
                //Log.e("Front","f");
            }
            else
            {
                //Log.e("Back","f");
                gameObject.getObjectPosition().position.point.x-=per*deltaMillisecond;
                if(gameObject.getObjectPosition().position.point.x<startx) {
                    gameObject.getObjectPosition().position.point.x = startx;
                    EventSystem.pushEvent("BEARDOVER");
                    //Log.e("Beard","Over");
                    start1=false;
                    //removeThis();
                }
            }
        }
        if(start2)
        {
            bitmapView.alpha= (int) (bitmapView.alpha-0.2f*deltaMillisecond);
            if(bitmapView.alpha<0)
            {
                bitmapView.alpha=0;
                bitmapView.draw=false;
                removeThis();
            }
        }
    }

    @Override
    public void collide(CollideEvent event) {

    }

    @Override
    public void action(Event e) {
        start2=true;
    }
}
