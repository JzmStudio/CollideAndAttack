package Scripts;

import android.graphics.Bitmap;
import android.util.Log;

import com.example.collideandattack.R;

import Bases.Point;
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
    BitmapView beard;
    BitmapView eye;
    float delta=0;
    boolean isover=false,isright=false;
    Point init;
    public StartTriangle(GameObject gameObject) {
        super(gameObject);
        objPostion=gameObject.getObjectPosition().position;
        for(index=0;index<=14;index++)
        {
            bitmaps[index]=SystemManager.getStorage().getBitmapFromAssets("anim"+index,"drawable", Bitmap.Config.ARGB_8888);
        }
        bitmapView=(BitmapView)gameObject.getComponent("BitmapView").get(0);
        beard=new BitmapView(gameObject,R.drawable.beard, Bitmap.Config.ARGB_8888,2);
        beard.position.point.x=-5;
        eye=new BitmapView(gameObject,R.drawable.eye, Bitmap.Config.ARGB_8888,2);
        gameObject.addComponent(beard);
        gameObject.addComponent(eye);
        index=0;
        EventSystem.newEventListenerList("AnimOver");
    }

    @Override
    public void Update(float deltaMillisecond) {
        delta+=deltaMillisecond;
        if(index<=14)
        {
            bitmapView.bitmap=bitmaps[index];
            index++;
            return;
        }
        if(!isover)
        {
            if(isright)
            {
                beard.position.point.x+=deltaMillisecond*0.05;
                //if(beard.position.point.x)
            }
        }
    }

    @Override
    public void collide(CollideEvent event) {

    }
}
