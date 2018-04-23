package Android;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.SurfaceView;

import java.util.ArrayList;

import Interfaces.CanUpdate;

public class AndroidUpdateView extends SurfaceView implements Runnable{
    private AndroidMain androidMain;
    private Bitmap bitmap;
    public Canvas canvas;
    final private int targetWidth = 1920;
    final private int targetHeight = 1080;
    private boolean isRun = false;
    private final long updateInMillis = 12;
    private Thread updateThread;
    private ArrayList<CanUpdate> updateList;

    public AndroidUpdateView(Context context) {
        super(context);
        bitmap=Bitmap.createBitmap(targetWidth,targetHeight, Bitmap.Config.RGB_565);
        canvas=new Canvas(bitmap);
        updateList=new ArrayList<>(5);
    }

    public void resume()
    {
        isRun=true;
        this.updateThread=new Thread(this);
        updateThread.run();
    }

    @Override
    public void run() {
        long startTime = System.currentTimeMillis();
        long deltaTime;
        while(isRun)
        {
            deltaTime = System.currentTimeMillis()-startTime;
            if(deltaTime<updateInMillis)
            {
                try {
                    Thread.sleep(updateInMillis-deltaTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            for(CanUpdate o:updateList)
            {
                o.update(System.currentTimeMillis()-startTime);
            }
            draw(canvas);

            startTime=System.currentTimeMillis();
        }
    }

    public void pause()
    {
        isRun=false;
    }

    public void addToUpdateList(CanUpdate o)
    {
        updateList.add(o);
    }

    public void removeFromUpdateList(CanUpdate o)
    {
        updateList.remove(o);
    }
}