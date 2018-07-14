package Android;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import java.util.ArrayList;
import Bases.ObjectPool;
import Events.TouchEvent;
import Systems.SystemManager;

public class AndroidTouchListener implements View.OnTouchListener {
    private ObjectPool<TouchEvent> eventPool;
    private ArrayList<TouchEvent> touchList;
    private ArrayList<TouchEvent> touchListBuffer;
    private int maxTouchCount=3;    //最多触屏点

    private float scaleX;
    private float scaleY;

    public AndroidTouchListener(int maxTouchPoints,float scaleX,float scaleY) {
        Log.d("InputScale",""+scaleX+" "+scaleY);
        eventPool=new ObjectPool<TouchEvent>(new ObjectPool.CreateConstructor<TouchEvent>(){

            @Override
            public TouchEvent newObject() {
                return new TouchEvent();
            }}, 50);

        touchList=new ArrayList<TouchEvent>();
        touchListBuffer=new ArrayList<TouchEvent>();
        this.maxTouchCount=maxTouchPoints;
        this.scaleX=scaleX;
        this.scaleY=scaleY;
        SystemManager.getMainActivity().getUpdateView().setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        int pointerCount=event.getActionIndex();
        if(pointerCount>maxTouchCount)
            return true;

        TouchEvent touchEvent=eventPool.creat();

        switch(event.getActionMasked())
        {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:
                touchEvent.point.x=event.getX(event.getActionIndex())*scaleX;
                touchEvent.point.y=event.getY(event.getActionIndex())*scaleY;
                touchEvent.type=TouchEvent.Touch_Down;
                touchEvent.ID=event.getPointerId(event.getActionIndex());
                touchList.add(touchEvent);
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                touchEvent.point.x=event.getX(event.getActionIndex())*scaleX;
                touchEvent.point.y=event.getY(event.getActionIndex())*scaleY;
                touchEvent.type=TouchEvent.Touch_Up;
                touchEvent.ID=event.getPointerId(event.getActionIndex());
                touchList.add(touchEvent);
                break;
            case MotionEvent.ACTION_MOVE:
                touchEvent.point.x=event.getX(event.getActionIndex())*scaleX;
                touchEvent.point.y=event.getY(event.getActionIndex())*scaleY;
                touchEvent.type=TouchEvent.Touch_Move;
                touchEvent.ID=event.getPointerId(event.getActionIndex());
                touchList.add(touchEvent);
                break;
        }

        return true;
    }

    public synchronized ArrayList<TouchEvent> getTouchList()
    {
        int len=touchListBuffer.size();
        for(int i=0;i<len;i++)
            eventPool.free(touchListBuffer.get(i));
        touchListBuffer.clear();
        touchListBuffer.addAll(touchList);
        touchList.clear();
        return touchListBuffer;
    }

    public void changeScale(float scaleX,float scaleY)
    {
        this.scaleX=scaleX;
        this.scaleY=scaleY;
    }
}