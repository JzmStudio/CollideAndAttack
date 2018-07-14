package Android;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;

import Interfaces.CanUpdate;
import Interfaces.Start;
import Interfaces.UpdateView;

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
    private ArrayList<UpdateView> updateViewList;
    private SurfaceHolder holder;
    private ArrayList<Start> startListeners;
    private boolean isNew=true; //判断是否为游戏刚开始

    public AndroidUpdateView(Context context) {
        super(context);
        bitmap=Bitmap.createBitmap(targetWidth,targetHeight, Bitmap.Config.RGB_565);
        updateList=new ArrayList<>(5);
        updateViewList=new ArrayList<>(2);
        startListeners=new ArrayList<>(2);
        holder=getHolder();
    }

    public void resume()
    {
        if(isNew)
        {
            isNew=false;
            for(Start o:startListeners)
                o.start();
        }
        isRun=true;
        this.updateThread=new Thread(this);
        updateThread.start();
    }

    @Override
    public void run() {
        long startTime = System.currentTimeMillis();
        long deltaTime;
        //int i=0,j=0;
        while(isRun)
        {
            if(!holder.getSurface().isValid()) {/*Log.d("UpdateFFF",""+j);
                j++;*/
//                try {
//                    Thread.sleep(100);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                continue; }
            deltaTime = System.currentTimeMillis()-startTime;
            if(deltaTime<updateInMillis)
            {
                try {
                    Thread.sleep(updateInMillis-deltaTime);
                    //Log.d("Sleep",""+(updateInMillis-deltaTime));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            //Log.d("Update",""+i);
            //i++;
            /*更新游戏逻辑*/
            for(CanUpdate o:updateList) {
                //Log.d("UC",""+updateList.size());
                o.update(System.currentTimeMillis() - startTime);
                //Log.d("UCOver",""+updateList.size());
            }

            /*更新游戏视图*/
            canvas=holder.lockCanvas();
            //canvas.drawColor(0xF10000FF);
            for(UpdateView u:updateViewList){
                //Log.d("UV",""+updateViewList.size());
                u.updateView(canvas);
                //Log.d("UVOver",""+updateViewList.size());
            }
            holder.unlockCanvasAndPost(canvas);

            startTime=System.currentTimeMillis();
        }
        //Log.e("Update","Stop!");
    }

    public void pause()
    {
        isRun=false;
    }

    public void stop()
    {
        isRun=false;
        while(true)
        {
            try{
                updateThread.join();
            }catch(InterruptedException e)
            {

            }
        }
    }

    public void addToUpdateList(CanUpdate o)
    {
        updateList.add(o);
    }

    public void removeFromUpdateList(CanUpdate o)
    {
        updateList.remove(o);
    }

    public void addToUpdateViewList(UpdateView updateView)
    {
        updateViewList.add(updateView);
    }

    public void removeFromUpdateViewList(UpdateView updateView)
    {
        updateViewList.remove(updateView);
    }

    public void addToStartList(Start o)
    {
        startListeners.add(o);
    }

    public void removeFromStartList(Start o) {
        startListeners.remove(o);
    }
}