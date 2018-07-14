package Scripts.StartScripts;

import android.graphics.Bitmap;
import android.util.Log;

import com.example.collideandattack.R;

import Components.UpdateComponent;
import Components.ViewComponents.BitmapView;
import Events.CollideEvent;
import Events.Event;
import Interfaces.EventAction;
import Prefabs.GameObject;
import Systems.EventSystem;
import Systems.SystemManager;

public class EyeAction extends UpdateComponent implements EventAction{
    public boolean isOver=false;
    private boolean canStart=false;
    private boolean canStart2=false;
    private boolean canStart3=false;
    private float delta=0;
    private BitmapView eyeBitmap;
    private Bitmap eye2;

    private float sum=0;
    private final float waitTime=100;

    public EyeAction(GameObject gameObject) {
        super(gameObject);
        EventSystem.registerToEventList("BEARDOVER",this);
        EventSystem.newEventListenerList("EYEOVER");
        eyeBitmap= (BitmapView) this.gameObject.getComponent("BitmapView").get(0);
        //Load eye2 bitmap to memory
        eye2= SystemManager.getStorage().getBitmapFromAssets(R.drawable.eye2, Bitmap.Config.ARGB_8888);
    }

    @Override
    public void Update(float deltaMillisecond) {
        if(canStart)
        {
            delta+=deltaMillisecond;
            if(delta>100)
            {
                eyeBitmap.bitmap=eye2;
                canStart=false;
                canStart2=true;
                return;
            }
        }
        if(canStart2)
        {
            if(sum<waitTime) {sum+=deltaMillisecond; return;}
            EventSystem.pushEvent("EYEOVER");
            canStart2=false;
            canStart3=true;
            return;
        }
        if(canStart3)
        {
            eyeBitmap.alpha= (int) (eyeBitmap.alpha-0.1f*eyeBitmap.alpha);
            if(eyeBitmap.alpha<0)
            {
                eyeBitmap.alpha=0;
                eyeBitmap.draw=false;
                removeThis();
            }
        }
    }

    @Override
    public void collide(CollideEvent event) {

    }

    @Override
    public void action(Event e) {
        Log.e("Eye","Start");
        canStart=true;
    }
}
