package Scripts;

import android.graphics.Bitmap;
import android.util.Log;

import com.example.collideandattack.R;

import Bases.Position;
import Components.UpdateComponent;
import Components.ViewComponents.BitmapView;
import Events.CollideEvent;
import Events.Event;
import Prefabs.GameObject;
import Systems.EventSystem;
import Systems.SystemManager;

public class StartTriangle extends UpdateComponent {
    Position objPostion;
    Bitmap[] bitmaps=new Bitmap[15];
    int index;
    BitmapView bitmapView;
    float delta=0;
    public StartTriangle(GameObject gameObject) {
        super(gameObject);
        objPostion=gameObject.getObjectPosition().position;
        for(index=0;index<=14;index++)
        {
            bitmaps[index]=SystemManager.getStorage().getBitmapFromAssets("anim"+index,"drawable", Bitmap.Config.ARGB_8888);
        }
        bitmapView=(BitmapView)gameObject.getComponent("BitmapView").get(0);
        index=0;
        EventSystem.newEventListenerList("AnimOver");
    }

    @Override
    public void Update(float deltaMillisecond) {
        delta+=deltaMillisecond;
        if(delta<70f)
            return;
        else
            delta=0;
        bitmapView.bitmap=bitmaps[index];
        index++;
        if(index==15)
        {
            EventSystem.pushEvent("AnimOver");
            removeThis();
        }
        /*if(objPostion.point.x<1200)
            objPostion.point.x+=deltaMillisecond*4;
        else
        {
            objPostion.point.x+=deltaMillisecond*4;
            objPostion.point.y+=deltaMillisecond*4;
        }*/
        //Log.d("Update",""+deltaMillisecond);
    }

    @Override
    public void collide(CollideEvent event) {

    }
}
